package com.example.meteo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SpashScreen extends Activity {

    private InternetCheck internetCheck;
    private Context mContext;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_spash_screen);

        mContext = this;
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        connection();

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipelayout_splash);

        swipeRefreshLayout.setColorSchemeResources(R.color.refresh, R.color.refresh1, R.color.refresh2);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);

                        connection();

                    }

                }, 3000);
            }
        });
    }

    public void connection(){

        internetCheck = new InternetCheck(mContext);

        if (internetCheck.isConnected()) {
            Thread mThread = new Thread() {
                @Override
                public void run() {
                    try {
                        for(int i=0; i<100; i++){
                            progressBar.incrementProgressBy(1);
                            sleep(10);
                            if(i==20 || i==75){
                                sleep(700);
                                progressBar.incrementProgressBy(20);
                            }
                            if(i==50 || i==99){
                                sleep(100);
                            }
                        }

                        Intent intent = new Intent(getApplicationContext(), Acceuil.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            mThread.start();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setCancelable(true);
            builder.setTitle("Erreur connexion");
            builder.setMessage("Verifier votre connexion internet puis réessayer");
            builder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
