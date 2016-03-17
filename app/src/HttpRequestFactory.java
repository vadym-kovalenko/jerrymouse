import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Vadim on 16.03.2016.
 */
public class HttpRequestFactory {

    private static HttpRequestFactory INSTANCE;

    public static synchronized HttpRequestFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HttpRequestFactory();
        }
        return INSTANCE;
    }

    public HttpServletRequest createRequestFromStringList(List<String> params) {
        if (params == null || params.isEmpty()) {
            throw new IllegalArgumentException("Params must not be null or empty");
        }
        JerryHttpRequestBuilder builder = new JerryHttpRequestBuilder();

        Iterator<String> iterator = params.iterator();

        String[] firstLineParts = iterator.next().split(" ");
        if (firstLineParts.length != 3 || !firstLineParts[2].startsWith("HTTP/1.")) {
            throw new IllegalArgumentException("Not valid http request string");
        }
        String method = firstLineParts[0];
        String query = firstLineParts[1];

        String host = iterator.next();
        host = host.substring(host.indexOf(':') + 2);

        while (iterator.hasNext()) {
            String header = iterator.next();
            if (header.isEmpty()) break;
            builder.addHeader(header.split(":")[0], header.substring(header.indexOf(":") + 1));
        }

        builder.setMethod(method);
        return builder.build();
    }

}
