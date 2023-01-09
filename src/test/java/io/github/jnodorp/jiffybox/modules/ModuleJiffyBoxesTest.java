package io.github.jnodorp.jiffybox.modules;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.jnodorp.jiffybox.Build;
import io.github.jnodorp.jiffybox.JiffyBoxApi;
import io.github.jnodorp.jiffybox.WireMockHelper;
import io.github.jnodorp.jiffybox.builders.JiffyBoxBuilder;
import io.github.jnodorp.jiffybox.models.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the 'jiffyBoxes' module.
 */
@ExtendWith(ModuleTestExtension.class)
@ModuleTestExtension.ModuleTest(runAgainstServer = false)
class ModuleJiffyBoxesTest {

    /**
     * Test for {@link ModuleJiffyBoxes#getJiffyBoxes()}.
     */
    @TestTemplate
    void testGetJiffyBoxes(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes")).willReturn(aResponse()
                .withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/jiffyBoxes/testGetJiffyBoxes.json")));

        Response<Map<String, JiffyBox>> response = api.jiffyBoxes().getJiffyBoxes();
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
    @TestTemplate
    void testGetJiffyBox(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes/12345")).willReturn
                (aResponse()
                .withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/jiffyBoxes/testGetJiffyBox.json")));

        Response<JiffyBox> response = api.jiffyBoxes().getJiffyBox(12345);
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
        assertEquals(1234567890L, result.getRunningSince());
        assertEquals(Status.READY, result.getStatus());
        assertFalse(result.isBeingCopied());
        assertFalse(result.isManualBackupRunning());
        assertFalse(result.isRecoverymodeActive());
        assertTrue(result.isRunning());

        assertEquals(1234567890L, profile.getCreated());
        assertEquals("xen-pvops-x86_64", profile.getKernel());
        assertEquals("Standard", profile.getName());
        assertEquals("/dev/xvda", profile.getRootdisk());
        assertEquals("ro", profile.getRootdiskMode());
        assertEquals("default", profile.getRunlevel());
        assertEquals(Status.READY, profile.getStatus());

        assertEquals("188.93.14.176", publicIps.get(0));

        assertEquals("10.93.14.175", privateIps.get(0));

        assertEquals("JiffyBoxTeam", metadata.get("createdby"));

        assertEquals(3, plan.getCpus());
        assertEquals(76800, plan.getDiskSizeInMB());
        assertEquals(20, plan.getId());
        assertEquals("CloudLevel 1 (Generation 3)", plan.getName());
        assertEquals(0.02, plan.getPricePerHour(), 0.001);
        assertEquals(0.005, plan.getPricePerHourFrozen(), 0.0001);
        assertEquals(2048, plan.getRamInMB());

        assertEquals(1234567890L, disk1.getCreated());
        assertEquals("debian_jessie_64bit", disk1.getDistribution());
        assertEquals("ext4", disk1.getFilesystem());
        assertEquals("Debian Jessie (8) 64-Bit", disk1.getName());
        assertEquals(76288, disk1.getSizeInMB());
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
    @TestTemplate
    void testDeleteJiffyBox(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(delete(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes/12345")).willReturn
                (aResponse()
                .withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/jiffyBoxes/testDeleteJiffyBox.json")));

        Response<Boolean> response = api.jiffyBoxes().deleteJiffyBox(12345);
        List<Message> messages = response.getMessages();

        assertTrue(messages.isEmpty());

        assertTrue(response.getResult());
    }

    /**
     * Test for {@link ModuleJiffyBoxes#createJiffyBox(JiffyBoxBuilder)}.
     */
    @TestTemplate
    void testCreateJiffyBoxFromDistribution(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes")).withRequestBody
                (equalToJson("{\"name\": \"Test\", \"planid\": 10, " + "\"distribution\":\"centos_5_4_64bit\" }"))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/jiffyBoxes/testCreateJiffyBoxFromDistribution.json")));

        JiffyBoxBuilder data = Build.jiffyBox("Test", 10).fromDistribution("centos_5_4_64bit");

        Response<JiffyBox> response = api.jiffyBoxes().createJiffyBox(data);
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
    @TestTemplate
    void testCreateJiffyBoxFromBackup(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes")).withRequestBody
                (equalToJson("{\"name\": \"Test\", \"planid\": 10, " +
                        "\"backupid\":\"1234567890abcdef1234567890abcdef\" }"))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/jiffyBoxes/testCreateJiffyBoxFromBackup.json")));

        JiffyBoxBuilder data = Build.jiffyBox("Test", 10).fromBackup("1234567890abcdef1234567890abcdef");

        Response<JiffyBox> response = api.jiffyBoxes().createJiffyBox(data);
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
    @TestTemplate
    void testCreateJiffyBoxFromDistributionWithMetadata(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes")).withRequestBody
                (equalToJson("{\"name\": \"Test\", \"planid\": 10, " + "\"distribution\":\"centos_5_4_64bit\", " +
                        "\"metadata\":{\"createdBy\": \"The JiffyBoxTeam\", " + "\"usedBy\": [\"Me\", \"You\", " +
                        "\"Everyone\"], \"freeForAll\":false}}"))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/jiffyBoxes/testCreateJiffyBoxFromDistributionWithMetadata.json")));

        ObjectNode metadata = Build.metadata();
        metadata.put("createdBy", "The JiffyBoxTeam");
        metadata.putArray("usedBy").add("Me").add("You").add("Everyone");
        metadata.put("freeForAll", false);

        JiffyBoxBuilder data = Build.jiffyBox("Test", 10).fromDistribution("centos_5_4_64bit").withMetadata(metadata);

        Response<JiffyBox> response = api.jiffyBoxes().createJiffyBox(data);
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
    @TestTemplate
    void testCreateJiffyBoxFromDistributionWithPasswordUseSshKey(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes")).withRequestBody
                (equalToJson("{\"name\": \"Test\", \"planid\": 10, " + "\"distribution\":\"centos_5_4_64bit\", " +
                        "\"password\":\"Passwort123!\", \"use_sshkey\": true}"))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/jiffyBoxes/testCreateJiffyBoxFromDistributionWithPasswordUseSshKey" +
                                ".json")));

        JiffyBoxBuilder data = Build.jiffyBox("Test", 10)
                .fromDistribution("centos_5_4_64bit")
                .withPassword("Passwort123!")
                .useSshKey(true);

        Response<JiffyBox> response = api.jiffyBoxes().createJiffyBox(data);
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
    @TestTemplate
    void testDuplicateJiffyBox1(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes/12345"))
                .withRequestBody(equalToJson("{\"name\": \"Test\", \"planid\": 10}"))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/jiffyBoxes/testDuplicateJiffyBox1.json")));

        Response<JiffyBox> response = api.jiffyBoxes().duplicateJiffyBox(12345, "Test", 10);
        testDuplicateJiffyBoxResponse(response);
    }

    /**
     * Test for {@link ModuleJiffyBoxes#duplicateJiffyBox(int, String, String)}.
     */
    @TestTemplate
    void testDuplicateJiffyBox2(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes/12345"))
                .withRequestBody(equalToJson("{\"name\": \"Test\", \"planid\": \"CloudLevel 1\"}"))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/jiffyBoxes/testDuplicateJiffyBox2.json")));

        Response<JiffyBox> response = api.jiffyBoxes().duplicateJiffyBox(12345, "Test", "CloudLevel 1");
        testDuplicateJiffyBoxResponse(response);
    }

    /**
     * Test for {@link ModuleJiffyBoxes#duplicateJiffyBox(int, String, int, ObjectNode)}.
     */
    @TestTemplate
    void testDuplicateJiffyBoxWithMetadata1(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes/12345"))
                .withRequestBody(equalToJson("{\"name\": \"Test\", \"planid\": 10, \"metadata\":{\"createdBy\": " +
                        "\"The" + " " + "JiffyBoxTeam\", \"usedBy\": [\"Me\", \"You\", \"Everyone\"], " +
                        "\"freeForAll\":false}}"))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/jiffyBoxes/testDuplicateJiffyBoxWithMetadata1.json")));

        ObjectNode metadata = Build.metadata().put("createdBy", "The JiffyBoxTeam");
        metadata.putArray("usedBy").add("Me").add("You").add("Everyone");
        metadata.put("freeForAll", false);

        Response<JiffyBox> response = api.jiffyBoxes().duplicateJiffyBox(12345, "Test", 10, metadata);
        testDuplicateJiffyBoxResponse(response);
    }

    /**
     * Test for {@link ModuleJiffyBoxes#duplicateJiffyBox(int, String, String, ObjectNode)}.
     */
    @TestTemplate
    void testDuplicateJiffyBoxWithMetadata2(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes/12345"))
                .withRequestBody(equalToJson("{\"name\": \"Test\", \"planid\": \"CloudLevel 1\", " +
                        "\"metadata\":{\"createdBy\": " + "\"The JiffyBoxTeam\", \"usedBy\": [\"Me\", \"You\", " +
                        "\"Everyone\"], " + "\"freeForAll\":false}}"))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/jiffyBoxes/testDuplicateJiffyBoxWithMetadata2.json")));

        ObjectNode metadata = Build.metadata().put("createdBy", "The JiffyBoxTeam");
        metadata.putArray("usedBy").add("Me").add("You").add("Everyone");
        metadata.put("freeForAll", false);

        Response<JiffyBox> response = api.jiffyBoxes().duplicateJiffyBox(12345, "Test", "CloudLevel 1", metadata);
        testDuplicateJiffyBoxResponse(response);
    }

    /**
     * Test for {@link ModuleJiffyBoxes#startJiffyBox(int)}.
     */
    @TestTemplate
    void testStartJiffyBox(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(put(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes/12345"))
                .withRequestBody(equalToJson("{\"status\": \"START\"}"))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/jiffyBoxes/testStartJiffyBox.json")));

        Response<JiffyBox> response = api.jiffyBoxes().startJiffyBox(12345);
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
    @TestTemplate
    void testStartJiffyBoxWithMetadata(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(put(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes/12345"))
                .withRequestBody(equalToJson("{\"status\": \"START\", \"metadata\":{\"createdBy\": \"Tester\"}}"))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/jiffyBoxes/testStartJiffyBoxWithMetadata.json")));

        ObjectNode metadata = Build.metadata().put("createdBy", "Tester");

        Response<JiffyBox> response = api.jiffyBoxes().startJiffyBox(12345, metadata);
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
    @TestTemplate
    void testChangePlanJiffyBox(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(put(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes/12345"))
                .withRequestBody(equalToJson("{\"status\": \"PLAN\", \"planid\":20}"))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/jiffyBoxes/testChangePlanJiffyBox.json")));

        Response<JiffyBox> response = api.jiffyBoxes().changePlanJiffyBox(12345, 20);
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
    @TestTemplate
    void testChangePlanJiffyBox1(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(put(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes/12345"))
                .withRequestBody(equalToJson("{\"status\": \"PLAN\", \"planid\": \"CloudLevel 1 SSD\"}"))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/jiffyBoxes/testChangePlanJiffyBox1.json")));

        Response<JiffyBox> response = api.jiffyBoxes().changePlanJiffyBox(12345, "CloudLevel 1 SSD");
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
     * Check the response for {@link #testDuplicateJiffyBox1(WireMockServer, JiffyBoxApi)},
     * {@link #testDuplicateJiffyBox2(WireMockServer, JiffyBoxApi)},
     * {@link #testDuplicateJiffyBoxWithMetadata1(WireMockServer, JiffyBoxApi)} ()} and
     * {@link #testDuplicateJiffyBoxWithMetadata2(WireMockServer, JiffyBoxApi)}.
     *
     * @param response The response.
     */
    @Disabled
    private void testDuplicateJiffyBoxResponse(Response<JiffyBox> response) {
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
     * Test for {@link ModuleJiffyBoxes#changePlanJiffyBox(int, String)}.
     */
    @TestTemplate
    void testGetJiffyBoxError(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/jiffyBoxes/12345"))
            .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/jiffyBoxes/testGetJiffyBoxError.json")));

        Response<JiffyBox> response = api.jiffyBoxes().getJiffyBox(12345);
        List<Message> messages = response.getMessages();
        assertEquals(1, messages.size());
        assertEquals(MessageType.ERROR, messages.get(0).getType());
        assertEquals("Der Kunde Nr. 42 hat keine JiffyBox Nr. 12345.", messages.get(0).getMessageText());

        assertNull(response.getResult());
    }
}
