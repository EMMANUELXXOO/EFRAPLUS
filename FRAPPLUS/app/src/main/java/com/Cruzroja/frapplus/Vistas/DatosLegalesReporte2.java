package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DatosLegalesReporte2 extends AppCompatActivity {
    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_legales_reporte2);

        EditText editTextTipoyMarcaV1 = findViewById(R.id.editTextTipoyMarcaV1);
        EditText editTextTipoyMarcaV2 = findViewById(R.id.editTextTipoyMarcaV2);
        EditText editTextTipoyMarcaV3 = findViewById(R.id.editTextTipoyMarcaV3);

        EditText editTextPlacaV1 = findViewById(R.id.editTextPlacaV1);
        EditText editTextPlacaV2 = findViewById(R.id.editTextPlacaV2);
        EditText editTextPlacaV3 = findViewById(R.id.editTextPlacaV3);
        EditText editTextPosicionOrientacionPaciente = findViewById(R.id.editTextPosicionOrientacionPaciente);


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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(DatosLegalesReporte2.this);
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
                // Obtén una instancia del Vibrator
                Intent intent = new Intent(DatosLegalesReporte2.this, DatosLegalesReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        //Metodo para insertar y editar informacion
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Obtener referencias a los EditText y Spinners
                // Obtener los valores de los EditText que contienen las horas
                // EditText editTextTipoyMarcaV1 = findViewById(R.id.editTextTipoyMarcaV1);
                //        EditText editTextTipoyMarcaV2 = findViewById(R.id.editTextTipoyMarcaV2);
                //        EditText editTextTipoyMarcaV3 = findViewById(R.id.editTextTipoyMarcaV3);
                //
                //        EditText editTextPlacaV1 = findViewById(R.id.editTextPlacaV1);
                //        EditText editTextPlacaV2 = findViewById(R.id.editTextPlacaV2);
                //        EditText editTextPlacaV3 = findViewById(R.id.editTextPlacaV3);


                String TipoyMarcaV1 = editTextTipoyMarcaV1.getText().toString();
                String TipoyMarcaV2 = editTextTipoyMarcaV2.getText().toString();
                String TipoyMarcaV3 = editTextTipoyMarcaV3.getText().toString();

                String PlacaV1 = editTextPlacaV1.getText().toString();
                String PlacaV2 = editTextPlacaV2.getText().toString();
                String PlacaV3 = editTextPlacaV3.getText().toString();

                String PosicionOrientacionPaciente = editTextPosicionOrientacionPaciente.getText().toString();
                //valorSeleccionado
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (TipoyMarcaV1.isEmpty()||PlacaV1.isEmpty()||PosicionOrientacionPaciente.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(DatosLegalesReporte2.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(DatosLegalesReporte2.this);
                    long ID = dbfrapOrden1.insertaDatosLegales2Reporte(claveGenerada,TipoyMarcaV1,PlacaV1,TipoyMarcaV2,PlacaV2,TipoyMarcaV3,PlacaV3,PosicionOrientacionPaciente);
                    if (ID > 0) {
                        Toast.makeText(DatosLegalesReporte2.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DatosLegalesReporte2.this, DatosLegalesReporte3.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarDatosLegales2Reporte(claveGenerada,TipoyMarcaV1,PlacaV1,TipoyMarcaV2,PlacaV2,TipoyMarcaV3,PlacaV3,PosicionOrientacionPaciente);
                        if (correcto) {
                            Intent intent = new Intent(DatosLegalesReporte2.this, DatosLegalesReporte3.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(DatosLegalesReporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DatosLegalesReporte2.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(DatosLegalesReporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}