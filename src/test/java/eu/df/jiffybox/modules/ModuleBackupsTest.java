package eu.df.jiffybox.modules;

import eu.df.jiffybox.models.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * This class tests the 'backups' module.
 */
public class ModuleBackupsTest extends ModuleTest {

    /**
     * Test for {@link ModuleBackups#getBackups()}.
     */
    @Test
    public void testGetBackups() throws IOException {
        Response<Map<String, Backup>> response = backups.getBackups();
        List<Message> messages = response.getMessages();
        Map<String, Backup> backups = response.getResult();
        Backup backup = backups.get("12345");
        BackupEntry daily = backup.getDaily();
        BackupEntry weekly = backup.getWeekly();
        BackupEntry biweekly = backup.getBiweekly();

        assertTrue(messages.isEmpty());

        assertEquals(0, backup.getDay());
        assertEquals(0, backup.getTime());

        assertEquals(1234567890, daily.getCreated());
        assertEquals("1234567890abcdef1234567890abcdef", daily.getId());
        assertEquals(1234567890, weekly.getCreated());
        assertEquals("234567890abcdef1234567890abcdef1", weekly.getId());
        assertEquals(1234567890, biweekly.getCreated());
        assertEquals("34567890abcdef1234567890abcdef12", biweekly.getId());

        assertNull(backup.getManual());
    }

    /**
     * Test for {@link ModuleBackups#getBackup(int)}.
     */
    @Test
    public void testGetBackup() throws IOException {
        Response<Backup> response = backups.getBackup(12345);
        List<Message> messages = response.getMessages();
        Backup backup = response.getResult();
        BackupEntry daily = backup.getDaily();
        BackupEntry weekly = backup.getWeekly();
        BackupEntry biweekly = backup.getBiweekly();

        assertTrue(messages.isEmpty());

        assertEquals(1, backup.getDay());
        assertEquals(1, backup.getTime());

        assertEquals(1234567890, daily.getCreated());
        assertEquals("1234567890abcdef1234567890abcdef", daily.getId());
        assertEquals(1234567890, weekly.getCreated());
        assertEquals("234567890abcdef1234567890abcdef1", weekly.getId());
        assertEquals(1234567890, biweekly.getCreated());
        assertEquals("34567890abcdef1234567890abcdef12", biweekly.getId());

        assertNull(backup.getManual());
    }

    /**
     * Test for {@link ModuleBackups#createPeriodicalBackups(int, int, int)}.
     */
    @Test
    public void testCreatePeriodicalBackups() throws IOException {
        Response<BackupConfig> response = backups.createPeriodicalBackups
                (12345, 0, 1);
        List<Message> messages = response.getMessages();
        BackupConfig backup = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(0, backup.getDayid());
        assertEquals(1, backup.getTimeid());
    }

    /**
     * Test for {@link ModuleBackups#updatePeriodicalBackups(int, Integer,
     * Integer)}.
     */
    @Test
    public void testUpdatePeriodicalBackups() throws IOException {
        Response<BackupConfig> response = backups.updatePeriodicalBackups
                (12345, 0, 1);
        List<Message> messages = response.getMessages();
        BackupConfig backup = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(0, backup.getDayid());
        assertEquals(1, backup.getTimeid());
    }

    /**
     * Test for {@link ModuleBackups#deletePeriodicalBackups(int)}.
     */
    @Test
    public void testDeletePeriodicalBackups() throws IOException {
        Response<Boolean> response = backups.deletePeriodicalBackups(12345);
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }

    /**
     * Test for {@link ModuleBackups#createManualBackup(int)}.
     */
    @Test
    public void testCreateManualBackup() throws IOException {
        Response<Boolean> response = backups.createManualBackup(12345);
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }

    /**
     * Test for {@link ModuleBackups#deleteBackup(int, String, String)}.
     */
    @Test
    public void testDeleteBackup() throws IOException {
        Response<Boolean> response = backups.deleteBackup(12345, "daily",
                "12345ACDEF");
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }

    /**
     * Test for {@link ModuleBackups#restoreBackup(int, String, String)}.
     */
    @Test
    public void testRestoreBackup() throws IOException {
        Response<Boolean> response = backups.restoreBackup(12345, "daily",
                "12345ACDEF");
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }
}
