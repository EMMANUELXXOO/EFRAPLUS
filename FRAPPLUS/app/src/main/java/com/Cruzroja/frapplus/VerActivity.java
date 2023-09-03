package com.Cruzroja.frapplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.Cruzroja.frapplus.DBSQLITE.dbinsercion;
import com.Cruzroja.frapplus.entidades.FRAP;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    FloatingActionButton btneditar;
    EditText txtEstado;

    Button btnGuardar;

    FRAP frap;

    int id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtEstado = findViewById(R.id.txtEstatus);
        btnGuardar = findViewById(R.id.guardar);

        btneditar = findViewById(R.id.fabEditar);

        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, editaractivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("id");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("id");
        }

        dbinsercion dbfrap = new dbinsercion(VerActivity.this);
        frap = dbfrap.verContacto(id);


        if (frap != null) {
            txtEstado.setText(frap.getStatus());

            txtEstado.setInputType(InputType.TYPE_NULL);
            //  btnGuardar.setVisibility(View.VISIBLE);
            btnGuardar.setInputType(InputType.TYPE_NULL);
        }
    }
}