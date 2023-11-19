package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.DBSQLITE.dbinsercion;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.VerActivity;
import com.Cruzroja.frapplus.editaractivity;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.Cruzroja.frapplus.funciones.DateUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerInformeFrap extends AppCompatActivity {

    ImageView imageView;
    FloatingActionButton btneditar, btneliminar, btnregresar;
    TextView viewid, viewclave, viewstatus, viewfechac, viewfechamodi;
    boolean correcto = false;
    FRAPOrden frapOrden;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_informe_frap);

        btneditar = findViewById(R.id.fabEditar);
        btneliminar = findViewById(R.id.fabEliminar);
        btnregresar = findViewById(R.id.fabRegresar);
        viewid = findViewById(R.id.ViewID);
        viewclave = findViewById(R.id.ViewClave);
        viewstatus = findViewById(R.id.ViewStatus);
        viewfechac = findViewById(R.id.ViewFechaCreacion);
        viewfechamodi = findViewById(R.id.ViewFechaModificacion);
        imageView = findViewById(R.id.imageView);

        // Obtiene el valor de 'id' de la intención.
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("id")) {
            id = extras.getInt("id");
        } else {
            // Manejar el caso en el que "id" no esté presente en la intención
            // Puedes mostrar un mensaje de error, cargar un valor predeterminado, etc.
            id = -1; // Establece un valor predeterminado o maneja el error de otra manera
        }

        final DBFRAPOrdenControl DBFRAPOrdenControl = new DBFRAPOrdenControl(VerInformeFrap.this);
        frapOrden = DBFRAPOrdenControl.verFRAPCONTROL(id);

        if (frapOrden != null) {
            viewid.setText(String.valueOf(frapOrden.getId()));
            viewclave.setText(frapOrden.getClave());
            viewstatus.setText(frapOrden.getStatus());

            if (viewstatus.getText().toString().equals("CANCELADO")) {
                viewstatus.setTextColor(getResources().getColor(R.color.red));
            }
            if (viewstatus.getText().toString().equals("COMPLETADO")) {
                viewstatus.setTextColor(getResources().getColor(R.color.green));
            }
            if (viewstatus.getText().toString().equals("PENDIENTE")) {
                viewstatus.setTextColor(getResources().getColor(R.color.colorPrimary));
            }

            viewfechac.setText(frapOrden.getFechaCreacion());
            viewfechamodi.setText(frapOrden.getFechadeMoficacion());
        }

        String fechamodf = DateUtils.getCurrentDate();
        if ("COMPLETADO".equals(viewstatus.getText().toString()) || "CANCELADO".equals(viewstatus.getText().toString())) {
            btneditar.setEnabled(false);
        } else {
            btneditar.setEnabled(true);

            btneditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    correcto = DBFRAPOrdenControl.editarfrapOrdenFechaMod(id, fechamodf);

                    if (correcto) {
                        verRegistro();
                    } else {
                        Toast.makeText(VerInformeFrap.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista();
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                if (vibrator != null && vibrator.hasVibrator()) {
                    vibrator.vibrate(100);
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(VerInformeFrap.this);
                builder.setMessage("¿Desea eliminar El Reporte?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (DBFRAPOrdenControl.eliminarFrapOrden(id)) {
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                                if (vibrator != null && vibrator.hasVibrator()) {
                                    vibrator.vibrate(100);
                                }
                            }
                        }).show();
            }
        });
    }

    private void verRegistro() {
        Intent intent = new Intent(this, MainReporte.class);
        intent.putExtra("id", id);
        startActivity(intent);
        ///////Para los botones bloqueados
// Dentro de la actividad donde deseas bloquear el botón
        SharedPreferences preferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("botonBloqueado", true); // Establece el estado del botón como bloqueado
        editor.apply();

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private void lista() {
        Intent intent = new Intent(this, InicioFrap.class);
        intent.putExtra("id", id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        finish();
    }
}
