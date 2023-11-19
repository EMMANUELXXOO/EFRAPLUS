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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Cruzroja.frapplus.DBSQLITE.DBFRAPOrdenControl;
import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AgregarMaterialMedico extends AppCompatActivity {
    TextView viewstado,viewClave,viewfechamodif;
    private FloatingActionButton guardarButton,btnregresar;

    Spinner SpinnerMaterial,SpinnerCantidad;

    FRAPOrden frapOrden;
    int id;
    boolean correcto = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_material_medico);

        // Obtén una instancia del Vibrator
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
//////////////////////////////////////////////////Spiners
///SpinnerMaterial,SpinnerCantidad

        SpinnerMaterial = findViewById(R.id.SpinnerMaterial);
        String[] SpinnerMaterial1 = {"",
                "AGUJA INTRAOSEA EZ-IO AMARILLA",
                "AGUJA INTRAOSEA EZ-IO AZUL",
                "AGUJA INTRAOSEA EZ-IO ROSA",
                "APOSITO NO ESTERIL",
                "BVM ADULTO",
                "BVM NEONATAL",
                "BVM PEDIATRICO",
                "CANULA DE GUEDEL 50 MM(0#)",
                "CANULA DE GUEDEL 60 MM(#1)",
                "CANULA DE GUEDEL 70 MM(#2)",
                "CANULA DE GUEDEL 80 MM(#3)",
                "CANULA DE GUEDEL 90 MM(#4)",
                "CANULA DE GUEDEL 100 MM(#5)",
                "CANULA DE GUEDEL 110 MM(#6)",
                "CANULA DE GUEDEL 120 MM(#7)",
                "CANULA NASOFARINGEA #14 FR",
                "CANULA NASOFARINGEA #16 FR",
                "CANULA NASOFARINGEA #18 FR",
                "CANULA NASOFARINGEA #20 FR",
                "CANULA NASOFARINGEA #22 FR",
                "CANULA NASOFARINGEA #24 FR",
                "CANULA NASOFARINGEA #26 FR",
                "CANULA NASOFARINGEA #28 FR",
                "CANULA NASOFARINGEA #30 FR",
                "CANULA NASOFARINGEA #32 FR",
                "CANULA NASOFARINGEA #34 FR",
                "CANULA RIGIDA TANKAUER",
                "CATETER INTRAOSEO MANUAL ADULTO 15GA",
                "CATETER INTRAOSEO MANUAL PEDIATRICO 18",
                "CATETER PERIFERICO #14",
                "CATETER PERIFERICO #16",
                "CATETER PERIFERICO #18",
                "CATETER PERIFERICO #20",
                "CATETER PERIFERICO #22",
                "CATETER PERIFERICO #24",
                "CINTA ADHESIVA",
                "COLLARIN MULTIMEDIDA ADULTO",
                "COLLARIN MULTIMEDIDA PEDIATRICO",
                "CUBREBOCAS",
                "ELECTRODOS",
                "EQUIPO DE PARTO",
                "FILTRO VIRAL",
                "GASAS NO ESTERILES",
                "GUANTE CHICO",
                "GUANTE GRANDE",
                "GUANTE MEDIANO",
                "GUA ELASTICA DE INTUBACION - BOUGIE",
                "GUIA PARA TUBO ET #14",
                "JERINGA #5 CC",
                "JERINGA #10 CC",
                "JERINGA #20 CC",
                "JERINGA DE INSULINA #1 CC",
                "LANCETAS",
                "MASCARILLA LARINGEA #1",
                "MASCARILLA LARINGEA #1.5",
                "MASCARILLA LARINGEA #2",
                "MASCARILLA LARINGEA #3",
                "MASCARILLA LARINGEA #4",
                "MASCARILLA LARINGEA #5",
                "MANGERA PARA SUCCION",
                "MASCARILLA N-95",
                "MASCARILLA RESERVORIO ADULTO",
                "MASCARILLA RESERVORIO PEDIATRICA",
                "MICROGOTERO",
                "MICRONEBULIZADOR",
                "NORMOGOTERO",
                "PUNTAS NASALES ADULTO",
                "PUNTAS NASALES PEDIATRICAS",
                "ROLLO DE PAPEL PARA MONITOR",
                "SABANA TERMICA",
                "SOLUCION DEXTROSA 10% 500 ml",
                "SOLUCION DEXTROSA 50% 50 ml",
                "SOLUCION FISIOLOGICA 250 ml",
                "SOLUCION FISIOLOGICA 500 ml",
                "SOLUCION GLUCOSADA 5% 250 ml",
                "SOLUCION HARTMAN 500 ml",
                "SONDA DE ASPIRACION BLANDA 14 FR",
                "SUJETADOR PARA TUBO ET ADULTO",
                "SUJETADOR PARA TUBO ET PEDIATRICO",
                "TIRAS REACTIVAS",
                "TUBO ENDOTRAQUEAL #2.5 MM",
                "TUBO ENDOTRAQUEAL #3 MM",
                "TUBO ENDOTRAQUEAL #3.5 MM",
                "TUBO ENDOTRAQUEAL #4 MM",
                "TUBO ENDOTRAQUEAL #4.5 MM",
                "TUBO ENDOTRAQUEAL #5 MM",
                "TUBO ENDOTRAQUEAL #5.5 MM",
                "TUBO ENDOTRAQUEAL #6 MM",
                "TUBO ENDOTRAQUEAL #6.5 MM",
                "TUBO ENDOTRAQUEAL #7 MM",
                "TUBO ENDOTRAQUEAL #7.5 MM",
                "TUBO ENDOTRAQUEAL #8 MM",
                "TUBO ENDOTRAQUEAL #8.5 MM",
                "TUBO ENDOTRAQUEAL #9 MM",
                "TUBO ENDOTRAQUEAL #9.5 MM",
                "TUBO ENDOTRAQUEAL #10 MM",
                "VENDA ELASTICA 5 CM",
                "VENDA ELASTICA 10 CM",
                "ISODINE ESPUMA",
                "TORUNDAS",
                "JABON QX"};

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinnerMaterialAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinnerMaterial1);
        SpinnerMaterialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerMaterial.setAdapter(SpinnerMaterialAdapter);



        SpinnerCantidad = findViewById(R.id.SpinnerCantidad);

        String[] SpinnerCantidad1 = new String[50]; // Crear un arreglo de 100 elementos

        for (int i = 0; i < 50; i++) {
            SpinnerCantidad1[i] = String.valueOf(i + 1); // Agregar el número como cadena
        }

        // Adaptadores para los spinners
        ArrayAdapter<String> SpinnerCantidadAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinnerCantidad1);
        SpinnerCantidadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerCantidad.setAdapter(SpinnerCantidadAdapter);
