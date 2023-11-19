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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.Vistas.ControlReporte;
import com.Cruzroja.frapplus.Vistas.InicioFrap;
import com.Cruzroja.frapplus.Vistas.MainReporte;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DatosControlCancelado extends AppCompatActivity {
    TextView viewstado,viewClave,viewfechamodif;

    private Spinner SpinnerNoAmbulancia;
    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_control_cancelado);

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }

        EditText editTextOperador = findViewById(R.id.editTextOperador);
        EditText editTextPrestadoresServicio = findViewById(R.id.editTextPrestadoresServicio);






        viewstado = findViewById(R.id.viewestado);
        viewClave = findViewById(R.id.viewclave);
        viewfechamodif = findViewById(R.id.viewFechamodif);

        SpinnerNoAmbulancia = findViewById(R.id.SpinnerNoAmbulancia);
        String[] SpinnerNoAmbulancia1 = {"BC-150",
                "BC-151",
                "BC-152",
                "BC-153",
                "BC-154",
                "BC-155",
                "BC-156",
                "BC-157",
                "BC-158",
                "BC-159",
                "BC-160",
                "BC-161",
                "BC-162",
                "BC-163",
                "BC-164",
                "BC-165",
                "BC-166",
                "BC-167",
                "BC-168",
                "BC-169",
                "BC-170",
                "BC-171",
                "BC-172",
                "BC-173",
                "BC-174",
                "BC-175",
                "BC-176",
                "BC-177",
                "BC-178",
                "BC-179",
                "BC-180",
                "BC-181",
                "BC-182",
                "BC-183",
                "BC-184",
                "BC-185",
                "BC-186",
                "BC-187",
                "BC-188",
                "BC-189",
                "BC-190",
                "BC-191",
                "BC-192",
                "BC-193",
                "BC-194",
                "BC-195",
                "BC-196",
                "BC-197",
                "BC-198",
                "BC-199",
                "BC-200",
                "BC-925",
                "BC-027"};

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinnerNoAmbulanciaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinnerNoAmbulancia1);
        SpinnerNoAmbulanciaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerNoAmbulancia.setAdapter(SpinnerNoAmbulanciaAdapter);



        // Declaramos la variable para la clave
        String clave;

        guardarButton = findViewById(R.id.fabGuardar);
        btnregresar = findViewById(R.id.fabRegresar);

        if (frapOrden != null) {
            viewstado.setText(String.valueOf(frapOrden.getStatus()));
            if (viewstado.getText().toString().equals("CANCELADO")) {
                // El texto en viewStatus1 es igual a "CANCELADO"
                // Realiza las acciones necesarias aquí
                viewstado.setTextColor(getResources().getColor(R.color.red));
            }
            clave = frapOrden.getClave();   // Guardamos la clave
            viewClave.setText(clave);       // Mostramos la clave
            viewfechamodif.setText(frapOrden.getFechadeMoficacion());
        } else {
            Log.d("VerInformeFrap", "Valor de id: " + id);
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(DatosControlCancelado.this);
        frapOrden = dbfrapOrdenControl.verFRAPCONTROL(id);

        // Declaramos la variable para la clave


        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén una instancia del Vibrator


                Intent intent = new Intent(DatosControlCancelado.this, MainReporte.class);
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

                String Operador = editTextOperador.getText().toString();
                String PrestadoresServicio = editTextPrestadoresServicio.getText().toString();

                String SNoAmbulancia = SpinnerNoAmbulancia.getSelectedItem().toString();
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (Operador.isEmpty() || PrestadoresServicio.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(DatosControlCancelado.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(DatosControlCancelado.this);
                    long ID = dbfrapOrden1.insertaControlCANCELADO(claveGenerada,SNoAmbulancia,Operador,PrestadoresServicio);
                    if (ID > 0) {
                        Toast.makeText(DatosControlCancelado.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DatosControlCancelado.this, InicioFrap.class);
                        intent.putExtra("id", id);


                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarControlReporte(claveGenerada,SNoAmbulancia,Operador,PrestadoresServicio);
                        if (correcto) {
                            Intent intent = new Intent(DatosControlCancelado.this, InicioFrap.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(DatosControlCancelado.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DatosControlCancelado.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(DatosControlCancelado.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}