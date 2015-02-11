package eu.df.jiffybox.modules;

import eu.df.jiffybox.models.Doc;
import eu.df.jiffybox.models.Response;

import java.io.IOException;
import java.util.Map;

/**
 * This interface describes the doc module.
 */
public interface ModuleDoc {

    /**
     * Provides a list of all modules including a brief description. Using the
     * name you are able to request further details.
     *
     * @return All available docs.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Map<String, String>> getDocs() throws IOException;

    /**
     * Provides a brief documentation of the module. A list of modules may be
     * retrieved using get().
     *
     * @param module The module.
     * @return The requested doc.
     * @throws java.io.IOException When either the API limits are exceeded or
     *                             the server is unreachable.
     */
    Response<Doc> getDoc(final String module) throws IOException;
}
