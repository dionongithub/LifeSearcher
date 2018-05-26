package com.example.dion.lifesearcher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class game extends AppCompatActivity{

    TextView text;
    TextView Title;
    int counter;
    int ranint;
    File file;
    FileOutputStream outputStream;
    String filename = "lifsearcher";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //MobileAds.initialize(this, "ca-app-pub-9288614020154194~7020988590");

        AdView adview = (AdView)findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();

        adview.loadAd(adRequest);

        int max = 100000;
        int min = 10000;

        Random rand = new Random();
        ranint = rand.nextInt(max - min + 1) + min;

        Title = findViewById(R.id.TextTitle);
        text = findViewById(R.id.Textcounter);

        Title.setText("You have to push the button " + ranint + " To get a life.");
    }

    public void Looking(View v){
        counter = counter + 1;
        text.setText("" + this.counter);

        if(counter >= ranint) {
            Title.setText("Congrats you have now your life back.");
        }
    }

    public void reset(View v){
        counter = 0;
        text.setText("" + this.counter);
    }
}