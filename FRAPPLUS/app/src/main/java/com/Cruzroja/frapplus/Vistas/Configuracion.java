package com.Cruzroja.frapplus.Vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;

import com.Cruzroja.frapplus.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Configuracion extends AppCompatActivity {

    // Definir una constante para el ID de Inicio
    private static final int ID_Configuracion = R.id.ConfiguracionMENU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion2);

        // Obtén una instancia del Vibrator
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {if (item.getItemId() == R.id.ConfiguracionMENU) {
                Intent intent = new Intent(Configuracion.this, Configuracion.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish(); // Cierra la actividad actual
                return true;
            } else if (item.getItemId() == R.id.Inicio) {
                Intent intent = new Intent(Configuracion.this, InicioFrap.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish(); // Cierra la actividad actual
                return true;
            } else if (item.getItemId() == R.id.ayuda) {
                Intent intent = new Intent(Configuracion.this, Ayuda.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish(); // Cierra la actividad actual
                return true;
            }
                return false;
            }
        });

    }
}