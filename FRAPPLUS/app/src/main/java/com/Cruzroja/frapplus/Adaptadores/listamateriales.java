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
import com.Cruzroja.frapplus.Vistas.VerInformeFrap;
import com.Cruzroja.frapplus.entidades.FRAPOrden;
import com.Cruzroja.frapplus.entidades.MaterialMedico;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class listamateriales extends RecyclerView.Adapter<listamateriales.frapMaterialesHolder> {

    List<MaterialMedico> listaMateriales;
    List<MaterialMedico> listaOriginal;

    private Context context; // Agrega esta variable de instancia

    public listamateriales(Context context, List<MaterialMedico> listaMateriales) {
        this.context = context; // Guarda el contexto
        this.listaMateriales = listaMateriales;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaMateriales);
    }


    @NonNull
    @Override
    public listamateriales.frapMaterialesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_lista_materiales_consumos,null,false);
        return new listamateriales.frapMaterialesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listamateriales.frapMaterialesHolder holder, int position) {

        String id = String.valueOf(listaMateriales.get(position).getId()); // Convertir a cadena si es necesario
        holder.viewID.setText(id);

        String Clave = String.valueOf(listaMateriales.get(position).getClaveFRAPOrden()); // Convertir a cadena si es necesario
        holder.viewClave.setText(Clave);

        String Material = String.valueOf(listaMateriales.get(position).getMaterial()); // Convertir a cadena si es necesario
        holder.viewMaterial.setText(Material);

        String Cantidad = String.valueOf(listaMateriales.get(position).getCantidad()); // Convertir a cadena si es necesario
        holder.viewCantidad.setText(Cantidad);



    }
    //Tamaño de la lista-
    @Override
    public int getItemCount() {
        return listaMateriales.size();
    }

    public class frapMaterialesHolder extends RecyclerView.ViewHolder {

        //Identificar los elementos

        TextView viewID,viewClave,viewMaterial,viewCantidad;


        public frapMaterialesHolder(@NonNull View itemView) {
            super(itemView);

            viewID = itemView.findViewById(R.id.ViewID);
            viewClave = itemView.findViewById(R.id.ViewClave);
            viewMaterial = itemView.findViewById(R.id.ViewMaterial);
            viewCantidad = itemView.findViewById(R.id.ViewCantidad);



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
                    intent.putExtra("id", listaMateriales.get(getAdapterPosition()).getId());

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
