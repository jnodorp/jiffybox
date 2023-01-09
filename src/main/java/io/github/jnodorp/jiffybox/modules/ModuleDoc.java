package io.github.jnodorp.jiffybox.modules;

import io.github.jnodorp.jiffybox.models.Doc;
import io.github.jnodorp.jiffybox.models.Response;
import feign.Param;
import feign.RequestLine;

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
     */
    @RequestLine("GET /doc")
    Response<Map<String, String>> getDocs();

    /**
     * Provides a brief documentation of the module. A list of modules may be
     * retrieved using get().
     *
     * @param module The module.
     * @return The requested doc.
     */
    @RequestLine("GET /doc/{module}")
    Response<Doc> getDoc(@Param("module") String module);
}
