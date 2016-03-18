package eu.df.jiffybox.modules;

import eu.df.jiffybox.JiffyBoxApi;
import eu.df.jiffybox.models.Doc;
import eu.df.jiffybox.models.DocEntry;
import eu.df.jiffybox.models.Message;
import eu.df.jiffybox.models.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the 'doc' module.
 */
public class ModuleDocTest extends ModuleTest {

    /**
     * The {@link JiffyBoxApi}.
     */
    private final JiffyBoxApi jiffyBoxApi;

    /**
     * Create a new instance using the given {@link JiffyBoxApi}.
     *
     * @param jiffyBoxApi The {@link JiffyBoxApi}.
     */
    public ModuleDocTest(final JiffyBoxApi jiffyBoxApi) {
        this.jiffyBoxApi = jiffyBoxApi;
    }

    /**
     * Test for {@link ModuleDoc#getDocs()}.
     */
    @Test
    public void testGetDocs() throws IOException {
        Response<Map<String, String>> response = jiffyBoxApi.doc().getDocs();
        List<Message> messages = response.getMessages();
        assertTrue(messages.isEmpty());

        Map<String, String> docs = response.getResult();
        assertEquals("Dokumentationsmodul", docs.get("doc"));
        assertEquals("Modul zum Auflisten von installierbaren " + "Linux-Distributionen", docs.get("distributions"));
        assertEquals("Modul zum Auflisten und Manipulieren von JiffyBoxen", docs.get("jiffyBoxes"));
        assertEquals("Modul zum Auflisten und Beantragen von Backups", docs.get("backups"));
        assertEquals("Modul zum Auflisten von JiffyBox-Tarifen", docs.get("plans"));
    }

    /**
     * Test for {@link ModuleDoc#getDoc(String)}.
     */
    @Test
    public void testGetDoc() throws IOException {
        Response<Doc> response = jiffyBoxApi.doc().getDoc("doc");
        List<Message> messages = response.getMessages();
        Doc doc = response.getResult();
        List<DocEntry> docEntries = doc.getDocEntries();
        DocEntry docEntry1 = docEntries.get(0);
        DocEntry docEntry2 = docEntries.get(1);

        assertTrue(messages.isEmpty());

        assertEquals("Dokumentationsmodul", doc.getDescription());

        assertEquals("Hiermit bekommen Sie eine Übersicht aller verfügbaren Module mit einer Kurzbeschreibung, was "
                + "diese machen.", docEntry1.getDescription());
        assertEquals("/", docEntry1.getPath());
        assertEquals("GET", docEntry1.getVerb());
        assertTrue(docEntry1.getParameters().getMay().isEmpty());

        assertEquals("Hiermit lesen Sie die Kurzdokumentation eines Moduls aus. Eine Liste aller Module gibt es " +
                "unter" + " /.", docEntry2.getDescription());
        assertEquals("/<module>", docEntry2.getPath());
        assertEquals("GET", docEntry2.getVerb());
        assertTrue(docEntry2.getParameters().getMay().isEmpty());
    }
}
