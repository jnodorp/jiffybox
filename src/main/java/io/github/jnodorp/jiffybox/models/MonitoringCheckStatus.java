package io.github.jnodorp.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * This enumeration contains all possible states of a monitoring check.
 */
@JsonAutoDetect
public enum MonitoringCheckStatus {

    /**
     * The monitoring check is in error and will be retried.
     */
    STATUS_ERROR_RETRY,

    /**
     * The monitoring check is in error.
     */
    STATUS_ERROR,

    /**
     * The monitoring check is offline.
     */
    STATUS_OFFLINE,

    /**
     * The monitoring check is ready.
     */
    STATUS_READY,

    /**
     * The monitoring check is creating.
     */
    STATUS_CREATING,

    /**
     * The monitoring check is created.
     */
    STATUS_CREATED,

    /**
     * The monitoring check is updating.
     */
    STATUS_UPDATING,

    /**
     * The monitoring check is updated.
     */
    STATUS_UPDATED,

    /**
     * The monitoring check is deactivating.
     */
    STATUS_DEACTIVATING,

    /**
     * The monitoring check is deactivated.
     */
    STATUS_DEACTIVATED,

    /**
     * The monitoring check is deleting.
     */
    STATUS_DELETING,

    /**
     * The monitoring check is deleted.
     */
    STATUS_DELETED
}
