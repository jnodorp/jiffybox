package eu.df.jiffybox.modules;

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
     * Test for {@link ModulePlans#getPlans()}.
     */
    @Test
    public void testGetPlans() throws IOException {
        Response<Map<String, Plan>> response = plans.getPlans();
        List<Message> messages = response.getMessages();
        Map<String, Plan> result = response.getResult();
        Plan plan1 = result.get("20");
        Plan plan2 = result.get("21");
        Plan plan3 = result.get("22");
        Plan plan4 = result.get("23");

        assertTrue(messages.isEmpty());

        assertEquals(3, plan1.getCpus());
        assertEquals(76800, plan1.getDiskSizeInMB());
        assertEquals(20, plan1.getId());
        assertEquals("CloudLevel 1", plan1.getName());
        assertEquals(0.02, plan1.getPricePerHour(), 0.001);
        assertEquals(0.005, plan1.getPricePerHourFrozen(), 0.0001);
        assertEquals(2048, plan1.getRamInMB());

        assertEquals(4, plan2.getCpus());
        assertEquals(153600, plan2.getDiskSizeInMB());
        assertEquals(21, plan2.getId());
        assertEquals("CloudLevel 2", plan2.getName());
        assertEquals(0.04, plan2.getPricePerHour(), 0.001);
        assertEquals(0.01, plan2.getPricePerHourFrozen(), 0.001);
        assertEquals(4096, plan2.getRamInMB());

        assertEquals(6, plan3.getCpus());
        assertEquals(307200, plan3.getDiskSizeInMB());
        assertEquals(22, plan3.getId());
        assertEquals("CloudLevel 3", plan3.getName());
        assertEquals(0.07, plan3.getPricePerHour(), 0.001);
        assertEquals(0.02, plan3.getPricePerHourFrozen(), 0.001);
        assertEquals(8192, plan3.getRamInMB());

        assertEquals(6, plan4.getCpus());
        assertEquals(409600, plan4.getDiskSizeInMB());
        assertEquals(23, plan4.getId());
        assertEquals("CloudLevel 4", plan4.getName());
        assertEquals(0.13, plan4.getPricePerHour(), 0.001);
        assertEquals(0.03, plan4.getPricePerHourFrozen(), 0.001);
        assertEquals(16384, plan4.getRamInMB());
    }

    /**
     * Test for {@link ModulePlans#getPlan(int)}.
     */
    @Test
    public void testGetPlan() throws IOException {
        Response<Plan> response = plans.getPlan(21);
        List<Message> messages = response.getMessages();
        Plan result = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(4, result.getCpus());
        assertEquals(153600, result.getDiskSizeInMB());
        assertEquals(21, result.getId());
        assertEquals("CloudLevel 2", result.getName());
        assertEquals(0.04, result.getPricePerHour(), 0.001);
        assertEquals(0.01, result.getPricePerHourFrozen(), 0.001);
        assertEquals(4096, result.getRamInMB());
    }

    /**
     * Test for {@link ModulePlans#getPlan(String)}.
     */
    @Test
    public void testGetPlan1() throws IOException {
        Response<Plan> response = plans.getPlan("CloudLevel 2");
        List<Message> messages = response.getMessages();
        Plan result = response.getResult();

        assertTrue(messages.isEmpty());

        assertEquals(4, result.getCpus());
        assertEquals(153600, result.getDiskSizeInMB());
        assertEquals(21, result.getId());
        assertEquals("CloudLevel 2", result.getName());
        assertEquals(0.04, result.getPricePerHour(), 0.001);
        assertEquals(0.01, result.getPricePerHourFrozen(), 0.001);
        assertEquals(4096, result.getRamInMB());
    }
}
