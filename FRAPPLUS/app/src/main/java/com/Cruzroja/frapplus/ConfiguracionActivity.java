package com.Cruzroja.frapplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfiguracionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

    Button btnAgregarImpresora = findViewById(R.id.btnAgregarImpresora);
    Button btnVerImpresora = findViewById(R.id.btnVerImpresora);
    Button btnAgregarServidor = findViewById(R.id.btnAgregarServidor);
    Button btnVerServidor = findViewById(R.id.btnVerServidores);

        btnAgregarImpresora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para cambiar de actividad
                Intent intent = new Intent(ConfiguracionActivity.this, AgregarImpresoras.class);
                startActivity(intent);
            }
        });

        btnVerImpresora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para cambiar de actividad
                Intent intent = new Intent(ConfiguracionActivity.this, VerImpresoras.class);
                startActivity(intent);
            }
        });


        btnAgregarServidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para cambiar de actividad
                Intent intent = new Intent(ConfiguracionActivity.this, AgregarServidor.class);
                startActivity(intent);
            }
        });

        btnVerServidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para cambiar de actividad
                Intent intent = new Intent(ConfiguracionActivity.this, VerServidores.class);
                startActivity(intent);
            }
        });


    }
    public void clickRegresar(View view) {
        Intent intent = new Intent(this, ActivityInicio.class);
        startActivity(intent);
    }
}