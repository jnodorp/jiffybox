package eu.df.jiffybox.modules;

import eu.df.jiffybox.JiffyBoxApi;
import eu.df.jiffybox.models.ContactGroup;
import eu.df.jiffybox.models.ContactGroupStatus;
import eu.df.jiffybox.models.Message;
import eu.df.jiffybox.models.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

/**
 * This class tests the 'contactGroups' module.
 */
public class ModuleContactGroupsTest extends ModuleTest {

    /**
     * The {@link JiffyBoxApi}.
     */
    private final JiffyBoxApi jiffyBoxApi;

    /**
     * Create a new instance using the given {@link JiffyBoxApi}.
     *
     * @param jiffyBoxApi The {@link JiffyBoxApi}.
     */
    public ModuleContactGroupsTest(final JiffyBoxApi jiffyBoxApi) {
        this.jiffyBoxApi = jiffyBoxApi;

        // Only run in development.
        assumeTrue(jiffyBoxApi.getUri().toString().contains("localhost"));
    }

    /**
     * Test for {@link ModuleContactGroups#getContactGroups()}.
     */
    @Test
    public void testGetContactGroups() throws IOException {
        Response<Map<String, ContactGroup>> response = jiffyBoxApi.getModuleContactGroups().getContactGroups();
        List<Message> messages = response.getMessages();
        Map<String, ContactGroup> contactGroups = response.getResult();
        ContactGroup contactGroup1 = contactGroups.get("122");
        ContactGroup contactGroup2 = contactGroups.get("123");
        List<String> contacts1 = contactGroup1.getContacts();
        List<String> contacts2 = contactGroup2.getContacts();

        assertTrue(messages.isEmpty());

        assertEquals(122, contactGroup1.getId());
        assertEquals(123, contactGroup2.getId());

        assertEquals("Stammdaten-E-Mail-Adresse", contactGroup1.getName());
        assertEquals("TestGruppe", contactGroup2.getName());

        assertEquals(ContactGroupStatus.STATUS_READY, contactGroup1.getStatus());
        assertEquals(ContactGroupStatus.STATUS_READY, contactGroup2.getStatus());

        assertEquals("m.mustermann@example.com", contacts1.get(0));
        assertEquals("m.mustermann@example.com", contacts2.get(0));
    }

    /**
     * Test for {@link ModuleContactGroups#getContactGroup(int)}.
     */
    @Test
    public void testGetContactGroup() throws IOException {
        Response<ContactGroup> response = jiffyBoxApi.getModuleContactGroups().getContactGroup(123);
        List<Message> messages = response.getMessages();
        ContactGroup contactGroup = response.getResult();
        List<String> contacts = contactGroup.getContacts();

        assertTrue(messages.isEmpty());

        assertEquals(123, contactGroup.getId());
        assertEquals("TestGruppe", contactGroup.getName());
        assertEquals(ContactGroupStatus.STATUS_READY, contactGroup.getStatus());

        assertEquals("m.mustermann@example.com", contacts.get(0));
    }

    /**
     * Test for {@link ModuleContactGroups#deleteContactGroup(int)}.
     */
    @Test
    public void testDeleteContactGroup() throws IOException {
        Response<Boolean> response = jiffyBoxApi.getModuleContactGroups().deleteContactGroup(123);
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }

    /**
     * Test for {@link ModuleContactGroups#createContactGroup(String, List)}.
     */
    @Test
    public void testCreateContactGroup() throws IOException {
        List<String> contacts1 = new ArrayList<>();
        contacts1.add("m.mustermann@df.eu");
        contacts1.add("f.musterfrau@df.eu");

        Response<ContactGroup> response = jiffyBoxApi.getModuleContactGroups().createContactGroup("TestGruppe",
                contacts1);
        List<Message> messages = response.getMessages();
        ContactGroup contactGroup = response.getResult();
        List<String> contacts2 = contactGroup.getContacts();

        assertTrue(messages.isEmpty());

        assertEquals(123, contactGroup.getId());
        assertEquals("TestGruppe", contactGroup.getName());
        assertEquals(ContactGroupStatus.STATUS_READY, contactGroup.getStatus());

        assertEquals("m.mustermann@df.eu", contacts2.get(0));
        assertEquals("f.musterfrau@df.eu", contacts2.get(1));
    }

