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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ResultActivity extends AppCompatActivity {

    boolean gameover; int gameovercode = 0;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    //================
    Button resultAgreeButton, button;
    TextView CEORepText, CEOAddRepText, turnResultscreenText, textView32, MMRepText, MMAddRepText;
    TextView workersRepText, workersAddRepText, bankeersRepText, bankeersAddRepText, PMCRepText, PMCAddRepText;
    TextView CEOPlusRepText, workersPlusRepText, bankeersPlusRepText, PMCPlusRepText, MMPlusRepText;
    TextView CEOMoney, workersMoney, bankeersMoney, PMCMoney, MMMoney, notEnouthMoney;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.resultscreen);

        getSupportActionBar().hide();

        gameover = false;

        resultAgreeButton = (Button) findViewById(R.id.resultAgreeButton);
        button = (Button) findViewById(R.id.button);
        //=======Отображение репутации//======================
        CEORepText = (TextView) findViewById(R.id.CEORepText);
        CEOAddRepText = (TextView) findViewById(R.id.CEOAddRepText);
        workersRepText = (TextView) findViewById(R.id.workersRepText);
        workersAddRepText = (TextView) findViewById(R.id.workersAddRepText);
        bankeersRepText = (TextView) findViewById(R.id.bankeersRepText);
        bankeersAddRepText = (TextView) findViewById(R.id.bankeersAddRepText);
        PMCRepText = (TextView) findViewById(R.id.PMCRepText);
        PMCAddRepText = (TextView) findViewById(R.id.PMCAddRepText);
        MMRepText = (TextView) findViewById(R.id.MMRepText);
        MMAddRepText = (TextView) findViewById(R.id.MMAddRepText);
        //============//Изменнение репутации//===================
        CEOPlusRepText = (TextView) findViewById(R.id.CEOPlusRepText);
        workersPlusRepText = (TextView) findViewById(R.id.workersPlusRepText);
        bankeersPlusRepText = (TextView) findViewById(R.id.bankeersPlusRepText);
        PMCPlusRepText = (TextView) findViewById(R.id.PMCPlusRepText);
        MMPlusRepText = (TextView) findViewById(R.id.MMPlusRepText);
        //===========//Денежное вознаграждение//=================
        CEOMoney = (TextView) findViewById(R.id.CEOMoney);
        workersMoney = (TextView) findViewById(R.id.workersMoney);
        bankeersMoney = (TextView) findViewById(R.id.bankeersMoney);
        PMCMoney = (TextView) findViewById(R.id.PMCMoney);
        MMMoney = (TextView) findViewById(R.id.MMMoney);
        //===========// //==============
        turnResultscreenText = (TextView) findViewById(R.id.turnResultscreenText);
        textView32 = (TextView) findViewById(R.id.textView32);
        notEnouthMoney = (TextView) findViewById(R.id.notEnouthMoney);


        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        SharedPreferences factions;
        factions = getSharedPreferences("Factions", MODE_PRIVATE);

        int a = factions.getInt("ceo", 0);
        int b = factions.getInt("work", 0);
        int c = factions.getInt("banks", 0);
        int d = factions.getInt("pmc", 0);
        int e = factions.getInt("mm", 0);
        int A = 0, B = 0, C = 0, D = 0, E = 0;
        int money = factions.getInt("money", 0);
        int turn = factions.getInt("turn", 0);

        turnResultscreenText.setText("" + turn);

        int rn = getIntent().getExtras().getInt("kek");

        SharedPreferences.Editor edit = factions.edit();

        Cursor cursor = mDb.rawQuery("SELECT * FROM events WHERE eventID = " + rn, null);

        int r1, r2, r3, r4, r5;
        if (getIntent().getExtras().getInt("an") == 1) {
            r1 = 4; r2 = 5; r3 = 6; r4 = 7; r5 = 8;
        }
        else {
            r1 = 10; r2 = 11; r3 = 12; r4 = 13; r5 = 14;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            a = a + cursor.getInt(r1);
            CEOPlusRepText.setText("" + cursor.getInt(r1));
            b = b + cursor.getInt(r2);
            workersPlusRepText.setText("" + cursor.getInt(r2));
            c = c + cursor.getInt(r3);
            bankeersPlusRepText.setText("" + cursor.getInt(r3));
            d = d + cursor.getInt(r4);
            PMCPlusRepText.setText("" + cursor.getInt(r4));
            e = e + cursor.getInt(r5);
            MMPlusRepText.setText("" + cursor.getInt(r5));
            money = money - cursor.getInt(9);
            cursor.moveToNext();
        }
        cursor.moveToFirst();

        cursor.close();

        //=================================
        if (a < 0) a = 0;
        else if (a > 20){
            A = 10; a = 10;
        }
        else if (a > 10) {
            A = a - 10; a = 10;
        }

        CEORepText.setText(fillDollars(a)); if (A > 0) CEOAddRepText.setText(fillDollars(A)); else CEOAddRepText.setText("");

        if (b < 0) b = 0;
        else if (b > 20){
            B = 10; b = 10;
            }
        else if (b > 10) {
            B = b - 10; b = 10;
        }

        workersRepText.setText(fillDollars(b)); if (B > 0) workersAddRepText.setText(fillDollars(B)); else workersAddRepText.setText("");
        if (c < 0) c = 0;
        else if (c > 20){
            C = 10; c = 10;
        }
        else if (c > 10) {
            C = c - 10; c = 10;
        }
        bankeersRepText.setText(fillDollars(c)); if (C > 0) bankeersAddRepText.setText("" + fillDollars(C)); else bankeersAddRepText.setText("");
        if (d < 0) d = 0;
        else if (d > 20){
            D = 10; d = 10;
        }
        else if (d > 10) {
            D = d - 10; d = 10;
        }
        PMCRepText.setText(fillDollars(d)); if (D > 0) PMCAddRepText.setText(fillDollars(D)); else PMCAddRepText.setText("");

        if (e < 0) e = 0;
        else if (e > 20){
            E = 10; e = 10;
        }
        else if (e > 10) {
            E = e - 10; e = 10;
        }
        MMRepText.setText(fillDollars(e)); if (E > 0) MMAddRepText.setText(fillDollars(E)); else MMAddRepText.setText("");

        int Amoney = A * 5, Bmoney = B * 5, Cmoney = C * 5, Dmoney = D * 5, Emoney = E * 5;
        money = money - 25   + (Amoney + Bmoney + Cmoney + Dmoney + Emoney);
        textView32.setText("" + money);
        if (Amoney != 0) CEOMoney.setText("+ " + Amoney + " МЛРД $"); else CEOMoney.setText("");
        if (Bmoney != 0) workersMoney.setText("+ " + Bmoney + " МЛРД $"); else workersMoney.setText("");
        if (Cmoney != 0) bankeersMoney.setText("+ " + Cmoney + " МЛРД $"); else bankeersMoney.setText("");
        if (Dmoney != 0) PMCMoney.setText("+ " + Dmoney + " МЛРД $"); else PMCMoney.setText("");
        if (Emoney != 0) MMMoney.setText("+ " + Emoney + " МЛРД $"                                                                                                                                                  ); else MMMoney.setText("");

        edit.putInt("ceo", a + A); edit.putInt("work", b + B); edit.putInt("banks", c + C); edit.putInt("pmc", d + D); edit.putInt("mm", e + E);
        edit.putInt("money", money); edit.putInt("turn", turn);
        edit.apply();

        //=============================================
        if ((factions.getInt("ceo", 0) <= 3) && factions.getBoolean("CEOstrike", false) == false)
        {
            edit.putBoolean("CEOstrike", true);
            edit.putInt("CEOstrikeDays", 3);
            edit.apply();
        }
        else if (factions.getInt("ceo", 0) <= 3)
        {
            int sd = factions.getInt("CEOstrikeDays", 0) - 1;
            edit.putInt("CEOstrikeDays", sd);
            edit.apply();
        }
        else {
            edit.putBoolean("CEOstrike", false);
            edit.apply();
        }
        //=============================================

        if ((factions.getInt("work", 0) <= 3) && factions.getBoolean("work_strike", false) == false)
        {
            edit.putBoolean("work_strike", true);
            edit.putInt("work_strikeDays", 3);
            edit.apply();
        }
        else if (factions.getInt("work", 0) <= 3)
        {
            int sd = factions.getInt("work_strikeDays", 0) - 1;
            edit.putInt("work_strikeDays", sd);
            edit.apply();
        }
        else {
            edit.putBoolean("work_strike", false);
            edit.apply();
        }

        //=============================================

        if ((factions.getInt("banks", 0) <= 3) && factions.getBoolean("banks_strike", false) == false)
        {
            edit.putBoolean("banks_strike", true);
            edit.putInt("banks_strikeDays", 3);
            edit.apply();
        }
        else if (factions.getInt("banks", 0) <= 3)
        {
            int sd = factions.getInt("banks_strikeDays", 0) - 1;
            edit.putInt("banks_strikeDays", sd);
            edit.apply();
        }
        else
        {
            edit.putBoolean("banks_strike", false);
            edit.apply();
        }

        //=============================================

        if ((factions.getInt("pmc", 0) <= 3) && factions.getBoolean("PMCstrike", false) == false)
        {
            edit.putBoolean("PMCstrike", true);
            edit.putInt("PMCstrikeDays", 3);
            edit.apply();
        }
        else if (factions.getInt("pmc", 0) <= 3)
        {
            int sd = factions.getInt("PMCstrikeDays", 0) - 1;
            edit.putInt("PMCstrikeDays", sd);
            edit.apply();
        }
        else {
            edit.putBoolean("PMCstrike", false);
            edit.apply();
        }

        //=============================================

        if ((factions.getInt("mm", 0) <= 3) && factions.getBoolean("MMstrike", false) == false) {
            edit.putBoolean("MMstrike", true);
            edit.putInt("MMstrikeDays", 3);
            edit.apply();
        }
        else if (factions.getInt("mm", 0) <= 3) {
            int sd = factions.getInt("MMstrikeDays", 0) - 1;
            edit.putInt("MMstrikeDays", sd);
            edit.apply();
        }
        else {
            edit.putBoolean("MMstrike", false);
            edit.apply();
        }

        //=============================================
        edit.apply();

        if (money < 25) {
            gameover = true;
            gameovercode = 1;
        }

        if (A == 10){
            gameover = true;
            gameovercode = 2;
        }

        if (B == 10){
            gameover = true;
            gameovercode = 3;
        }

        if (C == 10){
            gameover = true;
            gameovercode = 4;
        }

        if (D == 10){
            gameover = true;
            gameovercode = 5;
        }

        if (E == 10){
            gameover = true;
            gameovercode = 6;
        }

        if (factions.getInt("CEOstrikeDays", 0) == 0 && (factions.getBoolean("CEOstrike", true)) == true) {
            gameover = true;
            gameovercode = 7;
        }
        if (factions.getInt("work_strikeDays", 0) == 0 && (factions.getBoolean("work_strike", true)) == true) {
            gameover = true;
            gameovercode = 8;
        }
        if (factions.getInt("banks_strikeDays", 0) == 0 && (factions.getBoolean("banks_strike", true)) == true) {
            gameover = true;
            gameovercode = 9;
        }
        if (factions.getInt("PMCstrikeDays", 0) == 0 && (factions.getBoolean("PMCstrike", true)) == true) {
            gameover = true;
            gameovercode = 10;
        }
        if (factions.getInt("MMstrikeDays", 0) == 0 && (factions.getBoolean("MMstrike", true)) == true) {
            gameover = true;
            gameovercode = 11;
        }

        edit.putInt("gameoverid",gameovercode);
        edit.apply();

        if (gameover == true) notEnouthMoney.setVisibility(View.VISIBLE);
        else notEnouthMoney.setVisibility(View.INVISIBLE);



        //========================================
        resultAgreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameover == false) {
                    Intent intent = new Intent(getApplicationContext(), ChooseActivity.class);
                    int turn = factions.getInt("turn", 0);
                    SharedPreferences.Editor edit = factions.edit();
                    edit.putInt("turn", turn);
                    edit.apply();
                    startActivity(intent);
                }

                else {
                    Intent intent = new Intent(getApplicationContext(), gameoverscreenActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.alpha,R.anim.alpha);
                    gameover = false;
                }
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChooseActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.alpha2,R.anim.alpha);

                SharedPreferences.Editor edit = factions.edit();
                edit.putInt("ceo", 7); edit.putInt("work", 7); edit.putInt("banks", 7); edit.putInt("pmc", 7); edit.putInt("mm", 7);
                edit.putInt("money", 375); edit.putInt("turn", 1);
                edit.apply();
            }
        });



    }

    String fillDollars(int j){
        String dollars = "";

        for(int i = 0; i < j; i++){
            dollars += "$";
        }
        return dollars;
    }
}
