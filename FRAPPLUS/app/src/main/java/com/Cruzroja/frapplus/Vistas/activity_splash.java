package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.widget.ImageView;

import com.Cruzroja.frapplus.MainActivity;
import com.Cruzroja.frapplus.R;

public class activity_splash extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000; // 3 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // Obtén una instancia del Vibrator
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }

        ImageView splashImage;
        splashImage = findViewById(R.id.imageView);

        // Aquí puedes ajustar la imagen si es necesario
        // splashImage.setImageResource(R.drawable.splash_logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity_splash.this, InicioFrap.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}