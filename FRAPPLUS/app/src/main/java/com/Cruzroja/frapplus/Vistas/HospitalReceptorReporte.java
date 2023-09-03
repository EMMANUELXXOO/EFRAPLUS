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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

public class HospitalReceptorReporte extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_receptor_reporte);

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }

        viewstado = findViewById(R.id.viewestado);
        viewClave = findViewById(R.id.viewclave);
        viewfechamodif = findViewById(R.id.viewFechamodif);

        EditText editTextEntregaPaciente = findViewById(R.id.editTextEntregaPaciente);
        EditText editTextRecibePaciente = findViewById(R.id.editTextRecibePaciente);

        SignaturePad signaturePadEntrega = findViewById(R.id.signaturePadEntrega);
        SignaturePad signaturePadRecibe = findViewById(R.id.signaturePadRecibe);


        guardarButton = findViewById(R.id.fabGuardar);
        btnregresar = findViewById(R.id.fabRegresar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(HospitalReceptorReporte.this);
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
                Intent intent = new Intent(HospitalReceptorReporte.this, MainReporte.class);
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
                Bitmap firmaBitmap1 = signaturePadEntrega.getSignatureBitmap();
                Bitmap firmaBitmap2 = signaturePadRecibe.getSignatureBitmap();

                String NombreQuienEntregaP =  editTextEntregaPaciente.getText().toString();
                String NombreQuienRecibeP =  editTextRecibePaciente.getText().toString();

                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (firmaBitmap1 == null||firmaBitmap2==null||NombreQuienEntregaP.isEmpty()||NombreQuienRecibeP.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(HospitalReceptorReporte.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {

                    // Convertir el Bitmap1 de la firma a Base64
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    firmaBitmap1.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String FNombreQuienEntregaPaciente = Base64.encodeToString(byteArray, Base64.DEFAULT);

                    // Convertir el Bitmap2 de la firma a Base64
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    firmaBitmap2.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2);
                    byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                    String FNombreQuienRecibePaciente = Base64.encodeToString(byteArray2, Base64.DEFAULT);


                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(HospitalReceptorReporte.this);
                    long ID = dbfrapOrden1.insertaHospitalReceptorReporte(claveGenerada,FNombreQuienEntregaPaciente,NombreQuienEntregaP,FNombreQuienRecibePaciente,NombreQuienRecibeP);
                    if (ID > 0) {
                        Toast.makeText(HospitalReceptorReporte.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HospitalReceptorReporte.this, MainReporte.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarHospitalReceptorReporte(claveGenerada,FNombreQuienEntregaPaciente,NombreQuienEntregaP,FNombreQuienRecibePaciente,NombreQuienRecibeP);
                        if (correcto) {
                            Intent intent = new Intent(HospitalReceptorReporte.this, MainReporte.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(HospitalReceptorReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(HospitalReceptorReporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(HospitalReceptorReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}