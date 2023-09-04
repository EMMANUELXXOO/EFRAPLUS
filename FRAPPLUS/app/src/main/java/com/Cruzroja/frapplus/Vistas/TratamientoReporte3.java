package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TratamientoReporte3 extends AppCompatActivity {
    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;

    CheckBox checkbox_Hiperventilacion,checkbox_DescomprensionPleuralConAguja,checkbox_HemitoraxDerecho
    ,checkbox_HemitoraxIquierdo;

    RadioButton radPresionDirecta,radCrioterapia,radPresionIndirecta,radMast,radGravedad,radVendajeCompresivo;
     String ControlHemorragias = "";

     String Anexo1,Anexo2,Anexo3 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamiento_reporte3);
        /////////////////////////////////////////////////////////////////////////////////////////////////

        checkbox_Hiperventilacion = findViewById(R.id.checkbox_Hiperventilacion);
        checkbox_Hiperventilacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Hiperventilacion.isChecked()) {

                    Anexo1 = "Hiperventilacion";
                }
            }
        });


        checkbox_DescomprensionPleuralConAguja = findViewById(R.id.checkbox_DescomprensionPleuralConAguja);
        checkbox_DescomprensionPleuralConAguja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_DescomprensionPleuralConAguja.isChecked()) {

                    Anexo2 = "Descomprension Pleural Con Aguja";
                }
            }
        });
        checkbox_HemitoraxDerecho = findViewById(R.id.checkbox_HemitoraxDerecho);
        checkbox_HemitoraxIquierdo = findViewById(R.id.checkbox_HemitoraxIquierdo);
        checkbox_HemitoraxDerecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_HemitoraxDerecho.isChecked()) {

                    Anexo3 = "Hemitorax Derecho";
                }
            }
        });
        checkbox_HemitoraxIquierdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_HemitoraxIquierdo.isChecked()) {

                    Anexo3 = "Hemitorax Izquierdo";
                }
            }
        });



        radPresionDirecta = findViewById(R.id.radPresionDirecta);
        radCrioterapia = findViewById(R.id.radCrioterapia);
        radPresionIndirecta = findViewById(R.id.radPresionIndirecta);
        radMast = findViewById(R.id.radMast);
        radGravedad = findViewById(R.id.radGravedad);
        radVendajeCompresivo = findViewById(R.id.radVendajeCompresivo);

// Para radValvulaMascarilla
        radPresionDirecta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radPresionDirecta.isChecked()) {
                    radCrioterapia.setChecked(false);
                    radPresionIndirecta.setChecked(false);
                    radMast.setChecked(false);
                    radGravedad.setChecked(false);
                    radVendajeCompresivo.setChecked(false);
                    ControlHemorragias = "Presion Directa";
                }
            }
        });


// Para radCrioterapia
        radCrioterapia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radCrioterapia.isChecked()) {
                    radPresionDirecta.setChecked(false);
                    radPresionIndirecta.setChecked(false);
                    radMast.setChecked(false);
                    radGravedad.setChecked(false);
                    radVendajeCompresivo.setChecked(false);
                    ControlHemorragias = "Crioterapia";
                }
            }
        });

// Para radPresionIndirecta
        radPresionIndirecta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radPresionIndirecta.isChecked()) {
                    radPresionDirecta.setChecked(false);
                    radCrioterapia.setChecked(false);
                    radMast.setChecked(false);
                    radGravedad.setChecked(false);
                    radVendajeCompresivo.setChecked(false);
                    ControlHemorragias = "Presion Indirecta";
                }
            }
        });
// Para radMast
        radMast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radMast.isChecked()) {
                    radPresionDirecta.setChecked(false);
                    radCrioterapia.setChecked(false);
                    radPresionIndirecta.setChecked(false);
                    radGravedad.setChecked(false);
                    radVendajeCompresivo.setChecked(false);
                    ControlHemorragias = "Mast";
                }
            }
        });

// Para radGravedad
        radGravedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radGravedad.isChecked()) {
                    radPresionDirecta.setChecked(false);
                    radCrioterapia.setChecked(false);
                    radPresionIndirecta.setChecked(false);
                    radMast.setChecked(false);
                    radVendajeCompresivo.setChecked(false);
                    ControlHemorragias = "Gravedad";
                }
            }
        });

// Para radVendajeCompresivo
        radVendajeCompresivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radVendajeCompresivo.isChecked()) {
                    radPresionDirecta.setChecked(false);
                    radCrioterapia.setChecked(false);
                    radPresionIndirecta.setChecked(false);
                    radMast.setChecked(false);
                    radGravedad.setChecked(false);
                    ControlHemorragias = "Vendaje Compresivo";
                }
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////

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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(TratamientoReporte3.this);
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
                Intent intent = new Intent(TratamientoReporte3.this, TratamientoReporte2.class);
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
                if (claveGenerada.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(TratamientoReporte3.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(TratamientoReporte3.this);
                    long ID = dbfrapOrden1.insertaTratamiento3Reporte(claveGenerada,Anexo1,Anexo2,Anexo3,ControlHemorragias);
                    if (ID > 0) {
                        Toast.makeText(TratamientoReporte3.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TratamientoReporte3.this, TratamientoReporte4.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarTratamiento3Reporte(claveGenerada,Anexo1,Anexo2,Anexo3,ControlHemorragias);
                        if (correcto) {
                            Intent intent = new Intent(TratamientoReporte3.this, TratamientoReporte4.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(TratamientoReporte3.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TratamientoReporte3.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(TratamientoReporte3.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}