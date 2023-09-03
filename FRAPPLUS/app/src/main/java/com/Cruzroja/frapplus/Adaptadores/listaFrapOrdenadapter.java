package com.Cruzroja.frapplus.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Cruzroja.frapplus.R;
import com.Cruzroja.frapplus.VerActivity;
import com.Cruzroja.frapplus.Vistas.VerInformeFrap;
import com.Cruzroja.frapplus.entidades.FRAP;
import com.Cruzroja.frapplus.entidades.FRAPOrden;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class listaFrapOrdenadapter extends RecyclerView.Adapter<listaFrapOrdenadapter.frapOrderViewHolder>{

    List<FRAPOrden> listaFrapOrden;
    List<FRAPOrden> listaOriginal;

    private Context context; // Agrega esta variable de instancia

    public listaFrapOrdenadapter(Context context, List<FRAPOrden> listaFrapOrden) {
        this.context = context; // Guarda el contexto
        this.listaFrapOrden = listaFrapOrden;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaFrapOrden);
    }


    @NonNull
    @Override
    public listaFrapOrdenadapter.frapOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_fraporden,null,false);
        return new listaFrapOrdenadapter.frapOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listaFrapOrdenadapter.frapOrderViewHolder holder, int position) {

        String id = String.valueOf(listaFrapOrden.get(position).getId()); // Convertir a cadena si es necesario
        holder.viewID.setText(id);

        String Clave = String.valueOf(listaFrapOrden.get(position).getClave()); // Convertir a cadena si es necesario
        holder.viewClave.setText(Clave);

        String status = String.valueOf(listaFrapOrden.get(position).getStatus()); // Convertir a cadena si es necesario
        holder.viewStatus1.setText(status);


        if (holder.viewStatus1.getText().toString().equals("CANCELADO")) {
            // El texto en viewStatus1 es igual a "CANCELADO"
            // Realiza las acciones necesarias aquí
            holder.viewStatus1.setTextColor(context.getResources().getColor(R.color.red));
        }
        if (holder.viewStatus1.getText().toString().equals("PENDIENTE")) {
            // El texto en viewStatus1 es igual a "CANCELADO"
            // Realiza las acciones necesarias aquí
            holder.viewStatus1.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }
        if (holder.viewStatus1.getText().toString().equals("COMPLETADO")) {
            // El texto en viewStatus1 es igual a "CANCELADO"
            // Realiza las acciones necesarias aquí
            holder.viewStatus1.setTextColor(context.getResources().getColor(R.color.green));
        }
        String FechaCreacion = String.valueOf(listaFrapOrden.get(position).getFechaCreacion()); // Convertir a cadena si es necesario
        holder.viewFechaCreacion.setText(FechaCreacion);

        String FechaModificacion = String.valueOf(listaFrapOrden.get(position).getFechadeMoficacion()); // Convertir a cadena si es necesario
        holder.viewFechaModificacion.setText(FechaModificacion);

    }
    //Tamaño de la lista-
    @Override
    public int getItemCount() {
        return listaFrapOrden.size();
    }

    public class frapOrderViewHolder extends RecyclerView.ViewHolder {

        //Identificar los elementos

        TextView viewID,viewClave,viewStatus1,viewFechaCreacion,viewFechaModificacion;


        public frapOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            viewID = itemView.findViewById(R.id.ViewID);
            viewClave = itemView.findViewById(R.id.ViewClave);
            viewStatus1 = itemView.findViewById(R.id.ViewStatuse);
            viewFechaCreacion = itemView.findViewById(R.id.ViewFechaCreacion);
            viewFechaModificacion = itemView.findViewById(R.id.ViewFechaModificacion);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Obtén una instancia del Vibrator
                    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

                    // Verifica si el dispositivo admite la vibración
                    if (vibrator != null && vibrator.hasVibrator()) {
                        // Realiza una vibración corta al hacer clic en el botón
                        vibrator.vibrate(100); // 100 milisegundos de vibración
                    }

                    // Trae el contexto
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerInformeFrap.class);
                    intent.putExtra("id", listaFrapOrden.get(getAdapterPosition()).getId());

                    // Inicia la nueva actividad y aplica la transición personalizada
                    context.startActivity(intent);
                    if (context instanceof Activity) {
                        ((Activity) context).overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    }
                }
            });



        }
    }
    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaFrapOrden.clear();
            listaFrapOrden.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<FRAPOrden> collecion = listaFrapOrden.stream()
                        .filter(i -> String.valueOf(i.getId()).equals(txtBuscar))
                        .collect(Collectors.toList());
                listaFrapOrden.clear();
                listaFrapOrden.addAll(collecion);
            } else {
                listaFrapOrden.clear();
                for (FRAPOrden c : listaOriginal) {
                    if (String.valueOf(c.getId()).equals(txtBuscar)) {
                        listaFrapOrden.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


}
