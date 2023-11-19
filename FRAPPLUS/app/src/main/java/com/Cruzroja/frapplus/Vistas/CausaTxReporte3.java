package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class CausaTxReporte3 extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;
    boolean correcto = false;
    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    RadioButton radParabrisasIntegro,radParabrisasRotoDoblado,radVolanteIntegro
            ,radVolanteDoblado,radSiBolsa,radNoBolsa,radCinturonColocado
            ,radCinturonNoColocado,radDentroSiVehiculo,radDentroNoVehiculo
            ,radDentroEyectadoVehiculo,radAtropelladoAutomotor,radAtropelladoMotocicleta,radAtropelladoBicicleta
            ,radAtropelladoMaquinaria;
    String Parabrisas = "";
    String Volante = "";
    String BolsadeAire = "";
    String CinturonSeguridad = "";
    String DentroVehiculo = "";
    String Atropellado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_causa_tx_reporte3);

        EditText editTextHundimiento = findViewById(R.id.editTextHundimiento);

        //////////////////////////// Rad Buttons
//Parabrisas
        radParabrisasIntegro=findViewById(R.id.radParabrisasIntegro);
        radParabrisasRotoDoblado=findViewById(R.id.radParabrisasRotoDoblado);

        radParabrisasIntegro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radParabrisasIntegro.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radParabrisasRotoDoblado.setChecked(false);
                    Parabrisas = "Parabrisas Integro";
                    Toast.makeText(CausaTxReporte3.this, Parabrisas, Toast.LENGTH_SHORT).show();
                }
            }
        });
        radParabrisasRotoDoblado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radParabrisasRotoDoblado.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radParabrisasIntegro.setChecked(false);
                    Parabrisas = "Parabrisas Integro";
                    Toast.makeText(CausaTxReporte3.this, Parabrisas, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Volante
        radVolanteIntegro=findViewById(R.id.radVolanteIntegro);
        radVolanteDoblado=findViewById(R.id.radVolanteDoblado);

        radVolanteIntegro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radVolanteIntegro.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radVolanteDoblado.setChecked(false);
                    Volante = "Volante Integro";
                    Toast.makeText(CausaTxReporte3.this, Volante, Toast.LENGTH_SHORT).show();
                }
            }
        });
        radVolanteDoblado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radVolanteDoblado.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radVolanteIntegro.setChecked(false);
                    Volante = "Volante Doblado";
                    Toast.makeText(CausaTxReporte3.this, Volante, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Bolsa de aire
        radSiBolsa=findViewById(R.id.radSiBolsa);
        radNoBolsa=findViewById(R.id.radNoBolsa);

        radSiBolsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radSiBolsa.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radNoBolsa.setChecked(false);
                    BolsadeAire = "Si";
                    Toast.makeText(CausaTxReporte3.this, BolsadeAire, Toast.LENGTH_SHORT).show();
                }
            }
        });
        radNoBolsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radNoBolsa.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radSiBolsa.setChecked(false);
                    BolsadeAire = "No";
                    Toast.makeText(CausaTxReporte3.this, BolsadeAire, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Cinturon
        radCinturonColocado=findViewById(R.id.radCinturonColocado);
        radCinturonNoColocado=findViewById(R.id.radCinturonNoColocado);

        radCinturonColocado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radCinturonColocado.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radCinturonNoColocado.setChecked(false);
                    CinturonSeguridad = "Colocado";
                    Toast.makeText(CausaTxReporte3.this, CinturonSeguridad, Toast.LENGTH_SHORT).show();
                }
            }
        });
        radCinturonNoColocado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radCinturonNoColocado.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radCinturonColocado.setChecked(false);
                    CinturonSeguridad = "No Colocado";
                    Toast.makeText(CausaTxReporte3.this, CinturonSeguridad, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Dentrovehiculo
        radDentroSiVehiculo=findViewById(R.id.radDentroSiVehiculo);
        radDentroNoVehiculo=findViewById(R.id.radDentroNoVehiculo);
        radDentroEyectadoVehiculo=findViewById(R.id.radDentroEyectadoVehiculo);

        radDentroSiVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radDentroSiVehiculo.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radDentroNoVehiculo.setChecked(false);
                    radDentroEyectadoVehiculo.setChecked(false);
                    DentroVehiculo = "Si";
                    Toast.makeText(CausaTxReporte3.this, DentroVehiculo, Toast.LENGTH_SHORT).show();
                }
            }
        });
        radDentroNoVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radDentroNoVehiculo.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radDentroSiVehiculo.setChecked(false);
                    radDentroEyectadoVehiculo.setChecked(false);
                    DentroVehiculo = "No";
                    Toast.makeText(CausaTxReporte3.this, DentroVehiculo, Toast.LENGTH_SHORT).show();
                }
            }
        });
        radDentroEyectadoVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radDentroEyectadoVehiculo.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radDentroSiVehiculo.setChecked(false);
                    radDentroNoVehiculo.setChecked(false);
                    DentroVehiculo = "Eyectado";
                    Toast.makeText(CausaTxReporte3.this, DentroVehiculo, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //AtropelladoMedio
        radAtropelladoAutomotor=findViewById(R.id.radAtropelladoAutomotor);
        radAtropelladoMotocicleta=findViewById(R.id.radAtropelladoMotocicleta);
        radAtropelladoBicicleta=findViewById(R.id.radAtropelladoBicicleta);
        radAtropelladoMaquinaria=findViewById(R.id.radAtropelladoMaquinaria);

        // Set click listeners for each RadioButton
        radAtropelladoAutomotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radAtropelladoAutomotor.isChecked()) {
                    // Uncheck the other RadioButtons if needed
                    radAtropelladoMotocicleta.setChecked(false);
                    radAtropelladoBicicleta.setChecked(false);
                    radAtropelladoMaquinaria.setChecked(false);
                    Atropellado = "Automotor";
                    Toast.makeText(CausaTxReporte3.this, Atropellado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Repeat the above code pattern for the remaining RadioButtons
// Example for radAtropelladoMotocicleta
        radAtropelladoMotocicleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radAtropelladoMotocicleta.isChecked()) {
                    radAtropelladoAutomotor.setChecked(false);
                    radAtropelladoBicicleta.setChecked(false);
                    radAtropelladoMaquinaria.setChecked(false);
                    Atropellado = "Motocicleta";
                    Toast.makeText(CausaTxReporte3.this, Atropellado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Example for radAtropelladoBicicleta
        radAtropelladoBicicleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radAtropelladoBicicleta.isChecked()) {
                    radAtropelladoAutomotor.setChecked(false);
                    radAtropelladoMotocicleta.setChecked(false);
                    radAtropelladoMaquinaria.setChecked(false);
                    Atropellado = "Bicicleta";
                    Toast.makeText(CausaTxReporte3.this, Atropellado, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Example for radAtropelladoMaquinaria
        radAtropelladoMaquinaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radAtropelladoMaquinaria.isChecked()) {
                    radAtropelladoAutomotor.setChecked(false);
                    radAtropelladoMotocicleta.setChecked(false);
                    radAtropelladoBicicleta.setChecked(false);
                    Atropellado = "Maquinaria";
                    Toast.makeText(CausaTxReporte3.this, Atropellado, Toast.LENGTH_SHORT).show();
                }
            }
        });


