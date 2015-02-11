package eu.df.jiffybox.modules;

import eu.df.jiffybox.builders.MonitoringCheckBuilder;
import eu.df.jiffybox.models.MonitoringCheck;
import eu.df.jiffybox.models.MonitoringStatus;
import eu.df.jiffybox.models.Response;

import java.io.IOException;
import java.util.Map;

/**
 * This interface describes the monitoring module.
 */
public interface ModuleMonitoring {

    /**
     * With this call you will get an overview of all monitoring checks.
     *
     * @return The retrieved monitoring checks.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Map<String, MonitoringCheck>> getMonitoringChecks() throws
            IOException;

    /**
     * Provides details of a specific monitoring check. The result is equivalent
     * to a single monitoring check of get().
     *
     * @param id Check-ID
     * @return The retrieved monitoring check.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<MonitoringCheck> getMonitoringCheck(int id) throws IOException;

    /**
     * With this command you can delete monitoring checks. The command is
     * asynchronous. On success the result is true; false otherwise. A success
     * solely means the deletion was requested successfully. The actual deletion
     * is executed asynchronously. The state of deletion can be obtained by
     * fetching the checks details. As soon as the notice occurs, that the check
     * is unavailable, the deletion is finished. During the deletion the
     * monitoring checks status is set to DELETING / DELETED. In case the status
     * switches back to READY the deletion failed.
     *
     * @param id Check-ID
     * @return If the monitoring check was successfully deleted.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Boolean> deleteMonitoringCheck(int id) throws IOException;

    /**
     * There are two ways to create a new monitoring check: 1. Create a new
     * monitoring check from scratch. 2. Duplicate an existing monitoring check.
     * The creation is an synchronous process. A success as result solely means
     * the creation was started successfully. The result can only be obtained by
     * periodically requesting the according get(id) method. id is contained
     * within the result of the creation. During the creation the monitoring
     * checks state is CREATING. As soon as the monitoring check is set up the
     * status switches to READY. On failure the process is automatically
     * repeated one time. If the creation fails twice the state is switched to
     * ERROR. All parameters defining times are interpreted as seconds. Default
     * values are used when optional parameters are left out. The parameters are
     * handled using an existing MonitoringCheck object since the creation is
     * really complex.
     *
     * @param data The MonitoringCheck to create.
     * @return The created monitoring check.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<MonitoringCheck> createMonitoringCheck(final
                                                    MonitoringCheckBuilder
                                                            data) throws
            IOException;

    /**
     * Duplicating monitoring checks requires the id of the monitoring check as
     * GET parameter. To duplicate a monitoring check at least one parameter has
     * to be changed. The other settings are copied from the original monitoring
     * check. For the parameters please check the post() method. For further
     * documentation please read the official API documentation.
     *
     * @param id   Check-ID
     * @param data A monitoring check to use for configuration.
     * @return The created monitoring check.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<MonitoringCheck> duplicateMonitoringCheck(final int id, final
    MonitoringCheckBuilder data) throws IOException;

    /**
     * Provides the current status of a single monitoring check.
     *
     * @param id Check-ID
     * @return The monitoring checks status.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<MonitoringStatus> getStatus(final int id) throws IOException;

    /**
     * With this call you get the current status of every active monitoring
     * check defined on a given IP address.
     *
     * @param address IPv4-address
     * @return The monitoring checks statuses of the given IP address.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Map<String, MonitoringStatus>> getStatuses(final String address)
            throws IOException;
}
