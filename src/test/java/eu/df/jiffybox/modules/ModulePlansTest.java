package eu.df.jiffybox.modules;

import eu.df.jiffybox.JiffyBoxApi;
import eu.df.jiffybox.models.Message;
import eu.df.jiffybox.models.Plan;
import eu.df.jiffybox.models.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the 'plans' module.
 */
public class ModulePlansTest extends ModuleTest {

    /**
     * The {@link JiffyBoxApi}.
     */
    private final JiffyBoxApi jiffyBoxApi;

    /**
     * Create a new instance using the given {@link JiffyBoxApi}.
     *
     * @param jiffyBoxApi The {@link JiffyBoxApi}.
     */
    public ModulePlansTest(final JiffyBoxApi jiffyBoxApi) {
        this.jiffyBoxApi = jiffyBoxApi;
    }

    /**
     * Test for {@link ModulePlans#getPlans()}.
     */
    @Test
    public void testGetPlans() throws IOException {
        Response<Map<String, Plan>> response = jiffyBoxApi.getModulePlans().getPlans();
        List<Message> messages = response.getMessages();
        Map<String, Plan> result = response.getResult();
        assertEquals(12, result.size());
        assertTrue(messages.isEmpty());

        Plan plan = result.get("44");
        assertEquals(1, plan.getCpus());
        assertEquals(102400, plan.getDiskSizeInMB());
        assertEquals(44, plan.getId());
        assertEquals("CloudLevel 1", plan.getName());
        assertEquals(12.99, plan.getPriceCap(), 0.001);
        assertEquals(0.02, plan.getPricePerHour(), 0.001);
        assertEquals(0.005, plan.getPricePerHourFrozen(), 0.0001);
        assertEquals(2048, plan.getRamInMB());

        plan = result.get("45");
        assertEquals(2, plan.getCpus());
        assertEquals(204800, plan.getDiskSizeInMB());
        assertEquals(45, plan.getId());
        assertEquals("CloudLevel 2", plan.getName());
        assertEquals(19.99, plan.getPriceCap(), 0.001);
        assertEquals(0.03, plan.getPricePerHour(), 0.001);
        assertEquals(0.01, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(4096, plan.getRamInMB());

        plan = result.get("46");
        assertEquals(4, plan.getCpus());
        assertEquals(307200, plan.getDiskSizeInMB());
        assertEquals(46, plan.getId());
        assertEquals("CloudLevel 3", plan.getName());
        assertEquals(29.99, plan.getPriceCap(), 0.001);
        assertEquals(0.05, plan.getPricePerHour(), 0.001);
        assertEquals(0.015, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(8192, plan.getRamInMB());

        plan = result.get("47");
        assertEquals(6, plan.getCpus());
        assertEquals(512000, plan.getDiskSizeInMB());
        assertEquals(47, plan.getId());
        assertEquals("CloudLevel 4", plan.getName());
        assertEquals(49.99, plan.getPriceCap(), 0.001);
        assertEquals(0.08, plan.getPricePerHour(), 0.001);
        assertEquals(0.02, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(12288, plan.getRamInMB());

        plan = result.get("48");
        assertEquals(8, plan.getCpus());
        assertEquals(768000, plan.getDiskSizeInMB());
        assertEquals(48, plan.getId());
        assertEquals("CloudLevel 5", plan.getName());
        assertEquals(69.99, plan.getPriceCap(), 0.001);
        assertEquals(0.11, plan.getPricePerHour(), 0.001);
        assertEquals(0.03, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(16384, plan.getRamInMB());

        plan = result.get("49");
        assertEquals(12, plan.getCpus());
        assertEquals(1024000, plan.getDiskSizeInMB());
        assertEquals(49, plan.getId());
        assertEquals("CloudLevel 6", plan.getName());
        assertEquals(99.99, plan.getPriceCap(), 0.001);
        assertEquals(0.15, plan.getPricePerHour(), 0.001);
        assertEquals(0.04, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(32768, plan.getRamInMB());

        plan = result.get("50");
        assertEquals(1, plan.getCpus());
        assertEquals(51200, plan.getDiskSizeInMB());
        assertEquals(50, plan.getId());
        assertEquals("CloudLevel 1 SSD", plan.getName());
        assertEquals(14.99, plan.getPriceCap(), 0.001);
        assertEquals(0.03, plan.getPricePerHour(), 0.001);
        assertEquals(0.01, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(2048, plan.getRamInMB());

        plan = result.get("51");
        assertEquals(2, plan.getCpus());
        assertEquals(102400, plan.getDiskSizeInMB());
        assertEquals(51, plan.getId());
        assertEquals("CloudLevel 2 SSD", plan.getName());
        assertEquals(24.99, plan.getPriceCap(), 0.001);
        assertEquals(0.05, plan.getPricePerHour(), 0.001);
        assertEquals(0.015, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(4096, plan.getRamInMB());

        plan = result.get("52");
        assertEquals(4, plan.getCpus());
        assertEquals(153600, plan.getDiskSizeInMB());
        assertEquals(52, plan.getId());
        assertEquals("CloudLevel 3 SSD", plan.getName());
        assertEquals(39.99, plan.getPriceCap(), 0.001);
        assertEquals(0.08, plan.getPricePerHour(), 0.001);
        assertEquals(0.02, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(8192, plan.getRamInMB());

        plan = result.get("53");
        assertEquals(6, plan.getCpus());
        assertEquals(256000, plan.getDiskSizeInMB());
        assertEquals(53, plan.getId());
        assertEquals("CloudLevel 4 SSD", plan.getName());
        assertEquals(69.99, plan.getPriceCap(), 0.001);
        assertEquals(0.11, plan.getPricePerHour(), 0.001);
        assertEquals(0.03, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(12288, plan.getRamInMB());

        plan = result.get("54");
        assertEquals(8, plan.getCpus());
        assertEquals(358400, plan.getDiskSizeInMB());
        assertEquals(54, plan.getId());
        assertEquals("CloudLevel 5 SSD", plan.getName());
        assertEquals(99.99, plan.getPriceCap(), 0.001);
        assertEquals(0.15, plan.getPricePerHour(), 0.001);
        assertEquals(0.04, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(16384, plan.getRamInMB());

        plan = result.get("55");
        assertEquals(12, plan.getCpus());
        assertEquals(512000, plan.getDiskSizeInMB());
        assertEquals(55, plan.getId());
        assertEquals("CloudLevel 6 SSD", plan.getName());
        assertEquals(149.99, plan.getPriceCap(), 0.001);
        assertEquals(0.23, plan.getPricePerHour(), 0.001);
        assertEquals(0.06, plan.getPricePerHourFrozen(), 0.001);
        assertEquals(32768, plan.getRamInMB());
    }

    /**
     * Test for {@link ModulePlans#getPlan(int)}.
     */
    @Test
    public void testGetPlan() throws IOException {
        Response<Plan> response = jiffyBoxApi.getModulePlans().getPlan(45);
        List<Message> messages = response.getMessages();
        Plan result = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(2, result.getCpus());
        assertEquals(204800, result.getDiskSizeInMB());
        assertEquals(45, result.getId());
        assertEquals("CloudLevel 2", result.getName());
        assertEquals(19.99, result.getPriceCap(), 0.001);
        assertEquals(0.03, result.getPricePerHour(), 0.001);
        assertEquals(0.01, result.getPricePerHourFrozen(), 0.001);
        assertEquals(4096, result.getRamInMB());
    }

    /**
     * Test for {@link ModulePlans#getPlan(String)}.
     */
    @Test
    public void testGetPlan1() throws IOException {
        Response<Plan> response = jiffyBoxApi.getModulePlans().getPlan("CloudLevel 2");
        List<Message> messages = response.getMessages();
        Plan result = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(2, result.getCpus());
        assertEquals(204800, result.getDiskSizeInMB());
        assertEquals(45, result.getId());
        assertEquals("CloudLevel 2", result.getName());
        assertEquals(19.99, result.getPriceCap(), 0.001);
        assertEquals(0.03, result.getPricePerHour(), 0.001);
        assertEquals(0.01, result.getPricePerHourFrozen(), 0.001);
        assertEquals(4096, result.getRamInMB());
    }
}
