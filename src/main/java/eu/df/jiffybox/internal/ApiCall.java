package eu.df.jiffybox.internal;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import eu.df.jiffybox.models.Response;
import eu.df.jiffybox.models.MappableModel;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Make an API call. This class is a builder at the same time.
 */
final class ApiCall {

    /**
     * 'application/json' string for headers.
     */
    private static final String APPLICATION_JSON = "application/json";

    /**
     * Parameter 'metadata'.
     */
    private static final String PARAMETER_METADATA = "metadata";

    /**
     * Parameter 'contacts'.
     */
    private static final String PARAMETER_CONTACTS = "contacts";

    /**
     * The {@link ObjectMapper} used to convert classes to JSON and vice versa.
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * The RequestBuilder used to build the request.
     */
    private final RequestBuilder requestBuilder;

    /**
     * The call URI.
     */
    private final URI uri;

    /**
     * The POST/PUT parameters as JSON.
     */
    private ObjectNode json;

    /**
     * The api calls URI path after the base URI.
     */
    private String path;

    /**
     * The response type.
     */
    private JavaType type;

    /**
     * Construct a new APICall using the given RequestBuilder and base URI.
     *
     * @param requestBuilder The request builder.
     * @param baseUri        The base URI.
     */
    private ApiCall(final RequestBuilder requestBuilder, final URI baseUri) {
        this.requestBuilder = requestBuilder;
        this.uri = baseUri;
        this.path = "";
        this.json = JsonNodeFactory.instance.objectNode();
    }

    /**
     * Create a DELETE API call.
     *
     * @param baseUri The base uri.
     * @return The new API call.
     */
    public static ApiCall delete(final URI baseUri) {
        return new ApiCall(RequestBuilder.delete(), baseUri);
    }

    /**
     * Create a GET API call.
     *
     * @param baseUri The base uri.
     * @return The new API call.
     */
    public static ApiCall get(final URI baseUri) {
        return new ApiCall(RequestBuilder.get(), baseUri);
    }

    /**
     * Create a POST API call.
     *
     * @param baseUri The base uri.
     * @return The new API call.
     */
    public static ApiCall post(final URI baseUri) {
        return new ApiCall(RequestBuilder.post(), baseUri);
    }

    /**
     * Create a PUT API call.
     *
     * @param baseUri The base uri.
     * @return The new API call.
     */
    public static ApiCall put(final URI baseUri) {
        return new ApiCall(RequestBuilder.put(), baseUri);
    }

    /**
     * Add another part of the URI. This will be prefixed by a slash automatically.
     *
     * @param part The URI part to append.
     * @return The updated APICall.
     */
    public ApiCall appendPath(final String part) {
        path += "/" + part.replace(" ", "%20");
        return this;
    }

    /**
     * Add another part of the URI. This will be prefixed by a slash automatically.
     *
     * @param part The URI part to append.
     * @return The updated APICall.
     */
    public ApiCall appendPath(final int part) {
        path += "/" + part;
        return this;
    }

    /**
     * Add a parameter to the request data.
     *
     * @param key   The parameter name.
     * @param value The parameter value.
     * @return The updated API call.
     */
    public ApiCall addParameter(final String key, final String value) {
        json.put(key, value);
        return this;
    }

    /**
     * Add a parameter to the request data.
     *
     * @param key   The parameter name.
     * @param value The parameter value.
     * @return The updated API call.
     */
    public ApiCall addParameter(final String key, final int value) {
        json.put(key, value);
        return this;
    }

    /**
     * Add a parameter to the request data.
     *
     * @param metadata The metadata.
     * @return The updated API call.
     */
    public ApiCall addMetadata(final ObjectNode metadata) {
        json.putObject(PARAMETER_METADATA).setAll(metadata);
        return this;
    }

    /**
     * Set the request data.
     *
     * @param data The new parameters.
     * @return The updated API call.
     */
    public ApiCall setParameters(final ObjectNode data) {
        json = data;
        return this;
    }

