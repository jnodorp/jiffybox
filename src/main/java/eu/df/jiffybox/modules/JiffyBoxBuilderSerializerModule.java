package eu.df.jiffybox.modules;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import eu.df.jiffybox.builders.JiffyBoxBuilder;
import eu.df.jiffybox.internal.DefaultJiffyBoxBuilder;

import java.io.IOException;

/**
 * A custom {@link SimpleModule} providing a {@link JsonSerializer} for {@link JiffyBoxBuilder}s.
 *
 * @author Julian Schlichtholz
 */
final class JiffyBoxBuilderSerializerModule extends SimpleModule {

    JiffyBoxBuilderSerializerModule() {
        super(JiffyBoxBuilderSerializerModule.class.getSimpleName(),
                new Version(1, 0, 0, "SNAPSHOT", "eu.df", "jiffybox"));

        addSerializer(JiffyBoxBuilder.class, new JsonSerializer<JiffyBoxBuilder>() {
            @Override
            public void serialize(JiffyBoxBuilder value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                ((DefaultJiffyBoxBuilder) value).serialize(gen, serializers);
            }
        });
    }
}
