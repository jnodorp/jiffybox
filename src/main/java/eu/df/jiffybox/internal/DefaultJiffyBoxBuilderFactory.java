package eu.df.jiffybox.internal;

import eu.df.jiffybox.builders.JiffyBoxBuilder;
import eu.df.jiffybox.builders.JiffyBoxBuilderFactory;

/**
 * This class is used to create jiffy box builders of different types. Use any
 * of the methods you need and get the builder.
 */
public class DefaultJiffyBoxBuilderFactory implements JiffyBoxBuilderFactory {

    /**
     * The name.
     */
    private final String name;

    /**
     * The planId.
     */
    private final Object planId;

    /**
     * Is the planId integer?
     */
    private final boolean integer;

    /**
     * Create a new factory for JiffyBoxes.
     *
     * @param name   The name.
     * @param planId The planId.
     */
    public DefaultJiffyBoxBuilderFactory(final String name, final int planId) {
        this.name = name;
        this.planId = planId;
        this.integer = true;
    }

    /**
     * Create a new factory for JiffyBoxes.
     *
     * @param name   The name.
     * @param planId The planId.
     */
    public DefaultJiffyBoxBuilderFactory(final String name, final String
            planId) {
        this.name = name;
        this.planId = planId;
        this.integer = false;
    }

    @Override
    public JiffyBoxBuilder fromBackup(final String backupid) {
        if (integer) {
            return DefaultJiffyBoxBuilder.fromBackup(name, (int) planId,
                    backupid);
        } else {
            return DefaultJiffyBoxBuilder.fromBackup(name, (String) planId,
                    backupid);
        }
    }

    @Override
    public JiffyBoxBuilder fromDistribution(final String distribution) {
        if (integer) {
            return DefaultJiffyBoxBuilder.fromDistribution(name, (int)
                    planId, distribution);
        } else {
            return DefaultJiffyBoxBuilder.fromDistribution(name, (String)
                    planId, distribution);
        }
    }
}
