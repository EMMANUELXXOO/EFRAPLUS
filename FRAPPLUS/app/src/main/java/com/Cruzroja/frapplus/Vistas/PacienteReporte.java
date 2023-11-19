package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PacienteReporte extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;
    private Spinner SpinnerEdad;
    private Spinner SpinnerMunicipio;
    private Spinner SpinnerOcupacion;
    private Spinner SpinnerDerechoHabiente;
    String valorSeleccionado = "";
    String valorSeleccionadoNA = "";
    CheckBox checkbox_NA,checkbox_sexoF,checkbox_SexoM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_reporte);

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }

        viewstado = findViewById(R.id.viewestado);
        viewClave = findViewById(R.id.viewclave);
        viewfechamodif = findViewById(R.id.viewFechamodif);
        /////////////////////////////////////////////////
        EditText editTextNombreAfiliacion = findViewById(R.id.editTextNombreAfiliacion);
        EditText editTextColonia = findViewById(R.id.editTextColonia);
        EditText editTextDomicilio = findViewById(R.id.editTextDomicilio);
        EditText editTextTelefono = findViewById(R.id.editTextTelefono);

        /////////////////////////////////////////////CheckBox

        checkbox_NA = findViewById(R.id.checkbox_NA) ;
        checkbox_sexoF=findViewById(R.id.checkbox_sexoF);
        checkbox_SexoM = findViewById(R.id.checkbox_SexoM);

        checkbox_NA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_NA.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario
                    editTextNombreAfiliacion.setEnabled(false);
                    editTextNombreAfiliacion.setText("NA");
                    valorSeleccionadoNA = "NA";

                } else {
                    // Habilita la edición del EditText y borra el texto
                    editTextNombreAfiliacion.setEnabled(true);
                    editTextNombreAfiliacion.setText("");
                    valorSeleccionadoNA = "";
                }
            }
        });

        checkbox_sexoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_sexoF.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario

                    checkbox_SexoM.setChecked(false);
                    valorSeleccionado = "Femenino";

                }
            }
        });

        checkbox_SexoM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_SexoM.isChecked()) {
                    // Desmarca los otros RadioButtons si es necesario

                    checkbox_sexoF.setChecked(false);
                    valorSeleccionado = "Masculino";

                }
            }
        });
        /////////////////////////////////////////////CheckBox
/////////////////////////////////////////////Spiners
        SpinnerEdad = findViewById(R.id.SpinnerEdad);
        String[] SpinnerEdad1 = new String[100]; // Crear un arreglo de 100 elementos

        for (int i = 0; i < 100; i++) {
            SpinnerEdad1[i] = String.valueOf(i + 1); // Agregar el número como cadena
        }

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinnerEdadAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinnerEdad1);
        SpinnerEdadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerEdad.setAdapter(SpinnerEdadAdapter);


        SpinnerMunicipio = findViewById(R.id.SpinnerMunicipio);
        String[] SpinnerMunicipio1 = {"Tijuana",
                "Rosarito",
                "Ensenada",
                "Mexicali",
                "Tecate"};

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinnerMunicipioAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinnerMunicipio1);
        SpinnerMunicipioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerMunicipio.setAdapter(SpinnerMunicipioAdapter);


        SpinnerOcupacion = findViewById(R.id.SpinnerOcupacion);
        String[] SpinnerOcupacion1 = {"Empleado", "Desempleado"};

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinnerOcupacionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinnerOcupacion1);
        SpinnerOcupacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerOcupacion.setAdapter(SpinnerOcupacionAdapter);


        SpinnerDerechoHabiente = findViewById(R.id.SpinnerDerechoHabiente);
        String[] SpinnerDerechoHabiente1 = {"IMSS",
                "ISSSTE",
                "ISSSTECALU",
                "INSABI",
                "SMM",
                "Seguro Privado",
                "Ninguno"};

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinnerDerechoHabienteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinnerDerechoHabiente1);
        SpinnerDerechoHabienteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerDerechoHabiente.setAdapter(SpinnerDerechoHabienteAdapter);


        /////////////////////////////////////////////Spiners





        guardarButton = findViewById(R.id.fabGuardar);
        btnregresar = findViewById(R.id.fabRegresar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(PacienteReporte.this);
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


                Intent intent = new Intent(PacienteReporte.this, MainReporte.class);
                intent.putExtra("id", id);
                // Iniciar la nueva actividad
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        //Metodo para insertar y editar informacion
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Obtener referencias a los EditText y Spinners
                // Obtener los valores de los EditText que contienen las horas


                String NombreAfiliacion = editTextNombreAfiliacion.getText().toString();
                String Colonia = editTextColonia.getText().toString();
                String Domicilio = editTextDomicilio.getText().toString();
                String Telefono = editTextTelefono.getText().toString();
                String Edad = SpinnerEdad.getSelectedItem().toString();
                String Municipio = SpinnerMunicipio.getSelectedItem().toString();
                String Ocupacion = SpinnerOcupacion.getSelectedItem().toString();
                String DerechoAmbiente = SpinnerDerechoHabiente.getSelectedItem().toString();
                //valorSeleccionado
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (valorSeleccionado.isEmpty()||NombreAfiliacion.isEmpty()||Colonia.isEmpty()||Domicilio.isEmpty()||Telefono.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(PacienteReporte.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(PacienteReporte.this);
                    long ID = dbfrapOrden1.insertaPacienteReporte(claveGenerada,NombreAfiliacion,valorSeleccionado,Edad,Domicilio,Colonia,Municipio,Telefono,Ocupacion,DerechoAmbiente);
                    if (ID > 0) {
                        Toast.makeText(PacienteReporte.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PacienteReporte.this, MainReporte.class);
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
                        editor4.putBoolean("botonBloqueado4", true);
                        editor4.apply();

                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarPacienteReporte(claveGenerada,NombreAfiliacion,valorSeleccionado,Edad,Domicilio,Colonia,Municipio,Telefono,Ocupacion,DerechoAmbiente);
                        if (correcto) {
                            Intent intent = new Intent(PacienteReporte.this, MainReporte.class);
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
                            editor4.putBoolean("botonBloqueado4", true);
                            editor4.apply();

                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(PacienteReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PacienteReporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(PacienteReporte.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}