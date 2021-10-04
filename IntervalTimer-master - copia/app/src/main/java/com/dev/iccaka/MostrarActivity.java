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

import com.dev.iccaka.adaptadores.adaptador_sesion;
import com.dev.iccaka.intervaltimer.R;
import com.dev.iccaka.utilidades.Utilidades;

import java.util.ArrayList;


public class MostrarActivity extends AppCompatActivity {

    private adminBD admin;
    //private SQLiteDatabase entrenamiento;
    //String[] arreglo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        /*
        admin = new adminBD(this, vars.bd, null, vars.version);
        entrenamiento = admin.getWritableDatabase();
        lista= (ListView) findViewById(R.id.recyview);
        fila = entrenamiento.rawQuery("SELECT iddep AS _id, nombre FROM departamento ORDER BY nombre", null);
        SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_dropdown_item, fila,
                new String[] {"nombre"}, new int[] {android.R.id.text1}, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter2);
*/
        //admin = new adminBD(this, vars.bd, null, vars.version);
        //entrenamiento = admin.getWritableDatabase();
        /*Cursor c = entrenamiento.rawQuery("SELECT idsesion, fecha, enfoque, numejer FROM sesion", null);
        if (c != null) {
            c.moveToFirst();
            do {
                //Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                String idsesion = c.getString(c.getColumnIndex("id"));
                String fecha = c.getString(c.getColumnIndex("Fecha"));
                String enfoque = c.getString(c.getColumnIndex("Enfoque"));
                String numejer = c.getString(c.getColumnIndex("N° Ejercicios"));
            } while (c.moveToNext());
        }
        //Cerramos el cursor y la conexion con la base de datos
        c.close();
        entrenamiento.close();

         */
        RecyclerView recyclerView = findViewById(R.id.recyview_sesion);
        admin = new adminBD(this, vars.bd, null, vars.version);
        SQLiteDatabase entrenamiento = admin.getReadableDatabase();
        Cursor c = entrenamiento.rawQuery("SELECT idsesion, fecha, enfoque, numejer FROM sesion", null);
        if (!c.moveToFirst()) {
            c.close();
            entrenamiento.close();
            return;
        }
        ArrayList<Sesion> sesion = new ArrayList<>();


        do {
            String enfoque = c.getString(c.getColumnIndex(Utilidades.ENFOQUE_EJERCICO));
            String fecha = c.getString(c.getColumnIndex(Utilidades.FECHA_EJERCICIO));
            int numejer = c.getInt(c.getColumnIndex(Utilidades.NUMERODE_EJERCICIOS));
            int idsesion = c.getInt(c.getColumnIndex(Utilidades.ID_SESION));
            Sesion Sesion = new Sesion(enfoque, fecha, numejer, idsesion);
            sesion.add(Sesion);
        } while (c.moveToNext());

        c.close();
        //String enfoque, String fecha,int numejer, int idsesion
        adaptador_sesion adaptadorSesion = new adaptador_sesion(sesion);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorSesion);
    }
    public void ejercicios (View view){
        Intent ver = new Intent (this, MosejerciciosActivity.class);
        startActivity(ver);
    }
    public void volver (View view){
        Intent v = new Intent (this, ProgresoActivity.class);
        startActivity(v);
    }
}