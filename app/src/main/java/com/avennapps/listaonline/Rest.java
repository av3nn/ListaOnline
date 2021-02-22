package com.avennapps.listaonline;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class   Rest extends AsyncTask<Void, Void, Item[]> {
    @Override
    protected Item[] doInBackground(Void... voids) {
        Item[] items = new Item[10];


        try {

            StringBuilder resposta = new StringBuilder();
            URL url = new URL("http://avenn.ddns.net:8181/all");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(5000);
            connection.connect();
            Reader reader = new InputStreamReader( url.openStream() );
            JSONObject json = new JSONObject();
            //json.get(String.valueOf(url.openStream()));
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();

        }


        return items;
    }



}
