package eu.df.jiffybox.modules;

import com.fasterxml.jackson.databind.node.ObjectNode;
import eu.df.jiffybox.builders.Finished;
import eu.df.jiffybox.builders.JiffyBoxBuilder;
import eu.df.jiffybox.models.JiffyBox;
import eu.df.jiffybox.models.Response;

import java.io.IOException;
import java.util.Map;

/**
 * This interface describes the jiffyBoxes module.
 */
public interface ModuleJiffyBoxes {

    /**
     * This method provides an overview of all set up JiffyBoxes. Since this
     * method is rather time consuming continuous usage is deprecated. Use the
     * more specific call for a single JiffyBox instead.
     *
     * @return The retrieved JiffyBoxes.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Map<String, JiffyBox>> getJiffyBoxes() throws IOException;

    /**
     * This method provides details of a single JiffyBox. The result is
     * equivalent to a single JiffyBox from get() but if there is more than one
     * JiffyBox the execution speed is significantly higher.
     *
     * @param id Box-ID
     * @return The retrieved JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> getJiffyBox(final int id) throws IOException;

    /**
     * This method deletes a specified JiffyBox. This command is asynchronous .
     * The result is true on success and false otherwise. Success just means
     * that the request for deletion was successful. The deletion itself happens
     * in an asynchronous manner. The deletions state can be obtained by reading
     * the JiffyBox. As soon as the notice occurs, that the JiffyBox no longer
     * exists, the deletion was successful. During the deletion the JiffyBoxes
     * status is DELETING. In case the status switches back to READY the
     * deletion failed.
     *
     * @param id Box-ID
     * @return If the deletion was successfully initiated.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Boolean> deleteJiffyBox(final int id) throws IOException;

    /**
     * This method creates a JiffyBox. The JiffyBox is created in an
     * asynchronous manner. A success as a result solely means, that the
     * creation was initialized. The result can be obtained by get(int id). id
     * is contained in this methods result. During the creation the JiffyBoxes
     * status is set to CREATING. As soon as the JiffyBox is set up the state
     * changes to READY. On failure the JiffyBox is removed automatically.
     *
     * @param data The JSON data from the builder.
     * @return The created JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> createJiffyBox(final Finished<JiffyBoxBuilder> data)
            throws IOException;

    /**
     * This method duplicates an existing JiffyBox. The JiffyBox is created in
     * an asynchronous manner. A success as a result solely means, that the
     * creation was initialized. The result can be obtained by get(int id). id
     * is contained in this methods result. During the creation the JiffyBoxes
     * status is set to CREATING. As soon as the JiffyBox is set up the state
     * changes to READY. On failure the JiffyBox is removed automatically.
     *
     * @param id     The id of the JiffyBox to duplicate.
     * @param name   The name of the JiffyBoxes to create. Up to 30 characters
     *               are allowed. Allowed characters are {@code
     *               a-zA-Z0-9üöäÜÖÄß_()=!\*@.-} and spaces. The JiffyBoxes name
     *               has to be unique.
     * @param planId The plan of a JiffyBox is either an unique number or the
     *               plans name (CloudLevel 1, CloudLevel 2 etc.). The IDs and
     *               other details of the plan can be obtained via the plans
     *               module.
     * @return The duplicated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> duplicateJiffyBox(final int id, final String name,
                                         final int planId) throws IOException;

    /**
     * This method duplicates an existing JiffyBox. The JiffyBox is created in
     * an asynchronous manner. A success as a result solely means, that the
     * creation was initialized. The result can be obtained by get(int id). id
     * is contained in this methods result. During the creation the JiffyBoxes
     * status is set to CREATING. As soon as the JiffyBox is set up the state
     * changes to READY. On failure the JiffyBox is removed automatically.
     *
     * @param id     The id of the JiffyBox to duplicate.
     * @param name   The name of the JiffyBoxes to create. Up to 30 characters
     *               are allowed. Allowed characters are {@code
     *               a-zA-Z0-9üöäÜÖÄß_()=!\*@.-} and spaces. The JiffyBoxes name
     *               has to be unique.
     * @param planId The plan of a JiffyBox is either an unique number or the
     *               plans name (CloudLevel 1, CloudLevel 2 etc.). The IDs and
     *               other details of the plan can be obtained via the plans
     *               module.
     * @return The duplicated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> duplicateJiffyBox(final int id, final String name,
                                         final String planId) throws
            IOException;

    /**
     * This method duplicates an existing JiffyBox. The JiffyBox is created in
     * an asynchronous manner. A success as a result solely means, that the
     * creation was initialized. The result can be obtained by get(int id). id
     * is contained in this methods result. During the creation the JiffyBoxes
     * status is set to CREATING. As soon as the JiffyBox is set up the state
     * changes to READY. On failure the JiffyBox is removed automatically.
     *
     * @param id       The id of the JiffyBox to duplicate.
     * @param name     The name of the JiffyBoxes to create. Up to 30 characters
     *                 are allowed. Allowed characters are {@code
     *                 a-zA-Z0-9üöäÜÖÄß_()=!\*@.-} and spaces. The JiffyBoxes
     *                 name has to be unique.
     * @param planId   The plan of a JiffyBox is either an unique number or the
     *                 plans name (CloudLevel 1, CloudLevel 2 etc.). The IDs and
     *                 other details of the plan can be obtained via the plans
     *                 module.
     * @param metadata Each JiffyBox can contain optional data, which is
     *                 returned on each request. This data is saved as hash for
     *                 maximum flexibility. The value can be a complex type and
     *                 will be returned just as it has been provided. The whole
     *                 data may not exceed 4kb in size. Please notice, that this
     *                 size refers to the generated JsonObject.
     * @return The duplicated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> duplicateJiffyBox(final int id, final String name,
                                         final int planId, final ObjectNode
            metadata) throws IOException;

    /**
     * This method duplicates an existing JiffyBox. The JiffyBox is created in
     * an asynchronous manner. A success as a result solely means, that the
     * creation was initialized. The result can be obtained by get(int id). id
     * is contained in this methods result. During the creation the JiffyBoxes
     * status is set to CREATING. As soon as the JiffyBox is set up the state
     * changes to READY. On failure the JiffyBox is removed automatically.
     *
     * @param id       The id of the JiffyBox to duplicate.
     * @param name     The name of the JiffyBoxes to create. Up to 30 characters
     *                 are allowed. Allowed characters are {@code
     *                 a-zA-Z0-9üöäÜÖÄß_()=!\*@.-} and spaces. The JiffyBoxes
     *                 name has to be unique.
     * @param planId   The plan of a JiffyBox is either an unique number or the
     *                 plans name (CloudLevel 1, CloudLevel 2 etc.). The IDs and
     *                 other details of the plan can be obtained via the plans
     *                 module.
     * @param metadata Each JiffyBox can contain optional data, which is
     *                 returned on each request. This data is saved as hash for
     *                 maximum flexibility. The value can be a complex type and
     *                 will be returned just as it has been provided. The whole
     *                 data may not exceed 4kb in size. Please notice, that this
     *                 size refers to the generated JsonObject.
     * @return The duplicated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> duplicateJiffyBox(final int id, final String name,
                                         final String planId, final
    ObjectNode metadata) throws IOException;

    /**
     * This method starts a JiffyBox. This command acts in an entirely
     * asynchronous manner. To change a JiffyBoxes plan an PUT request is
     * required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id Box-ID
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> startJiffyBox(final int id) throws IOException;

    /**
     * This method starts a JiffyBox. This command acts in an entirely
     * asynchronous manner. To change a JiffyBoxes plan an PUT request is
     * required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id       Box-ID
     * @param metadata The meta data belonging to a JiffyBox can be changed or
     *                 extended. Meta data is provided as Map&lt;String,
     *                 Object&gt;. It is saved exactly in the same way. In case
     *                 a keys value is NULL the according entry will be deleted.
     *                 The values of keys not submitted will not change. The
     *                 entire meta data may not exceed 4 kB. Please notice, that
     *                 the space is referring to the generated JSON string.
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> startJiffyBox(final int id, final ObjectNode metadata)
            throws IOException;

    /**
     * This method invokes a shutdown on a JiffyBox. This command acts in an
     * entirely asynchronous manner. To change a JiffyBoxes plan an PUT request
     * is required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id Box-ID
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> shutdownJiffyBox(final int id) throws IOException;

    /**
     * This method invokes a shutdown on a JiffyBox. This command acts in an
     * entirely asynchronous manner. To change a JiffyBoxes plan an PUT request
     * is required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id       Box-ID
     * @param metadata The meta data belonging to a JiffyBox can be changed or
     *                 extended. Meta data is provided as Map&lt;String,
     *                 Object&gt;. It is saved exactly in the same way. In case
     *                 a keys value is NULL the according entry will be deleted.
     *                 The values of keys not submitted will not change. The
     *                 entire meta data may not exceed 4 kB. Please notice, that
     *                 the space is referring to the generated JSON string.
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> shutdownJiffyBox(final int id, final ObjectNode
            metadata) throws IOException;

    /**
     * This method invokes a 'pull plug' on a JiffyBox. This command acts in an
     * entirely asynchronous manner. To change a JiffyBoxes plan an PUT request
     * is required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id Box-ID
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> pullplugJiffyBox(final int id) throws IOException;

    /**
     * This method invokes a 'pull plug' on a JiffyBox. This command acts in an
     * entirely asynchronous manner. To change a JiffyBoxes plan an PUT request
     * is required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id       Box-ID
     * @param metadata The meta data belonging to a JiffyBox can be changed or
     *                 extended. Meta data is provided as Map&lt;String,
     *                 Object&gt;. It is saved exactly in the same way. In case
     *                 a keys value is NULL the according entry will be deleted.
     *                 The values of keys not submitted will not change. The
     *                 entire meta data may not exceed 4 kB. Please notice, that
     *                 the space is referring to the generated JSON string.
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> pullplugJiffyBox(final int id, final ObjectNode
            metadata) throws IOException;

    /**
     * This method freezes a JiffyBox. This command acts in an entirely
     * asynchronous manner. To change a JiffyBoxes plan an PUT request is
     * required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id Box-ID
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> freezeJiffyBox(final int id) throws IOException;

    /**
     * This method freezes a JiffyBox. This command acts in an entirely
     * asynchronous manner. To change a JiffyBoxes plan an PUT request is
     * required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id       Box-ID
     * @param metadata The meta data belonging to a JiffyBox can be changed or
     *                 extended. Meta data is provided as Map&lt;String,
     *                 Object&gt;. It is saved exactly in the same way. In case
     *                 a keys value is NULL the according entry will be deleted.
     *                 The values of keys not submitted will not change. The
     *                 entire meta data may not exceed 4 kB. Please notice, that
     *                 the space is referring to the generated JSON string.
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> freezeJiffyBox(final int id, final ObjectNode
            metadata) throws IOException;

    /**
     * This method thaws a JiffyBox. This command acts in an entirely
     * asynchronous manner. To change a JiffyBoxes plan an PUT request is
     * required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id     Box-ID
     * @param planId The plan of a JiffyBox is either an unique number or the
     *               plans name (CloudLevel 1, CloudLevel 2 etc.). The IDs and
     *               other details of the plan can be obtained via the plans
     *               module.
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> thawJiffyBox(final int id, final int planId) throws
            IOException;

    /**
     * This method thaws a JiffyBox. This command acts in an entirely
     * asynchronous manner. To change a JiffyBoxes plan an PUT request is
     * required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id     Box-ID
     * @param planId The plan of a JiffyBox is either an unique number or the
     *               plans name (CloudLevel 1, CloudLevel 2 etc.). The IDs and
     *               other details of the plan can be obtained via the plans
     *               module.
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> thawJiffyBox(final int id, final String planId) throws
            IOException;

    /**
     * This method thaws a JiffyBox. This command acts in an entirely
     * asynchronous manner. To change a JiffyBoxes plan an PUT request is
     * required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id       Box-ID
     * @param planId   The plan of a JiffyBox is either an unique number or the
     *                 plans name (CloudLevel 1, CloudLevel 2 etc.). The IDs and
     *                 other details of the plan can be obtained via the plans
     *                 module.
     * @param metadata The meta data belonging to a JiffyBox can be changed or
     *                 extended. Meta data is provided as Map&lt;String,
     *                 Object&gt;. It is saved exactly in the same way. In case
     *                 a keys value is NULL the according entry will be deleted.
     *                 The values of keys not submitted will not change. The
     *                 entire meta data may not exceed 4 kB. Please notice, that
     *                 the space is referring to the generated JSON string.
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> thawJiffyBox(final int id, final int planId, final
    ObjectNode metadata) throws IOException;

    /**
     * This method thaws a JiffyBox. This command acts in an entirely
     * asynchronous manner. To change a JiffyBoxes plan an PUT request is
     * required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id       Box-ID
     * @param planId   The plan of a JiffyBox is either an unique number or the
     *                 plans name (CloudLevel 1, CloudLevel 2 etc.). The IDs and
     *                 other details of the plan can be obtained via the plans
     *                 module.
     * @param metadata The meta data belonging to a JiffyBox can be changed or
     *                 extended. Meta data is provided as Map&lt;String,
     *                 Object&gt;. It is saved exactly in the same way. In case
     *                 a keys value is NULL the according entry will be deleted.
     *                 The values of keys not submitted will not change. The
     *                 entire meta data may not exceed 4 kB. Please notice, that
     *                 the space is referring to the generated JSON string.
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> thawJiffyBox(final int id, final String planId, final
    ObjectNode metadata) throws IOException;

    /**
     * This method changes the plan of a JiffyBox. This command acts in an
     * entirely asynchronous manner. To change a JiffyBoxes plan an PUT request
     * is required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id     Box-ID
     * @param planId The plan of a JiffyBox is either an unique number or the
     *               plans name (CloudLevel 1, CloudLevel 2 etc.). The IDs and
     *               other details of the plan can be obtained via the plans
     *               module.
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> changePlanJiffyBox(final int id, final int planId)
            throws IOException;

    /**
     * This method changes the plan of a JiffyBox. This command acts in an
     * entirely asynchronous manner. To change a JiffyBoxes plan an PUT request
     * is required as well. It sets the new plan. The modified values are status
     * and planId. This command is executed entirely asynchronous.
     *
     * @param id     Box-ID
     * @param planId The plan of a JiffyBox is either an unique number or the
     *               plans name (CloudLevel 1, CloudLevel 2 etc.). The IDs and
     *               other details of the plan can be obtained via the plans
     *               module.
     * @return The updated JiffyBox.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<JiffyBox> changePlanJiffyBox(final int id, final String planId)
            throws IOException;
}
