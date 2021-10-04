package com.dev.iccaka;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

import com.dev.iccaka.intervaltimer.R;

public class adaptador extends PagerAdapter {
    Context context;
    LayoutInflater LayouInflater;
    public adaptador(Context context){

        this.context = context;
    }
    // arrays para las imagenes

    public int[] imagenes = {
            //R.drawable.nombre de la imagen PNG
            R.drawable.flexiones,
            R.drawable.planchas,
            R.drawable.abdominales,
            R.drawable.fle_inclinadas,
            R.drawable.fle_declinadas
    };
    // array para nombre de los ejercicios
    public String [] nomejercicios = {
            "Flexiones",
            "Planchas",
            "Adominales",
            "Flexiones inclinadas",
            "Flexiones declinadas"
    };
    // array para concepto de los ejercicios
    public String [] conejercicios = {
            "Flexiones concepto",
            "Planchas_ concepto",
            "Adominales_ concepto",
            "Flexiones inclinadas_ concepto",
            "Flexiones declinadas_ concepto"
    };

    @Override
    public int getCount() {
        return nomejercicios.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return false;
    }
}
