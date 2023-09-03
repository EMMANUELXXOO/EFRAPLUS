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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DatosLegalesReporte extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;
    String valorSeleccionado = "";
    CheckBox checkbox_P_Municipal,checkbox_AFI,checkbox_P_Ministerial,checkbox_Ejercito,checkbox_P_Judicial,checkbox_Bomberos,checkbox_P_Federal,checkbox_Otro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_legales_reporte);


        EditText editTextNoUnidades = findViewById(R.id.editTextNoUnidades);
        EditText editTextNombreOficiales = findViewById(R.id.editTextNombreOficiales);

        ///////////////////////////////////////////////////////////////////////////////////////
        checkbox_P_Municipal = findViewById(R.id.checkbox_P_Municipal) ;
        checkbox_AFI = findViewById(R.id.checkbox_AFI) ;
        checkbox_P_Ministerial = findViewById(R.id.checkbox_P_Ministerial) ;
        checkbox_Ejercito = findViewById(R.id.checkbox_Ejercito) ;
        checkbox_P_Judicial = findViewById(R.id.checkbox_P_Judicial) ;
        checkbox_Bomberos = findViewById(R.id.checkbox_Bomberos) ;
        checkbox_P_Federal = findViewById(R.id.checkbox_P_Federal) ;
        checkbox_Otro = findViewById(R.id.checkbox_Otro) ;

        checkbox_P_Municipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_P_Municipal.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario

                    checkbox_AFI.setChecked(false);
                    checkbox_P_Ministerial.setChecked(false);
                    checkbox_Ejercito.setChecked(false);
                    checkbox_P_Judicial.setChecked(false);
                    checkbox_Bomberos.setChecked(false);
                    checkbox_P_Federal.setChecked(false);
                    checkbox_Otro.setChecked(false);
                    valorSeleccionado = "Palacio Municipal";

                }
            }

        });
        checkbox_AFI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_AFI.isChecked()) {
                    // Uncheck the other checkboxes if needed
                    checkbox_P_Municipal.setChecked(false);
                    checkbox_P_Ministerial.setChecked(false);
                    checkbox_Ejercito.setChecked(false);
                    checkbox_P_Judicial.setChecked(false);
                    checkbox_Bomberos.setChecked(false);
                    checkbox_P_Federal.setChecked(false);
                    checkbox_Otro.setChecked(false);

                    valorSeleccionado = "AFI"; // Set the selected value
                }
            }
        });

        checkbox_P_Ministerial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_P_Ministerial.isChecked()) {
                    checkbox_P_Municipal.setChecked(false);
                    checkbox_AFI.setChecked(false);
                    checkbox_Ejercito.setChecked(false);
                    checkbox_P_Judicial.setChecked(false);
                    checkbox_Bomberos.setChecked(false);
                    checkbox_P_Federal.setChecked(false);
                    checkbox_Otro.setChecked(false);
                    valorSeleccionado = "Palacio Ministerial";
                }
            }
        });

        checkbox_Ejercito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Ejercito.isChecked()) {
                    checkbox_P_Municipal.setChecked(false);
                    checkbox_AFI.setChecked(false);
                    checkbox_P_Ministerial.setChecked(false);
                    checkbox_P_Judicial.setChecked(false);
                    checkbox_Bomberos.setChecked(false);
                    checkbox_P_Federal.setChecked(false);
                    checkbox_Otro.setChecked(false);
                    valorSeleccionado = "Ejercito";
                }
            }
        });

        checkbox_P_Judicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_P_Judicial.isChecked()) {
                    checkbox_P_Municipal.setChecked(false);
                    checkbox_AFI.setChecked(false);
                    checkbox_P_Ministerial.setChecked(false);
                    checkbox_Ejercito.setChecked(false);
                    checkbox_Bomberos.setChecked(false);
                    checkbox_P_Federal.setChecked(false);
                    checkbox_Otro.setChecked(false);
                    valorSeleccionado = "Palacio Judicial";
                }
            }
        });

        checkbox_Bomberos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Bomberos.isChecked()) {
                    checkbox_P_Municipal.setChecked(false);
                    checkbox_AFI.setChecked(false);
                    checkbox_P_Ministerial.setChecked(false);
                    checkbox_Ejercito.setChecked(false);
                    checkbox_P_Judicial.setChecked(false);
                    checkbox_P_Federal.setChecked(false);
                    checkbox_Otro.setChecked(false);
                    valorSeleccionado = "Bomberos";
                }
            }
        });

        checkbox_P_Federal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_P_Federal.isChecked()) {
                    checkbox_P_Municipal.setChecked(false);
                    checkbox_AFI.setChecked(false);
                    checkbox_P_Ministerial.setChecked(false);
                    checkbox_Ejercito.setChecked(false);
                    checkbox_P_Judicial.setChecked(false);
                    checkbox_Bomberos.setChecked(false);
                    checkbox_Otro.setChecked(false);
                    valorSeleccionado = "Palacio Federal";
                }
            }
        });

        checkbox_Otro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Otro.isChecked()) {
                    checkbox_P_Municipal.setChecked(false);
                    checkbox_AFI.setChecked(false);
                    checkbox_P_Ministerial.setChecked(false);
                    checkbox_Ejercito.setChecked(false);
                    checkbox_P_Judicial.setChecked(false);
                    checkbox_Bomberos.setChecked(false);
                    checkbox_P_Federal.setChecked(false);
                    valorSeleccionado = "Otro";
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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(DatosLegalesReporte.this);
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
                Intent intent = new Intent(DatosLegalesReporte.this, MainReporte.class);
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


                String NoUnidades = editTextNoUnidades.getText().toString();
                String NombreOficiales = editTextNombreOficiales.getText().toString();


                //valorSeleccionado
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (valorSeleccionado.isEmpty()||NoUnidades.isEmpty()||NombreOficiales.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(DatosLegalesReporte.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(DatosLegalesReporte.this);
                    long ID = dbfrapOrden1.insertaDatosLegalesReporte(claveGenerada,valorSeleccionado,NoUnidades,NombreOficiales);
                    if (ID > 0) {
                        Toast.makeText(DatosLegalesReporte.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DatosLegalesReporte.this, DatosLegalesReporte2.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarDatosLegalesReporte(claveGenerada,valorSeleccionado,NoUnidades,NombreOficiales);
                        if (correcto) {
                            Intent intent = new Intent(DatosLegalesReporte.this, DatosLegalesReporte2.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(DatosLegalesReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DatosLegalesReporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(DatosLegalesReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}