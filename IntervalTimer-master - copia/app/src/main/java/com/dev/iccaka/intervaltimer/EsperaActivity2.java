package com.dev.iccaka.intervaltimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class EsperaActivity2 extends Activity {


    Contador counter;
    TextView texto;
    int b = 0;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera2);

        texto = (TextView) findViewById(R.id.txt);
        counter = new Contador(20000, 1000);

        counter.start();

    }

    public void fin() {

        texto.setText(texto.getText() + "FIN" + b);
    }


    public void hola() {

        texto.setText(texto.getText() + "Hola \n" + b);

    }

    public void segundos() {

        for (int a = 0; a <= 20; a++) {
            b = a + 1;
            texto.setText(texto.getText() + ". \n" + b);

        }
    }


    public class Contador extends CountDownTimer {

        public Contador(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);


        }

        @Override
        public void onFinish() {
            //fin();

        }

        @Override
        public void onTick(long millisUntilFinished) {
            // hola();
            segundos();

        }

    }
}