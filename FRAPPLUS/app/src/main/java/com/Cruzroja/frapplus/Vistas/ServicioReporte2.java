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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ServicioReporte2 extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private Spinner SpinerDelegacionM;
    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    boolean correcto = false;
    int id;
    /////////////////////////////////
    String valorSeleccionado = "";
    RadioGroup radioGroup;
EditText editTextCallePrincipal,editTextCalle1,editTextCalle2,editTextColonia;
    RadioButton radHogar,radRecreacion,radTrabajo,radViaPublica,radEscuela,radTransportePublico,radOtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_reporte2);


        // Agrega la inicialización de los EditText
        editTextCallePrincipal = findViewById(R.id.editTextCallePrincipal);
        editTextCalle1 = findViewById(R.id.editTextCalle1);
        editTextCalle2 = findViewById(R.id.editTextCalle2);
        editTextColonia = findViewById(R.id.editTextColonia);

        radHogar=findViewById(R.id.radHogar);
        radRecreacion=findViewById(R.id.radRecreacion);
        radTrabajo=findViewById(R.id.radTrabajo);
        radViaPublica=findViewById(R.id.radViaPublica);
        radEscuela=findViewById(R.id.radEscuela);
        radTransportePublico=findViewById(R.id.radTransportePublico);
        radOtro=findViewById(R.id.radOtro);

        radHogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radHogar.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radRecreacion.setChecked(false);
                    radTrabajo.setChecked(false);
                    radViaPublica.setChecked(false);
                    radEscuela.setChecked(false);
                    radTransportePublico.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Hogar";
                    Toast.makeText(ServicioReporte2.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radRecreacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radRecreacion.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radHogar.setChecked(false);
                    radTrabajo.setChecked(false);
                    radViaPublica.setChecked(false);
                    radEscuela.setChecked(false);
                    radTransportePublico.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Recreacion y Deporte";
                    Toast.makeText(ServicioReporte2.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radTrabajo.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radHogar.setChecked(false);
                    radRecreacion.setChecked(false);
                    radViaPublica.setChecked(false);
                    radEscuela.setChecked(false);
                    radTransportePublico.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Trabajo";
                    Toast.makeText(ServicioReporte2.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radViaPublica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radViaPublica.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radHogar.setChecked(false);
                    radRecreacion.setChecked(false);
                    radTrabajo.setChecked(false);
                    radEscuela.setChecked(false);
                    radTransportePublico.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Via Publica";
                    Toast.makeText(ServicioReporte2.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radEscuela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radEscuela.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radHogar.setChecked(false);
                    radRecreacion.setChecked(false);
                    radTrabajo.setChecked(false);
                    radViaPublica.setChecked(false);
                    radTransportePublico.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Escuela";
                    Toast.makeText(ServicioReporte2.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radTransportePublico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radTransportePublico.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radHogar.setChecked(false);
                    radRecreacion.setChecked(false);
                    radTrabajo.setChecked(false);
                    radViaPublica.setChecked(false);
                    radEscuela.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Transporte Publico";
                    Toast.makeText(ServicioReporte2.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radOtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radOtro.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radHogar.setChecked(false);
                    radRecreacion.setChecked(false);
                    radTrabajo.setChecked(false);
                    radViaPublica.setChecked(false);
                    radEscuela.setChecked(false);
                    radTransportePublico.setChecked(false);

                    valorSeleccionado = "Otro";
                    Toast.makeText(ServicioReporte2.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });


        ///////////////////////////////////////////////////////////////////
        SpinerDelegacionM = findViewById(R.id.SpinerDelegacionM);


        String[] SpinerDelegacion = {"Tijuana",
                "Rosarito",
                "Ensenada",
                "Mexicali",
                "Tecate"};

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinerDelegacionMAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinerDelegacion);
        SpinerDelegacionMAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerDelegacionM.setAdapter(SpinerDelegacionMAdapter);


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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(ServicioReporte2.this);
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


                Intent intent = new Intent(ServicioReporte2.this, ServicioReporte.class);
                intent.putExtra("id", id);
                // Iniciar la nueva actividad
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
                String CallePrincipal = editTextCallePrincipal.getText().toString();
                String Calle1 = editTextCalle1.getText().toString();
                String Calle2 = editTextCalle2.getText().toString();
                String Colonia = editTextColonia.getText().toString();
                String SpinerDelegacion = SpinerDelegacionM.getSelectedItem().toString();
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (CallePrincipal.isEmpty() || Calle1.isEmpty() || Calle2.isEmpty() ||Colonia.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(ServicioReporte2.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(ServicioReporte2.this);
                    long ID = dbfrapOrden1.insertaServicio2Reporte(claveGenerada,CallePrincipal,Calle1,Calle2,Colonia,SpinerDelegacion,valorSeleccionado);
                    if (ID > 0) {
                        Toast.makeText(ServicioReporte2.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ServicioReporte2.this, MainReporte.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarServicio2Reporte(claveGenerada,CallePrincipal,Calle1,Calle2,Colonia,SpinerDelegacion,valorSeleccionado);
                        if (correcto) {
                            Intent intent = new Intent(ServicioReporte2.this, MainReporte.class);
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
                            editor2.putBoolean("botonBloqueado2", true);
                            editor2.apply();

                            startActivity(intent);

                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(ServicioReporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ServicioReporte2.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(ServicioReporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    // Función para mostrar un mensaje en un Toast
    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}