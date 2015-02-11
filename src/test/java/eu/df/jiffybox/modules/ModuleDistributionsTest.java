package eu.df.jiffybox.modules;

import eu.df.jiffybox.models.Distribution;
import eu.df.jiffybox.models.Message;
import eu.df.jiffybox.models.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the 'distributions' module.
 */
public class ModuleDistributionsTest extends ModuleTest {

    /**
     * Test for {@link ModuleDistributions#getDistributions()}.
     */
    @Test
    public void testGetDistributions() throws IOException {
        Response<Map<String, Distribution>> response = distributions
                .getDistributions();
        List<Message> messages = response.getMessages();
        Map<String, Distribution> distributions = response.getResult();
        Distribution distribution1 = distributions.get("centos_5_4_32bit");
        Distribution distribution2 = distributions.get("centos_5_4_64bit");

        assertTrue(messages.isEmpty());

        assertEquals(1024, distribution1.getMinDiskSizeMB());
        assertEquals("xen-current", distribution1.getDefaultKernel());
        assertEquals("CentOS5.4", distribution1.getName());
        assertEquals("ro", distribution1.getRootdiskMode());

        assertEquals(1024, distribution2.getMinDiskSizeMB());
        assertEquals("xen-current-x86_64", distribution2.getDefaultKernel());
        assertEquals("CentOS5.464-Bit", distribution2.getName());
        assertEquals("ro", distribution2.getRootdiskMode());
    }

    /**
     * Test for {@link ModuleDistributions#getDistribution(String)}.
     */
    @Test
    public void testGetDistribution() throws IOException {
        Response<Distribution> response = distributions.getDistribution
                ("centos_5_4_64bit");
        List<Message> messages = response.getMessages();
        Distribution distribution = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(1024, distribution.getMinDiskSizeMB());
        assertEquals("xen-current-x86_64", distribution.getDefaultKernel());
        assertEquals("CentOS5.464-Bit", distribution.getName());
        assertEquals("ro", distribution.getRootdiskMode());
    }
}
