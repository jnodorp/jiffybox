package eu.df.jiffybox.modules;

import eu.df.jiffybox.JiffyBoxApi;
import eu.df.jiffybox.models.Distribution;
import eu.df.jiffybox.models.Message;
import eu.df.jiffybox.models.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the 'distributions' module.
 */
public class ModuleDistributionsTest extends ModuleTest {

    /**
     * The {@link JiffyBoxApi}.
     */
    private final JiffyBoxApi jiffyBoxApi;

    /**
     * Create a new instance using the given {@link JiffyBoxApi}.
     *
     * @param jiffyBoxApi The {@link JiffyBoxApi}.
     */
    public ModuleDistributionsTest(final JiffyBoxApi jiffyBoxApi) {
        this.jiffyBoxApi = jiffyBoxApi;
    }

    /**
     * Test for {@link ModuleDistributions#getDistributions()}.
     */
    @Test
    public void testGetDistributions() throws IOException {
        Response<List<Distribution>> response = jiffyBoxApi.distributions().getDistributions();
        List<Message> messages = response.getMessages();
        List<Distribution> distributions = response.getResult();
        assertEquals(8, distributions.size());

        Distribution distribution1 = distributions.get(0);
        Distribution distribution2 = distributions.get(1);
        Distribution distribution3 = distributions.get(2);
        Distribution distribution4 = distributions.get(3);
        Distribution distribution5 = distributions.get(4);
        Distribution distribution6 = distributions.get(5);
        Distribution distribution7 = distributions.get(6);
        Distribution distribution8 = distributions.get(7);

        assertTrue(messages.isEmpty());

        assertEquals("centos_6_0_64bit", distribution1.getKey());
        assertEquals(2048, distribution1.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution1.getDefaultKernel());
        assertEquals("CentOS 6 64-Bit", distribution1.getName());
        assertEquals("ro", distribution1.getRootdiskMode());

        assertEquals("centos_7_1_64bit", distribution2.getKey());
        assertEquals(2560, distribution2.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution2.getDefaultKernel());
        assertEquals("CentOS 7 64-Bit", distribution2.getName());
        assertEquals("ro", distribution2.getRootdiskMode());

        assertEquals("debian_jessie_64bit", distribution3.getKey());
        assertEquals(2048, distribution3.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution3.getDefaultKernel());
        assertEquals("Debian Jessie (8) 64-Bit", distribution3.getName());
        assertEquals("ro", distribution3.getRootdiskMode());

        assertEquals("fedora_24_64bit", distribution4.getKey());
        assertEquals(2048, distribution4.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution4.getDefaultKernel());
        assertEquals("Fedora 24 64-Bit", distribution4.getName());
        assertEquals("ro", distribution4.getRootdiskMode());

        assertEquals("opensuse_13_64bit", distribution5.getKey());
        assertEquals(2048, distribution5.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution5.getDefaultKernel());
        assertEquals("openSUSE 13.2 64-bit", distribution5.getName());
        assertEquals("ro", distribution5.getRootdiskMode());

        assertEquals("opensuse_42_64bit", distribution6.getKey());
        assertEquals(2048, distribution6.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution6.getDefaultKernel());
        assertEquals("openSUSE 42.1 64-Bit", distribution6.getName());
        assertEquals("ro", distribution6.getRootdiskMode());

        assertEquals("ubuntu_14_4_lts_64bit", distribution7.getKey());
        assertEquals(2048, distribution7.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution7.getDefaultKernel());
        assertEquals("Ubuntu 14.04 LTS 64-Bit", distribution7.getName());
        assertEquals("ro", distribution7.getRootdiskMode());

        assertEquals("ubuntu_16_4_lts_64bit", distribution8.getKey());
        assertEquals(2048, distribution8.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution8.getDefaultKernel());
        assertEquals("Ubuntu 16.04 LTS 64-Bit", distribution8.getName());
        assertEquals("ro", distribution8.getRootdiskMode());
    }

    /**
     * Test for {@link ModuleDistributions#getDistribution(String)}.
     */
    @Test
    public void testGetDistribution() throws IOException {
        Response<Distribution> response = jiffyBoxApi.distributions().getDistribution("centos_5_4_64bit");
        List<Message> messages = response.getMessages();
        Distribution distribution = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(1024, distribution.getMinDiskSizeMB());
        assertEquals("xen-current-x86_64", distribution.getDefaultKernel());
        assertEquals("CentOS 5.4 64-Bit", distribution.getName());
        assertEquals("ro", distribution.getRootdiskMode());
    }
}
