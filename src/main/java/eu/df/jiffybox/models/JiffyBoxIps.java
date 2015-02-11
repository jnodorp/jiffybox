package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

/**
 * JiffyBoxIps consist of a list of public and a list of private ips.
 */
@JsonAutoDetect
public class JiffyBoxIps {

    /**
     * The public ips.
     */
    @JsonProperty("public")
    private List<String> pub = new ArrayList<>();

    /**
     * The private ips.
     */
    @JsonProperty("private")
    private List<String> priv = new ArrayList<>();

    /**
     * Get the public ips.
     *
     * @return The public ips.
     */
    @JsonGetter("public")
    public List<String> getPublic() {
        return pub;
    }

    /**
     * Set the public ips.
     *
     * @param pub The public ips.
     */
    @JsonSetter("public")
    public void setPublic(final List<String> pub) {
        this.pub = pub;
    }

    /**
     * Get the private ips.
     *
     * @return The private ips.
     */
    @JsonGetter("private")
    public List<String> getPrivate() {
        return priv;
    }

    /**
     * Set the private ips.
     *
     * @param priv The private ips.
     */
    @JsonSetter("private")
    public void setPrivate(final List<String> priv) {
        this.priv = priv;
    }
}
