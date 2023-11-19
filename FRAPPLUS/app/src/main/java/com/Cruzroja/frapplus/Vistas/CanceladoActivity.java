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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.DatosServicioCancelado;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CanceladoActivity extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;
    private Spinner estadoSpinner;
    private Spinner delegacionSpinner;
    private Spinner asignacionSpinner;
    private  Spinner MotivoCancelacion;
    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;

    //Variable para editar
    boolean correcto = false;
    String Status = "CANCELADO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelado);

        // Obtén una instancia del Vibrator
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }


        viewstado = findViewById(R.id.viewestado);
        viewClave = findViewById(R.id.viewclave);
        viewfechamodif = findViewById(R.id.viewFechamodif);

        estadoSpinner = findViewById(R.id.estadoCSpinner);
        delegacionSpinner = findViewById(R.id.delegacionCSpinner);
        asignacionSpinner = findViewById(R.id.asignacionCSpinner);
        MotivoCancelacion = findViewById(R.id.MotivoCancelacionSpinner);
        guardarButton = findViewById(R.id.fabGuardar);
        btnregresar = findViewById(R.id.fabRegresar);

        // Datos para los spinners
        String[] estados = {"Baja California"};
        String[] delegaciones = {"Tijuana"};
        String[] asignaciones = {"Base 0 , Centro",
                "Base 1, Libramiento",
                "Base 2, Playas",
                "Base 4, El Dorado",
                "Base 5, Otay",
                "Base 8, Santa Fe",
                "Base 10, El Florido",
                "Base 11, Natura",
                "Base 58, La Mesa",
                "PF Torre AguaCaliente",
                "PF Pacifico",
                "PF Sanchez Taboada",
                "PF Jardin Dorado",
                "PF Ruta Hidalgo",
                "PF Revolucion",
                "PF Ojo de Agua",
                "PF Libertad"};
        String[] MotivoCancelacion1 = {"Flasa Alarma",
                "Atendido por otra ambulancia",
                "Llamaron para cancelar",
                "Cancelado por radioOperador",
                "Familia o amigos se hacen Cargo al arribo",
                "Se hizo Cargo otra dependencia"};
        // Adaptadores para los spinners
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estados);
        ArrayAdapter<String> delegacionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, delegaciones);
        ArrayAdapter<String> asignacionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, asignaciones);
        ArrayAdapter<String> MotivoCancelacionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MotivoCancelacion1);

        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        delegacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        asignacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MotivoCancelacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        estadoSpinner.setAdapter(estadoAdapter);
        delegacionSpinner.setAdapter(delegacionAdapter);
        asignacionSpinner.setAdapter(asignacionAdapter);
        MotivoCancelacion.setAdapter(MotivoCancelacionAdapter);

        int variableGlobalRecibida = getIntent().getIntExtra("variableGlobal", 0);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }
        // Declaramos la variable para la clave
        String clave;

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(CanceladoActivity.this);
        frapOrden = dbfrapOrdenControl.verFRAPCONTROL(id);

        if (variableGlobalRecibida==1){

            correcto = dbfrapOrdenControl.editarfrapOrdenEstado(id,Status);

            if (correcto) {
                // Toast.makeText(VerInformeFrap.this, "Modificación exitosa", Toast.LENGTH_SHORT).show();
                verRegistro();
            } else {
                Toast.makeText(CanceladoActivity.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
            }
        }



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


        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedEstado = estadoSpinner.getSelectedItem().toString();
                String selectedDelegacion = delegacionSpinner.getSelectedItem().toString();
                String selectedAsignacion = asignacionSpinner.getSelectedItem().toString();
                String motivocancelacion = MotivoCancelacion.getSelectedItem().toString();
                String claveGenerada = frapOrden.getClave();;

                DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(CanceladoActivity.this);
                long ID = dbfrapOrden1.insertarFrapcanceladoDelegacion(claveGenerada, selectedEstado, selectedDelegacion, selectedAsignacion,motivocancelacion);
                if (ID > 0) {
                    Toast.makeText(CanceladoActivity.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CanceladoActivity.this, DatosServicioCancelado.class);
                    intent.putExtra("id", id);

                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    finish();
                    // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                    String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                    Toast.makeText(CanceladoActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                } else {
                    correcto = dbfrapOrden1.editarFRAPcanceladoDelegacion(claveGenerada, selectedEstado, selectedDelegacion, selectedAsignacion,motivocancelacion);
                    if (correcto) {
                        Intent intent = new Intent(CanceladoActivity.this, DatosServicioCancelado.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        Toast.makeText(CanceladoActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CanceladoActivity.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(DelegacionReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
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

                Intent intent = new Intent(CanceladoActivity.this, InicioFrap.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish(); // Cierra la actividad actual
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });


    }
    private void verRegistro(){
        Intent intent = new Intent(this, CanceladoActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}