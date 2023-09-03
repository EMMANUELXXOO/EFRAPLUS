package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CanceladoActivity extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;
    private Spinner estadoSpinner;
    private Spinner delegacionSpinner;
    private Spinner asignacionSpinner;
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

        estadoSpinner = findViewById(R.id.estadoSpinner);
        delegacionSpinner = findViewById(R.id.delegacionSpinner);
        asignacionSpinner = findViewById(R.id.asignacionSpinner);
        guardarButton = findViewById(R.id.fabGuardar);
        btnregresar = findViewById(R.id.fabRegresar);

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