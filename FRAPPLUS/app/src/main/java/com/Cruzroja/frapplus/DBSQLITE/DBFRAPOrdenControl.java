package com.Cruzroja.frapplus.DBSQLITE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.Cruzroja.frapplus.entidades.FRAP;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.Cruzroja.frapplus.entidades.MaterialMedico;
import com.Cruzroja.frapplus.entidades.MedicamentosConsumos;

import java.util.ArrayList;
import java.util.List;


public class DBFRAPOrdenControl extends DBHelper {

        Context context;

        //Constructor de la clase
        public DBFRAPOrdenControl(@Nullable Context context) {
            super(context);
            this.context = context;
        }

        public long insertaFrapOrden(String clave, String estado, String fechaC,String fechamod) {
            long ID = 0;
            try {

                //Lllamamos a dbhelper
                DBHelper dbHelper = new DBHelper(context);
                //Lllamamos a sqlite
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                //Agregar la funcion para insertar registro
                ContentValues values = new ContentValues();

                values.put("Clave", clave);
                values.put("Status", estado);
                values.put("FechaCreacion", fechaC);
                values.put("FechadeMoficacion",fechamod);


                ID = db.insert(TABLE_FrapOrden, null, values);

            } catch (Exception e) {
                Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
                e.toString();
            }
            return ID;
        }

//Arreglo para lista mostrar
    public ArrayList<FRAPOrden> verFrapOrden() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<FRAPOrden> listaFrapOrden = new ArrayList<>();
        FRAPOrden frapOrden;
        Cursor cursorFrapOrden;
        cursorFrapOrden = db.rawQuery("SELECT * FROM " + TABLE_FrapOrden + " ORDER BY id DESC", null);

        if (cursorFrapOrden.moveToFirst()) {
            do {
                frapOrden = new FRAPOrden();
                frapOrden.setId(cursorFrapOrden.getInt(0));
                frapOrden.setClave(cursorFrapOrden.getString(1));
                frapOrden.setStatus(cursorFrapOrden.getString(2));
                frapOrden.setFechaCreacion(cursorFrapOrden.getString(3));
                frapOrden.setFechadeMoficacion(cursorFrapOrden.getString(4));
                listaFrapOrden.add(frapOrden);
            } while (cursorFrapOrden.moveToNext());
        }
        cursorFrapOrden.close();

        return listaFrapOrden;
    }

    public FRAPOrden verFRAPCONTROL(int id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        FRAPOrden frapOrden = null;
        Cursor cursorFRAP;


        cursorFRAP = db.rawQuery("SELECT * FROM " + TABLE_FrapOrden + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorFRAP.moveToFirst()) {
            frapOrden = new FRAPOrden();
            frapOrden.setId(cursorFRAP.getInt(0));
            frapOrden.setClave(cursorFRAP.getString(1));
            frapOrden.setStatus(cursorFRAP.getString(2));
            frapOrden.setFechaCreacion(cursorFRAP.getString(3));
            frapOrden.setFechadeMoficacion(cursorFRAP.getString(4));
        }

        cursorFRAP.close();


        return frapOrden;
    }

    public boolean editarfrapOrdenEstado(int id, String Status) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_FrapOrden + " SET Status = '" + Status + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        db.close(); // Importante cerrar la base de datos cuando hayas terminado.

        return correcto;
    }
    public boolean editarfrapOrdenFechaMod(int id, String FechadeMoficacion) {

            boolean correcto = false;

            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            try {
                db.execSQL("UPDATE " + TABLE_FrapOrden + " SET FechadeMoficacion = '" + FechadeMoficacion + "' WHERE id='" + id + "' ");
                correcto = true;
            } catch (Exception ex) {
                ex.toString();
                correcto = false;
            } finally {
                db.close();
            }

        db.close(); // Importante cerrar la base de datos cuando hayas terminado.

        return correcto;
        }

        public boolean eliminarFrapOrden(int id) {

            boolean correcto = false;

            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            try {
                db.execSQL("DELETE FROM " + TABLE_FrapOrden + " WHERE id = '" + id + "'");
                correcto = true;
            } catch (Exception ex) {
                ex.toString();
                correcto = false;
            } finally {
                db.close();
            }

            return correcto;
        }

        //Delegacion
    //Insertar datos primera vez
        public long insertaDelegacionReporte(String ClaveFRAPOrden, String Estado, String Delegacion,String Asignacion) {
            long ID = 0;
            try {

                //Lllamamos a dbhelper
                DBHelper dbHelper = new DBHelper(context);
                //Lllamamos a sqlite
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                //Agregar la funcion para insertar registro
                ContentValues values = new ContentValues();

                values.put("ClaveFRAPOrden", ClaveFRAPOrden);
                values.put("Estado", Estado);
                values.put("Delegacion", Delegacion);
                values.put("Asignacion",Asignacion);


                ID = db.insert(TABLE_DatosDelegacion, null, values);

            } catch (Exception e) {
                Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
                e.toString();
            }
            return ID;
        }
