package com.example.calculatorfinanciar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersoanaFizicaCheltuieliInvestitiiActivity extends AppCompatActivity {

    EditText adaugaCheltuialaInvestitie;
    EditText rezultatCheltuialaInvestitie;
    EditText ultimaCheltuialaInvestitie;
    Button adauga;
    Button reset;
    float rezultatCheltuialaInvestitieInt;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_ultimaCheltuialaInvestitie = "text16";
    public static final String TEXT_rezultatCheltuialaInvestitie = "text17";

    private String text_ultimaCheltuialaInvestitie;
    private String text_rezultatCheltuialaInvestitie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoana_fizica_cheltuieli_investitii);

        adaugaCheltuialaInvestitie = (EditText) findViewById(R.id.number_adauga_cheltuiala_investitie);
        rezultatCheltuialaInvestitie = (EditText) findViewById(R.id.number_suma_cheltuiala_investitie);
        ultimaCheltuialaInvestitie = (EditText) findViewById(R.id.number_ultima_cheltuiala_investitie);
        adauga = (Button) findViewById(R.id.button_adauga_cheltuiala_investitie);
        reset = (Button) findViewById(R.id.button_reset_cheltuieli_investitii);

        rezultatCheltuialaInvestitieInt=0;

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rezultatCheltuialaInvestitieInt=0;
                rezultatCheltuialaInvestitie.setText(String.valueOf(rezultatCheltuialaInvestitieInt));
                ultimaCheltuialaInvestitie.setText(String.valueOf(0));

                saveData();
            }
        });

        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNumeric(adaugaCheltuialaInvestitie.getText().toString())==true) {
                    if (!rezultatCheltuialaInvestitie.getText().toString().isEmpty())
                        rezultatCheltuialaInvestitieInt = Float.parseFloat(rezultatCheltuialaInvestitie.getText().toString());
                    float sumaCheltuialaInvestitieInt = Float.parseFloat(adaugaCheltuialaInvestitie.getText().toString());
                    rezultatCheltuialaInvestitieInt = rezultatCheltuialaInvestitieInt + sumaCheltuialaInvestitieInt;
                    rezultatCheltuialaInvestitie.setText(String.valueOf(rezultatCheltuialaInvestitieInt));
                    ultimaCheltuialaInvestitie.setText(String.valueOf(sumaCheltuialaInvestitieInt));

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

        editor.putString(TEXT_ultimaCheltuialaInvestitie,ultimaCheltuialaInvestitie.getText().toString());
        editor.putString(TEXT_rezultatCheltuialaInvestitie,rezultatCheltuialaInvestitie.getText().toString());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text_ultimaCheltuialaInvestitie = sharedPreferences.getString(TEXT_ultimaCheltuialaInvestitie,"");
        text_rezultatCheltuialaInvestitie = sharedPreferences.getString(TEXT_rezultatCheltuialaInvestitie,"");
    }

    public void updateViews() {
        ultimaCheltuialaInvestitie.setText(text_ultimaCheltuialaInvestitie);
        rezultatCheltuialaInvestitie.setText(text_rezultatCheltuialaInvestitie);
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