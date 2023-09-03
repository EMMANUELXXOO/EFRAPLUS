package com.Cruzroja.frapplus.DBSQLITE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.Cruzroja.frapplus.entidades.FRAP;

import java.util.ArrayList;

//db FRAP
    public class dbinsercion extends DBHelper{

    Context context;

    //Constructor de la clase
    public dbinsercion(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaFrap(String Status)
    {
        long ID=0;
        try{

            //Lllamamos a dbhelper
            DBHelper dbHelper = new DBHelper(context);
            //Lllamamos a sqlite
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Agregar la funcion para insertar registro
            ContentValues values = new ContentValues();

            values.put("Status",Status);

             ID = db.insert(TABLE_FRAP,null,values);

        }catch (Exception e)
        {
            Toast.makeText(context, "Error de insercion", Toast.LENGTH_SHORT).show();
            e.toString();
        }
        return ID;
    }


    public ArrayList<FRAP> mostrarContactos() {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<FRAP> listaContactos = new ArrayList<>();
        FRAP frap;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_FRAP + " ORDER BY id ASC", null);

        if (cursorContactos.moveToFirst()) {
            do {
                frap = new FRAP();
                frap.setId(cursorContactos.getInt(0));
                frap.setStatus(cursorContactos.getString(1));
                listaContactos.add(frap);
            } while (cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaContactos;
    }

    public FRAP verContacto(int id) {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        FRAP frap = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_FRAP + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorContactos.moveToFirst()) {
            frap = new FRAP();
            frap.setId(cursorContactos.getInt(0));
            frap.setStatus(cursorContactos.getString(1));
        }

        cursorContactos.close();

        return frap;
    }
    //Metodo para Seleccionar el id
    public FRAP VerFrap(int id) {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        FRAP frap=null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_FRAP + " WHERE id= "+ id + "LIMIT 1 ", null);

        if (cursorContactos.moveToFirst()) {

                frap = new FRAP();
                frap.setId(cursorContactos.getInt(0));
                frap.setStatus(cursorContactos.getString(1));


        }

        cursorContactos.close();

        return frap;
    }

    public boolean editarfrap(int id, String status) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_FRAP + " SET status = '" + status + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminar(int id) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_FRAP + " WHERE id = '" + id + "'");
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
