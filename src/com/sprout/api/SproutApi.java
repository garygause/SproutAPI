/*
 * Copyright (C) 2013 SproutOne, see LICENSE for details.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Gary Gause
 * 
 */
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

        String url = API_PURCHASE;
        url = url.replace("{oan}", String.valueOf(oan));
        Log.i(TAG, "Sprout purchase using url: " + url);  // FIXME: remove before launch
        
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
