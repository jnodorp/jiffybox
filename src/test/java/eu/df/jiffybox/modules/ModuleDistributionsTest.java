package eu.df.jiffybox.modules;

import eu.df.jiffybox.JiffyBoxApi;
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
        Response<Map<String, Distribution>> response = jiffyBoxApi.getModuleDistributions().getDistributions();
        List<Message> messages = response.getMessages();
        Map<String, Distribution> distributions = response.getResult();
        assertEquals(13, distributions.size());

        Distribution distribution1 = distributions.get("centos_6_0_32bit");
        Distribution distribution2 = distributions.get("centos_6_0_64bit");
        Distribution distribution3 = distributions.get("centos_7_1_64bit");
        Distribution distribution4 = distributions.get("debian_jessie_32bit");
        Distribution distribution5 = distributions.get("debian_jessie_64bit");
        Distribution distribution6 = distributions.get("fedora_22_32bit");
        Distribution distribution7 = distributions.get("fedora_22_64bit");
        Distribution distribution8 = distributions.get("opensuse_13_32bit");
        Distribution distribution9 = distributions.get("opensuse_13_64bit");
        Distribution distribution10 = distributions.get("ubuntu_12_4_lts_32bit");
        Distribution distribution11 = distributions.get("ubuntu_12_4_lts_64bit");
        Distribution distribution12 = distributions.get("ubuntu_14_4_lts_32bit");
        Distribution distribution13 = distributions.get("ubuntu_14_4_lts_64bit");

        assertTrue(messages.isEmpty());

        assertEquals(2048, distribution1.getMinDiskSizeMB());
        assertEquals("xen-pvops", distribution1.getDefaultKernel());
        assertEquals("CentOS 6", distribution1.getName());
        assertEquals("ro", distribution1.getRootdiskMode());

        assertEquals(2048, distribution2.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution2.getDefaultKernel());
        assertEquals("CentOS 6 64-Bit", distribution2.getName());
        assertEquals("ro", distribution2.getRootdiskMode());

        assertEquals(2500, distribution3.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution3.getDefaultKernel());
        assertEquals("CentOS 7 64-Bit", distribution3.getName());
        assertEquals("ro", distribution3.getRootdiskMode());

        assertEquals(2048, distribution4.getMinDiskSizeMB());
        assertEquals("xen-pvops", distribution4.getDefaultKernel());
        assertEquals("Debian Jessie (8)", distribution4.getName());
        assertEquals("ro", distribution4.getRootdiskMode());

        assertEquals(2048, distribution5.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution5.getDefaultKernel());
        assertEquals("Debian Jessie (8) 64-Bit", distribution5.getName());
        assertEquals("ro", distribution5.getRootdiskMode());

        assertEquals(2048, distribution6.getMinDiskSizeMB());
        assertEquals("xen-pvops", distribution6.getDefaultKernel());
        assertEquals("Fedora 22", distribution6.getName());
        assertEquals("ro", distribution6.getRootdiskMode());

        assertEquals(2048, distribution7.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution7.getDefaultKernel());
        assertEquals("Fedora 22 64-Bit", distribution7.getName());
        assertEquals("ro", distribution7.getRootdiskMode());

        assertEquals(2048, distribution8.getMinDiskSizeMB());
        assertEquals("xen-pvops", distribution8.getDefaultKernel());
        assertEquals("openSUSE 13.2", distribution8.getName());
        assertEquals("ro", distribution8.getRootdiskMode());

        assertEquals(2048, distribution9.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution9.getDefaultKernel());
        assertEquals("openSUSE 13.2 64-bit", distribution9.getName());
        assertEquals("ro", distribution9.getRootdiskMode());

        assertEquals(2048, distribution10.getMinDiskSizeMB());
        assertEquals("xen-pvops", distribution10.getDefaultKernel());
        assertEquals("Ubuntu 12.04 LTS", distribution10.getName());
        assertEquals("ro", distribution10.getRootdiskMode());

        assertEquals(2048, distribution11.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution11.getDefaultKernel());
        assertEquals("Ubuntu 12.04 LTS 64-Bit", distribution11.getName());
        assertEquals("ro", distribution11.getRootdiskMode());

        assertEquals(2048, distribution12.getMinDiskSizeMB());
        assertEquals("xen-pvops", distribution12.getDefaultKernel());
        assertEquals("Ubuntu 14.04 LTS", distribution12.getName());
        assertEquals("ro", distribution12.getRootdiskMode());

        assertEquals(2048, distribution13.getMinDiskSizeMB());
        assertEquals("xen-pvops-x86_64", distribution13.getDefaultKernel());
        assertEquals("Ubuntu 14.04 LTS 64-Bit", distribution13.getName());
        assertEquals("ro", distribution13.getRootdiskMode());
    }

    /**
     * Test for {@link ModuleDistributions#getDistribution(String)}.
     */
    @Test
    public void testGetDistribution() throws IOException {
        Response<Distribution> response = jiffyBoxApi.getModuleDistributions().getDistribution("centos_5_4_64bit");
        List<Message> messages = response.getMessages();
        Distribution distribution = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(1024, distribution.getMinDiskSizeMB());
        assertEquals("xen-current-x86_64", distribution.getDefaultKernel());
        assertEquals("CentOS 5.4 64-Bit", distribution.getName());
        assertEquals("ro", distribution.getRootdiskMode());
    }
}
