package eu.df.jiffybox.modules;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import eu.df.jiffybox.JiffyBoxApi;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * Rule to allow local (WireMock) and remote (JiffyBox API) test execution.
 */
class ModuleTestRule implements TestRule {

    /**
     * The test host.
     */
    private static final String TEST_HOST = "http://localhost";

    /**
     * The test token.
     */
    private static final String TEST_TOKEN = "00000000000000000000000000000000";

    /**
     * The real token.
     */
    private static final String REAL_TOKEN = System.getenv("JIFFYBOX_API_TOKEN");

    private final WireMockRule wireMockRule;

    private final boolean runAgainstServer;

    private JiffyBoxApi jiffyBoxApi;

    ModuleTestRule(boolean runAgainstServer) {
        this.wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
        this.runAgainstServer = runAgainstServer;
    }

    ModuleTestRule(WireMockRule wireMockRule, boolean runAgainstServer) {
        this.wireMockRule = wireMockRule;
        this.runAgainstServer = runAgainstServer;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                jiffyBoxApi = new JiffyBoxApi(TEST_TOKEN, TEST_HOST + ":" + wireMockRule.port());
                base.evaluate();

                if (REAL_TOKEN != null && runAgainstServer) {
                    jiffyBoxApi = new JiffyBoxApi(REAL_TOKEN);
                    base.evaluate();
                }
            }
        };
    }

    JiffyBoxApi get() {
        return jiffyBoxApi;
    }

    StubMapping stubFor(MappingBuilder mappingBuilder) {
        return wireMockRule.stubFor(mappingBuilder);
    }
}
