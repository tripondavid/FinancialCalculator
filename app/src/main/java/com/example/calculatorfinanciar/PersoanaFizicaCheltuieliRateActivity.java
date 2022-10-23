package com.example.calculatorfinanciar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersoanaFizicaCheltuieliRateActivity extends AppCompatActivity {

    EditText adaugaRata;
    EditText rezultatRate;
    EditText ultimaRata;
    Button adauga;
    Button reset;
    float rezultatRateInt;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_ultimaRata = "text13";
    public static final String TEXT_rezultatRata = "text14";

    private String text_ultimaRata;
    private String text_rezultatRata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoana_fizica_cheltuieli_rate);

        adaugaRata = (EditText) findViewById(R.id.number_adauga_rata);
        rezultatRate = (EditText) findViewById(R.id.number_suma_rate);
        ultimaRata = (EditText) findViewById(R.id.number_ultima_rata);
        adauga = (Button) findViewById(R.id.button_adauga_rata);
        reset = (Button) findViewById(R.id.button_reset_rate);

        rezultatRateInt=0;

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rezultatRateInt=0;
                rezultatRate.setText(String.valueOf(rezultatRateInt));
                ultimaRata.setText(String.valueOf(0));

                saveData();
            }
        });

        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNumeric(adaugaRata.getText().toString())==true) {
                    if (!rezultatRate.getText().toString().isEmpty())
                        rezultatRateInt = Float.parseFloat(rezultatRate.getText().toString());
                    float sumaRateInt = Float.parseFloat(adaugaRata.getText().toString());
                    rezultatRateInt = rezultatRateInt + sumaRateInt;
                    rezultatRate.setText(String.valueOf(rezultatRateInt));
                    ultimaRata.setText(String.valueOf(sumaRateInt));

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

        editor.putString(TEXT_ultimaRata,ultimaRata.getText().toString());
        editor.putString(TEXT_rezultatRata,rezultatRate.getText().toString());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text_ultimaRata= sharedPreferences.getString(TEXT_ultimaRata,"");
        text_rezultatRata= sharedPreferences.getString(TEXT_rezultatRata,"");
    }

    public void updateViews() {
        ultimaRata.setText(text_ultimaRata);
        rezultatRate.setText(text_rezultatRata);
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