package com.example.meteo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Acceuil extends Activity {

    private Button favoris;
    private Button carte;
    private TextView villeActuelle;
    private TextView temp;

    private TextView heure2;
    private TextView heure3;
    private TextView heure4;
    private TextView heure5;
    private TextView heure6;
    private TextView heure7;
    private TextView heure8;

    private TextView temp_heure2;
    private TextView temp_heure3;
    private TextView temp_heure4;
    private TextView temp_heure5;
    private TextView temp_heure6;
    private TextView temp_heure7;
    private TextView temp_heure8;

    private TextView jour1;
    private TextView jour2;
    private TextView jour3;
    private TextView jour4;

    private TextView temp_jour1;
    private TextView temp_jour2;
    private TextView temp_jour3;
    private TextView temp_jour4;

    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private ImageView imageView9;
    private ImageView imageView10;
    private ImageView imageView11;
    private ImageView imageView12;

    private TextView direction;
    private TextView vitesse;
    private  TextView humidity;


    private TextView description;
    private TextView temp_min_max;
    private String nomVille;
    private ProgressBar progressBar;

    private Ville ville;
    private ArrayList<Ville> villeArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_acceuil);

            SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("nomVille", null);
            Type type = new TypeToken<String>() {
            }.getType();
            nomVille = gson.fromJson(json, type);

            if (nomVille == null) {
                nomVille = "Paris";
            }

            favoris = (Button) findViewById(R.id.favoris);
            carte = (Button) findViewById(R.id.carte);
            villeActuelle = (TextView) findViewById(R.id.villeActuelle);

            heure2 = (TextView) findViewById(R.id.heure2);
            heure3 = (TextView) findViewById(R.id.heure3);
            heure4 = (TextView) findViewById(R.id.heure4);
            heure5 = (TextView) findViewById(R.id.heure5);
            heure6 = (TextView) findViewById(R.id.heure6);
            heure7 = (TextView) findViewById(R.id.heure7);
            heure8 = (TextView) findViewById(R.id.heure8);

            jour1 = (TextView) findViewById(R.id.jour1);
            jour2 = (TextView) findViewById(R.id.jour2);
            jour3 = (TextView) findViewById(R.id.jour3);
            jour4 = (TextView) findViewById(R.id.jour4);

            temp = (TextView) findViewById(R.id.temp);

            temp_heure2 = (TextView) findViewById(R.id.temp_heure2);
            temp_heure3 = (TextView) findViewById(R.id.temp_heure3);
            temp_heure4 = (TextView) findViewById(R.id.temp_heure4);
            temp_heure5 = (TextView) findViewById(R.id.temp_heure5);
            temp_heure6 = (TextView) findViewById(R.id.temp_heure6);
            temp_heure7 = (TextView) findViewById(R.id.temp_heure7);
            temp_heure8 = (TextView) findViewById(R.id.temp_heure8);

            temp_jour1 = (TextView) findViewById(R.id.temp_jour1);
            temp_jour2 = (TextView) findViewById(R.id.temp_jour2);
            temp_jour3 = (TextView) findViewById(R.id.temp_jour3);
            temp_jour4 = (TextView) findViewById(R.id.temp_jour4);

            imageView2 = (ImageView) findViewById(R.id.imageView2);
            imageView3 = (ImageView) findViewById(R.id.imageView3);
            imageView4 = (ImageView) findViewById(R.id.imageView4);
            imageView5 = (ImageView) findViewById(R.id.imageView5);
            imageView6 = (ImageView) findViewById(R.id.imageView6);
            imageView7 = (ImageView) findViewById(R.id.imageView7);
            imageView8 = (ImageView) findViewById(R.id.imageView8);
            imageView9 = (ImageView) findViewById(R.id.imageView9);
            imageView10 = (ImageView) findViewById(R.id.imageView10);
            imageView11 = (ImageView) findViewById(R.id.imageView11);
            imageView12 = (ImageView) findViewById(R.id.imageView12);

            direction = (TextView) findViewById(R.id.direction);
            vitesse = (TextView) findViewById(R.id.vitesse);
            humidity = (TextView) findViewById(R.id.humidity);


            description = (TextView) findViewById(R.id.description);
            temp_min_max = (TextView) findViewById(R.id.temp_min_max);
            ville = new Ville();
            villeArrayList = new ArrayList<>();

            rechercherVille();

            final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipelayout);

            swipeRefreshLayout.setColorSchemeResources(R.color.refresh, R.color.refresh1, R.color.refresh2);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeRefreshLayout.setRefreshing(true);
                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                            rechercherVille();
                        }

                    }, 3000);
                }
            });
    }

    public void goingToFavoris(View view) {
        if(view == favoris) {
            Intent intent = new Intent(this, Favoris.class);
            intent.putExtra("villeArrayList", villeArrayList);
            startActivityForResult(intent, 1);
        }
    }

    public void goingToMap(View view) {
        if(view == carte) {
            Intent intent = new Intent(this, Carte.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1 && resultCode == RESULT_OK){
            nomVille = data.getStringExtra("villeCliquer");

            SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(nomVille);
            editor.putString("nomVille", json);
            editor.apply();

            rechercherVille();

        }
    }

    private void rechercherVille(){
        AsyncTask task = new MeteoVille().execute("http://api.openweathermap.org/data/2.5/forecast?q=" + nomVille + "&APPID=dcd4828c83a4954be5e37bb65610b917", ville, nomVille);

        try {
            ville = (Ville) task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        villeActuelle.setText(ville.getNom());

        temp_heure2.setText(ville.getTemp().get(1));
        temp_heure3.setText(ville.getTemp().get(2));
        temp_heure4.setText(ville.getTemp().get(2));
        temp_heure5.setText(ville.getTemp().get(3));
        temp_heure6.setText(ville.getTemp().get(5));
        temp_heure7.setText(ville.getTemp().get(6));
        temp_heure8.setText(ville.getTemp().get(7));

        temp.setText(ville.getTemp().get(0));

        temp_jour1.setText(ville.getTemp_jour().get(0));
        temp_jour2.setText(ville.getTemp_jour().get(1));
        temp_jour3.setText(ville.getTemp_jour().get(2));
        temp_jour4.setText(ville.getTemp_jour().get(3));

        jour1.setText(ville.getJour().get(0));
        jour2.setText(ville.getJour().get(1));
        jour3.setText(ville.getJour().get(2));
        jour4.setText(ville.getJour().get(3));

        heure2.setText(ville.getHeure().get(1));
        heure3.setText(ville.getHeure().get(2));
        heure4.setText(ville.getHeure().get(3));
        heure5.setText(ville.getHeure().get(4));
        heure6.setText(ville.getHeure().get(5));
        heure7.setText(ville.getHeure().get(6));
        heure8.setText(ville.getHeure().get(7));

        Picasso.with(this).load("http://openweathermap.org/img/w/" + ville.getLogo().get(1) + ".png").into(imageView2);
        Picasso.with(this).load("http://openweathermap.org/img/w/" + ville.getLogo().get(2) + ".png").into(imageView3);
        Picasso.with(this).load("http://openweathermap.org/img/w/" + ville.getLogo().get(3) + ".png").into(imageView4);
        Picasso.with(this).load("http://openweathermap.org/img/w/" + ville.getLogo().get(4) + ".png").into(imageView5);
        Picasso.with(this).load("http://openweathermap.org/img/w/" + ville.getLogo().get(5) + ".png").into(imageView6);
        Picasso.with(this).load("http://openweathermap.org/img/w/" + ville.getLogo().get(6) + ".png").into(imageView7);
        Picasso.with(this).load("http://openweathermap.org/img/w/" + ville.getLogo().get(7) + ".png").into(imageView8);
        Picasso.with(this).load("http://openweathermap.org/img/w/" + ville.getLogo().get(8) + ".png").into(imageView9);
        Picasso.with(this).load("http://openweathermap.org/img/w/" + ville.getLogo().get(9) + ".png").into(imageView10);
        Picasso.with(this).load("http://openweathermap.org/img/w/" + ville.getLogo().get(10) + ".png").into(imageView11);
        Picasso.with(this).load("http://openweathermap.org/img/w/" + ville.getLogo().get(11) + ".png").into(imageView12);

        description.setText(ville.getDescription());
        temp_min_max.setText(ville.getTemp_min_max());

        vitesse.setText(ville.getVitesse());
        humidity.setText(String.valueOf(ville.getHumidity()));

        if(ville.getDirection()>337.5 && ville.getDirection()<=22.5){
            direction.setText("Nord");
        }else if(ville.getDirection()>22.5 && ville.getDirection()<=67.5){
            direction.setText("Nord-Est");
        }else if(ville.getDirection()>67.5 && ville.getDirection()<=112.5){
            direction.setText("Est");
        }else if(ville.getDirection()>112.5 && ville.getDirection()<=157.5){
            direction.setText("Sud-Est");
        }else if(ville.getDirection()>157.5 && ville.getDirection()<=202.5){
            direction.setText("Sud");
        }else if(ville.getDirection()>202.5 && ville.getDirection()<=247.5){
            direction.setText("Sud-Ouest");
        }else if(ville.getDirection()>247.5 && ville.getDirection()<=292.5){
            direction.setText("Ouest");
        }else if(ville.getDirection()>292.5 && ville.getDirection()<=337.5){
            direction.setText("Nord-Ouest");
        }
    }

}