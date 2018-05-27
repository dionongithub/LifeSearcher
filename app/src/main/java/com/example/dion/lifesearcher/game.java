package com.example.dion.lifesearcher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    ProgressBar progressBar;
    int counter;
    int ranint;
    String Filenamecount = "counter.txt";
    String Filenameranint = "ranint.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //MobileAds.initialize(this, "ca-app-pub-9288614020154194~7020988590");

        AdView adview = (AdView)findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();

        adview.loadAd(adRequest);

        progressBar = findViewById(R.id.progressbar);
        Title = findViewById(R.id.TextTitle);
        text = findViewById(R.id.Textcounter);

        if(!checkfile(Filenamecount)){

            counter = 0;

            saveFile(Filenamecount, Integer.toString(counter));
        } else {
            if(readFile(Filenamecount).isEmpty()){

                counter = 0;

                saveFile(Filenamecount, Integer.toString(counter));
            } else {

                counter = Integer.parseInt(readFile(Filenamecount));
            }
        }

        if(!checkfile(Filenameranint)){
            ranint = random();

            saveFile(Filenameranint, Integer.toString(ranint));
        } else {
            if(readFile(Filenameranint).isEmpty()){

                ranint = random();

                saveFile(Filenameranint, Integer.toString(ranint));
            } else {

                ranint = Integer.parseInt(readFile(Filenameranint));
            }
        }

        progressBar.setProgress(counter);
        progressBar.setScaleY(3f);
        progressBar.setMax(ranint);

        Title.setText("You have to push the button " + ranint + " To get a life.");
        text.setText("" + this.counter);
    }

    public void Looking(View v){
        counter = counter + 1;
        text.setText("" + this.counter);

        progressBar.setProgress(counter);

        saveFile(Filenamecount, Integer.toString(counter));

        if(counter >= ranint) {
            Title.setText("Congrats you have now your life back.");
        }
    }

    public void reset(View v){
        counter = 0;
        text.setText("" + this.counter);
        saveFile(Filenamecount, Integer.toString(counter));

        ranint = random();
        saveFile(Filenameranint, Integer.toString(ranint));

        progressBar.setProgress(counter);
        progressBar.setMax(ranint);

        Title.setText("\"You have to push the button " + ranint + " To get a life.");
    }

    public int random(){
        int max = 100000;
        int min = 10000;

        Random rand = new Random();
        ranint = rand.nextInt(max - min + 1) + min;

        return ranint;
    }

    public void saveFile(String file, String text){
        try {
            FileOutputStream fos = openFileOutput(file, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(game.this, "Can't save value to storage.", Toast.LENGTH_SHORT).show();
        }
    }

    public String readFile(String file){
        String text = "";

        try {
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(game.this, "Gegevens ophalen fout", Toast.LENGTH_LONG).show();
        }

        return text;
    }

    public boolean checkfile(String file){
        String text = "";
        boolean check = true;

        try {
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);

        } catch (IOException e) {
            e.printStackTrace();
            check = false;
        }
        return check;
    }
}