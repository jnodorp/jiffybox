package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test for {@link eu.df.jiffybox.models.PrintableModel}.
 */
public class PrintableModelTest {

    /**
     * Check if the toString method returns a valid JSON string if there are
     * properties present.
     */
    @Test
    public void testToStringValidObject() {
        ModelTestHelper.validateJson(new PrintableModel() {
            @JsonProperty("property")
            private String property = "value";
        }.toString());
    }

    /**
     * Check if the toString method throws an exception if it cannot create a
     * valid JSON string.
     */
    @Test
    public void testToStringInvalidObject() {
        assertThrows(UnsupportedOperationException.class, () -> {
            ModelTestHelper.validateJson(new PrintableModel() {
            }.toString());
        });
    }
}
