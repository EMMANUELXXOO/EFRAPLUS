package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CausaTxReporte2 extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;

    RadioButton radColision,radVolcadura,radAutomotor
            ,radMotocicleta,radBicicleta,radMaquinaria,radFijo
            ,radEnmovimiento,radFrontal,radLateral
            ,radPosterior;
    String AgenteAutomovilistico = "";
    String Medio = "";
    String ContraObjeto = "";
    String Impacto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_causa_tx_reporte2);
/////////////////////////////////////////////////////////////////////////////

//RadioButton ,,
//            ,,,,
//            ,,,
//            ,;
        /////////////////////////////////////////////////RadButtons
        ///Accidente Automovilistico
        radColision=findViewById(R.id.radColision);
        radVolcadura=findViewById(R.id.radVolcadura);

        radColision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radColision.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario

                    radVolcadura.setChecked(false);
                    AgenteAutomovilistico = "Colision";
                    Toast.makeText(CausaTxReporte2.this, AgenteAutomovilistico, Toast.LENGTH_SHORT).show();
                }
            }
        });
        radVolcadura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radVolcadura.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario

                    radColision.setChecked(false);
                    AgenteAutomovilistico = "Volcadura";
                    Toast.makeText(CausaTxReporte2.this, AgenteAutomovilistico, Toast.LENGTH_SHORT).show();
                }
            }
        });
        /////////////////////////////Medio
        radAutomotor=findViewById(R.id.radAutomotor);
        radMotocicleta=findViewById(R.id.radMotocicleta);
        radBicicleta=findViewById(R.id.radBicicleta);
        radMaquinaria=findViewById(R.id.radMaquinaria);

        radAutomotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radAutomotor.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radMotocicleta.setChecked(false);
                    radBicicleta.setChecked(false);
                    radMaquinaria.setChecked(false);
                    Medio = "Automotor";
                    Toast.makeText(CausaTxReporte2.this, Medio, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Repeat the above code pattern for the remaining RadioButtons
// Example for radMotocicleta
        radMotocicleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radMotocicleta.isChecked()) {
                    radAutomotor.setChecked(false);
                    radBicicleta.setChecked(false);
                    radMaquinaria.setChecked(false);
                    Medio = "Motocicleta";
                    Toast.makeText(CausaTxReporte2.this, Medio, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Example for radBicicleta
        radBicicleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radBicicleta.isChecked()) {
                    radAutomotor.setChecked(false);
                    radMotocicleta.setChecked(false);
                    radMaquinaria.setChecked(false);
                    Medio = "Bicicleta";
                    Toast.makeText(CausaTxReporte2.this, Medio, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Example for radMaquinaria
        radMaquinaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radMaquinaria.isChecked()) {
                    radAutomotor.setChecked(false);
                    radMotocicleta.setChecked(false);
                    radBicicleta.setChecked(false);
                    Medio = "Maquinaria";
                    Toast.makeText(CausaTxReporte2.this, Medio, Toast.LENGTH_SHORT).show();
                }
            }
        });

        ////////////////////ContraObjeto
        radFijo=findViewById(R.id.radFijo);
        radEnmovimiento=findViewById(R.id.radEnmovimiento);

        radFijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radFijo.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario

                    radEnmovimiento.setChecked(false);
                    ContraObjeto = "Fijo";
                    Toast.makeText(CausaTxReporte2.this, Impacto, Toast.LENGTH_SHORT).show();
                }
            }
        });
        radEnmovimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radEnmovimiento.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario

                    radFijo.setChecked(false);
                    ContraObjeto = "En movimiento";
                    Toast.makeText(CausaTxReporte2.this, Impacto, Toast.LENGTH_SHORT).show();
                }
            }
        });
        ///////////Impacto
        radFrontal=findViewById(R.id.radFrontal);
        radLateral=findViewById(R.id.radLateral);
        radPosterior=findViewById(R.id.radPosterior);

        radFrontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radFrontal.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radLateral.setChecked(false);
                    radPosterior.setChecked(false);
                    Impacto = "Frontal";
                    Toast.makeText(CausaTxReporte2.this, Impacto, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Repeat the above code pattern for the remaining RadioButtons
// Example for radLateral
        radLateral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radLateral.isChecked()) {
                    radFrontal.setChecked(false);
                    radPosterior.setChecked(false);
                    Impacto = "Lateral";
                    Toast.makeText(CausaTxReporte2.this, Impacto, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Example for radPosterior
        radPosterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radPosterior.isChecked()) {
                    radFrontal.setChecked(false);
                    radLateral.setChecked(false);
                    Impacto = "Posterior";
                    Toast.makeText(CausaTxReporte2.this, Impacto, Toast.LENGTH_SHORT).show();
                }
            }
        });






        /////////////////////////////////



        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }

        guardarButton = findViewById(R.id.fabGuardar);
        btnregresar = findViewById(R.id.fabRegresar);

        viewstado = findViewById(R.id.viewestado);
        viewClave = findViewById(R.id.viewclave);
        viewfechamodif = findViewById(R.id.viewFechamodif);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(CausaTxReporte2.this);
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


                Intent intent = new Intent(CausaTxReporte2.this, CausaTxReporte.class);
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
//              // EditText editTextLesionesCausadas = findViewById(R.id.editTextLesionesCausadas);

                //valorSeleccionado
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (AgenteAutomovilistico.isEmpty()||Medio.isEmpty()||ContraObjeto.isEmpty()||Impacto.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(CausaTxReporte2.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(CausaTxReporte2.this);
                    long ID = dbfrapOrden1.insertaCausaTX2Reporte(claveGenerada,AgenteAutomovilistico,Medio,ContraObjeto,Impacto);
                    if (ID > 0) {
                        Toast.makeText(CausaTxReporte2.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CausaTxReporte2.this, CausaTxReporte3.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarCausaTX2Reporte(claveGenerada,AgenteAutomovilistico,Medio,ContraObjeto,Impacto);
                        if (correcto) {
                            Intent intent = new Intent(CausaTxReporte2.this, CausaTxReporte3.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(CausaTxReporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CausaTxReporte2.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(CausaTxReporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}