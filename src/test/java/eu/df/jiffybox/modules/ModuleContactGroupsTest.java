package eu.df.jiffybox.modules;

import com.github.tomakehurst.wiremock.WireMockServer;
import eu.df.jiffybox.JiffyBoxApi;
import eu.df.jiffybox.WireMockHelper;
import eu.df.jiffybox.models.ContactGroup;
import eu.df.jiffybox.models.ContactGroupStatus;
import eu.df.jiffybox.models.Message;
import eu.df.jiffybox.models.Response;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the 'contactGroups' module.
 */
@ExtendWith(ModuleTestExtension.class)
@ModuleTestExtension.ModuleTest(runAgainstServer = false)
class ModuleContactGroupsTest {

    /**
     * Test for {@link ModuleContactGroups#getContactGroups()}.
     */
    @TestTemplate
    void testGetContactGroups(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/contactGroups")).willReturn
                (aResponse()
                .withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/contactGroups/testGetContactGroups.json")));

        Response<Map<String, ContactGroup>> response = api.contactGroups().getContactGroups();
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
    @TestTemplate
    void testGetContactGroup(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/contactGroups/123")).willReturn
                (aResponse()
                .withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/contactGroups/testGetContactGroup.json")));

        Response<ContactGroup> response = api.contactGroups().getContactGroup(123);
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
    @TestTemplate
    void testDeleteContactGroup(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(delete(urlPathEqualTo("/00000000000000000000000000000000/v1.0/contactGroups/123"))
                .willReturn(aResponse()
                .withHeaders(WireMockHelper.headers())
                .withStatus(200)
                .withBodyFile("modules/contactGroups/testDeleteContactGroup.json")));

        Response<Boolean> response = api.contactGroups().deleteContactGroup(123);
        List<Message> messages = response.getMessages();
        Boolean result = response.getResult();

        assertTrue(messages.isEmpty());

        assertTrue(result);
    }

    /**
     * Test for {@link ModuleContactGroups#createContactGroup(String, List)}.
     */
    @TestTemplate
    void testCreateContactGroup(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/contactGroups")).withRequestBody
                (equalToJson("{\"name\": \"TestGruppe\", \"contacts\": [\"m.mustermann@df.eu\", \"f" + "" + "" + "" +
                        ".musterfrau@df.eu\"] }", false, false))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/contactGroups/testCreateContactGroup.json")));

        List<String> contacts1 = new ArrayList<>();
        contacts1.add("m.mustermann@df.eu");
        contacts1.add("f.musterfrau@df.eu");

        Response<ContactGroup> response = api.contactGroups().createContactGroup("TestGruppe", contacts1);
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
    @TestTemplate
    void testUpdateContactGroup(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(put(urlPathEqualTo("/00000000000000000000000000000000/v1.0/contactGroups/1234"))
                .withRequestBody(equalToJson("{\"name\" : \"Neuer Name der TestGruppe\"}", false, false))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/contactGroups/testUpdateContactGroup.json")));

        Response<ContactGroup> response = api.contactGroups().updateContactGroup(1234, "Neuer Name der TestGruppe");
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
    @TestTemplate
    void testUpdateContactGroup1(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(put(urlPathEqualTo("/00000000000000000000000000000000/v1.0/contactGroups/1234"))
                .withRequestBody(equalToJson("{\"contacts\": [\"f.musterfrau@example.com\"]}", false, false))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/contactGroups/testUpdateContactGroup1.json")));

        List<String> contacts1 = new ArrayList<>();
        contacts1.add("f.musterfrau@example.com");

        Response<ContactGroup> response = api.contactGroups().updateContactGroup(1234, contacts1);
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
    @TestTemplate
    void testUpdateContactGroup2(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(put(urlPathEqualTo("/00000000000000000000000000000000/v1.0/contactGroups/1234"))
                .withRequestBody(equalToJson("{\"name\": \"Neuer Name der TestGruppe\", \"contacts\": [\"f" + "" + ""
                        + "" + "" + "" + ".musterfrau@example.com\"]}", false, false))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/contactGroups/testUpdateContactGroup2.json")));

        List<String> contacts1 = new ArrayList<>();
        contacts1.add("f.musterfrau@example.com");

        Response<ContactGroup> response = api.contactGroups()
                .updateContactGroup(1234, "Neuer Name der TestGruppe", contacts1);
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
    @TestTemplate
    void testDuplicateContactGroup(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/contactGroups/1234"))
                .withRequestBody(equalToJson("{\"name\": \"Kopie von TestGruppe\"}", false, false))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/contactGroups/testDuplicateContactGroup.json")));

        Response<ContactGroup> response = api.contactGroups().duplicateContactGroup(1234, "Kopie von TestGruppe");
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
    @TestTemplate
    void testDuplicateContactGroup1(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(post(urlPathEqualTo("/00000000000000000000000000000000/v1.0/contactGroups/1234"))
                .withRequestBody(equalToJson("{\"name\": \"Kopie von TestGruppe\", \"contacts\": [\"f" + "" + "" + ""
                        + ".musterfrau@example.com\"]}", false, false))
                .willReturn(aResponse().withHeaders(WireMockHelper.headers())
                        .withStatus(200)
                        .withBodyFile("modules/contactGroups/testDuplicateContactGroup1.json")));

        List<String> contacts1 = new ArrayList<>();
        contacts1.add("f.musterfrau@example.com");

        Response<ContactGroup> response = api.contactGroups()
                .duplicateContactGroup(1234, "Kopie von TestGruppe", contacts1);
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
