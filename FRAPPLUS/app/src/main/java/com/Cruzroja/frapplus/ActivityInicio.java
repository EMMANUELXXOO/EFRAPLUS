package com.Cruzroja.frapplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Cruzroja.frapplus.Vistas.InicioFrap;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityInicio extends AppCompatActivity {



    //Crear objeto lista
    ListView lista;
    EditText dataEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Button btnConfiguracion = findViewById(R.id.btnConfiguracion);
        Button btnIniciarTurno = findViewById(R.id.btnIniciarTurno);
        Button btnFrapCancelado = findViewById(R.id.btnCancelado);
        Button btnNuevo = findViewById(R.id.btnNuevo);

        ///////////////////////////////////////////
       // TextView dataTextView = findViewById(R.id.dataTextView);

        dataEditText = findViewById(R.id.dataEditText);

        /////////////////////////////////////////////////////////
        //lista
        lista = findViewById(R.id.Lista);

        List<String[]> datos;

        datos = new ArrayList<>();

        datos.add(new String[]{"Example 1", "Value 1"});
        datos.add(new String[]{"Example 2", "Value 2"});
        datos.add(new String[]{"Example 3", "Value 3"});
        datos.add(new String[]{"Example 4", "Value 4"});

// Crear adaptador personalizado
        ArrayAdapter<String[]> adaptador = new ArrayAdapter<String[]>(this, R.layout.item_layout, R.id.column1, datos) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemView = super.getView(position, convertView, parent);
                TextView column1TextView = itemView.findViewById(R.id.column1);
                TextView column2TextView = itemView.findViewById(R.id.column2);

                String[] itemData = getItem(position);
                if (itemData != null) {
                    column1TextView.setText(itemData[0]);
                    column2TextView.setText(itemData[1]);
                }

                return itemView;
            }
        };

        lista.setAdapter(adaptador);

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para cambiar de actividad
                EditText txtId = findViewById(R.id.dataEditText);

                Intent intent = new Intent(ActivityInicio.this, MainActivity.class);
                intent.putExtra("id", txtId.getText().toString());
                startActivity(intent);

            }
        });


        btnFrapCancelado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para cambiar de actividad
                Intent intent = new Intent(ActivityInicio.this, InicioFrap.class);
                startActivity(intent);
            }
        });



        btnIniciarTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para cambiar de actividad
                Intent intent = new Intent(ActivityInicio.this, IniciarTurnoActivity.class);
                startActivity(intent);
            }
        });

        btnConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para cambiar de actividad
                Intent intent = new Intent(ActivityInicio.this, ConfiguracionActivity.class);
                startActivity(intent);
            }
        });

    }
    public void onRequestButtonClicked(View view) {
        String url = "http://192.168.1.91/android_mysql/insertar.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest resultadoPost = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ActivityInicio.this, "Usuario insertado exitosamente", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityInicio.this, "Errors s " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("Estado", dataEditText.getText().toString());
                return parametros;
            }
        };

        queue.add(resultadoPost);
    }

}