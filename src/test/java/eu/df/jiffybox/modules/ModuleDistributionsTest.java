package eu.df.jiffybox.modules;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import eu.df.jiffybox.WireMockHelper;
import eu.df.jiffybox.models.Distribution;
import eu.df.jiffybox.models.Message;
import eu.df.jiffybox.models.Response;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the 'distributions' module.
 */
public class ModuleDistributionsTest {

    private final WireMockRule wireMock = new WireMockRule(wireMockConfig().dynamicPort());

    private final ModuleTestRule module = new ModuleTestRule(wireMock, true);

    @Rule
    public final RuleChain ruleChain = RuleChain.outerRule(wireMock).around(module);

    /**
     * Test for {@link ModuleDistributions#getDistributions()}.
     */
    @Test
    public void testGetDistributions() {
        wireMock.stubFor(get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/distributions")).willReturn
                (aResponse()
                .withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/distributions/testGetDistributions.json")));

        Response<Map<String, Distribution>> response = module.get().distributions().getDistributions();
        List<Message> messages = response.getMessages();
        Map<String, Distribution> distributions = response.getResult();
        assertEquals(8, distributions.size());

        Distribution distribution1 = distributions.get("centos_6_0_64bit");
        Distribution distribution2 = distributions.get("centos_7_1_64bit");
        Distribution distribution3 = distributions.get("debian_jessie_64bit");
        Distribution distribution4 = distributions.get("fedora_24_64bit");
        Distribution distribution5 = distributions.get("opensuse_13_64bit");
        Distribution distribution6 = distributions.get("opensuse_42_64bit");
        Distribution distribution7 = distributions.get("ubuntu_14_4_lts_64bit");
        Distribution distribution8 = distributions.get("ubuntu_16_4_lts_64bit");

        assertTrue(messages.isEmpty());

        assertEquals(2048, distribution1.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution1.getDefaultKernel());
        assertEquals("CentOS 6 64-Bit", distribution1.getName());
        assertEquals("ro", distribution1.getRootdiskMode());

        assertEquals(2560, distribution2.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution2.getDefaultKernel());
        assertEquals("CentOS 7 64-Bit", distribution2.getName());
        assertEquals("ro", distribution2.getRootdiskMode());

        assertEquals(2048, distribution3.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution3.getDefaultKernel());
        assertEquals("Debian Jessie (8) 64-Bit", distribution3.getName());
        assertEquals("ro", distribution3.getRootdiskMode());

        assertEquals(2048, distribution4.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution4.getDefaultKernel());
        assertEquals("Fedora 24 64-Bit", distribution4.getName());
        assertEquals("ro", distribution4.getRootdiskMode());

        assertEquals(2048, distribution5.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution5.getDefaultKernel());
        assertEquals("openSUSE 13.2 64-bit", distribution5.getName());
        assertEquals("ro", distribution5.getRootdiskMode());

        assertEquals(2048, distribution6.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution6.getDefaultKernel());
        assertEquals("openSUSE 42.1 64-Bit", distribution6.getName());
        assertEquals("ro", distribution6.getRootdiskMode());

        assertEquals(2048, distribution7.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution7.getDefaultKernel());
        assertEquals("Ubuntu 14.04 LTS 64-Bit", distribution7.getName());
        assertEquals("ro", distribution7.getRootdiskMode());

        assertEquals(2048, distribution8.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution8.getDefaultKernel());
        assertEquals("Ubuntu 16.04 LTS 64-Bit", distribution8.getName());
        assertEquals("ro", distribution8.getRootdiskMode());
    }

    /**
     * Test for {@link ModuleDistributions#getDistribution(String)}.
     */
    @Test
    public void testGetDistribution() {
        wireMock.stubFor(get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/distributions/centos_5_4_64bit"))
                .willReturn(aResponse()
                .withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/distributions/testGetDistribution.json")));

        Response<Distribution> response = module.get().distributions().getDistribution("centos_5_4_64bit");
        List<Message> messages = response.getMessages();
        Distribution distribution = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(1024, distribution.getMinDiskSizeMB());
        assertEquals("xen-current-x86_64", distribution.getDefaultKernel());
        assertEquals("CentOS 5.4 64-Bit", distribution.getName());
        assertEquals("ro", distribution.getRootdiskMode());
    }
}
