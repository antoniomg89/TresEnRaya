package com.example.antonio.tresenraya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeleccionModo extends AppCompatActivity implements View.OnClickListener{

    Button maquina, jugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_modo);

        maquina = findViewById(R.id.btnMaquina);
        jugador = findViewById(R.id.btnJugador);

        maquina.setOnClickListener(this);
        jugador.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnMaquina:
                Intent intent = new Intent(this, Tablero.class);
                startActivity(intent);
                break;
            case R.id.btnJugador:
                Intent intent2 = new Intent(this, TableroJ2.class);
                startActivity(intent2);
                break;
        }
    }
}
