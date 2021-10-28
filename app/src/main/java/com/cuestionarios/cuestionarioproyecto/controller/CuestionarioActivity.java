package com.cuestionarios.cuestionarioproyecto.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cuestionarios.cuestionarioproyecto.R;
import com.cuestionarios.cuestionarioproyecto.adapter.AdapterPregunta;
import com.cuestionarios.cuestionarioproyecto.model.Cuestionarios;
import com.cuestionarios.cuestionarioproyecto.model.Preguntas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.WriteResult;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CuestionarioActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    AdapterPregunta adapterPregunta = new AdapterPregunta();
    Cuestionarios cuestionario = new Cuestionarios();
    ArrayList<Preguntas> preguntas = new ArrayList<>();


    RadioGroup radioGroup;

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
    Button btnSiguiente;
    Button btnFinalizar;
    int numPregunta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);

        Bundle bundleExtra = this.getIntent().getExtras();
        String idExtra = bundleExtra.getString("id_cuestionario");
        Log.d("BundleExtra", idExtra.toString());
        //idRecyclerPreguntas = findViewById(R.id.idRecyclerCuestionario);
        obtenerCuestionario(idExtra);

        //Inicializamos las variables
        numeroPregunta = findViewById(R.id.idNumerorPreguntaC);
        titulo = findViewById(R.id.idTituloC);
        img_e = findViewById(R.id.idImg_eC);
        img_m = findViewById(R.id.idImg_mC);
        contenido = findViewById(R.id.idTxtContenidoC);
        opt1 = findViewById(R.id.idOption1C);
        opt2 = findViewById(R.id.idOption2C);
        opt3 = findViewById(R.id.idOption3C);
        opt4 = findViewById(R.id.idOption4C);
        btnSiguiente = findViewById(R.id.btnSiguientePregunta);
        btnFinalizar = findViewById(R.id.btnFinalizarCuestionario);
        btnSiguiente.setVisibility(View.VISIBLE);
        btnFinalizar.setVisibility(View.INVISIBLE);
        radioGroup = findViewById(R.id.idRadipGroupC);
        numPregunta = 0;

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguientePregunta(idExtra);
            }
        });

    }


    private void obtenerCuestionario(String idCuestionario) {
        cuestionario = new Cuestionarios();
        preguntas = new ArrayList<>();
        db.collection("Cuestionarios").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getId().equals(idCuestionario)) {
                                    cuestionario = document.toObject(Cuestionarios.class);
                                    cuestionario.setId(document.getId());

                                }
                            }
                        }
                        //Mostrar las preguntas
                        adapterPregunta.setPregunta(cuestionario.getPregunta());
                        adapterPregunta.setContext(CuestionarioActivity.this);
                        //Toast.makeText(context, "-->"+cuestionario.getPregunta().size(), Toast.LENGTH_SHORT).show();
                        //idRecyclerPreguntas.setLayoutManager(new LinearLayoutManager(CuestionarioActivity.this));
                        //idRecyclerPreguntas.setAdapter(adapterPregunta);
                        if (cuestionario.getPregunta().size() > 0) {
                            setearPregunta(cuestionario.getPregunta().get(0),numPregunta);
                        }
                        //Toast.makeText(CuestionarioActivity.this, "-->"+adapterPregunta.getPreguntas().size(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                    }
                });

    }

    private void siguientePregunta(String idCuestionario){
        cuestionario = new Cuestionarios();
        preguntas = new ArrayList<>();

        db.collection("Cuestionarios").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        String respuesta = "";
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getId().equals(idCuestionario)) {
                                    cuestionario = document.toObject(Cuestionarios.class);
                                    cuestionario.setId(document.getId());
                                }
                            }
                        }
                        numPregunta++;

                        if (cuestionario.getPregunta().size() > 0) {
                            if (numPregunta < cuestionario.getPregunta().size()) {
                                //Agregamos la respuesta
                                Boolean continuar = true;
                                if(opt1.isChecked()){
                                    respuesta = opt1.getText().toString();
                                } else if (opt2.isChecked()){
                                    respuesta = opt1.getText().toString();
                                } else if (opt3.isChecked()) {
                                    respuesta = opt1.getText().toString();
                                } else if(opt4.isChecked()) {
                                    respuesta = opt1.getText().toString();
                                } else {
                                    continuar = false;
                                    Toast.makeText(CuestionarioActivity.this, "Debes seleccionar una respuesta", Toast.LENGTH_SHORT).show();
                                }
                                //Se valida si se puede continuar o debe seleccionar una respuesta
                                if (continuar) {
                                    cuestionario.getRespuestas().set(numPregunta,respuesta);
                                    Toast.makeText(CuestionarioActivity.this, cuestionario.getRespuestas().get(numPregunta), Toast.LENGTH_SHORT).show();

                                    // Update an existing document
                                    DocumentReference docRef = db.collection("Cuetionarios").document(cuestionario.getId());

                                    // (async) Update one field
                                    docRef.update("respuestas", cuestionario.getRespuestas());


                                    setearPregunta(cuestionario.getPregunta().get(numPregunta), numPregunta);
                                } else {
                                    numPregunta--;
                                }
                                if (numPregunta == cuestionario.getPregunta().size()-1) {
                                    btnSiguiente.setVisibility(View.INVISIBLE);
                                    btnFinalizar.setVisibility(View.VISIBLE);
                                }
                            }

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                    }
                });
    }
    /*Agrega la información de una pregunta en los campos para que el usuario
    pueda visualizarla
    * */
    private void setearPregunta(Preguntas p, int numPregunta){
        opt1.setChecked(false);
        opt2.setChecked(false);
        opt3.setChecked(false);
        opt4.setChecked(false);

        if (!p.getImagen_e().equals("")){
            Glide.with(img_e).load(p.getImagen_e()).into(img_e);
            img_e.setVisibility(View.VISIBLE);
        } else {
            img_e.setVisibility(View.INVISIBLE);
        }
        if (!p.equals("")){
            Glide.with(img_m).load(p.getImagen_m()).into(img_m);
            img_m.setVisibility(View.VISIBLE);
        } else {
            img_m.setVisibility(View.INVISIBLE);
        }

        titulo.setText(p.getTitulo());
        contenido.setText(p.getContenido());
        opt1.setText(p.getOp1());
        opt4.setText(p.getOp4());
        opt2.setText(p.getOp2());
        opt3.setText(p.getOp3());
        numeroPregunta.setText("P "+(numPregunta+1));

    }

}