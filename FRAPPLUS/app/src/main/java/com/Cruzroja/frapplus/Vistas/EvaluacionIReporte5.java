package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EvaluacionIReporte5 extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_ireporte5);


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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(EvaluacionIReporte5.this);
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
                Intent intent = new Intent(EvaluacionIReporte5.this, EvaluacionIReporte4.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Obtén una instancia del Vibrator


                Intent intent = new Intent(EvaluacionIReporte5.this, MainReporte.class);
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
                editor5.putBoolean("botonBloqueado5", false);
                editor5.apply();

                SharedPreferences preferences6 = getSharedPreferences("MisPreferencias6", MODE_PRIVATE);
                SharedPreferences.Editor editor6 = preferences6.edit();
                editor6.putBoolean("botonBloqueado6", false);
                editor6.apply();

                SharedPreferences preferences7 = getSharedPreferences("MisPreferencias7", MODE_PRIVATE);
                SharedPreferences.Editor editor7 = preferences7.edit();
                editor7.putBoolean("botonBloqueado7", false);
                editor7.apply();

                SharedPreferences preferences8 = getSharedPreferences("MisPreferencias8", MODE_PRIVATE);
                SharedPreferences.Editor editor8 = preferences8.edit();
                editor8.putBoolean("botonBloqueado8", true);
                editor8.apply();



                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
                // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje


            }
        });
    }
}