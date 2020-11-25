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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class CursFragment extends Fragment {

    ArrayList<Category> datos;
    ArrayList<Category> c;
    ArrayList<String> categories_name;
    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_curs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinnerCursosTotales= view.findViewById(R.id.spinnerCursosTotals);
        Spinner spinnerCursosIniciados= view.findViewById(R.id.spinnerCursosIniciats);

        ArrayList<String> cursos=new ArrayList<String>();
        ArrayList<String> cursosIniciados=new ArrayList<String>();


        cursos.add("Cursos Totales");
        cursos.add("Ingles");
        cursos.add("Frances");
        cursos.add("Español");
        cursos.add("Japones");

        cursosIniciados.add("Cursos iniciados");

        ArrayAdapter<String> adapterCT=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, cursos);
        spinnerCursosTotales.setAdapter(adapterCT);

        ArrayAdapter<String> adapterCI=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, cursosIniciados);
        spinnerCursosIniciados.setAdapter(adapterCI);

        boolean cursoSelected=false;

        //quizas sea necesario un while


//        ArrayList<Category> list=new ArrayList<Category>();
//        Category cat1=new Category(1, "Animales");
//        Category cat2=new Category(2, "Plantas");
//        Category cat3=new Category(3, "Comercio");
//        Category cat4=new Category(4, "Patata");
//        Course c1=new Course(1, "Ingles", list);
//
//        list.add(cat1);
//        list.add(cat2);
//        list.add(cat3);
//        list.add(cat4);
//
//        for (Category category:list) {
//
//            categories_name.add(category.getCategory_name());
//
//        }

        //categories_name = c1.getCategories();


    }




}