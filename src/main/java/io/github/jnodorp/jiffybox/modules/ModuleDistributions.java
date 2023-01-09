package io.github.jnodorp.jiffybox.modules;

import io.github.jnodorp.jiffybox.models.Distribution;
import io.github.jnodorp.jiffybox.models.Response;
import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.Map;

/**
 * This interface describes the distributions module.
 */
public interface ModuleDistributions {

    /**
     * Build the module.
     *
     * @param baseUrl the base URL
     * @return the module
     */
    static ModuleDistributions build(String baseUrl) {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(ModuleDistributions.class, baseUrl);
    }

    /**
     * Lists all Linux distributions available for initializing hard drives
     * and JiffyBoxes. Each time those distributions are referenced by other
     * commands the key value of the hash (e. g. centos_5_4_32bit) has to be
     * used.
     *
     * @return The retrieved distributions.
     */
    @RequestLine("GET /distributions")
    Response<Map<String, Distribution>> getDistributions();

    /**
     * With this call you get details to a specified Linux distribution. id
     * is the hashes key value obtained by reading the whole list.
     *
     * @param id The id.
     * @return The retrieved Distribution
     */
    @RequestLine("GET /distributions/{id}")
    Response<Distribution> getDistribution(@Param("id") String id);
}
