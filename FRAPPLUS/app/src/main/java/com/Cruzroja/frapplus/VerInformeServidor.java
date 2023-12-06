package com.Cruzroja.frapplus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.entidades.DatosSERVIDORES;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

public class VerInformeServidor extends AppCompatActivity {
    EditText viewid, ViewURLSERVIDOR, ViewUSUARIO, ViewCONTRASEÑA;
    FloatingActionButton fabRegresar, fabEliminar;
    int tiempoEspera = 10000; // 10 segundos
    int numeroIntentos = 2;
    DatosSERVIDORES datosSERVIDORES;
    private int id = 0;

    FRAPOrden frapOrden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_informe_servidor);

        viewid = findViewById(R.id.ViewID);
        ViewURLSERVIDOR = findViewById(R.id.ViewURLSERVIDOR);
        ViewUSUARIO = findViewById(R.id.ViewUSUARIO);
        ViewCONTRASEÑA = findViewById(R.id.ViewCONTRASEÑA);


        fabRegresar = findViewById(R.id.fabRegresar);

        fabEliminar = findViewById(R.id.fabEliminar);


        fabRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista();
            }
        });

    }

    private void lista() {
        Intent intent = new Intent(this, VerServidores.class);
        intent.putExtra("id", id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        finish();
    }
    public void onRequestButtonClicked(View view) {
        String url = "http://192.168.1.182:80/php2/Insertartabla.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest resultadoPost = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(VerInformeServidor.this, "Usuario insertado exitosamente", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VerInformeServidor.this, "Errors s " + error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("VolleyError", "Error: " + error.toString());

                    }
                }) {
            @Override
            public RetryPolicy getRetryPolicy() {
                return new DefaultRetryPolicy(tiempoEspera, numeroIntentos, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
             //   parametros.put("id", id.getText().toString());
               // parametros.put("Clave", Clave.getText().toString());
                //parametros.put("Status", Status.getText().toString());
                //parametros.put("FechaCreacion", FechaCreacion.getText().toString());
                //parametros.put("FechadeMoficacion", FechaModificacion.getText().toString());
                return parametros;
            }
        };

        queue.add(resultadoPost);

    }
}
