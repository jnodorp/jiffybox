package eu.df.jiffybox.modules;

import eu.df.jiffybox.models.Distribution;
import eu.df.jiffybox.models.Response;

import java.io.IOException;
import java.util.Map;

/**
 * This interface describes the distributions module.
 */
public interface ModuleDistributions {

    /**
     * Lists all Linux distributions available for initializing hard drives
     * and JiffyBoxes. Each time those distributions are referenced by other
     * commands the key value of the hash (e. g. centos_5_4_32bit) has to be
     * used.
     *
     * @return The retrieved distributions.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Map<String, Distribution>> getDistributions() throws IOException;

    /**
     * With this call you get details to a specified Linux distribution. id
     * is the hashes key value obtained by reading the whole list.
     *
     * @param id The id.
     * @return The retrieved Distribution
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Distribution> getDistribution(final String id) throws IOException;
}
