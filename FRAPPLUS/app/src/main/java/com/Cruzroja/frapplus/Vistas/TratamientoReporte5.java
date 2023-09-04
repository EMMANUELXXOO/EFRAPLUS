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

public class TratamientoReporte5 extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    Spinner SpinerMedicamentos,SpinerVia,SpinerTE;
    boolean correcto = false;
    ImageButton btnHora;
    EditText editTextHora,editTextDosis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamiento_reporte5);
        editTextDosis = findViewById(R.id.editTextDosis);
        ///////////////////////////////////////////////////////////////////////////////////
        SpinerMedicamentos = findViewById(R.id.SpinerMedicamentos);
        String[] SpinerMedicamentos1 = {"",
                "ACIDO ACETILSALICILICO 500MG",
                "ADENOCINA 6MG/2ML",
                "ADRENALINA 1 MG/1ML",
                "ASPIRINA 500 MG",
                "ATROPINA 1MG/1ML",
                "BUTILHIOSCINA 20MG/1ML",
                "CAPTOPRIL 20MG/1ML",
                "CLORFENAMINA 10MG/2ML",
                "DIAZEPAM 10MG/2ML",
                "FUROSEMIDA 20MG/2ML",
                "ISOSORBIDA SL 5MG",
                "KETOROLACO 30MG/1ML",
                "LIDOCINA 2%",
                "METAMIZOL SODICO 1G/2ML",
                "MIDAZOLAM 15MG/3ML",
                "NALBUFINA 10MG/1ML",
                "NALOXONA 0.4MG/1ML",
                "NITROGLICERINA 0.4MG",
                "RANITIDINA 50G/2ML",
                "SALBUTAMOL SOLUCION 5MG/ML",
                "SALBUTAMOL IDM",
                "SULFATO DE MAGNESIO 1G/10ML"
        };


        ArrayAdapter<String> SpinerMedicamentosAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, SpinerMedicamentos1);
        SpinerMedicamentosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerMedicamentos.setAdapter(SpinerMedicamentosAdapter);


        SpinerVia = findViewById(R.id.SpinerVia);
        String[] SpinerVia1 = {"","IV","ORL","SBL","IM"};

        ArrayAdapter<String> SpinerViaAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, SpinerVia1);
        SpinerViaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerVia.setAdapter(SpinerViaAdapter);

        SpinerTE = findViewById(R.id.SpinerTE);
        String[] SpinerTE1 = {"","Desfribilacion","CardioVersion","M.T.C","Ninguno"};

        ArrayAdapter<String> SpinerTEAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, SpinerTE1);
        SpinerTEAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerTE.setAdapter(SpinerTEAdapter);

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
                        TratamientoReporte5.this,
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
                        DateFormat.is24HourFormat(TratamientoReporte5.this) // Formato de 24 horas
                );

                // Muestra el diálogo de selección de hora
                timePickerDialog.show();
            }
        });



        /////////////////////////////////////////////////////////////////////////////////////
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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(TratamientoReporte5.this);
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
                Intent intent = new Intent(TratamientoReporte5.this, TratamientoReporte4.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        //Metodo para insertar y editar informacion
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              // EditText editTextLesionesCausadas = findViewById(R.id.editTextLesionesCausadas);
                String hora = editTextHora.getText().toString();
                String VIa = SpinerVia.getSelectedItem().toString();
                String TE = SpinerTE.getSelectedItem().toString();
                String Medicamento = SpinerMedicamentos.getSelectedItem().toString();
                String Dosis = editTextDosis.getText().toString();
                //valorSeleccionado
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (claveGenerada.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(TratamientoReporte5.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(TratamientoReporte5.this);
                    long ID = dbfrapOrden1.insertaTratamiento5Reporte(claveGenerada,hora,Medicamento,Dosis,VIa,TE);
                    if (ID > 0) {
                        Toast.makeText(TratamientoReporte5.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TratamientoReporte5.this, TratamientoReporte6.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarTratamiento5Reporte(claveGenerada,hora,Medicamento,Dosis,VIa,TE);
                        if (correcto) {
                            Intent intent = new Intent(TratamientoReporte5.this, TratamientoReporte6.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(TratamientoReporte5.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TratamientoReporte5.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(TratamientoReporte5.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}