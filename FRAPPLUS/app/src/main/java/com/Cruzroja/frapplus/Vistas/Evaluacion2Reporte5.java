package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Evaluacion2Reporte5 extends AppCompatActivity {
    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;

    Spinner SpinnerTraumaScore,SpinnerGlasgow;

    RadioButton radCriticoInestable,radCriticoEstable
            ,radNocriticoInestable,radNocriticoEstable,

    radRojo,radVerde,radAmarillo,radNegro;

    String CondicionPaciente,PrioridadPaciente = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion2_reporte5);
/////////////////////////////////////SPiners
        SpinnerTraumaScore = findViewById(R.id.SpinnerTraumaScore);

        String[] SpinnerTraumaScore1 = {"0","1","2","3","4","5","6","7","8","9","10","11","12"};

        ArrayAdapter<String> SpinnerTraumaScoreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinnerTraumaScore1);
        SpinnerTraumaScoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerTraumaScore.setAdapter(SpinnerTraumaScoreAdapter);


        SpinnerGlasgow = findViewById(R.id.SpinnerGlasgow);

        String[] SpinnerGlasgow1 = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};

        ArrayAdapter<String> SpinnerGlasgowAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinnerGlasgow1);
        SpinnerGlasgowAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerGlasgow.setAdapter(SpinnerGlasgowAdapter);


///////////////////////////////////////////////////////////////////////////////Radiobutons
        // Para radCriticoInestable
       radCriticoInestable = findViewById(R.id.radCriticoInestable);
        radCriticoInestable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radCriticoInestable.isChecked()){
                    radCriticoEstable.setChecked(false);
                    radNocriticoInestable.setChecked(false);
                    radNocriticoEstable.setChecked(false);
                    CondicionPaciente = "Crítico Inestable";
                }
            }
        });

// Para radCriticoEstable
     radCriticoEstable = findViewById(R.id.radCriticoEstable);
        radCriticoEstable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radCriticoEstable.isChecked()){
                    radCriticoInestable.setChecked(false);
                    radNocriticoInestable.setChecked(false);
                    radNocriticoEstable.setChecked(false);
                    CondicionPaciente = "Crítico Estable";
                }
            }
        });

// Para radNocriticoInestable
      radNocriticoInestable = findViewById(R.id.radNocriticoInestable);
        radNocriticoInestable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radNocriticoInestable.isChecked()){
                    radCriticoInestable.setChecked(false);
                    radCriticoEstable.setChecked(false);
                    radNocriticoEstable.setChecked(false);
                    CondicionPaciente = "No Crítico Inestable";
                }
            }
        });

// Para radNocriticoEstable
       radNocriticoEstable = findViewById(R.id.radNocriticoEstable);
        radNocriticoEstable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radNocriticoEstable.isChecked()) {
                    radCriticoInestable.setChecked(false);
                    radCriticoEstable.setChecked(false);
                    radNocriticoInestable.setChecked(false);
                    CondicionPaciente = "No Crítico Estable";
                }
            }
        });


                    ///////////////////////////////////////////////////////////////////////////////

                    // Para radRojo
                    radRojo = findViewById(R.id.radRojo);
                    radRojo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (radRojo.isChecked()) {
                                radVerde.setChecked(false);
                                radAmarillo.setChecked(false);
                                radNegro.setChecked(false);
                                CondicionPaciente = "Rojo";
                            }
                        }
                    });

// Para radVerde
                    radVerde = findViewById(R.id.radVerde);
                    radVerde.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (radVerde.isChecked()) {
                                radRojo.setChecked(false);
                                radAmarillo.setChecked(false);
                                radNegro.setChecked(false);
                                CondicionPaciente = "Verde";
                            }
                        }
                    });

// Para radAmarillo
                    radAmarillo = findViewById(R.id.radAmarillo);
                    radAmarillo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (radAmarillo.isChecked()) {
                                radRojo.setChecked(false);
                                radVerde.setChecked(false);
                                radNegro.setChecked(false);
                                CondicionPaciente = "Amarillo";
                            }
                        }
                    });

