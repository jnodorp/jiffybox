package io.github.jnodorp.jiffybox.internal;

import io.github.jnodorp.jiffybox.builders.MonitoringCheckBuilderFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link DefaultMonitoringCheckBuilderFactory}.
 */
class DefaultMonitoringCheckBuilderFactoryTest {

    private final static MonitoringCheckBuilderFactory FACTORY = new DefaultMonitoringCheckBuilderFactory("", "", 0);

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#preserveType()}.
     */
    @Test
    void testPreserveType() {
        assertFalse(FACTORY.preserveType().toString().contains("checkType"));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#ping()}.
     */
    @Test
    void testPing() {
        assertTrue(FACTORY.ping().toString().contains("\"checkType\":\"ping\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#portTcp()}.
     */
    @Test
    void testPortTcp() {
        assertTrue(FACTORY.portTcp().toString().contains("\"checkType\":\"portTcp\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#portUdp(String, String)}.
     */
    @Test
    void testPortUdp() {
        assertTrue(FACTORY.portUdp("a", "b").toString().contains("\"checkType\":\"portUdp\""));
        assertTrue(FACTORY.portUdp("a", "b").toString().contains("\"sendMsg\":\"a\""));
        assertTrue(FACTORY.portUdp("a", "b").toString().contains("\"expectMsg\":\"b\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#http(String, String)}.
     */
    @Test
    void testHttp() {
        assertTrue(FACTORY.http("domain", "path").toString().contains("\"checkType\":\"http\""));
        assertTrue(FACTORY.http("domain", "path").toString().contains("\"domainname\":\"domain\""));
        assertTrue(FACTORY.http("domain", "path").toString().contains("\"path\":\"path\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#http(String, String, String, String)}.
     */
    @Test
    void testHttp1() {
        assertTrue(FACTORY.http("domain", "path", "user", "pass").toString().contains("\"checkType\":\"http\""));
        assertTrue(FACTORY.http("domain", "path", "user", "pass").toString().contains("\"domainname\":\"domain\""));
        assertTrue(FACTORY.http("domain", "path", "user", "pass").toString().contains("\"path\":\"path\""));
        assertTrue(FACTORY.http("domain", "path", "user", "pass").toString().contains("\"username\":\"user\""));
        assertTrue(FACTORY.http("domain", "path", "user", "pass").toString().contains("\"password\":\"pass\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#https(String, String)}.
     */
    @Test
    void testHttps() {
        assertTrue(FACTORY.https("domain", "path").toString().contains("\"checkType\":\"https\""));
        assertTrue(FACTORY.https("domain", "path").toString().contains("\"domainname\":\"domain\""));
        assertTrue(FACTORY.https("domain", "path").toString().contains("\"path\":\"path\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#https(String, String, String, String)}.
     */
    @Test
    void testHttps1() {
        assertTrue(FACTORY.https("domain", "path", "user", "pass").toString().contains("\"checkType\":\"https\""));
        assertTrue(FACTORY.https("domain", "path", "user", "pass").toString().contains("\"domainname\":\"domain\""));
        assertTrue(FACTORY.https("domain", "path", "user", "pass").toString().contains("\"path\":\"path\""));
        assertTrue(FACTORY.https("domain", "path", "user", "pass").toString().contains("\"username\":\"user\""));
        assertTrue(FACTORY.https("domain", "path", "user", "pass").toString().contains("\"password\":\"pass\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#smtp(String, String)}.
     */
    @Test
    void testSmtp() {
        assertTrue(FACTORY.smtp("user", "pass").toString().contains("\"checkType\":\"smtp\""));
        assertTrue(FACTORY.smtp("user", "pass").toString().contains("\"username\":\"user\""));
        assertTrue(FACTORY.smtp("user", "pass").toString().contains("\"password\":\"pass\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#pop3(String, String)}.
     */
    @Test
    void testPop3() {
        assertTrue(FACTORY.pop3("user", "pass").toString().contains("\"checkType\":\"pop3\""));
        assertTrue(FACTORY.pop3("user", "pass").toString().contains("\"username\":\"user\""));
        assertTrue(FACTORY.pop3("user", "pass").toString().contains("\"password\":\"pass\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#pop3(String, String, boolean)}.
     */
    @Test
    void testPop31() {
        assertTrue(FACTORY.pop3("user", "pass", true).toString().contains("\"checkType\":\"pop3\""));
        assertTrue(FACTORY.pop3("user", "pass", true).toString().contains("\"username\":\"user\""));
        assertTrue(FACTORY.pop3("user", "pass", true).toString().contains("\"password\":\"pass\""));
        assertTrue(FACTORY.pop3("user", "pass", true).toString().contains("\"ssl\":true"));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#imap(String, String)}.
     */
    @Test
    void testImap() {
        assertTrue(FACTORY.imap("user", "pass").toString().contains("\"checkType\":\"imap\""));
        assertTrue(FACTORY.imap("user", "pass").toString().contains("\"username\":\"user\""));
        assertTrue(FACTORY.imap("user", "pass").toString().contains("\"password\":\"pass\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#imap(String, String, boolean)}.
     */
    @Test
    void testImap1() {
        assertTrue(FACTORY.imap("user", "pass", true).toString().contains("\"checkType\":\"imap\""));
        assertTrue(FACTORY.imap("user", "pass", true).toString().contains("\"username\":\"user\""));
        assertTrue(FACTORY.imap("user", "pass", true).toString().contains("\"password\":\"pass\""));
        assertTrue(FACTORY.imap("user", "pass", true).toString().contains("\"ssl\":true"));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#ftp()}.
     */
    @Test
    void testFtp() {
        assertTrue(FACTORY.ftp().toString().contains("\"checkType\":\"ftp\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#ftp(String, String)}.
     */
    @Test
    void testFtp1() {
        assertTrue(FACTORY.ftp("user", "pass").toString().contains("\"checkType\":\"ftp\""));
        assertTrue(FACTORY.ftp("user", "pass").toString().contains("\"username\":\"user\""));
        assertTrue(FACTORY.ftp("user", "pass").toString().contains("\"password\":\"pass\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#ssh()}.
     */
    @Test
    void testSsh() {
        assertTrue(FACTORY.ssh().toString().contains("\"checkType\":\"ssh\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#mysql(String, String)}.
     */
    @Test
    void testMysql() {
        assertTrue(FACTORY.mysql("user", "pass").toString().contains("\"checkType\":\"mysql\""));
        assertTrue(FACTORY.mysql("user", "pass").toString().contains("\"username\":\"user\""));
        assertTrue(FACTORY.mysql("user", "pass").toString().contains("\"password\":\"pass\""));
    }

    /**
     * Test method for {@link DefaultMonitoringCheckBuilderFactory#dns(String, String, String)}.
     */
    @Test
    void testDns() {
        assertTrue(FACTORY.dns("t", "zone", "a").toString().contains("\"checkType\":\"dns\""));
        assertTrue(FACTORY.dns("t", "zone", "a").toString().contains("\"dnsType\":\"t\""));
        assertTrue(FACTORY.dns("t", "zone", "a").toString().contains("\"zone\":\"zone\""));
        assertTrue(FACTORY.dns("t", "zone", "a").toString().contains("\"expectedAnswer\":\"a\""));
    }
}
