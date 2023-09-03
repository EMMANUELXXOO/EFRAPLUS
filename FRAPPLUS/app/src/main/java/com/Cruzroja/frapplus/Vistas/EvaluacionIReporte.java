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

public class EvaluacionIReporte extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;

    RadioButton radConciente,radRespuestaEstimuloVerbal,radRespuestaEstimuloDoloroso
            ,radInconciente,radPermanente,radComprometida,radAusente
            ,radPresente;
    String NivelConciencia = "";
    String ViaAerea = "";
    String ReflejoDeDeglucion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_ireporte);


        /////////////////////////////////////////////////RadButtons
        // NivelConciencia
        // ,,
        ///
        // Initialize your radio buttons
        radConciente = findViewById(R.id.radConciente);
        radRespuestaEstimuloVerbal = findViewById(R.id.radRespuestaEstimuloVerbal);
        radRespuestaEstimuloDoloroso = findViewById(R.id.radRespuestaEstimuloDoloroso);
        radInconciente = findViewById(R.id.radInconciente);

// Set click listeners for each radio button
        radConciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radConciente.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radRespuestaEstimuloVerbal.setChecked(false);
                    radRespuestaEstimuloDoloroso.setChecked(false);
                    radInconciente.setChecked(false);
                    NivelConciencia = "Conciente";
                    Toast.makeText(EvaluacionIReporte.this, NivelConciencia, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radRespuestaEstimuloVerbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radRespuestaEstimuloVerbal.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radConciente.setChecked(false);
                    radRespuestaEstimuloDoloroso.setChecked(false);
                    radInconciente.setChecked(false);
                    NivelConciencia = "Respuesta Estímulo Verbal";
                    Toast.makeText(EvaluacionIReporte.this, NivelConciencia, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radRespuestaEstimuloDoloroso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radRespuestaEstimuloDoloroso.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radConciente.setChecked(false);
                    radRespuestaEstimuloVerbal.setChecked(false);
                    radInconciente.setChecked(false);
                    NivelConciencia = "Respuesta Estímulo Doloroso";
                    Toast.makeText(EvaluacionIReporte.this, NivelConciencia, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radInconciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radInconciente.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radConciente.setChecked(false);
                    radRespuestaEstimuloVerbal.setChecked(false);
                    radRespuestaEstimuloDoloroso.setChecked(false);
                    NivelConciencia = "Inconsciente";
                    Toast.makeText(EvaluacionIReporte.this, NivelConciencia, Toast.LENGTH_SHORT).show();
                }
            }
        });



        // Initialize your radio buttons
        radPermanente = findViewById(R.id.radPermanente);
        radComprometida = findViewById(R.id.radComprometida);

// Set click listeners for each radio button
        radPermanente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radPermanente.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radComprometida.setChecked(false);

                    ViaAerea = "Permanente";
                    Toast.makeText(EvaluacionIReporte.this, ViaAerea, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radComprometida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radComprometida.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radPermanente.setChecked(false);

                    ViaAerea = "Comprometida";
                    Toast.makeText(EvaluacionIReporte.this, ViaAerea, Toast.LENGTH_SHORT).show();
                }
            }
        });
///////////////////////////////////////////////////
        //            ,   ,,,
        //            ,
        radAusente = findViewById(R.id.radAusente);
        radPresente = findViewById(R.id.radPresente);
        radAusente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radAusente.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radPermanente.setChecked(false);

                    ReflejoDeDeglucion = "Ausente";
                    Toast.makeText(EvaluacionIReporte.this, ReflejoDeDeglucion, Toast.LENGTH_SHORT).show();
                }
            }
        });
        radPresente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radPresente.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radAusente.setChecked(false);

                    ReflejoDeDeglucion = "Presente";
                    Toast.makeText(EvaluacionIReporte.this, ReflejoDeDeglucion, Toast.LENGTH_SHORT).show();
                }
            }
        });






        /////////////////////////////////////////////
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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(EvaluacionIReporte.this);
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
                Intent intent = new Intent(EvaluacionIReporte.this, MainReporte.class);
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
                if (NivelConciencia.isEmpty()||ViaAerea.isEmpty()||ReflejoDeDeglucion.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(EvaluacionIReporte.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(EvaluacionIReporte.this);
                    long ID = dbfrapOrden1.insertaEvaluacionIReporte(claveGenerada,NivelConciencia,ViaAerea,ReflejoDeDeglucion);
                    if (ID > 0) {
                        Toast.makeText(EvaluacionIReporte.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EvaluacionIReporte.this, EvaluacionIReporte2.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarEvaluacionIReporte(claveGenerada,NivelConciencia,ViaAerea,ReflejoDeDeglucion);
                        if (correcto) {
                            Intent intent = new Intent(EvaluacionIReporte.this, EvaluacionIReporte2.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(EvaluacionIReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EvaluacionIReporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(EvaluacionIReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}