    /**
     * Add contacts to the request data.
     *
     * @param contacts The contacts to add.
     * @return The updated API call.
     */
    ApiCall addContacts(final List<String> contacts) {
        ArrayNode list = json.putArray(PARAMETER_CONTACTS);
        contacts.forEach(list::add);
        return this;
    }

    /**
     * Define the responses result as plain type.
     *
     * @return The response.
     * @throws java.io.IOException Either the API limit is reached or the host is unavailable.
     */
    public <T> Response<T> as(final Class type) throws IOException {
        return ok(text -> {
            JavaType rt = TypeFactory.defaultInstance().constructParametrizedType(Response.class, Response.class, type);
            JavaType et = TypeFactory.defaultInstance().constructParametrizedType(ErrorResponse.class, ErrorResponse
                    .class, type);
            return safeRead(text, rt, et);
        });
    }

    /**
     * Execute the API call. Note, that the response type needs to be set manually. {@see Response#as(Class)} {@see
     * Response#asMap(Class, Class)} {@see Response#asList(Class)}
     *
     * @throws java.io.IOException Either the API limit is reached or the host is unavailable.
     */
    private <R> R ok(Function<String, R> function) throws IOException {
        requestBuilder.setHeader("Connection", "Close");
        requestBuilder.setHeader("Accept", APPLICATION_JSON);
        requestBuilder.setUri(URI.create(uri + path));

        if (!HttpGet.METHOD_NAME.equals(requestBuilder.getMethod())) {
            StringEntity input = new StringEntity(new ObjectMapper().writeValueAsString(json));
            input.setContentType(APPLICATION_JSON);
            requestBuilder.setEntity(input);
        }

        HttpUriRequest request = requestBuilder.build();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = httpClient.execute(request);

        HttpEntity entity = httpResponse.getEntity();
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
        String text = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        R result = function.apply(text);

        EntityUtils.consume(entity);
        httpResponse.close();
        httpClient.close();

        return result;
    }

    /**
     * Define the responses result as list.
     *
     * @return The response.
     * @throws java.io.IOException Either the API limit is reached or the host is unavailable.
     */
    public <T extends MappableModel> Response<List<T>> asList(final Class elements) throws IOException {
        this.type = TypeFactory.defaultInstance().constructMapType(Map.class, String.class, elements);
        return ok(text -> {
            JavaType rt = TypeFactory.defaultInstance().constructParametrizedType(Response.class, Response.class, type);
            JavaType et = TypeFactory.defaultInstance().constructParametrizedType(ErrorResponse.class, ErrorResponse
                    .class, type);
            Response<Map<String, T>> mapResponse = safeRead(text, rt, et);

            Response<List<T>> listResponse = new Response<>();
            final List<T> result = new ArrayList<T>();
            if (mapResponse.getResult() != null) {
                mapResponse.getResult().entrySet().forEach(stringTEntry -> {
                    T element = stringTEntry.getValue();
                    element.setKey(stringTEntry.getKey());
                    result.add(element);
                });
            }

            listResponse.setResult(result);
            listResponse.setMessages(mapResponse.getMessages());
            return listResponse;
        });
    }

    /**
     * Define the responses result as map.
     *
     * @return The response.
     * @throws java.io.IOException Either the API limit is reached or the host is unavailable.
     */
    public <T> Response<Map<String, T>> asMap(final Class key, final Class value) throws IOException {
        this.type = TypeFactory.defaultInstance().constructMapType(Map.class, key, value);
        return ok(text -> {
            JavaType rt = TypeFactory.defaultInstance().constructParametrizedType(Response.class, Response.class, type);
            JavaType et = TypeFactory.defaultInstance().constructParametrizedType(ErrorResponse.class, ErrorResponse
                    .class, type);
            return safeRead(text, rt, et);
        });
    }

    private <X> X safeRead(String text, JavaType resultType, JavaType errorType) {
        X result;
        try {
            result = MAPPER.readValue(text, resultType);
        } catch (JsonMappingException ex) {
            try {
                result = MAPPER.readValue(text, errorType);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return result;
    }
}
