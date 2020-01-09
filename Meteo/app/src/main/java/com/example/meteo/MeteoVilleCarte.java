package com.example.meteo;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MeteoVilleCarte extends AsyncTask<Object, Void, Ville> {

    private Ville ville;
    private String nomVille;

    @Override
    protected Ville doInBackground(Object... objects) {

        ville = (Ville) objects[1];
        nomVille = (String) objects[2];

        URL url = null;
        StringBuilder jsonStr = new StringBuilder();
        try {
            url = new URL((String) objects[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader input = new BufferedReader(isr);

                jsonStr.append(input.readLine());
                String result = jsonStr.toString();
                JSONObject jsonObject = new JSONObject(result);

                ArrayList<String> temp = new ArrayList<>();
                ArrayList<String> logo = new ArrayList<>();

                for(int i=0; i<8; i++) {
                    try {
                        temp.add(String.valueOf((int) Math.round((double) jsonObject.getJSONArray("list").getJSONObject(i).getJSONObject("main").get("temp") - 273.15)) + "°");
                    }
                    catch (Exception e){
                        temp.add(String.valueOf((int) Math.round((int) jsonObject.getJSONArray("list").getJSONObject(i).getJSONObject("main").get("temp") - 273.15)) + "°");
                    }
                }

                for(int i=0; i<=8; i++){
                    String logo_heure = jsonObject.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).get("icon").toString();
                    logo.add(logo_heure);
                }

                for(int i=8; i<=32; i = i+8){
                    String logo_heure = jsonObject.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).get("icon").toString();
                    logo.add(logo_heure);
                }

                ville = new Ville(nomVille, temp, logo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        urlConnection.disconnect();
        return ville;
    }
}