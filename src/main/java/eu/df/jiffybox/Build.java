package eu.df.jiffybox;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import eu.df.jiffybox.builders.JiffyBoxBuilderFactory;
import eu.df.jiffybox.builders.MonitoringCheckBuilderFactory;
import eu.df.jiffybox.internal.DefaultJiffyBoxBuilderFactory;
import eu.df.jiffybox.internal.DefaultMonitoringCheckBuilderFactory;

/**
 * This class contains a static factory method to create a monitoring check
 * builder factory. This is used to build the data needed to create or duplicate
 * monitoring checks.
 *
 * @see eu.df.jiffybox.builders.JiffyBoxBuilder
 * @see eu.df.jiffybox.builders.JiffyBoxBuilderFactory
 * @see eu.df.jiffybox.builders.MonitoringCheckBuilder
 * @see eu.df.jiffybox.builders.MonitoringCheckBuilderFactory
 */
public final class Build {

    /**
     * Disable default constructor.
     */
    private Build() {
        throw new AssertionError();
    }

    /**
     * Create a new monitoring check builder factory. All parameters may be
     * 'null' if used for duplication and the according fields need to be
     * preserved.
     *
     * @param name The monitoring checks name.
     * @param ip   The monitoring checks ip.
     * @param port The monitoring checks port.
     * @return The monitoring check builder factory.
     */
    public static MonitoringCheckBuilderFactory monitoringCheck(final String
                                                                        name,
                                                                final String
                                                                        ip,
                                                                final Integer
                                                                        port) {
        return new DefaultMonitoringCheckBuilderFactory(name, ip, port);
    }

    /**
     * Create a new JiffyBox builder factory.
     *
     * @param name   The JiffyBoxes name.
     * @param planId The plan id.
     * @return The JiffyBox builder factory.
     */
    public static JiffyBoxBuilderFactory jiffyBox(final String name, final
    String planId) {
        return new DefaultJiffyBoxBuilderFactory(name, planId);
    }

    /**
     * Create a new JiffyBox builder factory.
     *
     * @param name   The JiffyBoxes name.
     * @param planId The plan id.
     * @return The JiffyBox builder factory.
     */
    public static JiffyBoxBuilderFactory jiffyBox(final String name, final
    int planId) {
        return new DefaultJiffyBoxBuilderFactory(name, planId);
    }

    /**
     * Create a new JSON object to build the metadata.
     *
     * @return The new JSON object.
     */
    public static ObjectNode metadata() {
        return JsonNodeFactory.instance.objectNode();
    }
}
