package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.*;

/**
 * A doc entry consists of a description and parameters.
 *
 * @see eu.df.jiffybox.models.Parameters
 */
@JsonAutoDetect
public class DocEntry extends PrintableModel {

    /**
     * The description.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The parameter.
     */
    @JsonProperty("parameters")
    private Parameters parameters;

    /**
     * The path.
     */
    @JsonIgnore
    private String path;

    /**
     * The verb.
     */
    @JsonIgnore
    private String verb;

    /**
     * Get the description.
     *
     * @return The description.
     */
    @JsonGetter("description")
    public String getDescription() {
        return description;
    }

    /**
     * Set the description.
     *
     * @param description The description.
     */
    @JsonSetter("description")
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get the parameters.
     *
     * @return The parameters.
     */
    @JsonGetter("parameters")
    public Parameters getParameters() {
        return parameters;
    }

    /**
     * Set the parameters.
     *
     * @param parameters The parameters.
     */
    @JsonSetter("parameters")
    public void setParameters(final Parameters parameters) {
        this.parameters = parameters;
    }

    /**
     * Get the path.
     *
     * @return The path.
     */
    @JsonIgnore
    public String getPath() {
        return path;
    }

    /**
     * Set the path.
     *
     * @param path The path.
     */
    @JsonIgnore
    public void setPath(final String path) {
        this.path = path;
    }

    /**
     * Get the verb.
     *
     * @return The verb.
     */
    @JsonIgnore
    public String getVerb() {
        return verb;
    }

    /**
     * Set the verb.
     *
     * @param verb The verb.
     */
    @JsonIgnore
    public void setVerb(final String verb) {
        this.verb = verb;
    }
}
