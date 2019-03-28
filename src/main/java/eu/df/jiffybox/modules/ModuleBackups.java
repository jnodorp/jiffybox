package eu.df.jiffybox.modules;

import eu.df.jiffybox.models.Backup;
import eu.df.jiffybox.models.BackupConfig;
import eu.df.jiffybox.models.Response;
import feign.Body;
import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.Map;

/**
 * This interface describes the backups module.
 */
public interface ModuleBackups {

    /**
     * Build the module.
     *
     * @param baseUrl the base URL
     * @return the module
     */
    static ModuleBackups build(String baseUrl) {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(ModuleBackups.class, baseUrl);
    }

    /**
     * You may use this command to display the backups of each JiffyBox but it
     * is significantly slower than the selective querying a single JiffyBox,
     * because the backup servers check the backups availability in a
     * synchronous manner. Whenever possible you should stick with the specific
     * query.
     *
     * @return The retrieved Backups.
     */
    @RequestLine("GET /backups")
    Response<Map<String, Backup>> getBackups();

    /**
     * This method provides details about every backup of a given JiffyBox.
     * Since this call acts in a synchronous manner it may take longer than
     * typical on-load. The availability of the backups is checked directly on
     * the backup storages, therefore only finished backups will be displayed.
     * The return values day and time show, if and when the automated daily
     * backup will be scheduled. If there are no return values automated backups
     * are deactivated. day is a value between 0 (Sunday) and 6 (Saturday). time
     * is either 0 (between 12:00 a.m and 4:00 a.m), 1 (between 4:00 a.m and
     * 6:00 a.m) or 6 (between 10:00 p.m and 12:00 a.m).
     *
     * @param id Box-ID
     * @return The retrieved Backup.
     */
    @RequestLine("GET /backups/{id}")
    Response<Backup> getBackup(@Param("id") int id);

    /**
     * Using this command you are able to set if periodical backups shall be
     * created.
     *
     * @param id     Box-ID
     * @param dayid  The day of week the backup should be done. Possible values
     *               are 0 (on Sunday) to 6 (on Saturday).
     * @param timeid The time frame, when the backup should be created. Possible
     *               values are: 1 (between 12:00 a.m and 4:00 a.m, 2 (between
     *               4:00 a.m and 6:00 a.m) and 6 (between 10:00 p.m and 12:00
     *               a.m)
     * @return The created BackupConfig.
     */
    @RequestLine("POST /backups/{id}")
    Response<BackupConfig> createPeriodicalBackups(@Param("id") int id, @Param("dayid") int dayid, @Param("timeid") int timeid);

    /**
     * Using this command you are able to change an existing backup
     * configuration. To create a new one or activate automated backups use the
     * POST verb. The arguments are the same as in the POST method. The only
     * exception is, that all arguments are optional and only given values will
     * be changed. Use null for values you don't want to change.
     *
     * @param id     Box-ID
     * @param dayid  The day of week the backup should be done. Possible values
     *               are 0 (on Sunday) to 6 (on Saturday).
     * @param timeid The time frame, when the backup should be created. Possible
     *               values are: 1 (between 12:00 a.m and 4:00 a.m, 2 (between
     *               4:00 a.m and 6:00 a.m) and 6 (between 10:00 p.m and 12:00
     *               a.m)
     * @return The updated BackupConfig.
     */
    @RequestLine("PUT /backups/{id}")
    Response<BackupConfig> updatePeriodicalBackups(@Param("id") int id, @Param("dayid") int dayid, @Param("timeid") int timeid);

    /**
     * By default automated backups of every JiffyBox are created on a daily
     * basis. If this is undesired (e. g. on-load) you may deactivate automated
     * backups. Please notice, that deactivating automated backups will cause
     * old automated backups to disappear. However this call does not affect
     * manual backups or the possibility to create those.
     *
     * @param id Box-ID
     * @return If the deletion of the BackupConfig has successfully been
     * initiated.
     */
    @RequestLine("DELETE /backups/{id}")
    Response<Boolean> deletePeriodicalBackups(@Param("id") int id);

    /**
     * Each JiffyBox is able to hold exactly one manual backup. This method may
     * be used to request a manual backup. The process itself is completely
     * asynchronous. A success (result TRUE) only means, that the manual backup
     * was requested successfully. However it does not mean it was finished or
     * even started. If a manual backup is running, can be evaluated using
     * ModuleJiffyBoxes. There is a flag manualBackupRunning which is set to
     * TRUE in case the backup is running. As soon as the backup is finished it
     * is displayed within the backup overview.
     *
     * @param id Box-ID
     * @return If the manual backup has been successfully initiated.
     */
    @RequestLine("POST /backups/{id}/manual")
    @Body("%7B%7D")
    Response<Boolean> createManualBackup(@Param("id") int id);

    /**
     * With this command it is possible to delete a backup using its type
     * (manual, daily, weekly, biweekly) and its unique ID. Type and ID may be
     * obtained using the according GET commands.
     *
     * @param id       Box-ID
     * @param type     Type
     * @param backupid Backup-ID
     * @return If the deletion of the backup has been successfully initiated.
     */
    @RequestLine("DELETE /backups/{id}/{type}/{backupid}")
    Response<Boolean> deleteBackup(@Param("id") int id, @Param("type") String type, @Param("backupid") String backupid);

    /**
     * With this command it is possible to replace the content of a given
     * JiffyBox with a given backup. All data is lost and configurations
     * (profiles, hard drives) are reset to the backups state during this
     * process. A list of valid backup types and IDs can be obtained using
     * get(int id) on this module.
     *
     * @param id       Box-ID
     * @param type     Type
     * @param backupid Backup-ID
     * @return If the process of restoring the backup has been successfully
     * initiated.
     */
    @RequestLine("POST /backups/{id}/{type}/{backupid}")
    @Body("%7B%7D")
    Response<Boolean> restoreBackup(@Param("id") int id, @Param("type") String type, @Param("backupid") String backupid);
}
