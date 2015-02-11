package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * A disk consists of a name, a filesystem, a size (in MB), a creation
 * timestamp, a status and a distribution.
 */
@JsonAutoDetect
public class Disk extends PrintableModel {

    /**
     * The name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The filesystem.
     */
    @JsonProperty("filesystem")
    private String filesystem;

    /**
     * The size in MB.
     */
    @JsonProperty("sizeInMB")
    private int sizeInMB;

    /**
     * The creation timestamp.
     */
    @JsonProperty("created")
    private long created;

    /**
     * The status.
     */
    @JsonProperty("status")
    private String status;

    /**
     * The distribution.
     */
    @JsonProperty("distribution")
    private String distribution;

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

    /**
     * Get the filesystem.
     *
     * @return The filesystem.
     */
    @JsonGetter("filesystem")
    public String getFilesystem() {
        return filesystem;
    }

    /**
     * Set the filesystem.
     *
     * @param filesystem The filesystem.
     */
    @JsonSetter("filesystem")
    public void setFilesystem(final String filesystem) {
        this.filesystem = filesystem;
    }

    /**
     * Get the size in MB.
     *
     * @return The size in MB.
     */
    @JsonGetter("sizeInMB")
    public int getSizeInMB() {
        return sizeInMB;
    }

    /**
     * Set the size in MB.
     *
     * @param sizeInMB The size in MB.
     */
    @JsonSetter("sizeInMB")
    public void setSizeInMB(final int sizeInMB) {
        this.sizeInMB = sizeInMB;
    }

    /**
     * Get the creation timestamp.
     *
     * @return The creation timestamp.
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

    /**
     * Get the status.
     *
     * @return The status.
     */
    @JsonGetter("status")
    public String getStatus() {
        return status;
    }

    /**
     * Set the status.
     *
     * @param status The status.
     */
    @JsonSetter("status")
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Get the distribution.
     *
     * @return The distribution.
     */
    @JsonGetter("distribution")
    public String getDistribution() {
        return distribution;
    }

    /**
     * Set the distribution.
     *
     * @param distribution The distribution.
     */
    @JsonSetter("distribution")
    public void setDistribution(final String distribution) {
        this.distribution = distribution;
    }
}
