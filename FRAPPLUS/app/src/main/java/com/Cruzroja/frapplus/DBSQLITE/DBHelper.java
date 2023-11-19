package com.Cruzroja.frapplus.DBSQLITE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATA_BASEVERSION = 20;
    private static final String DATABASE_NOMBRE = "FRAPPLUS.db";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATA_BASEVERSION);
        Log.d("DBHelper", "Database created: " + DATABASE_NOMBRE);

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String TABLE_FRAP = "FRAP";

    //TABLAS DE LA APLICACION
    public static final String TABLE_FrapOrden = "FRAPOrden";
    public static final String TABLE_DatosDelegacion = "DatosDelegacion";
    public static final String TABLE_DatosServicio = "DatosServicio";
    public static final String TABLE_DatosControl = "DatosControl";
    public static final String TABLE_DatosPaciente = "DatosPaciente";

    public static final String TABLE_DatosCausaTx = "DatosCausaTx";
    public static final String TABLE_DatosCausaClinica = "DatosCausaClinica";

    public static final String TABLE_DatosParto = "DatosParto";

    public static final String TABLE_EvaluacionI = "EvaluacionI";

    public static final String TABLE_EvaluacionII = "EvaluacionII";

    public static final String TABLE_Tratamiento = "Tratamiento";

    public static final String TABLE_Traslado = "Traslado";

    public static final String TABLE_Observaciones = "Observaciones";

    public static final String TABLE_MinisterioPublico = "MinisterioPublico";

    public static final String TABLE_DatosLegales = "DatosLegales";

    public static final String TABLE_HospitalReceptor = "HospitalReceptor";

    public static final String TABLE_MaterialMedico = "MaterialMedico";

    public static final String TABLE_MedicamentosConsumos = "MedicamentosConsumos";

    public static final String TABLE_FRAPCANCELADO = "FrapCancelado";

    public static final String TABLE_DatosServicioCancelado = "DatosServicioCancelado";
    public static final String TABLE_CONTROLCANCELADO = "DatosControlCancelado";

    //se crea al momento de mandar la base de datos
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_FRAP+"("+

                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Status TEXT NOT NULL)");

        //TABLA Ordenes Principal
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_FrapOrden+"("+

                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Clave TEXT NOT NULL, " +
                "Status TEXT NOT NULL," +
                "FechaCreacion TEXT NOT NULL," +
                "FechadeMoficacion TEXT)");
        //TABLA Datos Delegacion
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_DatosDelegacion+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "Estado TEXT ," +
                "Delegacion TEXT ," +
                "Asignacion TEXT )");
        //TABLA Datos Servicio
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_DatosServicio+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "Fecha TEXT ," +
                "Dia TEXT ," +
                "HoraLlamada TEXT ," +
                "HoraSalida TEXT," +
                "HoraLlegada TEXT," +
                "HoraTraslado TEXT ," +
                "HoraHospital TEXT ," +
                "HoraDisponible TEXT ," +
                "MotivoDeAtencion TEXT ," +
                "SubMotivo TEXT ," +
                "CalleServicio TEXT ," +
                "Calle1 TEXT ," +
                "Calle2 TEXT ," +
                "Colonia TEXT," +
                "DelegacionMunicipio TEXT ," +
                "LugarOcurrencia TEXT )");
        //TABLA Datos Control
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_DatosControl+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "NoAmbulancia TEXT," +
                "Operador TEXT," +
                "PrestadoresServicio TEXT ," +
                "MatriculaHelicoptero TEXT )");
        //TABLA Datos Paciente
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_DatosPaciente+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "NombreAfiliacion TEXT ," +
                "Sexo TEXT ," +
                "Edad TEXT ," +
                "Domicilio TEXT ," +
                "Colonia TEXT ," +
                "Municipio TEXT ," +
                "Telefono TEXT ," +
                "Ocupacion TEXT ," +
                "DerechoAmbiente TEXT )");
        //TABLA DatosDatosCausaTx
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_DatosCausaTx+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "AgenteCausal TEXT ," +
                "LesionesCausadas TEXT ," +
                "AccidentesAuto TEXT ," +
                "Medio TEXT ," +
                "ContraObjeto TEXT ," +
                "Impacto TEXT ," +
                "Hundimiento TEXT ," +
                "Parabrisas TEXT ," +
                "Volante TEXT ," +
                "BolsadeAire TEXT ," +
                "CinturodeSeg TEXT ," +
                "DentroVehi TEXT ," +
                "Atropellado TEXT )");

        //TABLA DatosCausaClinica
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_DatosCausaClinica+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "OrigenProbable TEXT ," +
                "primeravez TEXT ," +
                "SubSecuente TEXT )");

        //TABLA DatosParto
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_DatosParto+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "Gesta TEXT ," +
                "Cesareas TEXT ," +
                "Parto TEXT ," +
                "Abortos TEXT ," +
                "SemanasGestacion TEXT ," +
                "FechaPosibleParto TEXT ," +
                "Membrana TEXT ," +
                "HoraInicioContraccion TEXT ," +
                "Frecuencia TEXT ," +
                "Duracion TEXT ," +
                "HoraNacimiento TEXT ," +
                "Lugar TEXT ," +
                "PlacentaExpulsada TEXT ," +
                "Producto TEXT ," +
                "Sexo TEXT ," +
                "APGAR1Min TEXT ," +
                "APGAR5Min TEXT ," +
                "APGAR10Min TEXT ," +
                "SILVERMANN1 TEXT ," +
                "SILVERMANN2 TEXT )");


        //TABLA Evaluacion I
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_EvaluacionI+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "NivelConciencia TEXT ," +
                "ViaAerea TEXT ," +
                "ReflejoDeglusion TEXT ," +
                "Observacion TEXT ," +
                "Auscultacion TEXT ," +
                "VentHemitorax TEXT ," +
                "VentSitio TEXT ," +
                "CircuPresenciaPulsos TEXT ," +
                "CircuCalidad TEXT ," +
                "CircuPiel TEXT ," +
                "CircuCaracteristicas TEXT )");

        //TABLA EvaluacionII
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_EvaluacionII+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "LesionDiagrama TEXT ," +
                "PupilasDiagrama TEXT ," +
                "SVHora TEXT ," +
                "SVEKG TEXT ," +
                "SVFr TEXT ," +
                "SVFc TEXT ," +
                "SVTas TEXT ," +
                "SVTad TEXT ," +
                "SVSaO2 TEXT ," +
                "SVTemp TEXT ," +
                "SVGluc TEXT ," +
                "SVMiniexamenNeurologico TEXT ," +
                "Alergias TEXT ," +
                "MedicamentosIngeriendo TEXT ," +
                "EnfermedadesyCirugprevias TEXT ," +
                "HoraUltimaComida TEXT ," +
                "EventosPreviosRelacionados TEXT ," +
                "CondicionPaciente TEXT ," +
                "PrioridadPaciente TEXT ," +
                "TraumaScore TEXT ," +
                "Glasgow TEXT )");

        //TABLA Tratamiento
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_Tratamiento+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "ViaAerea TEXT ," +
                "ControlCervical TEXT ," +
                "ControlCervical2 TEXT ," +
                "Frec TEXT," +
                "Vol TEXT ," +
                "Oxigenoterapia TEXT ," +
                "LtsxMin TEXT ," +
                "Anexo1 TEXT ," +
                "Anexo2 TEXT ," +
                "Anexo3 TEXT ," +
                "ControlHemorragias TEXT ," +
                "ViasVenosas TEXT ," +
                "SitioAplicacion TEXT ," +
                "TiposSoluciones TEXT ," +
                "Cantml TEXT ," +
                "Infusiones TEXT," +
                "FarmacologiayTHora TEXT ," +
                "FarmacologiayTMedicamento TEXT ," +
                "FarmacologiayTDosis TEXT," +
                "FarmacologiayTVia TEXT ," +
                "FarmacologiayTT_E TEXT ," +
                "Procedimientos TEXT ," +
                "Procedimientos2 TEXT ," +
                "Procedimientos3 TEXT )");

        //TABLA Traslado
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_Traslado+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "IntitucionTraslado TEXT ," +
                "ClasificacionPaciente TEXT ," +
                "PrioridadPaciente TEXT ," +
                "NegativaArecibirAtencion TEXT ," +
                "FirmaPaciente TEXT ," +
                "NombrePaciente TEXT ," +
                "FirmaTestigo TEXT ," +
                "NombreTestigo TEXT )");


        //TABLA Observaciones
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_Observaciones+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "Observaciones TEXT )");

        //TABLA MinisterioPublico
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_MinisterioPublico+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "FirmaSelloQuienRecibe TEXT ," +
                "NombreQuienRecibe TEXT )");

        //TABLA DatosLegales
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_DatosLegales+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "Dependencia TEXT ," +
                "No_Unidades TEXT ," +
                "NombreoNoOficiales TEXT," +
                "Vehiculo1 TEXT ," +
                "Placa1 TEXT ," +
                "Vehiculo2 TEXT ," +
                "Placa2 TEXT ," +
                "Vehiculo3 TEXT ," +
                "Placa3 TEXT ," +
                "DescripcionPosicion TEXT ," +
                "Pertenencias TEXT ," +
                "FirmaRecibePertenencias TEXT ," +
                "NombreyCargo TEXT," +
                "SeguroAutomovil TEXT )");

        //TABLA HospitalReceptor
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_HospitalReceptor+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "FirmaEntegaPaciente TEXT ," +
                "NombreEntregaPaciente TEXT ," +
                "FirmaRecibePaciente TEXT ," +
                "NombreRecibePaciente TEXT )");
        //Material Medico
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_MaterialMedico+"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ClaveFRAPOrden TEXT  NOT NULL, " +
                "MaterialMedico TEXT ," +
                "CantidadMaterialMedico TEXT )");

        //Medicamentos
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_MedicamentosConsumos+"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ClaveFRAPOrden TEXT NOT NULL, " +
                "HoraCaptura TEXT ," +
                "Medicamento TEXT ," +
                "Dosis TEXT ," +
                "Via TEXT ," +
                "TE TEXT )");
        //FRAP CANCELADO
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_FRAPCANCELADO+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "StatusC TEXT ," +
                "DelegacionC TEXT ," +
                "AsignacionC TEXT ," +
                "MotivodeCancelacion TEXT )");

        //TABLA Datos Servicio
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_DatosServicioCancelado+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "Fecha TEXT ," +
                "Dia TEXT ," +
                "HoraLlamada TEXT ," +
                "HoraSalida TEXT," +
                "HoraLlegada TEXT," +
                "HoraTraslado TEXT ," +
                "HoraHospital TEXT ," +
                "HoraDisponible TEXT ," +
                "MotivoDeAtencion TEXT ," +
                "SubMotivo TEXT ," +
                "CalleServicio TEXT ," +
                "Calle1 TEXT ," +
                "Calle2 TEXT ," +
                "Colonia TEXT," +
                "DelegacionMunicipio TEXT)");

        //TABLA Datos Control CANCELADO
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_CONTROLCANCELADO+"("+
                "ClaveFRAPOrden TEXT PRIMARY KEY NOT NULL, " +
                "NoAmbulancia TEXT," +
                "Operador TEXT," +
                "PrestadoresServicio TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("DBHelper", "onUpgrade called: " + i + " to " + i1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FRAP);
        //1
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FrapOrden);
        //2
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DatosDelegacion);
        //3
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DatosServicio);
        //4
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DatosControl);
        //5
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DatosPaciente);
        //6
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DatosCausaTx);
        //7
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DatosCausaClinica);
        //8
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DatosParto);
        //9
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EvaluacionI);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EvaluacionII);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Tratamiento);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Traslado);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Observaciones);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MinisterioPublico);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DatosLegales);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HospitalReceptor);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MaterialMedico);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MedicamentosConsumos);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FRAPCANCELADO);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DatosServicioCancelado);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTROLCANCELADO);

        onCreate(sqLiteDatabase);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////
}
