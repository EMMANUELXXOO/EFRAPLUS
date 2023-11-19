package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import com.Cruzroja.frapplus.Adaptadores.listaMedicamentos;
import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.funciones.ClaveGenerator;
import com.Cruzroja.frapplus.funciones.DateUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ConsumosResumenMedicamentos extends AppCompatActivity {

    private listaMedicamentos AdapterMedicamentos; //adapter
    private ArrayList<listaMedicamentos> listaArrayMedicamentos;
    private RecyclerView listafrapOrdenRecycler;

    private FloatingActionButton btnhome ;

    // Definir una constante para el ID de Inicio

    private int id = 0;
    int bande=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumos_resumen_medicamentos);

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
        listafrapOrdenRecycler = findViewById(R.id.OrdenMedicamentos);
        listafrapOrdenRecycler.setLayoutManager(new LinearLayoutManager(this));

        DBFRAPOrdenControl dbfrapOrden = new DBFRAPOrdenControl(ConsumosResumenMedicamentos.this);
        listaArrayMedicamentos = new ArrayList<>();
        AdapterMedicamentos = new listaMedicamentos(this, dbfrapOrden.verMedicamentosConsumo());

        listafrapOrdenRecycler.setAdapter(AdapterMedicamentos);

        String estado = "PENDIENTE";
        String Clave = "CFW";
        String FechaC = "16 de Agosto 2023";
        String fechaActual = DateUtils.getCurrentDate();
        String fechamodf = DateUtils.getCurrentDate();
        String claveGenerada = ClaveGenerator.generateUniqueKey();

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén una instancia del Vibrator
                Intent intent = new Intent(ConsumosResumenMedicamentos.this, ConsumosReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });


    }
}