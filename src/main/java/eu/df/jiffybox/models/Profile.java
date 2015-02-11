package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A profile consists of a name, a creation timestamp, a runlevel, a kernel, a
 * rootdisk, a rootdisk mode, a status and disks.
 */
public class Profile extends PrintableModel {

    /**
     * The disks.
     */
    @JsonProperty("disks")
    private Map<String, Disk> disks = new LinkedHashMap<>();

    /**
     * The name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The creation timestamp.
     */
    @JsonProperty("created")
    private long created;

    /**
     * The runlevel.
     */
    @JsonProperty("runlevel")
    private String runlevel;

    /**
     * The kernel.
     */
    @JsonProperty("kernel")
    private String kernel;

    /**
     * The rootdisk.
     */
    @JsonProperty("rootdisk")
    private String rootdisk;

    /**
     * The rootdisk mode.
     */
    @JsonProperty("rootdiskMode")
    private String rootdiskMode;

    /**
     * The status.
     */
    @JsonProperty("status")
    private Status status;

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
     * Get the creation timestamp.
     *
     * @return Te creation timestamp.
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
     * Get the runlevel.
     *
     * @return The runlevel.
     */
    @JsonGetter("runlevel")
    public String getRunlevel() {
        return runlevel;
    }

    /**
     * Set runlevel.
     *
     * @param runlevel The runlevel.
     */
    @JsonSetter("runlevel")
    public void setRunlevel(final String runlevel) {
        this.runlevel = runlevel;
    }

    /**
     * Get the kernel.
     *
     * @return The kernel.
     */
    @JsonGetter("kernel")
    public String getKernel() {
        return kernel;
    }

    /**
     * Set the kernel.
     *
     * @param kernel The kernel.
     */
    @JsonSetter("kernel")
    public void setKernel(final String kernel) {
        this.kernel = kernel;
    }

    /**
     * Get the rootdisk.
     *
     * @return The rootdisk.
     */
    @JsonGetter("rootdisk")
    public String getRootdisk() {
        return rootdisk;
    }

    /**
     * Set the rootdisk.
     *
     * @param rootdisk The rootdisk.
     */
    @JsonSetter("rootdisk")
    public void setRootdisk(final String rootdisk) {
        this.rootdisk = rootdisk;
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
     * Get the status.
     *
     * @return The status.
     */
    @JsonGetter("status")
    public Status getStatus() {
        return status;
    }

    /**
     * Set the status.
     *
     * @param status The status.
     */
    @JsonSetter("status")
    public void setStatus(final Status status) {
        this.status = status;
    }

    /**
     * Get the disks.
     *
     * @return The disks.
     */
    @JsonGetter("disks")
    public Map<String, Disk> getDisks() {
        return disks;
    }

    /**
     * Set the disks.
     *
     * @param disks The disks.
     */
    @JsonSetter("disks")
    public void setDisks(final Map<String, Disk> disks) {
        this.disks = disks;
    }
}
