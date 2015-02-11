package eu.df.jiffybox.modules;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import eu.df.jiffybox.ModuleFactory;
import org.junit.Rule;

/**
 * Base class of all module tests providing predefined modules.
 */
public abstract class ModuleTest {

    /**
     * The test host.
     */
    private static final String TEST_HOST = "http://localhost:9090";

    /**
     * The test token.
     */
    private static final String TEST_TOKEN = "00000000000000000000000000000000";

    /**
     * An API instance using the test configuration.
     */
    private static final ModuleFactory api = ModuleFactory.getInstance(TEST_HOST,
            TEST_TOKEN);

    /**
     * The backups module.
     */
    static final ModuleBackups backups = api.backups();

    /**
     * The contact groups module.
     */
    static final ModuleContactGroups contactGroups = api.contactGroups();

    /**
     * The distributions module.
     */
    static final ModuleDistributions distributions = api.distributions();

    /**
     * The distributions module.
     */
    static final ModuleDoc doc = api.doc();

    /**
     * The ips module.
     */
    static final ModuleIps ips = api.ips();

    /**
     * The jiffyBoxes module.
     */
    static final ModuleJiffyBoxes jiffyBoxes = api.jiffyBoxes();

    /**
     * The monitoring module.
     */
    static final ModuleMonitoring monitoring = api.monitoring();

    /**
     * The plans module.
     */
    static final ModulePlans plans = api.plans();

    /**
     * Set up wiremock rule.
     */
    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(9090);
}
