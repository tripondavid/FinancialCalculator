package com.example.calculatorfinanciar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersoanaFizicaCheltuieliAlteCheltuieliActivity extends AppCompatActivity {
    EditText adaugaAltaCheltuiala;
    EditText rezultatAlteCheltuieli;
    EditText ultimaAltaCheltuiala;
    Button adauga;
    Button reset;
    float rezultatAltaCheltuialaInt;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_ultimaAltaCheltuiala = "text19";
    public static final String TEXT_rezultatAltaCheltuiala = "text20";

    private String text_ultimaAltaCheltuiala;
    private String text_rezultatAltaCheltuiala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoana_fizica_cheltuieli_alte_cheltuieli);

        adaugaAltaCheltuiala = (EditText) findViewById(R.id.number_adauga_alte_cheltuieli);
        rezultatAlteCheltuieli = (EditText) findViewById(R.id.number_suma_alte_cheltuieli);
        ultimaAltaCheltuiala = (EditText) findViewById(R.id.number_ultima_alta_cheltuiala);
        adauga = (Button) findViewById(R.id.button_adauga_alta_cheltuiala);
        reset = (Button) findViewById(R.id.button_reset_alte_cheltuieli);

        rezultatAltaCheltuialaInt = 0;

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rezultatAltaCheltuialaInt = 0;
                rezultatAlteCheltuieli.setText(String.valueOf(rezultatAltaCheltuialaInt));
                ultimaAltaCheltuiala.setText(String.valueOf(0));

                saveData();
            }
        });

        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNumeric(adaugaAltaCheltuiala.getText().toString())==true) {
                    if (!rezultatAlteCheltuieli.getText().toString().isEmpty()) {
                        rezultatAltaCheltuialaInt = Float.parseFloat(rezultatAlteCheltuieli.getText().toString());
                    }
                    float sumaCheltuialaInt = Float.parseFloat(adaugaAltaCheltuiala.getText().toString());
                    rezultatAltaCheltuialaInt = rezultatAltaCheltuialaInt + sumaCheltuialaInt;
                    rezultatAlteCheltuieli.setText(String.valueOf(rezultatAltaCheltuialaInt));
                    ultimaAltaCheltuiala.setText(String.valueOf(sumaCheltuialaInt));

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

        editor.putString(TEXT_ultimaAltaCheltuiala, ultimaAltaCheltuiala.getText().toString());
        editor.putString(TEXT_rezultatAltaCheltuiala, rezultatAlteCheltuieli.getText().toString());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text_ultimaAltaCheltuiala = sharedPreferences.getString(TEXT_ultimaAltaCheltuiala,"");
        text_rezultatAltaCheltuiala = sharedPreferences.getString(TEXT_rezultatAltaCheltuiala,"");
    }

    public void updateViews() {
        ultimaAltaCheltuiala.setText(text_ultimaAltaCheltuiala);
        rezultatAlteCheltuieli.setText(text_rezultatAltaCheltuiala);
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