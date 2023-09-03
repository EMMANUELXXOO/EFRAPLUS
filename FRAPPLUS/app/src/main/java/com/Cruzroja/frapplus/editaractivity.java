package com.Cruzroja.frapplus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.dbinsercion;
import com.Cruzroja.frapplus.entidades.FRAP;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class editaractivity extends AppCompatActivity {

    EditText txtEstado;

    Button btnGuardar;

    FRAP frap;

    int id=0;
    boolean correcto = false;

    FloatingActionButton btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaractivity);

        txtEstado = findViewById(R.id.txtEstatus);
        btnGuardar = findViewById(R.id.guardar);
        btnEliminar = findViewById(R.id.fabEliminar);




        final dbinsercion dbfrap = new dbinsercion(editaractivity.this);
        frap = dbfrap.verContacto(id);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(editaractivity.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbfrap.eliminar(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
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




        if (frap != null) {
           //Se desabilitan las conciones para poder editar
            //txtEstado.setText(frap.getStatus());

           // txtEstado.setInputType(InputType.TYPE_NULL);
            //  btnGuardar.setVisibility(View.VISIBLE);
            //btnGuardar.setInputType(InputType.TYPE_NULL);
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//Para validar que no quede vacio los campos
                if (!txtEstado.getText().toString().equals("")){
                    correcto= dbfrap.editarfrap(id,txtEstado.getText().toString());

                    if(correcto){
                        Toast.makeText(editaractivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(editaractivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(editaractivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, IniciarTurnoActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
    private void lista(){
        Intent intent = new Intent(this, IniciarTurnoActivity.class);
        startActivity(intent);
    }
}