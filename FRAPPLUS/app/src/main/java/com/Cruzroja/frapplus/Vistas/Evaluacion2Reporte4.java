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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Locale;

public class Evaluacion2Reporte4 extends AppCompatActivity {
    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;
    EditText editTextAlergias,editTextMedicamentoqueingiere,editTextEnfermedadesyCirugias
            ,editTextEventosPrevios,editTextHoraUltimaCOmida;
    ImageButton btnHrultimaComida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion2_reporte4);

        editTextAlergias = findViewById(R.id.editTextAlergias);
        editTextMedicamentoqueingiere = findViewById(R.id.editTextMedicamentoqueingiere);
        editTextEnfermedadesyCirugias = findViewById(R.id.editTextEnfermedadesyCirugias);
        editTextEventosPrevios = findViewById(R.id.editTextEventosPrevios);
        editTextHoraUltimaCOmida = findViewById(R.id.editTextHoraUltimaCOmida);
        btnHrultimaComida = findViewById(R.id.btnHrultimaComida);


        btnHrultimaComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén la hora actual
                Calendar calendar = Calendar.getInstance();
                int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
                int minutoActual = calendar.get(Calendar.MINUTE);

                // Crea un TimePickerDialog para que el usuario seleccione la hora
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Evaluacion2Reporte4.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // La hora seleccionada por el usuario
                                String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

                                // Muestra la hora seleccionada en el EditText
                                editTextHoraUltimaCOmida.setText(horaSeleccionada);
                            }
                        },
                        horaActual, // Hora actual
                        minutoActual, // Minuto actual
                        DateFormat.is24HourFormat(Evaluacion2Reporte4.this) // Formato de 24 horas
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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(Evaluacion2Reporte4.this);
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
                Intent intent = new Intent(Evaluacion2Reporte4.this, Evaluacion2Reporte3.class);
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

                String HoraUltimaC = editTextHoraUltimaCOmida.getText().toString();
                String Alergias = editTextAlergias.getText().toString();
                String Medicamentosi = editTextMedicamentoqueingiere.getText().toString();
                String EnfermedadesP = editTextEnfermedadesyCirugias.getText().toString();
                String EventosPR = editTextEventosPrevios.getText().toString();
                String claveGenerada = frapOrden.getClave();;

//              Realizar validaciones
                if (claveGenerada.isEmpty()){
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(Evaluacion2Reporte4.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia


                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(Evaluacion2Reporte4.this);
                    long ID = dbfrapOrden1.insertaEvaluacion2IReporte4(claveGenerada,Alergias,Medicamentosi,EnfermedadesP,HoraUltimaC,EventosPR);
                    if (ID > 0) {
                        Toast.makeText(Evaluacion2Reporte4.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Evaluacion2Reporte4.this, Evaluacion2Reporte5.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarEvaluacion2Reporte4(claveGenerada,Alergias,Medicamentosi,EnfermedadesP,HoraUltimaC,EventosPR);
                        if (correcto) {
                            Intent intent = new Intent(Evaluacion2Reporte4.this, Evaluacion2Reporte5.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Evaluacion2Reporte4.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Evaluacion2Reporte4.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(Evaluacion2Reporte4.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}