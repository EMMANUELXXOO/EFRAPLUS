package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EvaluacionIReporte2 extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;
    boolean correcto = false;
    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    String Observaciones = "";
    String Auscultacion = "";

    RadioButton RadAutomatismoRegular,radAutomatismoIrregular
            ,radVentilacionRapida,radVentilacionSuperficial
            ,radApnea,radRuidosRNormales,radRuidosRDisminuidos,radRuidosRAusentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_ireporte2);
        //////////////////////////////////////////////////////
        radRuidosRNormales = findViewById(R.id.radRuidosRNormales);
        radRuidosRDisminuidos = findViewById(R.id.radRuidosRDisminuidos);
        radRuidosRAusentes = findViewById(R.id.radRuidosRAusentes);

        radRuidosRNormales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radRuidosRNormales.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radRuidosRDisminuidos.setChecked(false);
                    radRuidosRAusentes.setChecked(false);

                    Auscultacion = "Ruidos Respiratorios Normales";
                    Toast.makeText(EvaluacionIReporte2.this, Auscultacion, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radRuidosRDisminuidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radRuidosRDisminuidos.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radRuidosRNormales.setChecked(false);
                    radRuidosRAusentes.setChecked(false);

                    Auscultacion = "Ruidos Respiratorios Disminuidos";
                    Toast.makeText(EvaluacionIReporte2.this, Auscultacion, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radRuidosRAusentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radRuidosRAusentes.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radRuidosRNormales.setChecked(false);
                    radRuidosRDisminuidos.setChecked(false);

                    Auscultacion = "Ruidos Respiratorios Ausentes";
                    Toast.makeText(EvaluacionIReporte2.this, Auscultacion, Toast.LENGTH_SHORT).show();
                }
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////

        RadAutomatismoRegular = findViewById(R.id.RadAutomatismoRegular);
        radAutomatismoIrregular = findViewById(R.id.radAutomatismoIrregular);
        radVentilacionRapida = findViewById(R.id.radVentilacionRapida);
        radVentilacionSuperficial = findViewById(R.id.radVentilacionSuperficial);
        radApnea = findViewById(R.id.radApnea);

        RadAutomatismoRegular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RadAutomatismoRegular.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radAutomatismoIrregular.setChecked(false);
                    radVentilacionRapida.setChecked(false);
                    radVentilacionSuperficial.setChecked(false);
                    radApnea.setChecked(false);
                    Observaciones  = "Automatismo Regular";
                    Toast.makeText(EvaluacionIReporte2.this, Observaciones, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radAutomatismoIrregular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radAutomatismoIrregular.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    RadAutomatismoRegular.setChecked(false);
                    radVentilacionRapida.setChecked(false);
                    radVentilacionSuperficial.setChecked(false);
                    radApnea.setChecked(false);
                    Observaciones = "Automatismo Irregular";
                    Toast.makeText(EvaluacionIReporte2.this, Observaciones, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radVentilacionRapida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radVentilacionRapida.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    RadAutomatismoRegular.setChecked(false);
                    radAutomatismoIrregular.setChecked(false);
                    radVentilacionSuperficial.setChecked(false);
                    radApnea.setChecked(false);
                    Observaciones = "Ventilación Rápida";
                    Toast.makeText(EvaluacionIReporte2.this, Observaciones, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radVentilacionSuperficial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radVentilacionSuperficial.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    RadAutomatismoRegular.setChecked(false);
                    radAutomatismoIrregular.setChecked(false);
                    radVentilacionRapida.setChecked(false);
                    radApnea.setChecked(false);
                    Observaciones = "Ventilación Superficial";
                    Toast.makeText(EvaluacionIReporte2.this, Observaciones, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radApnea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radApnea.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    RadAutomatismoRegular.setChecked(false);
                    radAutomatismoIrregular.setChecked(false);
                    radVentilacionRapida.setChecked(false);
                    radVentilacionSuperficial.setChecked(false);
                    Observaciones = "Apnea";
                    Toast.makeText(EvaluacionIReporte2.this, Observaciones, Toast.LENGTH_SHORT).show();
                }
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(EvaluacionIReporte2.this);
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
                Intent intent = new Intent(EvaluacionIReporte2.this, EvaluacionIReporte.class);
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
                if (Observaciones.isEmpty()||Auscultacion.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(EvaluacionIReporte2.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(EvaluacionIReporte2.this);
                    long ID = dbfrapOrden1.insertaEvaluacionIReporte2(claveGenerada,Observaciones,Auscultacion);
                    if (ID > 0) {
                        Toast.makeText(EvaluacionIReporte2.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EvaluacionIReporte2.this, EvaluacionIReporte3.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarEvaluacionIReporte2(claveGenerada,Observaciones,Auscultacion);
                        if (correcto) {
                            Intent intent = new Intent(EvaluacionIReporte2.this, EvaluacionIReporte3.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(EvaluacionIReporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EvaluacionIReporte2.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(EvaluacionIReporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}