import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vadim on 16.03.2016.
 */
public class JerryHttpRequestBuilder {

    private Map<String, List<String>> headers = new HashMap<>();
    private Map<String, List<String>> parameters = new HashMap<>();
    private String method;

    public JerryHttpRequestBuilder setMethod(String method) {
        this.method = method;
        return this;
    }

    public JerryHttpRequestBuilder addHeader(String name, String value) {
        List<String> headerValues = headers.get(name);
        if (headerValues == null) {
            headerValues = new ArrayList<>();
            headers.put(name, headerValues);
        }
        headerValues.add(value);
        return this;
    }

    public JerryHttpRequestBuilder addParameter(String name, String value) {
        List<String> paramValues = parameters.get(name);
        if (paramValues == null) {
            paramValues = new ArrayList<>();
            parameters.put(name, paramValues);
        }
        paramValues.add(value);
        return this;
    }

    public JerryHttpRequest build() {
        return new JerryHttpRequest(method, headers, parameters);
    }
}
