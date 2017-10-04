package eu.df.jiffybox.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link ParameterTest}.
 */
public class ParameterTest {

    /**
     * Parameter description.
     */
    private static final String TEST_DESCRIPTION = "Parameter description.";

    /**
     * Parameter type.
     */
    private static final String TEST_TYPE = "Parameter type.";

    /**
     * Test method for {@link Parameter#toString()}.
     */
    @Test
    public void testToString() {
        Parameter parameter = new Parameter();
        parameter.setDescription(TEST_DESCRIPTION);
        parameter.setType(TEST_TYPE);

        ModelTestHelper.validateJson(parameter.toString());

        Parameter p = ModelTestHelper.jsonTo(Parameter.class, parameter.toString());

        assertEquals(TEST_DESCRIPTION, p.getDescription());
        assertEquals(TEST_TYPE, p.getType());
    }
}
