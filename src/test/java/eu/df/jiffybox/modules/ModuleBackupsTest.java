package eu.df.jiffybox.modules;

import eu.df.jiffybox.JiffyBoxApi;
import eu.df.jiffybox.models.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

/**
 * This class tests the 'backups' module.
 */
public class ModuleBackupsTest extends ModuleTest {

    /**
     * The {@link JiffyBoxApi}.
     */
    private final JiffyBoxApi jiffyBoxApi;

    /**
     * Create a new instance using the given {@link JiffyBoxApi}.
     *
     * @param jiffyBoxApi The {@link JiffyBoxApi}.
     */
    public ModuleBackupsTest(final JiffyBoxApi jiffyBoxApi) {
        this.jiffyBoxApi = jiffyBoxApi;

        // Only run in development.
        assumeTrue(jiffyBoxApi.getUri().toString().contains("localhost"));
    }

    /**
     * Test for {@link ModuleBackups#getBackups()}.
     */
    @Test
    public void testGetBackups() throws IOException {
        Response<List<Backup>> response = jiffyBoxApi.getModuleBackups().getBackups();
        List<Message> messages = response.getMessages();
        List<Backup> backups = response.getResult();
        Backup backup = backups.get(0);
        BackupEntry daily = backup.getDaily();
        BackupEntry weekly = backup.getWeekly();
        BackupEntry biweekly = backup.getBiweekly();

        assertTrue(messages.isEmpty());

        assertEquals("12345", backup.getKey());
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
        Response<Backup> response = jiffyBoxApi.getModuleBackups().getBackup(12345);
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
        Response<BackupConfig> response = jiffyBoxApi.getModuleBackups().createPeriodicalBackups(12345, 0, 1);
        List<Message> messages = response.getMessages();
        BackupConfig backup = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(0, backup.getDayid());
        assertEquals(1, backup.getTimeid());
    }

    /**
     * Test for {@link ModuleBackups#updatePeriodicalBackups(int, Integer, Integer)}.
     */
    @Test
    public void testUpdatePeriodicalBackups() throws IOException {
        Response<BackupConfig> response = jiffyBoxApi.getModuleBackups().updatePeriodicalBackups(12345, 0, 1);
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
        Response<Boolean> response = jiffyBoxApi.getModuleBackups().deletePeriodicalBackups(12345);
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
        Response<Boolean> response = jiffyBoxApi.getModuleBackups().createManualBackup(12345);
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
        Response<Boolean> response = jiffyBoxApi.getModuleBackups().deleteBackup(12345, "daily", "12345ACDEF");
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
        Response<Boolean> response = jiffyBoxApi.getModuleBackups().restoreBackup(12345, "daily", "12345ACDEF");
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }
}
