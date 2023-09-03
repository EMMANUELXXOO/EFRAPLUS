package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TrasladoReporte extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    private Spinner SpinerInstitucionTraslado;
    String ClasificacionPaciente = "";
    String PrioridadPaciente = "";

    boolean correcto = false;
    CheckBox checkbox_CriticoInestable,checkbox_CriticoEstable,checkbox_NocriticoInestable,checkbox_NocriticoEstable,checkbox_Rojo,checkbox_Verde,checkbox_Amarillo,checkbox_Negro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traslado_reporte);
        /////////////////////////////////////////////////////////////CheckBox
        checkbox_Rojo = findViewById(R.id.checkbox_Rojo);
        checkbox_Verde = findViewById(R.id.checkbox_Verde);
        checkbox_Amarillo = findViewById(R.id.checkbox_Amarillo);
        checkbox_Negro = findViewById(R.id.checkbox_Negro);

        checkbox_Rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Rojo.isChecked()) {
                    // Desmarca los otros CheckBox si es necesario
                    checkbox_Negro.setChecked(false);
                    checkbox_Verde.setChecked(false);
                    checkbox_Amarillo.setChecked(false);
                    PrioridadPaciente = "Rojo";
                }
            }
        });

        checkbox_Verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Verde.isChecked()) {
                    // Desmarca los otros CheckBox si es necesario
                    checkbox_Rojo.setChecked(false);
                    checkbox_Negro.setChecked(false);
                    checkbox_Amarillo.setChecked(false);
                    PrioridadPaciente = "Verde";
                }
            }
        });

        checkbox_Amarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Amarillo.isChecked()) {
                    // Desmarca los otros CheckBox si es necesario
                    checkbox_Rojo.setChecked(false);
                    checkbox_Verde.setChecked(false);
                    checkbox_Negro.setChecked(false);
                    PrioridadPaciente = "Amarillo";
                }
            }
        });

        checkbox_Negro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Negro.isChecked()) {
                    // Desmarca los otros CheckBox si es necesario
                    checkbox_Rojo.setChecked(false);
                    checkbox_Verde.setChecked(false);
                    checkbox_Amarillo.setChecked(false);
                    PrioridadPaciente = "Negro";
                }
            }
        });


        checkbox_CriticoInestable = findViewById(R.id.checkbox_CriticoInestable) ;
        checkbox_CriticoEstable=findViewById(R.id.checkbox_CriticoEstable);
        checkbox_NocriticoInestable = findViewById(R.id.checkbox_NocriticoInestable);
        checkbox_NocriticoEstable = findViewById(R.id.checkbox_NocriticoEstable);



        checkbox_CriticoInestable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_CriticoInestable.isChecked()) {
                    // Desmarca los otros CheckBox si es necesario
                    checkbox_CriticoEstable.setChecked(false);
                    checkbox_NocriticoInestable.setChecked(false);
                    checkbox_NocriticoEstable.setChecked(false);
                    ClasificacionPaciente = "Critico Inestable";
                }
            }
        });

        checkbox_CriticoEstable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_CriticoEstable.isChecked()) {
                    // Desmarca los otros CheckBox si es necesario
                    checkbox_CriticoInestable.setChecked(false);
                    checkbox_NocriticoInestable.setChecked(false);
                    checkbox_NocriticoEstable.setChecked(false);
                    ClasificacionPaciente = "Critico Estable";
                }
            }
        });

        checkbox_NocriticoInestable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_NocriticoInestable.isChecked()) {
                    // Desmarca los otros CheckBox si es necesario
                    checkbox_CriticoInestable.setChecked(false);
                    checkbox_CriticoEstable.setChecked(false);
                    checkbox_NocriticoEstable.setChecked(false);
                    ClasificacionPaciente = "No Critico Inestable";
                }
            }
        });

        checkbox_NocriticoEstable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_NocriticoEstable.isChecked()) {
                    // Desmarca los otros CheckBox si es necesario
                    checkbox_CriticoInestable.setChecked(false);
                    checkbox_CriticoEstable.setChecked(false);
                    checkbox_NocriticoInestable.setChecked(false);
                    ClasificacionPaciente = "No Critico Estable";
                }
            }
        });
        /////////////////////////////////////////////////////////////CheckBox
