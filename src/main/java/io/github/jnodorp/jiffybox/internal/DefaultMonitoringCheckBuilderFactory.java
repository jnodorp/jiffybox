package io.github.jnodorp.jiffybox.internal;

import io.github.jnodorp.jiffybox.builders.MonitoringCheckBuilder;
import io.github.jnodorp.jiffybox.builders.MonitoringCheckBuilderFactory;
import io.github.jnodorp.jiffybox.models.MonitoringCheckType;

/**
 * This class is used to create monitoring check builder of different types. Use
 * any of the methods you need and get the builder.
 */
public class DefaultMonitoringCheckBuilderFactory implements
        MonitoringCheckBuilderFactory {

    /**
     * The parameter 'domainname'.
     */
    private static final String PARAMETER_DOMAIN_NAME = "domainname";

    /**
     * The parameter 'username'.
     */
    private static final String PARAMETER_USERNAME = "username";

    /**
     * The parameter 'path'.
     */
    private static final String PARAMETER_PATH = "path";

    /**
     * The parameter 'password'.
     */
    private static final String PARAMETER_PASSWORD = "password";

    /**
     * The parameter 'ssl'.
     */
    private static final String PARAMETER_SSL = "ssl";

    /**
     * The parameter 'sendMsg'.
     */
    private static final String PARAMETER_SEND_MSG = "sendMsg";

    /**
     * The parameter 'expectMsg'.
     */
    private static final String PARAMETER_EXPECT_MSG = "expectMsg";

    /**
     * The parameter 'dnsType'.
     */
    private static final String PARAMETER_DNS_TYPE = "dnsType";

    /**
     * The parameter 'zone'.
     */
    private static final String PARAMETER_ZONE = "zone";

    /**
     * The parameter 'expectedAnswer'.
     */
    private static final String PARAMETER_EXPECTED_ANSWER = "expectedAnswer";

    /**
     * The monitoring checks name.
     */
    private final String name;

    /**
     * The monitoring checks ip.
     */
    private final String ip;

    /**
     * The monitoring checks port.
     */
    private final Integer port;

    /**
     * Create a new default monitoring check builder factory using the given
     * parameters for each created monitoring check.
     *
     * @param name The name.
     * @param ip   The ip.
     * @param port The port.
     */
    public DefaultMonitoringCheckBuilderFactory(final String name, final
    String ip, final Integer port) {
        this.name = name;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public MonitoringCheckBuilder preserveType() {
        return new DefaultMonitoringCheckBuilder(this.name, this.ip, null,
                this.port);
    }

    @Override
    public MonitoringCheckBuilder ping() {
        return new DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.PING, this.port);
    }

    @Override
    public MonitoringCheckBuilder portTcp() {
        return new DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.PORT_TCP, this.port);
    }

    @Override
    public MonitoringCheckBuilder portUdp(String sendMsg, String expectMsg) {
        DefaultMonitoringCheckBuilder result = new
                DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.PORT_UDP, this.port);
        result.put(PARAMETER_SEND_MSG, sendMsg);
        result.put(PARAMETER_EXPECT_MSG, expectMsg);
        return result;
    }

    @Override
    public MonitoringCheckBuilder http(String domainname, String path) {
        DefaultMonitoringCheckBuilder result = new
                DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.HTTP, this.port);
        result.put(PARAMETER_DOMAIN_NAME, domainname);
        result.put(PARAMETER_PATH, path);
        return result;
    }

    @Override
    public MonitoringCheckBuilder http(String domainname, String path, String
            username, String password) {
        DefaultMonitoringCheckBuilder result = new
                DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.HTTP, this.port);
        result.put(PARAMETER_DOMAIN_NAME, domainname);
        result.put(PARAMETER_PATH, path);
        result.put(PARAMETER_USERNAME, username);
        result.put(PARAMETER_PASSWORD, password);
        return result;
    }

    @Override
    public MonitoringCheckBuilder https(String domainname, String path) {
        DefaultMonitoringCheckBuilder result = new
                DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.HTTPS, this.port);
        result.put(PARAMETER_DOMAIN_NAME, domainname);
        result.put(PARAMETER_PATH, path);
        return result;
    }

    @Override
    public MonitoringCheckBuilder https(String domainname, String path,
                                        String username, String password) {
        DefaultMonitoringCheckBuilder result = new
                DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.HTTPS, this.port);
        result.put(PARAMETER_DOMAIN_NAME, domainname);
        result.put(PARAMETER_PATH, path);
        result.put(PARAMETER_USERNAME, username);
        result.put(PARAMETER_PASSWORD, password);
        return result;
    }

    @Override
    public MonitoringCheckBuilder smtp(String username, String password) {
        DefaultMonitoringCheckBuilder result = new
                DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.SMTP, this.port);
        result.put(PARAMETER_USERNAME, username);
        result.put(PARAMETER_PASSWORD, password);
        return result;
    }

    @Override
    public MonitoringCheckBuilder pop3(String username, String password) {
        DefaultMonitoringCheckBuilder result = new
                DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.POP3, this.port);
        result.put(PARAMETER_USERNAME, username);
        result.put(PARAMETER_PASSWORD, password);
        return result;
    }

    @Override
    public MonitoringCheckBuilder pop3(String username, String password,
                                       boolean ssl) {
        DefaultMonitoringCheckBuilder result = new
                DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.POP3, this.port);
        result.put(PARAMETER_USERNAME, username);
        result.put(PARAMETER_PASSWORD, password);
        result.put(PARAMETER_SSL, ssl);
        return result;
    }

    @Override
    public MonitoringCheckBuilder imap(String username, String password) {
        DefaultMonitoringCheckBuilder result = new
                DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.IMAP, this.port);
        result.put(PARAMETER_USERNAME, username);
        result.put(PARAMETER_PASSWORD, password);
        return result;
    }

    @Override
    public MonitoringCheckBuilder imap(String username, String password,
                                       boolean ssl) {
        DefaultMonitoringCheckBuilder result = new
                DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.IMAP, this.port);
        result.put(PARAMETER_USERNAME, username);
        result.put(PARAMETER_PASSWORD, password);
        result.put(PARAMETER_SSL, ssl);
        return result;
    }

    @Override
    public MonitoringCheckBuilder ftp() {
        return new DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.FTP, this.port);
    }

    @Override
    public MonitoringCheckBuilder ftp(String username, String password) {
        DefaultMonitoringCheckBuilder result = new
                DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.FTP, this.port);
        result.put(PARAMETER_USERNAME, username);
        result.put(PARAMETER_PASSWORD, password);
        return result;
    }

    @Override
    public MonitoringCheckBuilder ssh() {
        return new DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.SSH, this.port);
    }

    @Override
    public MonitoringCheckBuilder mysql(String username, String password) {
        DefaultMonitoringCheckBuilder result = new
                DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.MYSQL, this.port);
        result.put(PARAMETER_USERNAME, username);
        result.put(PARAMETER_PASSWORD, password);
        return result;
    }

    @Override
    public MonitoringCheckBuilder dns(String dnsType, String zone, String
            expectedAnswer) {
        DefaultMonitoringCheckBuilder result = new
                DefaultMonitoringCheckBuilder(this.name, this.ip,
                MonitoringCheckType.DNS, this.port);
        result.put(PARAMETER_DNS_TYPE, dnsType);
        result.put(PARAMETER_ZONE, zone);
        result.put(PARAMETER_EXPECTED_ANSWER, expectedAnswer);
        return result;
    }
}
