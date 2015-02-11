package eu.df.jiffybox;

import eu.df.jiffybox.internal.DefaultModuleFactory;
import eu.df.jiffybox.modules.*;

/**
 * This library provides a wrapper for the JiffyBox API. To access the functions
 * use this classes factory methods. Use 'getInstance'.
 * This class is the main interface used for communication with this API.
 * Instances can be obtained using the 'JiffyBoxApi' 'create' (production) and
 * 'test' (testing) methods. Instances of the modules are created by the methods
 * of this class.
 *
 * @see eu.df.jiffybox.modules.ModuleBackups
 * @see eu.df.jiffybox.modules.ModuleContactGroups
 * @see eu.df.jiffybox.modules.ModuleDistributions
 * @see eu.df.jiffybox.modules.ModuleDoc
 * @see eu.df.jiffybox.modules.ModuleIps
 * @see eu.df.jiffybox.modules.ModuleJiffyBoxes
 * @see eu.df.jiffybox.modules.ModuleMonitoring
 * @see eu.df.jiffybox.modules.ModulePlans
 */
public abstract class ModuleFactory {

    /**
     * The production host.
     */
    private static final String HOST = "https://api.jiffybox.de";

    /**
     * The API version.
     */
    private static final String VERSION = "v1.0";

    /**
     * Disable default constructor.
     */
    protected ModuleFactory() {
    }

    /**
     * Get a new module factory instance using the given token.
     *
     * @param token The API token to use.
     * @return The module factory instance using the given token.
     */
    public static ModuleFactory getInstance(final String token) {
        return getInstance(HOST, token);
    }

    /**
     * Get a new module factory testing instance using the testing
     * configuration.
     *
     * @param host  The host.
     * @param token The API token to use.
     * @return The module factory testing instance using the testing
     * configuration.
     */
    public static ModuleFactory getInstance(final String host, final String
            token) {
        return new DefaultModuleFactory(host, VERSION, token);
    }

    /**
     * Get an instance of the backups module.
     *
     * @return An instance of the backups module.
     */
    public abstract ModuleBackups backups();

    /**
     * Get an instance of the contact groups module.
     *
     * @return An instance of the contact groups module.
     */
    public abstract ModuleContactGroups contactGroups();

    /**
     * Get an instance of the distributions module.
     *
     * @return An instance of the distributions module.
     */
    public abstract ModuleDistributions distributions();

    /**
     * Get an instance of the doc module.
     *
     * @return An instance of the doc module.
     */
    public abstract ModuleDoc doc();

    /**
     * Get an instance of the ips module.
     *
     * @return An instance of the ips module.
     */
    public abstract ModuleIps ips();

    /**
     * Get an instance of the jiffy boxes module.
     *
     * @return An instance of the jiffy boxes module.
     */
    public abstract ModuleJiffyBoxes jiffyBoxes();

    /**
     * Get an instance of the monitoring module.
     *
     * @return An instance of the monitoring module.
     */
    public abstract ModuleMonitoring monitoring();

    /**
     * Get an instance of the plans module.
     *
     * @return An instance of the plans module.
     */
    public abstract ModulePlans plans();
}
