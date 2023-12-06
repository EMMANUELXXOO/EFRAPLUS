package com.Cruzroja.frapplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import com.Cruzroja.frapplus.Adaptadores.listamateriales;
import com.Cruzroja.frapplus.Adaptadores.listaservidores;
import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.Vistas.Configuracion;
import com.Cruzroja.frapplus.Vistas.ConsumosReporte;
import com.Cruzroja.frapplus.Vistas.ConsumosResumenMateriales;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.Cruzroja.frapplus.funciones.ClaveGenerator;
import com.Cruzroja.frapplus.funciones.DateUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VerServidores extends AppCompatActivity {



    private listaservidores AdapterServidores; //adapter
    private ArrayList<listaservidores> listaArrayServidores;
    private RecyclerView listaservidoresRecycler;

    private FloatingActionButton btnhome ;
    private int id = 0;
    int bande=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_servidores);

        btnhome = findViewById(R.id.home);

        // Obtén una instancia del Vibrator
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                id = extras.getInt("id");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("id");
        }
        listaservidoresRecycler = findViewById(R.id.OrdenServidores);
        listaservidoresRecycler.setLayoutManager(new LinearLayoutManager(this));

        DBFRAPOrdenControl dbfrapOrden = new DBFRAPOrdenControl(VerServidores.this);
        listaArrayServidores = new ArrayList<>();
        AdapterServidores = new listaservidores(this, dbfrapOrden.verServidores());

        listaservidoresRecycler.setAdapter(AdapterServidores);

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén una instancia del Vibrator
                Intent intent = new Intent(VerServidores.this, Configuracion.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });
    }
}