package eu.df.jiffybox.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * This utility can be used in tests to validate results.
 */
class ModelTestHelper {

    /**
     * The {@link ObjectMapper}.
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Validate a JSON string.
	 *
	 * @param json the JSON string
	 * @throws AssertionError if JSON is invalid
     */
    public static void validateJson(String json) throws AssertionError {
        try {
            OBJECT_MAPPER.readTree(json);
        } catch (IOException e) {
            fail("'" + json + "' is not a valid JSON object.");
        }
    }

    /**
     * Convert a JSON string back to the given object.
     *
     * @param type The returned objects class.
     * @param json The JSON string.
     * @param <T>  The object to return.
     * @return The object constructed from the JSON string.
     */
    public static <T> T jsonTo(Class<T> type, String json) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new AssertionError("\"" + json + "\" is not a valid JSON object.");
        }
    }
}