////////////////////////////////////




        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }
        String clave;
        final DBFRAPOrdenControl dbfrapOrdenControl = new DBFRAPOrdenControl(AgregarMaterialMedico.this);
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
        }

        //Metodo para insertar y editar informacion
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Obtener referencias a los EditText y Spinners
                // Obtener los valores de los EditText que contienen las horas
                String CantidadMedico = SpinnerCantidad.getSelectedItem().toString();
                String MaterialMedico = SpinnerMaterial.getSelectedItem().toString();
                String claveGenerada = frapOrden.getClave();;
//              Realizar validaciones
                if (MaterialMedico.isEmpty()) {
                    // Mostrar un mensaje de error o notificación al usuario
                    Toast.makeText(AgregarMaterialMedico.this, "Por favor, complete todos los campos ", Toast.LENGTH_SHORT).show();
                } else {
                    // Obtener el valor de viewDia
                    DBFRAPOrdenControl dbfrapOrden1 = new DBFRAPOrdenControl(AgregarMaterialMedico.this);
                    long ID = dbfrapOrden1.insertarMaterialMedicO(claveGenerada,MaterialMedico,CantidadMedico);
                    if (ID > 0) {
                        Toast.makeText(AgregarMaterialMedico.this, "Dato Guardado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AgregarMaterialMedico.this, AgregarMaterialMedico.class);
                        intent.putExtra("id", id);


                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                        // Realiza alguna acción con los datos seleccionados, por ejemplo, mostrar un mensaje
                        //String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                        // Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        correcto = dbfrapOrden1.editarMaterialMedico(claveGenerada,MaterialMedico,CantidadMedico);
                        if (correcto) {
                            Intent intent = new Intent(AgregarMaterialMedico.this, AgregarMaterialMedico.class);
                            intent.putExtra("id", id);

                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                            // String mensaje = "Clave: " + claveGenerada +"Estado: " + selectedEstado + "\nDelegación: " + selectedDelegacion + "\nAsignación: " + selectedAsignacion;
                            //Toast.makeText(ServicioReporte.this, mensaje, Toast.LENGTH_SHORT).show();
                            Toast.makeText(AgregarMaterialMedico.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AgregarMaterialMedico.this, "Error en la modificación", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(AgregarMaterialMedico.this, "Datos Modificados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén una instancia del Vibrator
                Intent intent = new Intent(AgregarMaterialMedico.this, ConsumosReporte.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });

    }
}