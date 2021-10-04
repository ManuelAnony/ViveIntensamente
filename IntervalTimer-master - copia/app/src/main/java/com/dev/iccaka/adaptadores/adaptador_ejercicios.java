package com.dev.iccaka.adaptadores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.iccaka.Ejercicios;
import com.dev.iccaka.intervaltimer.R;

import java.util.List;

public class adaptador_ejercicios extends RecyclerView.Adapter<adaptador_ejercicios.MyViewHolder> {
    private List<Ejercicios> listaDeEjercicios;

    public List<Ejercicios> getListaDeEjercicios() {
        return listaDeEjercicios;
    }

    public void setListaDeEjercicios(List<Ejercicios> listaDeEjercicios) {
        this.listaDeEjercicios = listaDeEjercicios;
    }

    public adaptador_ejercicios(List<Ejercicios> listaDeEjercicios) {
        this.listaDeEjercicios = listaDeEjercicios;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaSesion = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_ejercicios, viewGroup, false);
        return new MyViewHolder(filaSesion);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
// Obtener la persona de nuestra lista gracias al índice i
        Ejercicios ejercicios = listaDeEjercicios.get(i);
        //(nomejer, series, idsesion, idejer);
        // Obtener los datos de la lista
        String nomejer = ejercicios.getNomejer();
        String rep = String.valueOf(ejercicios.getRep());
        String series = String.valueOf(ejercicios.getSeries());
        String idsesion = String.valueOf(ejercicios.getIdsesion());
        String idejer = String.valueOf(ejercicios.getIdejer());

        // Y poner a los TextView los datos con setText
        myViewHolder.tvnomejer.setText(nomejer);
        myViewHolder.tvrep.setText(rep);
        myViewHolder.tvseries.setText(series);
        myViewHolder.tvidsesion.setText(idsesion);
        myViewHolder.tvidejer.setText(idejer);

    }

    @Override
    public int getItemCount() {
        return listaDeEjercicios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvnomejer, tvrep, tvseries, tvidsesion, tvidejer;

        MyViewHolder(View itemView) {
            super(itemView);
            this.tvnomejer = itemView.findViewById(R.id.tvFilaNomejer);
            this.tvrep = itemView.findViewById(R.id.tvFilaRep);
            this.tvseries = itemView.findViewById(R.id.tvFilaSeries);
            this.tvidsesion = itemView.findViewById(R.id.tvFilaidsesion);
            this.tvidejer = itemView.findViewById(R.id.tvFilaIdejer);
        }
    }
}

