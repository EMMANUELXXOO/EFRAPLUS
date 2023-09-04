package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Locale;

public class Evaluacion2Reporte3 extends AppCompatActivity {
    TextView viewstado,viewClave,viewfechamodif;
    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
RadioButton radA,radD,radV,radI;
EditText editTextHora,editTextFr,editTextGluc,editTextFc,editTextSaO2,editTextTas,editTextTemp,editTextTad;
Spinner SpinnerEKG;
    ImageButton btnHora;
    boolean correcto = false;
    String Miniexamen="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion2_reporte3);

        ///EDitexts
        editTextFr = findViewById(R.id.editTextFr);
        editTextGluc = findViewById(R.id.editTextGluc);
        editTextFc = findViewById(R.id.editTextFc);
        editTextSaO2 = findViewById(R.id.editTextSaO2);
        editTextTas = findViewById(R.id.editTextTas);
        editTextTemp = findViewById(R.id.editTextTemp);
        editTextTad = findViewById(R.id.editTextTad);

////////////////RadBUttons

        radA = findViewById(R.id.radA);
        radD = findViewById(R.id.radD);
        radV = findViewById(R.id.radV);
        radI = findViewById(R.id.radI);

        radA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radA.isChecked()) {
                    radD.setChecked(false);
                    radV.setChecked(false);
                    radI.setChecked(false);
                    Miniexamen = "A";
                }
            }
        });

        radD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radD.isChecked()) {
                    radA.setChecked(false);
                    radV.setChecked(false);
                    radI.setChecked(false);
                    Miniexamen = "D";
                }
            }
        });

        radV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radV.isChecked()) {
                    radA.setChecked(false);
                    radD.setChecked(false);
                    radI.setChecked(false);
                    Miniexamen = "V";
                }
            }
        });

        radI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radI.isChecked()) {
                    radA.setChecked(false);
                    radD.setChecked(false);
                    radV.setChecked(false);
                    Miniexamen = "I";
                }
            }
        });


        //////////////////////////////////////////////////////////   //SPiner
        SpinnerEKG = findViewById(R.id.SpinnerEKG);

// Datos que quieres mostrar en el Spinner (reemplaza esto con tus datos)
        String[] datosEKG = {"", "Sinusal", "Bloqueo","Asistolia","TV","FV"};

// Crea un ArrayAdapter y configúralo con los datos y el diseño de cada elemento
        ArrayAdapter<String> adapterEKG = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datosEKG);

// Configura el diseño del desplegable (opcional)
        adapterEKG.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Establece el adaptador en tu Spinner
        SpinnerEKG.setAdapter(adapterEKG);





        //////////Boton HOra
        btnHora = findViewById(R.id.btnHora);
        editTextHora = findViewById(R.id.editTextHora);

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén la hora actual
                Calendar calendar = Calendar.getInstance();
                int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
                int minutoActual = calendar.get(Calendar.MINUTE);

                // Crea un TimePickerDialog para que el usuario seleccione la hora
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Evaluacion2Reporte3.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // La hora seleccionada por el usuario
                                String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

                                // Muestra la hora seleccionada en el EditText
                                editTextHora.setText(horaSeleccionada);
                            }
                        },
                        horaActual, // Hora actual
                        minutoActual, // Minuto actual
                        DateFormat.is24HourFormat(Evaluacion2Reporte3.this) // Formato de 24 horas
                );

                // Muestra el diálogo de selección de hora
                timePickerDialog.show();
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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(Evaluacion2Reporte3.this);
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
                Intent intent = new Intent(Evaluacion2Reporte3.this, Evaluacion2Reporte2.class);
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

                String Hora = editTextHora.getText().toString();
                String FR = editTextFr.getText().toString();
                String FC = editTextFc.getText().toString();
                String Tas = editTextTas.getText().toString();
                String Tad = editTextTad.getText().toString();
                String SAO2 = editTextSaO2.getText().toString();
                String Temp = editTextTemp.getText().toString();
                String GLuc = editTextGluc.getText().toString();
                String EKG = SpinnerEKG.getSelectedItem().toString();
                String claveGenerada = frapOrden.getClave();;

//              Realizar validaciones
                if (claveGenerada.isEmpty()){
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(Evaluacion2Reporte3.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia


                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(Evaluacion2Reporte3.this);
                    long ID = dbfrapOrden1.insertaEvaluacion2IReporte3(claveGenerada,Hora,EKG,FR,FC,Tas,Tad,SAO2,Temp,GLuc,Miniexamen);
                    if (ID > 0) {
                        Toast.makeText(Evaluacion2Reporte3.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Evaluacion2Reporte3.this, Evaluacion2Reporte4.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarEvaluacion2Reporte3(claveGenerada,Hora,EKG,FR,FC,Tas,Tad,SAO2,Temp,GLuc,Miniexamen);
                        if (correcto) {
                            Intent intent = new Intent(Evaluacion2Reporte3.this, Evaluacion2Reporte4.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Evaluacion2Reporte3.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Evaluacion2Reporte3.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(Evaluacion2Reporte3.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}