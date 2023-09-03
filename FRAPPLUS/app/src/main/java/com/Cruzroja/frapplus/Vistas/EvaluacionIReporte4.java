package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EvaluacionIReporte4 extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;

    String Piel = "";
    String Caracteristicas = "";

    RadioButton radNormal,radPalida
            ,radCianotica,radCaliente
            ,radFria,radDiaforesis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_ireporte4);

        radCaliente = findViewById(R.id.radCaliente);
        radFria = findViewById(R.id.radFria);
        radDiaforesis = findViewById(R.id.radDiaforesis);

        // Agregar OnClickListener para radCaliente
        radCaliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radCaliente.isChecked()) {
                    // Desmarcar los otros RadioButtons
                    radFria.setChecked(false);
                    radDiaforesis.setChecked(false);

                    // Asignar el valor de la variable Caracteristicas
                    Caracteristicas = "Caliente";
                }
            }
        });

// Agregar OnClickListener para radFria
        radFria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radFria.isChecked()) {
                    // Desmarcar los otros RadioButtons
                    radCaliente.setChecked(false);
                    radDiaforesis.setChecked(false);

                    // Asignar el valor de la variable Caracteristicas
                    Caracteristicas = "Fria";
                }
            }
        });

// Agregar OnClickListener para radDiaforesis
        radDiaforesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radDiaforesis.isChecked()) {
                    // Desmarcar los otros RadioButtons
                    radCaliente.setChecked(false);
                    radFria.setChecked(false);

                    // Asignar el valor de la variable Caracteristicas
                    Caracteristicas = "Diaforesis";
                }
            }
        });
        ////////////////////////////////////////

        radNormal = findViewById(R.id.radNormal);
        radPalida = findViewById(R.id.radPalida);
        radCianotica = findViewById(R.id.radCianotica);

        // Agregar OnClickListener para radNormal
        radNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radNormal.isChecked()) {
                    // Desmarcar los otros RadioButtons
                    radPalida.setChecked(false);
                    radCianotica.setChecked(false);

                    // Asignar el valor de la variable Piel
                    Piel = "Normal";
                }
            }
        });

// Agregar OnClickListener para radPalida
        radPalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radPalida.isChecked()) {
                    // Desmarcar los otros RadioButtons
                    radNormal.setChecked(false);
                    radCianotica.setChecked(false);

                    // Asignar el valor de la variable Piel
                    Piel = "Palida";
                }
            }
        });

// Agregar OnClickListener para radCianotica
        radCianotica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radCianotica.isChecked()) {
                    // Desmarcar los otros RadioButtons
                    radNormal.setChecked(false);
                    radPalida.setChecked(false);

                    // Asignar el valor de la variable Piel
                    Piel = "Cianotica";
                }
            }
        });



////////////////////////////////////////
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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(EvaluacionIReporte4.this);
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
                Intent intent = new Intent(EvaluacionIReporte4.this, EvaluacionIReporte3.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        //Metodo para insertar y editar informacion
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              // EditText editTextLesionesCausadas = findViewById(R.id.editTextLesionesCausadas);


                //valorSeleccionado
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (Piel.isEmpty()||Caracteristicas.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(EvaluacionIReporte4.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(EvaluacionIReporte4.this);
                    long ID = dbfrapOrden1.insertaEvaluacionIReporte4(claveGenerada,Piel,Caracteristicas);
                    if (ID > 0) {
                        Toast.makeText(EvaluacionIReporte4.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EvaluacionIReporte4.this, MainReporte.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarEvaluacionIReporte4(claveGenerada,Piel,Caracteristicas);
                        if (correcto) {
                            Intent intent = new Intent(EvaluacionIReporte4.this, MainReporte.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(EvaluacionIReporte4.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EvaluacionIReporte4.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(EvaluacionIReporte4.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}