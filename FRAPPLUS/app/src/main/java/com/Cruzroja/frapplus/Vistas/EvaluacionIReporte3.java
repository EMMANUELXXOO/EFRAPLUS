package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EvaluacionIReporte3 extends AppCompatActivity {
    boolean correcto = false;
    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    String Hemitorax = "";
    String Sitio = "";
    String PresenciaPulsos = "";
    String Calidad = "";

    CheckBox checkbox_Derecho,checkbox_Izquierdo,checkbox_Apical,checkbox_Base,checkbox_Carotideo,
            checkbox_Radial,checkbox_ParoRespiratorio,checkbox_Rapido,checkbox_Ritmico,checkbox_Lento,
            checkbox_Arritmico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_ireporte3);

        checkbox_Derecho = findViewById(R.id.checkbox_Derecho);
        checkbox_Izquierdo = findViewById(R.id.checkbox_Izquierdo);

        checkbox_Derecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Derecho.isChecked()) {
                    checkbox_Izquierdo.setChecked(false);

                    Hemitorax= "Derecho";
                }
            }
        });
        checkbox_Izquierdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Izquierdo.isChecked()) {

                    checkbox_Derecho.setChecked(false);

                    Hemitorax= "Izquierdo";
                }
            }
        });

///////////////////////////////////////////


        checkbox_Apical = findViewById(R.id.checkbox_Apical);
        checkbox_Base = findViewById(R.id.checkbox_Base);


        checkbox_Apical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Apical.isChecked()) {
                    checkbox_Base.setChecked(false);

                    Sitio= "Apical";
                }
            }
        });
        checkbox_Base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Base.isChecked()) {

                    checkbox_Apical.setChecked(false);

                    Sitio= "Base";
                }
            }
        });



        //////////////////////////////////////////
        checkbox_Carotideo = findViewById(R.id.checkbox_Carotideo);
        checkbox_Radial = findViewById(R.id.checkbox_Radial);
        checkbox_ParoRespiratorio = findViewById(R.id.checkbox_ParoRespiratorio);

        checkbox_Carotideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Carotideo.isChecked()) {
                    checkbox_Radial.setChecked(false);
                    checkbox_ParoRespiratorio.setChecked(false);
                    PresenciaPulsos= "Carotideo";
                }
            }
        });
        checkbox_Radial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Radial.isChecked()) {

                    checkbox_Carotideo.setChecked(false);
                    checkbox_ParoRespiratorio.setChecked(false);

                    PresenciaPulsos= "Radial";
                }
            }
        });
        checkbox_ParoRespiratorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_ParoRespiratorio.isChecked()) {
                    checkbox_Carotideo.setChecked(false);
                    checkbox_Apical.setChecked(false);

                    PresenciaPulsos= "Paro Respiratorio";
                }
            }
        });

        // Obtener referencias a los CheckBox
       checkbox_Rapido = findViewById(R.id.checkbox_Rapido);
        checkbox_Ritmico = findViewById(R.id.checkbox_Ritmico);
       checkbox_Lento = findViewById(R.id.checkbox_Lento);
       checkbox_Arritmico = findViewById(R.id.checkbox_Arritmico);

// Agregar OnClickListener para checkbox_Rapido
        checkbox_Rapido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Rapido.isChecked()) {
                    // Desmarcar otros CheckBox
                    checkbox_Ritmico.setChecked(false);
                    checkbox_Lento.setChecked(false);
                    checkbox_Arritmico.setChecked(false);

                    // Asignar el valor de la variable Calidad
                    Calidad = "Rapido";
                }
            }
        });

// Agregar OnClickListener para checkbox_Ritmico
        checkbox_Ritmico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Ritmico.isChecked()) {
                    // Desmarcar otros CheckBox
                    checkbox_Rapido.setChecked(false);
                    checkbox_Lento.setChecked(false);
                    checkbox_Arritmico.setChecked(false);

                    // Asignar el valor de la variable Calidad
                    Calidad = "Ritmico";
                }
            }
        });

// Agregar OnClickListener para checkbox_Lento
        checkbox_Lento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Lento.isChecked()) {
                    // Desmarcar otros CheckBox
                    checkbox_Rapido.setChecked(false);
                    checkbox_Ritmico.setChecked(false);
                    checkbox_Arritmico.setChecked(false);

                    // Asignar el valor de la variable Calidad
                    Calidad = "Lento";
                }
            }
        });

// Agregar OnClickListener para checkbox_Arritmico
        checkbox_Arritmico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Arritmico.isChecked()) {
                    // Desmarcar otros CheckBox
                    checkbox_Rapido.setChecked(false);
                    checkbox_Ritmico.setChecked(false);
                    checkbox_Lento.setChecked(false);

                    // Asignar el valor de la variable Calidad
                    Calidad = "Arritmico";
                }
            }
        });





        //////////////////////////////////////////////////////////////////////////

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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(EvaluacionIReporte3.this);
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
                Intent intent = new Intent(EvaluacionIReporte3.this, EvaluacionIReporte2.class);
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
                if (Calidad.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(EvaluacionIReporte3.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(EvaluacionIReporte3.this);
                    long ID = dbfrapOrden1.insertaEvaluacionIReporte3(claveGenerada,Hemitorax,Sitio,PresenciaPulsos,Calidad);
                    if (ID > 0) {
                        Toast.makeText(EvaluacionIReporte3.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EvaluacionIReporte3.this, EvaluacionIReporte4.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarEvaluacionIReporte3(claveGenerada,Hemitorax,Sitio,PresenciaPulsos,Calidad);
                        if (correcto) {
                            Intent intent = new Intent(EvaluacionIReporte3.this, EvaluacionIReporte4.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(EvaluacionIReporte3.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EvaluacionIReporte3.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(EvaluacionIReporte3.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}