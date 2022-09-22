package com.example.s;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ChooseActivity extends AppCompatActivity {

    ImageButton chooseCEO, chooseWorkers, chooseBankeers, choosePMC, chooseMM;
    TextView turnText, moneyText;
    TextView CEOstrikes, workers_strikes, bankeers_strikes, PMCstrikes, MMstrikes;

    int cn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.choosescreen);

        getSupportActionBar().hide();

        SharedPreferences factions;
        factions = getSharedPreferences("Factions", MODE_PRIVATE);

        chooseCEO = (ImageButton) findViewById(R.id.chooseCEO);
        chooseWorkers = (ImageButton) findViewById(R.id.chooseWorkers);
        chooseBankeers = (ImageButton) findViewById(R.id.chooseBankeers);
        choosePMC = (ImageButton) findViewById(R.id.choosePMC);
        chooseMM = (ImageButton) findViewById(R.id.chooseMM);

        turnText = (TextView) findViewById(R.id.turnText);
        moneyText = (TextView) findViewById(R.id.moneyText);

        CEOstrikes = (TextView) findViewById(R.id.CEOstrikes);
        workers_strikes = (TextView) findViewById(R.id.workers_strikes);
        bankeers_strikes = (TextView) findViewById(R.id.bankeers_strikes);
        PMCstrikes = (TextView) findViewById(R.id.PMCstrikes);
        MMstrikes = (TextView) findViewById(R.id.MMstrikes);

        SharedPreferences.Editor edit = factions.edit();
        int turn = factions.getInt("turn", 0); turn++;
        edit.putInt("turn", turn);



        turnText.setText("" + factions.getInt("turn", 0));
        moneyText.setText("" + factions.getInt("money", 0));

        //===========================================================================

        if (factions.getBoolean("ceo_chosen", false) == true) chooseCEO.setImageResource(R.drawable.n_ceo);
        if (factions.getBoolean("work_chosen", false) == true) chooseWorkers.setImageResource(R.drawable.n_work);
        if (factions.getBoolean("bank_chosen", false) == true) chooseBankeers.setImageResource(R.drawable.n_bank);
        if (factions.getBoolean("pmc_chosen", false) == true) choosePMC.setImageResource(R.drawable.n_pmc);
        if (factions.getBoolean("mm_chosen", false) == true) chooseMM.setImageResource(R.drawable.n_mm);


        //===========================================================================

        if (factions.getBoolean("CEOstrike", true) == true ){
            chooseCEO.setBackgroundResource(R.drawable.icon_ceo_fire1);
            CEOstrikes.setVisibility(View.VISIBLE);
            CEOstrikes.setText("" + factions.getInt("CEOstrikeDays", 0));
        }
        else {
            CEOstrikes.setVisibility(View.INVISIBLE);
            chooseMM.setBackgroundResource(R.drawable.ceoicon);
        }

        if (factions.getBoolean("work_strike", true) == true ){
            chooseWorkers.setBackgroundResource(R.drawable.icon_ceo_fire2);
            workers_strikes.setVisibility(View.VISIBLE);
            workers_strikes.setText("" + factions.getInt("work_strikeDays", 0));
        }
        else {
            workers_strikes.setVisibility(View.INVISIBLE);
            chooseMM.setBackgroundResource(R.drawable.workersicon);
        }

        if (factions.getBoolean("banks_strike", true) == true ){
            chooseBankeers.setBackgroundResource(R.drawable.icon_ceo_fire);
            bankeers_strikes.setVisibility(View.VISIBLE);
            bankeers_strikes.setText("" + factions.getInt("banks_strikeDays", 0));
        }
        else {
            bankeers_strikes.setVisibility(View.INVISIBLE);
            chooseMM.setBackgroundResource(R.drawable.bankeersicon);
        }

        if (factions.getBoolean("PMCstrike", true) == true ){
            choosePMC.setBackgroundResource(R.drawable.icon_ceo_fire4);
            PMCstrikes.setVisibility(View.VISIBLE);
            PMCstrikes.setText("" + factions.getInt("PMCstrikeDays", 0));
        }
        else {
            PMCstrikes.setVisibility(View.INVISIBLE);
            chooseMM.setBackgroundResource(R.drawable.pmcicon);
        }


        if (factions.getBoolean("MMstrike", true) == true ){
            chooseMM.setBackgroundResource(R.drawable.icon_ceo_fire3);
            MMstrikes.setVisibility(View.VISIBLE);
            MMstrikes.setText("" + factions.getInt("MMstrikeDays", 0));
        }
        else {
            MMstrikes.setVisibility(View.INVISIBLE);
            chooseMM.setBackgroundResource(R.drawable.mmicon);
        }

        //==========================================================

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AudienceActivity.class);

                switch (v.getId()) {
                    case R.id.chooseCEO:
                        if (factions.getBoolean("ceo_chosen", false) != true){
                            cn = 1;
                            edit.putBoolean("ceo_chosen", true);
                            edit.putBoolean("work_chosen", false);
                            edit.putBoolean("bank_chosen", false);
                            edit.putBoolean("pmc_chosen", false);
                            edit.putBoolean("mm_chosen", false);
                            edit.putBoolean("canbechosen", true);
                            edit.apply();

                        }
                        break;

                    case R.id.chooseWorkers:
                        if (factions.getBoolean("work_chosen", false) != true){
                            cn = 2;
                            edit.putBoolean("ceo_chosen", false);
                            edit.putBoolean("work_chosen", true);
                            edit.putBoolean("bank_chosen", false);
                            edit.putBoolean("pmc_chosen", false);
                            edit.putBoolean("mm_chosen", false);
                            edit.putBoolean("canbechosen", true);
                            edit.apply();
                        }
                        break;

                    case R.id.chooseBankeers:
                        if (factions.getBoolean("bank_chosen", false) != true){
                            cn = 3;
                            edit.putBoolean("ceo_chosen", false);
                            edit.putBoolean("work_chosen", false);
                            edit.putBoolean("bank_chosen", true);
                            edit.putBoolean("pmc_chosen", false);
                            edit.putBoolean("mm_chosen", false);
                            edit.putBoolean("canbechosen", true);
                            edit.apply();
                        }
                        break;

                    case R.id.choosePMC:
                        if (factions.getBoolean("pmc_chosen", false) != true){
                            cn = 4;
                            edit.putBoolean("ceo_chosen", false);
                            edit.putBoolean("work_chosen", false);
                            edit.putBoolean("bank_chosen", false);
                            edit.putBoolean("pmc_chosen", true);
                            edit.putBoolean("mm_chosen", false);
                            edit.putBoolean("canbechosen", true);
                            edit.apply();
                        }
                        break;

                    case R.id.chooseMM:
                        if (factions.getBoolean("mm_chosen", false) != true){
                            cn = 5;
                            edit.putBoolean("ceo_chosen", false);
                            edit.putBoolean("work_chosen", false);
                            edit.putBoolean("bank_chosen", false);
                            edit.putBoolean("pmc_chosen", false);
                            edit.putBoolean("mm_chosen", true);
                            edit.putBoolean("canbechosen", true);
                            edit.apply();
                        }
                        break;
                }

                intent.putExtra("kek", cn);
                if (factions.getBoolean("canbechosen", false) == true) {
                    edit.putBoolean("canbechosen", false);
                    edit.apply();
                    startActivity(intent);
                    overridePendingTransition(R.anim.alpha2,R.anim.alpha);
                    finish();

                }

            }
        };

        chooseCEO.setOnClickListener(onClickListener);
        chooseWorkers.setOnClickListener(onClickListener);
        chooseBankeers.setOnClickListener(onClickListener);
        choosePMC.setOnClickListener(onClickListener);
        chooseMM.setOnClickListener(onClickListener);
    }

    }



