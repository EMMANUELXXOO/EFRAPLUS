package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CausaClinicaReporte extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private Spinner Spiner1raVez;
    private Spinner SpinerSubsecuente;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;
    RadioButton radNeurologia,radUrogenital,radCardiovascular
            ,radDigestiva,radRespiratorio,radInfecciosa,radMetabolico
            ,radOncologico,radCognitivoEmocional,radMusculoEsqueletico
            ,radGinecoobtetrica,radOtro;
    String valorSeleccionado = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_causa_clinica_reporte);

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }

        viewstado = findViewById(R.id.viewestado);
        viewClave = findViewById(R.id.viewclave);
        viewfechamodif = findViewById(R.id.viewFechamodif);

/////////////////////////////////////////////////RadButtons
        radNeurologia=findViewById(R.id.radNeurologia);
        radUrogenital=findViewById(R.id.radUrogenital);
        radCardiovascular=findViewById(R.id.radCardiovascular);
        radDigestiva=findViewById(R.id.radDigestiva);
        radRespiratorio=findViewById(R.id.radRespiratorio);
        radInfecciosa=findViewById(R.id.radInfecciosa);
        radMetabolico=findViewById(R.id.radMetabolico);
        radOncologico=findViewById(R.id.radOncologico);
        radCognitivoEmocional=findViewById(R.id.radCognitivoEmocional);
        radMusculoEsqueletico=findViewById(R.id.radMusculoEsqueletico);
        radGinecoobtetrica=findViewById(R.id.radGinecoobtetrica);
        radOtro=findViewById(R.id.radOtro);

        radNeurologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radNeurologia.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario

                    radUrogenital.setChecked(false);
                    radCardiovascular.setChecked(false);
                    radDigestiva.setChecked(false);
                    radRespiratorio.setChecked(false);
                    radInfecciosa.setChecked(false);
                    radMetabolico.setChecked(false);
                    radOncologico.setChecked(false);
                    radCognitivoEmocional.setChecked(false);
                    radMusculoEsqueletico.setChecked(false);
                    radGinecoobtetrica.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Neurologia";
                    Toast.makeText(CausaClinicaReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Configurar el OnClickListener para radUrogenital
        radUrogenital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radUrogenital.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radNeurologia.setChecked(false);
                    radCardiovascular.setChecked(false);
                    radDigestiva.setChecked(false);
                    radRespiratorio.setChecked(false);
                    radInfecciosa.setChecked(false);
                    radMetabolico.setChecked(false);
                    radOncologico.setChecked(false);
                    radCognitivoEmocional.setChecked(false);
                    radMusculoEsqueletico.setChecked(false);
                    radGinecoobtetrica.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Urogenital";
                    Toast.makeText(CausaClinicaReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radCardiovascular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radCardiovascular.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radNeurologia.setChecked(false);
                    radUrogenital.setChecked(false);
                    radDigestiva.setChecked(false);
                    radRespiratorio.setChecked(false);
                    radInfecciosa.setChecked(false);
                    radMetabolico.setChecked(false);
                    radOncologico.setChecked(false);
                    radCognitivoEmocional.setChecked(false);
                    radMusculoEsqueletico.setChecked(false);
                    radGinecoobtetrica.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Cardiovascular";
                    Toast.makeText(CausaClinicaReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Configurar el OnClickListener para radDigestiva
        radDigestiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radDigestiva.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radNeurologia.setChecked(false);
                    radUrogenital.setChecked(false);
                    radCardiovascular.setChecked(false);
                    radRespiratorio.setChecked(false);
                    radInfecciosa.setChecked(false);
                    radMetabolico.setChecked(false);
                    radOncologico.setChecked(false);
                    radCognitivoEmocional.setChecked(false);
                    radMusculoEsqueletico.setChecked(false);
                    radGinecoobtetrica.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Digestiva";
                    Toast.makeText(CausaClinicaReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Configurar el OnClickListener para radRespiratorio
        radRespiratorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radRespiratorio.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radNeurologia.setChecked(false);
                    radUrogenital.setChecked(false);
                    radCardiovascular.setChecked(false);
                    radDigestiva.setChecked(false);
                    radInfecciosa.setChecked(false);
                    radMetabolico.setChecked(false);
                    radOncologico.setChecked(false);
                    radCognitivoEmocional.setChecked(false);
                    radMusculoEsqueletico.setChecked(false);
                    radGinecoobtetrica.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Respiratorio";
                    Toast.makeText(CausaClinicaReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Configurar el OnClickListener para radInfecciosa
        radInfecciosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radInfecciosa.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radNeurologia.setChecked(false);
                    radUrogenital.setChecked(false);
                    radCardiovascular.setChecked(false);
                    radDigestiva.setChecked(false);
                    radRespiratorio.setChecked(false);
                    radMetabolico.setChecked(false);
                    radOncologico.setChecked(false);
                    radCognitivoEmocional.setChecked(false);
                    radMusculoEsqueletico.setChecked(false);
                    radGinecoobtetrica.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Infecciosa";
                    Toast.makeText(CausaClinicaReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Configurar el OnClickListener para radMetabolico
        radMetabolico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radMetabolico.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radNeurologia.setChecked(false);
                    radUrogenital.setChecked(false);
                    radCardiovascular.setChecked(false);
                    radDigestiva.setChecked(false);
                    radRespiratorio.setChecked(false);
                    radInfecciosa.setChecked(false);
                    radOncologico.setChecked(false);
                    radCognitivoEmocional.setChecked(false);
                    radMusculoEsqueletico.setChecked(false);
                    radGinecoobtetrica.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Metabolico";
                    Toast.makeText(CausaClinicaReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Configurar el OnClickListener para radOncologico
        radOncologico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radOncologico.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radNeurologia.setChecked(false);
                    radUrogenital.setChecked(false);
                    radCardiovascular.setChecked(false);
                    radDigestiva.setChecked(false);
                    radRespiratorio.setChecked(false);
                    radInfecciosa.setChecked(false);
                    radMetabolico.setChecked(false);
                    radCognitivoEmocional.setChecked(false);
                    radMusculoEsqueletico.setChecked(false);
                    radGinecoobtetrica.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Oncologico";
                    Toast.makeText(CausaClinicaReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Configurar el OnClickListener para radCognitivoEmocional
        radCognitivoEmocional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radCognitivoEmocional.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radNeurologia.setChecked(false);
                    radUrogenital.setChecked(false);
                    radCardiovascular.setChecked(false);
                    radDigestiva.setChecked(false);
                    radRespiratorio.setChecked(false);
                    radInfecciosa.setChecked(false);
                    radMetabolico.setChecked(false);
                    radOncologico.setChecked(false);
                    radMusculoEsqueletico.setChecked(false);
                    radGinecoobtetrica.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Cognitivo/Emocional";
                    Toast.makeText(CausaClinicaReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Configurar el OnClickListener para radMusculoEsqueletico
        radMusculoEsqueletico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radMusculoEsqueletico.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radNeurologia.setChecked(false);
                    radUrogenital.setChecked(false);
                    radCardiovascular.setChecked(false);
                    radDigestiva.setChecked(false);
                    radRespiratorio.setChecked(false);
                    radInfecciosa.setChecked(false);
                    radMetabolico.setChecked(false);
                    radOncologico.setChecked(false);
                    radCognitivoEmocional.setChecked(false);
                    radGinecoobtetrica.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Musculo/Esqueletico";
                    Toast.makeText(CausaClinicaReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Configurar el OnClickListener para radGinecoobtetrica
        radGinecoobtetrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radGinecoobtetrica.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radNeurologia.setChecked(false);
                    radUrogenital.setChecked(false);
                    radCardiovascular.setChecked(false);
                    radDigestiva.setChecked(false);
                    radRespiratorio.setChecked(false);
                    radInfecciosa.setChecked(false);
                    radMetabolico.setChecked(false);
                    radOncologico.setChecked(false);
                    radCognitivoEmocional.setChecked(false);
                    radMusculoEsqueletico.setChecked(false);
                    radOtro.setChecked(false);

                    valorSeleccionado = "Ginecoobstetrica";
                    Toast.makeText(CausaClinicaReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Configurar el OnClickListener para radOtro
        radOtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radOtro.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    radNeurologia.setChecked(false);
                    radUrogenital.setChecked(false);
                    radCardiovascular.setChecked(false);
                    radDigestiva.setChecked(false);
                    radRespiratorio.setChecked(false);
                    radInfecciosa.setChecked(false);
                    radMetabolico.setChecked(false);
                    radOncologico.setChecked(false);
                    radCognitivoEmocional.setChecked(false);
                    radMusculoEsqueletico.setChecked(false);
                    radGinecoobtetrica.setChecked(false);

                    valorSeleccionado = "Otro";
                    Toast.makeText(CausaClinicaReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

        /////////////////////////////////////////////Spiners
        Spiner1raVez = findViewById(R.id.Spiner1raVez);
        SpinerSubsecuente = findViewById(R.id.SpinerSubsecuente);

        String[] Spiner1raVez1 = {"Si",
                "No"};

        // Adaptadores para los spinners
        ArrayAdapter<String> Spiner1raVezAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Spiner1raVez1);
        Spiner1raVezAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spiner1raVez.setAdapter(Spiner1raVezAdapter);

        String[] SpinerSubsecuente1 = {"1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10"};

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinerSubsecuenteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinerSubsecuente1);
        SpinerSubsecuenteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerSubsecuente.setAdapter(SpinerSubsecuenteAdapter);


        /////////////////////////////////////////////Spiners

        guardarButton = findViewById(R.id.fabGuardar);
        btnregresar = findViewById(R.id.fabRegresar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(CausaClinicaReporte.this);
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

        //Metodo para insertar y editar informacion
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Obtener referencias a los EditText y Spinners
                // Obtener los valores de los EditText que contienen las horas

                String Spiner1raVez1 = Spiner1raVez.getSelectedItem().toString();
                String SpinerSubsecuente1 = SpinerSubsecuente.getSelectedItem().toString();
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (valorSeleccionado.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(CausaClinicaReporte.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(CausaClinicaReporte.this);
                    long ID = dbfrapOrden1.insertaCausaClinicaReporte(claveGenerada,valorSeleccionado,Spiner1raVez1,SpinerSubsecuente1);
                    if (ID > 0) {
                        Toast.makeText(CausaClinicaReporte.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CausaClinicaReporte.this, MainReporte.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarCausaClinicaReporte(claveGenerada,valorSeleccionado,Spiner1raVez1,SpinerSubsecuente1);
                        if (correcto) {
                            Intent intent = new Intent(CausaClinicaReporte.this, MainReporte.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(CausaClinicaReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CausaClinicaReporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(CausaClinicaReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén una instancia del Vibrator
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                // Verifica si el dispositivo admite la vibración
                if (vibrator != null && vibrator.hasVibrator()) {
                    // Realiza una vibración corta al hacer clic en el botón
                    vibrator.vibrate(100); // 100 milisegundos de vibración
                }

                finish();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

    }
}