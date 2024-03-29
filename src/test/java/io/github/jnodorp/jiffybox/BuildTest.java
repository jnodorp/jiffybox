package io.github.jnodorp.jiffybox;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test for {@link Build}.
 */
class BuildTest {

    /**
     * Make sure an exception is thrown when trying to create an instance of the
     * {@link Build} class.
     */
    @Test
    void testPrivateConstructor_AssertionError() {
        assertThrows(Exception.class, () -> {
            Constructor<Build> constructor = Build.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }

    /**
     * Test for {@link Build#monitoringCheck(String, String, Integer)}.
     */
    @Test
    void testMonitoringCheck() {
        assertNotNull(Build.monitoringCheck("", "", 0));
    }

    /**
     * Test for {@link Build#jiffyBox(String, String)}.
     */
    @Test
    void testJiffyBox() {
        assertNotNull(Build.jiffyBox("", ""));
    }

    /**
     * Test for {@link Build#jiffyBox(String, int)}.
     */
    @Test
    void testJiffyBox1() {
        assertNotNull(Build.jiffyBox("", 0));
    }

    /**
     * Test for {@link Build#metadata()}.
     */
    @Test
    void testMetadata() {
        assertNotNull(Build.metadata());
    }
}
