package ns.api.Helper;


import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpHandler {

    private static String url = "https://gateway.apiportal.ns.nl/public-reisinformatie/api/v2/";
    private final String apikey;
    private List params;

    public HttpHandler(String apiKey) {
        this.apikey = apiKey;
    }

    public List getParams() {
        return params;
    }

    public URIBuilder setParams(URIBuilder builder, Map<String, String> params) {

        Set set = params.entrySet();//Converting to Set so that we can traverse
        Iterator itr = set.iterator();
        while (itr.hasNext()) {
            //Converting to Map.Entry so that we can get key and value separately
            Map.Entry entry = (Map.Entry) itr.next();
            builder.setParameter(entry.getKey().toString(), entry.getValue().toString());
        }
        return builder;
    }

    public String makeServiceCall(String path, Map<String, String> parameters) {
        HttpClient httpclient = HttpClients.createDefault();

        try {

            URIBuilder builder = new URIBuilder(url + path);

            builder = this.setParams(builder, parameters);
            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", apikey);


            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
