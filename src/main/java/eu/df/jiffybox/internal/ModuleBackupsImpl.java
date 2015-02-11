package eu.df.jiffybox.internal;

import eu.df.jiffybox.models.Backup;
import eu.df.jiffybox.models.BackupConfig;
import eu.df.jiffybox.models.Response;
import eu.df.jiffybox.modules.ModuleBackups;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * Implementation of the backups module.
 *
 * @see eu.df.jiffybox.modules.ModuleBackups
 */
public class ModuleBackupsImpl implements ModuleBackups {

    /**
     * The parameter 'dayid'.
     */
    private static final String PARAMETER_DAY_ID = "dayid";

    /**
     * The parameter 'dayid'.
     */
    private static final String PARAMETER_TIME_ID = "timeid";

    /**
     * The parameter 'dayid'.
     */
    private static final String PARAMETER_MANUAL = "manual";

    /**
     * The base URI.
     */
    private final URI baseUri;

    /**
     * Create an instance of this module using the specified base URI.
     *
     * @param baseUri The base URI to use.
     */
    public ModuleBackupsImpl(final URI baseUri) {
        this.baseUri = URI.create(baseUri + "/backups");
    }

    @Override
    public Response<Map<String, Backup>> getBackups() throws IOException {
        return ApiCall.get(baseUri).asMap(String.class, Backup.class);
    }

    @Override
    public Response<Backup> getBackup(int id) throws IOException {
        return ApiCall.get(baseUri).appendPath(id).as(Backup.class);
    }

    @Override
    public Response<BackupConfig> createPeriodicalBackups(int id, int dayid,
                                                          int timeid) throws
            IOException {
        return ApiCall.post(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_DAY_ID, dayid)
                      .addParameter(PARAMETER_TIME_ID, timeid)
                      .as(BackupConfig.class);
    }

    @Override
    public Response<BackupConfig> updatePeriodicalBackups(int id, Integer
            dayid, Integer timeid) throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_DAY_ID, dayid)
                      .addParameter(PARAMETER_TIME_ID, timeid)
                      .as(BackupConfig.class);
    }

    @Override
    public Response<Boolean> deletePeriodicalBackups(int id) throws
            IOException {
        return ApiCall.delete(baseUri).appendPath(id).as(Boolean.class);
    }

    @Override
    public Response<Boolean> createManualBackup(int id) throws IOException {
        return ApiCall.post(baseUri)
                      .appendPath(id)
                      .appendPath(PARAMETER_MANUAL)
                      .as(Boolean.class);
    }

    @Override
    public Response<Boolean> deleteBackup(int id, String type, String
            backupid) throws IOException {
        return ApiCall.delete(baseUri)
                      .appendPath(id)
                      .appendPath(type)
                      .appendPath(backupid)
                      .as(Boolean.class);
    }

    @Override
    public Response<Boolean> restoreBackup(int id, String type, String
            backupid) throws IOException {
        return ApiCall.post(baseUri)
                      .appendPath(id)
                      .appendPath(type)
                      .appendPath(backupid)
                      .as(Boolean.class);
    }
}
