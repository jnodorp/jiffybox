package eu.df.jiffybox.internal;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Test for {@link eu.df.jiffybox.internal.DefaultBuilder}.
 */
public class DefaultBuilderTest {

    /**
     * Test for {@link eu.df.jiffybox.internal
     * .DefaultBuilder#putContactGroups(java.util.List)}.
     */
    @Test
    public void testPutContactGroups() throws Exception {
        DefaultBuilder defaultBuilder = new DefaultBuilder() {
        };

        List<String> contactGroups = new ArrayList<>();
        contactGroups.add("Contact group 1");
        contactGroups.add("Contact group 2");

        defaultBuilder.putContactGroups(contactGroups);

        assertTrue(defaultBuilder.toString().contains("Contact group 1"));
        assertTrue(defaultBuilder.toString().contains("Contact group 2"));
    }
}