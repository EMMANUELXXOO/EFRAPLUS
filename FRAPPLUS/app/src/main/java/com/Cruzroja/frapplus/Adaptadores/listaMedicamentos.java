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
import com.Cruzroja.frapplus.entidades.MaterialMedico;
import com.Cruzroja.frapplus.entidades.MedicamentosConsumos;

import java.util.ArrayList;
import java.util.List;

public class listaMedicamentos extends RecyclerView.Adapter<listaMedicamentos.frapMedicamentosHolder>{
    List<MedicamentosConsumos> listaMedicamentos;
    List<MedicamentosConsumos> listaOriginal;

    private Context context; // Agrega esta variable de instancia

    public listaMedicamentos(Context context, List<MedicamentosConsumos> listaMedicamentos) {
        this.context = context;
        this.listaMedicamentos = listaMedicamentos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaMedicamentos);
    }




    @NonNull
    @Override
    public listaMedicamentos.frapMedicamentosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_lista_medicamentos,null,false);
        return new listaMedicamentos.frapMedicamentosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listaMedicamentos.frapMedicamentosHolder holder, int position) {

        String id = String.valueOf(listaMedicamentos.get(position).getId()); // Convertir a cadena si es necesario
        holder.viewID.setText(id);

        String Clave = String.valueOf(listaMedicamentos.get(position).getClaveFRAPOrden()); // Convertir a cadena si es necesario
        holder.viewClave.setText(Clave);

        String HoraCaptura = String.valueOf(listaMedicamentos.get(position).getHoraCaptura()); // Convertir a cadena si es necesario
        holder.ViewHoraCaptura.setText(HoraCaptura);

        String Medicamento = String.valueOf(listaMedicamentos.get(position).getMedicamento()); // Convertir a cadena si es necesario
        holder.ViewMedicamento.setText(Medicamento);

        String Dosis = String.valueOf(listaMedicamentos.get(position).getDosis()); // Convertir a cadena si es necesario
        holder.ViewDosis.setText(Dosis);

        String Via = String.valueOf(listaMedicamentos.get(position).getVia()); // Convertir a cadena si es necesario
        holder.ViewVia.setText(Via);

        String TE = String.valueOf(listaMedicamentos.get(position).getTE()); // Convertir a cadena si es necesario
        holder.ViewTE.setText(TE);

    }
    //Tamaño de la lista-
    @Override
    public int getItemCount() {
        return listaMedicamentos.size();
    }

    public class frapMedicamentosHolder extends RecyclerView.ViewHolder {

        //Identificar los elementos

        TextView viewID,viewClave,ViewHoraCaptura,ViewMedicamento,ViewDosis,ViewVia,ViewTE;


        public frapMedicamentosHolder(@NonNull View itemView) {
            super(itemView);

            viewID = itemView.findViewById(R.id.ViewID);
            viewClave = itemView.findViewById(R.id.ViewClave);
            ViewHoraCaptura = itemView.findViewById(R.id.ViewHoraCaptura);
            ViewMedicamento = itemView.findViewById(R.id.ViewMedicamento);
            ViewDosis = itemView.findViewById(R.id.ViewDosis);
            ViewVia = itemView.findViewById(R.id.ViewVia);
            ViewTE = itemView.findViewById(R.id.ViewTE);
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
                    Intent intent = new Intent(context, MedicamentosConsumos.class);
                    intent.putExtra("id", listaMedicamentos.get(getAdapterPosition()).getId());

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
