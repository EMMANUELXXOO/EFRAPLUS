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

public class AgregarMedicamentos extends AppCompatActivity {
    TextView viewstado,viewClave,viewfechamodif;
    private FloatingActionButton guardarButton,btnregresar;

    TextView editTextDosis,editTextHoraCaptura;
    private ImageButton btnHoraCaptura;
    Spinner SpinnerMedicamento,SpinnerVia,SpinnerTE;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_medicamentos);
        // Obtén una instancia del Vibrator
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }

        btnHoraCaptura = findViewById(R.id.btnHrCaptura);
        viewstado = findViewById(R.id.viewestado);
        viewClave = findViewById(R.id.viewclave);
        viewfechamodif = findViewById(R.id.viewFechamodif);

        guardarButton = findViewById(R.id.fabGuardar);
        editTextDosis = findViewById(R.id.editTextDosis);
        btnregresar = findViewById(R.id.fabRegresar);
        editTextHoraCaptura = findViewById(R.id.editTextHoraCaptura);


        btnHoraCaptura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén la hora actual
                Calendar calendar = Calendar.getInstance();
                int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
                int minutoActual = calendar.get(Calendar.MINUTE);

                // Crea un TimePickerDialog para que el usuario seleccione la hora
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AgregarMedicamentos.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // La hora seleccionada por el usuario
                                String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

                                // Muestra la hora seleccionada en el EditText
                                editTextHoraCaptura.setText(horaSeleccionada);
                            }
                        },
                        horaActual, // Hora actual
                        minutoActual, // Minuto actual
                        DateFormat.is24HourFormat(AgregarMedicamentos.this) // Formato de 24 horas
                );

                // Muestra el diálogo de selección de hora
                timePickerDialog.show();
            }
        });

        /////////////////spiners
        //
        //SpinnerMedicamento,SpinnerVia,SpinnerTE
        // Adaptadores para los spinners
        SpinnerMedicamento = findViewById(R.id.SpinnerMedicamento);
        String[] SpinnerMedicamento1 = {"",
                "Acido Acetilsalicilico 500mg(tab)",
                "Adenosina 6mg/2ml",
                "Adrenalina 1mg/1ml",
                "Amiodarona 150mg/3ml",
                "Atropina 1mg/1ml",
                "Bromuro de Vecuronio 4mg",
                "Carbon Activado 50g",
                "Clorfenamina 10mg/1ml",
                "Diazepam 10mg/2ml",
                "Gluconato de Calcio 10% 1g/10ml",
                "Fentanilo 0.25mg/5ml-0.5mg/10ml",
                "Fentanilo 0.5mg/10ml",
                "Ketamina 500mg/10ml",
                "Ketorolaco 30mg/1ml",
                "Lidocaina 2% Frasco",
                "Midazolam 15mg/3ml",
                "Nalbufina 10mg/1ml",
                "Naloxona 0.4mg/1ml",
                "Nitroglicerina tableta sublingual",
                "Bromuro de Ondansetron 8mg amputa",
                "Paracetamol Supositorios 300mg",
                "Sulfato de Magnesio 1g/10ml",
                "Salbutamol 5mg/ml p/nebulizar Frasco 10ml"};

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinnerMedicamentoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinnerMedicamento1);
        SpinnerMedicamentoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerMedicamento.setAdapter(SpinnerMedicamentoAdapter);


        SpinnerVia = findViewById(R.id.SpinnerVia);
        String[] SpinnerVia1 = {"",
                "IV",
                "ORL",
                "SBL",
                "IM"};

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinnerViaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinnerVia1);
        SpinnerViaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerVia.setAdapter(SpinnerViaAdapter);



        SpinnerTE = findViewById(R.id.SpinnerTE);
        String[] SpinnerTE1 = {"",
                "Desfribilacion",
                "Cardioversion",
                "M.T.C",
                "Ninguno"};

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinnerTEAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinnerTE1);
        SpinnerTEAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerTE.setAdapter(SpinnerTEAdapter);


        ////////////////////////////////////////////////////

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }
        String clave;
        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(AgregarMedicamentos.this);
        frapOrden = dbfrapOrdenControl.verFRAPCONTROL(id);

        if (frapOrden != null) {
            viewstado.setText(String.valueOf(frapOrden.getStatus()));
            viewstado.setTextColor(getResources().getColor(R.color.colorPrimary));
            clave = frapOrden.getClave();   // Guardamos la clave
            viewClave.setText(clave);       // Mostramos la clave
            viewfechamodif.setText(frapOrden.getFechadeMoficacion());
        } else {
            Log.d("VerInformeFrap", "Valor de id: " + id);
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }//


        //Metodo para insertar y editar informacion
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Obtener referencias a los EditText y Spinners
                // Obtener los valores de los EditText que contienen las horas
                String horaCaptura = editTextHoraCaptura.getText().toString();
                String Dosis = editTextDosis.getText().toString();
                String Via = SpinnerVia.getSelectedItem().toString();
                String TE = SpinnerTE.getSelectedItem().toString();
                String Medicamento = SpinnerMedicamento.getSelectedItem().toString();
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (claveGenerada.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(AgregarMedicamentos.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(AgregarMedicamentos.this);
                    long ID = dbfrapOrden1.insertarMedicamentosConsumo(claveGenerada,horaCaptura,Medicamento,Dosis,Via,TE);
                    if (ID > 0) {
                        Toast.makeText(AgregarMedicamentos.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AgregarMedicamentos.this, AgregarMedicamentos.class);
                        intent.putExtra("id", id);


                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarMedicamentosConsumo(claveGenerada,horaCaptura,Medicamento,Dosis,Via,TE);
                        if (correcto) {
                            Intent intent = new Intent(AgregarMedicamentos.this, AgregarMedicamentos.class);
                            intent.putExtra("id", id);

                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(AgregarMedicamentos.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AgregarMedicamentos.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(AgregarMedicamentos.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén una instancia del Vibrator
                Intent intent = new Intent(AgregarMedicamentos.this, ConsumosReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });
    }
}