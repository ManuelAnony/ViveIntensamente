package com.dev.iccaka.adaptadores;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.iccaka.Sesion;
import com.dev.iccaka.intervaltimer.R;

import java.util.List;

public class adaptador_sesion extends RecyclerView.Adapter<adaptador_sesion.MyViewHolder> {
        private List<Sesion> listaDeSesion;

        public List<Sesion> getListaDeSesion() {
            return listaDeSesion;
        }

        public void setListaDeSesion(List<Sesion> listaDeSesion) {
            this.listaDeSesion = listaDeSesion;
        }

        public adaptador_sesion(List<Sesion> listaDeSesion) {
            this.listaDeSesion = listaDeSesion;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View filaSesion = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_sesion, viewGroup, false);
            return new MyViewHolder(filaSesion);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
// Obtener la persona de nuestra lista gracias al índice i
            Sesion sesion = listaDeSesion.get(i);

            // Obtener los datos de la lista
            String enfoque = sesion.getEnfoque();
            String fecha = sesion.getFecha();
            String numejer = String.valueOf(sesion.getNumejer());
            String idsesion = String.valueOf(sesion.getIdsesion());
            // Y poner a los TextView los datos con setText
            myViewHolder.tvenfoque.setText(enfoque);
            myViewHolder.tvfecha.setText(fecha);
            myViewHolder.tvnumejer.setText(numejer);
            myViewHolder.tvidsesion.setText(idsesion);
        }

        @Override
        public int getItemCount() {
            return listaDeSesion.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvenfoque, tvfecha, tvnumejer,tvidsesion;

            MyViewHolder(View itemView) {
                super(itemView);
                this.tvenfoque = itemView.findViewById(R.id.tvFilaEnfoque);
                this.tvfecha = itemView.findViewById(R.id.tvFilaFecha);
                this.tvnumejer = itemView.findViewById(R.id.tvFilanumejer);
                this.tvidsesion = itemView.findViewById(R.id.tvFilaidsesion);
            }
        }
}

