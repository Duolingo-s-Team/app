package com.duolingo.client;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CursFragment extends Fragment {


    ArrayList<Category> arrayCategoryCurs1;
    ArrayList<Category> arrayCategoryCurs2 ;
    ArrayList<Category> datosAuxCurso = new ArrayList<>();
    RecyclerView recycler;
    Course course1;
    Course course;
    ArrayList<Course> arrayCourse;
    ArrayList<Course> arrayCourseIniciat;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_curs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        arrayCategoryCurs1 = new ArrayList<>();
        arrayCategoryCurs2 = new ArrayList<>();
        arrayCourse=new ArrayList<Course>();
        arrayCourseIniciat=new ArrayList<Course>();

        llenarDatos();
        llenarCourse();


        recycler = view.findViewById(R.id.RecyclerId);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

        Spinner spinnerCursosTotales= view.findViewById(R.id.spinnerCursosTotals);
        Spinner spinnerCursosIniciados= view.findViewById(R.id.spinnerCursosIniciats);


        ArrayAdapter<Course> adapterCT = new ArrayAdapter<Course>(this.getContext(), android.R.layout.simple_spinner_item, arrayCourse);
        spinnerCursosTotales.setAdapter(adapterCT);

        ArrayAdapter<Course> adapterCI= new ArrayAdapter<Course>(this.getContext(),android.R.layout.simple_spinner_item, arrayCourseIniciat);
        spinnerCursosIniciados.setAdapter(adapterCI);

        //detecta cuando selecciono algo nuevo en el spinner
         spinnerCursosTotales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if(position!=0){
                    adapterCI.add(adapterCT.getItem(position));
                    spinnerCursosIniciados.setAdapter(adapterCI);
                    Course object = adapterCT.getItem(position);
                    adapterCT.remove(object);
                    spinnerCursosTotales.setAdapter(adapterCT);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerCursosIniciados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


                if(position!=0) {
                    Category c;
                    datosAuxCurso.clear();
                    Course cursActual = adapterCI.getItem(position);
                    List<Category> categories = cursActual.getCategories();


                    for (int i = 0; i < categories.size(); i++) {
                        c = categories.get(i);
                        datosAuxCurso.add(c);
                    }

                    AdapterDatos adapter = new AdapterDatos(datosAuxCurso);
                    recycler.setAdapter(adapter);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void llenarDatos() {

        ArrayList<Exercise>exercises = new ArrayList<Exercise>();
        ArrayList<Exercise>exercises1 = new ArrayList<Exercise>();
        Exercise e1=new Exercise("e1", 5,"{\n" +
                "    \"Exercise_Type\": \"TIPUS_TEST\",\n" +
                "    \"exersiceExp\": 50,\n" +
                "    \"exersiceCoins\": 10,\n" +
                "    \"sentenceToTranslate\": \"My name is Paco\",\n" +
                "    \"Correct_Answer\": \"Mi nombre es Paco\",\n" +
                "    \"Wrong_Answers\": [\n" +
                "        \"El mio nombre es Paco\",\n" +
                "        \"Mi llamo Paco\"\n" +
                "    ]\n" +
                "}");
        Exercise e2=new Exercise("e2", 5,"{\n" +
                "    \"Exercise_Type\": \"TIPUS_TEST\",\n" +
                "    \"exersiceExp\": 50,\n "+
                "    \"exersiceCoins\": 10,\n" +
                "    \"sentenceToTranslate\": \"Hello\",\n" +
                "    \"Correct_Answer\": \"Hola\",\n" +
                "    \"Wrong_Answers\": [\n" +
                "        \"Adios\",\n" +
                "        \"Sayonara\"\n" +
                "    ]\n" +
                "}");
        Exercise e3=new Exercise("e3", 5,"");
        Exercise e4=new Exercise("e4", 5);
        Exercise e5=new Exercise("e5", 5);
        exercises.add(e1);
        exercises.add(e2);
        //exercises.add(e3);
        //exercises.add(e4);
        //exercises.add(e5);
        exercises1.add(e1);
        exercises1.add(e2);
        Category c1 = new Category("Mascotas");
        Level l1 = new Level("Level 1", true);
        Level l2 = new Level("Level 2", true);
        Level l3 = new Level("Level 3", false);
        l3.setExercises(exercises);
        Level l4 = new Level("Level 4", false);
        ArrayList<Level> l = new ArrayList<>();
        l.add(l1); l.add(l2); l.add(l3); l.add(l4);
        c1.setLevels(l);

        Category c2 = new Category("Viajes");
        Level l5 = new Level("Level 1.1", true);
        Level l6 = new Level("Level 1.2", false);
        l6.setExercises(exercises1);
        Level l7 = new Level("Level 1.3", false);
        Level l8 = new Level("Level 1.4", false);
        ArrayList<Level> lev2=new ArrayList<>();
        lev2.add(l5); lev2.add(l6); lev2.add(l7); lev2.add(l8);
        c2.setLevels(lev2);

        arrayCategoryCurs1.add(c1); arrayCategoryCurs1.add(c2);


        Category c21 = new Category("Comida");
        Level l21 = new Level("Level 5", true);
        Level l22 = new Level("Level 6", true);
        Level l23 = new Level("Level 7", false);
        l23.setExercises(exercises);
        Level l24 = new Level("Level 8", false);
        ArrayList<Level> lev21 = new ArrayList<>();
        lev21.add(l1); lev21.add(l2); lev21.add(l3); lev21.add(l4);
        c21.setLevels(lev21);

        Category c22 = new Category("Texto");
        Level l25 = new Level("Level 5.1", true);
        Level l26 = new Level("Level 6.2", true);
        Level l27 = new Level("Level 7.3", true);
        Level l28 = new Level("Level 8.4", false);
        l28.setExercises(exercises);
        ArrayList<Level> lev22=new ArrayList<>();
        lev22.add(l25); lev22.add(l26); lev22.add(l27); lev22.add(l28);
        c22.setLevels(lev22);

        arrayCategoryCurs2.add(c21); arrayCategoryCurs2.add(c22);

        course=new Course("Ingles",arrayCategoryCurs2);
        course1=new Course("Japones",arrayCategoryCurs1);
    }

    public void llenarCourse(){

        Course c=new Course("cursos totales",arrayCategoryCurs1);
        Course c1=new Course("cursos iniciados",arrayCategoryCurs1);
        arrayCourse.add(c);
        arrayCourse.add(course);
        arrayCourse.add(course1);
        arrayCourseIniciat.add(c1);
    }

}