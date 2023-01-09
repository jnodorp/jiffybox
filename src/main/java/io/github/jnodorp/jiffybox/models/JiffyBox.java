package io.github.jnodorp.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class contains all information associated with a JiffyBox.
 */
@JsonAutoDetect
public class JiffyBox {

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
     * The metadata.
     */
    @JsonProperty("metadata")
    private Map<String, Object> metadata = new LinkedHashMap<>();

    /**
     * The ips.
     */
    @JsonProperty("ips")
    private JiffyBoxIps ips;

    /**
     * The status.
     */
    @JsonProperty("status")
    private Status status;

    /**
     * The creation timestamp.
     */
    @JsonProperty("created")
    private long created;

    /**
     * Is the recoverymode active?
     */
    @JsonProperty("recoverymodeActive")
    private boolean recoverymodeActive;

    /**
     * Is an manual backup running?
     */
    @JsonProperty("manualBackupRunning")
    private boolean manualBackupRunning;

    /**
     * Is the JiffyBox being copied?
     */
    @JsonProperty("isBeingCopied")
    private boolean isBeingCopied;

    /**
     * Is the JiffyBox running?
     */
    @JsonProperty("running")
    private boolean running;

    /**
     * The host.
     */
    @JsonProperty("host")
    private String host;

    /**
     * The plan.
     */
    @JsonProperty("plan")
    private Plan plan;

    /**
     * Running since.
     */
    @JsonProperty("runningSince")
    private long runningSince;

    /**
     * The active profile.
     */
    @JsonProperty("activeProfile")
    private Profile activeProfile;

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

    /**
     * Get the ips.
     *
     * @return The ips.
     */
    @JsonGetter("ips")
    public JiffyBoxIps getIps() {
        return ips;
    }

    /**
     * Set the ips.
     *
     * @param ips The ips.
     */
    @JsonSetter("ips")
    public void setIps(final JiffyBoxIps ips) {
        this.ips = ips;
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
     * Check if the recovery mode is active.
     *
     * @return Is the recovery mode active?
     */
    @JsonGetter("recoverymodeActive")
    public boolean isRecoverymodeActive() {
        return recoverymodeActive;
    }

    /**
     * Set if the recovery mode is active.
     *
     * @param recoverymodeActive Is the recovery mode active?
     */
    @JsonSetter("recoverymodeActive")
    public void setRecoverymodeActive(final boolean recoverymodeActive) {
        this.recoverymodeActive = recoverymodeActive;
    }

    /**
     * Check if a manual backup is running.
     *
     * @return Is a manual backup running?
     */
    @JsonGetter("manualBackupRunning")
    public boolean isManualBackupRunning() {
        return manualBackupRunning;
    }

    /**
     * Set if a manual backup is running.
     *
     * @param manualBackupRunning Is a manual backup running?
     */
    @JsonSetter("manualBackupRunning")
    public void setManualBackupRunning(final boolean manualBackupRunning) {
        this.manualBackupRunning = manualBackupRunning;
    }

    /**
     * Get if the JiffyBox is being copied.
     *
     * @return Is the JiffyBox being copied?
     */
    @JsonGetter("isBeingCopied")
    public boolean isBeingCopied() {
        return isBeingCopied;
    }

    /**
     * Set if the JiffyBox is being copied.
     *
     * @param isBeingCopied Is the JiffyBox being copied?
     */
    @JsonSetter("isBeingCopied")
    public void setBeingCopied(final boolean isBeingCopied) {
        this.isBeingCopied = isBeingCopied;
    }

    /**
     * Get if the JiffyBox is running.
     *
     * @return Is running?
     */
    @JsonGetter("running")
    public boolean isRunning() {
        return running;
    }

    /**
     * Set if the JiffyBox is running.
     *
     * @param running Is running?
     */
    @JsonSetter("running")
    public void setRunning(final boolean running) {
        this.running = running;
    }

    /**
     * Get the host.
     *
     * @return The host.
     */
    @JsonGetter("host")
    public String getHost() {
        return host;
    }

    /**
     * Set the host.
     *
     * @param host The host.
     */
    @JsonSetter("host")
    public void setHost(final String host) {
        this.host = host;
    }

    /**
     * Get the plan.
     *
     * @return The plan.
     */
    @JsonGetter("plan")
    public Plan getPlan() {
        return plan;
    }

    /**
     * Set the plan.
     *
     * @param plan The plan.
     */
    @JsonSetter("plan")
    public void setPlan(final Plan plan) {
        this.plan = plan;
    }

    /**
     * Get the metadata.
     *
     * @return The metadata.
     */
    @JsonGetter("metadata")
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    /**
     * Set the metadata.
     *
     * @param metadata The metadata.
     */
    @JsonSetter("metadata")
    public void setMetadata(final Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    /**
     * Get the running since timestamp.
     *
     * @return The running since timestamp.
     */
    @JsonGetter("runningSince")
    public long getRunningSince() {
        return runningSince;
    }

    /**
     * Set the running since timestamp.
     *
     * @param runningSince The running since timestamp.
     */
    @JsonSetter("runningSince")
    public void setRunningSince(final long runningSince) {
        this.runningSince = runningSince;
    }

    /**
     * Get the active profile.
     *
     * @return The active profile.
     */
    @JsonGetter("activeProfile")
    public Profile getActiveProfile() {
        return activeProfile;
    }

    /**
     * Set the active profile.
     *
     * @param activeProfile The active profile.
     */
    @JsonSetter("activeProfile")
    public void setActiveProfile(final Profile activeProfile) {
        this.activeProfile = activeProfile;
    }
}
