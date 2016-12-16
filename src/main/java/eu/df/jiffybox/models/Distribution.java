package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * A distribution consists of a minimal disk size in MB, a name, a rootdisk mode
 * and a default kernel.
 */
@JsonAutoDetect
public class Distribution implements Comparable<Distribution> {

    /**
     * The minimal disk size in MB.
     */
    @JsonProperty("minDiskSizeMB")
    private int minDiskSizeMB;

    /**
     * The name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The rootdisk mode.
     */
    @JsonProperty("rootdiskMode")
    private String rootdiskMode;

    /**
     * The default kernel.
     */
    @JsonProperty("defaultKernel")
    private String defaultKernel;

    /**
     * Get the minimal disk size in MB.
     *
     * @return The minimal disk size in MB.
     */
    @JsonGetter("minDiskSizeMB")
    public int getMinDiskSizeMB() {
        return minDiskSizeMB;
    }

    /**
     * Set the minimal disk size in MB.
     *
     * @param minDiskSizeMB The minimal disk size in MB.
     */
    @JsonSetter("minDiskSizeMB")
    public void setMinDiskSizeMB(final int minDiskSizeMB) {
        this.minDiskSizeMB = minDiskSizeMB;
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

    /**
     * Get the rootdisk mode.
     *
     * @return The rootdisk mode.
     */
    @JsonGetter("rootdiskMode")
    public String getRootdiskMode() {
        return rootdiskMode;
    }

    /**
     * Set the rootdisk mode.
     *
     * @param rootdiskMode The rootdisk mode.
     */
    @JsonSetter("rootdiskMode")
    public void setRootdiskMode(final String rootdiskMode) {
        this.rootdiskMode = rootdiskMode;
    }

    /**
     * Get the default kernel.
     *
     * @return The default kernel.
     */
    @JsonGetter("defaultKernel")
    public String getDefaultKernel() {
        return defaultKernel;
    }

    /**
     * Set the default kernel.
     *
     * @param defaultKernel The default kernel.
     */
    @JsonSetter("defaultKernel")
    public void setDefaultKernel(final String defaultKernel) {
        this.defaultKernel = defaultKernel;
    }

    @Override
    public int compareTo(Distribution d) {
        return Integer.compare(getMinDiskSizeMB(), d.getMinDiskSizeMB());
    }
}
