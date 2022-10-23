package com.example.calculatorfinanciar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonPersoanaFizica;
    // Button buttonPersoanaJuridica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPersoanaFizica=(Button) findViewById(R.id.button_persoana_fizica);

        buttonPersoanaFizica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentPersoanaFizica=new Intent(MainActivity.this,PersoanaFizicaActivity.class);
                startActivity(intentPersoanaFizica);
            }
        });

      /*  buttonPersoanaJuridica=(Button) findViewById(R.id.button_persoana_juridica);

        buttonPersoanaJuridica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPersoanaJuridica=new Intent(MainActivity.this,PersoanaJuridicaActivity.class);
                startActivity(intentPersoanaJuridica);
            }
        }); */
    }
}