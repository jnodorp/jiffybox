package eu.df.jiffybox.internal;

import eu.df.jiffybox.models.Distribution;
import eu.df.jiffybox.models.Response;
import eu.df.jiffybox.modules.ModuleDistributions;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * Implementation of the distributions module.
 *
 * @see eu.df.jiffybox.modules.ModuleDistributions
 */
public class ModuleDistributionsImpl implements ModuleDistributions {

    /**
     * The base URI.
     */
    private final URI baseUri;

    /**
     * Create an instance of this module using the specified base URI.
     *
     * @param baseUri The base URI to use.
     */
    public ModuleDistributionsImpl(final URI baseUri) {
        this.baseUri = URI.create(baseUri + "/distributions");
    }

    @Override
    public Response<Map<String, Distribution>> getDistributions() throws
            IOException {
        return ApiCall.get(baseUri).asMap(String.class, Distribution.class);
    }

    @Override
    public Response<Distribution> getDistribution(String id) throws
            IOException {
        return ApiCall.get(baseUri).appendPath(id).as(Distribution.class);
    }
}
