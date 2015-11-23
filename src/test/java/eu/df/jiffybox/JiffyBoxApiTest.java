package eu.df.jiffybox;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Test class for {@link JiffyBoxApi}.
 */
public class JiffyBoxApiTest {

    /**
     * The first {@link JiffyBoxApi} under test.
     */
    private static final JiffyBoxApi API1 = new JiffyBoxApi("00000000000000000000000000000001");

    /**
     * The second {@link JiffyBoxApi} under test.
     */
    private static final JiffyBoxApi API2 = new JiffyBoxApi("00000000000000000000000000000001", "http://example.org");

    /**
     * The third {@link JiffyBoxApi} under test.
     */
    private static final JiffyBoxApi API3 = new JiffyBoxApi("00000000000000000000000000000003", "http://example.org",
            "v1.0");

    /**
     * Test for {@link JiffyBoxApi#getModuleBackups()}.
     */
    @Test
    public void testGetModuleBackups() {
        assertNotNull(API1.getModuleBackups());
        assertNotNull(API2.getModuleBackups());
        assertNotNull(API3.getModuleBackups());
    }

    /**
     * Test for {@link JiffyBoxApi#getModuleContactGroups()}.
     */
    @Test
    public void testGetModuleContactGroups() {
        assertNotNull(API1.getModuleBackups());
        assertNotNull(API2.getModuleBackups());
        assertNotNull(API3.getModuleBackups());
    }

    /**
     * Test for {@link JiffyBoxApi#getModuleDistributions()}.
     */
    @Test
    public void testGetModuleDistributions() {
        assertNotNull(API1.getModuleBackups());
        assertNotNull(API2.getModuleBackups());
        assertNotNull(API3.getModuleBackups());
    }

    /**
     * Test for {@link JiffyBoxApi#getModuleDoc()}.
     */
    @Test
    public void testGetModuleDoc() {
        assertNotNull(API1.getModuleBackups());
        assertNotNull(API2.getModuleBackups());
        assertNotNull(API3.getModuleBackups());
    }

    /**
     * Test for {@link JiffyBoxApi#getModuleIps()}.
     */
    @Test
    public void testGetModuleIps() {
        assertNotNull(API1.getModuleBackups());
        assertNotNull(API2.getModuleBackups());
        assertNotNull(API3.getModuleBackups());
    }

    /**
     * Test for {@link JiffyBoxApi#getModuleJiffyBoxes()}.
     */
    @Test
    public void testGetModuleJiffyBoxes() {
        assertNotNull(API1.getModuleBackups());
        assertNotNull(API2.getModuleBackups());
        assertNotNull(API3.getModuleBackups());
    }

    /**
     * Test for {@link JiffyBoxApi#getModuleMonitoring()};
     */
    @Test
    public void testGetModuleMonitoring() {
        assertNotNull(API1.getModuleBackups());
        assertNotNull(API2.getModuleBackups());
        assertNotNull(API3.getModuleBackups());
    }

    /**
     * Test for {@link JiffyBoxApi#getModulePlans()}.
     */
    @Test
    public void testGetModulePlans() {
        assertNotNull(API1.getModuleBackups());
        assertNotNull(API2.getModuleBackups());
        assertNotNull(API3.getModuleBackups());
    }
}