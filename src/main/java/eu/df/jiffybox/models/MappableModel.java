package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Mappable model are returned as a map with a key. The key is then flattened for better usability.
 */
public abstract class MappableModel extends PrintableModel {

    /**
     * The key.
     */
    @JsonIgnore
    private String key;

    /**
     * Get the key.
     *
     * @return The key.
     */
    @JsonIgnore
    public String getKey() {
        return key;
    }

    /**
     * Set the key.
     *
     * @param key The key.
     */
    @JsonIgnore
    public void setKey(final String key) {
        this.key = key;
    }
}
