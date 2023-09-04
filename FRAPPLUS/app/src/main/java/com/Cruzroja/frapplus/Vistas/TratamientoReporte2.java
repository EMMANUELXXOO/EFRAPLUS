package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TratamientoReporte2 extends AppCompatActivity {
    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;
    String ControlCervical2 = "";
    String OxigenoTerapia = "";
    RadioButton radValvulaMascarilla,radValvuladeDemanda,radVentiladorAutomatico,radPuntasNasales,radMascarillaSimple,radMascarillaReservorio
    ,radMascarillaVenturi;
    EditText editTextFreciencia,editTextVolumen,editTextLtsxMin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamiento_reporte2);


        editTextFreciencia = findViewById(R.id.editTextFreciencia);
        editTextVolumen = findViewById(R.id.editTextVolumen);
        editTextLtsxMin = findViewById(R.id.editTextLtsxMin);

        //,,,,,
        //    ,
//////////////////////////////////////////////////////////////////////////////////////////////
        radValvulaMascarilla = findViewById(R.id.radValvulaMascarilla);
        radValvuladeDemanda = findViewById(R.id.radValvuladeDemanda);
        radVentiladorAutomatico = findViewById(R.id.radVentiladorAutomatico);

        // Para radValvulaMascarilla
        radValvulaMascarilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radValvulaMascarilla.isChecked()) {
                    radValvuladeDemanda.setChecked(false);
                    radVentiladorAutomatico.setChecked(false);
                    ControlCervical2 = "Valvula Mascarilla";
                }
            }
        });

// Para radValvuladeDemanda
        radValvuladeDemanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radValvuladeDemanda.isChecked()) {
                    radValvulaMascarilla.setChecked(false);
                    radVentiladorAutomatico.setChecked(false);
                    ControlCervical2 = "Valvula de Demanda";
                }
            }
        });

// Para radVentiladorAutomatico
        radVentiladorAutomatico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radVentiladorAutomatico.isChecked()) {
                    radValvulaMascarilla.setChecked(false);
                    radValvuladeDemanda.setChecked(false);
                    ControlCervical2 = "Ventilador Automático";
                }
            }
        });


        ////////////////////////////////////////////////////////////////
        radPuntasNasales = findViewById(R.id.radPuntasNasales);
        radMascarillaSimple = findViewById(R.id.radMascarillaSimple);
        radMascarillaReservorio = findViewById(R.id.radMascarillaReservorio);
        radMascarillaVenturi = findViewById(R.id.radMascarillaVenturi);

        radPuntasNasales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radPuntasNasales.isChecked()) {
                    radMascarillaSimple.setChecked(false);
                    radMascarillaReservorio.setChecked(false);
                    radMascarillaVenturi.setChecked(false);
                    OxigenoTerapia = "Puntas Nasales";
                }
            }
        });
// Para radMascarillaSimple
        radMascarillaSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radMascarillaSimple.isChecked()) {
                    radPuntasNasales.setChecked(false);
                    radMascarillaReservorio.setChecked(false);
                    radMascarillaVenturi.setChecked(false);
                    OxigenoTerapia = "Mascarilla Simple";
                }
            }
        });

// Para radMascarillaReservorio
        radMascarillaReservorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radMascarillaReservorio.isChecked()) {
                    radPuntasNasales.setChecked(false);
                    radMascarillaSimple.setChecked(false);
                    radMascarillaVenturi.setChecked(false);
                    OxigenoTerapia = "Mascarilla con Reservorio";
                }
            }
        });

// Para radMascarillaVenturi
        radMascarillaVenturi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radMascarillaVenturi.isChecked()) {
                    radPuntasNasales.setChecked(false);
                    radMascarillaSimple.setChecked(false);
                    radMascarillaReservorio.setChecked(false);
                    OxigenoTerapia = "Mascarilla Venturi";
                }
            }
        });




/////////////////////////////////////////////////////////////////////////////////////
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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(TratamientoReporte2.this);
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
                Intent intent = new Intent(TratamientoReporte2.this, TratamientoReporte.class);
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

                String Frecuencia = editTextFreciencia.getText().toString();;
                String VOl = editTextVolumen.getText().toString();
                String LTS = editTextLtsxMin.getText().toString();
                //valorSeleccionado
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (claveGenerada.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(TratamientoReporte2.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(TratamientoReporte2.this);
                    long ID = dbfrapOrden1.insertaTratamiento2Reporte(claveGenerada,ControlCervical2,Frecuencia,VOl,OxigenoTerapia,LTS);
                    if (ID > 0) {
                        Toast.makeText(TratamientoReporte2.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TratamientoReporte2.this, TratamientoReporte3.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarTratamiento2Reporte(claveGenerada,ControlCervical2,Frecuencia,VOl,OxigenoTerapia,LTS);
                        if (correcto) {
                            Intent intent = new Intent(TratamientoReporte2.this, TratamientoReporte3.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(TratamientoReporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TratamientoReporte2.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(TratamientoReporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}