package com.example.antonio.tresenraya;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Tablero extends AppCompatActivity implements View.OnClickListener{

    Button boton11,boton12,boton13,boton21,boton22,boton23,boton31,boton32,boton33,botoncm;
    TextView resultado;
    Boolean persona = false;
    Boolean maquina = false;
    public Turno turnoTresEnRaya;
    public List<Integer> casillasMarcadas = new ArrayList<Integer>();
    public List<Integer> casillasNoMarcadas = new ArrayList<Integer>();
    public List<Integer> casillasMarcadasJugador = new ArrayList<Integer>();
    public List<Integer> casillasMarcadasMaquina = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);

        boton11 = findViewById(R.id.button11);
        boton11.setOnClickListener(this);
        boton12 = findViewById(R.id.button12);
        boton12.setOnClickListener(this);
        boton13 = findViewById(R.id.button13);
        boton13.setOnClickListener(this);
        boton21 = findViewById(R.id.button21);
        boton21.setOnClickListener(this);
        boton22 = findViewById(R.id.button22);
        boton22.setOnClickListener(this);
        boton23 = findViewById(R.id.button23);
        boton23.setOnClickListener(this);
        boton31 = findViewById(R.id.button31);
        boton31.setOnClickListener(this);
        boton32 = findViewById(R.id.button32);
        boton32.setOnClickListener(this);
        boton33 = findViewById(R.id.button33);
        boton33.setOnClickListener(this);
        botoncm = findViewById(R.id.buttonComenzar);
        botoncm.setOnClickListener(this);
        resultado = findViewById(R.id.resultado);



    }

    public void iniciarPartida(){
        turnoTresEnRaya = new Turno();
        findViewById(R.id.buttonComenzar).setVisibility(View.GONE);
        int primerJugador = turnoTresEnRaya.getOrdenComienzo();

        if (primerJugador == 1)
            persona = true;
        else
            maquina = true;

        casillasNoMarcadas.add(11);
        casillasNoMarcadas.add(12);
        casillasNoMarcadas.add(13);
        casillasNoMarcadas.add(21);
        casillasNoMarcadas.add(22);
        casillasNoMarcadas.add(23);
        casillasNoMarcadas.add(31);
        casillasNoMarcadas.add(32);
        casillasNoMarcadas.add(33);

        if(maquina) {
            System.out.println("Empieza la máquina.");
            int posicion;
            posicion = casillasNoMarcadas.get(turnoTresEnRaya.getPosicionTurnoMaquina(casillasNoMarcadas));
            System.out.println("Posicion máquina inicio: "+posicion);
            marcarCasillaMaquina(posicion);



        }
        else {
            System.out.println("Empieza el jugador.");
            boton11.setClickable(true);
            boton12.setClickable(true);
            boton13.setClickable(true);
            boton21.setClickable(true);
            boton22.setClickable(true);
            boton23.setClickable(true);
            boton31.setClickable(true);
            boton32.setClickable(true);
            boton33.setClickable(true);
        }


    }

    public void marcarCasillaMaquina(int posicion){
        switch (posicion){
            case 11:
                boton11.setText("X");
                boton11.setTextColor(Color.parseColor("#DD2C00"));
                casillasMarcadas.add(11);
                casillasMarcadasMaquina.add(11);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(11));
                System.out.println("Casilla "+posicion+" marcada por la máquina");
                break;

            case 12:
                boton12.setText("X");
                boton12.setTextColor(Color.parseColor("#DD2C00"));
                casillasMarcadas.add(12);
                casillasMarcadasMaquina.add(12);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(12));
                System.out.println("Casilla "+posicion+" marcada por la máquina");
                break;

            case 13:
                boton13.setText("X");
                boton13.setTextColor(Color.parseColor("#DD2C00"));
                casillasMarcadas.add(13);
                casillasMarcadasMaquina.add(13);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(13));
                System.out.println("Casilla "+posicion+" marcada por la máquina");
                break;

            case 21:
                boton21.setText("X");
                boton21.setTextColor(Color.parseColor("#DD2C00"));
                casillasMarcadas.add(21);
                casillasMarcadasMaquina.add(21);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(21));
                System.out.println("Casilla "+posicion+" marcada por la máquina");
                break;

            case 22:
                boton22.setText("X");
                boton22.setTextColor(Color.parseColor("#DD2C00"));
                casillasMarcadas.add(22);
                casillasMarcadasMaquina.add(22);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(22));
                System.out.println("Casilla "+posicion+" marcada por la máquina");
                break;

            case 23:
                boton23.setText("X");
                boton23.setTextColor(Color.parseColor("#DD2C00"));
                casillasMarcadas.add(23);
                casillasMarcadasMaquina.add(23);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(23));
                System.out.println("Casilla "+posicion+" marcada por la máquina");
                break;

            case 31:
                boton31.setText("X");
                boton31.setTextColor(Color.parseColor("#DD2C00"));
                casillasMarcadas.add(31);
                casillasMarcadasMaquina.add(31);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(31));
                System.out.println("Casilla "+posicion+" marcada por la máquina");
                break;

            case 32:
                boton32.setText("X");
                boton32.setTextColor(Color.parseColor("#DD2C00"));
                casillasMarcadas.add(32);
                casillasMarcadasMaquina.add(32);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(32));
                System.out.println("Casilla "+posicion+" marcada por la máquina");
                break;

            case 33:
                boton33.setText("X");
                boton33.setTextColor(Color.parseColor("#DD2C00"));
                casillasMarcadas.add(33);
                casillasMarcadasMaquina.add(33);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(33));
                System.out.println("Casilla "+posicion+" marcada por la máquina");
                break;
        }
        if(casillasMarcadas.size()<9)
            finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,0,false);
        else
            finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,0,true);
    }

    public void turnoJugador(){
        int numCM;
        numCM = casillasMarcadas.size();
        int numNCM;
        numNCM = casillasNoMarcadas.size();
        int casilla;

        if (numCM > 0) {
            for (int i = 0; i < casillasMarcadas.size(); i++) {
                casilla = casillasMarcadas.get(i);

                switch (casilla) {
                    case 11:
                        boton11.setClickable(false);

                        break;
                    case 12:
                        boton12.setClickable(false);

                        break;
                    case 13:
                        boton13.setClickable(false);

                        break;
                    case 21:
                        boton21.setClickable(false);

                        break;
                    case 22:
                        boton22.setClickable(false);

                        break;
                    case 23:
                        boton23.setClickable(false);

                        break;
                    case 31:
                        boton31.setClickable(false);

                        break;
                    case 32:
                        boton32.setClickable(false);

                        break;
                    case 33:
                        boton33.setClickable(false);

                        break;

                }


            }
        }

        if (numNCM > 0) {
            for (int i = 0; i < casillasNoMarcadas.size(); i++){
                casilla = casillasNoMarcadas.get(i);

                switch(casilla){
                    case 11:
                        boton11.setClickable(true);

                        break;
                    case 12:
                        boton12.setClickable(true);

                        break;
                    case 13:
                        boton13.setClickable(true);

                        break;
                    case 21:
                        boton21.setClickable(true);

                        break;
                    case 22:
                        boton22.setClickable(true);

                        break;
                    case 23:
                        boton23.setClickable(true);

                        break;
                    case 31:
                        boton31.setClickable(true);

                        break;
                    case 32:
                        boton32.setClickable(true);

                        break;
                    case 33:
                        boton33.setClickable(true);

                        break;

                }


            }
        }
    }

    public void turnoMaquina(){
        int posicion;
        boton11.setClickable(false);
        boton12.setClickable(false);
        boton13.setClickable(false);
        boton21.setClickable(false);
        boton22.setClickable(false);
        boton23.setClickable(false);
        boton31.setClickable(false);
        boton32.setClickable(false);
        boton33.setClickable(false);

        posicion = casillasNoMarcadas.get(turnoTresEnRaya.getPosicionTurnoMaquinav2(casillasNoMarcadas, casillasMarcadasJugador, casillasMarcadasMaquina));
        System.out.println("Posicion máquina: "+posicion);
        marcarCasillaMaquina(posicion);
    }

    public void finPartida(List<Integer> j, List<Integer> m, int sigT, boolean f){
        if (turnoTresEnRaya.condicionVictoria(j,m,f) == 0) {
            findViewById(R.id.resultado_layout).setVisibility(View.VISIBLE);
            resultado.setText("Victoria!");
            botoncm.setVisibility(View.VISIBLE);
            botoncm.setText("Volver a jugar");
            System.out.println("Gana el jugador.");
            deshabilitarC();
        }
        if (turnoTresEnRaya.condicionVictoria(j,m,f) == 1) {
            findViewById(R.id.resultado_layout).setVisibility(View.VISIBLE);
            resultado.setText("Derrota!");
            botoncm.setVisibility(View.VISIBLE);
            botoncm.setText("Volver a jugar");
            System.out.println("Gana la máquina.");
            deshabilitarC();
        }
        if (turnoTresEnRaya.condicionVictoria(j,m,f) == 2) {
            findViewById(R.id.resultado_layout).setVisibility(View.VISIBLE);
            resultado.setText("Empate!");
            botoncm.setVisibility(View.VISIBLE);
            botoncm.setText("Volver a jugar");
            System.out.println("Empate.");
            deshabilitarC();
        }
        if (turnoTresEnRaya.condicionVictoria(j,m,f) == 3) {
            if(sigT == 0)
                turnoJugador();
            if(sigT == 1)
                turnoMaquina();
        }

    }

    public void deshabilitarC(){
        boton11.setClickable(false);
        boton12.setClickable(false);
        boton13.setClickable(false);
        boton21.setClickable(false);
        boton22.setClickable(false);
        boton23.setClickable(false);
        boton31.setClickable(false);
        boton32.setClickable(false);
        boton33.setClickable(false);
    }

    public void resetearJuego(){
        boton11.setText("");
        boton12.setText("");
        boton13.setText("");
        boton21.setText("");
        boton22.setText("");
        boton23.setText("");
        boton31.setText("");
        boton32.setText("");
        boton33.setText("");
        boton11.setTextColor(Color.parseColor("#000000"));
        boton12.setTextColor(Color.parseColor("#000000"));
        boton13.setTextColor(Color.parseColor("#000000"));
        boton21.setTextColor(Color.parseColor("#000000"));
        boton22.setTextColor(Color.parseColor("#000000"));
        boton23.setTextColor(Color.parseColor("#000000"));
        boton31.setTextColor(Color.parseColor("#000000"));
        boton32.setTextColor(Color.parseColor("#000000"));
        boton33.setTextColor(Color.parseColor("#000000"));
        findViewById(R.id.resultado_layout).setVisibility(View.GONE);

        casillasMarcadas = new ArrayList<Integer>();
        casillasNoMarcadas = new ArrayList<Integer>();
        casillasMarcadasJugador = new ArrayList<Integer>();
        casillasMarcadasMaquina = new ArrayList<Integer>();

        maquina = false;
        persona = false;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonComenzar:
                if(botoncm.getText().equals("Comenzar")) {
                    findViewById(R.id.c1).setVisibility(View.VISIBLE);
                    findViewById(R.id.c2).setVisibility(View.VISIBLE);
                    findViewById(R.id.c3).setVisibility(View.VISIBLE);
                    iniciarPartida();
                }

                if(botoncm.getText().equals("Volver a jugar")) {
                    resetearJuego();
                    iniciarPartida();
                }

                break;

            case R.id.button11:
                boton11.setText("O");
                casillasMarcadas.add(11);
                casillasMarcadasJugador.add(11);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(11));
                System.out.println("Casilla 11 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button12:
                boton12.setText("O");
                casillasMarcadas.add(12);
                casillasMarcadasJugador.add(12);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(12));
                System.out.println("Casilla 12 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button13:
                boton13.setText("O");
                casillasMarcadas.add(13);
                casillasMarcadasJugador.add(13);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(13));
                System.out.println("Casilla 13 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button21:
                boton21.setText("O");
                casillasMarcadas.add(21);
                casillasMarcadasJugador.add(21);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(21));
                System.out.println("Casilla 21 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button22:
                boton22.setText("O");
                casillasMarcadas.add(22);
                casillasMarcadasJugador.add(22);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(22));
                System.out.println("Casilla 22 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button23:
                boton23.setText("O");
                casillasMarcadas.add(23);
                casillasMarcadasJugador.add(23);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(23));
                System.out.println("Casilla 23 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button31:
                boton31.setText("O");
                casillasMarcadas.add(31);
                casillasMarcadasJugador.add(31);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(31));
                System.out.println("Casilla 31 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button32:
                boton32.setText("O");
                casillasMarcadas.add(32);
                casillasMarcadasJugador.add(32);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(32));
                System.out.println("Casilla 32 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button33:
                boton33.setText("O");
                casillasMarcadas.add(33);
                casillasMarcadasJugador.add(33);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(33));
                System.out.println("Casilla 33 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

        }
    }
}
