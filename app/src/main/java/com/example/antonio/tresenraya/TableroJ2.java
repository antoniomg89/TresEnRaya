package com.example.antonio.tresenraya;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TableroJ2 extends AppCompatActivity implements View.OnClickListener{

    Button boton11,boton12,boton13,boton21,boton22,boton23,boton31,boton32,boton33,botoncm, botonlog, botonlog2;
    TextView resultado, direccioncorreo;
    TextView correo, contraseña;
    Boolean persona = false;
    Boolean jugador2 = false;
    public Turno turnoTresEnRaya;
    public List<Integer> casillasMarcadas = new ArrayList<Integer>();
    public List<Integer> casillasNoMarcadas = new ArrayList<Integer>();
    public List<Integer> casillasMarcadasJugador = new ArrayList<Integer>();
    public List<Integer> casillasMarcadasMaquina = new ArrayList<Integer>();
    public String TAG;
    public CharSequence simboloM;
    public CharSequence simboloM2;
    public boolean segTurn = false;

    private FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;
    private DatabaseReference refCM;
    GenericTypeIndicator<List<Integer>> t = new GenericTypeIndicator<List<Integer>>() {};
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero_j2);

        boton11 = findViewById(R.id.button11j2);
        boton11.setOnClickListener(this);
        boton12 = findViewById(R.id.button12j2);
        boton12.setOnClickListener(this);
        boton13 = findViewById(R.id.button13j2);
        boton13.setOnClickListener(this);
        boton21 = findViewById(R.id.button21j2);
        boton21.setOnClickListener(this);
        boton22 = findViewById(R.id.button22j2);
        boton22.setOnClickListener(this);
        boton23 = findViewById(R.id.button23j2);
        boton23.setOnClickListener(this);
        boton31 = findViewById(R.id.button31j2);
        boton31.setOnClickListener(this);
        boton32 = findViewById(R.id.button32j2);
        boton32.setOnClickListener(this);
        boton33 = findViewById(R.id.button33j2);
        boton33.setOnClickListener(this);
        botoncm = findViewById(R.id.buttonComenzarj2);
        botoncm.setOnClickListener(this);
        resultado = findViewById(R.id.resultadoj2);
        botonlog = findViewById(R.id.btnSignUp);
        botonlog.setOnClickListener(this);
        botonlog2 = findViewById(R.id.btnSignIn);
        botonlog2.setOnClickListener(this);
        correo = findViewById(R.id.textCorreo);
        contraseña = findViewById(R.id.textPassword);
        direccioncorreo = findViewById(R.id.direccion_email);

        mAuth = FirebaseAuth.getInstance();
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Firebase DB
        refCM = FirebaseDatabase.getInstance().getReference();
        //mMyRef = refCM.child(mFirebaseUser.getUid());



        // Read from the database
        refCM.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                List <Integer> casillasMarcadasFB = dataSnapshot.getValue(t);
                //System.out.println("Casillas marcadas firebase: "+casillasMarcadasFB.size());
                if(casillasMarcadasFB !=null) {
                    int s = casillasMarcadasFB.size();
                    System.out.println("Firebase no null");
                    System.out.println("Tamaño firebase: "+s);
                    if (casillasMarcadas.size() != casillasMarcadasFB.size())
                        marcarCasillasActualizar(casillasMarcadas, casillasMarcadasFB);
                }
                else
                    System.out.println("Firebase null");

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (mFirebaseUser != null){
            // Name, email address, and profile photo Url
            String name = mFirebaseUser.getDisplayName();
            String email = mFirebaseUser.getEmail();
            System.out.println("Usuario actual: "+mFirebaseUser);
            System.out.println("Email del usuario actual: "+email);
            direccioncorreo.setText(email);

            // Check if user's email is verified
            //boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            //String uid = user.getUid();
            findViewById(R.id.layout_Login).setVisibility(View.GONE);
            findViewById(R.id.c1j2).setVisibility(View.VISIBLE);
            findViewById(R.id.c2j2).setVisibility(View.VISIBLE);
            findViewById(R.id.c3j2).setVisibility(View.VISIBLE);
            iniciarPartida();
        }
    }



    public void iniciarPartida(){
        turnoTresEnRaya = new Turno();
        findViewById(R.id.buttonComenzarj2).setVisibility(View.GONE);
        findViewById(R.id.resultado_layoutj2).setVisibility(View.VISIBLE);
        int primerJugador = turnoTresEnRaya.getOrdenComienzo();

        if (primerJugador == 1) {
            persona = true;
            turnoJugador();
            mostrarTurno();
            simboloMarcado(1);
        }
        else {
            jugador2 = true;
            deshabilitarC();
            mostrarTurno();
            simboloMarcado(primerJugador);
        }

        casillasNoMarcadas.add(11);
        casillasNoMarcadas.add(12);
        casillasNoMarcadas.add(13);
        casillasNoMarcadas.add(21);
        casillasNoMarcadas.add(22);
        casillasNoMarcadas.add(23);
        casillasNoMarcadas.add(31);
        casillasNoMarcadas.add(32);
        casillasNoMarcadas.add(33);

        if(jugador2) {

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

    public void marcarCasillasActualizar(List<Integer> l, List<Integer> fb){
        int casilla;


        if (l.size()==0){
            casilla = fb.get(fb.size()-1);

            switch (casilla) {
                case 11:
                    boton11.setText(simboloM2);
                    boton11.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(11);
                    casillasMarcadasMaquina.add(11);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(11));
                    break;
                case 12:
                    boton12.setText(simboloM2);
                    boton12.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(12);
                    casillasMarcadasMaquina.add(12);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(12));
                    break;
                case 13:
                    boton13.setText(simboloM2);
                    boton13.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(13);
                    casillasMarcadasMaquina.add(13);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(13));
                    break;
                case 21:
                    boton21.setText(simboloM2);
                    boton21.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(21);
                    casillasMarcadasMaquina.add(21);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(21));
                    break;
                case 22:
                    boton22.setText(simboloM2);
                    boton22.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(22);
                    casillasMarcadasMaquina.add(22);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(22));
                    break;
                case 23:
                    boton23.setText(simboloM2);
                    boton23.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(23);
                    casillasMarcadasMaquina.add(23);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(23));
                    break;
                case 31:
                    boton31.setText(simboloM2);
                    boton31.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(31);
                    casillasMarcadasMaquina.add(31);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(31));
                    break;
                case 32:
                    boton32.setText(simboloM2);
                    boton32.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(32);
                    casillasMarcadasMaquina.add(32);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(32));
                    break;
                case 33:
                    boton33.setText(simboloM2);
                    boton33.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(33);
                    casillasMarcadasMaquina.add(33);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(33));
                    break;

            }
            cambioTurno();
            mostrarTurno();
        }


        else if (fb.size() > l.size() && l.size() > 0) {
            int sizelocal = l.size();
            int sizefirebase = fb.size();
            int tam = 0;
            int dif = 0;

            if (sizelocal > sizefirebase){
                tam = sizelocal;
                dif = sizelocal-sizefirebase;
            }
            else if (sizelocal < sizefirebase){
                tam = sizefirebase;
                dif = sizefirebase-sizelocal;
            }

            casilla = fb.get(fb.size()-1);

            switch (casilla) {
                case 11:
                    boton11.setText(simboloM2);
                    boton11.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(11);
                    casillasMarcadasMaquina.add(11);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(11));
                    if(casillasMarcadas.size()<9)
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                    else
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);

                    break;
                case 12:
                    boton12.setText(simboloM2);
                    boton12.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(12);
                    casillasMarcadasMaquina.add(12);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(12));
                    if(casillasMarcadas.size()<9)
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                    else
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);

                    break;
                case 13:
                    boton13.setText(simboloM2);
                    boton13.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(13);
                    casillasMarcadasMaquina.add(13);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(13));
                    if(casillasMarcadas.size()<9)
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                    else
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);

                    break;
                case 21:
                    boton21.setText(simboloM2);
                    boton21.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(21);
                    casillasMarcadasMaquina.add(21);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(21));
                    if(casillasMarcadas.size()<9)
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                    else
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);

                    break;
                case 22:
                    boton22.setText(simboloM2);
                    boton22.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(22);
                    casillasMarcadasMaquina.add(22);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(22));
                    if(casillasMarcadas.size()<9)
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                    else
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);

                    break;
                case 23:
                    boton23.setText(simboloM2);
                    boton23.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(23);
                    casillasMarcadasMaquina.add(23);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(23));
                    if(casillasMarcadas.size()<9)
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                    else
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);

                    break;
                case 31:
                    boton31.setText(simboloM2);
                    boton31.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(31);
                    casillasMarcadasMaquina.add(31);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(31));
                    if(casillasMarcadas.size()<9)
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                    else
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);

                    break;
                case 32:
                    boton32.setText(simboloM2);
                    boton32.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(32);
                    casillasMarcadasMaquina.add(32);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(32));
                    if(casillasMarcadas.size()<9)
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                    else
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);

                    break;
                case 33:
                    boton33.setText(simboloM2);
                    boton33.setTextColor(Color.parseColor("#DD2C00"));
                    casillasMarcadas.add(33);
                    casillasMarcadasMaquina.add(33);
                    casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(33));
                    if(casillasMarcadas.size()<9)
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                    else
                        finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);

                    break;

            }
        }

    }

    public void mostrarTurno(){
        if (persona && !jugador2) {
            resultado.setText("Tu Turno");
            resultado.setBackgroundResource(R.drawable.border2);
        }
        else if(jugador2 && !persona) {
            resultado.setText("Turno del oponente");
            resultado.setBackgroundResource(R.drawable.border3);
        }
    }

    public void cambioTurno(){
        if (persona) {
            persona = false;
            jugador2 = true;
            deshabilitarC();
        }
        else if(!persona){
            persona = true;
            jugador2 = false;
            turnoJugador();
        }

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
    public void finPartida(List<Integer> j, List<Integer> m, int sigT, boolean f){
        if (turnoTresEnRaya.condicionVictoria(j,m,f) == 0) {
            findViewById(R.id.resultado_layoutj2).setVisibility(View.VISIBLE);
            resultado.setText("Victoria!");
            botoncm.setVisibility(View.VISIBLE);
            botoncm.setText("Volver a jugar");

            deshabilitarC();
        }
        if (turnoTresEnRaya.condicionVictoria(j,m,f) == 1) {
            findViewById(R.id.resultado_layoutj2).setVisibility(View.VISIBLE);
            resultado.setText("Derrota!");
            botoncm.setVisibility(View.VISIBLE);
            botoncm.setText("Volver a jugar");

            deshabilitarC();
        }
        if (turnoTresEnRaya.condicionVictoria(j,m,f) == 2) {
            findViewById(R.id.resultado_layoutj2).setVisibility(View.VISIBLE);
            resultado.setText("Empate!");
            botoncm.setVisibility(View.VISIBLE);
            botoncm.setText("Volver a jugar");
            System.out.println("Empate.");
            deshabilitarC();
        }
        if (turnoTresEnRaya.condicionVictoria(j,m,f) == 3) {
            cambioTurno();
            mostrarTurno();
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
        refCM = FirebaseDatabase.getInstance().getReference();
        refCM.setValue(null);
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
        findViewById(R.id.resultado_layoutj2).setVisibility(View.GONE);

        casillasMarcadas = new ArrayList<Integer>();
        casillasNoMarcadas = new ArrayList<Integer>();
        casillasMarcadasJugador = new ArrayList<Integer>();
        casillasMarcadasMaquina = new ArrayList<Integer>();

        jugador2 = false;
        persona = false;
    }

    void buttonSignUpEvent(View View) {
        String email;
        String em = String.valueOf(correo.getText());
        email = em;
        String password;
        String ps = String.valueOf(contraseña.getText());
        password = ps;

        loginToFirebase(email, password);
    }


    void buttonSignInEvent(View View) {
        String email;
        String em = String.valueOf(correo.getText());
        email = em;
        String password;
        String ps = String.valueOf(contraseña.getText());
        password = ps;

        signInToFirebase(email, password);
    }

    void loginToFirebase(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                            if (mFirebaseUser != null){

                                // Name, email address, and profile photo Url
                                String name = mFirebaseUser.getDisplayName();
                                String email = mFirebaseUser.getEmail();
                                System.out.println("Usuario actual: "+mFirebaseUser);
                                System.out.println("Email del usuario actual: "+email);
                                //CharSequence dir = String.valueOf(email);
                                direccioncorreo.setText(email);

                                // Check if user's email is verified
                                //boolean emailVerified = user.isEmailVerified();

                                // The user's ID, unique to the Firebase project. Do NOT use this value to
                                // authenticate with your backend server, if you have one. Use
                                // FirebaseUser.getToken() instead.
                                //String uid = user.getUid();
                                findViewById(R.id.layout_Login).setVisibility(View.GONE);
                                findViewById(R.id.c1j2).setVisibility(View.VISIBLE);
                                findViewById(R.id.c2j2).setVisibility(View.VISIBLE);
                                findViewById(R.id.c3j2).setVisibility(View.VISIBLE);
                                iniciarPartida();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(TableroJ2.this, "Sign up failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    void signInToFirebase(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                            if (mFirebaseUser != null){

                                // Name, email address, and profile photo Url
                                String name = mFirebaseUser.getDisplayName();
                                String email = mFirebaseUser.getEmail();
                                System.out.println("Usuario actual: "+mFirebaseUser);
                                System.out.println("Email del usuario actual: "+email);
                                //CharSequence dir = String.valueOf(email);
                                direccioncorreo.setText(email);

                                // Check if user's email is verified
                                //boolean emailVerified = user.isEmailVerified();

                                // The user's ID, unique to the Firebase project. Do NOT use this value to
                                // authenticate with your backend server, if you have one. Use
                                // FirebaseUser.getToken() instead.
                                //String uid = user.getUid();
                                findViewById(R.id.layout_Login).setVisibility(View.GONE);
                                findViewById(R.id.c1j2).setVisibility(View.VISIBLE);
                                findViewById(R.id.c2j2).setVisibility(View.VISIBLE);
                                findViewById(R.id.c3j2).setVisibility(View.VISIBLE);
                                iniciarPartida();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(TableroJ2.this, "Sign in failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }

    void simboloMarcado (int o){
        if (o == 1) {
            simboloM = "O";
            simboloM2 = "X";
        }
        else {
            simboloM = "X";
            simboloM2 = "O";
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.btnSignUp:
                buttonSignUpEvent(v);
                break;

            case R.id.btnSignIn:
                buttonSignInEvent(v);
                break;

            case R.id.buttonComenzarj2:
                if(botoncm.getText().equals("Volver a jugar")) {
                    resetearJuego();
                    iniciarPartida();
                }

                break;

            case R.id.button11j2:
                boton11.setText(simboloM);
                casillasMarcadas.add(11);
                casillasMarcadasJugador.add(11);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(11));

                refCM = FirebaseDatabase.getInstance().getReference();
                refCM.setValue(casillasMarcadas);
                System.out.println("Casilla 11 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);

                break;

            case R.id.button12j2:
                boton12.setText(simboloM);
                casillasMarcadas.add(12);
                casillasMarcadasJugador.add(12);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(12));

                refCM = FirebaseDatabase.getInstance().getReference();
                refCM.setValue(casillasMarcadas);
                System.out.println("Casilla 12 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button13j2:
                boton13.setText(simboloM);
                casillasMarcadas.add(13);
                casillasMarcadasJugador.add(13);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(13));

                refCM = FirebaseDatabase.getInstance().getReference();
                refCM.setValue(casillasMarcadas);
                System.out.println("Casilla 13 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button21j2:
                boton21.setText(simboloM);
                casillasMarcadas.add(21);
                casillasMarcadasJugador.add(21);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(21));

                refCM = FirebaseDatabase.getInstance().getReference();
                refCM.setValue(casillasMarcadas);
                System.out.println("Casilla 21 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button22j2:
                boton22.setText(simboloM);
                casillasMarcadas.add(22);
                casillasMarcadasJugador.add(22);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(22));

                refCM = FirebaseDatabase.getInstance().getReference();
                refCM.setValue(casillasMarcadas);
                System.out.println("Casilla 22 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button23j2:
                boton23.setText(simboloM);
                casillasMarcadas.add(23);
                casillasMarcadasJugador.add(23);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(23));

                refCM = FirebaseDatabase.getInstance().getReference();
                refCM.setValue(casillasMarcadas);
                System.out.println("Casilla 23 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button31j2:
                boton31.setText(simboloM);
                casillasMarcadas.add(31);
                casillasMarcadasJugador.add(31);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(31));

                refCM = FirebaseDatabase.getInstance().getReference();
                refCM.setValue(casillasMarcadas);
                System.out.println("Casilla 31 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button32j2:
                boton32.setText(simboloM);
                casillasMarcadas.add(32);
                casillasMarcadasJugador.add(32);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(32));

                refCM = FirebaseDatabase.getInstance().getReference();
                refCM.setValue(casillasMarcadas);
                System.out.println("Casilla 32 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

            case R.id.button33j2:
                boton33.setText(simboloM);
                casillasMarcadas.add(33);
                casillasMarcadasJugador.add(33);
                casillasNoMarcadas.remove(casillasNoMarcadas.indexOf(33));

                refCM = FirebaseDatabase.getInstance().getReference();
                refCM.setValue(casillasMarcadas);
                System.out.println("Casilla 33 marcada jugador");
                if(casillasMarcadas.size()<9)
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,false);
                else
                    finPartida(casillasMarcadasJugador,casillasMarcadasMaquina,1,true);
                break;

        }
    }
}
