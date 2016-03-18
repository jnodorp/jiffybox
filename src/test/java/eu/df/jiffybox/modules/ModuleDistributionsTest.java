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
        assertEquals(14, distributions.size());

        Distribution distribution1 = distributions.get(0);
        Distribution distribution2 = distributions.get(1);
        Distribution distribution3 = distributions.get(2);
        Distribution distribution4 = distributions.get(3);
        Distribution distribution5 = distributions.get(4);
        Distribution distribution6 = distributions.get(5);
        Distribution distribution7 = distributions.get(6);
        Distribution distribution8 = distributions.get(7);
        Distribution distribution9 = distributions.get(8);
        Distribution distribution10 = distributions.get(9);
        Distribution distribution11 = distributions.get(10);
        Distribution distribution12 = distributions.get(11);
        Distribution distribution13 = distributions.get(12);
        Distribution distribution14 = distributions.get(13);

        assertTrue(messages.isEmpty());

        assertEquals("centos_6_0_32bit", distribution1.getKey());
        assertEquals(2048, distribution1.getMinDiskSizeMB());
        assertEquals("xen-pvops", distribution1.getDefaultKernel());
        assertEquals("CentOS 6", distribution1.getName());
        assertEquals("ro", distribution1.getRootdiskMode());

        assertEquals("centos_6_0_64bit", distribution2.getKey());
        assertEquals(2048, distribution2.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution2.getDefaultKernel());
        assertEquals("CentOS 6 64-Bit", distribution2.getName());
        assertEquals("ro", distribution2.getRootdiskMode());

        assertEquals("centos_7_1_64bit", distribution3.getKey());
        assertEquals(2500, distribution3.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution3.getDefaultKernel());
        assertEquals("CentOS 7 64-Bit", distribution3.getName());
        assertEquals("ro", distribution3.getRootdiskMode());

        assertEquals("debian_jessie_32bit", distribution4.getKey());
        assertEquals(2048, distribution4.getMinDiskSizeMB());
        assertEquals("xen-pvops", distribution4.getDefaultKernel());
        assertEquals("Debian Jessie (8)", distribution4.getName());
        assertEquals("ro", distribution4.getRootdiskMode());

        assertEquals("debian_jessie_64bit", distribution5.getKey());
        assertEquals(2048, distribution5.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution5.getDefaultKernel());
        assertEquals("Debian Jessie (8) 64-Bit", distribution5.getName());
        assertEquals("ro", distribution5.getRootdiskMode());

        assertEquals("fedora_23_32bit", distribution6.getKey());
        assertEquals(2048, distribution6.getMinDiskSizeMB());
        assertEquals("xen-pvops", distribution6.getDefaultKernel());
        assertEquals("Fedora 23", distribution6.getName());
        assertEquals("ro", distribution6.getRootdiskMode());

        assertEquals("fedora_23_64bit", distribution7.getKey());
        assertEquals(2048, distribution7.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution7.getDefaultKernel());
        assertEquals("Fedora 23 64-Bit", distribution7.getName());
        assertEquals("ro", distribution7.getRootdiskMode());

        assertEquals("opensuse_13_32bit", distribution8.getKey());
        assertEquals(2048, distribution8.getMinDiskSizeMB());
        assertEquals("xen-pvops", distribution8.getDefaultKernel());
        assertEquals("openSUSE 13.2", distribution8.getName());
        assertEquals("ro", distribution8.getRootdiskMode());

        assertEquals("opensuse_13_64bit", distribution9.getKey());
        assertEquals(2048, distribution9.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution9.getDefaultKernel());
        assertEquals("openSUSE 13.2 64-bit", distribution9.getName());
        assertEquals("ro", distribution9.getRootdiskMode());

        assertEquals("opensuse_42_64bit", distribution10.getKey());
        assertEquals(2048, distribution10.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution10.getDefaultKernel());
        assertEquals("openSUSE 42.1 64-Bit", distribution10.getName());
        assertEquals("ro", distribution10.getRootdiskMode());

        assertEquals("ubuntu_12_4_lts_32bit", distribution11.getKey());
        assertEquals(2048, distribution11.getMinDiskSizeMB());
        assertEquals("xen-pvops", distribution11.getDefaultKernel());
        assertEquals("Ubuntu 12.04 LTS", distribution11.getName());
        assertEquals("ro", distribution11.getRootdiskMode());

        assertEquals("ubuntu_12_4_lts_64bit", distribution12.getKey());
        assertEquals(2048, distribution12.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution12.getDefaultKernel());
        assertEquals("Ubuntu 12.04 LTS 64-Bit", distribution12.getName());
        assertEquals("ro", distribution12.getRootdiskMode());

        assertEquals("ubuntu_14_4_lts_32bit", distribution13.getKey());
        assertEquals(2048, distribution13.getMinDiskSizeMB());
        assertEquals("xen-pvops", distribution13.getDefaultKernel());
        assertEquals("Ubuntu 14.04 LTS", distribution13.getName());
        assertEquals("ro", distribution13.getRootdiskMode());

        assertEquals("ubuntu_14_4_lts_64bit", distribution14.getKey());
        assertEquals(2048, distribution14.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution14.getDefaultKernel());
        assertEquals("Ubuntu 14.04 LTS 64-Bit", distribution14.getName());
        assertEquals("ro", distribution14.getRootdiskMode());
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
