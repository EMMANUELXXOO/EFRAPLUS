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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DelegacionReporte extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;
    private Spinner estadoSpinner;
    private Spinner delegacionSpinner;
    private Spinner asignacionSpinner;
    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;
    public int Bandera1=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegacion_reporte);

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

        estadoSpinner = findViewById(R.id.Spinnerestado);
        delegacionSpinner = findViewById(R.id.Spinnerdelegacion);
        asignacionSpinner = findViewById(R.id.Spinnerasignacion);
        guardarButton = findViewById(R.id.fabGuardar);
        btnregresar = findViewById(R.id.fabRegresar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(DelegacionReporte.this);
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

        // Adaptadores para los spinners
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estados);
        ArrayAdapter<String> delegacionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, delegaciones);
        ArrayAdapter<String> asignacionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, asignaciones);

        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        delegacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        asignacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        estadoSpinner.setAdapter(estadoAdapter);
        delegacionSpinner.setAdapter(delegacionAdapter);
        asignacionSpinner.setAdapter(asignacionAdapter);
        //Metodo para insertar y editar informacion
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedEstado = estadoSpinner.getSelectedItem().toString();
                String selectedDelegacion = delegacionSpinner.getSelectedItem().toString();
                String selectedAsignacion = asignacionSpinner.getSelectedItem().toString();
                String claveGenerada = frapOrden.getClave();;

            DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(DelegacionReporte.this);
            long ID = dbfrapOrden1.insertaDelegacionReporte(claveGenerada, selectedEstado, selectedDelegacion, selectedAsignacion);
            if (ID > 0) {
                Toast.makeText(DelegacionReporte.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DelegacionReporte.this, MainReporte.class);
                intent.putExtra("id", id);
                SharedPreferences preferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("botonBloqueado", false);
                editor.apply();

                //////
                SharedPreferences preferences1 = getSharedPreferences("MisPreferencias1", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = preferences1.edit();
                editor1.putBoolean("botonBloqueado1", true);
                editor1.apply();
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
                // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                Toast.makeText(DelegacionReporte.this, mensaje, Toast.LENGTH_SHORT).show();
            } else {
                correcto = dbfrapOrden1.editarDelegacionReporte(claveGenerada, selectedEstado, selectedDelegacion, selectedAsignacion);
                if (correcto) {
                    Intent intent = new Intent(DelegacionReporte.this, MainReporte.class);
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
                    editor1.putBoolean("botonBloqueado1", true);
                    editor1.apply();

                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    finish();
                    String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                    Toast.makeText(DelegacionReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DelegacionReporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(DelegacionReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
            }
        }
        });
        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén una instancia del Vibrator
                Intent intent = new Intent(DelegacionReporte.this, MainReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
    }
}