package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CausaTxReporte extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;
    RadioButton radArma,radSustanciasCalientes,radJuguete
            ,radSustanciaToxica,radAutomotor,radElectricidad,radBicicleta
            ,radExplosion,radAnimal,radSerHumano
            ,radMaquinaria,radProductoBiologico,radFuego,radHerramienta,radOtro;
    String valorSeleccionado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_causa_tx_reporte);

        EditText editTextLesionesCausadas = findViewById(R.id.editTextLesionesCausadas);

        /////////////////////////////////////////////////RadButtons
        radArma=findViewById(R.id.radArma);
        radSustanciasCalientes=findViewById(R.id.radSustanciasCalientes);
        radJuguete=findViewById(R.id.radJuguete);
        radSustanciaToxica=findViewById(R.id.radSustanciaToxica);
        radAutomotor=findViewById(R.id.radAutomotor);
        radElectricidad=findViewById(R.id.radElectricidad);
        radBicicleta=findViewById(R.id.radBicicleta);
        radExplosion=findViewById(R.id.radExplosion);
        radAnimal=findViewById(R.id.radAnimal);
        radSerHumano=findViewById(R.id.radSerHumano);
        radMaquinaria=findViewById(R.id.radMaquinaria);
        radProductoBiologico=findViewById(R.id.radProductoBiologico);
        radFuego=findViewById(R.id.radFuego);
        radHerramienta=findViewById(R.id.radHerramienta);
        radOtro=findViewById(R.id.radOtro);


        radArma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radArma.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario

                    radSustanciasCalientes.setChecked(false);
                    radJuguete.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radAutomotor.setChecked(false);
                    radElectricidad.setChecked(false);
                    radBicicleta.setChecked(false);
                    radExplosion.setChecked(false);
                    radAnimal.setChecked(false);
                    radSerHumano.setChecked(false);
                    radMaquinaria.setChecked(false);
                    radProductoBiologico.setChecked(false);
                    radFuego.setChecked(false);
                    radHerramienta.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Arma";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radSustanciasCalientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radSustanciasCalientes.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radArma.setChecked(false);
                    radJuguete.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radAutomotor.setChecked(false);
                    radElectricidad.setChecked(false);
                    radBicicleta.setChecked(false);
                    radExplosion.setChecked(false);
                    radAnimal.setChecked(false);
                    radSerHumano.setChecked(false);
                    radMaquinaria.setChecked(false);
                    radProductoBiologico.setChecked(false);
                    radFuego.setChecked(false);
                    radHerramienta.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Sustancias Calientes";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radJuguete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radJuguete.isChecked()) {
                    radArma.setChecked(false);
                    radSustanciasCalientes.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radAutomotor.setChecked(false);
                    radElectricidad.setChecked(false);
                    radBicicleta.setChecked(false);
                    radExplosion.setChecked(false);
                    radAnimal.setChecked(false);
                    radSerHumano.setChecked(false);
                    radMaquinaria.setChecked(false);
                    radProductoBiologico.setChecked(false);
                    radFuego.setChecked(false);
                    radHerramienta.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Juguete";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radAutomotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radAutomotor.isChecked()) {
                    radArma.setChecked(false);
                    radSustanciasCalientes.setChecked(false);
                    radJuguete.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radElectricidad.setChecked(false);
                    radBicicleta.setChecked(false);
                    radExplosion.setChecked(false);
                    radAnimal.setChecked(false);
                    radSerHumano.setChecked(false);
                    radMaquinaria.setChecked(false);
                    radProductoBiologico.setChecked(false);
                    radFuego.setChecked(false);
                    radHerramienta.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Automotor";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Continue this pattern for the remaining RadioButtons
