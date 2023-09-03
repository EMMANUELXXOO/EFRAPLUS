package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

public class TrasladoReporte3 extends AppCompatActivity {
    TextView viewstado,viewClave,viewfechamodif;
    boolean correcto = false;
    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traslado_reporte3);


        EditText editTextNombrePaciente = findViewById(R.id.editTextNombrePaciente);
        EditText editTextNombreTestigo = findViewById(R.id.editTextNombreTestigo);

        SignaturePad signaturePadFirmaPaciente = findViewById(R.id.signaturePadFirmaPaciente);
        SignaturePad signaturePadFirmaTestigo = findViewById(R.id.signaturePadFirmaTestigo);





        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }

        viewstado = findViewById(R.id.viewestado);
        viewClave = findViewById(R.id.viewclave);
        viewfechamodif = findViewById(R.id.viewFechamodif);


        guardarButton = findViewById(R.id.fabGuardar);
        btnregresar = findViewById(R.id.fabRegresar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(TrasladoReporte3.this);
        frapOrden = dbfrapOrdenControl.verFRAPCONTROL(id);

        // Declaramos la variable para la clave
        String clave;

        if (frapOrden != null) {
            viewstado.setText(String.valueOf(frapOrden.getStatus()));
            viewstado.setTextColor(getResources().getColor(R.color.colorPrimary));
            clave = frapOrden.getClave();   // Guardamos la clave
            viewClave.setText(clave);       // Mostramos la clave
            viewfechamodif.setText(frapOrden.getFechadeMoficacion());
        } else {
            Log.d("VerInformeFrap", "Valor de id: " + id);
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrasladoReporte3.this, TrasladoReporte2.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
            }
        });
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Obtener referencias a los EditText y Spinners
                // Obtener los valores de los EditText que contienen las horas
                Bitmap firmaBitmap1 = signaturePadFirmaPaciente.getSignatureBitmap();
                Bitmap firmaBitmap2 = signaturePadFirmaTestigo.getSignatureBitmap();

                String NombrePaciente =  editTextNombrePaciente.getText().toString();
                String NombreTestigo =  editTextNombreTestigo.getText().toString();

                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (firmaBitmap1 == null||firmaBitmap2==null||NombrePaciente.isEmpty()||NombreTestigo.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(TrasladoReporte3.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {

                    // Convertir el Bitmap1 de la firma a Base64
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    firmaBitmap1.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String FirmaPaciente = Base64.encodeToString(byteArray, Base64.DEFAULT);

                    // Convertir el Bitmap2 de la firma a Base64
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    firmaBitmap2.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2);
                    byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                    String FirmaTestigo = Base64.encodeToString(byteArray2, Base64.DEFAULT);


                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(TrasladoReporte3.this);
                    long ID = dbfrapOrden1.insertaTraslado3Reporte(claveGenerada,FirmaPaciente,NombrePaciente,FirmaTestigo,NombreTestigo);
                    if (ID > 0) {
                        Toast.makeText(TrasladoReporte3.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TrasladoReporte3.this, MainReporte.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarTraslado3Reporte(claveGenerada,FirmaPaciente,NombrePaciente,FirmaTestigo,NombreTestigo);
                        if (correcto) {
                            Intent intent = new Intent(TrasladoReporte3.this, MainReporte.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(TrasladoReporte3.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TrasladoReporte3.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(TrasladoReporte3.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}