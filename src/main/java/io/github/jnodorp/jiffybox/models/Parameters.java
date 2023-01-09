package io.github.jnodorp.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Map;

/**
 * A list of parameters consists of mandatory parameters (must) and optional
 * parameters (may).
 */
@JsonAutoDetect
public class Parameters extends PrintableModel {

    /**
     * Mandatory parameters.
     */
    @JsonProperty("must")
    private Map<String, Parameter> must;

    /**
     * Optional parameters.
     */
    @JsonProperty("may")
    private Map<String, Parameter> may;

    /**
     * Get the mandatory parameters.
     *
     * @return The mandatory parameters.
     */
    @JsonGetter("must")
    public Map<String, Parameter> getMust() {
        return must;
    }

    /**
     * Set the mandatory parameters.
     *
     * @param must The mandatory parameters.
     */
    @JsonSetter("must")
    public void setMust(final Map<String, Parameter> must) {
        this.must = must;
    }

    /**
     * Get the optional parameters.
     *
     * @return The optional parameters.
     */
    @JsonGetter("may")
    public Map<String, Parameter> getMay() {
        return may;
    }

    /**
     * Set the optional parameters.
     *
     * @param may The optional parameters.
     */
    @JsonSetter("may")
    public void setMay(final Map<String, Parameter> may) {
        this.may = may;
    }
}
