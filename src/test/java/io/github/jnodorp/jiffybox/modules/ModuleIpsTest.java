package io.github.jnodorp.jiffybox.modules;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.jnodorp.jiffybox.JiffyBoxApi;
import io.github.jnodorp.jiffybox.WireMockHelper;
import io.github.jnodorp.jiffybox.models.IP;
import io.github.jnodorp.jiffybox.models.IPSet;
import io.github.jnodorp.jiffybox.models.Message;
import io.github.jnodorp.jiffybox.models.Response;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the 'ips' module.
 */
@ExtendWith(ModuleTestExtension.class)
@ModuleTestExtension.ModuleTest(runAgainstServer = false)
class ModuleIpsTest {

    /**
     * Test for {@link ModuleIps#getIPSets()}.
     */
    @TestTemplate
    void testGetIPSets(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/ips")).willReturn(aResponse()
                .withHeaders(WireMockHelper
                .headers()).withStatus(200).withBodyFile("modules/ips/testGetIPSets.json")));

        Response<Map<String, IPSet>> response = api.ips().getIPSets();
        List<Message> messages = response.getMessages();
        Map<String, IPSet> result = response.getResult();
        IPSet ipSet = result.get("12345");
        Map<String, IP> ips = ipSet.getIps();
        IP ip1 = ips.get("8376");
        IP ip2 = ips.get("8377");
        IP ip3 = ips.get("8463");
        IP ip4 = ips.get("8467");
        IP ip5 = ips.get("8465");

        assertTrue(messages.isEmpty());

        assertEquals(8376, ip1.getId());
        assertEquals(8377, ip2.getId());
        assertEquals(8463, ip3.getId());
        assertEquals(8467, ip4.getId());
        assertEquals(8465, ip5.getId());

        assertEquals("188.1.2.3", ip1.getIp());
        assertEquals("10.1.2.3", ip2.getIp());
        assertEquals("2a00:1234:1:234::1", ip3.getIp());
        assertEquals("2a00:1234:1:211::/56", ip4.getIp());
        assertEquals("188.1.4.241", ip5.getIp());

        assertEquals(4, ip1.getIpVersion());
        assertEquals(4, ip2.getIpVersion());
        assertEquals(6, ip3.getIpVersion());
        assertEquals(6, ip4.getIpVersion());
        assertEquals(4, ip5.getIpVersion());

        assertFalse(ip1.isSubnet());
        assertFalse(ip2.isSubnet());
        assertFalse(ip3.isSubnet());
        assertTrue(ip4.isSubnet());
        assertFalse(ip5.isSubnet());

        assertEquals("blah.blubb.de", ip1.getReverseLookup());
        assertEquals("j4192.servers.jiffybox.net", ip2.getReverseLookup());
        assertEquals("test2.de", ip3.getReverseLookup());
        assertNotNull(ip4.getReverseLookup());
        assertEquals("ip-188.1.4.241.servers.jiffybox.net", ip5.getReverseLookup());

        assertEquals("public", ip1.getType());
        assertEquals("private", ip2.getType());
        assertEquals("public", ip3.getType());
        assertEquals("public", ip4.getType());
        assertEquals("public", ip5.getType());

        assertFalse(ip1.isFloating());
        assertFalse(ip2.isFloating());
        assertFalse(ip3.isFloating());
        assertFalse(ip4.isFloating());
        assertTrue(ip5.isFloating());
    }

    /**
     * Test for {@link ModuleIps#getIPSet(int)}.
     */
    @TestTemplate
    void testGetIPSet(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/ips/12345")).willReturn(aResponse
                ().withHeaders(WireMockHelper
                .headers()).withStatus(200).withBodyFile("modules/ips/testGetIPSet.json")));

        Response<IPSet> response = api.ips().getIPSet(12345);
        List<Message> messages = response.getMessages();
        IPSet result = response.getResult();

        Map<String, IP> ips = result.getIps();
        IP ip1 = ips.get("8376");
        IP ip2 = ips.get("8377");
        IP ip3 = ips.get("8463");
        IP ip4 = ips.get("8467");
        IP ip5 = ips.get("8465");

        assertTrue(messages.isEmpty());

        assertEquals(8376, ip1.getId());
        assertEquals(8377, ip2.getId());
        assertEquals(8463, ip3.getId());
        assertEquals(8467, ip4.getId());
        assertEquals(8465, ip5.getId());

        assertEquals("188.1.2.3", ip1.getIp());
        assertEquals("10.1.2.3", ip2.getIp());
        assertEquals("2a00:1234:1:234::1", ip3.getIp());
        assertEquals("2a00:1234:1:211::/56", ip4.getIp());
        assertEquals("188.1.4.241", ip5.getIp());

        assertEquals(4, ip1.getIpVersion());
        assertEquals(4, ip2.getIpVersion());
        assertEquals(6, ip3.getIpVersion());
        assertEquals(6, ip4.getIpVersion());
        assertEquals(4, ip5.getIpVersion());

        assertFalse(ip1.isSubnet());
        assertFalse(ip2.isSubnet());
        assertFalse(ip3.isSubnet());
        assertTrue(ip4.isSubnet());
        assertFalse(ip5.isSubnet());

        assertEquals("blah.blubb.de", ip1.getReverseLookup());
        assertEquals("j4192.servers.jiffybox.net", ip2.getReverseLookup());
        assertEquals("test2.de", ip3.getReverseLookup());
        assertNotNull(ip4.getReverseLookup());
        assertEquals("ip-188.1.4.241.servers.jiffybox.net", ip5.getReverseLookup());

        assertEquals("public", ip1.getType());
        assertEquals("private", ip2.getType());
        assertEquals("public", ip3.getType());
        assertEquals("public", ip4.getType());
        assertEquals("public", ip5.getType());

        assertFalse(ip1.isFloating());
        assertFalse(ip2.isFloating());
        assertFalse(ip3.isFloating());
        assertFalse(ip4.isFloating());
        assertTrue(ip5.isFloating());
    }

    /**
     * Test for {@link ModuleIps#moveIPAddress(int, int, int)}.
     */
    @TestTemplate
    void testMoveIPAddress(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(put(urlPathEqualTo("/00000000000000000000000000000000/v1.0/ips/12345/8465/move"))
                .withRequestBody(equalToJson("{\"targetid\": 4321}", false, false))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/ips/testMoveIPAddress.json")));

        Response<Boolean> response = api.ips().moveIPAddress(12345, 8465, 4321);
        List<Message> messages = response.getMessages();

        assertTrue(messages.isEmpty());
        assertTrue(response.getResult());
    }
}
