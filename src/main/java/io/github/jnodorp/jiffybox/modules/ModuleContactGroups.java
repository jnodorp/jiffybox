package io.github.jnodorp.jiffybox.modules;

import io.github.jnodorp.jiffybox.models.ContactGroup;
import io.github.jnodorp.jiffybox.models.Response;
import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.List;
import java.util.Map;

/**
 * This interface describes the contactGroups module.
 */
public interface ModuleContactGroups {

    /**
     * Build the module.
     *
     * @param baseUrl the base URL
     * @return the module
     */
    static ModuleContactGroups build(String baseUrl) {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(ModuleContactGroups.class, baseUrl);
    }

    /**
     * With this call you get an overview of all contact groups.
     *
     * @return The retrieved contact groups.
     */
    @RequestLine("GET /contactGroups")
    Response<Map<String, ContactGroup>> getContactGroups();

    /**
     * Provides details regarding a specific contact group. The result is
     * equivalent to a single entry of the get() method.
     *
     * @param id Group-ID
     * @return The retrieved contact group.
     */
    @RequestLine("GET /contactGroups/{id}")
    Response<ContactGroup> getContactGroup(@Param("id") int id);

    /**
     * Using this command you are able to delete contact groups. This command is
     * asynchronous. On success the result will be true; false otherwise. A
     * success solely means the deletion was requested successfully. The
     * deletion itself is executed asynchronously. The state of deletion can be
     * queried by reading the contact group. As soon as the notice occurs, that
     * the contact group does not exist, the deletion was successful. During the
     * deletion the contact groups state is STATUS_DELETING / STATUS_DELETED. In
     * case this switches back to STATUS_READY the deletion failed.
     *
     * @param id Group-ID
     * @return If the contact group has been deleted successfully.
     */
    @RequestLine("DELETE /contactGroups/{id}")
    Response<Boolean> deleteContactGroup(@Param("id") int id);

    /**
     * There are two possibilities to create contact groups: 1. Create a new
     * contact group from scratch. 2. Duplicate an existing contact group. The
     * creation of a contact group is an asynchronous process. The result can
     * only be obtained using periodical GET requests on the created contact
     * group. The contact groups id is handled in the result of the creation.
     * During the creation the status field is set to STATUS_CREATING. As soon
     * as the contact group is set up, the status switches to STATUS_READY. On
     * failure the process is automatically repeated one time. In case the
     * creation still fails the status is set to STATUS_ERROR.
     *
     * @param name     The contact groups name. Up to 30 characters are allowed.
     *                 Valid characters are: a-zA-Z0-9üöäÜÖÄß_()=!\*@.- and
     *                 spaces.
     * @param contacts An array of up to ten e-mail addresses.
     * @return The created contact group.
     */
    @RequestLine("POST /contactGroups")
    Response<ContactGroup> createContactGroup(@Param("name") String name, @Param("contacts") List<String> contacts);

    /**
     * With this command contact groups (except "Stammdaten-E-Mail-Addresse")
     * can be changed. The behavior of the field status is the same as during
     * the creation.
     *
     * @param id       ContactGroup-ID
     * @param name     The contact groups name. Up to 30 characters are allowed.
     *                 Valid characters are: a-zA-Z0-9üöäÜÖÄß_()=!\*@.- and
     *                 spaces.
     * @param contacts An array of up to ten e-mail addresses.
     * @return The updated contact group.
     */
    @RequestLine("PUT /contactGroups/{id}")
    Response<ContactGroup> updateContactGroup(@Param("id") int id, @Param("name") String name, @Param("contacts")
            List<String> contacts);

    /**
     * With this command contact groups (except "Stammdaten-E-Mail-Addresse")
     * can be changed. The behavior of the field status is the same as during
     * the creation.
     *
     * @param id   ContactGroup-ID
     * @param name The contact groups name. Up to 30 characters are allowed.
     *             Valid characters are: a-zA-Z0-9üöäÜÖÄß_()=!\*@.- and spaces.
     * @return The updated contact group.
     */
    @RequestLine("PUT /contactGroups/{id}")
    Response<ContactGroup> updateContactGroup(@Param("id") int id, @Param("name") String name);

    /**
     * With this command contact groups (except "Stammdaten-E-Mail-Addresse")
     * can be changed. The behavior of the field status is the same as during
     * the creation.
     *
     * @param id       ContactGroup-ID
     * @param contacts An array of up to ten e-mail addresses.
     * @return The updated contact group.
     */
    @RequestLine("PUT /contactGroups/{id}")
    Response<ContactGroup> updateContactGroup(@Param("id") int id, @Param("contacts") List<String> contacts);

    /**
     * The duplication of contact groups needs the id of the contact group to
     * duplicate as parameter. To duplicate a group at least one parameter needs
     * to be changed. Typically this is the name.
     *
     * @param id       Group-ID
     * @param name     The contact groups name. Up to 30 characters are allowed.
     *                 Valid characters are: a-zA-Z0-9üöäÜÖÄß_()=!\*@.- and
     *                 spaces.
     * @param contacts An array of up to ten e-mail addresses.
     * @return The duplicated contact group.
     */
    @RequestLine("POST /contactGroups/{id}")
    Response<ContactGroup> duplicateContactGroup(@Param("id") int id, @Param("name") String name, @Param("contacts")
            List<String> contacts);

    /**
     * The duplication of contact groups needs the id of the contact group to
     * duplicate as parameter. To duplicate a group at least one parameter needs
     * to be changed. Typically this is the name.
     *
     * @param id   Group-ID
     * @param name The contact groups name. Up to 30 characters are allowed.
     *             Valid characters are: a-zA-Z0-9üöäÜÖÄß_()=!\*@.- and spaces.
     * @return The duplicated contact group.
     */
    @RequestLine("POST /contactGroups/{id}")
    Response<ContactGroup> duplicateContactGroup(@Param("id") int id, @Param("name") String name);
}