//////////////////////////////////////////////////////////////////////Spiner
        SpinerInstitucionTraslado = findViewById(R.id.SpinerInstitucionTraslado);
        String[] SpinerInstitucionTraslado1 = {"Centro Medico el Dorado",
                "Centro Medico Florence",
                "Centro Medico los Pinos",
                "CHIPSA Centro Hospitalario",
                "Clinica Buena Vista",
                "Clinica del Riñon",
                "Clinica Lara",
                "Clinica Medical Express",
                "Clinica Nueva Tijuana",
                "Clinica Robsan",
                "Clinica San Jorge",
                "Clinica San Juan",
                "Clinica Santa Fe",
                "Clinica St Joseph",
                "Hospital Angeles",
                "Hospital Arcangeles",
                "Hospital Bernardette",
                "Hospital Bethel",
                "Hospital Bonanova",
                "Hospital Cancun",
                "Hospital Cipres",
                "Hospital Cruz Roja Rosarito",
                "Hospital Cruz Roja Tijuana",
                "Hospital de la Mujer y el Niño",
                "Hospital de Salud Mental",
                "Hospital del Carmen",
                "Hospital del Prado",
                "Hospital del Sagrado Corazon",
                "Hospital Excel",
                "Hospital Filadelfia",
                "Hospital Flores",
                "Hospital Fundadores",
                "Hospital General Ensenada",
                "Hospital General Mexicali",
                "Hospital General Rosarito",
                "Hospital General Tecate",
                "Hospital General Tijuana",
                "Hospital Guadalajara",
                "Hospital Guzman",
                "Hospital Insurgentes Rio",
                "Hospital Jerusalem",
                "Hospital Hospital La Piedad",
                "Hospital Luis Luan",
                "Hospital Materno Infantil",
                "Hospital Mexico",
                "Hospital Nova",
                "Hospital San Francisco",
                "Hospital San Luis Rey",
                "Hospital Santa Catalina",
                "Hospital Santa Maria",
                "Hospital Vida",
                "Hospital Villa",
                "Hospital Radiologica Central",
                "Hospital IMSS 1",
                "Hospital IMSS 6",
                "Hospital IMSS 7",
                "Hospital IMSS 8",
                "Hospital IMSS 17",
                "Hospital IMSS 18",
                "Hospital IMSS 19",
                "Hospital IMSS 20",
                "Hospital IMSS 21",
                "Hospital IMSS 27",
                "Hospital IMSS 30",
                "Hospital IMSS 33",
                "Hospital IMSS 34",
                "Hospital IMSS 36",
                "Hospital ISSSTE PALMAS",
                "Hospital ISSSTECALI MIRADOR",
                "Hospital ISSSTECALI PALMAS",
                "Hospital Linea Internacional Otay",
                "Hospital Linea Internacional San Ysidro",
                "Hospital Sanatorio San Francisco",
                "Hospital Sanefro",
                "Hospital Ninguna",
                "Hospital Otro",
                "Hospital Clinica Columbia",
                "Hospital Green And Health Hospital",
                "Hospital Sonora"};

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinerInstitucionTrasladoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinerInstitucionTraslado1);
        SpinerInstitucionTrasladoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerInstitucionTraslado.setAdapter(SpinerInstitucionTrasladoAdapter);

        ////////////////////////////////////////////////////////////////
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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(TrasladoReporte.this);
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
                Intent intent = new Intent(TrasladoReporte.this, MainReporte.class);
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
                 String InstitucionTraslado = SpinerInstitucionTraslado.getSelectedItem().toString();
                //valorSeleccionado
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (ClasificacionPaciente.isEmpty()||PrioridadPaciente.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(TrasladoReporte.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(TrasladoReporte.this);
                    long ID = dbfrapOrden1.insertaTraslado1Reporte(claveGenerada,InstitucionTraslado,ClasificacionPaciente,PrioridadPaciente);
                    if (ID > 0) {
                        Toast.makeText(TrasladoReporte.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TrasladoReporte.this, TrasladoReporte2.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarTraslado1Reporte(claveGenerada,InstitucionTraslado,ClasificacionPaciente,PrioridadPaciente);
                        if (correcto) {
                            Intent intent = new Intent(TrasladoReporte.this, TrasladoReporte2.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(TrasladoReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TrasladoReporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(TrasladoReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}