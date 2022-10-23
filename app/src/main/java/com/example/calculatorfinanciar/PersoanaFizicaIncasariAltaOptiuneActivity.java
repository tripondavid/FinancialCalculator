package com.example.calculatorfinanciar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersoanaFizicaIncasariAltaOptiuneActivity extends AppCompatActivity {

    EditText adaugaIncasare;
    EditText rezultatIncasare;
    EditText ultimaIncasare;
    Button adauga;
    Button reset;
    float rezultatIncasareInt;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_ultimaIncasare = "text 5";
    public static final String TEXT_rezultatIncasare = "text 6";

    private String text_ultimaIncasare;
    private String text_rezultatIncasare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoana_fizica_incasari_alta_optiune);

        adaugaIncasare = (EditText) findViewById(R.id.number_adauga_alte_incasari);
        rezultatIncasare = (EditText) findViewById(R.id.number_suma_alte_incasari);
        ultimaIncasare = (EditText) findViewById(R.id.number_ultima_alta_incasare);
        adauga = (Button) findViewById(R.id.button_adauga_alta_incasare);
        reset = (Button) findViewById(R.id.button_reset_alte_incasari);

        rezultatIncasareInt=0;

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rezultatIncasareInt=0;
                rezultatIncasare.setText(String.valueOf(rezultatIncasareInt));
                ultimaIncasare.setText(String.valueOf(0));

                saveData();
            }
        });

        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNumeric(adaugaIncasare.getText().toString())==true) {
                    if (!rezultatIncasare.getText().toString().isEmpty()) {
                        rezultatIncasareInt = Float.parseFloat(rezultatIncasare.getText().toString());
                    }
                    float sumaIncasareInt = Float.parseFloat(adaugaIncasare.getText().toString());
                    rezultatIncasareInt = rezultatIncasareInt + sumaIncasareInt;
                    rezultatIncasare.setText(String.valueOf(rezultatIncasareInt));
                    ultimaIncasare.setText(String.valueOf(sumaIncasareInt));

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

        editor.putString(TEXT_ultimaIncasare,ultimaIncasare.getText().toString());
        editor.putString(TEXT_rezultatIncasare,rezultatIncasare.getText().toString());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text_ultimaIncasare = sharedPreferences.getString(TEXT_ultimaIncasare,"");
        text_rezultatIncasare = sharedPreferences.getString(TEXT_rezultatIncasare,"");
    }

    public void updateViews() {
        ultimaIncasare.setText(text_ultimaIncasare);
        rezultatIncasare.setText(text_rezultatIncasare);
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