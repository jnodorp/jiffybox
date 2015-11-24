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
        assertEquals(10, result.size());

        Plan plan1 = result.get("20");
        Plan plan2 = result.get("21");
        Plan plan3 = result.get("22");
        Plan plan4 = result.get("23");
        Plan plan5 = result.get("24");
        Plan plan6 = result.get("28");
        Plan plan7 = result.get("29");
        Plan plan8 = result.get("30");
        Plan plan9 = result.get("31");
        Plan plan10 = result.get("32");

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

        assertEquals(8, plan4.getCpus());
        assertEquals(409600, plan4.getDiskSizeInMB());
        assertEquals(23, plan4.getId());
        assertEquals("CloudLevel 4", plan4.getName());
        assertEquals(0.13, plan4.getPricePerHour(), 0.001);
        assertEquals(0.03, plan4.getPricePerHourFrozen(), 0.001);
        assertEquals(16384, plan4.getRamInMB());

        assertEquals(8, plan5.getCpus());
        assertEquals(716800, plan5.getDiskSizeInMB());
        assertEquals(24, plan5.getId());
        assertEquals("CloudLevel 5", plan5.getName());
        assertEquals(0.25, plan5.getPricePerHour(), 0.001);
        assertEquals(0.04, plan5.getPricePerHourFrozen(), 0.001);
        assertEquals(32768, plan5.getRamInMB());

        assertEquals(3, plan6.getCpus());
        assertEquals(25600, plan6.getDiskSizeInMB());
        assertEquals(28, plan6.getId());
        assertEquals("CloudLevel 1 SSD", plan6.getName());
        assertEquals(0.025, plan6.getPricePerHour(), 0.001);
        assertEquals(0.004, plan6.getPricePerHourFrozen(), 0.001);
        assertEquals(2048, plan6.getRamInMB());

        assertEquals(4, plan7.getCpus());
        assertEquals(51200, plan7.getDiskSizeInMB());
        assertEquals(29, plan7.getId());
        assertEquals("CloudLevel 2 SSD", plan7.getName());
        assertEquals(0.05, plan7.getPricePerHour(), 0.001);
        assertEquals(0.01, plan7.getPricePerHourFrozen(), 0.001);
        assertEquals(4096, plan7.getRamInMB());

        assertEquals(6, plan8.getCpus());
        assertEquals(102400, plan8.getDiskSizeInMB());
        assertEquals(30, plan8.getId());
        assertEquals("CloudLevel 3 SSD", plan8.getName());
        assertEquals(0.09, plan8.getPricePerHour(), 0.001);
        assertEquals(0.02, plan8.getPricePerHourFrozen(), 0.001);
        assertEquals(8192, plan8.getRamInMB());

        assertEquals(8, plan9.getCpus());
        assertEquals(204800, plan9.getDiskSizeInMB());
        assertEquals(31, plan9.getId());
        assertEquals("CloudLevel 4 SSD", plan9.getName());
        assertEquals(0.15, plan9.getPricePerHour(), 0.001);
        assertEquals(0.03, plan9.getPricePerHourFrozen(), 0.001);
        assertEquals(16384, plan9.getRamInMB());

        assertEquals(8, plan10.getCpus());
        assertEquals(409600, plan10.getDiskSizeInMB());
        assertEquals(32, plan10.getId());
        assertEquals("CloudLevel 5 SSD", plan10.getName());
        assertEquals(0.30, plan10.getPricePerHour(), 0.001);
        assertEquals(0.04, plan10.getPricePerHourFrozen(), 0.001);
        assertEquals(32768, plan10.getRamInMB());
    }

    /**
     * Test for {@link ModulePlans#getPlan(int)}.
     */
    @Test
    public void testGetPlan() throws IOException {
        Response<Plan> response = jiffyBoxApi.getModulePlans().getPlan(21);
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
        Response<Plan> response = jiffyBoxApi.getModulePlans().getPlan("CloudLevel 2");
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
