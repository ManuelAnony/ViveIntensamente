package com.dev.iccaka.intervaltimer;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static java.lang.StrictMath.log;


public class MainActivity extends  Activity  {

    /*private SensorManager sensorManager;
    private Sensor pressure;
    private String aux;
    private TextView salida;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Intent Progreso = new Intent(this, ProgressActivity.class);
        startActivity(Progreso);
    }

    public void ejercicio (View view){
        Intent ejercicio = new Intent(this, ProgressActivity.class);
        startActivity(ejercicio);
    }
}