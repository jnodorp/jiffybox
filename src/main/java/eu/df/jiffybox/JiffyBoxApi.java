package eu.df.jiffybox;

import eu.df.jiffybox.internal.*;
import eu.df.jiffybox.modules.*;

import java.net.URI;

/**
 * This is the base class of the JiffyBox API. Usually is should be created only once. You have to specify the API token
 * to use. Furthermore is is possible to add a host, which is useful for testing purposes.
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
public class JiffyBoxApi {

    /**
     * The default host.
     */
    private static final String DEFAULT_HOST = "https://api.jiffybox.de";

    /**
     * The default API version.
     */
    private static final String DEFAULT_VERSION = "v1.0";

    /**
     * The {@link ModuleBackups}.
     */
    private final ModuleBackups moduleBackups;

    /**
     * The {@link ModuleContactGroups}.
     */
    private final ModuleContactGroups moduleContactGroups;

    /**
     * The {@link ModuleDistributions}.
     */
    private final ModuleDistributions moduleDistributions;

    /**
     * The {@link ModuleDoc}.
     */
    private final ModuleDoc moduleDoc;

    /**
     * The {@link ModuleIps}.
     */
    private final ModuleIps moduleIps;

    /**
     * The {@link ModuleJiffyBoxes}.
     */
    private final ModuleJiffyBoxes moduleJiffyBoxes;

    /**
     * The {@link ModuleMonitoring}.
     */
    private final ModuleMonitoring moduleMonitoring;

    /**
     * The {@link ModulePlans}.
     */
    private final ModulePlans modulePlans;

    /**
     * Create a new instance using a specified token and the default host.
     *
     * @param token The API token.
     */
    public JiffyBoxApi(final String token) {
        this(token, DEFAULT_HOST, DEFAULT_VERSION);
    }

    /**
     * Create a new instance using a specified token and host.
     *
     * @param token The API token.
     * @param host  The host.
     */
    public JiffyBoxApi(final String token, final String host) {
        this(token, host, DEFAULT_VERSION);
    }

    /**
     * Create a new instance using a specified token, host and version.
     *
     * @param token   The API token.
     * @param host    The host.
     * @param version The version.
     */
    protected JiffyBoxApi(final String token, final String host, final String version) {
        final URI uri = URI.create(host + "/" + token + "/" + version);

        this.moduleBackups = new ModuleBackupsImpl(uri);
        this.moduleContactGroups = new ModuleContactGroupsImpl(uri);
        this.moduleDistributions = new ModuleDistributionsImpl(uri);
        this.moduleDoc = new ModuleDocImpl(uri);
        this.moduleIps = new ModuleIpsImpl(uri);
        this.moduleJiffyBoxes = new ModuleJiffyBoxesImpl(uri);
        this.moduleMonitoring = new ModuleMonitoringImpl(uri);
        this.modulePlans = new ModulePlansImpl(uri);
    }

    /**
     * Get an instance of the backups module.
     *
     * @return An instance of the backups module.
     */
    public ModuleBackups getModuleBackups() {
        return moduleBackups;
    }

    /**
     * Get an instance of the contact groups module.
     *
     * @return An instance of the contact groups module.
     */
    public ModuleContactGroups getModuleContactGroups() {
        return moduleContactGroups;
    }

    /**
     * Get an instance of the distributions module.
     *
     * @return An instance of the distributions module.
     */
    public ModuleDistributions getModuleDistributions() {
        return moduleDistributions;
    }

    /**
     * Get an instance of the doc module.
     *
     * @return An instance of the doc module.
     */
    public ModuleDoc getModuleDoc() {
        return moduleDoc;
    }

    /**
     * Get an instance of the ips module.
     *
     * @return An instance of the ips module.
     */
    public ModuleIps getModuleIps() {
        return moduleIps;
    }

    /**
     * Get an instance of the jiffy boxes module.
     *
     * @return An instance of the jiffy boxes module.
     */
    public ModuleJiffyBoxes getModuleJiffyBoxes() {
        return moduleJiffyBoxes;
    }

    /**
     * Get an instance of the monitoring module.
     *
     * @return An instance of the monitoring module.
     */
    public ModuleMonitoring getModuleMonitoring() {
        return moduleMonitoring;
    }

    /**
     * Get an instance of the plans module.
     *
     * @return An instance of the plans module.
     */
    public ModulePlans getModulePlans() {
        return modulePlans;
    }
}
