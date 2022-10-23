package com.example.calculatorfinanciar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class PersoanaFizicaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Spinner variables
    private Spinner spinnerIncasari;
    private Spinner spinnerCheltuieli;

    //EdiTexts for total receipts and total spendings
    EditText incasariTotale;
    EditText cheltuieliTotale;

    //Buttons for receipts and spendings spinners
    Button buttonIncasari;
    Button buttonCheltuieli;

    //Floats to calculate total receipts and total spendings
    Float rezultatIncasariTotale;
    Float rezultatCheltuieliTotale;


    //Shared Preferences in order to save the receipts and the spendings
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_incasariTotale = "text35";
    public static final String TEXT_cheltuieliTotale = "text36";

    public String text_incasariTotale;
    public String text_cheltuieliTotale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoana_fizica);

        rezultatIncasariTotale=Float.valueOf(0);
        rezultatCheltuieliTotale=Float.valueOf(0);


        //Spinner functionality
        spinnerIncasari = findViewById(R.id.spinner_profit_persoana_fizica);

        ArrayAdapter<CharSequence> adapterProfit = ArrayAdapter.createFromResource(this,R.array.optiuni_incasari, android.R.layout.simple_spinner_item);
        adapterProfit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIncasari.setAdapter(adapterProfit);

        spinnerIncasari.setOnItemSelectedListener(this);


        spinnerCheltuieli = findViewById(R.id.spinner_cheltuieli_persoana_fizica);

        ArrayAdapter<CharSequence> adapterCheltuieli = ArrayAdapter.createFromResource(this,R.array.optiuni_cheltuieli, android.R.layout.simple_spinner_item);
        adapterCheltuieli.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCheltuieli.setAdapter(adapterCheltuieli);

        spinnerCheltuieli.setOnItemSelectedListener(this);


        //Buttons for spinners functionality
        buttonIncasari = (Button) findViewById(R.id.button_selectie_spinner_persoana_fizica_incasari);
        buttonCheltuieli = (Button) findViewById(R.id.button_selectie_spinner_persoana_fizica_cheltuieli);

        buttonIncasari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String text=spinnerIncasari.getSelectedItem().toString();
                if(text.equals("Salariu"))
                {
                    Intent intentSalariu=new Intent(PersoanaFizicaActivity.this,PersoanaFizicaIncasariSalariuActivity.class);
                    startActivity(intentSalariu);
                } else if(text.equals("Investitii"))
                {
                    Intent intentInvestitii=new Intent(PersoanaFizicaActivity.this,PersoanaFizicaIncasariInvestitiiActivity.class);
                    startActivity(intentInvestitii);
                } else if(text.equals("Alte incasari"))
                {
                    Intent intentAltaOptiune=new Intent(PersoanaFizicaActivity.this,PersoanaFizicaIncasariAltaOptiuneActivity.class);
                    startActivity(intentAltaOptiune);
                }
            }
        });

        buttonCheltuieli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String text1=spinnerCheltuieli.getSelectedItem().toString();
                if(text1.equals("Cumparaturi"))
                {
                    Intent intentCumparaturi=new Intent(PersoanaFizicaActivity.this,PersoanaFizicaCheltuieliCumparaturiActivity.class);
                    startActivity(intentCumparaturi);
                } else if(text1.equals("Rate"))
                {
                    Intent intentRate=new Intent(PersoanaFizicaActivity.this,PersoanaFizicaCheltuieliRateActivity.class);
                    startActivity(intentRate);
                } else if(text1.equals("Investitii"))
                {
                    Intent intentCheltuieliInvestitii=new Intent(PersoanaFizicaActivity.this, PersoanaFizicaCheltuieliInvestitiiActivity.class);
                    startActivity(intentCheltuieliInvestitii);
                } else if(text1.equals("Alte cheltuieli"))
                {
                    Intent intentAlteCheltuieli=new Intent(PersoanaFizicaActivity.this, PersoanaFizicaCheltuieliAlteCheltuieliActivity.class);
                    startActivity(intentAlteCheltuieli);
                }
            }
        });

        //Total receipts and spendings functionality
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String sharedPrefsIncasariSalariu = sharedPreferences.getString(PersoanaFizicaIncasariSalariuActivity.TEXT_rezultatSalariu,"");
        String sharedPrefsIncasariInvestitii = sharedPreferences.getString(PersoanaFizicaIncasariInvestitiiActivity.TEXT_rezultatInvestitie,"");
        String sharedPrefsIncasariAlteIncasari = sharedPreferences.getString(PersoanaFizicaIncasariAltaOptiuneActivity.TEXT_rezultatIncasare,"");
        String sharedPrefsCheltuieliRate = sharedPreferences.getString(PersoanaFizicaCheltuieliRateActivity.TEXT_rezultatRata,"");
        String sharedPrefsCheltuieliCumparaturi = sharedPreferences.getString(PersoanaFizicaCheltuieliCumparaturiActivity.TEXT_rezultatCumparatura,"");
        String sharedPrefsCheltuieliInvestitii = sharedPreferences.getString(PersoanaFizicaCheltuieliInvestitiiActivity.TEXT_rezultatCheltuialaInvestitie,"");
        String sharedPrefsCheltuieliAlteCheltuieli = sharedPreferences.getString(PersoanaFizicaCheltuieliAlteCheltuieliActivity.TEXT_rezultatAltaCheltuiala,"");
        incasariTotale = (EditText) findViewById(R.id.number_persoana_fizica_incasari_totale);
        cheltuieliTotale = (EditText) findViewById(R.id.number_persoana_fizica_cheltuieli_totale);
        cheltuieliTotale.setText(String.valueOf(0));
        incasariTotale.setText(String.valueOf(0));
        if (!sharedPrefsIncasariSalariu.isEmpty())
        {
            Float incasariSalariuFloat = Float.parseFloat(sharedPrefsIncasariSalariu);
            rezultatIncasariTotale = rezultatIncasariTotale+incasariSalariuFloat;
            incasariTotale.setText(String.valueOf(rezultatIncasariTotale));
        }
        if(!sharedPrefsIncasariInvestitii.isEmpty())
        {
            Float incasariInvestitiiFloat = Float.parseFloat(sharedPrefsIncasariInvestitii);
            rezultatIncasariTotale = rezultatIncasariTotale + incasariInvestitiiFloat;
            incasariTotale.setText(String.valueOf(rezultatIncasariTotale));
        }
        if(!sharedPrefsIncasariAlteIncasari.isEmpty())
        {
            Float incasariAlteIncasariFloat = Float.parseFloat(sharedPrefsIncasariAlteIncasari);
            rezultatIncasariTotale = rezultatIncasariTotale +incasariAlteIncasariFloat;
            incasariTotale.setText(String.valueOf(rezultatIncasariTotale));
        }
        if(!sharedPrefsCheltuieliCumparaturi.isEmpty())
        {
            Float cheltuieliCumparaturiFloat = Float.parseFloat(sharedPrefsCheltuieliCumparaturi);
            rezultatCheltuieliTotale = rezultatCheltuieliTotale + cheltuieliCumparaturiFloat;
            cheltuieliTotale.setText(String.valueOf(rezultatCheltuieliTotale));
        }
        if(!sharedPrefsCheltuieliRate.isEmpty())
        {
            Float cheltuieliRateFloat = Float.parseFloat(sharedPrefsCheltuieliRate);
            rezultatCheltuieliTotale = rezultatCheltuieliTotale + cheltuieliRateFloat;
            cheltuieliTotale.setText(String.valueOf(rezultatCheltuieliTotale));
        }
        if(!sharedPrefsCheltuieliInvestitii.isEmpty())
        {
            Float cheltuieliInvestitiiFloat = Float.parseFloat(sharedPrefsCheltuieliInvestitii);
            rezultatCheltuieliTotale = rezultatCheltuieliTotale + cheltuieliInvestitiiFloat;
            cheltuieliTotale.setText(String.valueOf(rezultatCheltuieliTotale));
        }
        if(!sharedPrefsCheltuieliAlteCheltuieli.isEmpty())
        {
            Float cheltuieliAlteCheltuieliFloat = Float.parseFloat(sharedPrefsCheltuieliAlteCheltuieli);
            rezultatCheltuieliTotale = rezultatCheltuieliTotale + cheltuieliAlteCheltuieliFloat;
            cheltuieliTotale.setText(String.valueOf(rezultatCheltuieliTotale));
        }
        saveData();
        loadData();
        updateViews();

    }

    //Refresh the page when back button is pressed
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    //Save Data of total receipts and spendings
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT_incasariTotale, incasariTotale.getText().toString());
        editor.putString(TEXT_cheltuieliTotale, cheltuieliTotale.getText().toString());

        editor.apply();

    }
    //Load Data of total receipts and spendings
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text_incasariTotale = sharedPreferences.getString(TEXT_incasariTotale,"");
        text_cheltuieliTotale = sharedPreferences.getString(TEXT_cheltuieliTotale,"");
    }
    //Update Data of total receipts and spendings
    public void updateViews() {

        incasariTotale.setText(text_incasariTotale);
        cheltuieliTotale.setText(text_cheltuieliTotale);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}