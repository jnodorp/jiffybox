package eu.df.jiffybox.internal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for {@link eu.df.jiffybox.internal.DefaultBuilder}.
 */
class DefaultBuilderTest {

    /**
     * Test for {@link eu.df.jiffybox.internal.DefaultBuilder#put(String, String)}}.
     */
    @Test
    void testPutContactGroups() {
        final DefaultBuilder defaultBuilder = new DefaultBuilder() {
        };

        defaultBuilder.put("testKey", "testValue");
        assertEquals("{\"testKey\":\"testValue\"}", defaultBuilder.toString());
    }
}
