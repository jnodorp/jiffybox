package eu.df.jiffybox.builders;

/**
 * This class is used to create monitoring check builder of different types. Use
 * any of the methods you need and get the builder.
 */
public interface MonitoringCheckBuilderFactory {

    /**
     * Get a monitoring check builder which will preserve the previous type on
     * duplication.
     *
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder preserveType();

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'ping'.
     *
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder ping();

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'portTcp'.
     *
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder portTcp();

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'portUdp'.
     *
     * @param sendMsg   The message to send.
     * @param expectMsg The expected response.
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder portUdp(final String sendMsg, final String
            expectMsg);

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'http'.
     *
     * @param domainname The domainname.
     * @param path       The path.
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder http(final String domainname, final String path);

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'http'.
     *
     * @param domainname The domainname.
     * @param path       The path.
     * @param username   The username.
     * @param password   The password.
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder http(final String domainname, final String path,
                                final String username, final String password);

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'https'.
     *
     * @param domainname The domainname.
     * @param path       The path.
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder https(final String domainname, final String path);

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'https'.
     *
     * @param domainname The domainname.
     * @param path       The path.
     * @param username   The username.
     * @param password   The password.
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder https(final String domainname, final String path,
                                 final String username, final String password);

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'smtp'.
     *
     * @param username The username.
     * @param password The password.
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder smtp(final String username, final String password);

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'pop3'.
     *
     * @param username The username.
     * @param password The password.
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder pop3(final String username, final String password);

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'pop3'.
     *
     * @param username The username.
     * @param password The password.
     * @param ssl      Use SSL?
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder pop3(final String username, final String password,
                                final boolean ssl);

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'imap'.
     *
     * @param username The username.
     * @param password The password.
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder imap(final String username, final String password);

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'imap'.
     *
     * @param username The username.
     * @param password The password.
     * @param ssl      Use SSL?
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder imap(final String username, final String password,
                                final boolean ssl);

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'ftp'.
     *
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder ftp();

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'ftp'.
     *
     * @param username The username.
     * @param password The password.
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder ftp(final String username, final String password);

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'ssh'.
     *
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder ssh();

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'mysql'.
     *
     * @param username The username.
     * @param password The password.
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder mysql(final String username, final String password);

    /**
     * Get a monitoring check builder to create a monitoring check of the type
     * 'dns'.
     *
     * @param dnsType        The DNS type.
     * @param zone           The DNS zone.
     * @param expectedAnswer The expected response.
     * @return A monitoring check builder.
     */
    MonitoringCheckBuilder dns(final String dnsType, final String zone, final
    String expectedAnswer);
}
