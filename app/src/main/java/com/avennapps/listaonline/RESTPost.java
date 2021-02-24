package com.avennapps.listaonline;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class RESTPost extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {

        try {
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("desc", "aaaaaa");
            params.put("qtd", 5);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            URL url = new URL("http://avenn.ddns.net:8181/create?desc=aaaa&qtd=5");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            connection.setUseCaches(false);
            connection.setConnectTimeout(5000);
            connection.connect();
            int ss = connection.getResponseCode();
            InputStream _is;
            if (connection.getResponseCode() / 100 == 2) { // 2xx code means success
                _is = connection.getInputStream();
            } else {

                _is = connection.getErrorStream();

                String result = _is.toString();
                System.out.println(ss + "  ---   " + result);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
