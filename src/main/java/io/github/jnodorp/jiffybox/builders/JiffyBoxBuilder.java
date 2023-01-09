package io.github.jnodorp.jiffybox.builders;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * This class is used to build JiffyBoxes. Use any of the methods you need and
 * get the built object using the 'build' method.
 */
public interface JiffyBoxBuilder {

    /**
     * Set the password.
     *
     * @param password When the password should not be generated randomly is can
     *                 be set using this parameter. If this parameter is not
     *                 provided the password will be generated randomly and sent
     *                 via e-mail.
     * @return The same builder for method chaining.
     */
    JiffyBoxBuilder withPassword(final String password);

    /**
     * Set whether to use a ssh key.
     *
     * @param useSshKey When this parameter is provided and there is a SSH key
     *                  saved within the control panel, this key will be granted
     *                  root access to the newly created JiffyBox.
     * @return The same builder for method chaining.
     */
    JiffyBoxBuilder useSshKey(final boolean useSshKey);

    /**
     * Set the metadata.
     *
     * @param metadata Each JiffyBox can contain optional data, which is
     *                 returned on each request. This data is saved as hash for
     *                 maximum flexibility. The value can be a complex type and
     *                 will be returned just as it has been provided. The whole
     *                 data may not exceed 4kb in size. Please notice, that this
     *                 size refers to the generated JsonObject.
     * @return The same builder for method chaining.
     */
    JiffyBoxBuilder withMetadata(final ObjectNode metadata);
}
