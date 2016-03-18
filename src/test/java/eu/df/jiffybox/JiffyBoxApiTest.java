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
     * Test for {@link JiffyBoxApi#backups()}.
     */
    @Test
    public void testGetModuleBackups() {
        assertNotNull(API1.backups());
        assertNotNull(API2.backups());
        assertNotNull(API3.backups());
    }

    /**
     * Test for {@link JiffyBoxApi#contactGroups()}.
     */
    @Test
    public void testGetModuleContactGroups() {
        assertNotNull(API1.backups());
        assertNotNull(API2.backups());
        assertNotNull(API3.backups());
    }

    /**
     * Test for {@link JiffyBoxApi#distributions()}.
     */
    @Test
    public void testGetModuleDistributions() {
        assertNotNull(API1.backups());
        assertNotNull(API2.backups());
        assertNotNull(API3.backups());
    }

    /**
     * Test for {@link JiffyBoxApi#doc()}.
     */
    @Test
    public void testGetModuleDoc() {
        assertNotNull(API1.backups());
        assertNotNull(API2.backups());
        assertNotNull(API3.backups());
    }

    /**
     * Test for {@link JiffyBoxApi#ips()}.
     */
    @Test
    public void testGetModuleIps() {
        assertNotNull(API1.backups());
        assertNotNull(API2.backups());
        assertNotNull(API3.backups());
    }

    /**
     * Test for {@link JiffyBoxApi#jiffyBoxes()}.
     */
    @Test
    public void testGetModuleJiffyBoxes() {
        assertNotNull(API1.backups());
        assertNotNull(API2.backups());
        assertNotNull(API3.backups());
    }

    /**
     * Test for {@link JiffyBoxApi#monitoring()};
     */
    @Test
    public void testGetModuleMonitoring() {
        assertNotNull(API1.backups());
        assertNotNull(API2.backups());
        assertNotNull(API3.backups());
    }

    /**
     * Test for {@link JiffyBoxApi#plans()}.
     */
    @Test
    public void testGetModulePlans() {
        assertNotNull(API1.backups());
        assertNotNull(API2.backups());
        assertNotNull(API3.backups());
    }
}