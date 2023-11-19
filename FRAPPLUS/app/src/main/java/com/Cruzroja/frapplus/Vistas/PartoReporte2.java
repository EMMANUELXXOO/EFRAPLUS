package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class PartoReporte2 extends AppCompatActivity {
    TextView viewstado,viewClave,viewfechamodif;

    private FloatingActionButton guardarButton,btnregresar;
    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;
    Spinner SpinerLugar,SpinerPlacentaExpulsada,SpinerProducto
            ,SpinerSexo,Spiner1Min,Spiner5Min,Spiner10Min,SpinerSILVERMANN1,SpinerSILVERMANN2;

    EditText editTextHoraNacimiento;
    ImageButton btnHrdeNacimiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parto_reporte2);

        ///////////////////////////////////////////Hora
        btnHrdeNacimiento = findViewById(R.id.btnHrdeNacimiento);
        editTextHoraNacimiento = findViewById(R.id.editTextHoraNacimiento);

        btnHrdeNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén la hora actual
                Calendar calendar = Calendar.getInstance();
                int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
                int minutoActual = calendar.get(Calendar.MINUTE);

                // Crea un TimePickerDialog para que el usuario seleccione la hora
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        PartoReporte2.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // La hora seleccionada por el usuario
                                String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

                                // Muestra la hora seleccionada en el EditText
                                editTextHoraNacimiento.setText(horaSeleccionada);
                            }
                        },
                        horaActual, // Hora actual
                        minutoActual, // Minuto actual
                        DateFormat.is24HourFormat(PartoReporte2.this) // Formato de 24 horas
                );

                // Muestra el diálogo de selección de hora
                timePickerDialog.show();
            }
        });


        ////////////////////////////////////////////////////Spiners
        // ,SpinerPlacentaExpulsada,SpinerProducto
        //            ,SpinerSexo,Spiner1Min,Spiner5Min,Spiner10Min,SpinerSILVERMANN1,SpinerSILVERMANN2
        SpinerLugar = findViewById(R.id.SpinerLugar);


        String[] Lugar = {"Ambulancia",
                "Calle",
                "Casa",
                "Cruz Roja",
                "Hospital Privado",
                "Trabajo",
                "Trasnporte Publico",
                "Vehiculo Particular"};

        // Adaptadores para los spinners


        ArrayAdapter<String> LugarAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Lugar);
        LugarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerLugar.setAdapter(LugarAdapter);
/////////////////////////////////////////////////////////PlacentaExpulsada
        SpinerPlacentaExpulsada = findViewById(R.id.SpinerPlacentaExpulsada);
        String[] PlacentaExpulsada = {"Si", "No"};

        ArrayAdapter<String> PlacentaExpulsadaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, PlacentaExpulsada);
        PlacentaExpulsadaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerPlacentaExpulsada.setAdapter(PlacentaExpulsadaAdapter);
////////////////////////////////////////////////////////////////
        SpinerProducto = findViewById(R.id.SpinerProducto);
        String[] Producto = {"Vivo", "Muerto"};

        ArrayAdapter<String> ProductoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Producto);
        ProductoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerProducto.setAdapter(ProductoAdapter);
// Initialize SpinerSexo
        SpinerSexo = findViewById(R.id.SpinerSexo);

        String[] Sexo = {"F", "M"};

        ArrayAdapter<String> SexoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Sexo);
        SexoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerSexo.setAdapter(SexoAdapter);

// Repeat this pattern for the other Spinners
// Example for Spiner1Min
        Spiner1Min = findViewById(R.id.Spiner1Min);

        String[] Min1 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        ArrayAdapter<String> Min1Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Min1);
        Min1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spiner1Min.setAdapter(Min1Adapter);

// Example for Spiner5Min
        Spiner5Min = findViewById(R.id.Spiner5Min);

        String[] Min5 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        ArrayAdapter<String> Min5Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Min5);
        Min5Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spiner5Min.setAdapter(Min5Adapter);

// Repeat this pattern for the other Spinners
// Example for Spiner10Min
        Spiner10Min = findViewById(R.id.Spiner10Min);

        String[] Min10 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        ArrayAdapter<String> Min10Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Min10);
        Min10Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spiner10Min.setAdapter(Min10Adapter);

// Example for SpinerSILVERMANN1
        SpinerSILVERMANN1 = findViewById(R.id.SpinerSILVERMANN1);

        String[] SILVERMANN1 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        ArrayAdapter<String> SILVERMANN1Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SILVERMANN1);
        SILVERMANN1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerSILVERMANN1.setAdapter(SILVERMANN1Adapter);

// Example for SpinerSILVERMANN2
        SpinerSILVERMANN2 = findViewById(R.id.SpinerSILVERMANN2);

        String[] SILVERMANN2 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

        ArrayAdapter<String> SILVERMANN2Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SILVERMANN2);
        SILVERMANN2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinerSILVERMANN2.setAdapter(SILVERMANN2Adapter);





        //////////////////////////////////////////////////////////////////////////////
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

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(PartoReporte2.this);
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
                /////////////////////////////////////////////////,,,,,,,
                //            ,,,,,,;
                // Obtener los valores de los EditText que contienen las horas
                String HoraNacimiento = editTextHoraNacimiento.getText().toString();
                String Lugar = SpinerLugar.getSelectedItem().toString();
                String PlacentaExpulsada = SpinerPlacentaExpulsada.getSelectedItem().toString();
                String Producto = SpinerProducto.getSelectedItem().toString();
                String Sexo = SpinerSexo.getSelectedItem().toString();
                String _1Min = Spiner1Min.getSelectedItem().toString();
                String _5Min = Spiner5Min.getSelectedItem().toString();
                String _10Min = Spiner10Min.getSelectedItem().toString();
                String SILVERMANN2 = SpinerSILVERMANN2.getSelectedItem().toString();
                String SILVERMANN1 = SpinerSILVERMANN1.getSelectedItem().toString();

                String claveGenerada = frapOrden.getClave();;

//              Realizar validaciones
                if (claveGenerada.isEmpty()){
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(PartoReporte2.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia


                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(PartoReporte2.this);
                    long ID = dbfrapOrden1.insertaParto2Reporte(claveGenerada,HoraNacimiento,Lugar,PlacentaExpulsada,Producto,Sexo,_1Min,_5Min,_10Min,SILVERMANN1,SILVERMANN2);
                    if (ID > 0) {
                        Toast.makeText(PartoReporte2.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PartoReporte2.this, MainReporte.class);
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
                        editor8.putBoolean("botonBloqueado8", true);
                        editor8.apply();

                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarParto2Reporte(claveGenerada,HoraNacimiento,Lugar,PlacentaExpulsada,Producto,Sexo,_1Min,_5Min,_10Min,SILVERMANN1,SILVERMANN2);
                        if (correcto) {
                            Intent intent = new Intent(PartoReporte2.this, MainReporte.class);
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
                            editor7.putBoolean("botonBloqueado7", true);
                            editor7.apply();

                            intent.putExtra("id", id);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(PartoReporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PartoReporte2.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(PartoReporte2.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
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
}