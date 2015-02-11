package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * This enumeration contains all possible states of a contact group.
 *
 * @see eu.df.jiffybox.models.ContactGroup
 */
@JsonAutoDetect
public enum ContactGroupStatus {

    /**
     * The contact group is ready.
     */
    STATUS_READY,

    /**
     * The contact group is deleting.
     */
    STATUS_DELETING,

    /**
     * The contact group has been deleted.
     */
    STATUS_DELETED,

    /**
     * The contact group is updating.
     */
    STATUS_UPDATING,

    /**
     * The contact group has been updated.
     */
    STATUS_UPDATED
}
