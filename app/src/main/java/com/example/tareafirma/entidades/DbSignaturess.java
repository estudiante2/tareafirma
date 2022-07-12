package com.example.tareafirma.entidades;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import com.example.tareafirma.datos.DbHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DbSignaturess extends DbHelper {
    Context context;

    public DbSignaturess(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarFirma(Bitmap imagen, String descripcion)
    {
        long id = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
        imagen.compress(Bitmap.CompressFormat.PNG, 0 , baos);
        byte[] blob = baos.toByteArray();
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase datos = dbHelper.getWritableDatabase();

        String sql = "INSERT INTO "+ TABLE_FIRMAS + " (imagen, descripcion) VALUES(?,?)";
        SQLiteStatement insert = datos.compileStatement(sql);
        insert.clearBindings();
        insert.bindBlob(1, blob);
        insert.bindString(2, descripcion);
        id = insert.executeInsert();
        datos.close();

        return id;
    }

    public ArrayList<Signaturess> mostrarFirmas()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase datos = dbHelper.getWritableDatabase();

        ArrayList<Signaturess> listaFirmas = new ArrayList<>();
        Signaturess firma = null;
        Cursor cursorFirmas = null;

        cursorFirmas = datos.rawQuery("SELECT * FROM " + TABLE_FIRMAS,null);

        if(cursorFirmas.moveToFirst()){
            do{
                firma = new Signaturess();
                firma.setId(cursorFirmas.getInt(0));
                firma.setFirma(cursorFirmas.getBlob(1));
                firma.setDescripcion(cursorFirmas.getString(2));
                listaFirmas.add(firma);
            }while (cursorFirmas.moveToNext());
        }

        cursorFirmas.close();

        return listaFirmas;
    }

    public Signaturess verFirmas(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase datos = dbHelper.getWritableDatabase();

        Signaturess firma = null;
        Cursor cursorFirmas = null;

        cursorFirmas = datos.rawQuery("SELECT * FROM " + TABLE_FIRMAS + " WHERE id = " + id + " LIMIT 1",null);

        if(cursorFirmas.moveToFirst()){
            firma = new Signaturess();
            firma.setId(cursorFirmas.getInt(0));
            firma.setFirma(cursorFirmas.getBlob(1));
            firma.setDescripcion(cursorFirmas.getString(2));
        }

        cursorFirmas.close();

        return firma;
    }

    public boolean eliminarFirma(int id) {

        boolean isCorrecto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase datos = dbHelper.getWritableDatabase();

        try {
            datos.execSQL("DELETE FROM " + TABLE_FIRMAS + " WHERE id = '" + id + "'");
            isCorrecto = true;
        } catch (Exception ex)
        {
            ex.toString();
            isCorrecto = false;
        } finally {
            datos.close();
        }

        return isCorrecto;
    }
}
