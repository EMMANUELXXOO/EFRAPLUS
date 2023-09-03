package com.Cruzroja.frapplus.Vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.Cruzroja.frapplus.ActivityInicio;
import com.Cruzroja.frapplus.Adaptadores.listaFrapOrdenadapter;
import com.Cruzroja.frapplus.Adaptadores.listafrapadapter;
import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.DBSQLITE.DBHelper;
import com.Cruzroja.frapplus.DBSQLITE.dbinsercion;
import com.Cruzroja.frapplus.IniciarTurnoActivity;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAP;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.Cruzroja.frapplus.funciones.ClaveGenerator;
import com.Cruzroja.frapplus.funciones.DateUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class InicioFrap extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private listaFrapOrdenadapter AdapterfrapOrden;
    private ArrayList<FRAPOrden> listaArrayfrapOrden;
    private RecyclerView listafrapOrdenRecycler;
    private SearchView txtBuscar;
    private FloatingActionButton btnGuardar;

    // Definir una constante para el ID de Inicio
    private static final int ID_INICIO = R.id.Inicio;
    private int id = 0;
    int bande=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_frap);

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.Inicio) {
                Intent intent = new Intent(InicioFrap.this, InicioFrap.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish(); // Cierra la actividad actual
                return true;
            } else if (item.getItemId() == R.id.ConfiguracionMENU) {
                Intent intent = new Intent(InicioFrap.this, Configuracion.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish(); // Cierra la actividad actual
                return true;
            } else if (item.getItemId() == R.id.ayuda) {
                Intent intent = new Intent(InicioFrap.this, Ayuda.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish(); // Cierra la actividad actual
                return true;
            }
            return false;
        });

        btnGuardar = findViewById(R.id.guardar);
        listafrapOrdenRecycler = findViewById(R.id.Orden);
        listafrapOrdenRecycler.setLayoutManager(new LinearLayoutManager(this));

        DBFRAPOrdenControl dbfrapOrden = new DBFRAPOrdenControl(InicioFrap.this);
        listaArrayfrapOrden = new ArrayList<>();
        AdapterfrapOrden = new listaFrapOrdenadapter(this, dbfrapOrden.verFrapOrden());

        listafrapOrdenRecycler.setAdapter(AdapterfrapOrden);

        String estado = "PENDIENTE";
        String Clave = "CFW";
        String FechaC = "16 de Agosto 2023";
        String fechaActual = DateUtils.getCurrentDate();
        String fechamodf = DateUtils.getCurrentDate();
        String claveGenerada = ClaveGenerator.generateUniqueKey();

        txtBuscar = findViewById(R.id.txtBuscar);

        if (id == 0) {
            // Configura el OnQueryTextListener
            txtBuscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    AdapterfrapOrden.filtrado(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    AdapterfrapOrden.filtrado(newText);
                    return true;
                }
            });
        } else {
            // Establece el texto en el SearchView
            txtBuscar.setQuery(String.valueOf(id), false);

            // Realiza la búsqueda automáticamente con el valor de 'id'
            AdapterfrapOrden.filtrado(String.valueOf(id));

            // Muestra un cuadro de diálogo informativo
            AlertDialog.Builder builder = new AlertDialog.Builder(InicioFrap.this);
            builder.setTitle("Mensaje");
            builder.setMessage("Reporte Cerrado, OK para continuar.");

            // Configura el botón OK
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Cierra el cuadro de diálogo cuando se presiona OK
                    dialogInterface.dismiss();

                    // Crea un Intent para abrir la actividad InicioFrap
                    Intent intent = new Intent(InicioFrap.this, InicioFrap.class);

                    // Inicia la actividad InicioFrap
                    startActivity(intent);

                    // Transición personalizada (opcional)
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

                    // Cierra la actividad actual
                    finish();
                }
            });
            // Crea y muestra el cuadro de diálogo
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        }



        btnGuardar.setOnClickListener(view -> {
            DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(InicioFrap.this);
            long ID = dbfrapOrden1.insertaFrapOrden(claveGenerada, estado, fechaActual, fechamodf);

            if (ID > 0) {
                Toast.makeText(InicioFrap.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InicioFrap.this, InicioFrap.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
            } else {
                Toast.makeText(InicioFrap.this, "Error de Guardado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Métodos para buscar
    @Override
    public boolean onQueryTextSubmit(String s) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }
        AdapterfrapOrden.filtrado(s);
        return false;
    }

    public void mostrarMensajeYBúsquedaAutomática(int id) {
        if (id == 0) {
            // Configura el OnQueryTextListener
            txtBuscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    AdapterfrapOrden.filtrado(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    AdapterfrapOrden.filtrado(newText);
                    return true;
                }
            });
        } else {
            // Establece el texto en el SearchView
            txtBuscar.setQuery(String.valueOf(id), false);

            // Realiza la búsqueda automáticamente con el valor de 'id'
            AdapterfrapOrden.filtrado(String.valueOf(id));

            // Muestra un cuadro de diálogo informativo
            AlertDialog.Builder builder = new AlertDialog.Builder(InicioFrap.this);
            builder.setTitle("Mensaje");
            builder.setMessage("Operación exitosa. Presiona OK para continuar.");

            // Configura el botón OK
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Cierra el cuadro de diálogo cuando se presiona OK
                    dialogInterface.dismiss();
                }
            });

            // Crea y muestra el cuadro de diálogo
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

}