// Para radNegro
                    radNegro = findViewById(R.id.radNegro);
                    radNegro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (radNegro.isChecked()) {
                                radRojo.setChecked(false);
                                radVerde.setChecked(false);
                                radAmarillo.setChecked(false);
                                CondicionPaciente = "Negro";
                            }
                        }
                    });


                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    // Verifica si el dispositivo admite la vibración
                    if (vibrator != null && vibrator.hasVibrator()) {
                        // Realiza una vibración corta al hacer clic en el botón
                        vibrator.vibrate(100); // 100 milisegundos de vibración
                    }

                    viewstado = findViewById(R.id.viewestado);
                    viewClave = findViewById(R.id.viewclave);
                    viewfechamodif = findViewById(R.id.viewFechamodif);


                    guardarButton = findViewById(R.id.fabGuardar);
                    btnregresar = findViewById(R.id.fabRegresar);


                    Bundle extras = getIntent().getExtras();
                    if (extras != null) {
                        id = extras.getInt("id");
                    }

                    final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(Evaluacion2Reporte5.this);
                    frapOrden = dbfrapOrdenControl.verFRAPCONTROL(id);

                    // Declaramos la variable para la clave
                    String clave;

                    if (frapOrden != null) {
                        viewstado.setText(String.valueOf(frapOrden.getStatus()));
                        viewstado.setTextColor(getResources().getColor(R.color.colorPrimary));
                        clave = frapOrden.getClave();   // Guardamos la clave
                        viewClave.setText(clave);       // Mostramos la clave
                        viewfechamodif.setText(frapOrden.getFechadeMoficacion());
                    } else {
                        Log.d("VerInformeFrap", "Valor de id: " + id);
                        Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                    }

                    btnregresar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Obtén una instancia del Vibrator
                            // Obtén una instancia del Vibrator
                            Intent intent = new Intent(Evaluacion2Reporte5.this, Evaluacion2Reporte4.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        }
                    });
        //Metodo para insertar y editar informacion
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                /////////////////////////////////////////////////,,,,,
                // Obtener los valores de los EditText que contienen las horas



                String TraumaScore = SpinnerTraumaScore.getSelectedItem().toString();
                String Glasgow = SpinnerGlasgow.getSelectedItem().toString();
                String claveGenerada = frapOrden.getClave();;

