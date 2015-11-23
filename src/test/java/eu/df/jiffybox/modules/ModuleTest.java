package eu.df.jiffybox.modules;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import eu.df.jiffybox.JiffyBoxApi;
import org.junit.Rule;

/**
 * Base class of all module tests providing predefined modules.
 */
public abstract class ModuleTest {

    /**
     * The test host.
     */
    protected static final String TEST_HOST = "http://localhost:9090";

    /**
     * The test token.
     */
    protected static final String TEST_TOKEN = "00000000000000000000000000000000";

    /**
     * An API instance using the test configuration.
     */
    private static final JiffyBoxApi api = new JiffyBoxApi(TEST_TOKEN, TEST_HOST);

    /**
     * The backups module.
     */
    static final ModuleBackups backups = api.getModuleBackups();

    /**
     * The contact groups module.
     */
    static final ModuleContactGroups contactGroups = api.getModuleContactGroups();

    /**
     * The distributions module.
     */
    static final ModuleDistributions distributions = api.getModuleDistributions();

    /**
     * The distributions module.
     */
    static final ModuleDoc doc = api.getModuleDoc();

    /**
     * The ips module.
     */
    static final ModuleIps ips = api.getModuleIps();

    /**
     * The jiffyBoxes module.
     */
    static final ModuleJiffyBoxes jiffyBoxes = api.getModuleJiffyBoxes();

    /**
     * The monitoring module.
     */
    static final ModuleMonitoring monitoring = api.getModuleMonitoring();

    /**
     * The plans module.
     */
    static final ModulePlans plans = api.getModulePlans();

    /**
     * Set up wiremock rule.
     */
    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(9090);
}
