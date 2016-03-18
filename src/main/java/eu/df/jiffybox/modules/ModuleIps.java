package eu.df.jiffybox.modules;

import eu.df.jiffybox.models.IPSet;
import eu.df.jiffybox.models.Response;

import java.io.IOException;
import java.util.List;

/**
 * This interface describes the ips module.
 */
public interface ModuleIps {

    /**
     * To display every IP address of each JiffyBox you can use this command.
     * The result isSubnet says whether it is an IP address or a subnet. IPv6
     * subnets can be created using the control panel. The value reverseLookup
     * provides the IPs reverse DNS name. For subnets the DNS lookup can
     * optionally be delegated to a foreign name server. The result value type
     * private provides information whether the IP address is public or private
     * (non-routable). The value floating says if it is possible to move the
     * address between JiffyBoxes, e. g. to implement a failover mechanism.
     * Moving addresses is only possible for IPv4 addresses ordered additionally
     * via the control panel.
     *
     * @return All IP sets by JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<List<IPSet>> getIPSets() throws IOException;

    /**
     * Lists all IP addresses of a single JiffyBox.
     *
     * @param id Box-ID
     * @return The IP set of a specific JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<IPSet> getIPSet(final int id) throws IOException;

    /**
     * Using this command you can move IPv4 addresses between JiffyBoxes. This
     * is only possible for IPv4 addresses ordered additionally via the control.
     * The command influences solely the routing within the JiffyBox network. To
     * be able to use the on the targeted JiffyBox it has to be configured
     * within the targeted Linux normally.
     *
     * @param boxid    Box-ID
     * @param ipid     IP-ID
     * @param targetid The targeted JiffyBox to which the IP address should be
     *                 allocated.
     * @return If the moving of the IP address was successful.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Boolean> moveIPAddress(final int boxid, final int ipid, final
    int targetid) throws IOException;
}
