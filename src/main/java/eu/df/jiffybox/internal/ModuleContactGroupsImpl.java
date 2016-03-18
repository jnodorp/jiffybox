package eu.df.jiffybox.internal;

import eu.df.jiffybox.models.ContactGroup;
import eu.df.jiffybox.models.Response;
import eu.df.jiffybox.modules.ModuleContactGroups;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the contact groups module.
 *
 * @see eu.df.jiffybox.modules.ModuleContactGroups
 */
public class ModuleContactGroupsImpl implements ModuleContactGroups {

    /**
     * Parameter 'name'.
     */
    private static final String PARAMETER_NAME = "name";

    /**
     * The base URI.
     */
    private final URI baseUri;

    /**
     * Create an instance of this module using the specified base URI.
     *
     * @param baseUri The base URI to use.
     */
    public ModuleContactGroupsImpl(final URI baseUri) {
        this.baseUri = URI.create(baseUri + "/contactGroups");
    }

    @Override
    public Response<List<ContactGroup>> getContactGroups() throws
            IOException {
        return ApiCall.get(baseUri).asList(ContactGroup.class);
    }

    @Override
    public Response<ContactGroup> getContactGroup(int id) throws IOException {
        return ApiCall.get(baseUri).appendPath(id).as(ContactGroup.class);
    }

    @Override
    public Response<Boolean> deleteContactGroup(int id) throws IOException {
        return ApiCall.delete(baseUri).appendPath(id).as(Boolean.class);
    }

    @Override
    public Response<ContactGroup> createContactGroup(String name,
                                                     List<String> contacts)
            throws IOException {
        return ApiCall.post(baseUri)
                      .addParameter(PARAMETER_NAME, name)
                      .addContacts(contacts)
                      .as(ContactGroup.class);
    }

    @Override
    public Response<ContactGroup> updateContactGroup(int id, String name,
                                                     List<String> contacts)
            throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_NAME, name)
                      .addContacts(contacts)
                      .as(ContactGroup.class);
    }

    @Override
    public Response<ContactGroup> updateContactGroup(int id, String name)
            throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_NAME, name)
                      .as(ContactGroup.class);
    }

    @Override
    public Response<ContactGroup> updateContactGroup(int id, List<String>
            contacts) throws IOException {
        return ApiCall.put(baseUri)
                      .appendPath(id)
                      .addContacts(contacts)
                      .as(ContactGroup.class);
    }

    @Override
    public Response<ContactGroup> duplicateContactGroup(int id, String name,
                                                        List<String>
                                                                contacts)
            throws IOException {
        return ApiCall.post(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_NAME, name)
                      .addContacts(contacts)
                      .as(ContactGroup.class);
    }

    @Override
    public Response<ContactGroup> duplicateContactGroup(int id, String name)
            throws IOException {
        return ApiCall.post(baseUri)
                      .appendPath(id)
                      .addParameter(PARAMETER_NAME, name)
                      .as(ContactGroup.class);
    }
}
