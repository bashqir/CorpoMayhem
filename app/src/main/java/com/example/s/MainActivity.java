package com.example.s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    /*Button button, answer1Button, answer2Button, resultAgreeButton, noMoneyButton, gameoverButton;
    TextView audText, turnText, moneyText, gameoverText, CEORepText, CEOAddRepText, workersRepText, workersAddRepText;
    ImageButton imageButton, chooseCEO, chooseWorkers, chooseBankeers, choosePMC, chooseMM;*/


    ImageButton imageButton;
    SharedPreferences factions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        imageButton = (ImageButton) findViewById(R.id.imageButton);

        SharedPreferences factions = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = factions.edit();


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChooseActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}