//Editar
public boolean editarDelegacionReporte(String ClaveFRAPOrden, String Estado, String Delegacion, String Asignacion) {

    boolean correcto = false;

    DBHelper dbHelper = new DBHelper(context);
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    try {
        // Corrige las columnas para usar las variables pasadas como argumentos
        db.execSQL("UPDATE " + TABLE_DatosDelegacion +
                " SET Estado = '" + Estado + "', Delegacion = '" + Delegacion + "', Asignacion = '" + Asignacion + "'" +
                " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");

        correcto = true;
    } catch (Exception ex) {
        ex.toString();
        correcto = false;
    } finally {
        db.close();
    }

    return correcto;
    }

    //Servicio(Hoja Principal NO ANEXO
    //Insertar datos primera vez
    public long insertaServicio1Reporte(String ClaveFRAPOrden, String Fecha
            , String Dia, String HoraLlamada, String HoraSalida, String HoraLlegada
            , String HoraTraslado,String HoraHospital
            ,String HoraDisponible, String MotivoDeAtencion,String SubMotivo) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("Fecha", Fecha);
            values.put("Dia", Dia);
            values.put("HoraLlamada",HoraLlamada);
            values.put("HoraSalida", HoraSalida);
            values.put("HoraLlegada", HoraLlegada);
            values.put("HoraTraslado",HoraTraslado);
            values.put("HoraHospital", HoraHospital);
            values.put("HoraDisponible", HoraDisponible);
            values.put("MotivoDeAtencion",MotivoDeAtencion);
            values.put("SubMotivo", SubMotivo);

            ID = db.insert(TABLE_DatosServicio, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarServicio1Reporte(String ClaveFRAPOrden, String Fecha
            , String Dia, String HoraLlamada, String HoraSalida, String HoraLlegada
            , String HoraTraslado,String HoraHospital
            ,String HoraDisponible, String MotivoDeAtencion,String SubMotivo) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosServicio +
                    " SET Fecha = '" + Fecha + "'" +
                    ", Dia = '" + Dia + "'" +
                    ", HoraLlamada = '" + HoraLlamada + "'" +
                    ", HoraSalida = '" + HoraSalida + "'" +
                    ", HoraLlegada = '" + HoraLlegada + "'" +
                    ", HoraTraslado = '" + HoraTraslado + "'" +
                    ", HoraHospital = '" + HoraHospital + "'" +
                    ", HoraDisponible = '" + HoraDisponible + "'" +
                    ", MotivoDeAtencion = '" + MotivoDeAtencion + "'" +
                    ", SubMotivo = '" + SubMotivo + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Servicio(Hoja Principal ANEXO(2)
    //Insertar datos primera vez
    public long insertaServicio2Reporte(String ClaveFRAPOrden, String CalleServicio
            , String Calle1, String Calle2, String Colonia, String DelegacionMunicipio
            ,String LugarOcurrencia) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("CalleServicio", CalleServicio);
            values.put("Calle1", Calle1);
            values.put("Calle2",Calle2);
            values.put("Colonia", Colonia);
            values.put("DelegacionMunicipio", DelegacionMunicipio);
            values.put("LugarOcurrencia",LugarOcurrencia);

            ID = db.insert(TABLE_DatosServicio, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarServicio2Reporte(String ClaveFRAPOrden, String CalleServicio
            , String Calle1, String Calle2, String Colonia, String DelegacionMunicipio
            ,String LugarOcurrencia) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosServicio +
                    " SET CalleServicio = '" + CalleServicio + "'" +
                    ", Calle1 = '" + Calle1 + "'" +
                    ", Calle2 = '" + Calle2 + "'" +
                    ", Colonia = '" + Colonia + "'" +
                    ", DelegacionMunicipio = '" + DelegacionMunicipio + "'" +
                    ", LugarOcurrencia = '" + LugarOcurrencia + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
    //Control(Hoja Principal
    //Insertar datos primera vez
    public long insertaControlReporte(String ClaveFRAPOrden, String NoAmbulancia
            , String Operador, String PrestadoresServicio, String MatriculaHelicoptero) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("NoAmbulancia", NoAmbulancia);
            values.put("Operador", Operador);
            values.put("PrestadoresServicio",PrestadoresServicio);
            values.put("MatriculaHelicoptero", MatriculaHelicoptero);


            ID = db.insert(TABLE_DatosControl, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarControlReporte(String ClaveFRAPOrden, String NoAmbulancia
            , String Operador, String PrestadoresServicio, String MatriculaHelicoptero) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosServicio +
                    " SET NoAmbulancia = '" + NoAmbulancia + "'" +
                    ", Operador = '" + Operador + "'" +
                    ", PrestadoresServicio = '" + PrestadoresServicio + "'" +
                    ", MatriculaHelicoptero = '" + MatriculaHelicoptero + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Paciente(Hoja Principal)
    //Insertar datos primera vez
    public long insertaPacienteReporte(String ClaveFRAPOrden
            , String NombreAfiliacion
            ,String Sexo
            ,String Edad
            ,String Domicilio
            ,String Colonia
            ,String Municipio
            ,String Telefono
            ,String Ocupacion
            ,String DerechoAmbiente) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("NombreAfiliacion", NombreAfiliacion);
            values.put("Sexo", Sexo);
            values.put("Edad", Edad);
            values.put("Domicilio", Domicilio);
            values.put("Colonia", Colonia);
            values.put("Municipio", Municipio);
            values.put("Telefono", Telefono);
            values.put("Ocupacion", Ocupacion);
            values.put("DerechoAmbiente", DerechoAmbiente);

            ID = db.insert(TABLE_DatosPaciente, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarPacienteReporte(String ClaveFRAPOrden
            , String NombreAfiliacion
            ,String Sexo
            ,String Edad
            ,String Domicilio
            ,String Colonia
            ,String Municipio
            ,String Telefono
            ,String Ocupacion
            ,String DerechoAmbiente) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosPaciente +
                    " SET NombreAfiliacion = '" + NombreAfiliacion + "'" +
                    ", Sexo = '" + Sexo + "'" +
                    ", Edad = '" + Edad + "'" +
                    ", Domicilio = '" + Domicilio + "'" +
                    ", Colonia = '" + Colonia + "'" +
                    ", Municipio = '" + Municipio + "'" +
                    ", Telefono = '" + Telefono + "'" +
                    ", Ocupacion = '" + Ocupacion + "'" +
                    ", DerechoAmbiente = '" + DerechoAmbiente + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }


    //CausaTX(Hoja Principal)
    //Insertar datos primera vez
    public long insertaCausaTXReporte(String ClaveFRAPOrden
            , String AgenteCausal
            ,String LesionesCausadas) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("AgenteCausal", AgenteCausal);
            values.put("LesionesCausadas", LesionesCausadas);



            ID = db.insert(TABLE_DatosCausaTx, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarCausaTXReporte(String ClaveFRAPOrden
            , String AgenteCausal
            ,String LesionesCausadas) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosCausaTx +
                    " SET AgenteCausal = '" + AgenteCausal + "'" +
                    ", LesionesCausadas = '" + LesionesCausadas + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //CausaTX(Hoja 2) ANEXO 1
    //Insertar datos primera vez
    public long insertaCausaTX2Reporte(String ClaveFRAPOrden
            , String AccidentesAuto
            ,String Medio,String ContraObjeto,String Impacto) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("AccidentesAuto", AccidentesAuto);
            values.put("Medio", Medio);
            values.put("ContraObjeto", ContraObjeto);
            values.put("Impacto", Impacto);

            ID = db.insert(TABLE_DatosCausaTx, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarCausaTX2Reporte(String ClaveFRAPOrden
            , String AccidentesAuto
            ,String Medio,String ContraObjeto,String Impacto) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosCausaTx +
                    " SET AccidentesAuto = '" + AccidentesAuto + "'" +
                    ", Medio = '" + Medio + "'" +
                    ", ContraObjeto = '" + ContraObjeto + "'" +
                    ", Impacto = '" + Impacto + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }


    //CausaTX(Hoja 3) ANEXO II
    //Insertar datos primera vez
    public long insertaCausaTX3Reporte(String ClaveFRAPOrden,String Hundimiento
            ,String Parabrisas,String Volante,String BolsadeAire,String CinturodeSeg
            ,String DentroVehi,String Atropellado) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("Hundimiento", Hundimiento);
            values.put("Parabrisas", Parabrisas);
            values.put("Volante", Volante);
            values.put("BolsadeAire", BolsadeAire);
            values.put("CinturodeSeg", CinturodeSeg);
            values.put("DentroVehi", DentroVehi);
            values.put("Atropellado", Atropellado);
            ID = db.insert(TABLE_DatosCausaTx, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarCausaTX3Reporte(String ClaveFRAPOrden,String Hundimiento
            ,String Parabrisas,String Volante,String BolsadeAire,String CinturodeSeg
            ,String DentroVehi,String Atropellado) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosCausaTx +
                    " SET Hundimiento = '" + Hundimiento + "'" +
                    ", Parabrisas = '" + Parabrisas + "'" +
                    ", Volante = '" + Volante + "'" +
                    ", BolsadeAire = '" + BolsadeAire + "'" +
                    ", CinturodeSeg = '" + CinturodeSeg + "'" +
                    ", DentroVehi = '" + DentroVehi + "'" +
                    ", Atropellado = '" + Atropellado + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }


    //Causa Clinica(Hoja Principal
    //Insertar datos primera vez
    public long insertaCausaClinicaReporte(String ClaveFRAPOrden
            , String OrigenProbable,String primeravez,String SubSecuente) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("OrigenProbable", OrigenProbable);
            values.put("primeravez", primeravez);
            values.put("SubSecuente", SubSecuente);


            ID = db.insert(TABLE_DatosCausaClinica, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarCausaClinicaReporte(String ClaveFRAPOrden
            , String OrigenProbable,String primeravez,String SubSecuente) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosCausaClinica +
                    " SET OrigenProbable = '" + OrigenProbable + "'" +
                    ", primeravez = '" + primeravez + "'" +
                    ", SubSecuente = '" + SubSecuente + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }


    //Parto(Hoja Principal
    //Insertar datos primera vez
    public long insertaPartoReporte(String ClaveFRAPOrden
            , String Gesta,String Cesareas,String Parto,String Abortos,String SemanasGestacion
            ,String FechaPosibleParto,String Membrana,String HoraInicioContraccion,String Frecuencia,String Duracion) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("Gesta", Gesta);
            values.put("Cesareas", Cesareas);
            values.put("Parto", Parto);
            values.put("Abortos", Abortos);
            values.put("SemanasGestacion", SemanasGestacion);
            values.put("FechaPosibleParto", FechaPosibleParto);
            values.put("Membrana", Membrana);
            values.put("HoraInicioContraccion", HoraInicioContraccion);
            values.put("Frecuencia", Frecuencia);
            values.put("Duracion", Duracion);

            ID = db.insert(TABLE_DatosParto, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarPartoReporte(String ClaveFRAPOrden
            , String Gesta,String Cesareas,String Parto,String Abortos,String SemanasGestacion
            ,String FechaPosibleParto,String Membrana,String HoraInicioContraccion,String Frecuencia,String Duracion) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosParto +
                    " SET Gesta = '" + Gesta + "'" +
                    ", Cesareas = '" + Cesareas + "'" +
                    ", Parto = '" + Parto + "'" +
                    ", Abortos = '" + Abortos + "'" +
                    ", SemanasGestacion = '" + SemanasGestacion + "'" +
                    ", FechaPosibleParto = '" + FechaPosibleParto + "'" +
                    ", Membrana = '" + Membrana + "'" +
                    ", HoraInicioContraccion = '" + HoraInicioContraccion + "'" +
                    ", Frecuencia = '" + Frecuencia + "'" +
                    ", Duracion = '" + Duracion + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Parto2(Hoja 2 ANEXO I
    //Insertar datos primera vez
    public long insertaParto2Reporte(String ClaveFRAPOrden
            , String HoraNacimiento,String Lugar,String PlacentaExpulsada,String Producto,String Sexo
            ,String APGAR1Min,String APGAR5Min,String APGAR10Min,String SILVERMANN1,String SILVERMANN2) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("HoraNacimiento", HoraNacimiento);
            values.put("Lugar", Lugar);
            values.put("PlacentaExpulsada", PlacentaExpulsada);
            values.put("Producto", Producto);
            values.put("Sexo", Sexo);
            values.put("APGAR1Min", APGAR1Min);
            values.put("APGAR5Min", APGAR5Min);
            values.put("APGAR10Min", APGAR10Min);
            values.put("SILVERMANN1", SILVERMANN1);
            values.put("SILVERMANN2", SILVERMANN2);


            ID = db.insert(TABLE_DatosParto, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarParto2Reporte(String ClaveFRAPOrden
            , String HoraNacimiento,String Lugar,String PlacentaExpulsada,String Producto,String Sexo
            ,String APGAR1Min,String APGAR5Min,String APGAR10Min,String SILVERMANN1,String SILVERMANN2) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosParto +
                    " SET HoraNacimiento = '" + HoraNacimiento + "'" +
                    ", Lugar = '" + Lugar + "'" +
                    ", PlacentaExpulsada = '" + PlacentaExpulsada + "'" +
                    ", Producto = '" + Producto + "'" +
                    ", Sexo = '" + Sexo + "'" +
                    ", APGAR1Min = '" + APGAR1Min + "'" +
                    ", APGAR5Min = '" + APGAR5Min + "'" +
                    ", APGAR10Min = '" + APGAR10Min + "'" +
                    ", SILVERMANN1 = '" + SILVERMANN1 + "'" +
                    ", SILVERMANN2 = '" + SILVERMANN2 + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Evaluacion I(Hoja Principal)
    //Insertar datos primera vez
    public long insertaEvaluacionIReporte(String ClaveFRAPOrden
            , String NivelConciencia,String ViaAerea,String ReflejoDeglusion) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("NivelConciencia", NivelConciencia);
            values.put("ViaAerea", ViaAerea);
            values.put("ReflejoDeglusion", ReflejoDeglusion);



            ID = db.insert(TABLE_EvaluacionI, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarEvaluacionIReporte(String ClaveFRAPOrden
            , String NivelConciencia,String ViaAerea,String ReflejoDeglusion) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_EvaluacionI +
                    " SET NivelConciencia = '" + NivelConciencia + "'" +
                    ", ViaAerea = '" + ViaAerea + "'" +
                    ", ReflejoDeglusion = '" + ReflejoDeglusion + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
    //Evaluacion I(Hoja 2) ANEXO I
    //Insertar datos primera vez
    public long insertaEvaluacionIReporte2(String ClaveFRAPOrden
            , String Observacion,String Auscultacion) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("Observacion", Observacion);
            values.put("Auscultacion", Auscultacion);




            ID = db.insert(TABLE_EvaluacionI, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarEvaluacionIReporte2(String ClaveFRAPOrden
            , String Observacion,String Auscultacion) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_EvaluacionI +
                    " SET Observacion = '" + Observacion + "'" +
                    ", Auscultacion = '" + Auscultacion + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Evaluacion I(Hoja 3) ANEXO II
    //Insertar datos primera vez
    public long insertaEvaluacionIReporte3(String ClaveFRAPOrden
            , String VentHemitorax,String VentSitio,String CircuPresenciaPulsos
            ,String CircuCalidad) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("VentHemitorax", VentHemitorax);
            values.put("VentSitio", VentSitio);
            values.put("CircuPresenciaPulsos", CircuPresenciaPulsos);
            values.put("CircuCalidad", CircuCalidad);



            ID = db.insert(TABLE_EvaluacionI, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarEvaluacionIReporte3(String ClaveFRAPOrden
            , String VentHemitorax,String VentSitio,String CircuPresenciaPulsos
            ,String CircuCalidad) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_EvaluacionI +
                    " SET VentHemitorax = '" + VentHemitorax + "'" +
                    ", VentSitio = '" + VentSitio + "'" +
                    ", CircuPresenciaPulsos = '" + CircuPresenciaPulsos + "'" +
                    ", CircuCalidad = '" + CircuCalidad + "'" +

                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
    //Evaluacion I(Hoja 4) ANEXO III
    //Insertar datos primera vez
    public long insertaEvaluacionIReporte4(String ClaveFRAPOrden
            , String CircuPiel,String CircuCaracteristicas) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("CircuPiel", CircuPiel);
            values.put("CircuCaracteristicas", CircuCaracteristicas);




            ID = db.insert(TABLE_EvaluacionI, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarEvaluacionIReporte4(String ClaveFRAPOrden
            , String CircuPiel,String CircuCaracteristicas) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_EvaluacionI +
                    " SET CircuPiel = '" + CircuPiel + "'" +
                    ", CircuCaracteristicas = '" + CircuCaracteristicas + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
    //Evaluacion II
    public long insertaEvaluacion2IReporte(String ClaveFRAPOrden
            , String LesionDiagrama) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("LesionDiagrama", LesionDiagrama);




            ID = db.insert(TABLE_EvaluacionII, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarEvaluacion2Reporte(String ClaveFRAPOrden
            , String LesionDiagrama) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_EvaluacionII +
                    " SET LesionDiagrama = '" + LesionDiagrama + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
    //Evaluacion II(Anexo HOja 2 I)
    public long insertaEvaluacion2IReporte2(String ClaveFRAPOrden
            , String PupilasDiagrama) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("PupilasDiagrama", PupilasDiagrama);




            ID = db.insert(TABLE_EvaluacionII, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarEvaluacion2Reporte2(String ClaveFRAPOrden
            , String PupilasDiagrama) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_EvaluacionII +
                    " SET PupilasDiagrama = '" + PupilasDiagrama + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
    //Evaluacion II(Hoja 3 Anexo II)
    public long insertaEvaluacion2IReporte3(String ClaveFRAPOrden
            , String SVHora,String SVEKG,String SVFr
            ,String SVFc, String SVTas,String SVTad,String SVSaO2,String SVTemp,String SVGluc,String SVMiniexamenNeurologico) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("SVHora", SVHora);
            values.put("SVEKG", SVEKG);
            values.put("SVFr", SVFr);
            values.put("SVFc", SVFc);
            values.put("SVTas", SVTas);
            values.put("SVTad", SVTad);
            values.put("SVSaO2", SVSaO2);
            values.put("SVTemp", SVTemp);
            values.put("SVGluc", SVGluc);
            values.put("SVMiniexamenNeurologico", SVMiniexamenNeurologico);



            ID = db.insert(TABLE_EvaluacionII, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarEvaluacion2Reporte3(String ClaveFRAPOrden
            , String SVHora,String SVEKG,String SVFr
            ,String SVFc, String SVTas,String SVTad,String SVSaO2,String SVTemp,String SVGluc,String SVMiniexamenNeurologico) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_EvaluacionII +
                    " SET SVHora = '" + SVHora + "'" +
                    ", SVEKG = '" + SVEKG + "'" +
                    ", SVFr = '" + SVFr + "'" +
                    ", SVFc = '" + SVFc + "'" +
                    ", SVTas = '" + SVTas + "'" +
                    ", SVTad = '" + SVTad + "'" +
                    ", SVSaO2 = '" + SVSaO2 + "'" +
                    ", SVTemp = '" + SVTemp + "'" +
                    ", SVGluc = '" + SVGluc + "'" +
                    ", SVMiniexamenNeurologico = '" + SVMiniexamenNeurologico + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
    //Evaluacion II(Anexo HOJA 4 III)
    public long insertaEvaluacion2IReporte4(String ClaveFRAPOrden
            , String Alergias,String MedicamentosIngeriendo,String EnfermedadesyCirugprevias
            ,String HoraUltimaComida, String EventosPreviosRelacionados) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("Alergias", Alergias);
            values.put("MedicamentosIngeriendo", MedicamentosIngeriendo);
            values.put("EnfermedadesyCirugprevias", EnfermedadesyCirugprevias);
            values.put("HoraUltimaComida", HoraUltimaComida);
            values.put("EventosPreviosRelacionados", EventosPreviosRelacionados);

            ID = db.insert(TABLE_EvaluacionII, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarEvaluacion2Reporte4(String ClaveFRAPOrden
            , String Alergias,String MedicamentosIngeriendo,String EnfermedadesyCirugprevias
            ,String HoraUltimaComida, String EventosPreviosRelacionados) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_EvaluacionII +
                    " SET Alergias = '" + Alergias + "'" +
                    ", MedicamentosIngeriendo = '" + MedicamentosIngeriendo + "'" +
                    ", EnfermedadesyCirugprevias = '" + EnfermedadesyCirugprevias + "'" +
                    ", HoraUltimaComida = '" + HoraUltimaComida + "'" +
                    ", EventosPreviosRelacionados = '" + EventosPreviosRelacionados + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
    //Evaluacion II(Anexo  hOJA 5   IV)
    public long insertaEvaluacion2IReporte5(String ClaveFRAPOrden
            , String CondicionPaciente,String PrioridadPaciente,String TraumaScore
            ,String Glasgow) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("CondicionPaciente", CondicionPaciente);
            values.put("PrioridadPaciente", PrioridadPaciente);
            values.put("TraumaScore", TraumaScore);
            values.put("Glasgow", Glasgow);


            ID = db.insert(TABLE_EvaluacionII, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarEvaluacion2Reporte5(String ClaveFRAPOrden
            , String CondicionPaciente,String PrioridadPaciente,String TraumaScore
            ,String Glasgow) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_EvaluacionII +
                    " SET CondicionPaciente = '" + CondicionPaciente + "'" +
                    ", PrioridadPaciente = '" + PrioridadPaciente + "'" +
                    ", TraumaScore = '" + TraumaScore + "'" +
                    ", Glasgow = '" + Glasgow + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }



    //Traslado(Hoja Principal NO ANEXO)
    //Insertar datos primera vez
    public long insertaTraslado1Reporte(String ClaveFRAPOrden, String IntitucionTraslado
            , String ClasificacionPaciente, String PrioridadPaciente) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("IntitucionTraslado", IntitucionTraslado);
            values.put("ClasificacionPaciente", ClasificacionPaciente);
            values.put("PrioridadPaciente",PrioridadPaciente);


            ID = db.insert(TABLE_Traslado, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarTraslado1Reporte(String ClaveFRAPOrden, String IntitucionTraslado
            , String ClasificacionPaciente, String PrioridadPaciente) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_Traslado +
                    " SET IntitucionTraslado = '" + IntitucionTraslado + "'" +
                    ", ClasificacionPaciente = '" + ClasificacionPaciente + "'" +
                    ", PrioridadPaciente = '" + PrioridadPaciente + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Tratamiento(Hoja Principal NO ANEXO)
    //Insertar datos primera vez
    public long insertaTratamientoReporte(String ClaveFRAPOrden, String ViaAerea
            , String ControlCervical) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("ViaAerea", ViaAerea);
            values.put("ControlCervical", ControlCervical);



            ID = db.insert(TABLE_Tratamiento, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarTratamientoReporte(String ClaveFRAPOrden, String ViaAerea
            , String ControlCervical) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_Tratamiento +
                    " SET ViaAerea = '" + ViaAerea + "'" +
                    ", ControlCervical = '" + ControlCervical + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
    //Tratamiento(Hoja 2 Principal ANEXO I)
    //Insertar datos primera vez
    public long insertaTratamiento2Reporte(String ClaveFRAPOrden, String ControlCervical2
            , String Frec,String Vol,String Oxigenoterapia,String LtsxMin) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("ControlCervical2", ControlCervical2);
            values.put("Frec", Frec);
            values.put("Vol", Vol);
            values.put("Oxigenoterapia", Oxigenoterapia);
            values.put("LtsxMin", LtsxMin);

            ID = db.insert(TABLE_Tratamiento, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarTratamiento2Reporte(String ClaveFRAPOrden, String ControlCervical2
            , String Frec,String Vol,String Oxigenoterapia,String LtsxMin) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_Tratamiento +
                    " SET ControlCervical2 = '" + ControlCervical2 + "'" +
                    ", Frec = '" + Frec + "'" +
                    ", Vol = '" + Vol + "'" +
                    ", Oxigenoterapia = '" + Oxigenoterapia + "'" +
                    ", LtsxMin = '" + LtsxMin + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Tratamiento(Hoja 3 Principal ANEXO II)
    //Insertar datos primera vez
    public long insertaTratamiento3Reporte(String ClaveFRAPOrden, String Anexo1
            , String Anexo2,String Anexo3,String ControlHemorragias) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("Anexo1", Anexo1);
            values.put("Anexo2", Anexo2);
            values.put("Anexo3", Anexo3);
            values.put("ControlHemorragias", ControlHemorragias);

            ID = db.insert(TABLE_Tratamiento, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarTratamiento3Reporte(String ClaveFRAPOrden, String Anexo1
            , String Anexo2,String Anexo3,String ControlHemorragias) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_Tratamiento +
                    " SET Anexo1 = '" + Anexo1 + "'" +
                    ", Anexo2 = '" + Anexo2 + "'" +
                    ", Anexo3 = '" + Anexo3 + "'" +
                    ", ControlHemorragias = '" + ControlHemorragias + "'" +

                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Tratamiento(Hoja 4 Principal ANEXO III)
    //Insertar datos primera vez
    public long insertaTratamiento4Reporte(String ClaveFRAPOrden, String ViasVenosas
            , String SitioAplicacion,String TiposSoluciones,String Cantml,String Infusiones) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("ViasVenosas", ViasVenosas);
            values.put("SitioAplicacion", SitioAplicacion);
            values.put("TiposSoluciones", TiposSoluciones);
            values.put("Cantml", Cantml);
            values.put("Infusiones", Infusiones);

            ID = db.insert(TABLE_Tratamiento, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarTratamiento4Reporte(String ClaveFRAPOrden, String ViasVenosas
            , String SitioAplicacion,String TiposSoluciones,String Cantml,String Infusiones) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_Tratamiento +
                    " SET ViasVenosas = '" + ViasVenosas + "'" +
                    ", SitioAplicacion = '" + SitioAplicacion + "'" +
                    ", TiposSoluciones = '" + TiposSoluciones + "'" +
                    ", Cantml = '" + Cantml + "'" +
                    ", Infusiones = '" + Infusiones + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Tratamiento(Hoja 5 Principal ANEXO IIII)
    //Insertar datos primera vez
    public long insertaTratamiento5Reporte(String ClaveFRAPOrden, String FarmacologiayTHora
            , String FarmacologiayTMedicamento,String FarmacologiayTDosis,String FarmacologiayTVia,String FarmacologiayTT_E) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("FarmacologiayTHora", FarmacologiayTHora);
            values.put("FarmacologiayTMedicamento", FarmacologiayTMedicamento);
            values.put("FarmacologiayTDosis", FarmacologiayTDosis);
            values.put("FarmacologiayTVia", FarmacologiayTVia);
            values.put("FarmacologiayTT_E", FarmacologiayTT_E);

            ID = db.insert(TABLE_Tratamiento, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarTratamiento5Reporte(String ClaveFRAPOrden, String FarmacologiayTHora
            , String FarmacologiayTMedicamento,String FarmacologiayTDosis,String FarmacologiayTVia,String FarmacologiayTT_E) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_Tratamiento +
                    " SET FarmacologiayTHora = '" + FarmacologiayTHora + "'" +
                    ", FarmacologiayTMedicamento = '" + FarmacologiayTMedicamento + "'" +
                    ", FarmacologiayTDosis = '" + FarmacologiayTDosis + "'" +
                    ", FarmacologiayTVia = '" + FarmacologiayTVia + "'" +
                    ", FarmacologiayTT_E = '" + FarmacologiayTT_E + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Tratamiento(Hoja 6 Principal ANEXO V)
    //Insertar datos primera vez
    public long insertaTratamiento6Reporte(String ClaveFRAPOrden, String Procedimientos
            , String Procedimientos2,String Procedimientos3) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("Procedimientos", Procedimientos);
            values.put("Procedimientos2", Procedimientos2);
            values.put("Procedimientos3", Procedimientos3);


            ID = db.insert(TABLE_Tratamiento, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarTratamiento6Reporte(String ClaveFRAPOrden, String Procedimientos
            , String Procedimientos2,String Procedimientos3) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_Tratamiento +
                    " SET Procedimientos = '" + Procedimientos + "'" +
                    ", Procedimientos2 = '" + Procedimientos2 + "'" +
                    ", Procedimientos3 = '" + Procedimientos3 + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }



    //Traslado(Hoja 2  ANEXO I)
    //Insertar datos primera vez
    public long insertaTraslado2Reporte(String ClaveFRAPOrden, String NegativaArecibirAtencion) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("NegativaArecibirAtencion", NegativaArecibirAtencion);



            ID = db.insert(TABLE_Traslado, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarTraslado2Reporte(String ClaveFRAPOrden, String NegativaArecibirAtencion) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_Traslado +
                    " SET NegativaArecibirAtencion = '" + NegativaArecibirAtencion + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Traslado(Hoja 3  ANEXO II)
    //Insertar datos primera vez
    public long insertaTraslado3Reporte(String ClaveFRAPOrden, String FirmaPaciente,String NombrePaciente,String FirmaTestigo,String NombreTestigo) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("FirmaPaciente", FirmaPaciente);
            values.put("NombrePaciente", NombrePaciente);
            values.put("FirmaTestigo", FirmaTestigo);
            values.put("NombreTestigo", NombreTestigo);


            ID = db.insert(TABLE_Traslado, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarTraslado3Reporte(String ClaveFRAPOrden, String FirmaPaciente,String NombrePaciente,String FirmaTestigo,String NombreTestigo) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_Traslado +
                    " SET FirmaPaciente = '" + FirmaPaciente + "'" +
                    ", NombrePaciente = '" + NombrePaciente + "'" +
                    ", FirmaTestigo = '" + FirmaTestigo + "'" +
                    ", NombreTestigo = '" + NombreTestigo + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Observacion(Hoja Principal
    //Insertar datos primera vez
    public long insertaObservacionReporte(String ClaveFRAPOrden, String Observaciones) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("Observaciones", Observaciones);



            ID = db.insert(TABLE_Observaciones, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarObservacionReporte(String ClaveFRAPOrden, String Observaciones) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_Observaciones +
                    " SET Observaciones = '" + Observaciones + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
    //Ministerio Publico(Hoja Principal
    //Insertar datos primera vez
    public long insertaMinisterioPublicoReporte(String ClaveFRAPOrden, String FirmaSelloQuienRecibe,String NombreQuienRecibe) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("FirmaSelloQuienRecibe", FirmaSelloQuienRecibe);
            values.put("NombreQuienRecibe", NombreQuienRecibe);


            ID = db.insert(TABLE_MinisterioPublico, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarMinisterioPublicoReporte(String ClaveFRAPOrden, String FirmaSelloQuienRecibe,String NombreQuienRecibe) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_MinisterioPublico +
                    " SET FirmaSelloQuienRecibe = '" + FirmaSelloQuienRecibe + "'" +
                    ", NombreQuienRecibe = '" + NombreQuienRecibe + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;



    }

    //Datos Legales(Hoja Principal
    //Insertar datos primera vez
    public long insertaDatosLegalesReporte(String ClaveFRAPOrden, String Dependencia,String No_Unidades,String NombreoNoOficiales) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("Dependencia", Dependencia);
            values.put("No_Unidades", No_Unidades);
            values.put("NombreoNoOficiales", NombreoNoOficiales);

            ID = db.insert(TABLE_DatosLegales, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarDatosLegalesReporte(String ClaveFRAPOrden, String Dependencia,String No_Unidades,String NombreoNoOficiales) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosLegales +
                    " SET Dependencia = '" + Dependencia + "'" +
                    ", No_Unidades = '" + No_Unidades + "'" +
                    ", NombreoNoOficiales = '" + NombreoNoOficiales + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;



    }

    //Datos Legales(Hoja 2 ANEXO 1
    //Insertar datos primera vez
    public long insertaDatosLegales2Reporte(String ClaveFRAPOrden, String Vehiculo1
            ,String Placa1,String Vehiculo2,String Placa2,String Vehiculo3,String Placa3,String DescripcionPosicion) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("Vehiculo1", Vehiculo1);
            values.put("Placa1", Placa1);
            values.put("Placa2", Placa2);
            values.put("Vehiculo3", Vehiculo3);
            values.put("Placa3", Placa3);
            values.put("DescripcionPosicion", DescripcionPosicion);

            ID = db.insert(TABLE_DatosLegales, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarDatosLegales2Reporte(String ClaveFRAPOrden, String Vehiculo1
            ,String Placa1,String Vehiculo2,String Placa2,String Vehiculo3,String Placa3,String DescripcionPosicion) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosLegales +
                    " SET Vehiculo1 = '" + Vehiculo1 + "'" +
                    ", Placa1 = '" + Placa1 + "'" +
                    ", Vehiculo2 = '" + Vehiculo2 + "'" +
                    ", Placa2 = '" + Placa2 + "'" +
                    ", Vehiculo3 = '" + Vehiculo3 + "'" +
                    ", Placa3 = '" + Placa3 + "'" +
                    ", DescripcionPosicion = '" + DescripcionPosicion + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;



    }


    //Datos Legales(Hoja 3 ANEXO 2
    //Insertar datos primera vez
    public long insertaDatosLegales3Reporte(String ClaveFRAPOrden, String Pertenencias
            ,String FirmaRecibePertenencias,String NombreyCargo,String SeguroAutomovil) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("Pertenencias", Pertenencias);
            values.put("FirmaRecibePertenencias", FirmaRecibePertenencias);
            values.put("NombreyCargo", NombreyCargo);
            values.put("SeguroAutomovil", SeguroAutomovil);


            ID = db.insert(TABLE_DatosLegales, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarDatosLegales3Reporte(String ClaveFRAPOrden, String Pertenencias
            ,String FirmaRecibePertenencias,String NombreyCargo,String SeguroAutomovil) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosLegales +
                    " SET Pertenencias = '" + Pertenencias + "'" +
                    ", FirmaRecibePertenencias = '" + FirmaRecibePertenencias + "'" +
                    ", NombreyCargo = '" + NombreyCargo + "'" +
                    ", SeguroAutomovil = '" + SeguroAutomovil + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;



    }
    //HospitalReceptor(Hoja Principal
    //Insertar datos primera vez
    public long insertaHospitalReceptorReporte(String ClaveFRAPOrden
            , String FirmaEntegaPaciente,String NombreEntregaPaciente,String FirmaRecibePaciente,String NombreRecibePaciente) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("FirmaEntegaPaciente", FirmaEntegaPaciente);
            values.put("NombreEntregaPaciente", NombreEntregaPaciente);
            values.put("FirmaRecibePaciente", FirmaRecibePaciente);
            values.put("NombreRecibePaciente", NombreRecibePaciente);

            ID = db.insert(TABLE_HospitalReceptor, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarHospitalReceptorReporte(String ClaveFRAPOrden
            , String FirmaEntegaPaciente,String NombreEntregaPaciente,String FirmaRecibePaciente,String NombreRecibePaciente) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_HospitalReceptor +
                    " SET FirmaEntegaPaciente = '" + FirmaEntegaPaciente + "'" +
                    ", NombreEntregaPaciente = '" + NombreEntregaPaciente + "'" +
                    ", FirmaRecibePaciente = '" + FirmaRecibePaciente + "'" +
                    ", NombreRecibePaciente = '" + NombreRecibePaciente + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
    //////////MaterialMedico
    //Insertar datos primera vez
    public long insertarMaterialMedicO(String ClaveFRAPOrden,String MaterialMedico,String CantidadMaterialMedico) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("MaterialMedico", MaterialMedico);
            values.put("CantidadMaterialMedico", CantidadMaterialMedico);

            ID = db.insert(TABLE_MaterialMedico, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarMaterialMedico(String ClaveFRAPOrden,String MaterialMedico,String CantidadMaterialMedico) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_MaterialMedico +
                    " SET MaterialMedico = '" + MaterialMedico + "'" +
                    ", CantidadMaterialMedico = '" + CantidadMaterialMedico + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Arreglo para lista mostrar
    public ArrayList<MaterialMedico> verMaterialesConsumo() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<MaterialMedico> MaterialMedicoC = new ArrayList<>();
        MaterialMedico MaterialMedico1;
        Cursor cursorMateriales;
        cursorMateriales = db.rawQuery("SELECT * FROM " + TABLE_MaterialMedico + " ORDER BY id DESC", null);

        if (cursorMateriales.moveToFirst()) {
            do {
                MaterialMedico1 = new MaterialMedico();
                MaterialMedico1.setId(cursorMateriales.getInt(0));
                MaterialMedico1.setClaveFRAPOrden(cursorMateriales.getString(1));
                MaterialMedico1.setMaterial(cursorMateriales.getString(2));
                MaterialMedico1.setCantidad(cursorMateriales.getString(3));
                MaterialMedicoC.add(MaterialMedico1);
            } while (cursorMateriales.moveToNext());
        }
        cursorMateriales.close();

        return MaterialMedicoC;
    }

    //////////Medicamentos Consumos
    //Insertar datos primera vez
    public long insertarMedicamentosConsumo(String ClaveFRAPOrden
            ,String HoraCaptura,String Medicamento,String Dosis,String Via,String TE) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("HoraCaptura", HoraCaptura);
            values.put("Medicamento", Medicamento);
            values.put("Dosis", Dosis);
            values.put("Via", Via);
            values.put("TE", TE);
            ID = db.insert(TABLE_MedicamentosConsumos, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarMedicamentosConsumo(String ClaveFRAPOrden
            ,String HoraCaptura,String Medicamento,String Dosis,String Via,String TE) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_MedicamentosConsumos +
                    " SET HoraCaptura = '" + HoraCaptura + "'" +
                    ", Medicamento = '" + Medicamento + "'" +
                    ", Dosis = '" + Dosis + "'" +
                    ", Via = '" + Via + "'" +
                    ", TE = '" + TE + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
    //Arreglo para lista mostrar
    public ArrayList<MedicamentosConsumos> verMedicamentosConsumo() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<MedicamentosConsumos> MedicamentosConsumos = new ArrayList<>();
        MedicamentosConsumos medicamentosConsumos1;
        Cursor cursorMedicamentos;
        cursorMedicamentos = db.rawQuery("SELECT * FROM " + TABLE_MedicamentosConsumos + " ORDER BY id DESC", null);

        if (cursorMedicamentos.moveToFirst()) {
            do {
                medicamentosConsumos1 = new MedicamentosConsumos();
                medicamentosConsumos1.setId(cursorMedicamentos.getInt(0));
                medicamentosConsumos1.setClaveFRAPOrden(cursorMedicamentos.getString(1));
                medicamentosConsumos1.setHoraCaptura(cursorMedicamentos.getString(2));
                medicamentosConsumos1.setMedicamento(cursorMedicamentos.getString(3));
                medicamentosConsumos1.setDosis(cursorMedicamentos.getString(4));
                medicamentosConsumos1.setVia(cursorMedicamentos.getString(5));
                medicamentosConsumos1.setTE(cursorMedicamentos.getString(6));
                MedicamentosConsumos.add(medicamentosConsumos1);
            } while (cursorMedicamentos.moveToNext());
        }
        cursorMedicamentos.close();

        return MedicamentosConsumos;
    }

    //////////FRAP CANCELADO DELEGACION
    //Insertar datos primera vez
    public long insertarFrapcanceladoDelegacion(String ClaveFRAPOrden
            ,String StatusC,String DelegacionC,String AsignacionC,String MotivodeCancelacion) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("StatusC", StatusC);
            values.put("DelegacionC", DelegacionC);
            values.put("AsignacionC", AsignacionC);
            values.put("MotivodeCancelacion", MotivodeCancelacion);

            ID = db.insert(TABLE_FRAPCANCELADO, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarFRAPcanceladoDelegacion(String ClaveFRAPOrden
            ,String StatusC,String DelegacionC,String AsignacionC,String MotivodeCancelacion) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_FRAPCANCELADO +
                    " SET StatusC = '" + StatusC + "'" +
                    ", DelegacionC = '" + DelegacionC + "'" +
                    ", AsignacionC = '" + AsignacionC + "'" +
                    ", MotivodeCancelacion = '" + MotivodeCancelacion + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Servicio(Hoja Principal NO ANEXO
    //Insertar datos primera vez
    public long insertaServicio1CANCELADOReporte(String ClaveFRAPOrden, String Fecha
            , String Dia, String HoraLlamada, String HoraSalida, String HoraLlegada
            , String HoraTraslado,String HoraHospital
            ,String HoraDisponible, String MotivoDeAtencion,String SubMotivo) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("Fecha", Fecha);
            values.put("Dia", Dia);
            values.put("HoraLlamada",HoraLlamada);
            values.put("HoraSalida", HoraSalida);
            values.put("HoraLlegada", HoraLlegada);
            values.put("HoraTraslado",HoraTraslado);
            values.put("HoraHospital", HoraHospital);
            values.put("HoraDisponible", HoraDisponible);
            values.put("MotivoDeAtencion",MotivoDeAtencion);
            values.put("SubMotivo", SubMotivo);

            ID = db.insert(TABLE_DatosServicioCancelado, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarServicio1CANCELADOReporte(String ClaveFRAPOrden, String Fecha
            , String Dia, String HoraLlamada, String HoraSalida, String HoraLlegada
            , String HoraTraslado,String HoraHospital
            ,String HoraDisponible, String MotivoDeAtencion,String SubMotivo) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosServicioCancelado +
                    " SET Fecha = '" + Fecha + "'" +
                    ", Dia = '" + Dia + "'" +
                    ", HoraLlamada = '" + HoraLlamada + "'" +
                    ", HoraSalida = '" + HoraSalida + "'" +
                    ", HoraLlegada = '" + HoraLlegada + "'" +
                    ", HoraTraslado = '" + HoraTraslado + "'" +
                    ", HoraHospital = '" + HoraHospital + "'" +
                    ", HoraDisponible = '" + HoraDisponible + "'" +
                    ", MotivoDeAtencion = '" + MotivoDeAtencion + "'" +
                    ", SubMotivo = '" + SubMotivo + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Servicio(Hoja Principal ANEXO(2)
    //Insertar datos primera vez
    public long insertaServicio2CANCELADOReporte(String ClaveFRAPOrden, String CalleServicio
            , String Calle1, String Calle2, String Colonia, String DelegacionMunicipio
            ) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("CalleServicio", CalleServicio);
            values.put("Calle1", Calle1);
            values.put("Calle2",Calle2);
            values.put("Colonia", Colonia);
            values.put("DelegacionMunicipio", DelegacionMunicipio);


            ID = db.insert(TABLE_DatosServicioCancelado, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarServicio2CANCELADOReporte(String ClaveFRAPOrden, String CalleServicio
            , String Calle1, String Calle2, String Colonia, String DelegacionMunicipio
            ) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_DatosServicioCancelado +
                    " SET CalleServicio = '" + CalleServicio + "'" +
                    ", Calle1 = '" + Calle1 + "'" +
                    ", Calle2 = '" + Calle2 + "'" +
                    ", Colonia = '" + Colonia + "'" +
                    ", DelegacionMunicipio = '" + DelegacionMunicipio + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Control(Hoja Principal
    //Insertar datos primera vez
    public long insertaControlCANCELADO(String ClaveFRAPOrden, String NoAmbulancia
            , String Operador, String PrestadoresServicio) {
        long ID = 0;
        try {

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("ClaveFRAPOrden", ClaveFRAPOrden);
            values.put("NoAmbulancia", NoAmbulancia);
            values.put("Operador", Operador);
            values.put("PrestadoresServicio",PrestadoresServicio);



            ID = db.insert(TABLE_CONTROLCANCELADO, null, values);

        } catch (Exception e) {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }

    //Editar
    public boolean editarControlReporte(String ClaveFRAPOrden, String NoAmbulancia
            , String Operador, String PrestadoresServicio) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Modifica la tabla
            db.execSQL("UPDATE " + TABLE_CONTROLCANCELADO +
                    " SET NoAmbulancia = '" + NoAmbulancia + "'" +
                    ", Operador = '" + Operador + "'" +
                    ", PrestadoresServicio = '" + PrestadoresServicio + "'" +
                    " WHERE ClaveFRAPOrden = '" + ClaveFRAPOrden + "'");


            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
}
