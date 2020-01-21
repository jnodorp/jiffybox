package eu.df.jiffybox.modules;

import com.github.tomakehurst.wiremock.WireMockServer;
import eu.df.jiffybox.JiffyBoxApi;
import eu.df.jiffybox.WireMockHelper;
import eu.df.jiffybox.models.Doc;
import eu.df.jiffybox.models.DocEntry;
import eu.df.jiffybox.models.Message;
import eu.df.jiffybox.models.Response;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class tests the 'doc' module.
 */
@ExtendWith(ModuleTestExtension.class)
@ModuleTestExtension.ModuleTest(runAgainstServer = true)
class ModuleDocTest {

    /**
     * Test for {@link ModuleDoc#getDocs()}.
     */
    @TestTemplate
    void testGetDocs(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/doc")).willReturn(aResponse()
                .withHeaders(WireMockHelper
                .headers()).withStatus(200).withBodyFile("modules/doc/testGetDocs.json")));

        Response<Map<String, String>> response = api.doc().getDocs();
        List<Message> messages = response.getMessages();
        assertTrue(messages.isEmpty());

        Map<String, String> docs = response.getResult();
        assertEquals("Modul zur Monitoring-Verwaltung", docs.get("Monitoring"));
        assertEquals("Modul zum Auflisten und Manipulieren von JiffyBoxen", docs.get("JiffyBoxes"));
        assertEquals("Dokumentationsmodul", docs.get("Doc"));
        assertEquals("Modul zum Auflisten von installierbaren Linux-Distributionen", docs.get("Distributions"));
        assertEquals("Modul zum Auflisten und ändern von IP-Adressen", docs.get("Ips"));
        assertEquals("Modul zum Auflisten von JiffyBox-Tarifen", docs.get("Plans"));
        assertEquals("Modul zum Auflisten und Beantragen von Backups", docs.get("Backups"));
        assertEquals("Modul zur Verwaltung von Kontaktgruppen", docs.get("ContactGroups"));
    }

    /**
     * Test for {@link ModuleDoc#getDoc(String)}.
     */
    @TestTemplate
    void testGetDoc(WireMockServer wireMock, JiffyBoxApi api) {
        wireMock.stubFor(get(urlPathEqualTo("/00000000000000000000000000000000/v1.0/doc/doc")).willReturn(aResponse()
                .withHeaders(WireMockHelper
                .headers()).withStatus(200).withBodyFile("modules/doc/testGetDoc.json")));

        Response<Doc> response = api.doc().getDoc("doc");
        List<Message> messages = response.getMessages();
        Doc doc = response.getResult();
        List<DocEntry> docEntries = doc.getDocEntries();
        DocEntry docEntry1 = docEntries.get(0);
        DocEntry docEntry2 = docEntries.get(1);

        assertTrue(messages.isEmpty());

        assertEquals("Dokumentationsmodul", doc.getDescription());

        assertEquals("Hiermit bekommen Sie eine Übersicht aller verfügbaren Module mit einer Kurzbeschreibung, was "
                + "diese machen.", docEntry1
                .getDescription());
        assertEquals("/", docEntry1.getPath());
        assertEquals("GET", docEntry1.getVerb());
        assertTrue(docEntry1.getParameters().getMay().isEmpty());
        assertTrue(docEntry1.getParameters().getMust().isEmpty());

        assertEquals("Hiermit lesen Sie die Kurzdokumentation eines Moduls aus. Eine Liste aller Module gibt es " +
                "unter" + " /.", docEntry2
                .getDescription());
        assertEquals("/<module>", docEntry2.getPath());
        assertEquals("GET", docEntry2.getVerb());
        assertTrue(docEntry2.getParameters().getMay().isEmpty());
        assertTrue(docEntry2.getParameters().getMust().isEmpty());
    }
}
