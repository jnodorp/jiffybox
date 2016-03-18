package eu.df.jiffybox.internal;

import com.fasterxml.jackson.databind.node.ObjectNode;
import eu.df.jiffybox.builders.MonitoringCheckBuilder;
import eu.df.jiffybox.models.MonitoringCheck;
import eu.df.jiffybox.models.MonitoringStatus;
import eu.df.jiffybox.models.Response;
import eu.df.jiffybox.modules.ModuleMonitoring;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Implementation of the monitoring module.
 *
 * @see eu.df.jiffybox.modules.ModuleMonitoring
 */
public class ModuleMonitoringImpl implements ModuleMonitoring {

    /**
     * The base URI.
     */
    private final URI baseUri;

    /**
     * Create an instance of this module using the specified base URI.
     *
     * @param baseUri The base URI to use.
     */
    public ModuleMonitoringImpl(final URI baseUri) {
        this.baseUri = URI.create(baseUri + "/monitoring");
    }

    @Override
    public Response<List<MonitoringCheck>> getMonitoringChecks() throws IOException {
        return ApiCall.get(baseUri).asList(MonitoringCheck.class);
    }

    @Override
    public Response<MonitoringCheck> getMonitoringCheck(int id) throws
            IOException {
        return ApiCall.get(baseUri).appendPath(id).as(MonitoringCheck.class);
    }

    @Override
    public Response<Boolean> deleteMonitoringCheck(int id) throws IOException {
        return ApiCall.delete(baseUri).appendPath(id).as(Boolean.class);
    }

    @Override
    public Response<MonitoringCheck> createMonitoringCheck
            (MonitoringCheckBuilder data) throws IOException {
        return ApiCall.post(baseUri)
                .setParameters((ObjectNode) data)
                .as(MonitoringCheck.class);
    }

    @Override
    public Response<MonitoringCheck> duplicateMonitoringCheck(int id,
                                                              MonitoringCheckBuilder data) throws IOException {
        return ApiCall.post(baseUri)
                .appendPath(id)
                .setParameters((ObjectNode) data)
                .as(MonitoringCheck.class);
    }

    @Override
    public Response<MonitoringStatus> getStatus(int id) throws IOException {
        return ApiCall.get(baseUri)
                .appendPath(id)
                .appendPath("status")
                .as(MonitoringStatus.class);
    }

    @Override
    public Response<List<MonitoringStatus>> getStatuses(String address) throws IOException {
        return ApiCall.get(baseUri)
                .appendPath(address)
                .appendPath("status")
                .asList(MonitoringStatus.class);
    }
}
