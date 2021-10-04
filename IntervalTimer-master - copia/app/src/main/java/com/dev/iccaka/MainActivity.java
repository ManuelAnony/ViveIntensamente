package com.dev.iccaka;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dev.iccaka.intervaltimer.R;


public class MainActivity extends  Activity  {

    public int sumador = RegistroActivity.puntaje;

    int pun =+ sumador;

    String puntos = String.valueOf(pun);

    /*private SensorManager sensorManager;
    private Sensor pressure;
    private String aux;
    private TextView salida;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.Puntaje);
        textView.setText(puntos);


        // temperatura
        /*salida = (TextView) findViewById(R.id.salida);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        pressure = sensorManager.getDefaultSensor(Integer.parseInt(Sensor.STRING_TYPE_AMBIENT_TEMPERATURE));
        aux = String.valueOf(pressure);*/


    }


    public void tiempo (View view){
        Intent ejercicio = new Intent(this, IntervaloActivity.class);
        startActivity(ejercicio);
    }

    public void progreso (View view){
        Intent Progreso = new Intent(this, ProgresoActivity.class);
        startActivity(Progreso);
    }
    public void ejercicios (View view){
        Intent ejercicios = new Intent(this, EjerciciosActivity.class);
        startActivity(ejercicios);
    }


}