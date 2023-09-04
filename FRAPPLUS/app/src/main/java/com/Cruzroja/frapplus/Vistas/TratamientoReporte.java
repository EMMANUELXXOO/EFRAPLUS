package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TratamientoReporte extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;

    boolean correcto = false;
    String ViaAerea = "";
    String ControlCervical = "";
    CheckBox checkbox_Aspiracion,checkbox_CanulaOrofaringea,checkbox_IntubacionOrotraqueal
            ,checkbox_IntubacionNasotraqueal,checkbox_Combitubo,checkbox_MascarillaLaringe
            ,checkbox_Cricotirodotomia,checkbox_Manual,checkbox_CollarinRigido,checkbox_CollarinBlando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamiento_reporte);
//////////////////////////////////////////////////////////////////////////////////////////


        checkbox_Aspiracion = findViewById(R.id.checkbox_Aspiracion);
        checkbox_CanulaOrofaringea = findViewById(R.id.checkbox_CanulaOrofaringea);
        checkbox_IntubacionOrotraqueal = findViewById(R.id.checkbox_IntubacionOrotraqueal);
        checkbox_IntubacionNasotraqueal = findViewById(R.id.checkbox_IntubacionNasotraqueal);
        checkbox_Combitubo = findViewById(R.id.checkbox_Combitubo);
        checkbox_MascarillaLaringe = findViewById(R.id.checkbox_MascarillaLaringea);
        checkbox_Cricotirodotomia = findViewById(R.id.checkbox_Cricotirodotomia);

        checkbox_Aspiracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Aspiracion.isChecked()) {
                    checkbox_CanulaOrofaringea.setChecked(false);
                    checkbox_IntubacionOrotraqueal.setChecked(false);
                    checkbox_IntubacionNasotraqueal.setChecked(false);
                    checkbox_Combitubo.setChecked(false);
                    checkbox_MascarillaLaringe.setChecked(false);
                    checkbox_Cricotirodotomia.setChecked(false);
                    ViaAerea = "Aspiracion";
                }
            }
        });

        checkbox_CanulaOrofaringea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_CanulaOrofaringea.isChecked()) {
                    checkbox_Aspiracion.setChecked(false);
                    checkbox_IntubacionOrotraqueal.setChecked(false);
                    checkbox_IntubacionNasotraqueal.setChecked(false);
                    checkbox_Combitubo.setChecked(false);
                    checkbox_MascarillaLaringe.setChecked(false);
                    checkbox_Cricotirodotomia.setChecked(false);
                    ViaAerea = "Canula Orofaringea";
                }
            }
        });

        checkbox_IntubacionOrotraqueal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_IntubacionOrotraqueal.isChecked()) {
                    checkbox_Aspiracion.setChecked(false);
                    checkbox_CanulaOrofaringea.setChecked(false);
                    checkbox_IntubacionNasotraqueal.setChecked(false);
                    checkbox_Combitubo.setChecked(false);
                    checkbox_MascarillaLaringe.setChecked(false);
                    checkbox_Cricotirodotomia.setChecked(false);
                    ViaAerea = "Intubacion Orotraqueal";
                }
            }
        });

        checkbox_IntubacionNasotraqueal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_IntubacionNasotraqueal.isChecked()) {
                    checkbox_Aspiracion.setChecked(false);
                    checkbox_CanulaOrofaringea.setChecked(false);
                    checkbox_IntubacionOrotraqueal.setChecked(false);
                    checkbox_Combitubo.setChecked(false);
                    checkbox_MascarillaLaringe.setChecked(false);
                    checkbox_Cricotirodotomia.setChecked(false);
                    ViaAerea = "Intubacion Nasotraqueal";
                }
            }
        });

        checkbox_Combitubo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Combitubo.isChecked()) {
                    checkbox_Aspiracion.setChecked(false);
                    checkbox_CanulaOrofaringea.setChecked(false);
                    checkbox_IntubacionOrotraqueal.setChecked(false);
                    checkbox_IntubacionNasotraqueal.setChecked(false);
                    checkbox_MascarillaLaringe.setChecked(false);
                    checkbox_Cricotirodotomia.setChecked(false);
                    ViaAerea = "Combitubo";
                }
            }
        });

        checkbox_MascarillaLaringe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_MascarillaLaringe.isChecked()) {
                    checkbox_Aspiracion.setChecked(false);
                    checkbox_CanulaOrofaringea.setChecked(false);
                    checkbox_IntubacionOrotraqueal.setChecked(false);
                    checkbox_IntubacionNasotraqueal.setChecked(false);
                    checkbox_Combitubo.setChecked(false);
                    checkbox_Cricotirodotomia.setChecked(false);
                    ViaAerea = "Mascarilla Laringea";
                }
            }
        });

        checkbox_Cricotirodotomia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Cricotirodotomia.isChecked()) {
                    checkbox_Aspiracion.setChecked(false);
                    checkbox_CanulaOrofaringea.setChecked(false);
                    checkbox_IntubacionOrotraqueal.setChecked(false);
                    checkbox_IntubacionNasotraqueal.setChecked(false);
                    checkbox_Combitubo.setChecked(false);
                    checkbox_MascarillaLaringe.setChecked(false);
                    ViaAerea = "Cricotirodotomia";
                }
            }
        });

        checkbox_Manual = findViewById(R.id.checkbox_Manual);
        checkbox_CollarinRigido = findViewById(R.id.checkbox_CollarinRigido);
        checkbox_CollarinBlando = findViewById(R.id.checkbox_CollarinBlando);

        checkbox_Manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_Manual.isChecked()) {
                    checkbox_CollarinRigido.setChecked(false);
                    checkbox_CollarinBlando.setChecked(false);
                    ControlCervical= "Manual";
                }
            }
        });
        checkbox_CollarinRigido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_CollarinRigido.isChecked()) {
                    checkbox_Manual.setChecked(false);
                    checkbox_CollarinBlando.setChecked(false);
                    ControlCervical= "Collarin Rigido";
                }
            }
        });
        checkbox_CollarinBlando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_CollarinBlando.isChecked()) {
                    checkbox_Manual.setChecked(false);
                    checkbox_CollarinRigido.setChecked(false);
                    ControlCervical= "Collarin Blando";
                }
            }
        });







        ////////////////////////////////////////////////////////////////////////////
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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(TratamientoReporte.this);
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
                Intent intent = new Intent(TratamientoReporte.this, MainReporte.class);
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
                    Toast.makeText(TratamientoReporte.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(TratamientoReporte.this);
                    long ID = dbfrapOrden1.insertaTratamientoReporte(claveGenerada,ViaAerea,ControlCervical);
                    if (ID > 0) {
                        Toast.makeText(TratamientoReporte.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TratamientoReporte.this, TratamientoReporte2.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarTratamientoReporte(claveGenerada,ViaAerea,ControlCervical);
                        if (correcto) {
                            Intent intent = new Intent(TratamientoReporte.this, TratamientoReporte2.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(TratamientoReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TratamientoReporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(TratamientoReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}