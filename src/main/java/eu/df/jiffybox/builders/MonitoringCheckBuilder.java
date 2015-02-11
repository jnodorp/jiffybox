package eu.df.jiffybox.builders;

import java.util.List;

/**
 * This class is used to build monitoring checks. Use any of the methods you
 * need and get the built object using the 'build' method.
 */
public interface MonitoringCheckBuilder extends
        Builder<MonitoringCheckBuilder> {

    /**
     * Set the contact groups.
     *
     * @param contactgroups One or more contact group IDs to which e-mail
     *                      notifications should be sent. If this parameter is
     *                      missing the results can be checked only within the
     *                      control panel.
     * @return The same builder for method chaining.
     */
    MonitoringCheckBuilder withContactgroups(final List<String> contactgroups);

    /**
     * Set the check interval.
     *
     * @param checkInterval The check interval specifies in which period the
     *                      check is executed. Default: 5 minutes (300
     *                      Seconds).
     * @return The same builder for method chaining.
     */
    MonitoringCheckBuilder withCheckInterval(final int checkInterval);

    /**
     * Set the reminder interval.
     *
     * @param reminderInterval The reminder interval specifies in which period
     *                         e-mails are sent. Default: 1 Hour (3600
     *                         Seconds).
     * @return The same builder for method chaining.
     */
    MonitoringCheckBuilder withReminderInterval(final int reminderInterval);

    /**
     * Set the retry tolerance.
     *
     * @param retryTolerance The retry tolerance specifies how often a
     *                       monitoring check has to become critical before a
     *                       notification is sent. Default: 3.
     * @return The same builder for method chaining.
     */
    MonitoringCheckBuilder withRetryTolerance(final int retryTolerance);

    /**
     * Set the timeout.
     *
     * @param timeout The timeout value specifies after which time the check is
     *                aborted. Default: 30.
     * @return The same builder for method chaining.
     */
    MonitoringCheckBuilder withTimeout(final int timeout);

    /**
     * Set active.
     *
     * @param active Monitoring checks can be created as well active
     *               (asynchronous process), and checked after a reload of the
     *               monitoring configuration, as inactive and saved only.
     * @return The same builder for method chaining.
     */
    MonitoringCheckBuilder withActive(final boolean active);
}
