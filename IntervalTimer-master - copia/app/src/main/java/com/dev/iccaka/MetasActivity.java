package com.dev.iccaka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dev.iccaka.intervaltimer.R;

public class MetasActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metas);
    }

    public void volver (View view){
        Intent v = new Intent (this, ProgresoActivity.class);
        startActivity(v);
    }
}
