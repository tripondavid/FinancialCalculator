package com.example.calculatorfinanciar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileReader;

public class PersoanaFizicaIncasariInvestitiiActivity extends AppCompatActivity {

    EditText adaugaInvestitie;
    EditText rezultatInvestitie;
    EditText ultimaInvestitie;
    Button adauga;
    Button reset;
    float rezultatInvestitieInt;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_ultimaInvestitie = "text3";
    public static final String TEXT_rezultatInvestitie = "text4";

    private String text_ultimaInvestitie;
    private String text_rezultatInvestitie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoana_fizica_incasari_investitii);

        adaugaInvestitie = (EditText) findViewById(R.id.number_adauga_investitie);
        adauga = (Button) findViewById(R.id.button_adauga_investitie_persoana_fizica);
        ultimaInvestitie = (EditText) findViewById(R.id.number_ultima_investitie);
        rezultatInvestitie = (EditText) findViewById(R.id.number_suma_investitie);
        reset = (Button) findViewById(R.id.button_reset_investitii_incasari);

        rezultatInvestitieInt=0;

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rezultatInvestitieInt=0;
                rezultatInvestitie.setText(String.valueOf(rezultatInvestitieInt));
                ultimaInvestitie.setText(String.valueOf(0));

                saveData();
            }
        });

        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNumeric(adaugaInvestitie.getText().toString())==true) {
                    if (!rezultatInvestitie.getText().toString().isEmpty())
                        rezultatInvestitieInt = Float.parseFloat(rezultatInvestitie.getText().toString());
                    float sumaInvestitieInt = Float.parseFloat(adaugaInvestitie.getText().toString());
                    rezultatInvestitieInt = rezultatInvestitieInt + sumaInvestitieInt;
                    rezultatInvestitie.setText(String.valueOf(rezultatInvestitieInt));
                    ultimaInvestitie.setText(String.valueOf(sumaInvestitieInt));

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

        editor.putString(TEXT_ultimaInvestitie, ultimaInvestitie.getText().toString());
        editor.putString(TEXT_rezultatInvestitie, rezultatInvestitie.getText().toString());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text_ultimaInvestitie = sharedPreferences.getString(TEXT_ultimaInvestitie,"");
        text_rezultatInvestitie = sharedPreferences.getString(TEXT_rezultatInvestitie,"");
    }

    public void updateViews() {
        ultimaInvestitie.setText(text_ultimaInvestitie);
        rezultatInvestitie.setText(text_rezultatInvestitie);
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