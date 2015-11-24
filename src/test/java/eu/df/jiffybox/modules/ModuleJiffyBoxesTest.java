package eu.df.jiffybox.modules;

import com.fasterxml.jackson.databind.node.ObjectNode;
import eu.df.jiffybox.Build;
import eu.df.jiffybox.JiffyBoxApi;
import eu.df.jiffybox.builders.JiffyBoxBuilder;
import eu.df.jiffybox.models.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

/**
 * This class tests the 'jiffyBoxes' module.
 */
public class ModuleJiffyBoxesTest extends ModuleTest {

    /**
     * The {@link JiffyBoxApi}.
     */
    private final JiffyBoxApi jiffyBoxApi;

    /**
     * Create a new instance using the given {@link JiffyBoxApi}.
     *
     * @param jiffyBoxApi The {@link JiffyBoxApi}.
     */
    public ModuleJiffyBoxesTest(final JiffyBoxApi jiffyBoxApi) {
        this.jiffyBoxApi = jiffyBoxApi;

        // Only run in development.
        assumeTrue(jiffyBoxApi.getUri().toString().contains("localhost"));
    }

    /**
     * Test for {@link ModuleJiffyBoxes#getJiffyBoxes()}.
     */
    @Test
    public void testGetJiffyBoxes() throws IOException {
        Response<Map<String, JiffyBox>> response = jiffyBoxApi.getModuleJiffyBoxes().getJiffyBoxes();
        List<Message> messages = response.getMessages();
        Map<String, JiffyBox> result = response.getResult();

        JiffyBox jiffyBox = result.get("12345");
        Profile profile = jiffyBox.getActiveProfile();

        JiffyBoxIps ips = jiffyBox.getIps();
        List<String> publicIps = ips.getPublic();
        List<String> privateIps = ips.getPrivate();

        Map<String, Object> metadata = jiffyBox.getMetadata();
        Plan plan = jiffyBox.getPlan();

        Map<String, Disk> disks = profile.getDisks();
        Disk disk1 = disks.get("xvda");
        Disk disk2 = disks.get("xvdb");

        assertTrue(messages.isEmpty());

        assertEquals(1234567890L, jiffyBox.getCreated());
        assertEquals("vmhost-testsys-2-2-9-1", jiffyBox.getHost());
        assertEquals(12345, jiffyBox.getId());
        assertEquals("Test", jiffyBox.getName());
        assertEquals(0L, jiffyBox.getRunningSince());
        assertEquals(Status.READY, jiffyBox.getStatus());
        assertFalse(jiffyBox.isBeingCopied());
        assertFalse(jiffyBox.isManualBackupRunning());
        assertFalse(jiffyBox.isRecoverymodeActive());
        assertFalse(jiffyBox.isRunning());

        assertEquals(1234567890L, profile.getCreated());
        assertEquals("xen-current", profile.getKernel());
        assertEquals("Standard", profile.getName());
        assertEquals("/dev/xvda", profile.getRootdisk());
        assertEquals("ro", profile.getRootdiskMode());
        assertEquals("default", profile.getRunlevel());
        assertEquals(Status.READY, profile.getStatus());

        assertEquals("188.93.14.176", publicIps.get(0));

        assertEquals("10.93.14.175", privateIps.get(0));

        assertEquals("JiffyBoxTeam", metadata.get("createdby"));

        assertEquals(6, plan.getCpus());
        assertEquals(307200, plan.getDiskSizeInMB());
        assertEquals(22, plan.getId());
        assertEquals("CloudLevel 3", plan.getName());
        assertEquals(0.07, plan.getPricePerHour(), 0.001);
        assertEquals(0.02, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(8192, plan.getRamInMB());

        assertEquals(1234567890L, disk1.getCreated());
        assertEquals("centos_5_4_32bit", disk1.getDistribution());
        assertEquals("ext3", disk1.getFilesystem());
        assertEquals("CentOS 5.4", disk1.getName());
        assertEquals(81408, disk1.getSizeInMB());
        assertEquals("READY", disk1.getStatus());

        assertEquals(1234567890L, disk2.getCreated());
        assertNull(disk2.getDistribution());
        assertEquals("swap", disk2.getFilesystem());
        assertEquals("Swap", disk2.getName());
        assertEquals(512, disk2.getSizeInMB());
        assertEquals("READY", disk2.getStatus());
    }

    /**
     * Test for {@link ModuleJiffyBoxes#getJiffyBox(int)}.
     */
    @Test
    public void testGetJiffyBox() throws IOException {
        Response<JiffyBox> response = jiffyBoxApi.getModuleJiffyBoxes().getJiffyBox(12345);
        List<Message> messages = response.getMessages();
        JiffyBox result = response.getResult();
        Profile profile = result.getActiveProfile();

        JiffyBoxIps ips = result.getIps();
        List<String> publicIps = ips.getPublic();
        List<String> privateIps = ips.getPrivate();

        Map<String, Object> metadata = result.getMetadata();
        Plan plan = result.getPlan();

        Map<String, Disk> disks = profile.getDisks();
        Disk disk1 = disks.get("xvda");
        Disk disk2 = disks.get("xvdb");

        assertTrue(messages.isEmpty());

        assertEquals(1234567890L, result.getCreated());
        assertEquals("vmhost-testsys-2-2-9-1", result.getHost());
        assertEquals(12345, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(0L, result.getRunningSince());
        assertEquals(Status.READY, result.getStatus());
        assertFalse(result.isBeingCopied());
        assertFalse(result.isManualBackupRunning());
        assertFalse(result.isRecoverymodeActive());
        assertFalse(result.isRunning());

        assertEquals(1234567890L, profile.getCreated());
        assertEquals("xen-current", profile.getKernel());
        assertEquals("Standard", profile.getName());
        assertEquals("/dev/xvda", profile.getRootdisk());
        assertEquals("ro", profile.getRootdiskMode());
        assertEquals("default", profile.getRunlevel());
        assertEquals(Status.READY, profile.getStatus());

        assertEquals("188.93.14.176", publicIps.get(0));

        assertEquals("10.93.14.175", privateIps.get(0));

        assertEquals("JiffyBoxTeam", metadata.get("createdby"));

        assertEquals(6, plan.getCpus());
        assertEquals(307200, plan.getDiskSizeInMB());
        assertEquals(22, plan.getId());
        assertEquals("CloudLevel 3", plan.getName());
        assertEquals(0.07, plan.getPricePerHour(), 0.001);
        assertEquals(0.02, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(8192, plan.getRamInMB());

        assertEquals(1234567890L, disk1.getCreated());
        assertEquals("centos_5_4_32bit", disk1.getDistribution());
        assertEquals("ext3", disk1.getFilesystem());
        assertEquals("CentOS 5.4", disk1.getName());
        assertEquals(81408, disk1.getSizeInMB());
        assertEquals("READY", disk1.getStatus());

        assertEquals(1234567890L, disk2.getCreated());
        assertNull(disk2.getDistribution());
        assertEquals("swap", disk2.getFilesystem());
        assertEquals("Swap", disk2.getName());
        assertEquals(512, disk2.getSizeInMB());
        assertEquals("READY", disk2.getStatus());
    }

    /**
     * Test for {@link ModuleJiffyBoxes#deleteJiffyBox(int)}.
     */
    @Test
    public void testDeleteJiffyBox() throws IOException {
        Response<Boolean> response = jiffyBoxApi.getModuleJiffyBoxes().deleteJiffyBox(12345);
        List<Message> messages = response.getMessages();

        assertTrue(messages.isEmpty());

        assertTrue(response.getResult());
    }

    /**
     * Test for {@link ModuleJiffyBoxes#createJiffyBox(JiffyBoxBuilder)}.
     */
    @Test
    public void testCreateJiffyBoxFromDistribution() throws IOException {
        JiffyBoxBuilder data = Build.jiffyBox("Test", 10).fromDistribution("centos_5_4_64bit");

        Response<JiffyBox> response = jiffyBoxApi.getModuleJiffyBoxes().createJiffyBox(data);
        List<Message> messages = response.getMessages();
        JiffyBox result = response.getResult();

        JiffyBoxIps ips = result.getIps();
        List<String> publicIps = ips.getPublic();
        List<String> privateIps = ips.getPrivate();

        Plan plan = result.getPlan();

        assertTrue(messages.isEmpty());

        assertEquals(1234567890L, result.getCreated());
        assertEquals("vmhost-testsys-2-2-9-2", result.getHost());
        assertEquals(12345, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(0L, result.getRunningSince());
        assertEquals(Status.CREATING, result.getStatus());
        assertFalse(result.isBeingCopied());
        assertFalse(result.isManualBackupRunning());
        assertFalse(result.isRecoverymodeActive());
        assertFalse(result.isRunning());

        assertEquals("188.93.14.211", publicIps.get(0));

        assertEquals("10.93.14.211", privateIps.get(0));

        assertEquals(3, plan.getCpus());
        assertEquals(76800, plan.getDiskSizeInMB());
        assertEquals(20, plan.getId());
        assertEquals("CloudLevel 1", plan.getName());
        assertEquals(0.02, plan.getPricePerHour(), 0.001);
        assertEquals(0.005, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(2048, plan.getRamInMB());
    }

    /**
     * Test for {@link ModuleJiffyBoxes#createJiffyBox(JiffyBoxBuilder)}.
     */
    @Test
    public void testCreateJiffyBoxFromBackup() throws IOException {
        JiffyBoxBuilder data = Build.jiffyBox("Test", 10).fromBackup("1234567890abcdef1234567890abcdef");

        Response<JiffyBox> response = jiffyBoxApi.getModuleJiffyBoxes().createJiffyBox(data);
        List<Message> messages = response.getMessages();
        JiffyBox result = response.getResult();

        JiffyBoxIps ips = result.getIps();
        List<String> publicIps = ips.getPublic();
        List<String> privateIps = ips.getPrivate();

        Plan plan = result.getPlan();

        assertTrue(messages.isEmpty());

        assertEquals(1234567890L, result.getCreated());
        assertEquals("vmhost-testsys-2-2-9-2", result.getHost());
        assertEquals(12346, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(0L, result.getRunningSince());
        assertEquals(Status.CREATING, result.getStatus());
        assertFalse(result.isBeingCopied());
        assertFalse(result.isManualBackupRunning());
        assertFalse(result.isRecoverymodeActive());
        assertFalse(result.isRunning());

        assertEquals("188.93.14.212", publicIps.get(0));

        assertEquals("10.93.14.212", privateIps.get(0));

        assertEquals(3, plan.getCpus());
        assertEquals(76800, plan.getDiskSizeInMB());
        assertEquals(20, plan.getId());
        assertEquals("CloudLevel 1", plan.getName());
        assertEquals(0.02, plan.getPricePerHour(), 0.001);
        assertEquals(0.005, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(2048, plan.getRamInMB());
    }

    /**
     * Test for {@link ModuleJiffyBoxes#createJiffyBox(JiffyBoxBuilder)}.
     */
    @Test
    public void testCreateJiffyBoxFromDistributionWithMetadata() throws IOException {
        ObjectNode metadata = Build.metadata();
        metadata.put("createdBy", "The JiffyBoxTeam");
        metadata.putArray("usedBy").add("Me").add("You").add("Everyone");
        metadata.put("freeForAll", false);

        JiffyBoxBuilder data = Build.jiffyBox("Test", 10).fromDistribution("centos_5_4_64bit").withMetadata(metadata);

        Response<JiffyBox> response = jiffyBoxApi.getModuleJiffyBoxes().createJiffyBox(data);
        List<Message> messages = response.getMessages();
        JiffyBox result = response.getResult();

        JiffyBoxIps ips = result.getIps();
        List<String> publicIps = ips.getPublic();
        List<String> privateIps = ips.getPrivate();

        Plan plan = result.getPlan();

        assertTrue(messages.isEmpty());

        assertEquals(1234567890L, result.getCreated());
        assertEquals("vmhost-testsys-2-2-9-2", result.getHost());
        assertEquals(12345, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(0L, result.getRunningSince());
        assertEquals(Status.CREATING, result.getStatus());
        assertFalse(result.isBeingCopied());
        assertFalse(result.isManualBackupRunning());
        assertFalse(result.isRecoverymodeActive());
        assertFalse(result.isRunning());

        assertEquals("188.93.14.211", publicIps.get(0));

        assertEquals("10.93.14.211", privateIps.get(0));

        assertEquals(3, plan.getCpus());
        assertEquals(76800, plan.getDiskSizeInMB());
        assertEquals(20, plan.getId());
        assertEquals("CloudLevel 1", plan.getName());
        assertEquals(0.02, plan.getPricePerHour(), 0.001);
        assertEquals(0.005, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(2048, plan.getRamInMB());
    }

    /**
     * Test for {@link ModuleJiffyBoxes#createJiffyBox(JiffyBoxBuilder)}.
     */
    @Test
    public void testCreateJiffyBoxFromDistributionWithPasswordUseSshKey() throws IOException {
        JiffyBoxBuilder data = Build.jiffyBox("Test", 10).fromDistribution("centos_5_4_64bit").withPassword
                ("Passwort123!").useSshKey(true);

        Response<JiffyBox> response = jiffyBoxApi.getModuleJiffyBoxes().createJiffyBox(data);
        List<Message> messages = response.getMessages();
        JiffyBox result = response.getResult();

        JiffyBoxIps ips = result.getIps();
        List<String> publicIps = ips.getPublic();
        List<String> privateIps = ips.getPrivate();

        Plan plan = result.getPlan();

        assertTrue(messages.isEmpty());

        assertEquals(1234567890L, result.getCreated());
        assertEquals("vmhost-testsys-2-2-9-2", result.getHost());
        assertEquals(12345, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(0L, result.getRunningSince());
        assertEquals(Status.CREATING, result.getStatus());
        assertFalse(result.isBeingCopied());
        assertFalse(result.isManualBackupRunning());
        assertFalse(result.isRecoverymodeActive());
        assertFalse(result.isRunning());

        assertEquals("188.93.14.211", publicIps.get(0));

        assertEquals("10.93.14.211", privateIps.get(0));

        assertEquals(3, plan.getCpus());
        assertEquals(76800, plan.getDiskSizeInMB());
        assertEquals(20, plan.getId());
        assertEquals("CloudLevel 1", plan.getName());
        assertEquals(0.02, plan.getPricePerHour(), 0.001);
        assertEquals(0.005, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(2048, plan.getRamInMB());
    }

    /**
     * Test for {@link ModuleJiffyBoxes#duplicateJiffyBox(int, String, int)}.
     */
    @Test
    public void testDuplicateJiffyBox() throws IOException {
        Response<JiffyBox> response = jiffyBoxApi.getModuleJiffyBoxes().duplicateJiffyBox(12345, "Test", 10);
        List<Message> messages = response.getMessages();
        JiffyBox result = response.getResult();

        JiffyBoxIps ips = result.getIps();
        List<String> publicIps = ips.getPublic();
        List<String> privateIps = ips.getPrivate();

        Plan plan = result.getPlan();

        assertTrue(messages.isEmpty());

        assertEquals(1359635993L, result.getCreated());
        assertEquals("vmhost-2-2-8-11", result.getHost());
        assertEquals(40978, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(0L, result.getRunningSince());
        assertEquals(Status.CREATING, result.getStatus());
        assertFalse(result.isBeingCopied());
        assertFalse(result.isManualBackupRunning());
        assertFalse(result.isRecoverymodeActive());
        assertFalse(result.isRunning());

        assertEquals("93.180.154.7", publicIps.get(0));

        assertEquals("10.1.16.179", privateIps.get(0));

        assertEquals(3, plan.getCpus());
        assertEquals(76800, plan.getDiskSizeInMB());
        assertEquals(20, plan.getId());
        assertEquals("CloudLevel 1", plan.getName());
        assertEquals(0.02, plan.getPricePerHour(), 0.001);
        assertEquals(0.005, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(2048, plan.getRamInMB());
    }

    /**
     * Test for {@link ModuleJiffyBoxes#duplicateJiffyBox(int, String, int, ObjectNode)}.
     */
    @Test
    public void testDuplicateJiffyBoxWithMetadata() throws IOException {
        ObjectNode metadata = Build.metadata().put("createdBy", "The JiffyBoxTeam");
        metadata.putArray("usedBy").add("Me").add("You").add("Everyone");
        metadata.put("freeForAll", false);

        Response<JiffyBox> response = jiffyBoxApi.getModuleJiffyBoxes().duplicateJiffyBox(12345, "Test", 10, metadata);
        List<Message> messages = response.getMessages();
        JiffyBox result = response.getResult();

        JiffyBoxIps ips = result.getIps();
        List<String> publicIps = ips.getPublic();
        List<String> privateIps = ips.getPrivate();

        Plan plan = result.getPlan();

        assertTrue(messages.isEmpty());

        assertEquals(1359635993L, result.getCreated());
        assertEquals("vmhost-2-2-8-11", result.getHost());
        assertEquals(40978, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(0L, result.getRunningSince());
        assertEquals(Status.CREATING, result.getStatus());
        assertFalse(result.isBeingCopied());
        assertFalse(result.isManualBackupRunning());
        assertFalse(result.isRecoverymodeActive());
        assertFalse(result.isRunning());

        assertEquals("93.180.154.7", publicIps.get(0));

        assertEquals("10.1.16.179", privateIps.get(0));

        assertEquals(3, plan.getCpus());
        assertEquals(76800, plan.getDiskSizeInMB());
        assertEquals(20, plan.getId());
        assertEquals("CloudLevel 1", plan.getName());
        assertEquals(0.02, plan.getPricePerHour(), 0.001);
        assertEquals(0.005, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(2048, plan.getRamInMB());
    }

    /**
     * Test for {@link ModuleJiffyBoxes#startJiffyBox(int)}.
     */
    @Test
    public void testStartJiffyBox() throws IOException {
        Response<JiffyBox> response = jiffyBoxApi.getModuleJiffyBoxes().startJiffyBox(12345);
        List<Message> messages = response.getMessages();
        JiffyBox result = response.getResult();

        JiffyBoxIps ips = result.getIps();
        List<String> publicIps = ips.getPublic();
        List<String> privateIps = ips.getPrivate();

        Plan plan = result.getPlan();

        assertTrue(messages.isEmpty());

        assertEquals(1234567890L, result.getCreated());
        assertEquals("vmhost-testsys-2-2-9-2", result.getHost());
        assertEquals(12345, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(0L, result.getRunningSince());
        assertEquals(Status.UPDATING, result.getStatus());
        assertFalse(result.isBeingCopied());
        assertFalse(result.isManualBackupRunning());
        assertFalse(result.isRecoverymodeActive());
        assertFalse(result.isRunning());

        assertEquals("188.93.14.176", publicIps.get(0));

        assertEquals("10.93.14.175", privateIps.get(0));

        assertEquals(3, plan.getCpus());
        assertEquals(25600, plan.getDiskSizeInMB());
        assertEquals(28, plan.getId());
        assertEquals("CloudLevel 1 SSD", plan.getName());
        assertEquals(0.025, plan.getPricePerHour(), 0.001);
        assertEquals(0.005, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(2048, plan.getRamInMB());
    }

    /**
     * Test for {@link ModuleJiffyBoxes#startJiffyBox(int, ObjectNode)}.
     */
    @Test
    public void testStartJiffyBoxWithMetadata() throws IOException {
        ObjectNode metadata = Build.metadata().put("createdBy", "Tester");

        Response<JiffyBox> response = jiffyBoxApi.getModuleJiffyBoxes().startJiffyBox(12345, metadata);
        List<Message> messages = response.getMessages();
        JiffyBox result = response.getResult();

        JiffyBoxIps ips = result.getIps();
        List<String> publicIps = ips.getPublic();
        List<String> privateIps = ips.getPrivate();

        Plan plan = result.getPlan();

        assertTrue(messages.isEmpty());

        assertEquals(1234567890L, result.getCreated());
        assertEquals("vmhost-testsys-2-2-9-2", result.getHost());
        assertEquals(12345, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(0L, result.getRunningSince());
        assertEquals(Status.UPDATING, result.getStatus());
        assertFalse(result.isBeingCopied());
        assertFalse(result.isManualBackupRunning());
        assertFalse(result.isRecoverymodeActive());
        assertFalse(result.isRunning());

        assertEquals("188.93.14.176", publicIps.get(0));

        assertEquals("10.93.14.175", privateIps.get(0));

        assertEquals(3, plan.getCpus());
        assertEquals(76800, plan.getDiskSizeInMB());
        assertEquals(20, plan.getId());
        assertEquals("CloudLevel 1", plan.getName());
        assertEquals(0.02, plan.getPricePerHour(), 0.001);
        assertEquals(0.005, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(2048, plan.getRamInMB());
    }

    /**
     * Test for {@link ModuleJiffyBoxes#changePlanJiffyBox(int, int)}.
     */
    @Test
    public void testChangePlanJiffyBox() throws IOException {
        Response<JiffyBox> response = jiffyBoxApi.getModuleJiffyBoxes().changePlanJiffyBox(12345, 20);
        List<Message> messages = response.getMessages();
        JiffyBox result = response.getResult();

        JiffyBoxIps ips = result.getIps();
        List<String> publicIps = ips.getPublic();
        List<String> privateIps = ips.getPrivate();

        Plan plan = result.getPlan();

        assertTrue(messages.isEmpty());

        assertEquals(1234567890L, result.getCreated());
        assertEquals("vmhost-testsys-2-2-9-2", result.getHost());
        assertEquals(12345, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(0L, result.getRunningSince());
        assertEquals(Status.CHANGING_PLAN, result.getStatus());
        assertFalse(result.isBeingCopied());
        assertFalse(result.isManualBackupRunning());
        assertFalse(result.isRecoverymodeActive());
        assertFalse(result.isRunning());

        assertEquals("188.93.14.176", publicIps.get(0));

        assertEquals("10.93.14.175", privateIps.get(0));

        assertEquals(3, plan.getCpus());
        assertEquals(25600, plan.getDiskSizeInMB());
        assertEquals(28, plan.getId());
        assertEquals("CloudLevel 1 SSD", plan.getName());
        assertEquals(0.025, plan.getPricePerHour(), 0.001);
        assertEquals(0.005, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(2048, plan.getRamInMB());
    }

    /**
     * Test for {@link ModuleJiffyBoxes#changePlanJiffyBox(int, String)}.
     */
    @Test
    public void testChangePlanJiffyBox1() throws IOException {
        Response<JiffyBox> response = jiffyBoxApi.getModuleJiffyBoxes().changePlanJiffyBox(12345, "CloudLevel 1 SSD");
        List<Message> messages = response.getMessages();
        JiffyBox result = response.getResult();

        JiffyBoxIps ips = result.getIps();
        List<String> publicIps = ips.getPublic();
        List<String> privateIps = ips.getPrivate();

        Plan plan = result.getPlan();

        assertTrue(messages.isEmpty());

        assertEquals(1234567890L, result.getCreated());
        assertEquals("vmhost-testsys-2-2-9-2", result.getHost());
        assertEquals(12345, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(0L, result.getRunningSince());
        assertEquals(Status.CHANGING_PLAN, result.getStatus());
        assertFalse(result.isBeingCopied());
        assertFalse(result.isManualBackupRunning());
        assertFalse(result.isRecoverymodeActive());
        assertFalse(result.isRunning());

        assertEquals("188.93.14.176", publicIps.get(0));

        assertEquals("10.93.14.175", privateIps.get(0));

        assertEquals(3, plan.getCpus());
        assertEquals(25600, plan.getDiskSizeInMB());
        assertEquals(28, plan.getId());
        assertEquals("CloudLevel 1 SSD", plan.getName());
        assertEquals(0.025, plan.getPricePerHour(), 0.001);
        assertEquals(0.005, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(2048, plan.getRamInMB());
    }
}
