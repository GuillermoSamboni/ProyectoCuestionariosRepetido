package com.cuestionarios.cuestionarioproyecto.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cuestionarios.cuestionarioproyecto.R;
import com.cuestionarios.cuestionarioproyecto.model.Cuestionarios;
import com.cuestionarios.cuestionarioproyecto.model.Preguntas;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterPregunta extends RecyclerView.Adapter<AdapterPregunta.ViewHolder> {

    private ArrayList<Preguntas> pregunta = new ArrayList<Preguntas>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Preguntas> getPreguntas() { return this.pregunta; }

    public void setPregunta(ArrayList<Preguntas> pregunta) {
        this.pregunta = pregunta;
    }


    @NonNull
    @NotNull
    @Override
    public AdapterPregunta.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuestionario, null, false);

        return new ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterPregunta.ViewHolder holder, int position) {
        if (!pregunta.get(position).getImagen_e().equals("")){
            Uri uri= (Uri.parse(pregunta.get(position).getImagen_e()));
            Glide.with(holder.img_e).load(pregunta.get(position).getImagen_e()).into(holder.img_e);
        }
        if (!pregunta.get(position).getImagen_m().equals("")){
            Uri uri= (Uri.parse(pregunta.get(position).getImagen_m()));
            Glide.with(holder.img_m).load(pregunta.get(position).getImagen_m()).into(holder.img_m);
        }
        holder.numeroPregunta.setText("P "+(position+1));

        holder.titulo.setText(pregunta.get(position).getTitulo());
        holder.contenido.setText(pregunta.get(position).getContenido());
        holder.opt1.setText(pregunta.get(position).getOp1());
        holder.opt2.setText(pregunta.get(position).getOp2());
        holder.opt3.setText(pregunta.get(position).getOp3());
        holder.opt4.setText(pregunta.get(position).getOp4());

    }

    @Override
    public int getItemCount() {

        return pregunta.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_e;
        ImageView img_m;
        TextView contenido;
        RadioButton opt1;
        RadioButton opt2;
        RadioButton opt3;
        RadioButton opt4;
        Context context;
        TextView titulo;
        TextView numeroPregunta;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            numeroPregunta=itemView.findViewById(R.id.idNumerorPregunta);
            titulo=itemView.findViewById(R.id.idTitulo);
            img_e=itemView.findViewById(R.id.idImg_e);
            img_m=itemView.findViewById(R.id.idImg_m);
            contenido=itemView.findViewById(R.id.idTxtContenido);
            opt1=itemView.findViewById(R.id.idOption1);
            opt2=itemView.findViewById(R.id.idOption2);
            opt3=itemView.findViewById(R.id.idOption3);
            opt4=itemView.findViewById(R.id.idOption4);

        }

    }
}
