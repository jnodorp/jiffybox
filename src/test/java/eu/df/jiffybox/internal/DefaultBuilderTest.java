package eu.df.jiffybox.internal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link eu.df.jiffybox.internal.DefaultBuilder}.
 */
public class DefaultBuilderTest {

    /**
     * Test for {@link eu.df.jiffybox.internal.DefaultBuilder#put(String, String)}}.
     */
    @Test
    public void testPutContactGroups() throws Exception {
        final DefaultBuilder defaultBuilder = new DefaultBuilder() {
        };

        defaultBuilder.put("testKey", "testValue");
        assertEquals("{\"testKey\":\"testValue\"}", defaultBuilder.toString());
    }
}