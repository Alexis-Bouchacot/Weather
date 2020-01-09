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

public class MeteoVille extends AsyncTask<Object, Void, Ville> {

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
                ArrayList<String> heure = new ArrayList<>();
                ArrayList<String> temp_jour = new ArrayList<>();
                ArrayList<String> jour = new ArrayList<>();
                ArrayList<String> logo = new ArrayList<>();

                for(int i=0; i<8; i++){
                    try {
                        temp.add(String.valueOf((int) Math.round((double) jsonObject.getJSONArray("list").getJSONObject(i).getJSONObject("main").get("temp") - 273.15)) + "°");
                    }
                    catch (Exception e){
                        temp.add(String.valueOf((int) Math.round((int) jsonObject.getJSONArray("list").getJSONObject(i).getJSONObject("main").get("temp") - 273.15)) + "°");
                    }                }

                String temp_min_max = String.valueOf((int) Math.round((double) jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").get("temp_min") - 273.15)) + "° / " + String.valueOf((int) Math.round((double) jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").get("temp_max") - 273.15)) + "°";
                String description = jsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("description").toString();

                for(int i=0; i<8; i++){
                    String dt_txt = jsonObject.getJSONArray("list").getJSONObject(i).get("dt_txt").toString();
                    String[] values = dt_txt.split(" ");
                    String[] values2 = values[1].split(":");
                    String heureComplete = values2[0]+":"+values2[1];
                    heure.add(heureComplete);
                }

                temp_jour.add(String.valueOf((int) Math.round((((double) jsonObject.getJSONArray("list").getJSONObject(4).getJSONObject("main").get("temp_max") + (double) jsonObject.getJSONArray("list").getJSONObject(5).getJSONObject("main").get("temp_max") + (double) jsonObject.getJSONArray("list").getJSONObject(7).getJSONObject("main").get("temp_max")) / 3) - 273.15)) + "° / " + String.valueOf((int) Math.round((((double) jsonObject.getJSONArray("list").getJSONObject(4).getJSONObject("main").get("temp_min") + (double) jsonObject.getJSONArray("list").getJSONObject(5).getJSONObject("main").get("temp_min") + (double) jsonObject.getJSONArray("list").getJSONObject(7).getJSONObject("main").get("temp_min")) / 3) - 273.15)) + "°");
                temp_jour.add(String.valueOf((int) Math.round((((double) jsonObject.getJSONArray("list").getJSONObject(12).getJSONObject("main").get("temp_max") + (double) jsonObject.getJSONArray("list").getJSONObject(13).getJSONObject("main").get("temp_max") + (double) jsonObject.getJSONArray("list").getJSONObject(15).getJSONObject("main").get("temp_max")) / 3) - 273.15)) + "° / " + String.valueOf((int) Math.round((((double) jsonObject.getJSONArray("list").getJSONObject(12).getJSONObject("main").get("temp_min") + (double) jsonObject.getJSONArray("list").getJSONObject(13).getJSONObject("main").get("temp_min") + (double) jsonObject.getJSONArray("list").getJSONObject(15).getJSONObject("main").get("temp_min")) / 3) - 273.15)) + "°");
                temp_jour.add(String.valueOf((int) Math.round((((double) jsonObject.getJSONArray("list").getJSONObject(20).getJSONObject("main").get("temp_max") + (double) jsonObject.getJSONArray("list").getJSONObject(21).getJSONObject("main").get("temp_max") + (double) jsonObject.getJSONArray("list").getJSONObject(23).getJSONObject("main").get("temp_max")) / 3) - 273.15)) + "° / " + String.valueOf((int) Math.round((((double) jsonObject.getJSONArray("list").getJSONObject(20).getJSONObject("main").get("temp_min") + (double) jsonObject.getJSONArray("list").getJSONObject(21).getJSONObject("main").get("temp_min") + (double) jsonObject.getJSONArray("list").getJSONObject(23).getJSONObject("main").get("temp_min")) / 3) - 273.15)) + "°");
                temp_jour.add(String.valueOf((int) Math.round((((double) jsonObject.getJSONArray("list").getJSONObject(28).getJSONObject("main").get("temp_max") + (double) jsonObject.getJSONArray("list").getJSONObject(29).getJSONObject("main").get("temp_max") + (double) jsonObject.getJSONArray("list").getJSONObject(31).getJSONObject("main").get("temp_max")) / 3) - 273.15)) + "° / " + String.valueOf((int) Math.round((((double) jsonObject.getJSONArray("list").getJSONObject(28).getJSONObject("main").get("temp_min") + (double) jsonObject.getJSONArray("list").getJSONObject(29).getJSONObject("main").get("temp_min") + (double) jsonObject.getJSONArray("list").getJSONObject(31).getJSONObject("main").get("temp_min")) / 3) - 273.15)) + "°");

                for(int i=4; i<=28; i = i+8){
                    String dt_txt = jsonObject.getJSONArray("list").getJSONObject(i).get("dt_txt").toString();
                    String[] values = dt_txt.split(" ");
                    String[] values2 = values[0].split("-");
                    String jourComplet = values2[2]+" / "+values2[1] + " :";
                    jour.add(jourComplet);
                }

                for(int i=0; i<=8; i++){
                    String logo_heure = jsonObject.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).get("icon").toString();
                    logo.add(logo_heure);
                }

                for(int i=8; i<=32; i = i+8){
                    String logo_heure = jsonObject.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).get("icon").toString();
                    logo.add(logo_heure);
                }

                double direction = Double.parseDouble(jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("wind").get("deg").toString());
                String vitesse = String.valueOf((int) Math.round((double) jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("wind").get("speed")*3.6) + " km/h");
                int humidity = (int) jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").get("humidity");


                ville = new Ville(nomVille, temp, description, temp_min_max, heure, temp_jour, jour, logo, direction, vitesse, humidity);
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