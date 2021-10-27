package com.cuestionarios.cuestionarioproyecto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuestionarios.cuestionarioproyecto.R;
import com.cuestionarios.cuestionarioproyecto.model.Cuestionarios;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kotlin.jvm.internal.markers.KMutableList;

public class AdapterCuestionario extends RecyclerView.Adapter<AdapterCuestionario.ViewHolder> {

    private ArrayList<String> dataLis;

    public AdapterCuestionario(ArrayList<String> dataLis) {
        this.dataLis = dataLis;
    }


    @NonNull
    @NotNull
    @Override
    public AdapterCuestionario.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterCuestionario.ViewHolder holder, int position) {
        holder.asignarDatos(dataLis.get(position));
    }

    @Override
    public int getItemCount() {
        return dataLis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomCuestion;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nomCuestion=itemView.findViewById(R.id.idNombreVuestionario);
        }

        public void asignarDatos(String cuestionarios) {
            nomCuestion.setText(cuestionarios);
        }
    }

}
