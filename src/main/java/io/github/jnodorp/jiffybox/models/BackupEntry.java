package io.github.jnodorp.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Backup entries consist of an id and a timestamp.
 */
@JsonAutoDetect
public class BackupEntry extends PrintableModel {

    /**
     * The id.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The creation timestamp.
     */
    @JsonProperty("created")
    private long created;

    /**
     * Get the backup entries id.
     *
     * @return The backup entries id.
     */
    @JsonGetter("id")
    public String getId() {
        return id;
    }

    /**
     * Set the id.
     *
     * @param id The id.
     */
    @JsonSetter("id")
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the backup entries creation timestamp.
     *
     * @return The backup entries creation timestamp.
     */
    @JsonGetter("created")
    public long getCreated() {
        return created;
    }

    /**
     * Set the creation timestamp.
     *
     * @param created The creation timestamp.
     */
    @JsonSetter("created")
    public void setCreated(final long created) {
        this.created = created;
    }
}
