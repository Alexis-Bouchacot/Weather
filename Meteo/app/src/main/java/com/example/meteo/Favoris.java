package com.example.meteo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Favoris extends Activity {

    private ListView nListView;
    private VilleAdapter adapter;
    private ArrayList<Ville> villeArrayList;
    private String villeCliquer;

    private TextView rechercher;
    private Ville ville;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_favoris);

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("villeArrayList", null);
        Type type = new TypeToken<ArrayList<Ville>>() {}.getType();
        villeArrayList = gson.fromJson(json, type);

        if(villeArrayList == null){
            villeArrayList = new ArrayList<>();
        }

        nListView = (ListView)findViewById(R.id.listeFavoris);

        rechercher = (TextView) findViewById(R.id.rechercheFavoris);

        if(!villeArrayList.isEmpty()) {
            adapter = new VilleAdapter(this, villeArrayList);
            nListView.setAdapter(adapter);
        }

        nListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                villeCliquer = villeArrayList.get(position).getNom();
                finish();
            }
        });

        nListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                villeArrayList.remove(position);

                adapter.notifyDataSetChanged();

                Toast.makeText(Favoris.this, "Ville Supprimer", Toast.LENGTH_LONG).show();

                return true;
            }

        });

    }

    public void ajouter_favoris(View view) {
        AsyncTask task = new MeteoVille().execute("http://api.openweathermap.org/data/2.5/forecast?q="+rechercher.getText().toString()+"&APPID=dcd4828c83a4954be5e37bb65610b917", ville, rechercher.getText().toString());

        try {
            ville = (Ville) task.get();
            villeArrayList.add(ville);
            adapter = new VilleAdapter(getApplicationContext(), villeArrayList);
            nListView.setAdapter(adapter);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(villeArrayList);
        editor.putString("villeArrayList", json);
        editor.apply();

        Intent data = new Intent();
        data.putExtra("villeCliquer", villeCliquer);
        setResult(RESULT_OK, data);
        super.finish();
    }

}