//              Realizar validaciones
                if (claveGenerada.isEmpty()){
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(Evaluacion2Reporte5.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(Evaluacion2Reporte5.this);
                    long ID = dbfrapOrden1.insertaEvaluacion2IReporte5(claveGenerada,CondicionPaciente,PrioridadPaciente,TraumaScore,Glasgow);
                    if (ID > 0) {
                        Toast.makeText(Evaluacion2Reporte5.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Evaluacion2Reporte5.this, MainReporte.class);
                        intent.putExtra("id", id);

                        ///////Para los botones bloqueados
// Dentro de la actividad donde deseas bloquear el botón
                        SharedPreferences preferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("botonBloqueado", false);
                        editor.apply();

                        //////
                        SharedPreferences preferences1 = getSharedPreferences("MisPreferencias1", MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = preferences1.edit();
                        editor1.putBoolean("botonBloqueado1", false);
                        editor1.apply();

                        SharedPreferences preferences2 = getSharedPreferences("MisPreferencias2", MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = preferences2.edit();
                        editor2.putBoolean("botonBloqueado2", false);
                        editor2.apply();

                        SharedPreferences preferences3 = getSharedPreferences("MisPreferencias3", MODE_PRIVATE);
                        SharedPreferences.Editor editor3 = preferences3.edit();
                        editor3.putBoolean("botonBloqueado3", false);
                        editor3.apply();

                        SharedPreferences preferences4 = getSharedPreferences("MisPreferencias4", MODE_PRIVATE);
                        SharedPreferences.Editor editor4 = preferences4.edit();
                        editor4.putBoolean("botonBloqueado4", false);
                        editor4.apply();

                        SharedPreferences preferences5 = getSharedPreferences("MisPreferencias5", MODE_PRIVATE);
                        SharedPreferences.Editor editor5 = preferences5.edit();
                        editor5.putBoolean("botonBloqueado5", false);
                        editor5.apply();

                        SharedPreferences preferences6 = getSharedPreferences("MisPreferencias6", MODE_PRIVATE);
                        SharedPreferences.Editor editor6 = preferences6.edit();
                        editor6.putBoolean("botonBloqueado6", false);
                        editor6.apply();

                        SharedPreferences preferences7 = getSharedPreferences("MisPreferencias7", MODE_PRIVATE);
                        SharedPreferences.Editor editor7 = preferences7.edit();
                        editor7.putBoolean("botonBloqueado7", false);
                        editor7.apply();

                        SharedPreferences preferences8 = getSharedPreferences("MisPreferencias8", MODE_PRIVATE);
                        SharedPreferences.Editor editor8 = preferences8.edit();
                        editor8.putBoolean("botonBloqueado8", false);
                        editor8.apply();

                        SharedPreferences preferences9 = getSharedPreferences("MisPreferencias9", MODE_PRIVATE);
                        SharedPreferences.Editor editor9 = preferences9.edit();
                        editor9.putBoolean("botonBloqueado9", true);
                        editor9.apply();



                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarEvaluacion2Reporte5(claveGenerada,CondicionPaciente,PrioridadPaciente,TraumaScore,Glasgow);
                        if (correcto) {
                            Intent intent = new Intent(Evaluacion2Reporte5.this, MainReporte.class);
                            intent.putExtra("id", id);

                            ///////Para los botones bloqueados
// Dentro de la actividad donde deseas bloquear el botón
                            SharedPreferences preferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("botonBloqueado", false);
                            editor.apply();

                            //////
                            SharedPreferences preferences1 = getSharedPreferences("MisPreferencias1", MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = preferences1.edit();
                            editor1.putBoolean("botonBloqueado1", false);
                            editor1.apply();

                            SharedPreferences preferences2 = getSharedPreferences("MisPreferencias2", MODE_PRIVATE);
                            SharedPreferences.Editor editor2 = preferences2.edit();
                            editor2.putBoolean("botonBloqueado2", false);
                            editor2.apply();

                            SharedPreferences preferences3 = getSharedPreferences("MisPreferencias3", MODE_PRIVATE);
                            SharedPreferences.Editor editor3 = preferences3.edit();
                            editor3.putBoolean("botonBloqueado3", false);
                            editor3.apply();

                            SharedPreferences preferences4 = getSharedPreferences("MisPreferencias4", MODE_PRIVATE);
                            SharedPreferences.Editor editor4 = preferences4.edit();
                            editor4.putBoolean("botonBloqueado4", false);
                            editor4.apply();

                            SharedPreferences preferences5 = getSharedPreferences("MisPreferencias5", MODE_PRIVATE);
                            SharedPreferences.Editor editor5 = preferences5.edit();
                            editor5.putBoolean("botonBloqueado5", false);
                            editor5.apply();

                            SharedPreferences preferences6 = getSharedPreferences("MisPreferencias6", MODE_PRIVATE);
                            SharedPreferences.Editor editor6 = preferences6.edit();
                            editor6.putBoolean("botonBloqueado6", false);
                            editor6.apply();

                            SharedPreferences preferences7 = getSharedPreferences("MisPreferencias7", MODE_PRIVATE);
                            SharedPreferences.Editor editor7 = preferences7.edit();
                            editor7.putBoolean("botonBloqueado7", false);
                            editor7.apply();

                            SharedPreferences preferences8 = getSharedPreferences("MisPreferencias8", MODE_PRIVATE);
                            SharedPreferences.Editor editor8 = preferences8.edit();
                            editor8.putBoolean("botonBloqueado8", false);
                            editor8.apply();

                            SharedPreferences preferences9 = getSharedPreferences("MisPreferencias9", MODE_PRIVATE);
                            SharedPreferences.Editor editor9 = preferences9.edit();
                            editor9.putBoolean("botonBloqueado9", true);
                            editor9.apply();


                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Evaluacion2Reporte5.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Evaluacion2Reporte5.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(Evaluacion2Reporte5.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
                }
            }

