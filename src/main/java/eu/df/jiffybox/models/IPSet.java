package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An ip set consists of ips.
 */
@JsonAutoDetect
public class IPSet extends PrintableModel {

    /**
     * The ips.
     */
    @JsonIgnore
    private final Map<String, IP> ipSets = new LinkedHashMap<>();

    /**
     * Add an ip to the set.
     *
     * @param s  The key.
     * @param ip The ip.
     */
    @JsonAnySetter
    public void addIp(final String s, final IP ip) {
        ipSets.put(s, ip);
    }

    /**
     * Get the ips.
     *
     * @return The ips.
     */
    @JsonIgnore
    public Map<String, IP> getIps() {
        return ipSets;
    }
}
