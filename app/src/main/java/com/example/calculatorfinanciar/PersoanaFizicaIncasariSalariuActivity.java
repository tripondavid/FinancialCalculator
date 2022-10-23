package com.example.calculatorfinanciar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersoanaFizicaIncasariSalariuActivity extends AppCompatActivity {

    EditText adaugaSalariu;
    EditText rezultatSalariu;
    EditText ultimSalariu;
    Button adauga;
    Button reset;
    float rezultatSalariuInt;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_ultimSalariu = "text1";
    public static final String TEXT_rezultatSalariu = "text2";

    public String text_ultimSalariu;
    public String text_rezultatSalariu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoana_fizica_incasari_salariu);

        adaugaSalariu = (EditText) findViewById(R.id.number_adauga_salariu);
        rezultatSalariu= (EditText) findViewById(R.id.number_suma_salariu);
        ultimSalariu= (EditText) findViewById(R.id.number_ultim_salariu_adaugat);
        adauga= (Button) findViewById(R.id.button_adauga_salariu_persoana_fizica);
        reset = (Button) findViewById(R.id.button_reset_salariu);

        rezultatSalariuInt=0;

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rezultatSalariuInt=0;
                rezultatSalariu.setText(String.valueOf(rezultatSalariuInt));
                ultimSalariu.setText(String.valueOf(0));

                saveData();
            }
        });

        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNumeric(adaugaSalariu.getText().toString())==true) {
                    if (!rezultatSalariu.getText().toString().isEmpty())
                        rezultatSalariuInt = Float.parseFloat(rezultatSalariu.getText().toString());
                    float sumaSalariuInt = Float.parseFloat(adaugaSalariu.getText().toString());
                    rezultatSalariuInt = rezultatSalariuInt + sumaSalariuInt;
                    rezultatSalariu.setText(String.valueOf(rezultatSalariuInt));
                    ultimSalariu.setText(String.valueOf(sumaSalariuInt));

                    saveData();
                }
            }
        });

        loadData();
        updateViews();

    }
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT_ultimSalariu, ultimSalariu.getText().toString());
        editor.putString(TEXT_rezultatSalariu, rezultatSalariu.getText().toString());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text_ultimSalariu = sharedPreferences.getString(TEXT_ultimSalariu, "");
        text_rezultatSalariu = sharedPreferences.getString(TEXT_rezultatSalariu, "");
    }

    public void updateViews() {
        ultimSalariu.setText(text_ultimSalariu);
        rezultatSalariu.setText(text_rezultatSalariu);
    }

    public static boolean isNumeric(String verifica) {
        if (verifica == null)
            return false;
        try {
            Float.parseFloat(verifica);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }
}