// Example for radElectricidad
        radElectricidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radElectricidad.isChecked()) {
                    radArma.setChecked(false);
                    radSustanciasCalientes.setChecked(false);
                    radJuguete.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radAutomotor.setChecked(false);
                    radBicicleta.setChecked(false);
                    radExplosion.setChecked(false);
                    radAnimal.setChecked(false);
                    radSerHumano.setChecked(false);
                    radMaquinaria.setChecked(false);
                    radProductoBiologico.setChecked(false);
                    radFuego.setChecked(false);
                    radHerramienta.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Electricidad";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radBicicleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radBicicleta.isChecked()) {
                    radArma.setChecked(false);
                    radSustanciasCalientes.setChecked(false);
                    radJuguete.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radAutomotor.setChecked(false);
                    radElectricidad.setChecked(false);
                    radExplosion.setChecked(false);
                    radAnimal.setChecked(false);
                    radSerHumano.setChecked(false);
                    radMaquinaria.setChecked(false);
                    radProductoBiologico.setChecked(false);
                    radFuego.setChecked(false);
                    radHerramienta.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Bicicleta";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Continue this pattern for the remaining RadioButtons
// Example for radExplosion
        radExplosion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radExplosion.isChecked()) {
                    radArma.setChecked(false);
                    radSustanciasCalientes.setChecked(false);
                    radJuguete.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radAutomotor.setChecked(false);
                    radElectricidad.setChecked(false);
                    radBicicleta.setChecked(false);
                    radAnimal.setChecked(false);
                    radSerHumano.setChecked(false);
                    radMaquinaria.setChecked(false);
                    radProductoBiologico.setChecked(false);
                    radFuego.setChecked(false);
                    radHerramienta.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Explosión";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });
        radAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radAnimal.isChecked()) {
                    radArma.setChecked(false);
                    radSustanciasCalientes.setChecked(false);
                    radJuguete.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radAutomotor.setChecked(false);
                    radElectricidad.setChecked(false);
                    radBicicleta.setChecked(false);
                    radExplosion.setChecked(false);
                    radSerHumano.setChecked(false);
                    radMaquinaria.setChecked(false);
                    radProductoBiologico.setChecked(false);
                    radFuego.setChecked(false);
                    radHerramienta.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Animal";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Continue this pattern for the remaining RadioButtons
// Example for radSerHumano
        radSerHumano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radSerHumano.isChecked()) {
                    radArma.setChecked(false);
                    radSustanciasCalientes.setChecked(false);
                    radJuguete.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radAutomotor.setChecked(false);
                    radElectricidad.setChecked(false);
                    radBicicleta.setChecked(false);
                    radExplosion.setChecked(false);
                    radAnimal.setChecked(false);
                    radMaquinaria.setChecked(false);
                    radProductoBiologico.setChecked(false);
                    radFuego.setChecked(false);
                    radHerramienta.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Ser Humano";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });
// Example for radMaquinaria
        radMaquinaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radMaquinaria.isChecked()) {
                    radArma.setChecked(false);
                    radSustanciasCalientes.setChecked(false);
                    radJuguete.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radAutomotor.setChecked(false);
                    radElectricidad.setChecked(false);
                    radBicicleta.setChecked(false);
                    radExplosion.setChecked(false);
                    radAnimal.setChecked(false);
                    radSerHumano.setChecked(false);
                    radProductoBiologico.setChecked(false);
                    radFuego.setChecked(false);
                    radHerramienta.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Maquinaria";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Continue this pattern for the remaining RadioButtons
// Example for radProductoBiologico
        radProductoBiologico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radProductoBiologico.isChecked()) {
                    radArma.setChecked(false);
                    radSustanciasCalientes.setChecked(false);
                    radJuguete.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radAutomotor.setChecked(false);
                    radElectricidad.setChecked(false);
                    radBicicleta.setChecked(false);
                    radExplosion.setChecked(false);
                    radAnimal.setChecked(false);
                    radSerHumano.setChecked(false);
                    radMaquinaria.setChecked(false);
                    radFuego.setChecked(false);
                    radHerramienta.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Producto Biológico";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Example for radFuego
        radFuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radFuego.isChecked()) {
                    radArma.setChecked(false);
                    radSustanciasCalientes.setChecked(false);
                    radJuguete.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radAutomotor.setChecked(false);
                    radElectricidad.setChecked(false);
                    radBicicleta.setChecked(false);
                    radExplosion.setChecked(false);
                    radAnimal.setChecked(false);
                    radSerHumano.setChecked(false);
                    radMaquinaria.setChecked(false);
                    radProductoBiologico.setChecked(false);
                    radHerramienta.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Fuego";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Continue this pattern for the remaining RadioButtons
// Example for radHerramienta
        radHerramienta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radHerramienta.isChecked()) {
                    radArma.setChecked(false);
                    radSustanciasCalientes.setChecked(false);
                    radJuguete.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radAutomotor.setChecked(false);
                    radElectricidad.setChecked(false);
                    radBicicleta.setChecked(false);
                    radExplosion.setChecked(false);
                    radAnimal.setChecked(false);
                    radSerHumano.setChecked(false);
                    radMaquinaria.setChecked(false);
                    radProductoBiologico.setChecked(false);
                    radFuego.setChecked(false);
                    radOtro.setChecked(false);
                    valorSeleccionado = "Herramienta";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Example for radOtro
        radOtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radOtro.isChecked()) {
                    radArma.setChecked(false);
                    radSustanciasCalientes.setChecked(false);
                    radJuguete.setChecked(false);
                    radSustanciaToxica.setChecked(false);
                    radAutomotor.setChecked(false);
                    radElectricidad.setChecked(false);
                    radBicicleta.setChecked(false);
                    radExplosion.setChecked(false);
                    radAnimal.setChecked(false);
                    radSerHumano.setChecked(false);
                    radMaquinaria.setChecked(false);
                    radProductoBiologico.setChecked(false);
                    radFuego.setChecked(false);
                    radHerramienta.setChecked(false);
                    valorSeleccionado = "Otro";
                    Toast.makeText(CausaTxReporte.this, valorSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });






////////////////////////////////////////////////////////////////////////////RadioButtons
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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(CausaTxReporte.this);
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

        guardarButton = findViewById(R.id.fabGuardar);
        btnregresar = findViewById(R.id.fabRegresar);

        //Metodo para insertar y editar informacion
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              // EditText editTextLesionesCausadas = findViewById(R.id.editTextLesionesCausadas);


                String LesionesCausadas = editTextLesionesCausadas.getText().toString();

                //valorSeleccionado
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (LesionesCausadas.isEmpty()||valorSeleccionado.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(CausaTxReporte.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(CausaTxReporte.this);
                    long ID = dbfrapOrden1.insertaCausaTXReporte(claveGenerada,valorSeleccionado,LesionesCausadas);
                    if (ID > 0) {
                        Toast.makeText(CausaTxReporte.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CausaTxReporte.this, CausaTxReporte2.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarCausaTXReporte(claveGenerada,valorSeleccionado,LesionesCausadas);
                        if (correcto) {
                            Intent intent = new Intent(CausaTxReporte.this, CausaTxReporte2.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(CausaTxReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CausaTxReporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(CausaTxReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén una instancia del Vibrator


                Intent intent = new Intent(CausaTxReporte.this, MainReporte.class);
                intent.putExtra("id", id);
                // Iniciar la nueva actividad
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

    }
}