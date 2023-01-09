package io.github.jnodorp.jiffybox.internal;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.jnodorp.jiffybox.builders.JiffyBoxBuilder;

/**
 * This class is used to build JiffyBoxes. Use any of the methods you need and
 * get the built object using the 'build' method.
 */
public class DefaultJiffyBoxBuilder extends DefaultBuilder implements
        JiffyBoxBuilder {

    /**
     * The parameter 'name'.
     */
    private static final String PARAMETER_NAME = "name";

    /**
     * The parameter 'planid'.
     */
    private static final String PARAMETER_PLAN_ID = "planid";

    /**
     * The parameter 'distribution'.
     */
    private static final String PARAMETER_DISTRIBUTION = "distribution";

    /**
     * The parameter 'backupid'.
     */
    private static final String PARAMETER_BACKUP_ID = "backupid";

    /**
     * The parameter 'password'.
     */
    private static final String PARAMETER_PASSWORD = "password";

    /**
     * The parameter 'use_sshkey'.
     */
    private static final String PARAMETER_USE_SSH_KEY = "use_sshkey";

    /**
     * The parameter 'metadata'.
     */
    private static final String PARAMETER_METADATA = "metadata";

    /**
     * Create a JiffyBox from scratch.
     *
     * @param name         The name of the JiffyBoxes to create. Up to 30
     *                     characters are allowed. Allowed characters are {@code
     *                     a-zA-Z0-9üöäÜÖÄß_()=!\*@.-} and spaces. The
     *                     JiffyBoxes name has to be unique.
     * @param planId       The plan of a JiffyBox is either an unique number or
     *                     the plans name (CloudLevel 1, CloudLevel 2 etc.). The
     *                     IDs and other details of the plan can be obtained via
     *                     the plans module.
     * @param distribution When provided, this is the distribution the new
     *                     JiffyBox will have. A list of all distributions can
     *                     be obtained via the distributions module. This
     *                     parameter and backupid exclude each other but it is
     *                     mandatory to provide one of them.
     * @return The JiffyBoxBuilder.
     */
    public static JiffyBoxBuilder fromDistribution(final String name, final
    String planId, final String distribution) {
        DefaultJiffyBoxBuilder result = new DefaultJiffyBoxBuilder();
        result.put(PARAMETER_NAME, name);
        result.put(PARAMETER_PLAN_ID, planId);
        result.put(PARAMETER_DISTRIBUTION, distribution);
        return result;
    }

    /**
     * Create a JiffyBox from scratch.
     *
     * @param name         The name of the JiffyBoxes to create. Up to 30
     *                     characters are allowed. Allowed characters are {@code
     *                     a-zA-Z0-9üöäÜÖÄß_()=!\*@.-} and spaces. The
     *                     JiffyBoxes name has to be unique.
     * @param planId       The plan of a JiffyBox is either an unique number or
     *                     the plans name (CloudLevel 1, CloudLevel 2 etc.). The
     *                     IDs and other details of the plan can be obtained via
     *                     the plans module.
     * @param distribution When provided, this is the distribution the new
     *                     JiffyBox will have. A list of all distributions can
     *                     be obtained via the distributions module. This
     *                     parameter and backupid exclude each other but it is
     *                     mandatory to provide one of them.
     * @return The JiffyBoxBuilder.
     */
    public static JiffyBoxBuilder fromDistribution(final String name, final
    int planId, final String distribution) {
        DefaultJiffyBoxBuilder result = new DefaultJiffyBoxBuilder();
        result.put(PARAMETER_NAME, name);
        result.put(PARAMETER_PLAN_ID, planId);
        result.put(PARAMETER_DISTRIBUTION, distribution);
        return result;
    }

    /**
     * Create a JiffyBox from a backup.
     *
     * @param name     The name of the JiffyBoxes to create. Up to 30 characters
     *                 are allowed. Allowed characters are {@code
     *                 a-zA-Z0-9üöäÜÖÄß_()=!\*@.-} and spaces. The JiffyBoxes
     *                 name has to be unique.
     * @param planId   The plan of a JiffyBox is either an unique number or the
     *                 plans name (CloudLevel 1, CloudLevel 2 etc.). The IDs and
     *                 other details of the plan can be obtained via the plans
     *                 module.
     * @param backupid When provided, this is the backup ID of the backup from
     *                 which the JiffyBox should be created. A list of all
     *                 backup IDs can be obtained via the backups module. This
     *                 parameter and distribution exclude each other but it is
     *                 mandatory to provide one of them.
     * @return The JiffyBoxBuilder.
     */
    public static JiffyBoxBuilder fromBackup(final String name, final String
            planId, final String backupid) {
        DefaultJiffyBoxBuilder result = new DefaultJiffyBoxBuilder();
        result.put(PARAMETER_NAME, name);
        result.put(PARAMETER_PLAN_ID, planId);
        result.put(PARAMETER_BACKUP_ID, backupid);
        return result;
    }

    /**
     * Create a JiffyBox from a backup.
     *
     * @param name     The name of the JiffyBoxes to create. Up to 30 characters
     *                 are allowed. Allowed characters are {@code
     *                 a-zA-Z0-9üöäÜÖÄß_()=!\*@.-} and spaces. The JiffyBoxes
     *                 name has to be unique.
     * @param planId   The plan of a JiffyBox is either an unique number or the
     *                 plans name (CloudLevel 1, CloudLevel 2 etc.). The IDs and
     *                 other details of the plan can be obtained via the plans
     *                 module.
     * @param backupid When provided, this is the backup ID of the backup from
     *                 which the JiffyBox should be created. A list of all
     *                 backup IDs can be obtained via the backups module. This
     *                 parameter and distribution exclude each other but it is
     *                 mandatory to provide one of them.
     * @return The JiffyBoxBuilder.
     */
    public static JiffyBoxBuilder fromBackup(final String name, final int
            planId, final String backupid) {
        DefaultJiffyBoxBuilder result = new DefaultJiffyBoxBuilder();
        result.put(PARAMETER_NAME, name);
        result.put(PARAMETER_PLAN_ID, planId);
        result.put(PARAMETER_BACKUP_ID, backupid);
        return result;
    }

    @Override
    public JiffyBoxBuilder withPassword(final String password) {
        put(PARAMETER_PASSWORD, password);
        return this;
    }

    @Override
    public JiffyBoxBuilder useSshKey(final boolean useSshKey) {
        put(PARAMETER_USE_SSH_KEY, useSshKey);
        return this;
    }

    @Override
    public JiffyBoxBuilder withMetadata(final ObjectNode metadata) {
        putObject(PARAMETER_METADATA).setAll(metadata);
        return this;
    }
}
