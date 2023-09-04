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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TratamientoReporte4 extends AppCompatActivity {
    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;

    boolean correcto = false;

    CheckBox checkbox_LineaIV,checkbox_Cateter;
    RadioButton radMano,radPliegueAntecubital,radIntraosea,radOtroSitioAPlicacion,radHartman,radPNACL09
            ,radMixta,radGlucosa5,radOtraSolucion;

    String ViasVenosas,SitioAPlicacion,TiposSoluciones = "";

    Spinner SpinerCantidadml,SpinerInfusiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamiento_reporte4);
//////////////////////////////////////////////Spiner
        SpinerCantidadml = findViewById(R.id.SpinerCantidadml);
        String[] SpinerCantidadml1 = {"","250",
                "500",
                "1000"};

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinerCantidadmlAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinerCantidadml1);
        SpinerCantidadmlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerCantidadml.setAdapter(SpinerCantidadmlAdapter);



        SpinerInfusiones = findViewById(R.id.SpinerInfusiones);

        String[] SpinerInfusiones1 = {"",
                "1",
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
        ArrayAdapter<String> SpinerInfusionesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinerInfusiones1);
        SpinerInfusionesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerInfusiones.setAdapter(SpinerInfusionesAdapter);





        /////////////////////////////////////////////

        checkbox_Cateter = findViewById(R.id.checkbox_Cateter);
        checkbox_Cateter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Cateter.isChecked()) {
                    checkbox_LineaIV.setChecked(false);

                    ViasVenosas = "Cateter";
                }
            }
        });

        checkbox_LineaIV = findViewById(R.id.checkbox_LineaIV);
        checkbox_LineaIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_LineaIV.isChecked()) {
                    checkbox_Cateter.setChecked(false);

                    ViasVenosas = "Linea IV";
                }
            }
        });

        ///////////////////////////////////////////////////////////////////SitioAPlicacion
        // Para radMano
        radMano = findViewById(R.id.radMano);
        radMano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radMano.isChecked()) {
                    radPliegueAntecubital.setChecked(false);
                    radIntraosea.setChecked(false);
                    radOtroSitioAPlicacion.setChecked(false);
                    SitioAPlicacion = "Mano";
                }
            }
        });

// Para radPliegueAntecubital
         radPliegueAntecubital = findViewById(R.id.radPliegueAntecubital);
        radPliegueAntecubital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radPliegueAntecubital.isChecked()) {
                    radMano.setChecked(false);
                    radIntraosea.setChecked(false);
                    radOtroSitioAPlicacion.setChecked(false);
                    SitioAPlicacion = "Pliegue Antecubital";
                }
            }
        });
// Para radIntraosea
         radIntraosea = findViewById(R.id.radIntraosea);
        radIntraosea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radIntraosea.isChecked()) {
                    radMano.setChecked(false);
                    radPliegueAntecubital.setChecked(false);
                    radOtroSitioAPlicacion.setChecked(false);
                    SitioAPlicacion = "Intraosea";
                }
            }
        });

// Para radOtraSolucion
        radOtroSitioAPlicacion = findViewById(R.id.radOtroSitioAPlicacion);
        radOtroSitioAPlicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radOtroSitioAPlicacion.isChecked()) {
                    radMano.setChecked(false);
                    radPliegueAntecubital.setChecked(false);
                    radIntraosea.setChecked(false);
                    SitioAPlicacion = "Otra Solución";
                }
            }
        });

//TIpos de SOluciones

        radHartman = findViewById(R.id.radHartman);
        radPNACL09 = findViewById(R.id.radPNACL09);
        radMixta = findViewById(R.id.radMixta);
        radGlucosa5 = findViewById(R.id.radGlucosa5);
        radOtraSolucion = findViewById(R.id.radOtraSolucion);

        radHartman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radHartman.isChecked()) {
                    radPNACL09.setChecked(false);
                    radMixta.setChecked(false);
                    radGlucosa5.setChecked(false);
                    radOtraSolucion.setChecked(false);
                    TiposSoluciones = "Hartman";
                }
            }
        });
        // Para radPNACL09
        radPNACL09 = findViewById(R.id.radPNACL09);
        radPNACL09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radPNACL09.isChecked()) {
                    radHartman.setChecked(false);
                    radMixta.setChecked(false);
                    radGlucosa5.setChecked(false);
                    radOtraSolucion.setChecked(false);
                    TiposSoluciones = "PNACL 0.9%";
                }
            }
        });

// Para radMixta
        radMixta = findViewById(R.id.radMixta);
        radMixta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radMixta.isChecked()) {
                    radHartman.setChecked(false);
                    radPNACL09.setChecked(false);
                    radGlucosa5.setChecked(false);
                    radOtraSolucion.setChecked(false);
                    TiposSoluciones = "Mixta";
                }
            }
        });

// Para radGlucosa5
        radGlucosa5 = findViewById(R.id.radGlucosa5);
        radGlucosa5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radGlucosa5.isChecked()) {
                    radHartman.setChecked(false);
                    radPNACL09.setChecked(false);
                    radMixta.setChecked(false);
                    radOtraSolucion.setChecked(false);
                    TiposSoluciones = "Glucosa 5%";
                }
            }
        });

// Para radOtraSolucion
        radOtraSolucion = findViewById(R.id.radOtraSolucion);
        radOtraSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radOtraSolucion.isChecked()) {
                    radHartman.setChecked(false);
                    radPNACL09.setChecked(false);
                    radMixta.setChecked(false);
                    radGlucosa5.setChecked(false);
                    TiposSoluciones = "Otra Solución";
                }
            }
        });

/////////////////////////////////////////////////////

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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(TratamientoReporte4.this);
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
                Intent intent = new Intent(TratamientoReporte4.this, TratamientoReporte3.class);
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
                String Cantidadml = SpinerCantidadml.getSelectedItem().toString();
                String Infusiones = SpinerInfusiones.getSelectedItem().toString();
                //valorSeleccionado
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (claveGenerada.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(TratamientoReporte4.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(TratamientoReporte4.this);
                    long ID = dbfrapOrden1.insertaTratamiento4Reporte(claveGenerada,ViasVenosas,SitioAPlicacion,TiposSoluciones,Cantidadml,Infusiones);
                    if (ID > 0) {
                        Toast.makeText(TratamientoReporte4.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TratamientoReporte4.this, TratamientoReporte5.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarTratamiento4Reporte(claveGenerada,ViasVenosas,SitioAPlicacion,TiposSoluciones,Cantidadml,Infusiones);
                        if (correcto) {
                            Intent intent = new Intent(TratamientoReporte4.this, TratamientoReporte5.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(TratamientoReporte4.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TratamientoReporte4.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(TratamientoReporte4.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}