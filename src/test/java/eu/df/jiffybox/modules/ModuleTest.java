package eu.df.jiffybox.modules;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import eu.df.jiffybox.JiffyBoxApi;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Base class of all module tests providing predefined modules.
 */
@RunWith(Parameterized.class)
public abstract class ModuleTest {

    /**
     * The test host.
     */
    protected static final String TEST_HOST = "http://localhost:9090";

    /**
     * The test token.
     */
    private static final String TEST_TOKEN = "00000000000000000000000000000000";

    /**
     * The real token.
     */
    private static final String REAL_TOKEN = System.getenv("JIFFYBOX_API_TOKEN");

    /**
     * Set up wiremock rule.
     */
    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(9090);

    /**
     * Provide parameters for test cases which implement integrations tests.
     *
     * @return A list of {@link JiffyBoxApi}s. The first element is the local test API. The second element is an API
     * created using an access token read from the JIFFYBOX_API_TOKEN environment variable.
     */
    @Parameterized.Parameters
    public static Iterable<JiffyBoxApi> parameters() {
        Collection<JiffyBoxApi> parameters = new ArrayList<>();
        parameters.add(new JiffyBoxApi(TEST_TOKEN, TEST_HOST));

        if (REAL_TOKEN != null) {
            parameters.add(new JiffyBoxApi(REAL_TOKEN));
        }

        return parameters;
    }
}
