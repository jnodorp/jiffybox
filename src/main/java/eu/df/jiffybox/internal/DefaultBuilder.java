package eu.df.jiffybox.internal;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * This class offers methods for creating JSON data for put and post requests.
 */
abstract class DefaultBuilder extends ObjectNode {

    /**
     * Create a new object node.
     */
    DefaultBuilder() {
        super(JsonNodeFactory.instance);
    }
}
