package com.Cruzroja.frapplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.Vistas.Configuracion;
import com.Cruzroja.frapplus.Vistas.DelegacionReporte;
import com.Cruzroja.frapplus.Vistas.MainReporte;
import com.Cruzroja.frapplus.entidades.FRAPOrden;

public class Configurarservidormysql extends AppCompatActivity {

    EditText URLSERVIDOR,USUARIO,PASSWORD;
    Button GuardarConfiguracion;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurarservidormysql);

        // Obtén una instancia del Vibrator
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }

        URLSERVIDOR = findViewById(R.id.editTextServerURL);
        USUARIO = findViewById(R.id.editTextUsername);
        PASSWORD = findViewById(R.id.editTextPassword);
        GuardarConfiguracion = findViewById(R.id.btnGuardarConfiguracion);


        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(Configurarservidormysql.this);
        frapOrden = dbfrapOrdenControl.verFRAPCONTROL(id);

        // Declaramos la variable para la clave
        String clave;





        GuardarConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URLSERVIDOR1 = URLSERVIDOR.getText().toString();
                String USUARIO1 = USUARIO.getText().toString();
                String PASSWORD1 = PASSWORD.getText().toString();


                DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(Configurarservidormysql.this);
                long ID = dbfrapOrden1.insertarServidor(URLSERVIDOR1, USUARIO1, PASSWORD1);
                if (ID > 0) {
                    Toast.makeText(Configurarservidormysql.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Configurarservidormysql.this, Configuracion.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    finish();
                    // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje

                } else {
                    correcto = dbfrapOrden1.editarServidor(URLSERVIDOR1, USUARIO1, PASSWORD1);
                    if (correcto) {
                        Intent intent = new Intent(Configurarservidormysql.this, Configuracion.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();

                    } else {
                        Toast.makeText(Configurarservidormysql.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(DelegacionReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}