package com.sprout.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;

import android.util.Log;

public class SproutApi extends RestApiBase {

    protected static final String TAG = "SproutApi";
    private static final boolean JSON_FAIL_ON_UNKNOWN = false;

    private static final String API_BASE = "https://gigolo-vampire.herokuapp.com/";
    private static final String API_CARD = API_BASE + "card/"; // slash required at end for api
    private static String API_PURCHASE = API_CARD + "{oan}/transaction/purchase/";
    
    public SproutApi(String apiToken) {
        super(apiToken);
    }

    public ArrayList<SproutCard> getCards(String number, String pin) {
        ArrayList<SproutCard> cards = null;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("phone_number", number);
        params.put("pin", pin);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json;version=1");

        HttpURLConnection conn = doHttpMethodReq(API_CARD, "GET", params, headers);
        if (conn != null) {
            try {
                InputStream istream = conn.getInputStream();
                if (istream != null) {
                    JsonFactory jf = new JsonFactory();
                    JsonParser jp = jf.createJsonParser(istream);
                    SproutCardResponse response = mMapper.readValue(jp, SproutCardResponse.class);
                    cards = response.getResults();
                    Log.i(TAG, "Sprout results: " + cards.size() + " card(s)");
                }
            } catch (JsonParseException e) {
                Log.e(TAG, "getCards: JSON parse error " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "getCards: Server I/O error " + e.getMessage());
            }
        }
        return cards;
    }
    
    public SproutPurchaseResponse purchase(long oan, String refId, ArrayList<SproutItem> items) {
        /*
        JSONObject json = new JSONObject();
        try {
            json.put("external_reference_id", refId);
            json.put("items", items); // TODO: fix
            // json.put("sprout_sticker_id", 0);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("external_reference_id", refId);
        map.put("items", items);
        String data = null;
        try {
            data = mMapper.writeValueAsString(map);
        } catch (JsonGenerationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (JsonMappingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Log.i(TAG, "json: " + data);
        
        SproutPurchaseResponse response = null;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("data", data);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json;version=1");
        headers.put("Content-type", "application/json;version=1");

        String url = API_PURCHASE; // TODO: replace oan
        url = url.replace("{oan}", String.valueOf(oan));
        Log.i(TAG, "Sprout purchase using url: " + url);
        
        HttpURLConnection conn = doHttpMethodReq(url, "POST", params, headers);
        if (conn != null) {
            try {
                InputStream istream = conn.getInputStream();
                if (istream != null) {
                    JsonFactory jf = new JsonFactory();
                    JsonParser jp = jf.createJsonParser(istream);
                    response = mMapper.readValue(jp, SproutPurchaseResponse.class);
                    Log.i(TAG, "Sprout purchase response (amount): " + response.getAmount());
                }
            } catch (JsonParseException e) {
                Log.e(TAG, "purchase: JSON parse error " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "purchase: Server I/O error " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return response;
    }

}
