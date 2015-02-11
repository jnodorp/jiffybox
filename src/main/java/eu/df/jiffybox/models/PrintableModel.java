package eu.df.jiffybox.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is implemented by each model which will return a valid JSON
 * representation of itself whenever the toString method is invoked.
 */
abstract class PrintableModel {

    /**
     * Try to return the object as JSON.
     *
     * @return The objects JSON representation.
     */
    @Override
    public final String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter()
                                     .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new UnsupportedOperationException("Unable to generate a " +
                    "string from this object.", e);
        }
    }
}
