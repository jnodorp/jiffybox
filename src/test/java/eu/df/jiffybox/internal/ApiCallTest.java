package eu.df.jiffybox.internal;

import eu.df.jiffybox.models.MessageType;
import eu.df.jiffybox.models.Response;
import eu.df.jiffybox.modules.ModuleTest;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test class for {@link ApiCall}.
 */
public class ApiCallTest extends ModuleTest {

    /**
     * Error test.
     */
    @Test
    public void testErrorTooManyRequests() throws IOException {
        Response<Map<String, String>> response = ApiCall.get(URI.create(TEST_HOST + "/testErrorTooManyRequests"))
                .asMap(String.class, String.class);

        assertEquals(MessageType.ERROR, response.getMessages().get(0).getType());
        assertEquals("Zu viele Aufrufe. Bitte warten Sie einen moment und versuchen Sie es dann wieder.", response
                .getMessages().get(0).getMessageText());
        Map<String, String> result = response.getResult();
        assertNull(result);
    }
}