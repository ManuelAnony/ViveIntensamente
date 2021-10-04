package com.dev.iccaka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dev.iccaka.intervaltimer.R;

public class EjerciciosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);
    }
    public void volver (View view){
        Intent v = new Intent (this, MainActivity.class);
        startActivity(v);
    }
    public void superiror (View view){
        Intent superior = new Intent (this, SuperiorActivity.class);
        startActivity(superior);
    }
    public void inferiror (View view) {
        Intent inferiror = new Intent(this, InferiroActivity.class);
        startActivity(inferiror);
    }
    public void completo (View view) {
        Intent completo = new Intent(this, CompletoActivity.class);
        startActivity(completo);
    }
}