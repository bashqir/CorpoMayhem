package com.example.s;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class gameoverscreenActivity extends AppCompatActivity {

    SharedPreferences factions;
    TextView gameoverText, textView4;
    Button gameoverButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.gameoverscreen);
        init();

        getSupportActionBar().hide();

        int t = factions.getInt("turn",0);

        textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setText("Вы управляли корпорацией в течение " + t + " месяцев.");

        SharedPreferences.Editor edit = factions.edit();
        edit.putInt("ceo", 7); edit.putInt("work", 7); edit.putInt("banks", 7); edit.putInt("pmc", 7); edit.putInt("mm", 7);
        edit.putInt("money", 375); edit.putInt("turn", 1);

        edit.putInt("CEOstrikeDays", 3); edit.putInt("work_strikeDays", 3); edit.putInt("banks_strikeDays", 3);edit.putInt("PMCstrikeDays", 3); edit.putInt("MMstrikeDays", 3);
        edit.putBoolean("CEOstrike", false); edit.putBoolean("work_strike", false); edit.putBoolean("banks_strike", false); edit.putBoolean("PMCstrike", false); edit.putBoolean("MMstrike", false);
        edit.putBoolean("ceo_chosen", false); edit.putBoolean("work_chosen", false); edit.putBoolean("bank_chosen", false); edit.putBoolean("pmc_chosen", false); edit.putBoolean("mm_chosen", false);

        edit.apply();

        gameoverButton = (Button) findViewById(R.id.gameoverButton);
        gameoverText = (TextView) findViewById(R.id.gameoverText);

        int go_id = factions.getInt("gameoverid",0);
        String goText = "";
        switch (go_id){
            case 1:
                goText = "Наш бюджет закончился. Итог очевиден - корпорация признана банкротом, а всё иммущество было распродано для оплаты задолженностей перед Всемирным Банком. ";
                break;
            case 2:
                goText = "Избыточное финансирование директората привело к тому, что их влияние стало более весомым, нежели вашим. В один день вас попросту выдворили за порог собственно кабинета, тем самым сместив с поста Гендиректора";
                break;
            case 3:
                goText = "Изобилие денежных выплат и 'лафа' на рабочем месте привели к резкому снижению производительности корпорации. А дальше по накатанной - падение акций, распродажа активов и... Банкротство.";
                break;
            case 4:
                goText = "Тот, кто управляет деньгами - управляет и миром. Своими действиями вы буквально выстлали перед финансистами ковровую дорожку, дав им карт-бланш на захват власти в корпорации. Теперь вы им не нужны.";
                break;
            case 5:
                goText = "Денежные довольствия вашей Частной Военной Компании позволили им силой выдворить вас из кабинета директора. Теперь это ОНИ будут владеть корпорацией, а не корпорация ими.";
                break;
            case 6:
                goText = "Ваши вложения в управление информацией сыграли с вами злую шутку. Заставив ВАС подчиняться инфополю, специалисты сектора СМИ смогли перехватить власть над корпорацией.";
                break;
            case 7:
                goText = "Коррупция в верхах корпоративной иерархии и намеренное воровство денежных активов и оборудования в конечном итоге разрушило корпорацию изнутри.";
                break;
            case 8:
                goText = "Корпоративная культура в вашей фирме стала для работников настолько невыносимой, что ими было принято решение массово уволиться. Итог плачевен - производство остановилось, акции стремительно рухнули, а затем корпорация обанкротилась и ушла в забвение";
                break;
            case 9:
                goText = "После того, как многие финансисты массово уволились, приток капитала в корпорацию резко остановился. И это тотчас повлекло за собой разрушение корпорации как таковой.";
                break;
            case 10:
                goText = "Столь ужасные условия труда ЧВК было не по нраву. Они разорвали контракт. Последствия не заставили себя долго ждать - вооруженные рекетиры силой подмяли вашу корпорацию под себя, а вас выгнали на мороз. Повезло, что не расстреляли.";
                break;
            case 11:
                goText = "Ужасная репутация в СМИ и Интернете привели к массовым бойкотам продукции корпорации и 'Отмене'. Рекламодатели и партнеры отвернулись от вашей фирмы, после чего последовал её незамедлительный крах. ";
                break;

        }


        gameoverText.setText(goText);

        gameoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }



    private void init() {
        factions = getSharedPreferences("Factions", MODE_PRIVATE);
    }
}
