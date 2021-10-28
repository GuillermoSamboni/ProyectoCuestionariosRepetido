package com.cuestionarios.cuestionarioproyecto.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuestionarios.cuestionarioproyecto.R;
import com.cuestionarios.cuestionarioproyecto.controller.CuestionarioActivity;
import com.cuestionarios.cuestionarioproyecto.model.Cuestionarios;
import com.cuestionarios.cuestionarioproyecto.model.Preguntas;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kotlin.jvm.internal.markers.KMutableList;

public class AdapterCuestionario extends RecyclerView.Adapter<AdapterCuestionario.ViewHolder> implements View.OnClickListener {

    private ArrayList<Cuestionarios> cuestionarios = new ArrayList<Cuestionarios>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setCuestionarios(ArrayList<Cuestionarios> listCuestionarios){
        this.cuestionarios = listCuestionarios;
    }



    @NonNull
    @NotNull
    @Override
    public AdapterCuestionario.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterCuestionario.ViewHolder holder, int position) {

        holder.nomCuestion.setText(cuestionarios.get(position).getNombre());

        holder.btnSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(context, cuestionarios.get(position).getId(), Toast.LENGTH_SHORT).show();

                Intent intentDetail=new Intent(context, CuestionarioActivity.class);
                intentDetail.putExtra("id_cuestionario",cuestionarios.get(position).getId());

                context.startActivity(intentDetail);

            }
        });
        holder.btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado(cuestionarios.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {

        return cuestionarios.size();

    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomCuestion;
        ImageButton btnSee;
        ImageButton btnRepit;
        ImageButton btnHistory;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nomCuestion=itemView.findViewById(R.id.idNombreVuestionario);
            btnSee=itemView.findViewById(R.id.idBtnSee);
            btnRepit=itemView.findViewById(R.id.idBtnRepit);
            btnHistory=itemView.findViewById(R.id.idBtnHistory);

        }
    }

    private void calcularResultado(Cuestionarios c){
        int buenas = 0;
        int malas = 0;

        if (!c.getRespuestas().get(0).equals("")) {
            for (int i = 0; i < c.getPregunta().size(); i++) {
                if (c.getPregunta().get(i).getRespuesta().equals(c.getRespuestas().get(i))) {
                    buenas++;
                } else {
                    malas++;
                }
            }
            //Calcular el puntaje de la prueba
            float puntaje = (buenas * 100 ) / c.getPregunta().size();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("Resultado de prueba");
            alertDialog.setMessage("Buenas: "+buenas+ "\n"
                    + "Malas: " + malas+ "\n"
                    + "Puntaje: " + puntaje+ "%");
            alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.create().show();
        } else {
            Toast.makeText(context, "Debe realizar la prueba", Toast.LENGTH_SHORT).show();
        }
        
    }

}
