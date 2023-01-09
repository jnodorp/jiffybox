package io.github.jnodorp.jiffybox.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import java.io.IOException;
import java.util.List;

public class ResponseDeserializer extends JsonDeserializer<Response<?>>
    implements ContextualDeserializer {

  private JavaType resultType;

  @Override
  public JsonDeserializer<Response<?>> createContextual(DeserializationContext ctx, BeanProperty property) {
    final JavaType responseType;
    if (property == null) {
      responseType = ctx.getContextualType();
    } else {
      responseType = property.getType();
    }

    ResponseDeserializer deserializer = new ResponseDeserializer();
    deserializer.resultType = responseType.containedType(0);
    return deserializer;
  }

  @Override
  public Response<?> deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
    ObjectMapper mapper = (ObjectMapper) p.getCodec();
    JsonNode root = mapper.readTree(p);
    JsonNode messages = root.get("messages");
    JsonNode result = root.get("result");

    final Response<?> response = new Response<>();

    JavaType msgType = ctx.getTypeFactory().constructCollectionLikeType(List.class, Message.class);
    response.setMessages(mapper.convertValue(messages, msgType));

    // If we have a boolean but the result is not expected to be a boolean (happens on errors)
    // set the result to null.
    if (result.isBoolean() && !this.resultType.getRawClass().isAssignableFrom(Boolean.class)) {
      response.setResult(null);
    } else {
      response.setResult(mapper.convertValue(result, this.resultType));
    }

    return response;
  }
}
