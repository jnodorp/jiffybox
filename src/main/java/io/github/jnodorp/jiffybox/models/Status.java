package io.github.jnodorp.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This enumeration contains all possible states of a profile / JiffyBox.
 */
@JsonAutoDetect
public enum Status {

    /**
     * The profile / JiffyBox is deleting.
     */
    DELETING,

    /**
     * The profile / JiffyBox is running.
     */
    RUNNING,

    /**
     * The profile / JiffyBox is ready.
     */
    READY,

    /**
     * The profile / JiffyBox is creating.
     */
    CREATING,

    /**
     * The profile / JiffyBox is updating.
     */
    UPDATING,

    /**
     * The profile / JiffyBox is freezing.
     */
    FREEZING,

    /**
     * The profile / JiffyBox is thawing.
     */
    THAWING,

    /**
     * The profile / JiffyBox is frozen.
     */
    FROZEN,

    /**
     * The profile / JiffyBox is changing plan.
     */
    CHANGING_PLAN;

    /**
     * Convert a string to a message type.
     *
     * @param text The string.
     * @return The message type
     */
    @JsonCreator
    public static Status fromJson(String text) {
        return valueOf(text.replace(" ", "_"));
    }

    /**
     * Convert a message type to a string.
     *
     * @return The string.
     */
    @JsonValue
    public String toJson() {
        return name().replace("_", " ");
    }
}
