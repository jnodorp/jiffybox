package eu.df.jiffybox.internal;

import eu.df.jiffybox.models.Doc;
import eu.df.jiffybox.models.Response;
import eu.df.jiffybox.modules.ModuleDoc;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * Implementation of the doc module.
 *
 * @see eu.df.jiffybox.modules.ModuleDoc
 */
public class ModuleDocImpl implements ModuleDoc {

    /**
     * The base URI.
     */
    private final URI baseUri;

    /**
     * Create an instance of this module using the specified base URI.
     *
     * @param baseUri The base URI to use.
     */
    public ModuleDocImpl(final URI baseUri) {
        this.baseUri = URI.create(baseUri + "/doc");
    }

    @Override
    public Response<Map<String, String>> getDocs() throws IOException {
        return ApiCall.get(baseUri).asMap(String.class, String.class);
    }

    @Override
    public Response<Doc> getDoc(final String module) throws IOException {
        return ApiCall.get(baseUri).appendPath(module).as(Doc.class);
    }
}