//////////////////////////////////////////////////////////////////////////////////////////




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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(CausaTxReporte3.this);
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


                Intent intent = new Intent(CausaTxReporte3.this, CausaTxReporte2.class);
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
//
                String Hundimiento = editTextHundimiento.getText().toString();
                //valorSeleccionado
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (claveGenerada.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(CausaTxReporte3.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(CausaTxReporte3.this);
                    long ID = dbfrapOrden1.insertaCausaTX3Reporte(claveGenerada,Hundimiento,Parabrisas,Volante,BolsadeAire,CinturonSeguridad,DentroVehiculo,Atropellado);
                    if (ID > 0) {
                        Toast.makeText(CausaTxReporte3.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CausaTxReporte3.this, MainReporte.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarCausaTX3Reporte(claveGenerada,Hundimiento,Parabrisas,Volante,BolsadeAire,CinturonSeguridad,DentroVehiculo,Atropellado);
                        if (correcto) {
                            Intent intent = new Intent(CausaTxReporte3.this, MainReporte.class);
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
                            editor2.putBoolean("botonBloqueado2", false);
                            editor2.apply();

                            SharedPreferences preferences3 = getSharedPreferences("MisPreferencias3", MODE_PRIVATE);
                            SharedPreferences.Editor editor3 = preferences3.edit();
                            editor3.putBoolean("botonBloqueado3", false);
                            editor3.apply();

                            SharedPreferences preferences4 = getSharedPreferences("MisPreferencias4", MODE_PRIVATE);
                            SharedPreferences.Editor editor4 = preferences4.edit();
                            editor4.putBoolean("botonBloqueado4", false);
                            editor4.apply();

                            SharedPreferences preferences5 = getSharedPreferences("MisPreferencias5", MODE_PRIVATE);
                            SharedPreferences.Editor editor5 = preferences5.edit();
                            editor5.putBoolean("botonBloqueado5", true);
                            editor5.apply();

                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(CausaTxReporte3.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CausaTxReporte3.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(CausaTxReporte3.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


}