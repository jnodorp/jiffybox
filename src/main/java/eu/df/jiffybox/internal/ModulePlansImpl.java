package eu.df.jiffybox.internal;

import eu.df.jiffybox.models.Plan;
import eu.df.jiffybox.models.Response;
import eu.df.jiffybox.modules.ModulePlans;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * Implementation of the plans module.
 *
 * @see eu.df.jiffybox.modules.ModulePlans
 */
public class ModulePlansImpl implements ModulePlans {

    /**
     * The base URI.
     */
    private final URI baseUri;

    /**
     * Create an instance of this module using the specified base URI.
     *
     * @param baseUri The base URI to use.
     */
    public ModulePlansImpl(final URI baseUri) {
        this.baseUri = URI.create(baseUri + "/plans");
    }

    @Override
    public Response<Map<String, Plan>> getPlans() throws IOException {
        return ApiCall.get(baseUri).asMap(String.class, Plan.class);
    }

    @Override
    public Response<Plan> getPlan(int id) throws IOException {
        return ApiCall.get(baseUri).appendPath(id).as(Plan.class);
    }

    @Override
    public Response<Plan> getPlan(String name) throws IOException {
        return ApiCall.get(baseUri).appendPath(name).as(Plan.class);
    }
}
