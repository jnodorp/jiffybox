package eu.df.jiffybox.internal;

import eu.df.jiffybox.models.IPSet;
import eu.df.jiffybox.models.Response;
import eu.df.jiffybox.modules.ModuleIps;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * Implementation of the ips module.
 *
 * @see eu.df.jiffybox.modules.ModuleIps
 */
public class ModuleIpsImpl implements ModuleIps {

    /**
     * The base URI.
     */
    private final URI baseUri;

    /**
     * Create an instance of this module using the specified base URI.
     *
     * @param baseUri The base URI to use.
     */
    public ModuleIpsImpl(final URI baseUri) {
        this.baseUri = URI.create(baseUri + "/ips");
    }

    @Override
    public Response<Map<String, IPSet>> getIPSets() throws IOException {
        return ApiCall.get(baseUri).asMap(String.class, IPSet.class);
    }

    @Override
    public Response<IPSet> getIPSet(final int id) throws IOException {
        return ApiCall.get(baseUri).appendPath(id).as(IPSet.class);
    }

    @Override
    public Response<Boolean> moveIPAddress(final int boxid, final int ipid,
                                           final int targetid) throws
            IOException {
        return ApiCall.put(baseUri)
                      .appendPath(boxid)
                      .appendPath(ipid)
                      .appendPath("move")
                      .addParameter("targetid", targetid)
                      .as(Boolean.class);
    }
}
