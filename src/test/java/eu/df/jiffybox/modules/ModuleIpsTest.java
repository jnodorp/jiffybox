package eu.df.jiffybox.modules;

import eu.df.jiffybox.JiffyBoxApi;
import eu.df.jiffybox.models.IP;
import eu.df.jiffybox.models.IPSet;
import eu.df.jiffybox.models.Message;
import eu.df.jiffybox.models.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

/**
 * This class tests the 'ips' module.
 */
public class ModuleIpsTest extends ModuleTest {

    /**
     * The {@link JiffyBoxApi}.
     */
    private final JiffyBoxApi jiffyBoxApi;

    /**
     * Create a new instance using the given {@link JiffyBoxApi}.
     *
     * @param jiffyBoxApi The {@link JiffyBoxApi}.
     */
    public ModuleIpsTest(final JiffyBoxApi jiffyBoxApi) {
        this.jiffyBoxApi = jiffyBoxApi;

        // Only run in development.
        assumeTrue(jiffyBoxApi.getUri().toString().contains("localhost"));
    }

    /**
     * Test for {@link ModuleIps#getIPSets()}.
     */
    @Test
    public void testGetIPSets() throws IOException {
        Response<Map<String, IPSet>> response = jiffyBoxApi.getModuleIps().getIPSets();
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
    @Test
    public void testGetIPSet() throws IOException {
        Response<IPSet> response = jiffyBoxApi.getModuleIps().getIPSet(12345);
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
    @Test
    public void testMoveIPAddress() throws IOException {
        Response<Boolean> response = jiffyBoxApi.getModuleIps().moveIPAddress(12345, 8465, 4321);
        List<Message> messages = response.getMessages();

        assertTrue(messages.isEmpty());
        assertTrue(response.getResult());
    }
}
