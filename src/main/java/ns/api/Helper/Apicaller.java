package ns.api.Helper;

import com.google.gson.Gson;

public class Apicaller {

    private HttpHandler httpHandler;
    public static String mainUrl = "https://gateway.apiportal.ns.nl/public-reisinformatie/api/v2/";
    private static String APIKEY;
    private static Apicaller instance;


    private Apicaller() {
    }

    public static synchronized Apicaller getInstance() {

        if (instance == null) {
            instance = new Apicaller();
        }
        return instance;
    }

    public Gson getStations() {
        //TODO
return null;

    }


}
