package io.github.jnodorp.jiffybox.modules;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.jnodorp.jiffybox.JiffyBoxApi;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;
import static org.junit.platform.commons.util.AnnotationUtils.isAnnotated;

/**
 * Rule to allow local (WireMock) and remote (JiffyBox API) test execution.
 */
class ModuleTestExtension implements TestTemplateInvocationContextProvider {

    /**
     * The namespace used when storing the WireMock port in the context.
     */
    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(ModuleTestExtension
            .class);

    /**
     * The key used when storing the WireMock port in the context.
     */
    private static final String STORE_WIREMOCK_PORT = "wiremock.port";

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

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return isAnnotated(context.getTestClass(), ModuleTest.class);
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        List<TestTemplateInvocationContext> contexts = new ArrayList<>();

        Optional<ModuleTest> moduleTest = findAnnotation(context.getTestClass(), ModuleTest.class);
        if (REAL_TOKEN != null && moduleTest.isPresent() && moduleTest.get().runAgainstServer()) {
            contexts.add(new ModuleTestContext(REAL_TOKEN));
        }

        contexts.add(new ModuleTestContext(TEST_TOKEN));
        return contexts.stream();
    }

    /**
     * Marker interface for test classes using the {@link ModuleTestExtension}.
     */
    @TestTemplate
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
    @interface ModuleTest {
        boolean runAgainstServer();
    }

    /**
     * {@link TestTemplateInvocationContext} used to invoke tests wit different API tokens.
     */
    private record ModuleTestContext(String token) implements TestTemplateInvocationContext {

        @Override
        public String getDisplayName(int invocationIndex) {
            return "[" + (token.equals(TEST_TOKEN) ? "test" : "real") + "]";
        }

        @Override
        public List<Extension> getAdditionalExtensions() {
            return Arrays.asList(new WireMockInjector(), new JiffyBoxApiInjector(token));
        }
    }

    /**
     * Extension to inject {@link JiffyBoxApi} instances into tests.
     */
    private record JiffyBoxApiInjector(String token) implements ParameterResolver {

        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext context) {
            return parameterContext.getParameter().getType().isAssignableFrom(JiffyBoxApi.class);
        }

        @Override
        public Object resolveParameter(ParameterContext parameterContext, ExtensionContext context) {
            if (token.equals(TEST_TOKEN)) {
                String port = context.getStore(NAMESPACE).get(STORE_WIREMOCK_PORT).toString();

                return new JiffyBoxApi(token, TEST_HOST + ":" + port);
            } else {
                return new JiffyBoxApi(token);
            }
        }
    }

    /**
     * Extension to inject {@link WireMockServer} instances into tests.
     */
    private static class WireMockInjector implements AfterEachCallback, ParameterResolver {

        /**
         * The {@link WireMockServer}.
         */
        private WireMockServer server;

        @Override
        public void afterEach(ExtensionContext context) {
            if (server == null || !server.isRunning()) {
                return;
            }

            server.resetRequests();
            server.resetToDefaultMappings();
            server.stop();
        }

        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext context) {
            return parameterContext.getParameter().getType().isAssignableFrom(WireMockServer.class);
        }

        @Override
        public Object resolveParameter(ParameterContext parameterContext, ExtensionContext context) {
            if (server != null && server.isRunning()) {
                throw new IllegalStateException();
            }

            server = new WireMockServer(wireMockConfig().dynamicPort());
            server.start();

            context.getStore(NAMESPACE).put(STORE_WIREMOCK_PORT, server.port());
            return server;
        }
    }
}
