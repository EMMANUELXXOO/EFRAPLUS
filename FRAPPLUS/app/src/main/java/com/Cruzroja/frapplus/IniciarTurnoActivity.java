package com.Cruzroja.frapplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.Cruzroja.frapplus.Adaptadores.listafrapadapter;
import com.Cruzroja.frapplus.DBSQLITE.DBHelper;
import com.Cruzroja.frapplus.DBSQLITE.dbinsercion;
import com.Cruzroja.frapplus.entidades.FRAP;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.IDN;
import java.util.ArrayList;

public class IniciarTurnoActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView txtbuscar;
    Button btncreardb;


    EditText Estado;
    FloatingActionButton btnGuardar;
    RecyclerView listafrapRecycler;

    ArrayList<FRAP> listaArrayfrap;

    listafrapadapter Adapterfrap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_turno);

        btnGuardar = findViewById(R.id.guardar);
        listafrapRecycler = findViewById(R.id.listaContactos);
        listafrapRecycler.setLayoutManager(new LinearLayoutManager(this));


        dbinsercion dbfrap = new dbinsercion(IniciarTurnoActivity.this);

        listaArrayfrap = new ArrayList<>();

        Adapterfrap = new listafrapadapter(dbfrap.mostrarContactos());

        listafrapRecycler.setAdapter(Adapterfrap);
        String estado="PENDIENTE";


        txtbuscar = findViewById(R.id.txtBuscar);

        txtbuscar.setOnQueryTextListener(this);

        btncreardb = findViewById(R.id.btncreardb);




        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbinsercion dbinsercion = new dbinsercion(IniciarTurnoActivity.this);
                long ID = dbinsercion.insertaFrap(estado.toString());

                if (ID > 0) {

                    Toast.makeText(IniciarTurnoActivity.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(IniciarTurnoActivity.this, IniciarTurnoActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(IniciarTurnoActivity.this, "Error de Guardado", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btncreardb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(IniciarTurnoActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db !=null){
                    Toast.makeText(IniciarTurnoActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(IniciarTurnoActivity.this, "ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void limpiar()
    {
        //Estado.setText("");
    }
//Metodos para buscar
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Adapterfrap.filtrado(s);
        return false;
    }
}