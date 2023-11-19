package com.Cruzroja.frapplus;

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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.Vistas.MainReporte;
import com.Cruzroja.frapplus.Vistas.ServicioReporte;
import com.Cruzroja.frapplus.Vistas.ServicioReporte2;
import com.Cruzroja.frapplus.entidades.DatosServicioCancelado;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class activity_datos_servicio_cancelado2 extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_servicio_cancelado2);


        // Agrega la inicialización de los EditText
        editTextCallePrincipal = findViewById(R.id.editTextCallePrincipal);
        editTextCalle1 = findViewById(R.id.editTextCalle1);
        editTextCalle2 = findViewById(R.id.editTextCalle2);
        editTextColonia = findViewById(R.id.editTextColonia);

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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(activity_datos_servicio_cancelado2.this);
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


                Intent intent = new Intent(activity_datos_servicio_cancelado2.this, DatosServicioCancelado.class);
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
                    Toast.makeText(activity_datos_servicio_cancelado2.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(activity_datos_servicio_cancelado2.this);
                    long ID = dbfrapOrden1.insertaServicio2CANCELADOReporte(claveGenerada,CallePrincipal,Calle1,Calle2,Colonia,SpinerDelegacion);
                    if (ID > 0) {
                        Toast.makeText(activity_datos_servicio_cancelado2.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity_datos_servicio_cancelado2.this, DatosControlCancelado.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarServicio2CANCELADOReporte(claveGenerada,CallePrincipal,Calle1,Calle2,Colonia,SpinerDelegacion);
                        if (correcto) {
                            Intent intent = new Intent(activity_datos_servicio_cancelado2.this, DatosControlCancelado.class);
                            intent.putExtra("id", id);


                            startActivity(intent);

                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(activity_datos_servicio_cancelado2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity_datos_servicio_cancelado2.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(activity_datos_servicio_cancelado2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
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