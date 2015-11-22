package eu.df.jiffybox.internal;

import com.fasterxml.jackson.databind.node.ObjectNode;
import eu.df.jiffybox.builders.JiffyBoxBuilder;
import eu.df.jiffybox.models.JiffyBox;
import eu.df.jiffybox.models.Response;
import eu.df.jiffybox.modules.ModuleJiffyBoxes;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * Implementation of the jiffy boxes module.
 *
 * @see eu.df.jiffybox.modules.ModuleJiffyBoxes
 */
public class ModuleJiffyBoxesImpl implements ModuleJiffyBoxes {

    /**
     * The START status.
     */
    private static final String STATUS_START = "START";

    /**
     * The PULLPLUG status.
     */
    private static final String STATUS_PULL_PLUG = "PULLPLUG";

    /**
     * The SHUTDOWN status.
     */
    private static final String STATUS_SHUTDOWN = "SHUTDOWN";

    /**
     * The FREEZE status.
     */
    private static final String STATUS_FREEZE = "FREEZE";

    /**
     * The THAW status.
     */
    private static final String STATUS_THAW = "THAW";

    /**
     * The PLAN status.
     */
    private static final String STATUS_PLAN = "PLAN";

    /**
     * The parameter 'status'.
     */
    private static final String PARAMETER_STATUS = "status";

    /**
     * The parameter 'name'.
     */
    private static final String PARAMETER_NAME = "name";

    /**
     * The parameter 'planid'.
     */
    private static final String PARAMETER_PLAN_ID = "planid";

    /**
     * The base URI.
     */
    private final URI baseUri;

    /**
     * Create an instance of this module using the specified base URI.
     *
     * @param baseUri The base URI to use.
     */
    public ModuleJiffyBoxesImpl(final URI baseUri) {
        this.baseUri = URI.create(baseUri + "/jiffyBoxes");
    }

    @Override
    public Response<Map<String, JiffyBox>> getJiffyBoxes() throws IOException {
        return ApiCall.get(baseUri).asMap(String.class, JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> getJiffyBox(int id) throws IOException {
        return ApiCall.get(baseUri).appendPath(id).as(JiffyBox.class);
    }

    @Override
    public Response<Boolean> deleteJiffyBox(int id) throws IOException {
        return ApiCall.delete(baseUri).appendPath(id).as(Boolean.class);
    }

    @Override
    public Response<JiffyBox> createJiffyBox(JiffyBoxBuilder data)
            throws IOException {
        return ApiCall.post(baseUri).setParameters((ObjectNode) data).as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> duplicateJiffyBox(int id, String name, int
            planId) throws IOException {
        return ApiCall.post(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_NAME, name)
                      .addParameter(PARAMETER_PLAN_ID, planId)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> duplicateJiffyBox(int id, String name, String
            planId) throws IOException {
        return ApiCall.post(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_NAME, name)
                      .addParameter(PARAMETER_PLAN_ID, planId)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> duplicateJiffyBox(int id, String name, int
            planId, ObjectNode metadata) throws IOException {
        return ApiCall.post(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_NAME, name)
                      .addParameter(PARAMETER_PLAN_ID, planId)
                      .addMetadata(metadata)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> duplicateJiffyBox(int id, String name, String
            planId, ObjectNode metadata) throws IOException {
        return ApiCall.post(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_NAME, name)
                      .addParameter(PARAMETER_PLAN_ID, planId)
                      .addMetadata(metadata)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> startJiffyBox(int id) throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_START)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> startJiffyBox(int id, ObjectNode metadata)
            throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_START)
                      .addMetadata(metadata)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> shutdownJiffyBox(int id) throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_SHUTDOWN)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> shutdownJiffyBox(int id, ObjectNode metadata)
            throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_SHUTDOWN)
                      .addMetadata(metadata)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> pullplugJiffyBox(int id) throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_PULL_PLUG)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> pullplugJiffyBox(int id, ObjectNode metadata)
            throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_PULL_PLUG)
                      .addMetadata(metadata)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> freezeJiffyBox(int id) throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_FREEZE)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> freezeJiffyBox(int id, ObjectNode metadata)
            throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_FREEZE)
                      .addMetadata(metadata)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> thawJiffyBox(int id, int planId) throws
            IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_THAW)
                      .addParameter(PARAMETER_PLAN_ID, planId)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> thawJiffyBox(int id, String planId) throws
            IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_THAW)
                      .addParameter(PARAMETER_PLAN_ID, planId)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> thawJiffyBox(int id, int planId, ObjectNode
            metadata) throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_THAW)
                      .addParameter(PARAMETER_PLAN_ID, planId)
                      .addMetadata(metadata)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> thawJiffyBox(int id, String planId, ObjectNode
            metadata) throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_THAW)
                      .addParameter(PARAMETER_PLAN_ID, planId)
                      .addMetadata(metadata)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> changePlanJiffyBox(int id, int planId) throws
            IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_PLAN)
                      .addParameter(PARAMETER_PLAN_ID, planId)
                      .as(JiffyBox.class);
    }

    @Override
    public Response<JiffyBox> changePlanJiffyBox(int id, String planId)
            throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_STATUS, STATUS_PLAN)
                      .addParameter(PARAMETER_PLAN_ID, planId)
                      .as(JiffyBox.class);
    }
}
