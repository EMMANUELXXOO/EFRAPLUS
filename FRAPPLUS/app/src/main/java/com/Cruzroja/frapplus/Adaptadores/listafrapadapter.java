package com.Cruzroja.frapplus.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.VerActivity;
import com.Cruzroja.frapplus.entidades.FRAP;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class listafrapadapter extends RecyclerView.Adapter<listafrapadapter.frapViewHolder> {
    //Constructor para recibir valores del arreglo
    ArrayList<FRAP> listafrap ;

    ArrayList<FRAP> listaOriginal;
    public listafrapadapter(ArrayList<FRAP> listafrap){

        this.listafrap = listafrap;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listafrap);
    }

    @NonNull
    @Override
    public frapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_frap,null,false);
        return new frapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull frapViewHolder holder, int position) {

        String id = String.valueOf(listafrap.get(position).getId()); // Convertir a cadena si es necesario
        holder.viewID.setText(id);

        String status = String.valueOf(listafrap.get(position).getStatus()); // Convertir a cadena si es necesario
        holder.viewStatus1.setText(status);

    }
//Tamaño de la lista-
    @Override
    public int getItemCount() {
       return listafrap.size();
    }

    public class frapViewHolder extends RecyclerView.ViewHolder {

  //Identificar los elementos
        TextView viewID,viewStatus1;


        public frapViewHolder(@NonNull View itemView) {
            super(itemView);

            viewID = itemView.findViewById(R.id.ViewID);
            viewStatus1 = itemView.findViewById(R.id.ViewStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //trae el contexto
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("id", listafrap.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });


        }
    }
    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listafrap.clear();
            listafrap.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<FRAP> collecion = listafrap.stream()
                        .filter(i -> String.valueOf(i.getId()).contains(txtBuscar))
                        .collect(Collectors.toList());
                listafrap.clear();
                listafrap.addAll(collecion);
            } else {
                for (FRAP c : listaOriginal) {
                    if (String.valueOf(c.getId()).contains(txtBuscar)) {
                        listafrap.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}
