package com.example.tareafirma.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "firmas.datos";
    public static final String TABLE_FIRMAS = "tbl_firmas";

    public DbHelper(@Nullable Context context)
    {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_FIRMAS + "(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "imagen BLOB," + "descripcion TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_FIRMAS);
        onCreate(sqLiteDatabase);
    }
}
