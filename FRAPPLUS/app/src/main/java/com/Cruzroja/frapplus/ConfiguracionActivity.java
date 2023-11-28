package com.Cruzroja.frapplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.Cruzroja.frapplus.Vistas.InicioFrap;

public class ConfiguracionActivity extends AppCompatActivity {

    Button btnAgregarImpresora;
    Button btnVerImpresora;

    Button btnAgregarServidor;
    Button btnVerServidor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);




        btnVerServidor= findViewById(R.id.btnVerServidores);


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



}