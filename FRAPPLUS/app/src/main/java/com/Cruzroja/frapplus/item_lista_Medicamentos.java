package com.Cruzroja.frapplus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.SearchView;
import android.widget.Toast;

import com.Cruzroja.frapplus.Adaptadores.listaFrapOrdenadapter;
import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.Vistas.Ayuda;
import com.Cruzroja.frapplus.Vistas.Configuracion;
import com.Cruzroja.frapplus.Vistas.InicioFrap;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.Cruzroja.frapplus.funciones.ClaveGenerator;
import com.Cruzroja.frapplus.funciones.DateUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class item_lista_Medicamentos extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_lista_medicamentos);




    }




}