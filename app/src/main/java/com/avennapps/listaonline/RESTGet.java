package com.avennapps.listaonline;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class RESTGet extends AsyncTask<Void, Void, JSONArray> {
    @Override
    protected JSONArray doInBackground(Void... voids) {
        JSONArray itemsArray = null;

        try {

            StringBuilder resposta = new StringBuilder();
            URL url = new URL("http://avenn.ddns.net:8181/all");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(5000);
            connection.connect();
            Reader reader = new InputStreamReader(url.openStream());
            JSONObject json = new JSONObject();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String inputLine;
            if ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            //inputLine = inputLine.replace('"', '\"');
            in.close();
            itemsArray = new JSONArray(inputLine);
            //itemsArray = new Gson().fromJson(inputLine, JSONArray.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemsArray;
    }
}
