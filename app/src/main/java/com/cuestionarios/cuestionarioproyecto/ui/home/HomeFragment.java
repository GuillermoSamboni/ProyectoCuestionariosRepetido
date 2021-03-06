package com.cuestionarios.cuestionarioproyecto.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuestionarios.cuestionarioproyecto.adapter.AdapterCuestionario;
import com.cuestionarios.cuestionarioproyecto.databinding.FragmentHomeBinding;
import com.cuestionarios.cuestionarioproyecto.model.Cuestionarios;
import com.cuestionarios.cuestionarioproyecto.model.Preguntas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HomeFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Preguntas> listaPreguntasFiltrado = new ArrayList<>();
    AdapterCuestionario adapterCuestionario = new AdapterCuestionario();

    private HomeViewModel homeViewModel;

    private FragmentHomeBinding binding;

    ArrayList<Preguntas> listaPreguntasMatematicas = new ArrayList<>();
    ArrayList<Preguntas> listaPreguntasLiteratura = new ArrayList<>();
    ArrayList<Preguntas> listaPreguntasIngles = new ArrayList<>();
    ArrayList<Preguntas> listaPreguntasSociales = new ArrayList<>();
    ArrayList<Preguntas> listaPreguntasCiencias = new ArrayList<>();
    ArrayList<Preguntas> listaFinal = new ArrayList<Preguntas>();


    ArrayList<String> listaRespuestas = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listarCuestionarios();

        binding.idBtnFloatGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                obtenerPreguntasPorMateria();

            }
        });


        return root;
    }


    public void obtenerPreguntasPorMateria() {

        /**
         * INICIO PREGUGTAS CIENCIAS
         */
        listaPreguntasMatematicas = new ArrayList<>();
        listaPreguntasCiencias = new ArrayList<>();
        listaPreguntasSociales = new ArrayList<>();
        listaPreguntasLiteratura = new ArrayList<>();
        listaPreguntasIngles = new ArrayList<>();

        listaFinal = new ArrayList<>();
        listaRespuestas = new ArrayList<>();

        db.collection("Preguntas").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Preguntas pregunmta = document.toObject(Preguntas.class);
                                switch (pregunmta.getTipo()) {
                                    case "matematicas":
                                        listaPreguntasMatematicas.add(pregunmta);
                                        break;

                                    case "literatura":
                                        listaPreguntasLiteratura.add(pregunmta);
                                        break;

                                    case "sociales":
                                        listaPreguntasSociales.add(pregunmta);
                                        break;

                                    case "ciencias":
                                        listaPreguntasCiencias.add(pregunmta);
                                        break;

                                    case "ingles":
                                        listaPreguntasIngles.add(pregunmta);
                                        break;
                                }
                                //randomPregunta(listaPreguntasCiencias);
                            }
                            Random preguntaRandom = new Random();
                            int valor = preguntaRandom.nextInt();

                            for (int i = 0; i < 2; i++) {
                                valor = preguntaRandom.nextInt(5);

                                listaFinal.add(listaPreguntasMatematicas.get(valor));
                                listaFinal.add(listaPreguntasLiteratura.get(valor));
                                listaFinal.add(listaPreguntasCiencias.get(valor));
                                listaFinal.add(listaPreguntasSociales.get(valor));
                                listaFinal.add(listaPreguntasIngles.get(valor));

                            }

                            for (int i = 0; i < listaFinal.size(); i++) {
                                listaRespuestas.add("");
                            }
                            Log.d("LilstaFinal", String.valueOf(listaFinal.size()));

                            Map<String, Object> city = new HashMap<>();
                            city.put("nombre", "NombreCuestionario");
                            city.put("correo", "correo@gmail.com");
                            city.put("fecha", "2131313");
                            city.put("pregunta", listaFinal);
                            city.put("respuestas", listaRespuestas);

                            db.collection("Cuestionarios").document().set(city)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("onSucces", "DocumentSnapshotsuccessfullywritten!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("onFailure", "Errorwritingdocument", e);
                                        }
                                    });
                        } else {
                            Toast.makeText(requireContext(), "Error obteniendo preguntas por tipo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * listamos cuiestionarios
     */
    public void listarCuestionarios() {
        ArrayList<Cuestionarios> listaCuestionarios = new ArrayList<>();

        db.collection("Cuestionarios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Cuestionarios cuestionario = document.toObject(Cuestionarios.class);
                        if (cuestionario.getCorreo().equals("correo@gmail.com")) {
                            cuestionario.setId(document.getId());
                            listaCuestionarios.add(cuestionario);
                            //Log.d("idDocument", document.getId());
                        }
                    }
                    Log.d("CuestionarioLista", String.valueOf(listaCuestionarios.size()));
                    LinearLayoutManager llm = new LinearLayoutManager(requireContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);


                    adapterCuestionario.setCuestionarios(listaCuestionarios);
                    adapterCuestionario.setContext(getContext());
                    binding.idRecyclerPreguntas.setLayoutManager(new LinearLayoutManager(requireContext()));
                    binding.idRecyclerPreguntas.setAdapter(adapterCuestionario);

                } else {
                    Toast.makeText(requireContext(), "No se pudo obetener la lista de cuestionarios", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(requireContext(), "No se pudo obetener la lista de cuestionarios", Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}