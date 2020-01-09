package com.example.meteo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

public class Carte extends Activity {

    private TextView temp_carte1;
    private TextView temp_carte2;
    private TextView temp_carte3;
    private TextView temp_carte4;
    private TextView temp_carte5;
    private TextView temp_carte6;
    private TextView temp_carte7;
    private TextView temp_carte8;
    private TextView temp_carte9;
    private TextView temp_carte10;
    private TextView temp_carte11;
    private TextView temp_carte12;
    private TextView temp_carte13;
    private TextView temp_carte14;
    private TextView temp_carte15;
    private TextView temp_carte16;

    private ImageView logo_carte1;
    private ImageView logo_carte2;
    private ImageView logo_carte3;
    private ImageView logo_carte4;
    private ImageView logo_carte5;
    private ImageView logo_carte6;
    private ImageView logo_carte7;
    private ImageView logo_carte8;
    private ImageView logo_carte9;
    private ImageView logo_carte10;
    private ImageView logo_carte11;
    private ImageView logo_carte12;
    private ImageView logo_carte13;
    private ImageView logo_carte14;
    private ImageView logo_carte15;
    private ImageView logo_carte16;

    private Ville ville;
    private String ville_rechercher[] = {"Paris", "Caen", "Lille", "Strasbourg", "Bordeaux", "Toulouse", "Brest", "Ajaccio", "Marseille", "Perpigan", "Tours", "Lyon", "Clermont-Ferrand", "Dijon", "La Rochelle", "Reims"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_carte);

        temp_carte1 = (TextView) findViewById(R.id.temp_carte1);
        temp_carte2 = (TextView) findViewById(R.id.temp_carte2);
        temp_carte3 = (TextView) findViewById(R.id.temp_carte3);
        temp_carte4 = (TextView) findViewById(R.id.temp_carte4);
        temp_carte5 = (TextView) findViewById(R.id.temp_carte5);
        temp_carte6 = (TextView) findViewById(R.id.temp_carte6);
        temp_carte7 = (TextView) findViewById(R.id.temp_carte7);
        temp_carte8 = (TextView) findViewById(R.id.temp_carte8);
        temp_carte9 = (TextView) findViewById(R.id.temp_carte9);
        temp_carte10 = (TextView) findViewById(R.id.temp_carte10);
        temp_carte11 = (TextView) findViewById(R.id.temp_carte11);
        temp_carte12 = (TextView) findViewById(R.id.temp_carte12);
        temp_carte13 = (TextView) findViewById(R.id.temp_carte13);
        temp_carte14 = (TextView) findViewById(R.id.temp_carte14);
        temp_carte15 = (TextView) findViewById(R.id.temp_carte15);
        temp_carte16 = (TextView) findViewById(R.id.temp_carte16);

        logo_carte1 = (ImageView) findViewById(R.id.logo_carte1);
        logo_carte2 = (ImageView) findViewById(R.id.logo_carte2);
        logo_carte3 = (ImageView) findViewById(R.id.logo_carte3);
        logo_carte4 = (ImageView) findViewById(R.id.logo_carte4);
        logo_carte5 = (ImageView) findViewById(R.id.logo_carte5);
        logo_carte6 = (ImageView) findViewById(R.id.logo_carte6);
        logo_carte7 = (ImageView) findViewById(R.id.logo_carte7);
        logo_carte8 = (ImageView) findViewById(R.id.logo_carte8);
        logo_carte9 = (ImageView) findViewById(R.id.logo_carte9);
        logo_carte10 = (ImageView) findViewById(R.id.logo_carte10);
        logo_carte11 = (ImageView) findViewById(R.id.logo_carte11);
        logo_carte12 = (ImageView) findViewById(R.id.logo_carte12);
        logo_carte13 = (ImageView) findViewById(R.id.logo_carte13);
        logo_carte14 = (ImageView) findViewById(R.id.logo_carte14);
        logo_carte15 = (ImageView) findViewById(R.id.logo_carte15);
        logo_carte16 = (ImageView) findViewById(R.id.logo_carte16);

        ville = new Ville();

        rechercher(temp_carte1, logo_carte1, 0);
        rechercher(temp_carte2, logo_carte2, 1);
        rechercher(temp_carte3, logo_carte3, 2);
        rechercher(temp_carte4, logo_carte4, 3);
        rechercher(temp_carte5, logo_carte5, 4);
        rechercher(temp_carte6, logo_carte6, 5);
        rechercher(temp_carte7, logo_carte7, 6);
        rechercher(temp_carte8, logo_carte8, 7);
        rechercher(temp_carte9, logo_carte9, 8);
        rechercher(temp_carte10, logo_carte10, 9);
        rechercher(temp_carte11, logo_carte11, 10);
        rechercher(temp_carte12, logo_carte12, 11);
        rechercher(temp_carte13, logo_carte13, 12);
        rechercher(temp_carte14, logo_carte14, 13);
        rechercher(temp_carte15, logo_carte15, 14);
        rechercher(temp_carte16, logo_carte16, 15);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipelayout_carte);

        swipeRefreshLayout.setColorSchemeResources(R.color.refresh, R.color.refresh1, R.color.refresh2);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        rechercher(temp_carte1, logo_carte1, 0);
                        rechercher(temp_carte2, logo_carte2, 1);
                        rechercher(temp_carte3, logo_carte3, 2);
                        rechercher(temp_carte4, logo_carte4, 3);
                        rechercher(temp_carte5, logo_carte5, 4);
                        rechercher(temp_carte6, logo_carte6, 5);
                        rechercher(temp_carte7, logo_carte7, 6);
                        rechercher(temp_carte8, logo_carte8, 7);
                        rechercher(temp_carte9, logo_carte9, 8);
                        rechercher(temp_carte10, logo_carte10, 9);
                        rechercher(temp_carte11, logo_carte11, 10);
                        rechercher(temp_carte12, logo_carte12, 11);
                        rechercher(temp_carte13, logo_carte13, 12);
                        rechercher(temp_carte14, logo_carte14, 13);
                        rechercher(temp_carte15, logo_carte15, 14);
                        rechercher(temp_carte16, logo_carte16, 15);
                    }

                }, 3000);
            }
        });
    }

    private void rechercher(TextView tv, ImageView iv, int i){
        AsyncTask task = new MeteoVilleCarte().execute("http://api.openweathermap.org/data/2.5/forecast?q=" + ville_rechercher[i] + "&APPID=dcd4828c83a4954be5e37bb65610b917", ville, ville_rechercher[i]);

        try {
            ville = (Ville) task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tv.setText(ville.getTemp().get(1));
        Picasso.with(this).load("http://openweathermap.org/img/w/" + ville.getLogo().get(1) + ".png").into(iv);

    }
}
