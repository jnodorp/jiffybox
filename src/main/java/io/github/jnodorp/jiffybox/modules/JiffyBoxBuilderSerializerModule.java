package io.github.jnodorp.jiffybox.modules;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.jnodorp.jiffybox.builders.JiffyBoxBuilder;
import io.github.jnodorp.jiffybox.internal.DefaultJiffyBoxBuilder;

import java.io.IOException;

/**
 * A custom {@link SimpleModule} providing a {@link JsonSerializer} for {@link JiffyBoxBuilder}s.
 *
 * @author Julian Nodorp
 */
final class JiffyBoxBuilderSerializerModule extends SimpleModule {

    JiffyBoxBuilderSerializerModule() {
        super(JiffyBoxBuilderSerializerModule.class.getSimpleName(),
                new Version(1, 0, 0, "SNAPSHOT", "io.github.jnodorp", "jiffybox"));

        addSerializer(JiffyBoxBuilder.class, new JsonSerializer<JiffyBoxBuilder>() {
            @Override
            public void serialize(JiffyBoxBuilder value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                ((DefaultJiffyBoxBuilder) value).serialize(gen, serializers);
            }
        });
    }
}
