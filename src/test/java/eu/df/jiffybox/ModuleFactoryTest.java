package eu.df.jiffybox;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertNotNull;

/**
 * Test for {@link ModuleFactory}.
 */
public class ModuleFactoryTest {

    /**
     * Make sure an exception is thrown when trying to create an instance of the
     * {@link ModuleFactory} class.
     *
     * @throws Exception Expect an exception.
     */
    @Test(expected = Exception.class)
    public void testPrivateConstructor_AssertionError() throws Exception {
        Constructor<ModuleFactory> constructor = ModuleFactory.class
                .getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    /**
     * Test if {@link ModuleFactory#getInstance(String)} returns something else
     * than null.
     */
    @Test
    public void testCreate() {
        assertNotNull(ModuleFactory.getInstance(""));
    }

    /**
     * Test if {@link ModuleFactory#getInstance(String, String)} returns
     * something else than null.
     */
    @Test
    public void testTest() {
        assertNotNull(ModuleFactory.getInstance("", ""));
    }
}