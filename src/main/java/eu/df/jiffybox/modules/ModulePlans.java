package eu.df.jiffybox.modules;

import eu.df.jiffybox.models.Plan;
import eu.df.jiffybox.models.Response;

import java.io.IOException;
import java.util.Map;

/**
 * This interface describes the plans module.
 */
public interface ModulePlans {

    /**
     * Provides a list of all plans available to order. Whenever you refer to a
     * plan you can use its id or name. The results keys are equivalent to the
     * the id.
     *
     * @return The retrieved plans.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Map<String, Plan>> getPlans() throws IOException;

    /**
     * Provides detail of a specified plan. The plan may be obtained using its
     * ID or its name.
     *
     * @param id id
     * @return The retrieved Plan.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Plan> getPlan(final int id) throws IOException;

    /**
     * Provides detail of a specified plan. The plan may be obtained using its
     * ID or its name.
     *
     * @param name name
     * @return The retrieved Plan.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Plan> getPlan(final String name) throws IOException;
}
