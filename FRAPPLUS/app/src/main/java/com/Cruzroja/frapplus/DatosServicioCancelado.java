package com.Cruzroja.frapplus;

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
import com.Cruzroja.frapplus.Vistas.MainReporte;
import com.Cruzroja.frapplus.Vistas.ServicioReporte;
import com.Cruzroja.frapplus.Vistas.ServicioReporte2;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatosServicioCancelado extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;
    private Spinner estadoSpinner;
    private Spinner delegacionSpinner;
    private Spinner asignacionSpinner;
    private FloatingActionButton guardarButton,btnregresar;

    boolean correcto = false;

    FRAPOrden frapOrden;
    int id;
    /////////////////////////////////////////////////////////////////////////
    //Formulario
    private Spinner SpinnerMotivoAtencion;
    private Spinner SpinnerSubmotivo;
    private TextInputLayout viewDia;
    private TextInputLayout viewFecha;
    private EditText editTextFecha;
    private ImageButton btnFecha;
    private Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_servicio_cancelado);

        //////////////////////////////////////spinners

        SpinnerMotivoAtencion = findViewById(R.id.SpinnerMotivoAtencion);
        SpinnerSubmotivo = findViewById(R.id.SpinnerSubmotivo);

        String[] MotivoAtencion = {"Enfermedad",
                "Traumatismo",
                "Ginecoobsetrico",
                "Emocional",
                "Abuso Sustancias"};

        String[] SubMotivo = {"Alergia",
                "Anafilaxis",
                "Alteraciones Musculares",
                "Cardiovascular",
                "Asma",
                "Cognitivo Emocional",
                "Debilidad por Enfermedad",
                "Desconocido",
                "Diarrea",
                "Dificultad Respiratoria",
                "Dolor Abdominal Agudo",
                "Dolor de Cabeza-Cefalea",
                "Dolor Toracico-No Cardiaco",
                "Dolor Lumbar",
                "Emergencia Diabética-Hiperglucemia",
                "Emergencia Diabética-Hipoglucemia",
                "EPOC-Enfermedad Pulmonar Obstructiva Crónica",
                "EVC-Embolia,AIT",
                "Gineco-Obstetrico",
                "Herida Infectada",
                "Hepatico",
                "Gastroenteritis Por Infeccion",
                "Intoxicacion por Alcohol",
                "Mareo",
                "Neurologico",
                "No Especifico",
                "Oncologico",
                "Post-Operativo,Complicacion",
                "Renal,Patologia",
                "Respiratorio",
                "Hemorragia-Enfermedad",
                "Septicemia",
                "Sincope",
                "Sindrome Metabolico",
                "Urogenital",
                "Emergencia Menor",
                "Otro"};
        // Adaptadores para los spinners
        ArrayAdapter<String> MotivoAtencionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MotivoAtencion);
        ArrayAdapter<String> SubmotivoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SubMotivo);

        MotivoAtencionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SubmotivoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerMotivoAtencion.setAdapter(MotivoAtencionAdapter);
        SpinnerSubmotivo.setAdapter(SubmotivoAdapter);
        //////////////////////////////////////
        EditText  editTextHoraLlamada = findViewById(R.id.editTextHoraLlamada);
        ImageButton btnHoraLlamada = findViewById(R.id.btnHoraLlamada);
        btnHoraLlamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén la hora actual
                Calendar calendar = Calendar.getInstance();
                int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
                int minutoActual = calendar.get(Calendar.MINUTE);

                // Crea un TimePickerDialog para que el usuario seleccione la hora
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        DatosServicioCancelado.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // La hora seleccionada por el usuario
                                String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

                                // Muestra la hora seleccionada en el EditText

                                editTextHoraLlamada.setText(horaSeleccionada);
                            }
                        },
                        horaActual, // Hora actual
                        minutoActual, // Minuto actual
                        DateFormat.is24HourFormat(DatosServicioCancelado.this) // Formato de 24 horas
                );

                // Muestra el diálogo de selección de hora
                timePickerDialog.show();
            }
        });
        /////////////////////////////////////////////////////////////
        EditText  editTextHoraSalida = findViewById(R.id.editTextHoraSalida);
        ImageButton btnHoraSalida = findViewById(R.id.btnHoraSalida);

        btnHoraSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén la hora actual
                Calendar calendar = Calendar.getInstance();
                int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
                int minutoActual = calendar.get(Calendar.MINUTE);

                // Crea un TimePickerDialog para que el usuario seleccione la hora
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        DatosServicioCancelado.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // La hora seleccionada por el usuario
                                String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

                                // Muestra la hora seleccionada en el EditText
                                editTextHoraSalida.setText(horaSeleccionada);
                            }
                        },
                        horaActual, // Hora actual
                        minutoActual, // Minuto actual
                        DateFormat.is24HourFormat(DatosServicioCancelado.this) // Formato de 24 horas
                );

                // Muestra el diálogo de selección de hora
                timePickerDialog.show();
            }
        });
        ///////////////////////////////////////////////
        EditText  editTextHoraLlegada = findViewById(R.id.editTextHoraLlegada);
        ImageButton btnHoraLlegada = findViewById(R.id.btnHoraLlegada);
        btnHoraLlegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén la hora actual
                Calendar calendar = Calendar.getInstance();
                int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
                int minutoActual = calendar.get(Calendar.MINUTE);

                // Crea un TimePickerDialog para que el usuario seleccione la hora
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        DatosServicioCancelado.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // La hora seleccionada por el usuario
                                String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

                                // Muestra la hora seleccionada en el EditText
                                editTextHoraLlegada.setText(horaSeleccionada);
                            }
                        },
                        horaActual, // Hora actual
                        minutoActual, // Minuto actual
                        DateFormat.is24HourFormat(DatosServicioCancelado.this) // Formato de 24 horas
                );

                // Muestra el diálogo de selección de hora
                timePickerDialog.show();
            }
        });
        ///////////////////////////////////////////////
        EditText  editTextHoraTraslado = findViewById(R.id.editTextHoraTraslado);
        ImageButton btnHoraTraslado = findViewById(R.id.btnHoraTraslado);
        btnHoraTraslado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén la hora actual
                Calendar calendar = Calendar.getInstance();
                int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
                int minutoActual = calendar.get(Calendar.MINUTE);

                // Crea un TimePickerDialog para que el usuario seleccione la hora
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        DatosServicioCancelado.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // La hora seleccionada por el usuario
                                String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

                                // Muestra la hora seleccionada en el EditText
                                editTextHoraTraslado.setText(horaSeleccionada);
                            }
                        },
                        horaActual, // Hora actual
                        minutoActual, // Minuto actual
                        DateFormat.is24HourFormat(DatosServicioCancelado.this) // Formato de 24 horas
                );
                // Muestra el diálogo de selección de hora
                timePickerDialog.show();
            }
        });
        ///////////////////////////////////////////////
        EditText  editTextHoraHospital = findViewById(R.id.editTextHoraHospital);
        ImageButton btnHoraHospital = findViewById(R.id.btnHoraHospital);
        btnHoraHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén la hora actual
                Calendar calendar = Calendar.getInstance();
                int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
                int minutoActual = calendar.get(Calendar.MINUTE);
                // Crea un TimePickerDialog para que el usuario seleccione la hora
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        DatosServicioCancelado.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // La hora seleccionada por el usuario
                                String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

                                // Muestra la hora seleccionada en el EditText
                                editTextHoraHospital.setText(horaSeleccionada);
                            }
                        },
                        horaActual, // Hora actual
                        minutoActual, // Minuto actual
                        DateFormat.is24HourFormat(DatosServicioCancelado.this) // Formato de 24 horas
                );

                // Muestra el diálogo de selección de hora
                timePickerDialog.show();
            }
        });
        ///////////////////////////////////////////////
        EditText  editTextHoraDisponible = findViewById(R.id.editTextHoraDisponible);
        ImageButton btnHoraDisponible = findViewById(R.id.btnHoraDisponible);
        btnHoraDisponible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén la hora actual
                Calendar calendar = Calendar.getInstance();
                int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
                int minutoActual = calendar.get(Calendar.MINUTE);

                // Crea un TimePickerDialog para que el usuario seleccione la hora
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        DatosServicioCancelado.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // La hora seleccionada por el usuario
                                String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

                                // Muestra la hora seleccionada en el EditText
                                editTextHoraDisponible.setText(horaSeleccionada);
                            }
                        },
                        horaActual, // Hora actual
                        minutoActual, // Minuto actual
                        DateFormat.is24HourFormat(DatosServicioCancelado.this) // Formato de 24 horas
                );

                // Muestra el diálogo de selección de hora
                timePickerDialog.show();
            }
        });
        /////////////////////////////////////////////////////////////
        viewDia = findViewById(R.id.viewDia);
        // Obtiene el nombre del día actual en palabras y lo establece en el TextInputLayout
        String nombreDiaActual = getNombreDiaActual();
        viewDia.getEditText().setText(nombreDiaActual);
        //////////////////
        editTextFecha = findViewById(R.id.editTextFecha);
        btnFecha = findViewById(R.id.btnFecha);
        calendar = Calendar.getInstance();
        // Configura un listener para el botón o el EditText para mostrar el DatePickerDialog
        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        editTextFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        ///////////
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }
        viewstado = findViewById(R.id.viewestado);
        viewClave = findViewById(R.id.viewclave);
        viewfechamodif = findViewById(R.id.viewFechamodif);
        estadoSpinner = findViewById(R.id.estadoSpinner);
        delegacionSpinner = findViewById(R.id.delegacionSpinner);
        asignacionSpinner = findViewById(R.id.asignacionSpinner);
        guardarButton = findViewById(R.id.fabGuardar);
        btnregresar = findViewById(R.id.fabRegresar);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }
        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(DatosServicioCancelado.this);
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
                Intent intent = new Intent(DatosServicioCancelado.this, MainReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        //Metodo para insertar y editar informacion
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Obtener referencias a los EditText y Spinners
                EditText editTextFecha = findViewById(R.id.editTextFecha);
                EditText editTextHoraLlamada = findViewById(R.id.editTextHoraLlamada);
                Spinner spinnerMotivoAtencion = findViewById(R.id.SpinnerMotivoAtencion);
//              Obtener valores de los campos
                String fecha = editTextFecha.getText().toString();
                String horaLlamada = editTextHoraLlamada.getText().toString();
                String motivoAtencion = spinnerMotivoAtencion.getSelectedItem().toString();
//              Realizar validaciones
                if (fecha.isEmpty() || horaLlamada.isEmpty() || motivoAtencion.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(DatosServicioCancelado.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    String dia = viewDia.getEditText().getText().toString();
                    // Obtener los valores de los EditText que contienen las horas
                    String horaSalida = editTextHoraSalida.getText().toString();
                    String horaLlegada = editTextHoraLlegada.getText().toString();
                    String horaTraslado = editTextHoraTraslado.getText().toString();
                    String horaHospital = editTextHoraHospital.getText().toString();
                    String horaDisponible = editTextHoraDisponible.getText().toString();
                    String selectedMotivo = SpinnerMotivoAtencion.getSelectedItem().toString();
                    String selectedSubmotivo = SpinnerSubmotivo.getSelectedItem().toString();
                    String claveGenerada = frapOrden.getClave();;
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(DatosServicioCancelado.this);
                    long ID = dbfrapOrden1.insertaServicio1CANCELADOReporte(claveGenerada,fecha,dia,horaLlamada,horaSalida,horaLlegada,horaTraslado,horaHospital,horaDisponible,selectedMotivo,selectedSubmotivo);
                    if (ID > 0) {
                        Toast.makeText(DatosServicioCancelado.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DatosServicioCancelado.this, activity_datos_servicio_cancelado2.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarServicio1CANCELADOReporte(claveGenerada,fecha,dia,horaLlamada,horaSalida,horaLlegada,horaTraslado,horaHospital,horaDisponible,selectedMotivo,selectedSubmotivo);
                        if (correcto) {
                            Intent intent = new Intent(DatosServicioCancelado.this, activity_datos_servicio_cancelado2.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(DatosServicioCancelado.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DatosServicioCancelado.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(DatosServicioCancelado.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
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
                        editTextFecha.setText(formattedDate);
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
    private String getNombreDiaActual() {
        Calendar calendar = Calendar.getInstance();
        // Establecer el idioma local en español
        Locale spanishLocale = new Locale("es", "ES");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", spanishLocale);
        return dateFormat.format(calendar.getTime());
    }


}