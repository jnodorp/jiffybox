package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * A backup configuration consists of a day id and a time id.
 */
@JsonAutoDetect
public class BackupConfig extends PrintableModel {

    /**
     * The dayid.
     */
    @JsonProperty("dayid")
    private int dayid;

    /**
     * The timeid.
     */
    @JsonProperty("timeid")
    private int timeid;

    /**
     * Get the dayid.
     *
     * @return The dayid.
     */
    @JsonGetter("dayid")
    public int getDayid() {
        return dayid;
    }

    /**
     * Set the dayid.
     *
     * @param dayid The dayid.
     */
    @JsonSetter("dayid")
    public void setDayid(final int dayid) {
        this.dayid = dayid;
    }

    /**
     * Get the timeid.
     *
     * @return The timeid.
     */
    @JsonGetter("timeid")
    public int getTimeid() {
        return timeid;
    }

    /**
     * Set the timeid.
     *
     * @param timeid The timeid.
     */
    @JsonSetter("timeid")
    public void setTimeid(final int timeid) {
        this.timeid = timeid;
    }
}
