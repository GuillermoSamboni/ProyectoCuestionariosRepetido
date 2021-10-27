package com.cuestionarios.cuestionarioproyecto.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuestionarios.cuestionarioproyecto.R;
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
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Preguntas> listaPreguntasFiltrado = new ArrayList<>();


    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    ArrayList<String> listaCuestionarios;

    ArrayList<Preguntas> listaPreguntasMatematicas = new ArrayList<>();
    ArrayList<Preguntas> listaPreguntasLiteratura = new ArrayList<>();
    ArrayList<Preguntas> listaPreguntasIngles = new ArrayList<>();
    ArrayList<Preguntas> listaPreguntasSociales = new ArrayList<>();
    ArrayList<Preguntas> listaPreguntasCiencias = new ArrayList<>();



    RecyclerView recyclerView;
    AdapterCuestionario adapterCuestionario;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.idRecyclerPreguntas.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        listaCuestionarios = new ArrayList<String>();

        for (int i = 0; i <= 10; i++) {
            listaCuestionarios.add("cuestionario" + i + "cue1");
        }
        adapterCuestionario = new AdapterCuestionario(listaCuestionarios);
        binding.idRecyclerPreguntas.setAdapter(adapterCuestionario);


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
         * INICIO PREGUGTAS MATEMATICAS
         */
        db.collection("Preguntas").whereEqualTo("tipo", "matematicas").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Preguntas pregunmta = document.toObject(Preguntas.class);
                                listaPreguntasMatematicas.add(pregunmta);
                            }
                            Log.d("listaMatematicas", listaPreguntasMatematicas.toString());
                        } else {
                            Toast.makeText(requireContext(), "Error obteniendo preguntas por tipo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        /**
         * INICIO PREGUGTAS LITERATURA
         */
        db.collection("Preguntas").whereEqualTo("tipo", "literatura").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Preguntas pregunmta = document.toObject(Preguntas.class);
                                listaPreguntasLiteratura.add(pregunmta);
                            }
                            Log.d("listaLiteratura", listaPreguntasLiteratura.toString());
                        } else {
                            Toast.makeText(requireContext(), "Error obteniendo preguntas por tipo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        /**
         * INICIO INGLES
         */
        db.collection("Preguntas").whereEqualTo("tipo", "ingles").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Preguntas pregunmta = document.toObject(Preguntas.class);
                                listaPreguntasIngles.add(pregunmta);
                            }
                            Log.d("listaIngles", listaPreguntasIngles.toString());
                        } else {
                            Toast.makeText(requireContext(), "Error obteniendo preguntas por tipo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        /**
         * INICIO PREGUGTAS SOCIALES
         */
        db.collection("Preguntas").whereEqualTo("tipo", "sociales").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Preguntas pregunmta = document.toObject(Preguntas.class);
                                listaPreguntasSociales.add(pregunmta);
                            }
                            Log.d("listasociales", listaPreguntasSociales.toString());
                        } else {
                            Toast.makeText(requireContext(), "Error obteniendo preguntas por tipo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        /**
         * INICIO PREGUGTAS CIENCIAS
         */
        db.collection("Preguntas").whereEqualTo("tipo", "ciencias").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Preguntas pregunmta = document.toObject(Preguntas.class);
                                listaPreguntasCiencias.add(pregunmta);
                            }
                            Log.d("listaCiencias", listaPreguntasCiencias.toString());
                        } else {
                            Toast.makeText(requireContext(), "Error obteniendo preguntas por tipo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void randomPregunta(){

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}