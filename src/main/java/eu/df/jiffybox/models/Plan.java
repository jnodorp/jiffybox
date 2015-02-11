package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * A plan consists of an id, a name, a disk size in MB, a RAM in MB, a price per
 * hour, a price per hour frozen and the number of CPUs.
 */
@JsonAutoDetect
public class Plan extends PrintableModel {

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
     * The disk size in MB.
     */
    @JsonProperty("diskSizeInMB")
    private int diskSizeInMB;

    /**
     * The RAM in MB.
     */
    @JsonProperty("ramInMB")
    private int ramInMB;

    /**
     * The price per hour.
     */
    @JsonProperty("pricePerHour")
    private float pricePerHour;

    /**
     * The price per hour frozen.
     */
    @JsonProperty("pricePerHourFrozen")
    private float pricePerHourFrozen;

    /**
     * The number of CPUs.
     */
    @JsonProperty("cpus")
    private int cpus;

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
     * Get the disk size in MB.
     *
     * @return The disk size in MB.
     */
    @JsonGetter("diskSizeInMB")
    public int getDiskSizeInMB() {
        return diskSizeInMB;
    }

    /**
     * Set the disk size in MB.
     *
     * @param diskSizeInMB The disk size in MB.
     */
    @JsonSetter("diskSizeInMB")
    public void setDiskSizeInMB(final int diskSizeInMB) {
        this.diskSizeInMB = diskSizeInMB;
    }

    /**
     * Get the RAM in MB.
     *
     * @return The RAM in MB.
     */
    @JsonGetter("ramInMB")
    public int getRamInMB() {
        return ramInMB;
    }

    /**
     * Set the RAM in MB.
     *
     * @param ramInMB The RAM in MB.
     */
    @JsonSetter("ramInMB")
    public void setRamInMB(final int ramInMB) {
        this.ramInMB = ramInMB;
    }

    /**
     * Get the price per hour.
     *
     * @return The price per hour.
     */
    @JsonGetter("pricePerHour")
    public float getPricePerHour() {
        return pricePerHour;
    }

    /**
     * Set the price per hour.
     *
     * @param pricePerHour The price per hour.
     */
    @JsonSetter("pricePerHour")
    public void setPricePerHour(final float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    /**
     * Get the price per hour frozen.
     *
     * @return The price per hour frozen.
     */
    @JsonGetter("pricePerHourFrozen")
    public float getPricePerHourFrozen() {
        return pricePerHourFrozen;
    }

    /**
     * Set the price per hour frozen.
     *
     * @param pricePerHourFrozen The price per hour frozen.
     */
    @JsonSetter("pricePerHourFrozen")
    public void setPricePerHourFrozen(final float pricePerHourFrozen) {
        this.pricePerHourFrozen = pricePerHourFrozen;
    }

    /**
     * Get the number of CPUs.
     *
     * @return The number of CPUs.
     */
    @JsonGetter("cpus")
    public int getCpus() {
        return cpus;
    }

    /**
     * Set the number of CPUs.
     *
     * @param cpus The number of CPUs.
     */
    @JsonSetter("cpus")
    public void setCpus(final int cpus) {
        this.cpus = cpus;
    }
}
