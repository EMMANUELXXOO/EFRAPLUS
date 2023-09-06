package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.Cruzroja.frapplus.funciones.CharacterWithCoordinates;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluacion2Reporte extends AppCompatActivity {
    // Declaración de variables de clase
    TextView viewstado, viewClave, viewfechamodif;
    private FloatingActionButton guardarButton, btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;
    private Spinner spinnerLesion;
    private ImageView imageView;
    private Bitmap originalBitmap;
    private Bitmap bitmapWithText;
    private Paint paint;
    private List<CharacterWithCoordinates> selectedChars = new ArrayList<>();
    float realX = 0;
    float realY = 0;
    private String selectedText = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion2_reporte);

        // Inicialización de vistas y variables
        spinnerLesion = findViewById(R.id.spinnerLesion);
        imageView = findViewById(R.id.imageView);
         // Datos para el Spinner
        String[] datos = {" ", "D-Deformidades", "CD-Contusiones", "A-Abrasiones", "P-Penetraciones", "MP-Movimiento Paradojico", "C-Crepitacion", "H-Heridas", "F-Fracturas", "ES-Enfisema Subcutaneo", "Q-Quemaduras", "L-Laceraciones", "E-Edema", "AS-Alteracion de Sensibilidad", "AM-Alteracion de MOvilidad", "DO-Dolor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLesion.setAdapter(adapter);
        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.frapc);
        bitmapWithText = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(75);

        // Manejo del Spinner
        spinnerLesion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedText = spinnerLesion.getSelectedItem().toString(); // Almacenar elemento seleccionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No hacer nada cuando no se selecciona nada
            }
        });

        // Manejo de eventos táctiles en la ImageView
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        if (!selectedText.equals(" ")) {
                            float touchX = event.getX();
                            float touchY = event.getY();
                            float relativeX = touchX / imageView.getWidth();
                            float relativeY = touchY / imageView.getHeight();
                            float realX = relativeX * originalBitmap.getWidth();
                            float realY = relativeY * originalBitmap.getHeight();
                            String selectedString = selectedText;
                            selectedChars.add(new CharacterWithCoordinates(selectedText, realX, realY));
                            drawTextOnImage();
                        }
                        break;
                }

                return true;
            }
        });

        // Vibración al hacer clic en el botón
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }

        // Inicialización de vistas y manejo de eventos de botones
        viewstado = findViewById(R.id.viewestado);
        viewClave = findViewById(R.id.viewclave);
        viewfechamodif = findViewById(R.id.viewFechamodif);
        guardarButton = findViewById(R.id.fabGuardar);
        btnregresar = findViewById(R.id.fabRegresar);

        // Obtención del ID de la actividad anterior
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        // Consulta de la base de datos y muestra de datos en vistas
        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(Evaluacion2Reporte.this);
        frapOrden = dbfrapOrdenControl.verFRAPCONTROL(id);
        String clave;

        if (frapOrden != null) {
            viewstado.setText(String.valueOf(frapOrden.getStatus()));
            viewstado.setTextColor(getResources().getColor(R.color.colorPrimary));
            clave = frapOrden.getClave();
            viewClave.setText(clave);
            viewfechamodif.setText(frapOrden.getFechadeMoficacion());
        } else {
            Log.d("VerInformeFrap", "Valor de id: " + id);
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }

        // Manejo de clics en el botón de regresar
        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Evaluacion2Reporte.this, MainReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

        // Manejo de clics en el botón de guardar
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String claveGenerada = frapOrden.getClave();
                String IMagen = getBase64FromBitmap();

                // Realizar validaciones
                if (IMagen.isEmpty()) {
                    Toast.makeText(Evaluacion2Reporte.this, "Por favor, Selecciona una Imagen", Toast.LENGTH_SHORT).show();
                } else {
                    // Insertar o editar información en la base de datos
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(Evaluacion2Reporte.this);
                    long ID = dbfrapOrden1.insertaEvaluacion2IReporte(claveGenerada, IMagen);

                    if (ID > 0) {
                        Toast.makeText(Evaluacion2Reporte.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Evaluacion2Reporte.this, Evaluacion2Reporte2.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                    } else {
                        correcto = dbfrapOrden1.editarEvaluacion2Reporte(claveGenerada, IMagen);

                        if (correcto) {
                            Intent intent = new Intent(Evaluacion2Reporte.this, Evaluacion2Reporte2.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            Toast.makeText(Evaluacion2Reporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Evaluacion2Reporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(Evaluacion2Reporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Obtener la representación Base64 de la imagen actual
        String base64Image = getBase64FromBitmap();
    }

    private void drawTextOnImage() {
        // Crear una copia de la imagen original para dibujar el texto
        bitmapWithText = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmapWithText);

        // Inicializar las coordenadas de dibujo
        float x = 100;
        float y = 100;

        // Iterar a través de los caracteres con coordenadas y dibujar texto en la imagen
        for (CharacterWithCoordinates characterWithCoordinates : selectedChars) {
            String selectedString = characterWithCoordinates.getText();

            // Verificar si la cadena tiene al menos una letra
            if (!selectedString.isEmpty()) {
                // Mostrar la cadena completa si no contiene un guión "-", de lo contrario, mostrar solo la parte antes del guión
                int dashIndex = selectedString.indexOf('-');
                String displayText = (dashIndex >= 0) ? selectedString.substring(0, dashIndex) : selectedString;

                // Dibujar el texto en la posición especificada
                canvas.drawText(displayText, characterWithCoordinates.getRealX(), characterWithCoordinates.getRealY(), paint);
            }
        }

        // Establecer la imagen modificada en la ImageView
        imageView.setImageBitmap(bitmapWithText);
    }

    // Función para obtener la representación Base64 de la imagen actual
    private String getBase64FromBitmap() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapWithText.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}














