package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

                        ///////Para los botones bloqueados
// Dentro de la actividad donde deseas bloquear el botón
                        SharedPreferences preferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("botonBloqueado", false);
                        editor.apply();

                        //////
                        SharedPreferences preferences1 = getSharedPreferences("MisPreferencias1", MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = preferences1.edit();
                        editor1.putBoolean("botonBloqueado1", false);
                        editor1.apply();

                        SharedPreferences preferences2 = getSharedPreferences("MisPreferencias2", MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = preferences2.edit();
                        editor2.putBoolean("botonBloqueado2", false);
                        editor2.apply();

                        SharedPreferences preferences3 = getSharedPreferences("MisPreferencias3", MODE_PRIVATE);
                        SharedPreferences.Editor editor3 = preferences3.edit();
                        editor3.putBoolean("botonBloqueado3", false);
                        editor3.apply();

                        SharedPreferences preferences4 = getSharedPreferences("MisPreferencias4", MODE_PRIVATE);
                        SharedPreferences.Editor editor4 = preferences4.edit();
                        editor4.putBoolean("botonBloqueado4", false);
                        editor4.apply();

                        SharedPreferences preferences5 = getSharedPreferences("MisPreferencias5", MODE_PRIVATE);
                        SharedPreferences.Editor editor5 = preferences5.edit();
                        editor5.putBoolean("botonBloqueado5", false);
                        editor5.apply();

                        SharedPreferences preferences6 = getSharedPreferences("MisPreferencias6", MODE_PRIVATE);
                        SharedPreferences.Editor editor6 = preferences6.edit();
                        editor6.putBoolean("botonBloqueado6", false);
                        editor6.apply();

                        SharedPreferences preferences7 = getSharedPreferences("MisPreferencias7", MODE_PRIVATE);
                        SharedPreferences.Editor editor7 = preferences7.edit();
                        editor7.putBoolean("botonBloqueado7", false);
                        editor7.apply();

                        SharedPreferences preferences8 = getSharedPreferences("MisPreferencias8", MODE_PRIVATE);
                        SharedPreferences.Editor editor8 = preferences8.edit();
                        editor8.putBoolean("botonBloqueado8", false);
                        editor8.apply();

                        SharedPreferences preferences9 = getSharedPreferences("MisPreferencias9", MODE_PRIVATE);
                        SharedPreferences.Editor editor9 = preferences9.edit();
                        editor9.putBoolean("botonBloqueado9", false);
                        editor9.apply();

                        SharedPreferences preferences10 = getSharedPreferences("MisPreferencias10", MODE_PRIVATE);
                        SharedPreferences.Editor editor10 = preferences10.edit();
                        editor10.putBoolean("botonBloqueado10", false);
                        editor10.apply();

                        SharedPreferences preferences11 = getSharedPreferences("MisPreferencias11", MODE_PRIVATE);
                        SharedPreferences.Editor editor11 = preferences11.edit();
                        editor11.putBoolean("botonBloqueado11", true);
                        editor11.apply();

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

                            ///////Para los botones bloqueados
// Dentro de la actividad donde deseas bloquear el botón
                            SharedPreferences preferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("botonBloqueado", false);
                            editor.apply();

                            //////
                            SharedPreferences preferences1 = getSharedPreferences("MisPreferencias1", MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = preferences1.edit();
                            editor1.putBoolean("botonBloqueado1", false);
                            editor1.apply();

                            SharedPreferences preferences2 = getSharedPreferences("MisPreferencias2", MODE_PRIVATE);
                            SharedPreferences.Editor editor2 = preferences2.edit();
                            editor2.putBoolean("botonBloqueado2", false);
                            editor2.apply();

                            SharedPreferences preferences3 = getSharedPreferences("MisPreferencias3", MODE_PRIVATE);
                            SharedPreferences.Editor editor3 = preferences3.edit();
                            editor3.putBoolean("botonBloqueado3", false);
                            editor3.apply();

                            SharedPreferences preferences4 = getSharedPreferences("MisPreferencias4", MODE_PRIVATE);
                            SharedPreferences.Editor editor4 = preferences4.edit();
                            editor4.putBoolean("botonBloqueado4", false);
                            editor4.apply();

                            SharedPreferences preferences5 = getSharedPreferences("MisPreferencias5", MODE_PRIVATE);
                            SharedPreferences.Editor editor5 = preferences5.edit();
                            editor5.putBoolean("botonBloqueado5", false);
                            editor5.apply();

                            SharedPreferences preferences6 = getSharedPreferences("MisPreferencias6", MODE_PRIVATE);
                            SharedPreferences.Editor editor6 = preferences6.edit();
                            editor6.putBoolean("botonBloqueado6", false);
                            editor6.apply();

                            SharedPreferences preferences7 = getSharedPreferences("MisPreferencias7", MODE_PRIVATE);
                            SharedPreferences.Editor editor7 = preferences7.edit();
                            editor7.putBoolean("botonBloqueado7", false);
                            editor7.apply();

                            SharedPreferences preferences8 = getSharedPreferences("MisPreferencias8", MODE_PRIVATE);
                            SharedPreferences.Editor editor8 = preferences8.edit();
                            editor8.putBoolean("botonBloqueado8", false);
                            editor8.apply();

                            SharedPreferences preferences9 = getSharedPreferences("MisPreferencias9", MODE_PRIVATE);
                            SharedPreferences.Editor editor9 = preferences9.edit();
                            editor9.putBoolean("botonBloqueado9", false);
                            editor9.apply();

                            SharedPreferences preferences10 = getSharedPreferences("MisPreferencias10", MODE_PRIVATE);
                            SharedPreferences.Editor editor10 = preferences10.edit();
                            editor10.putBoolean("botonBloqueado10", false);
                            editor10.apply();

                            SharedPreferences preferences11 = getSharedPreferences("MisPreferencias11", MODE_PRIVATE);
                            SharedPreferences.Editor editor11 = preferences11.edit();
                            editor11.putBoolean("botonBloqueado11", true);
                            editor11.apply();

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