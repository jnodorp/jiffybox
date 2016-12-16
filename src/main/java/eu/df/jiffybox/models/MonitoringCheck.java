package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

/**
 * A monitoring check consists of an id, a name, a jiffy box, an ip, a check
 * type, a check interval, a reminder interval, a retry tolerance, a status, a
 * port, a path, a username, a password and a list of contact groups.
 */
@JsonAutoDetect
public class MonitoringCheck {

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
     * The contact groups.
     */
    @JsonProperty("contactgroups")
    private List<ContactGroup> contactgroups = new ArrayList<>();

    /**
     * The JiffyBox.
     */
    @JsonProperty("jiffyBox")
    private int jiffyBox;

    /**
     * The ip.
     */
    @JsonProperty("ip")
    private String ip;

    /**
     * The check type.
     */
    @JsonProperty("checkType")
    private MonitoringCheckType checkType;

    /**
     * The check interval.
     */
    @JsonProperty("checkInterval")
    private int checkInterval;

    /**
     * The reminder interval.
     */
    @JsonProperty("reminderInterval")
    private int reminderInterval;

    /**
     * The retry tolerance.
     */
    @JsonProperty("retryTolerance")
    private int retryTolerance;

    /**
     * The status.
     */
    @JsonProperty("status")
    private MonitoringCheckStatus status;

    /**
     * The port.
     */
    @JsonProperty("port")
    private int port;

    /**
     * The path.
     */
    @JsonProperty("path")
    private String path;

    /**
     * The domain name.
     */
    @JsonProperty("domainname")
    private String domainname;

    /**
     * The username.
     */
    @JsonProperty("username")
    private String username;

    /**
     * The password.
     */
    @JsonProperty("password")
    private String password;

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
     * Get the jiffy box.
     *
     * @return The jiffy box.
     */
    @JsonGetter("jiffyBox")
    public int getJiffyBox() {
        return jiffyBox;
    }

    /**
     * Set the jiffy box.
     *
     * @param jiffyBox The jiffy box.
     */
    @JsonSetter("jiffyBox")
    public void setJiffyBox(final int jiffyBox) {
        this.jiffyBox = jiffyBox;
    }

    /**
     * Get the ip.
     *
     * @return The ip.
     */
    @JsonGetter("ip")
    public String getIp() {
        return ip;
    }

    /**
     * Set the ip.
     *
     * @param ip The ip.
     */
    @JsonSetter("ip")
    public void setIp(final String ip) {
        this.ip = ip;
    }

    /**
     * Get the check type.
     *
     * @return The check type.
     */
    @JsonGetter("checkType")
    public MonitoringCheckType getCheckType() {
        return checkType;
    }

    /**
     * Set the check type.
     *
     * @param checkType The check type.
     */
    @JsonSetter("checkType")
    public void setCheckType(final MonitoringCheckType checkType) {
        this.checkType = checkType;
    }

    /**
     * Get the check interval.
     *
     * @return The check interval.
     */
    @JsonGetter("checkInterval")
    public int getCheckInterval() {
        return checkInterval;
    }

    /**
     * Set the check interval.
     *
     * @param checkInterval The check interval.
     */
    @JsonSetter("checkInterval")
    public void setCheckInterval(final int checkInterval) {
        this.checkInterval = checkInterval;
    }

    /**
     * Get the reminder interval.
     *
     * @return The reminder interval.
     */
    @JsonGetter("reminderInterval")
    public int getReminderInterval() {
        return reminderInterval;
    }

    /**
     * Set the reminder interval.
     *
     * @param reminderInterval The reminder interval.
     */
    @JsonSetter("reminderInterval")
    public void setReminderInterval(final int reminderInterval) {
        this.reminderInterval = reminderInterval;
    }

    /**
     * Get the retry tolerance.
     *
     * @return The retry tolerance.
     */
    @JsonGetter("retryTolerance")
    public int getRetryTolerance() {
        return retryTolerance;
    }

    /**
     * Set the retry tolerance.
     *
     * @param retryTolerance The retry tolerance.
     */
    @JsonSetter("retryTolerance")
    public void setRetryTolerance(final int retryTolerance) {
        this.retryTolerance = retryTolerance;
    }

    /**
     * Get the status.
     *
     * @return The status.
     */
    @JsonGetter("status")
    public MonitoringCheckStatus getStatus() {
        return status;
    }

    /**
     * Set the status.
     *
     * @param status The status.
     */
    @JsonSetter("status")
    public void setStatus(final MonitoringCheckStatus status) {
        this.status = status;
    }

    /**
     * Get the port.
     *
     * @return The port.
     */
    @JsonGetter("port")
    public int getPort() {
        return port;
    }

    /**
     * Set the port.
     *
     * @param port The port.
     */
    @JsonSetter("port")
    public void setPort(final int port) {
        this.port = port;
    }

    /**
     * Get the path.
     *
     * @return The path.
     */
    @JsonGetter("path")
    public String getPath() {
        return path;
    }

    /**
     * Set the path.
     *
     * @param path The path.
     */
    @JsonSetter("path")
    public void setPath(final String path) {
        this.path = path;
    }

    /**
     * Get the domain name.
     *
     * @return The domain name.
     */
    @JsonGetter("domainname")
    public String getDomainname() {
        return domainname;
    }

    /**
     * Set the domain name.
     *
     * @param domainname The domain name.
     */
    @JsonSetter("domainname")
    public void setDomainname(final String domainname) {
        this.domainname = domainname;
    }

    /**
     * Get the username.
     *
     * @return The username.
     */
    @JsonGetter("username")
    public String getUsername() {
        return username;
    }

    /**
     * Set the username.
     *
     * @param username The username.
     */
    @JsonSetter("username")
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Get the password.
     *
     * @return The password.
     */
    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    /**
     * Set the password.
     *
     * @param password The password.
     */
    @JsonSetter("password")
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Get the contact groups.
     *
     * @return The contact groups.
     */
    @JsonGetter("contactgroups")
    public List<ContactGroup> getContactgroups() {
        return contactgroups;
    }

    /**
     * Set the contact groups.
     *
     * @param contactgroups The contact groups.
     */
    @JsonSetter("contactgroups")
    public void setContactgroups(final List<ContactGroup> contactgroups) {
        this.contactgroups = contactgroups;
    }
}
