package eu.df.jiffybox.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static org.junit.Assert.fail;

/**
 * This utility can be used in tests to validate results.
 */
public class Validate {

    /**
     * Validate a JSON object.
     */
    public static void json(String json) {
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
}
