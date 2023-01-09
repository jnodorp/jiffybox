package io.github.jnodorp.jiffybox.internal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for {@link DefaultBuilder}.
 */
class DefaultBuilderTest {

    /**
     * Test for {@link DefaultBuilder#put(String, String)}}.
     */
    @Test
    void testPutContactGroups() {
        final DefaultBuilder defaultBuilder = new DefaultBuilder() {
        };

        defaultBuilder.put("testKey", "testValue");
        assertEquals("{\"testKey\":\"testValue\"}", defaultBuilder.toString());
    }
}
