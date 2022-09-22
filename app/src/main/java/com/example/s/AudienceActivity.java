package com.example.s;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;


public class AudienceActivity extends AppCompatActivity {

    SharedPreferences factions;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    TextView audText, textView;
    Button answer1Button, answer2Button;

    ImageView imageView6, bg;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.audience);

        getSupportActionBar().hide();

        init();

        audText = (TextView) findViewById(R.id.audText);
        answer1Button = (Button) findViewById(R.id.answer1Button);
        answer2Button = (Button) findViewById(R.id.answer2Button);
        textView = (TextView) findViewById(R.id.textView);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        bg = (ImageView) findViewById(R.id.bg);
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

        //================


        int bn = getIntent().getExtras().getInt("kek");
        int rn = 1000 * bn + (int)( Math.random() * 10 );

        switch(bn){
            case 1:
                imageView6.setImageResource(R.drawable.ceoman);
                bg.setImageResource(R.drawable.ceoroom);
                break;

            case 2:
                imageView6.setImageResource(R.drawable.workers);
                bg.setImageResource(R.drawable.workersbackground);
                break;

            case 3:
                imageView6.setImageResource(R.drawable.bankeers);
                bg.setImageResource(R.drawable.bankeersback);
                break;

            case 4:
                imageView6.setImageResource(R.drawable.pmc);
                bg.setImageResource(R.drawable.pmcback);
                break;

            case 5:
                imageView6.setImageResource(R.drawable.mm);
                bg.setImageResource(R.drawable.mmback);
                break;
        }

        // ===============

        String product = "";
        String an1 = "";
        String an2 = "";

        /*SharedPreferences.Editor edit = factions.edit();
                edit.putInt("ceo", a);
                edit.apply();

                int a = factions.getInt("ceo", 0);
                audText.setText(" " + a);*/

            Cursor cursor = mDb.rawQuery("SELECT * FROM events WHERE eventID = " + rn, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                product += cursor.getString(1);
                an1 += cursor.getString(2);
                an2 += cursor.getString(3);
                cursor.moveToNext();
            }
            cursor.close();
        //================
        textView.setText("" + rn);
        //================
        audText.setText(product);
        answer1Button.setText(an1);
        answer2Button.setText(an2);


        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("kek", rn);
                intent.putExtra("an", 1);
                startActivity(intent);
                finish();
            }
        });

        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("kek", rn);
                intent.putExtra("an", 2);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.alpha2,R.anim.alpha);
            }
        });

    }


    private void init() {
        factions = getSharedPreferences("Factions", MODE_PRIVATE);

    }
}
