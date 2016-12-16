package eu.df.jiffybox;

import com.github.tomakehurst.wiremock.http.ContentTypeHeader;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.net.HttpHeaders.CACHE_CONTROL;
import static com.google.common.net.HttpHeaders.DATE;
import static com.google.common.net.HttpHeaders.EXPIRES;
import static com.google.common.net.HttpHeaders.SERVER;
import static com.google.common.net.HttpHeaders.TRANSFER_ENCODING;


/**
 * A helper class for {@link com.github.tomakehurst.wiremock.client.WireMock}
 * tests.
 *
 * @author Julian Schlichtholz
 */
public class WireMockHelper {

    /**
     * List of default headers.
     */
    private static final List<HttpHeader> HEADERS = new ArrayList<>();

    // Prefill list of default headers.
    static {
        HEADERS.add(new HttpHeader(CACHE_CONTROL, "no-cache, must-revalidate"));
        HEADERS.add(new ContentTypeHeader("application/json; charset=utf-8"));
        HEADERS.add(new HttpHeader(EXPIRES, "Sat, 26 Jul 1997 05:00:00 GMT"));
        HEADERS.add(new HttpHeader(SERVER, "lighttpd/1.4.35"));
        HEADERS.add(new HttpHeader(TRANSFER_ENCODING, "chunked"));
    }

    /**
     * Prepare {@link HttpHeaders} as a combination of the default headers and a
     * current date header.
     *
     * @return the {@link HttpHeaders}
     */
    public static HttpHeaders headers() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT"));
        String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(now);

        List<HttpHeader> httpHeaders = new ArrayList<>(HEADERS);
        httpHeaders.add(new HttpHeader(DATE, date));
        return new HttpHeaders(httpHeaders);
    }
}
