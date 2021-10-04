package com.dev.iccaka;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class adminBD extends SQLiteOpenHelper{

    public adminBD(@Nullable Context context, @org.jetbrains.annotations.Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase entrenamiento ) {
        entrenamiento.execSQL("create table sesion (idsesion INTEGER PRIMARY KEY AUTOINCREMENT, fecha int not null, enfoque text not null, numejer int not null )");
        entrenamiento.execSQL("create table ejercicios (idejer INTEGER PRIMARY KEY AUTOINCREMENT, idsesion INTEGER, nomejer text not null, series int not null, rep int not null, " +
                "FOREIGN KEY (idsesion) REFERENCES sesion(idsesion))");
        entrenamiento.execSQL("create table puntos (id_puntos INTEGER PRIMARY KEY AUTOINCREMENT, puntos int)");
        entrenamiento.execSQL("create table metas(id_metas INTEGER PRIMARY KEY AUTOINCREMENT, meta text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase entrenamiento, int i, int i1) {
        entrenamiento.execSQL("drop table if exists sesion" );
        entrenamiento.execSQL("create table sesion (idsesion INTEGER PRIMARY KEY AUTOINCREMENT, fecha int not null, enfoque text not null, numejer int not null )");
        entrenamiento.execSQL("drop table if exists ejercicios" );
        entrenamiento.execSQL("create table ejercicios (idejer INTEGER PRIMARY KEY AUTOINCREMENT, idsesion INTEGER, nomejer text not null, series int not null, rep int not null, " +
                "FOREIGN KEY (idsesion) REFERENCES sesion(idsesion))");

    }

}
