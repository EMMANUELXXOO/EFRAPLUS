package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

public class Evaluacion2Reporte2 extends AppCompatActivity {
    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;

    boolean correcto = false;
    RadioButton radioImage1,radioImage2,radioImage3,radioImage4,radioImage5;
    ImageView image1,image2,image3,image4,image5;
    String Pupilasimagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion2_reporte2);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);


        radioImage1 = findViewById(R.id.radioImage1);
        radioImage2 = findViewById(R.id.radioImage2);
        radioImage3 = findViewById(R.id.radioImage3);
        radioImage4 = findViewById(R.id.radioImage4);
        radioImage5 = findViewById(R.id.radioImage5);

        radioImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioImage1.isChecked()) {
                    radioImage2.setChecked(false);
                    radioImage3.setChecked(false);
                    radioImage4.setChecked(false);
                    radioImage5.setChecked(false);
                    // Realiza alguna acción aquí cuando se selecciona radioImage1
                    // Obtén la imagen desde el ImageView
                    image1 = findViewById(R.id.image1);
                    Bitmap firmaBitmap = ((BitmapDrawable) image1.getDrawable()).getBitmap();

                    // Convierte la imagen en un array de bytes
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    firmaBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] imageBytes = byteArrayOutputStream.toByteArray();

                    // Codifica los bytes de la imagen en Base64
                    Pupilasimagen = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                }
            }
        });

        radioImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioImage2.isChecked()) {
                    radioImage1.setChecked(false);
                    radioImage3.setChecked(false);
                    radioImage4.setChecked(false);
                    radioImage5.setChecked(false);
                    // Realiza alguna acción aquí cuando se selecciona radioImage2
                    // Obtén la imagen desde el ImageView
                    image2 = findViewById(R.id.image2);
                    Bitmap firmaBitmap = ((BitmapDrawable) image2.getDrawable()).getBitmap();

                    // Convierte la imagen en un array de bytes
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    firmaBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] imageBytes = byteArrayOutputStream.toByteArray();

                    // Codifica los bytes de la imagen en Base64
                    Pupilasimagen = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                }
            }
        });

        radioImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioImage3.isChecked()) {
                    radioImage1.setChecked(false);
                    radioImage2.setChecked(false);
                    radioImage4.setChecked(false);
                    radioImage5.setChecked(false);
                    // Realiza alguna acción aquí cuando se selecciona radioImage3
                    // Obtén la imagen desde el ImageView
                    image3 = findViewById(R.id.image3);
                    Bitmap firmaBitmap = ((BitmapDrawable) image3.getDrawable()).getBitmap();

                    // Convierte la imagen en un array de bytes
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    firmaBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] imageBytes = byteArrayOutputStream.toByteArray();

                    // Codifica los bytes de la imagen en Base64
                    Pupilasimagen = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                }
            }
        });

        radioImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioImage4.isChecked()) {
                    radioImage1.setChecked(false);
                    radioImage2.setChecked(false);
                    radioImage3.setChecked(false);
                    radioImage5.setChecked(false);
                    // Realiza alguna acción aquí cuando se selecciona radioImage4
                    // Obtén la imagen desde el ImageView
                    image4 = findViewById(R.id.image4);
                    Bitmap firmaBitmap = ((BitmapDrawable) image4.getDrawable()).getBitmap();

                    // Convierte la imagen en un array de bytes
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    firmaBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] imageBytes = byteArrayOutputStream.toByteArray();

                    // Codifica los bytes de la imagen en Base64
                    Pupilasimagen = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                }
            }
        });

        radioImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioImage5.isChecked()) {
                    radioImage1.setChecked(false);
                    radioImage2.setChecked(false);
                    radioImage3.setChecked(false);
                    radioImage4.setChecked(false);
                    // Realiza alguna acción aquí cuando se selecciona radioImage5
                    // Obtén la imagen desde el ImageView
                    image5 = findViewById(R.id.image5);
                    Bitmap firmaBitmap = ((BitmapDrawable) image5.getDrawable()).getBitmap();

                    // Convierte la imagen en un array de bytes
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    firmaBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] imageBytes = byteArrayOutputStream.toByteArray();

                    // Codifica los bytes de la imagen en Base64
                    Pupilasimagen = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                }
            }
        });




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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(Evaluacion2Reporte2.this);
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
                // Obtén una instancia del Vibrator
                // Obtén una instancia del Vibrator
                Intent intent = new Intent(Evaluacion2Reporte2.this, Evaluacion2Reporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        //Metodo para insertar y editar informacion
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                /////////////////////////////////////////////////,,,,,
                // Obtener los valores de los EditText que contienen las horas

                String claveGenerada = frapOrden.getClave();;

//              Realizar validaciones
                if (Pupilasimagen.isEmpty()){
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(Evaluacion2Reporte2.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia


                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(Evaluacion2Reporte2.this);
                    long ID = dbfrapOrden1.insertaEvaluacion2IReporte2(claveGenerada,Pupilasimagen);
                    if (ID > 0) {
                        Toast.makeText(Evaluacion2Reporte2.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Evaluacion2Reporte2.this, Evaluacion2Reporte3.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarEvaluacion2Reporte2(claveGenerada,Pupilasimagen);
                        if (correcto) {
                            Intent intent = new Intent(Evaluacion2Reporte2.this, Evaluacion2Reporte3.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Evaluacion2Reporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Evaluacion2Reporte2.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(Evaluacion2Reporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}