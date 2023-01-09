package io.github.jnodorp.jiffybox.builders;

/**
 * This class is used to create jiffy box builders of different types. Use any
 * of the methods you need and get the builder.
 */
public interface JiffyBoxBuilderFactory {

    /**
     * Get a JiffyBox builder.
     *
     * @param backupid The backup id.
     * @return A JiffyBox builder.
     */
    JiffyBoxBuilder fromBackup(final String backupid);

    /**
     * Get a JiffyBox builder.
     *
     * @param distribution The distribution.
     * @return A JiffyBox builder.
     */
    JiffyBoxBuilder fromDistribution(final String distribution);
}
