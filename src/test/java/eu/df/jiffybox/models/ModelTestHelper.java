package eu.df.jiffybox.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * This utility can be used in tests to validate results.
 */
public class ModelTestHelper {

    /**
     * Validate a JSON object.
     */
    public static void validateJson(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException ex) {
            try {
                new JSONArray(json);
            } catch (JSONException ex1) {
                fail("\"" + json + "\" is not a valid JSON object.");
            }
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
            return new ObjectMapper().readValue(json, type);
        } catch (IOException e) {
            throw new AssertionError("\"" + json + "\" is not a valid JSON object.");
        }
    }
}
