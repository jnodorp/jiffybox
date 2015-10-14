package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This enumeration contains all available types of monitoring checks.
 */
@JsonAutoDetect
public enum MonitoringCheckType {

    /**
     * A ping check.
     */
    PING("ping"),

    /**
     * A TCP port check.
     */
    PORT_TCP("portTcp"),

    /**
     * A UDP port check.
     */
    PORT_UDP("portUdp"),

    /**
     * A HTTP check.
     */
    HTTP("http"),

    /**
     * A HTTPS check.
     */
    HTTPS("https"),

    /**
     * A smtp check.
     */
    SMTP("smtp"),

    /**
     * A POP3 check.
     */
    POP3("pop3"),

    /**
     * A IMAP check.
     */
    IMAP("imap"),

    /**
     * A FTP check.
     */
    FTP("ftp"),

    /**
     * A SSH check.
     */
    SSH("ssh"),

    /**
     * A MySQL check.
     */
    MYSQL("mysql"),

    /**
     * A DNS check.
     */
    DNS("dns");

    /**
     * The JSON representation.
     */
    private final String json;

    /**
     * Create a monitoring check type using its JSON string.
     *
     * @param json The JSON string.
     */
    MonitoringCheckType(String json) {
        this.json = json;
    }

    /**
     * Create a monitoring check type from JSON.
     *
     * @param json The JSON string.
     * @return The monitoring check type.
     */
    @JsonCreator
    public static MonitoringCheckType fromJson(String json) {
        return valueOf(json.toUpperCase());
    }

    /**
     * Get the JSON value.
     *
     * @return The JSON value.
     */
    @JsonValue
    public String toJson() {
        return this.json;
    }
}
