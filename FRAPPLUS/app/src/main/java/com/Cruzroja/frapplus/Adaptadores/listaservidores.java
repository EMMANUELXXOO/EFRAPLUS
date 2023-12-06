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
import com.Cruzroja.frapplus.VerInformeServidor;
import com.Cruzroja.frapplus.Vistas.VerInformeFrap;
import com.Cruzroja.frapplus.entidades.DatosSERVIDORES;
import com.Cruzroja.frapplus.entidades.MaterialMedico;

import java.util.ArrayList;
import java.util.List;

public class listaservidores extends RecyclerView.Adapter<listaservidores.frapServidoresHolder>{

    List<DatosSERVIDORES> listaservidores;
    List<DatosSERVIDORES> listaOriginal;

    private Context context; // Agrega esta variable de instancia

    public listaservidores(Context context, List<DatosSERVIDORES> listaServidores) {
        this.context = context; // Guarda el contexto
        this.listaservidores = listaServidores;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaservidores);
    }


    @NonNull
    @Override
    public listaservidores.frapServidoresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_itemlistaservidores,null,false);
        return new listaservidores.frapServidoresHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listaservidores.frapServidoresHolder holder, int position) {

        String id = String.valueOf(listaservidores.get(position).getId()); // Convertir a cadena si es necesario
        holder.viewID.setText(id);

        String Servidor = String.valueOf(listaservidores.get(position).getURLSERVIDOR()); // Convertir a cadena si es necesario
        holder.ViewSERVIDOR.setText(Servidor);

        String Usuario = String.valueOf(listaservidores.get(position).getUSUARIO()); // Convertir a cadena si es necesario
        holder.ViewUSUARIO.setText(Usuario);

        String Contraseña = String.valueOf(listaservidores.get(position).getPASSWORD()); // Convertir a cadena si es necesario
        holder.ViewCONTRASEÑA.setText(Contraseña);



    }
    //Tamaño de la lista-
    @Override
    public int getItemCount() {
        return listaservidores.size();
    }

    public class frapServidoresHolder extends RecyclerView.ViewHolder {

        //Identificar los elementos

        TextView viewID,ViewSERVIDOR,ViewUSUARIO,ViewCONTRASEÑA;


        public frapServidoresHolder(@NonNull View itemView) {
            super(itemView);

            viewID = itemView.findViewById(R.id.ViewID);
            ViewSERVIDOR = itemView.findViewById(R.id.ViewSERVIDOR);
            ViewUSUARIO = itemView.findViewById(R.id.ViewUSUARIO);
            ViewCONTRASEÑA = itemView.findViewById(R.id.ViewCONTRASEÑA);



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
                    Intent intent = new Intent(context, VerInformeServidor.class);
                    intent.putExtra("id", listaservidores.get(getAdapterPosition()).getId());
                    // Inicia la nueva actividad y aplica la transición personalizada
                    context.startActivity(intent);
                    if (context instanceof Activity) {
                        ((Activity) context).overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    }
                }
            });

        }
    }
}
