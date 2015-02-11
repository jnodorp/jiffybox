package eu.df.jiffybox.internal;

import eu.df.jiffybox.builders.Finished;
import eu.df.jiffybox.builders.MonitoringCheckBuilder;
import eu.df.jiffybox.models.MonitoringCheckType;

import java.util.List;

/**
 * A monitoring check consists of an id, a name, a jiffy box, an ip, a check
 * type, a check interval, a reminder interval, a retry tolerance, a status, a
 * port, a path, a username, a password and a list of contact groups. This class
 * is the only class which is used as a builder. Monitoring checks are too
 * complex to build using parametrized API calls. To create a monitoring check
 * call the needed setter methods on a new monitoring check instance and use the
 * readily set up instance as the argument for the final API call.
 */
class DefaultMonitoringCheckBuilder extends DefaultBuilder implements
        MonitoringCheckBuilder {

    /**
     * Set the name, ip and checkType.
     *
     * @param name      The monitoring checks name. Up to 30 characters are
     *                  allowed. Valid characters are a-zA-Z0-9üöäÜÖÄß_()=!\*@.-
     *                  and spaces.
     * @param ip        IP address, the monitoring check is bound to.
     * @param checkType The monitoring checks type. Possible types are: portUdp,
     *                  http, https, smtp, pop3, imap, ftp, ssh, mysql, dns.
     * @param port      Mandatory for: all; Optional for: -
     */
    public DefaultMonitoringCheckBuilder(final String name, final String ip,
                                         final MonitoringCheckType checkType,
                                         final Integer port) {
        put("name", name);
        put("ip", ip);

        if (checkType != null) {
            put("checkType", checkType.toJson());
        }

        if (port != null) {
            put("port", port);
        }
    }

    @Override
    public MonitoringCheckBuilder withContactgroups(List<String>
                                                                contactgroups) {
        putContactGroups(contactgroups);
        return this;
    }

    @Override
    public MonitoringCheckBuilder withCheckInterval(int checkInterval) {
        put("checkInterval", checkInterval);
        return this;
    }

    @Override
    public MonitoringCheckBuilder withReminderInterval(int reminderInterval) {
        put("reminderInterval", reminderInterval);
        return this;
    }

    @Override
    public MonitoringCheckBuilder withRetryTolerance(int retryTolerance) {
        put("retryTolerance", retryTolerance);
        return this;
    }

    @Override
    public MonitoringCheckBuilder withTimeout(int timeout) {
        put("timeout", timeout);
        return this;
    }

    @Override
    public MonitoringCheckBuilder withActive(boolean active) {
        put("active", active);
        return this;
    }

    @Override
    public Finished<MonitoringCheckBuilder> build() {
        return new FinishedImpl<MonitoringCheckBuilder>(this);
    }
}
