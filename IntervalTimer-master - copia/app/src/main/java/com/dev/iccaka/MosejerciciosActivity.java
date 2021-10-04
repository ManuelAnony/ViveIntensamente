package com.dev.iccaka;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dev.iccaka.adaptadores.adaptador_ejercicios;
import com.dev.iccaka.intervaltimer.R;
import com.dev.iccaka.utilidades.Utilidades;

import java.util.ArrayList;

public class MosejerciciosActivity extends AppCompatActivity {

    private adminBD admin;
    String ejercicio = "Ejercicio: ";
    //private SQLiteDatabase entrenamiento;
    //String[] arreglo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar2);

        RecyclerView recyclerView = findViewById(R.id.recyview_ejercicios);
        admin = new adminBD(this, vars.bd, null, vars.version);
        SQLiteDatabase entrenamiento = admin.getReadableDatabase();
        Cursor c = entrenamiento.rawQuery("SELECT idejer, idsesion, nomejer, series, rep FROM ejercicios", null);
        if (!c.moveToFirst()) {
            c.close();
            entrenamiento.close();
            return;
        }
        ArrayList<Ejercicios> ejercicios = new ArrayList<>();


        do {
            String nomejer = c.getString(c.getColumnIndex(Utilidades.NOMBRE_EJERCICIO));
            int rep = c.getInt(c.getColumnIndex(Utilidades.N_REP));
            int series = c.getInt(c.getColumnIndex(Utilidades.N_SERIES));
            int idsesion = c.getInt(c.getColumnIndex(Utilidades.ID_SESION));
            int idejer = c.getInt(c.getColumnIndex(Utilidades.ID_EJERCICIO));
            Ejercicios Ejercicios = new Ejercicios(nomejer, rep, series, idsesion, idejer);
            ejercicios.add(Ejercicios);
        } while (c.moveToNext());

        c.close();
        adaptador_ejercicios adaptadorEjercicios = new adaptador_ejercicios(ejercicios);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorEjercicios);

    }
    public void volver (View view){
        Intent v = new Intent (this, MostrarActivity.class);
        startActivity(v);
    }
}