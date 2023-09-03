package com.Cruzroja.frapplus.Vistas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.DatosDelegacion;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainReporte extends AppCompatActivity {

    TextView viewstado,viewClave,viewfechamodif;
    FloatingActionButton btnguardar,btnregresar,btnCancelado;
    Button Button1,Button2,Button3,Button4,Button5,Button6,Button7,Button8,Button9,Button10,Button11,Button12,Button13
            ,Button14,Button15,Button16;
    FRAPOrden frapOrden;
    DatosDelegacion datosDelegacion;
    int id;
    int Bandera = 0;
    private  int miVariableGlobal = 0;

    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reporte);


        viewstado = findViewById(R.id.viewestado);
        viewClave = findViewById(R.id.viewclave);
        viewfechamodif = findViewById(R.id.viewFechamodif);
        btnregresar = findViewById(R.id.fabRegresar);
        btnCancelado = findViewById(R.id.FabCancelado);
        btnguardar = findViewById(R.id.fabGuardar);


        // Obtén una instancia del Vibrator
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Verifica si el dispositivo admite la vibración
        if (vibrator != null && vibrator.hasVibrator()) {
            // Realiza una vibración corta al hacer clic en el botón
            vibrator.vibrate(100); // 100 milisegundos de vibración
        }

        String Status = "COMPLETADO";
        String StatusC = "CANCELADO";
        Button1 = findViewById(R.id.button1);


        Button2 = findViewById(R.id.button2);
        Button3 = findViewById(R.id.button3);
        Button4 = findViewById(R.id.button4);
        Button5 = findViewById(R.id.button5);
        Button6 = findViewById(R.id.button6);
        Button7 = findViewById(R.id.button7);
        Button8 = findViewById(R.id.button8);
        Button9 = findViewById(R.id.button9);
        Button10 = findViewById(R.id.button10);
        Button11 = findViewById(R.id.button11);
        Button12 = findViewById(R.id.button12);
        Button13 = findViewById(R.id.button13);
        Button14 = findViewById(R.id.button14);
        Button15 = findViewById(R.id.button15);
        Button16 = findViewById(R.id.button16);

        // Obtiene el valor de 'id' de la intención.

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(MainReporte.this);
        frapOrden = dbfrapOrdenControl.verFRAPCONTROL(id);

        if (frapOrden != null) {
            viewstado.setText(String.valueOf(frapOrden.getStatus()));
            if (viewstado.getText().toString().equals("CANCELADO")) {
                // El texto en viewStatus1 es igual a "CANCELADO"
                // Realiza las acciones necesarias aquí
                viewstado.setTextColor(getResources().getColor(R.color.red));
            }
            if (viewstado.getText().toString().equals("COMPLETADO")) {
                // El texto en viewStatus1 es igual a "CANCELADO"
                // Realiza las acciones necesarias aquí
                viewstado.setTextColor(getResources().getColor(R.color.green));
            }

            if (viewstado.getText().toString().equals("PENDIENTE")) {
                // El texto en viewStatus1 es igual a "CANCELADO"
                // Realiza las acciones necesarias aquí
                viewstado.setTextColor(getResources().getColor(R.color.colorPrimary));
            }

            viewClave.setText(frapOrden.getClave());
            viewfechamodif.setText(frapOrden.getFechadeMoficacion());
        } else {

            Log.d("VerInformeFrap", "Valor de id: " + id);

            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista();
            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainReporte.this);
                builder.setMessage("¿Seguro Que Quieres Marcar COMPLETADO El Reporte? (No se podran Realizar Cambios)")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                miVariableGlobal=1;
                                if (miVariableGlobal==1){

                                    correcto = dbfrapOrdenControl.editarfrapOrdenEstado(id,Status);

                                    if (correcto) {
                                        // Toast.makeText(VerInformeFrap.this, "Modificación exitosa", Toast.LENGTH_SHORT).show();
                                        verRegistro();
                                    } else {
                                        Toast.makeText(MainReporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                                // Verifica si el dispositivo admite la vibración
                                if (vibrator != null && vibrator.hasVibrator()) {
                                    // Realiza una vibración corta al hacer clic en el botón
                                    vibrator.vibrate(100); // 100 milisegundos de vibración
                                }
                            }
                        }).show();




            }
        });

        btnCancelado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainReporte.this);
                builder.setMessage("¿Seguro Que Quieres Marcar CANCELADO El Reporte? (No se podran Realizar Cambios)")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                miVariableGlobal=1;
                                if (miVariableGlobal==1){

                                    correcto = dbfrapOrdenControl.editarfrapOrdenEstado(id,StatusC);

                                    if (correcto) {
                                        // Toast.makeText(VerInformeFrap.this, "Modificación exitosa", Toast.LENGTH_SHORT).show();
                                        miVariableGlobal = 1;
                                        Intent intent = new Intent(MainReporte.this, CanceladoActivity.class);
                                        intent.putExtra("id", id);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                                    } else {
                                        Toast.makeText(MainReporte.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                                // Verifica si el dispositivo admite la vibración
                                if (vibrator != null && vibrator.hasVibrator()) {
                                    // Realiza una vibración corta al hacer clic en el botón
                                    vibrator.vibrate(100); // 100 milisegundos de vibración
                                }
                            }
                        }).show();





            }
        });

        //Actividad Delegacion
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, DelegacionReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });
        //Actividad Servicio
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, ServicioReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Control
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, ControlReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Paciente
        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, PacienteReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Causa TX
        Button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, CausaTxReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Causa Clinica
        Button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, CausaClinicaReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Parto
        Button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, PartoReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Evaluacion I
        Button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, EvaluacionIReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Evaluacion II
        Button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, Evaluacion2Reporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Tratamiento
        Button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, TratamientoReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Traslado
        Button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, TrasladoReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Observaciones
        Button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, ObservacionesReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Ministerio Publico
        Button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, MinisterioPublicoReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Datos Legales
        Button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, DatosLegalesReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Hospital Receptor
        Button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, HospitalReceptorReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

        //Actividad Consumos
        Button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainReporte.this, ConsumosReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });


    }

    private void lista(){
        Intent intent = new Intent(this, InicioFrap.class);
        startActivity(intent);
        intent.putExtra("id", id);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        finish();
    }
    private void verRegistro(){
        Intent intent = new Intent(this, InicioFrap.class);
        intent.putExtra("id", id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}