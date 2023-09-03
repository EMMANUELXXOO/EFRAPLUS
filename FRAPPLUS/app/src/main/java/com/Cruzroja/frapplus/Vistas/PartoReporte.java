package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PartoReporte extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    boolean correcto = false;
    int id;
    private Calendar calendar;
    Spinner SpinerGesta,SpinerCesareas,SpinerParto,SpinerAbortos,SpinerSemanadeGestacion,SpinerMembrana;
    EditText editTextFechaProbableParto,editTextHoraInicioContraccion,editTextFreciencia
    ,editTextDuracion;
    private ImageButton btnFechaProbDeParto,btnHrInicioContraccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parto_reporte);

        editTextFechaProbableParto = findViewById(R.id.editTextFechaProbableParto);
        editTextHoraInicioContraccion = findViewById(R.id.editTextHoraInicioContraccion);
        editTextFreciencia = findViewById(R.id.editTextFreciencia);
        editTextDuracion = findViewById(R.id.editTextDuracion);

        btnFechaProbDeParto = findViewById(R.id.btnFechaProbDeParto);
        btnHrInicioContraccion = findViewById(R.id.btnHrInicioContraccion);
        calendar = Calendar.getInstance();
///////////////////////btnfecha y hora
        // Configura un listener para el botón o el EditText para mostrar el DatePickerDialog
        btnFechaProbDeParto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        btnHrInicioContraccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén la hora actual
                Calendar calendar = Calendar.getInstance();
                int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
                int minutoActual = calendar.get(Calendar.MINUTE);

                // Crea un TimePickerDialog para que el usuario seleccione la hora
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        PartoReporte.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // La hora seleccionada por el usuario
                                String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

                                // Muestra la hora seleccionada en el EditText
                                editTextHoraInicioContraccion.setText(horaSeleccionada);
                            }
                        },
                        horaActual, // Hora actual
                        minutoActual, // Minuto actual
                        DateFormat.is24HourFormat(PartoReporte.this) // Formato de 24 horas
                );

                // Muestra el diálogo de selección de hora
                timePickerDialog.show();
            }
        });

////////////////////////////////////////Spiners


////////////////////////////////////,SpinerCesareas,SpinerParto,SpinerAbortos,SpinerSemanadeGestacion,SpinerMembrana

        SpinerGesta = findViewById(R.id.SpinerGesta);


        String[] Gesta = {"0","1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15"};

        // Adaptadores para los spinners
        ArrayAdapter<String> GestaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Gesta);
        GestaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerGesta.setAdapter(GestaAdapter);

        //////////////////////////////////////
// Initialize SpinerCesareas
        SpinerCesareas = findViewById(R.id.SpinerCesareas);

        String[] Cesareas = {"0","1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15"};

        ArrayAdapter<String> CesareasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Cesareas);
        CesareasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerCesareas.setAdapter(CesareasAdapter);

// Example for SpinerParto
        SpinerParto = findViewById(R.id.SpinerParto);

        String[] Parto = {"0","1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15"};

        ArrayAdapter<String> PartoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Parto);
        PartoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerParto.setAdapter(PartoAdapter);

// Example for SpinerAbortos
        SpinerAbortos = findViewById(R.id.SpinerAbortos);

        String[] Abortos = {"0","1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15"};

        ArrayAdapter<String> AbortosAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Abortos);
        AbortosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerAbortos.setAdapter(AbortosAdapter);

// Example for SpinerSemanadeGestacion
        SpinerSemanadeGestacion = findViewById(R.id.SpinerSemanadeGestacion);

        // Create an array of values from "0" to "40"
        String[] SemanadeGestacion = new String[41];
        for (int i = 0; i <= 40; i++) {
            SemanadeGestacion[i] = String.valueOf(i);
        }


        ArrayAdapter<String> SemanadeGestacionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SemanadeGestacion);
        SemanadeGestacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerSemanadeGestacion.setAdapter(SemanadeGestacionAdapter);

// Example for SpinerMembrana
        SpinerMembrana = findViewById(R.id.SpinerMembrana);

        String[] Membrana = {"No Aplica","Integra", "Rota"};

        ArrayAdapter<String> MembranaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Membrana);
        MembranaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerMembrana.setAdapter(MembranaAdapter);

        ////////////////////////////////////////////////////////////////////////////////////
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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(PartoReporte.this);
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

        //Metodo para insertar y editar informacion
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                /////////////////////////////////////////////////,,,,,
                // Obtener los valores de los EditText que contienen las horas
                String Gesta = SpinerGesta.getSelectedItem().toString();
                String Cesareas = SpinerCesareas.getSelectedItem().toString();
                String Parto = SpinerParto.getSelectedItem().toString();
                String Abortos = SpinerAbortos.getSelectedItem().toString();
                String SemanasGestacion = SpinerSemanadeGestacion.getSelectedItem().toString();
                String Membrana = SpinerMembrana.getSelectedItem().toString();
                String FechaPosibleParto = editTextFechaProbableParto.getText().toString();
                String HoraInicioContraccion = editTextHoraInicioContraccion.getText().toString();
                String Frecuencia = editTextFreciencia.getText().toString();
                String Duracion = editTextDuracion.getText().toString();
                String claveGenerada = frapOrden.getClave();;

//              Realizar validaciones
                if (claveGenerada.isEmpty()){
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(PartoReporte.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia


                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(PartoReporte.this);
                    long ID = dbfrapOrden1.insertaPartoReporte(claveGenerada,Gesta,Cesareas,Parto,Abortos,SemanasGestacion,FechaPosibleParto,Membrana,HoraInicioContraccion,Frecuencia,Duracion);
                    if (ID > 0) {
                        Toast.makeText(PartoReporte.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PartoReporte.this, PartoReporte2.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarPartoReporte(claveGenerada,Gesta,Cesareas,Parto,Abortos,SemanasGestacion,FechaPosibleParto,Membrana,HoraInicioContraccion,Frecuencia,Duracion);
                        if (correcto) {
                            Intent intent = new Intent(PartoReporte.this, PartoReporte2.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(PartoReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PartoReporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(PartoReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén una instancia del Vibrator
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                // Verifica si el dispositivo admite la vibración
                if (vibrator != null && vibrator.hasVibrator()) {
                    // Realiza una vibración corta al hacer clic en el botón
                    vibrator.vibrate(100); // 100 milisegundos de vibración
                }

                finish();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

    }
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Actualiza el campo de fecha con la fecha seleccionada
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String formattedDate = sdf.format(calendar.getTime());
                        editTextFechaProbableParto.setText(formattedDate);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_NEGATIVE) {
                    // Manejar la cancelación del diálogo aquí si es necesario.
                }
            }
        });
        datePickerDialog.show();
    }
}