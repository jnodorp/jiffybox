package eu.df.jiffybox.modules;

import eu.df.jiffybox.Build;
import eu.df.jiffybox.builders.MonitoringCheckBuilder;
import eu.df.jiffybox.models.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * This class tests the 'monitoring' module.
 */
public class ModuleMonitoringTest extends ModuleTest {

    /**
     * Test for {@link ModuleMonitoring#getMonitoringChecks()}.
     */
    @Test
    public void testGetMonitoringChecks() throws IOException {
        Response<Map<String, MonitoringCheck>> response = monitoring
                .getMonitoringChecks();
        List<Message> messages = response.getMessages();
        Map<String, MonitoringCheck> result = response.getResult();
        MonitoringCheck monitoringCheck = result.get("911");

        List<ContactGroup> contactGroups = monitoringCheck.getContactgroups();
        ContactGroup contactGroup = contactGroups.get(0);

        assertTrue(messages.isEmpty());

        assertEquals(300, monitoringCheck.getCheckInterval());
        assertEquals(MonitoringCheckType.HTTP, monitoringCheck.getCheckType());
        assertEquals("example.com", monitoringCheck.getDomainname());
        assertEquals(1234, monitoringCheck.getId());
        assertEquals("188.93.14.165", monitoringCheck.getIp());
        assertEquals(4849, monitoringCheck.getJiffyBox());
        assertEquals("test", monitoringCheck.getName());
        assertEquals("", monitoringCheck.getPassword());
        assertEquals("/index.php", monitoringCheck.getPath());
        assertEquals(80, monitoringCheck.getPort());
        assertEquals(3600, monitoringCheck.getReminderInterval());
        assertEquals(3, monitoringCheck.getRetryTolerance());
        assertEquals(MonitoringCheckStatus.STATUS_READY, monitoringCheck
                .getStatus());
        assertEquals("", monitoringCheck.getUsername());

        assertTrue(contactGroup.getContacts().isEmpty());
        assertEquals(123, contactGroup.getId());
        assertEquals("TestGruppe", contactGroup.getName());
        assertNull(contactGroup.getStatus());
    }

    /**
     * Test for {@link ModuleMonitoring#getMonitoringCheck(int)}.
     */
    @Test
    public void testGetMonitoringCheck() throws IOException {
        Response<MonitoringCheck> response = monitoring.getMonitoringCheck
                (1234);
        List<Message> messages = response.getMessages();
        MonitoringCheck result = response.getResult();

        List<ContactGroup> contactGroups = result.getContactgroups();
        ContactGroup contactGroup = contactGroups.get(0);

        assertTrue(messages.isEmpty());

        assertEquals(300, result.getCheckInterval());
        assertEquals(MonitoringCheckType.HTTP, result.getCheckType());
        assertEquals("example.com", result.getDomainname());
        assertEquals(1234, result.getId());
        assertEquals("188.93.14.211", result.getIp());
        assertEquals(4849, result.getJiffyBox());
        assertEquals("test", result.getName());
        assertEquals("", result.getPassword());
        assertEquals("/index.php", result.getPath());
        assertEquals(80, result.getPort());
        assertEquals(3600, result.getReminderInterval());
        assertEquals(3, result.getRetryTolerance());
        assertEquals(MonitoringCheckStatus.STATUS_READY, result.getStatus());
        assertEquals("", result.getUsername());

        assertTrue(contactGroup.getContacts().isEmpty());
        assertEquals(123, contactGroup.getId());
        assertEquals("TestGruppe", contactGroup.getName());
        assertNull(contactGroup.getStatus());
    }

    /**
     * Test for {@link ModuleMonitoring#deleteMonitoringCheck(int)}.
     */
    @Test
    public void testDeleteMonitoringCheck() throws IOException {
        Response<Boolean> response = monitoring.deleteMonitoringCheck(1234);
        List<Message> messages = response.getMessages();
        Message message = messages.get(0);

        boolean result = response.getResult();

        assertEquals("Der Monitoring-Check Test(M1234) wird geloescht",
                message.getMessageText());
        assertEquals(MessageType.SUCCESS, message.getType());

        assertTrue(result);
    }

