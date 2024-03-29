package io.github.jnodorp.jiffybox.internal;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.jnodorp.jiffybox.builders.JiffyBoxBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link DefaultJiffyBoxBuilder}.
 */
class DefaultJiffyBoxBuilderTest {

    /**
     * Test for {@link DefaultJiffyBoxBuilder#fromDistribution(String, int, String)}.
     */
    @Test
    void testFromDistribution() {
        JiffyBoxBuilder builder = DefaultJiffyBoxBuilder.fromDistribution("test", 20, "ubuntu");
        assertEquals("{\"name\":\"test\",\"planid\":20,\"distribution\":\"ubuntu\"}", builder.toString());
    }

    /**
     * Test for {@link DefaultJiffyBoxBuilder#fromDistribution(String, String, String)}.
     */
    @Test
    void testFromDistribution1() {
        JiffyBoxBuilder builder = DefaultJiffyBoxBuilder.fromDistribution("test", "plan", "ubuntu");
        assertEquals("{\"name\":\"test\",\"planid\":\"plan\",\"distribution\":\"ubuntu\"}", builder.toString());
    }

    /**
     * Test for {@link DefaultJiffyBoxBuilder#fromBackup(String, int, String)}.
     */
    @Test
    void testFromBackup() {
        JiffyBoxBuilder builder = DefaultJiffyBoxBuilder.fromBackup("test", 20, "abc");
        assertEquals("{\"name\":\"test\",\"planid\":20,\"backupid\":\"abc\"}", builder.toString());
    }

    /**
     * Test for {@link DefaultJiffyBoxBuilder#fromBackup(String, String, String)}.
     */
    @Test
    void testFromBackup1() {
        JiffyBoxBuilder builder = DefaultJiffyBoxBuilder.fromBackup("test", "plan", "abc");
        assertEquals("{\"name\":\"test\",\"planid\":\"plan\",\"backupid\":\"abc\"}", builder.toString());
    }

    /**
     * Test for {@link DefaultJiffyBoxBuilder#withPassword(String)}.
     */
    @Test
    void testWithPassword() {
        JiffyBoxBuilder builder = new DefaultJiffyBoxBuilder();
        builder.withPassword("password");
        assertEquals("{\"password\":\"password\"}", builder.toString());
    }

    /**
     * Test for {@link DefaultJiffyBoxBuilder#useSshKey(boolean)}.
     */
    @Test
    void testUseSshKey() {
        JiffyBoxBuilder builder = new DefaultJiffyBoxBuilder();
        builder.useSshKey(true);
        assertEquals("{\"use_sshkey\":true}", builder.toString());
    }

    /**
     * Test for {@link DefaultJiffyBoxBuilder#withMetadata(ObjectNode)}.
     */
    @Test
    void testWithMetadata() {
        ObjectNode metadata = new ObjectNode(JsonNodeFactory.instance);
        metadata.put("key1", "value");
        metadata.put("key2", true);
        metadata.put("key3", 1);

        JiffyBoxBuilder builder = new DefaultJiffyBoxBuilder();
        builder.withMetadata(metadata);
        assertEquals("{\"metadata\":{\"key1\":\"value\",\"key2\":true,\"key3\":1}}", builder.toString());
    }
}
