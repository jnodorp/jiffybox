package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * An IP consists of an id, an ip, an ip version, if it is a subnet, the reverse
 * lookup information, a type and if it is floating.
 */
@JsonAutoDetect
public class IP extends PrintableModel {

    /**
     * The id.
     */
    @JsonProperty("id")
    private int id;

    /**
     * The ip.
     */
    @JsonProperty("ip")
    private String ipString;

    /**
     * The ip version.
     */
    @JsonProperty("ipVersion")
    private int ipVersion;

    /**
     * Is subnet?
     */
    @JsonProperty("isSubnet")
    private boolean isSubnet;

    /**
     * The reverse lookup information.
     */
    @JsonProperty("reverseLookup")
    private Object reverseLookup;

    /**
     * The type.
     */
    @JsonProperty("type")
    private String type;

    /**
     * Floating?
     */
    @JsonProperty("floating")
    private boolean floating;

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
     * Get the ip.
     *
     * @return The ip.
     */
    @JsonGetter("ip")
    public String getIp() {
        return ipString;
    }

    /**
     * Set the ip.
     *
     * @param ipString The ip.
     */
    @JsonSetter("ip")
    public void setIp(final String ipString) {
        this.ipString = ipString;
    }

    /**
     * Get the ip version.
     *
     * @return The ip version.
     */
    @JsonGetter("ipVersion")
    public int getIpVersion() {
        return ipVersion;
    }

    /**
     * Set the ip version.
     *
     * @param ipVersion The ip version.
     */
    @JsonSetter("ipVersion")
    public void setIpVersion(final int ipVersion) {
        this.ipVersion = ipVersion;
    }

    /**
     * Is this ip a subnet?
     *
     * @return If this ip is a subnet.
     */
    @JsonGetter("isSubnet")
    public boolean isSubnet() {
        return isSubnet;
    }

    /**
     * Set isSubnet.
     *
     * @param isSubnet Is subnet?
     */
    @JsonSetter("isSubnet")
    public void setSubnet(final boolean isSubnet) {
        this.isSubnet = isSubnet;
    }

    /**
     * Get the reverse lookup information.
     *
     * @return The reverse lookup information.
     */
    @JsonGetter("reverseLookup")
    public Object getReverseLookup() {
        return reverseLookup;
    }

    /**
     * Set the reverse lookup information.
     *
     * @param reverseLookup The reverse lookup information.
     */
    @JsonSetter("reverseLookup")
    public void setReverseLookup(final Object reverseLookup) {
        this.reverseLookup = reverseLookup;
    }

    /**
     * Get the type.
     *
     * @return The type.
     */
    @JsonGetter("type")
    public String getType() {
        return type;
    }

    /**
     * Set the type.
     *
     * @param type The type.
     */
    @JsonSetter("type")
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Is this ip floating.
     *
     * @return If this ip is floating.
     */
    @JsonGetter("floating")
    public boolean isFloating() {
        return floating;
    }

    /**
     * Set is floating?
     *
     * @param floating Is floating?
     */
    @JsonSetter("floating")
    public void setFloating(boolean floating) {
        this.floating = floating;
    }
}
