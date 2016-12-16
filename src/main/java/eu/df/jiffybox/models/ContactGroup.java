package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

/**
 * A contact group consists of an id, a name, a list of contacts and a status.
 *
 * @see eu.df.jiffybox.models.ContactGroupStatus
 */
@JsonAutoDetect
public class ContactGroup {

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
     * The contacts.
     */
    @JsonProperty("contacts")
    private List<String> contacts = new ArrayList<>();

    /**
     * The status.
     */
    @JsonProperty("status")
    private ContactGroupStatus status;

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
     * Get the contacts.
     *
     * @return The contacts.
     */
    @JsonGetter("contacts")
    public List<String> getContacts() {
        return contacts;
    }

    /**
     * Set the contacts.
     *
     * @param contacts The contacts.
     */
    @JsonSetter("contacts")
    public void setContacts(final List<String> contacts) {
        this.contacts = contacts;
    }

    /**
     * Get the status.
     *
     * @return The status.
     */
    @JsonGetter("status")
    public ContactGroupStatus getStatus() {
        return status;
    }

    /**
     * Set the status.
     *
     * @param status The status.
     */
    @JsonSetter("status")
    public void setStatus(final ContactGroupStatus status) {
        this.status = status;
    }
}
