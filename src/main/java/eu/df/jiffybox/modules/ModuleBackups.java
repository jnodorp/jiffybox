package eu.df.jiffybox.modules;

import eu.df.jiffybox.models.Backup;
import eu.df.jiffybox.models.BackupConfig;
import eu.df.jiffybox.models.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This interface describes the backups module.
 */
public interface ModuleBackups {

    /**
     * You may use this command to display the backups of each JiffyBox but it
     * is significantly slower than the selective querying a single JiffyBox,
     * because the backup servers check the backups availability in a
     * synchronous manner. Whenever possible you should stick with the specific
     * query.
     *
     * @return The retrieved Backups.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<List<Backup>> getBackups() throws IOException;

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
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Backup> getBackup(final int id) throws IOException;

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
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<BackupConfig> createPeriodicalBackups(int id, int dayid, int
            timeid) throws IOException;

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
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<BackupConfig> updatePeriodicalBackups(int id, Integer dayid,
                                                   Integer timeid) throws
            IOException;

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
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Boolean> deletePeriodicalBackups(int id) throws IOException;

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
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Boolean> createManualBackup(int id) throws IOException;

    /**
     * With this command it is possible to delete a backup using its type
     * (manual, daily, weekly, biweekly) and its unique ID. Type and ID may be
     * obtained using the according GET commands.
     *
     * @param id       Box-ID
     * @param type     Type
     * @param backupid Backup-ID
     * @return If the deletion of the backup has been successfully initiated.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Boolean> deleteBackup(final int id, final String type, final
    String backupid) throws IOException;

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
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Boolean> restoreBackup(final int id, final String type, final
    String backupid) throws IOException;
}
