package eu.df.jiffybox.internal;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

/**
 * This class offers methods for creating JSON data for put and post requests.
 */
abstract class DefaultBuilder extends ObjectNode {

    /**
     * Create a new object node and hide its methods.
     */
    DefaultBuilder() {
        super(JsonNodeFactory.instance);
    }

    /**
     * Add a list of contact groups to the built object.
     *
     * @param v The value.
     */
    void putContactGroups(final List<String> v) {
        ArrayNode array = putArray("contactGroups");
        for (String contactGroup : v) {
            array.add(contactGroup);
        }
    }
}
