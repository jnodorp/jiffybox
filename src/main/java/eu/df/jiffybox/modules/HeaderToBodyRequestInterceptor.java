package eu.df.jiffybox.modules;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A {@link RequestInterceptor} to move temporary headers to the body.
 *
 * @author Julian Schlichtholz
 */
final class HeaderToBodyRequestInterceptor implements RequestInterceptor {

    /**
     * The {@link ObjectMapper}.
     */
    private final ObjectMapper objectMapper;

    /**
     * The {@link JavaType}.
     */
    private final JavaType javaType;

    /**
     * The header key.
     */
    private final String headerKey;

    /**
     * The body key.
     */
    private final String bodyKey;

    /**
     * Create a new instance
     *
     * @param headerKey the header key
     * @param bodyKey   the body key
     */
    HeaderToBodyRequestInterceptor(String headerKey, String bodyKey) {
        this.objectMapper = new ObjectMapper();
        this.javaType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class);
        this.headerKey = headerKey;
        this.bodyKey = bodyKey;
    }

    @Override
    public void apply(RequestTemplate template) {
        if (template.headers().containsKey(headerKey)) {
            String value = template.headers().get(headerKey).iterator().next();

            try {
                Map<String, Object> json;
                if (template.body() != null) {
                    json = objectMapper.readValue(template.body(), javaType);
                } else {
                    json = new HashMap<>();
                }

                json.put(bodyKey, value);
                template.body(objectMapper.writeValueAsString(json));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                template.header(headerKey, new String[]{null});
            }
        }
    }
}
