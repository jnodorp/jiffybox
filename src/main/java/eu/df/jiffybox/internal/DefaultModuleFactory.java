package eu.df.jiffybox.internal;

import eu.df.jiffybox.ModuleFactory;
import eu.df.jiffybox.modules.*;

import java.net.URI;

/**
 * The default module factory is used to obtain module instances.
 */
public class DefaultModuleFactory extends ModuleFactory {

    /**
     * The base URI.
     */
    private final URI uri;

    /**
     * Create a default module factory.
     *
     * @param host    The host to call.
     * @param version The API version to use.
     * @param token   The API token to use.
     */
    public DefaultModuleFactory(final String host, final String version,
                                final String token) {
        super();
        this.uri = URI.create(host + "/" + token + "/" + version);
    }

    /**
     * Return an instance of the 'backups' module.
     *
     * @return An instance of the 'backups' module.
     */
    public ModuleBackups backups() {
        return new ModuleBackupsImpl(uri);
    }

    /**
     * Return an instance of the 'contactGroups' module.
     *
     * @return An instance of the 'contactGroups' module.
     */
    public ModuleContactGroups contactGroups() {
        return new ModuleContactGroupsImpl(uri);
    }

    /**
     * Return an instance of the 'distributions' module.
     *
     * @return An instance of the 'distributions' module.
     */
    public ModuleDistributions distributions() {
        return new ModuleDistributionsImpl(uri);
    }

    /**
     * Return an instance of the 'doc' module.
     *
     * @return An instance of the 'doc' module.
     */
    public ModuleDoc doc() {
        return new ModuleDocImpl(uri);
    }

    /**
     * Return an instance of the 'ips' module.
     *
     * @return An instance of the 'ips' module.
     */
    public ModuleIps ips() {
        return new ModuleIpsImpl(uri);
    }

    /**
     * Return an instance of the 'jiffyBoxes' module.
     *
     * @return An instance of the 'jiffyBoxes' module.
     */
    public ModuleJiffyBoxes jiffyBoxes() {
        return new ModuleJiffyBoxesImpl(uri);
    }

    /**
     * Return an instance of the 'monitoring' module.
     *
     * @return An instance of the 'monitoring' module.
     */
    public ModuleMonitoring monitoring() {
        return new ModuleMonitoringImpl(uri);
    }

    /**
     * Return an instance of the 'plans' module.
     *
     * @return An instance of the 'plans' module.
     */
    public ModulePlans plans() {
        return new ModulePlansImpl(uri);
    }
}
