package io.github.jnodorp.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This enumeration provides all possible message types.
 *
 * @see Message
 */
@JsonAutoDetect
public enum MessageType {

    /**
     * The message contains an error.
     */
    ERROR,

    /**
     * The message contains a warning.
     */
    WARNING,

    /**
     * The message contains a notification.
     */
    NOTICE,

    /**
     * The message is a success message.
     */
    SUCCESS;

    /**
     * Convert a string to a message type.
     *
     * @param text The string.
     * @return The message type
     */
    @JsonCreator
    public static MessageType fromJson(String text) {
        return valueOf(text.toUpperCase());
    }

    /**
     * Convert a message type to a string.
     *
     * @return The string.
     */
    @JsonValue
    public String toJson() {
        return name().toLowerCase();
    }
}
