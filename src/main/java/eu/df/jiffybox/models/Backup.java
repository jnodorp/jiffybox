package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * This class represents a set of backups belonging to a JiffyBox.
 *
 * @see eu.df.jiffybox.models.BackupEntry
 * @see eu.df.jiffybox.models.JiffyBox
 */
@JsonAutoDetect
public class Backup {

    /**
     * The manual backup entry.
     */
    @JsonProperty("manual")
    private BackupEntry manual;

    /**
     * The daily backup entry.
     */
    @JsonProperty("daily")
    private BackupEntry daily;

    /**
     * The weekly backup entry.
     */
    @JsonProperty("weekly")
    private BackupEntry weekly;

    /**
     * The biweekly backup entry.
     */
    @JsonProperty("biweekly")
    private BackupEntry biweekly;

    /**
     * The day.
     */
    @JsonProperty("day")
    private int day;

    /**
     * The time.
     */
    @JsonProperty("time")
    private int time;

    /**
     * Get the manual backup entry.
     *
     * @return The manual backup entry.
     */
    @JsonGetter("manual")
    public BackupEntry getManual() {
        return manual;
    }

    /**
     * Set the manual backup entry.
     *
     * @param manual The manual backup entry.
     */
    @JsonSetter("manual")
    public void setManual(final BackupEntry manual) {
        this.manual = manual;
    }

    /**
     * Get the daily backup entry.
     *
     * @return The daily backup entry.
     */
    @JsonGetter("daily")
    public BackupEntry getDaily() {
        return daily;
    }

    /**
     * Set the daily backup entry.
     *
     * @param daily The daily backup entry.
     */
    @JsonSetter("daily")
    public void setDaily(final BackupEntry daily) {
        this.daily = daily;
    }

    /**
     * Get the weekly backup entry.
     *
     * @return The weekly backup entry.
     */
    @JsonGetter("weekly")
    public BackupEntry getWeekly() {
        return weekly;
    }

    /**
     * Set the weekly backup entry.
     *
     * @param weekly The weekly backup entry.
     */
    @JsonSetter("weekly")
    public void setWeekly(final BackupEntry weekly) {
        this.weekly = weekly;
    }

    /**
     * Get the biweekly backup entry.
     *
     * @return The biweekly backup entry.
     */
    @JsonGetter("biweekly")
    public BackupEntry getBiweekly() {
        return biweekly;
    }

    /**
     * Set the biweekly backup entry.
     *
     * @param biweekly The biweekly backup entry.
     */
    @JsonSetter("biweekly")
    public void setBiweekly(final BackupEntry biweekly) {
        this.biweekly = biweekly;
    }

    /**
     * Get the day.
     *
     * @return The day.
     */
    @JsonGetter("getDay")
    public int getDay() {
        return day;
    }

    /**
     * Set the day.
     *
     * @param day The day.
     */
    @JsonSetter("day")
    public void setDay(final int day) {
        this.day = day;
    }

    /**
     * Get the time.
     *
     * @return The time.
     */
    @JsonGetter("time")
    public int getTime() {
        return time;
    }

    /**
     * Set the time.
     *
     * @param time The time.
     */
    @JsonSetter("time")
    public void setTime(final int time) {
        this.time = time;
    }
}
