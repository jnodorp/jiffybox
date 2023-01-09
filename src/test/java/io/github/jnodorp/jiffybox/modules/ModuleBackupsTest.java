package io.github.jnodorp.jiffybox.modules;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import io.github.jnodorp.jiffybox.JiffyBoxApi;
import io.github.jnodorp.jiffybox.WireMockHelper;
import io.github.jnodorp.jiffybox.models.*;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the 'backups' module.
 */
@ExtendWith(ModuleTestExtension.class)
@ModuleTestExtension.ModuleTest(runAgainstServer = false)
class ModuleBackupsTest {

    /**
     * Test for {@link ModuleBackups#getBackups()}.
     */
    @TestTemplate
    void testGetBackups(WireMockServer wireMock, JiffyBoxApi api) {
        MappingBuilder builder = get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/backups"));
        wireMock.stubFor(builder.willReturn(aResponse().withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/backups/testGetBackups.json")));

        Response<Map<String, Backup>> response = api.backups().getBackups();
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
    @TestTemplate
    void testGetBackup(WireMockServer wireMock, JiffyBoxApi api) {
        MappingBuilder builder = get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/backups/12345"));
        wireMock.stubFor(builder.willReturn(aResponse().withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/backups/testGetBackup.json")));

        Response<Backup> response = api.backups().getBackup(12345);
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
    @TestTemplate
    void testCreatePeriodicalBackups(WireMockServer wireMock, JiffyBoxApi api) {
        MappingBuilder builder = post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/backups/12345"));
        StringValuePattern body = equalToJson("{\"dayid\": 0, \"timeid\": 1}", false, false);
        wireMock.stubFor(builder.withRequestBody(body)
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/backups/testCreatePeriodicalBackups.json")));

        Response<BackupConfig> response = api.backups().createPeriodicalBackups(12345, 0, 1);
        List<Message> messages = response.getMessages();
        BackupConfig backup = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(0, backup.getDayid());
        assertEquals(1, backup.getTimeid());
    }

    /**
     * Test for {@link ModuleBackups#updatePeriodicalBackups(int, int, int)}.
     */
    @TestTemplate
    void testUpdatePeriodicalBackups(WireMockServer wireMock, JiffyBoxApi api) {
        MappingBuilder builder = put(urlPathEqualTo("/00000000000000000000000000000000/v1.0/backups/12345"));
        StringValuePattern body = equalToJson("{\"dayid\": 0, \"timeid\": 1}", false, false);
        wireMock.stubFor(builder.withRequestBody(body)
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/backups/testUpdatePeriodicalBackups.json")));

        Response<BackupConfig> response = api.backups().updatePeriodicalBackups(12345, 0, 1);
        List<Message> messages = response.getMessages();
        BackupConfig backup = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(0, backup.getDayid());
        assertEquals(1, backup.getTimeid());
    }

    /**
     * Test for {@link ModuleBackups#deletePeriodicalBackups(int)}.
     */
    @TestTemplate
    void testDeletePeriodicalBackups(WireMockServer wireMock, JiffyBoxApi api) {
        MappingBuilder builder = delete(urlPathEqualTo("/00000000000000000000000000000000/v1.0/backups/12345"));
        wireMock.stubFor(builder.willReturn(aResponse().withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/backups/testDeletePeriodicalBackups.json")));

        Response<Boolean> response = api.backups().deletePeriodicalBackups(12345);
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }

    /**
     * Test for {@link ModuleBackups#createManualBackup(int)}.
     */
    @TestTemplate
    void testCreateManualBackup(WireMockServer wireMock, JiffyBoxApi api) {
        MappingBuilder builder = post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/backups/12345/manual"));
        StringValuePattern body = equalTo("%7B%7D");
        wireMock.stubFor(builder.withRequestBody(body)
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/backups/testCreateManualBackups.json")));

        Response<Boolean> response = api.backups().createManualBackup(12345);
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }

    /**
     * Test for {@link ModuleBackups#deleteBackup(int, String, String)}.
     */
    @TestTemplate
    void testDeleteBackup(WireMockServer wireMock, JiffyBoxApi api) {
        MappingBuilder builder = delete(urlPathEqualTo
                ("/00000000000000000000000000000000/v1.0/backups/12345/daily/12345ACDEF"));
        wireMock.stubFor(builder.willReturn(aResponse().withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/backups/testDeleteBackup.json")));

        Response<Boolean> response = api.backups().deleteBackup(12345, "daily", "12345ACDEF");
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }

    /**
     * Test for {@link ModuleBackups#restoreBackup(int, String, String)}.
     */
    @TestTemplate
    void testRestoreBackup(WireMockServer wireMock, JiffyBoxApi api) {
        MappingBuilder builder = post(urlPathEqualTo
                ("/00000000000000000000000000000000/v1.0/backups/12345/daily/12345ACDEF"));
        StringValuePattern body = equalTo("%7B%7D");
        wireMock.stubFor(builder.withRequestBody(body)
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/backups/testRestoreBackup.json")));

        Response<Boolean> response = api.backups().restoreBackup(12345, "daily", "12345ACDEF");
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }
}
