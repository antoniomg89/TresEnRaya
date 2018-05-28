package com.example.antonio.tresenraya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tablero extends AppCompatActivity implements View.OnClickListener{

    Button boton11,boton12,boton13,boton21,boton22,boton23,boton31,boton32,boton33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);

        boton11 = findViewById(R.id.button11);
        boton12 = findViewById(R.id.button12);
        boton13 = findViewById(R.id.button13);
        boton21 = findViewById(R.id.button21);
        boton22 = findViewById(R.id.button22);
        boton23 = findViewById(R.id.button23);
        boton31 = findViewById(R.id.button31);
        boton32 = findViewById(R.id.button32);
        boton33 = findViewById(R.id.button33);


    }

    @Override
    public void onClick(View v) {

    }
}
