package eu.df.jiffybox.modules;

import eu.df.jiffybox.models.Plan;
import eu.df.jiffybox.models.Response;
import feign.Param;
import feign.RequestLine;

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
     */
    @RequestLine("GET /plans")
    Response<Map<String, Plan>> getPlans();

    /**
     * Provides detail of a specified plan. The plan may be obtained using its
     * ID or its name.
     *
     * @param id id
     * @return The retrieved Plan.
     */
    @RequestLine("GET /plans/{id}")
    Response<Plan> getPlan(@Param("id") int id);

    /**
     * Provides detail of a specified plan. The plan may be obtained using its
     * ID or its name.
     *
     * @param name name
     * @return The retrieved Plan.
     */
    @RequestLine("GET /plans/{name}")
    Response<Plan> getPlan(@Param("name") String name);
}
