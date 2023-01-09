package io.github.jnodorp.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * A parameter consists of a type and a description.
 *
 * @see DocEntry
 */
@JsonAutoDetect
class Parameter extends PrintableModel {

    /**
     * The description.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The type.
     */
    @JsonProperty("type")
    private String type;

    /**
     * Get the description.
     *
     * @return The description.
     */
    @JsonGetter("description")
    public String getDescription() {
        return description;
    }

    /**
     * Set the description.
     *
     * @param description The description.
     */
    @JsonSetter("description")
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get the type.
     *
     * @return The type.
     */
    @JsonGetter("type")
    public String getType() {
        return type;
    }

    /**
     * Set the type.
     *
     * @param type The type.
     */
    @JsonSetter("type")
    public void setType(final String type) {
        this.type = type;
    }
}
