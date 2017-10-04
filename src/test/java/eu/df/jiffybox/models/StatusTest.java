package eu.df.jiffybox.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for {@link eu.df.jiffybox.models.Status}.
 */
class StatusTest {

    /**
     * Check if the status of each JSON representation is correct.
     */
    @Test
    void testFromJson() {
        assertEquals(Status.DELETING, Status.fromJson("DELETING"));
        assertEquals(Status.RUNNING, Status.fromJson("RUNNING"));
        assertEquals(Status.READY, Status.fromJson("READY"));
        assertEquals(Status.CREATING, Status.fromJson("CREATING"));
        assertEquals(Status.FREEZING, Status.fromJson("FREEZING"));
        assertEquals(Status.THAWING, Status.fromJson("THAWING"));
        assertEquals(Status.FROZEN, Status.fromJson("FROZEN"));
        assertEquals(Status.CHANGING_PLAN, Status.fromJson("CHANGING PLAN"));
    }

    /**
     * Check if the JSON representation of each status is correct.
     */
    @Test
    void testToJson() {
        assertEquals("DELETING", Status.DELETING.toJson());
        assertEquals("RUNNING", Status.RUNNING.toJson());
        assertEquals("READY", Status.READY.toJson());
        assertEquals("CREATING", Status.CREATING.toJson());
        assertEquals("FREEZING", Status.FREEZING.toJson());
        assertEquals("THAWING", Status.THAWING.toJson());
        assertEquals("FROZEN", Status.FROZEN.toJson());
        assertEquals("CHANGING PLAN", Status.CHANGING_PLAN.toJson());
    }
}
