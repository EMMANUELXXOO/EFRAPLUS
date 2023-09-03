package com.Cruzroja.frapplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText txtestado1 ;
    EditText txtid1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtid1 = findViewById(R.id.txtid);
        txtestado1 = findViewById(R.id.txtestado);
        String id = getIntent().getStringExtra("id");

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://192.168.1.91/android_mysql/registro.php?id="+id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            txtid1.setText(response.getString("id"));
                            txtestado1.setText(response.getString("Estado"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    private static final String TAG = "EERRORR"; // Cambia "MyApp" a un nombre significativo
                    @Override

                    public void onErrorResponse(VolleyError error) {

                        // Imprime el mensaje de error en el logcat
                        Log.e("Volley Error", error.toString());

                        // Muestra un mensaje al usuario
                        Toast.makeText(MainActivity.this, "Error en la solicitud", Toast.LENGTH_LONG).show();
                    }
                }
        );
        queue.add(jsonObjectRequest);

    }
}