    /**
     * Test for {@link ModuleMonitoring#createMonitoringCheck(
     *MonitoringCheckBuilder)}.
     */
    @Test
    public void testCreateMonitoringCheck() throws IOException {
        MonitoringCheckBuilder data = Build.monitoringCheck("Test", "188.93"
                + ".14.211", 80)
                                           .http("example.com", "/index.php");

        Response<MonitoringCheck> response = monitoring.createMonitoringCheck
                (data);
        List<Message> messages = response.getMessages();
        MonitoringCheck result = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(300, result.getCheckInterval());
        assertEquals(MonitoringCheckType.HTTP, result.getCheckType());
        assertTrue(result.getContactgroups().isEmpty());
        assertEquals("example.com", result.getDomainname());
        assertEquals(1234, result.getId());
        assertEquals("188.93.14.211", result.getIp());
        assertEquals(4849, result.getJiffyBox());
        assertEquals("Test", result.getName());
        assertNull(result.getPassword());
        assertEquals("/index.php", result.getPath());
        assertEquals(80, result.getPort());
        assertEquals(3600, result.getReminderInterval());
        assertEquals(3, result.getRetryTolerance());
        assertEquals(MonitoringCheckStatus.STATUS_CREATING, result.getStatus());
        assertNull(result.getUsername());
    }

    /**
     * Test for {@link ModuleMonitoring#duplicateMonitoringCheck(int,
     * MonitoringCheckBuilder)}.
     */
    @Test
    public void testDuplicateMonitoringCheck() throws IOException {
        MonitoringCheckBuilder data = Build.monitoringCheck("Kopie von Test",
                "188.93.14.212", null)
                                           .preserveType();

        Response<MonitoringCheck> response = monitoring
                .duplicateMonitoringCheck(1234, data);
        List<Message> messages = response.getMessages();
        MonitoringCheck result = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(300, result.getCheckInterval());
        assertEquals(MonitoringCheckType.HTTP, result.getCheckType());
        assertTrue(result.getContactgroups().isEmpty());
        assertEquals("example.com", result.getDomainname());
        assertEquals(1235, result.getId());
        assertEquals("188.93.14.212", result.getIp());
        assertEquals(4849, result.getJiffyBox());
        assertEquals("Kopie von Test", result.getName());
        assertNull(result.getPassword());
        assertEquals("/index.php", result.getPath());
        assertEquals(80, result.getPort());
        assertEquals(3600, result.getReminderInterval());
        assertEquals(3, result.getRetryTolerance());
        assertEquals(MonitoringCheckStatus.STATUS_CREATING, result.getStatus());
        assertNull(result.getUsername());
    }

    /**
     * Test for {@link ModuleMonitoring#getStatus(int)}.
     */
    @Test
    public void testGetStatus() throws IOException {
        Response<MonitoringStatus> response = monitoring.getStatus(1234);
        List<Message> messages = response.getMessages();
        MonitoringStatus result = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(1234, result.getId().intValue());
        assertEquals(0, result.getCode());
        assertEquals("OK - 123.45.67.89: rta 0.313ms, lost 0%", result
                .getResponse());
    }

    /**
     * Test for {@link ModuleMonitoring#getStatuses(String)}.
     */
    @Test
    public void testGetStatuses() throws IOException {
        Response<Map<String, MonitoringStatus>> response = monitoring
                .getStatuses("123.45.67.89");
        List<Message> messages = response.getMessages();
        Map<String, MonitoringStatus> result = response.getResult();

        MonitoringStatus monitoringStatus1 = result.get("1234");
        MonitoringStatus monitoringStatus2 = result.get("5678");

        assertTrue(messages.isEmpty());

        assertNull(monitoringStatus1.getId());
        assertEquals(0, monitoringStatus1.getCode());
        assertEquals("OK - 123.45.67.89: rta 0.313ms, lost 0%",
                monitoringStatus1
                        .getResponse());

        assertNull(monitoringStatus2.getId());
        assertEquals(0, monitoringStatus2.getCode());
        assertEquals("HTTP OK: Status line output matched &quot;200&quot; " +
                "-3827 bytes in 0.003 second response time", monitoringStatus2
                .getResponse());
    }
}
