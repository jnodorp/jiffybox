package eu.df.jiffybox.modules;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import eu.df.jiffybox.WireMockHelper;
import eu.df.jiffybox.models.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.*;

/**
 * This class tests the 'backups' module.
 */
public class ModuleBackupsTest {

    private final WireMockRule wireMock = new WireMockRule(wireMockConfig().dynamicPort());

    private final ModuleTestRule module = new ModuleTestRule(wireMock, false);

    @Rule
    public final RuleChain ruleChain = RuleChain.outerRule(wireMock).around(module);

    /**
     * Test for {@link ModuleBackups#getBackups()}.
     */
    @Test
    public void testGetBackups() {
        MappingBuilder builder = get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/backups"));
        wireMock.stubFor(builder.willReturn(aResponse().withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/backups/testGetBackups.json")));

        Response<Map<String, Backup>> response = module.get().backups().getBackups();
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
    public void testGetBackup() {
        MappingBuilder builder = get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/backups/12345"));
        wireMock.stubFor(builder.willReturn(aResponse().withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/backups/testGetBackup.json")));

        Response<Backup> response = module.get().backups().getBackup(12345);
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
    public void testCreatePeriodicalBackups() {
        MappingBuilder builder = post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/backups/12345"));
        StringValuePattern body = equalToJson("{\"dayid\": 0, \"timeid\": 1}", false, false);
        wireMock.stubFor(builder.withRequestBody(body)
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/backups/testCreatePeriodicalBackups.json")));

        Response<BackupConfig> response = module.get().backups().createPeriodicalBackups(12345, 0, 1);
        List<Message> messages = response.getMessages();
        BackupConfig backup = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(0, backup.getDayid());
        assertEquals(1, backup.getTimeid());
    }

    /**
     * Test for {@link ModuleBackups#updatePeriodicalBackups(int, int, int)}.
     */
    @Test
    public void testUpdatePeriodicalBackups() {
        MappingBuilder builder = put(urlPathEqualTo("/00000000000000000000000000000000/v1.0/backups/12345"));
        StringValuePattern body = equalToJson("{\"dayid\": 0, \"timeid\": 1}", false, false);
        wireMock.stubFor(builder.withRequestBody(body)
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/backups/testUpdatePeriodicalBackups.json")));

        Response<BackupConfig> response = module.get().backups().updatePeriodicalBackups(12345, 0, 1);
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
    public void testDeletePeriodicalBackups() {
        MappingBuilder builder = delete(urlPathEqualTo("/00000000000000000000000000000000/v1.0/backups/12345"));
        wireMock.stubFor(builder.willReturn(aResponse().withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/backups/testDeletePeriodicalBackups.json")));

        Response<Boolean> response = module.get().backups().deletePeriodicalBackups(12345);
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }

    /**
     * Test for {@link ModuleBackups#createManualBackup(int)}.
     */
    @Test
    public void testCreateManualBackup() {
        MappingBuilder builder = post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/backups/12345/manual"));
        StringValuePattern body = equalToJson("{}", false, false);
        wireMock.stubFor(builder.withRequestBody(body)
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/backups/testCreateManualBackups.json")));

        Response<Boolean> response = module.get().backups().createManualBackup(12345);
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }

    /**
     * Test for {@link ModuleBackups#deleteBackup(int, String, String)}.
     */
    @Test
    public void testDeleteBackup() {
        MappingBuilder builder = delete(urlPathEqualTo
                ("/00000000000000000000000000000000/v1.0/backups/12345/daily/12345ACDEF"));
        wireMock.stubFor(builder.willReturn(aResponse().withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/backups/testDeleteBackup.json")));

        Response<Boolean> response = module.get().backups().deleteBackup(12345, "daily", "12345ACDEF");
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }

    /**
     * Test for {@link ModuleBackups#restoreBackup(int, String, String)}.
     */
    @Test
    public void testRestoreBackup() {
        MappingBuilder builder = post(urlPathEqualTo
                ("/00000000000000000000000000000000/v1.0/backups/12345/daily/12345ACDEF"));
        StringValuePattern body = equalToJson("{}", false, false);
        wireMock.stubFor(builder.withRequestBody(body)
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/backups/testRestoreBackup.json")));

        Response<Boolean> response = module.get().backups().restoreBackup(12345, "daily", "12345ACDEF");
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }
}
