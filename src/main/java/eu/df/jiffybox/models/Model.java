package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * This class is used only to reduce code duplication.
 */
abstract class Model extends PrintableModel {

    /**
     * The id.
     */
    @JsonProperty("id")
    private int id;

    /**
     * The name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Get the id.
     *
     * @return The id.
     */
    @JsonGetter("id")
    public int getId() {
        return id;
    }

    /**
     * Set the id.
     *
     * @param id The id.
     */
    @JsonSetter("id")
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Get the name.
     *
     * @return The name.
     */
    @JsonGetter("name")
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     *
     * @param name The name.
     */
    @JsonSetter("name")
    public void setName(final String name) {
        this.name = name;
    }
}
