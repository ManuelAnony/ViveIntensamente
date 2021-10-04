package com.dev.iccaka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dev.iccaka.intervaltimer.R;

public class ProgresoActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_progreso);

        }
        public void volver (View view){
            Intent v = new Intent (this, MainActivity.class);
            startActivity(v);
        }
        public void registro_progreso (View view){
            Intent regis = new Intent (this,RegistroActivity.class);
            startActivity(regis);
        }
        public void ver_progreso (View view){
            Intent ver = new Intent (this, MostrarActivity.class);
            startActivity(ver);
        }
        public void metas (View view){
            Intent m = new Intent (this, MetasActivity.class);
            startActivity(m);
        }

//MetasActivity
}