package io.github.jnodorp.jiffybox.internal;

import io.github.jnodorp.jiffybox.builders.MonitoringCheckBuilder;
import io.github.jnodorp.jiffybox.models.MonitoringCheckType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link DefaultMonitoringCheckBuilder}.
 */
class DefaultMonitoringCheckBuilderTest {

    private final static MonitoringCheckBuilder BUILDER = new DefaultMonitoringCheckBuilder("", "",
            MonitoringCheckType.DNS, 0);

    /**
     * Test method for {@link DefaultMonitoringCheckBuilder#withContactgroups(List)}.
     */
    @Test
    void testWithContactgroups() {
        final List<Integer> contactGroupIds = new ArrayList<>();
        contactGroupIds.add(1);
        contactGroupIds.add(2);
        contactGroupIds.add(3);

        assertTrue(BUILDER.withContactgroups(contactGroupIds).toString().contains("\"contactGroups\":[1,2,3]"));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilder#withCheckInterval(int)}.
     */
    @Test
    void testWithCheckInterval() {
        assertTrue(BUILDER.withCheckInterval(1).toString().contains("\"checkInterval\":1"));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilder#withReminderInterval(int)}.
     */
    @Test
    void testWithReminderInterval() {
        assertTrue(BUILDER.withReminderInterval(1).toString().contains("\"reminderInterval\":1"));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilder#withRetryTolerance(int)}.
     */
    @Test
    void testWithRetryTolerance() {
        assertTrue(BUILDER.withRetryTolerance(1).toString().contains("\"retryTolerance\":1"));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilder#withTimeout(int)}.
     */
    @Test
    void testWithTimeout() {
        assertTrue(BUILDER.withTimeout(1).toString().contains("\"timeout\":1"));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilder#withActive(boolean)}.
     */
    @Test
    void testWithActive() {
        assertTrue(BUILDER.withActive(true).toString().contains("\"checkInterval\":1"));
    }
}
