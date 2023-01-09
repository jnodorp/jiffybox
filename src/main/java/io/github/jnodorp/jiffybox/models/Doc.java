package io.github.jnodorp.jiffybox.models;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A documentation consists of a list of doc entries and a description.
 *
 * @see DocEntry
 */
@JsonAutoDetect
public class Doc extends PrintableModel {

    /**
     * A list of doc entries.
     */
    @JsonIgnore
    private final List<DocEntry> docEntries = new ArrayList<>();

    /**
     * The description.
     */
    @JsonProperty("description")
    private String description;

    /**
     * Add an entry to the doc.
     *
     * @param path     The path.
     * @param docEntry The entry.
     */
    @JsonAnySetter
    protected void addDocEntry(final String path, final Map<String, DocEntry>
            docEntry) {
        DocEntry result = docEntry.values().iterator().next();
        result.setPath(path);
        result.setVerb(docEntry.keySet().iterator().next());
        docEntries.add(result);
    }

    /**
     * Get the doc entries.
     *
     * @return The doc entries.
     */
    @JsonIgnore
    public List<DocEntry> getDocEntries() {
        return docEntries;
    }

    /**
     * Get the description.
     *
     * @return The description.
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * Set the description.
     *
     * @param description The description.
     */
    @JsonSetter("description")
    public void setDescription(String description) {
        this.description = description;
    }
}
