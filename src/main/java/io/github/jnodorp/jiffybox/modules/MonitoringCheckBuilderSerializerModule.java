package io.github.jnodorp.jiffybox.modules;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.jnodorp.jiffybox.builders.MonitoringCheckBuilder;
import io.github.jnodorp.jiffybox.internal.DefaultMonitoringCheckBuilder;

import java.io.IOException;

/**
 * A custom {@link SimpleModule} providing a {@link JsonSerializer} for {@link MonitoringCheckBuilder}s.
 *
 * @author Julian Nodorp
 */
final class MonitoringCheckBuilderSerializerModule extends SimpleModule {

    MonitoringCheckBuilderSerializerModule() {
        super(MonitoringCheckBuilderSerializerModule.class.getSimpleName(),
                new Version(1, 0, 0, "SNAPSHOT", "io.github.jnodorp", "jiffybox"));

        addSerializer(MonitoringCheckBuilder.class, new JsonSerializer<>() {
            @Override
            public void serialize(MonitoringCheckBuilder value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                ((DefaultMonitoringCheckBuilder) value).serialize(gen, serializers);
            }
        });
    }
}
