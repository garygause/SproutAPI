package com.sprout.api;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


public class RestApiBase {

    protected static final String TAG = "RestApi";
    protected static final boolean JSON_FAIL_ON_UNKNOWN = false;
    
    protected String mApiToken;
    protected String mApiKey;
    protected String mApiSecret;
    protected ObjectMapper mMapper;
    
    public interface RestResult {
        public static final int FAIL = 0;
        public static final int SUCCESS = 1;
        public static final int ERROR = 2;
        public static final int NO_INTERNET = 3;
    }
    
    public RestApiBase(String apiToken) {
        mApiToken = apiToken;
        mMapper = new ObjectMapper();
        mMapper.configure(
                DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                JSON_FAIL_ON_UNKNOWN);
    }
    
    public RestApiBase(String apiKey, String apiSecret) {
        mApiKey = apiKey;
        mApiSecret = apiSecret;
        mMapper = new ObjectMapper();
        mMapper.configure(
                DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                JSON_FAIL_ON_UNKNOWN);
    }
    
    /********************************
     * 
     *******************************/

    /**
     * buildParms
     * 
     * @param url
     * @param args
     * @return
     */
    public static String buildParams(String url, Map<String, String> args) {
        List<String> argList = new ArrayList<String>();
        for (String key : args.keySet()) {
            String arg = key + "=" + encode(args.get(key));
            // String arg = key+"="+ args.get(key);
            argList.add(arg);
        }
        Collections.sort(argList);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < argList.size(); i++) {
            s.append(argList.get(i));
            if (i != argList.size() - 1) {
                s.append("&");
            }
        }
        return url + "?" + s.toString();
    }

    /**
     * buildParmStr
     * 
     * @param url
     * @param args
     * @return
     */
    public static String buildParamStr(Map<String, String> args) {
        List<String> argList = new ArrayList<String>();
        for (String key : args.keySet()) {
            String arg = key + "=" + encode(args.get(key));
            // String arg = key+"="+ args.get(key);
            argList.add(arg);
        }
        Collections.sort(argList);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < argList.size(); i++) {
            s.append(argList.get(i));
            if (i != argList.size() - 1) {
                s.append("&");
            }
        }
        return s.toString();
    }

    /**
     * encode
     * 
     * @param value
     * @return
     */
    public static String encode(String value) {
        if (value == null)
            return "";
        try {
            return URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException wow) {
            throw new RuntimeException(wow.getMessage(), wow);
        }
    }

    /**
     * doHttpMethodReq
     * 
     * @param urlStr
     * @param requestMethod
     * @param paramStr
     * @param header
     * @return
     */
    public HttpURLConnection doHttpMethodReq(String urlStr,
            String requestMethod, Map<String, String> params,
            Map<String, String> header) {

        try {
            Log.i(TAG, "RestApi url: " + urlStr);          
            if (params != null && !requestMethod.equals("POST"))
                urlStr = buildParams(urlStr, params);
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (requestMethod.equals("POST"))
                conn.setDoOutput(true);
            conn.setDoInput(true);
            if (requestMethod != null)
                conn.setRequestMethod(requestMethod);
            if (header != null) {
                for (String key : header.keySet()) {
                    conn.setRequestProperty(key, header.get(key));
                }
            }

            // this ensures the auth header is present with the post
            // otherwise, the post data will be lost. preemptive auth.
            final String username = mApiKey;
            final String password = mApiSecret;     
            if (username != null && password != null) {
                String authStr = username + ":" + password;
                String encoding = Base64.encodeBytes(authStr.getBytes());
                conn.setRequestProperty  ("Authorization", "Basic " + encoding);
            } else if (mApiToken != null) {
                //String encoding = Base64.encodeBytes(mApiToken.getBytes());
                conn.setRequestProperty  ("Authorization", "Token " + mApiToken);
            }
            // conn.setRequestProperty("X-HTTP-Method-Override", "PUT"); 
            // if POST, should use this
            // conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            OutputStreamWriter wr = null;
            if (requestMethod != null && !requestMethod.equals("GET")
                    && !requestMethod.equals("DELETE")) {
                String paramStr = buildParamStr(params);
                wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(paramStr);
                wr.flush();
            }
            Log.i(TAG, "Sprout server response code: " + conn.getResponseCode() + "/" + conn.getResponseMessage());
            
            return conn;

        } catch (ProtocolException e) {
            Log.e(TAG, "Invalid authentication credentials.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Detect if an Internet connection is available.
     * 
     * @return true if an Internet connection is available
     */
    /*
    public static boolean internetIsAvailable() {
        boolean bAvailable = false;
        ConnectivityManager cm = (ConnectivityManager) ApplicationContext.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if ((netInfo != null) && netInfo.isConnected()) {
            // connection is available
            bAvailable = true;
        }
        return bAvailable;
    }
    */
    
}