    /**
     * Test for {@link ModuleContactGroups#updateContactGroup(int, String)}.
     */
    @Test
    public void testUpdateContactGroup() throws IOException {
        Response<ContactGroup> response = jiffyBoxApi.getModuleContactGroups().updateContactGroup(1234, "Neuer Name " +
                "der " +
                "" + "TestGruppe");
        List<Message> messages = response.getMessages();
        ContactGroup contactGroup = response.getResult();
        List<String> contacts = contactGroup.getContacts();

        assertTrue(messages.isEmpty());

        assertEquals(1234, contactGroup.getId());
        assertEquals("Neuer Name der TestGruppe", contactGroup.getName());
        assertEquals(ContactGroupStatus.STATUS_UPDATING, contactGroup.getStatus());

        assertEquals("m.mustermann@example.com", contacts.get(0));
        assertEquals("f.musterfrau@example.com", contacts.get(1));
    }

    /**
     * Test for {@link ModuleContactGroups#updateContactGroup(int, List)}
     */
    @Test
    public void testUpdateContactGroup1() throws IOException {
        List<String> contacts1 = new ArrayList<>();
        contacts1.add("f.musterfrau@example.com");

        Response<ContactGroup> response = jiffyBoxApi.getModuleContactGroups().updateContactGroup(1234, contacts1);
        List<Message> messages = response.getMessages();
        ContactGroup contactGroup = response.getResult();
        List<String> contacts2 = contactGroup.getContacts();

        assertTrue(messages.isEmpty());

        assertEquals(1234, contactGroup.getId());
        assertEquals("TestGruppe", contactGroup.getName());
        assertEquals(ContactGroupStatus.STATUS_UPDATING, contactGroup.getStatus());

        assertEquals("f.musterfrau@example.com", contacts2.get(0));
    }

    /**
     * Test for {@link ModuleContactGroups#updateContactGroup(int, String, java.util.List)}.
     */
    @Test
    public void testUpdateContactGroup2() throws IOException {
        List<String> contacts1 = new ArrayList<>();
        contacts1.add("f.musterfrau@example.com");

        Response<ContactGroup> response = jiffyBoxApi.getModuleContactGroups().updateContactGroup(1234, "Neuer Name "
                + "der TestGruppe", contacts1);
        List<Message> messages = response.getMessages();
        ContactGroup contactGroup = response.getResult();
        List<String> contacts2 = contactGroup.getContacts();

        assertTrue(messages.isEmpty());

        assertEquals(1234, contactGroup.getId());
        assertEquals("Neuer Name der TestGruppe", contactGroup.getName());
        assertEquals(ContactGroupStatus.STATUS_UPDATING, contactGroup.getStatus());

        assertEquals("f.musterfrau@example.com", contacts2.get(0));
    }

    /**
     * Test for {@link ModuleContactGroups#duplicateContactGroup(int, String)}.
     */
    @Test
    public void testDuplicateContactGroup() throws IOException {
        Response<ContactGroup> response = jiffyBoxApi.getModuleContactGroups().duplicateContactGroup(1234, "Kopie " +
                "von TestGruppe");
        List<Message> messages = response.getMessages();
        ContactGroup contactGroup = response.getResult();
        List<String> contacts2 = contactGroup.getContacts();

        assertTrue(messages.isEmpty());

        assertEquals(1235, contactGroup.getId());
        assertEquals("Kopie von TestGruppe", contactGroup.getName());
        assertEquals(ContactGroupStatus.STATUS_UPDATING, contactGroup.getStatus());

        assertEquals("m.mustermann@example.com", contacts2.get(0));
        assertEquals("f.musterfrau@example.com", contacts2.get(1));
    }

    /**
     * Test for {@link ModuleContactGroups#duplicateContactGroup(int, String, java.util.List)}.
     */
    @Test
    public void testDuplicateContactGroup1() throws IOException {
        List<String> contacts1 = new ArrayList<>();
        contacts1.add("f.musterfrau@example.com");

        Response<ContactGroup> response = jiffyBoxApi.getModuleContactGroups().duplicateContactGroup(1234, "Kopie " +
                "von TestGruppe", contacts1);
        List<Message> messages = response.getMessages();
        ContactGroup contactGroup = response.getResult();
        List<String> contacts2 = contactGroup.getContacts();

        assertTrue(messages.isEmpty());

        assertEquals(1235, contactGroup.getId());
        assertEquals("Kopie von TestGruppe", contactGroup.getName());
        assertEquals(ContactGroupStatus.STATUS_UPDATING, contactGroup.getStatus());

        assertEquals("f.musterfrau@example.com", contacts2.get(0));
    }
}
