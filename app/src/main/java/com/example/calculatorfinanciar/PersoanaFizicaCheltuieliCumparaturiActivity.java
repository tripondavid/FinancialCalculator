package com.example.calculatorfinanciar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersoanaFizicaCheltuieliCumparaturiActivity extends AppCompatActivity {

    EditText adaugaCumparatura;
    EditText rezultatCumparaturi;
    EditText ultimaCumparatura;
    Button adauga;
    Button reset;
    float rezultatCumparaturaInt;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_ultimaCumparatura = "text12";
    public static final String TEXT_rezultatCumparatura = "text11";

    private String text_ultimaCumparatura;
    private String text_rezultatCumparatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoana_fizica_cheltuieli_cumparaturi);

        adaugaCumparatura = (EditText) findViewById(R.id.number_adauga_cumparaturi);
        rezultatCumparaturi = (EditText) findViewById(R.id.number_suma_cumparaturi);
        ultimaCumparatura = (EditText) findViewById(R.id.number_ultima_cumparatura);
        adauga = (Button) findViewById(R.id.button_adauga_cumparaturi);
        reset = (Button) findViewById(R.id.button_reset_cumparaturi);

        rezultatCumparaturaInt=0;

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rezultatCumparaturaInt=0;
                rezultatCumparaturi.setText(String.valueOf(rezultatCumparaturaInt));
                ultimaCumparatura.setText(String.valueOf(0));

                saveData();
            }
        });

        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNumeric(adaugaCumparatura.getText().toString())==true) {
                    if (!rezultatCumparaturi.getText().toString().isEmpty())
                        rezultatCumparaturaInt = Float.parseFloat(rezultatCumparaturi.getText().toString());
                    float sumaCumparaturiInt = Float.parseFloat(adaugaCumparatura.getText().toString());
                    rezultatCumparaturaInt = rezultatCumparaturaInt + sumaCumparaturiInt;
                    rezultatCumparaturi.setText(String.valueOf(rezultatCumparaturaInt));
                    ultimaCumparatura.setText(String.valueOf(sumaCumparaturiInt));

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

        editor.putString(TEXT_ultimaCumparatura, ultimaCumparatura.getText().toString());
        editor.putString(TEXT_rezultatCumparatura, rezultatCumparaturi.getText().toString());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text_ultimaCumparatura = sharedPreferences.getString(TEXT_ultimaCumparatura,"");
        text_rezultatCumparatura = sharedPreferences.getString(TEXT_rezultatCumparatura,"");
    }

    public void updateViews() {
        ultimaCumparatura.setText(text_ultimaCumparatura);
        rezultatCumparaturi.setText(text_rezultatCumparatura);
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