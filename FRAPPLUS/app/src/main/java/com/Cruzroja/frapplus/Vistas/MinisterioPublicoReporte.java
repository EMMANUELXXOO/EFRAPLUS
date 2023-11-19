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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

public class MinisterioPublicoReporte extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ministerio_publico_reporte);

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }

        viewstado = findViewById(R.id.viewestado);
        viewClave = findViewById(R.id.viewclave);
        viewfechamodif = findViewById(R.id.viewFechamodif);

        EditText editTextNombre = findViewById(R.id.editTextNombre);

        SignaturePad signaturePadSelloMinisterio = findViewById(R.id.signaturePadSelloMinisterio);

        guardarButton = findViewById(R.id.fabGuardar);
        btnregresar = findViewById(R.id.fabRegresar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(MinisterioPublicoReporte.this);
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
                Intent intent = new Intent(MinisterioPublicoReporte.this, MainReporte.class);
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
                Bitmap firmaBitmap = signaturePadSelloMinisterio.getSignatureBitmap();

                String NombreQuienRecibe =  editTextNombre.getText().toString();
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (firmaBitmap == null||NombreQuienRecibe.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(MinisterioPublicoReporte.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    // Convertir el Bitmap de la firma a Base64
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    firmaBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String FirmaSelloQuienRecibe = Base64.encodeToString(byteArray, Base64.DEFAULT);

                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(MinisterioPublicoReporte.this);
                    long ID = dbfrapOrden1.insertaMinisterioPublicoReporte(claveGenerada,FirmaSelloQuienRecibe,NombreQuienRecibe);
                    if (ID > 0) {
                        Toast.makeText(MinisterioPublicoReporte.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MinisterioPublicoReporte.this, MainReporte.class);
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
                        editor11.putBoolean("botonBloqueado11", false);
                        editor11.apply();


                        SharedPreferences preferences12 = getSharedPreferences("MisPreferencias12", MODE_PRIVATE);
                        SharedPreferences.Editor editor12 = preferences12.edit();
                        editor12.putBoolean("botonBloqueado12", false);
                        editor12.apply();


                        SharedPreferences preferences13 = getSharedPreferences("MisPreferencias13", MODE_PRIVATE);
                        SharedPreferences.Editor editor13 = preferences13.edit();
                        editor13.putBoolean("botonBloqueado13", true);
                        editor13.apply();

                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarMinisterioPublicoReporte(claveGenerada,FirmaSelloQuienRecibe,NombreQuienRecibe);
                        if (correcto) {
                            Intent intent = new Intent(MinisterioPublicoReporte.this, MainReporte.class);
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
                            editor11.putBoolean("botonBloqueado11", false);
                            editor11.apply();


                            SharedPreferences preferences12 = getSharedPreferences("MisPreferencias12", MODE_PRIVATE);
                            SharedPreferences.Editor editor12 = preferences12.edit();
                            editor12.putBoolean("botonBloqueado12", false);
                            editor12.apply();


                            SharedPreferences preferences13 = getSharedPreferences("MisPreferencias13", MODE_PRIVATE);
                            SharedPreferences.Editor editor13 = preferences13.edit();
                            editor13.putBoolean("botonBloqueado13", true);
                            editor13.apply();


                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(MinisterioPublicoReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MinisterioPublicoReporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(MinisterioPublicoReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}