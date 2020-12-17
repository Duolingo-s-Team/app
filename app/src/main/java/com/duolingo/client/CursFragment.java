package com.duolingo.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duolingo.client.rmi.ClienteRMI;
import com.duolingo.client.rmi.models.Category;
import com.duolingo.client.rmi.models.Course;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class CursFragment extends Fragment {

    RecyclerView recycler;

    public ArrayList<Category> datosAuxCurso = new ArrayList<>();
    public static ArrayList<Course> arrayCourse = new ArrayList<>();

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

        arrayCourse.clear();
        arrayCourse.add(new Course("Loading", "Please wait..."));

        ArrayList<Course> courses = connection();

        if (courses.size() > 1) {
            arrayCourse = courses;
        } else {
            Toast.makeText(getContext(), "Conexion fallida", Toast.LENGTH_SHORT);
        }

        arrayCourseIniciat = new ArrayList<>();
        arrayCourseIniciat.add(new Course("Cursos", "Iniciados"));

        recycler = view.findViewById(R.id.RecyclerId);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

        Spinner spinnerCursosTotales= view.findViewById(R.id.spinnerCursosTotals);
        Spinner spinnerCursosIniciados= view.findViewById(R.id.spinnerCursosIniciats);

        ArrayAdapter<Course> adapterCT = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, arrayCourse);
        spinnerCursosTotales.setAdapter(adapterCT);

        ArrayAdapter<Course> adapterCI= new ArrayAdapter<>(this.getContext(),android.R.layout.simple_spinner_item, arrayCourseIniciat);
        spinnerCursosIniciados.setAdapter(adapterCI);

        // detecta cuando selecciono algo nuevo en el spinner
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
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        spinnerCursosIniciados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if(position!=0) {
                    Category c;
                    datosAuxCurso.clear();
                    List<Category> categories = getMockupCategories();

                    for (int i = 0; i < categories.size(); i++) {
                        c = categories.get(i);
                        datosAuxCurso.add(c);
                    }

                    AdapterDatos adapter = new AdapterDatos(datosAuxCurso);
                    recycler.setAdapter(adapter);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

    }


    public ArrayList<Course> parseToCourse(ArrayList<String> courseNames) {
        ArrayList<Course> courses = new ArrayList<>();

        if (courseNames != null) {
            Scanner s;
            for (String i : courseNames) {
                s = new Scanner(i);
                courses.add(
                        new Course(
                                Long.parseLong(s.next()),
                                s.next(),
                                s.next(),
                                Boolean.valueOf(s.next())));
                s.close();
            }
        }
        return courses;
    }

    public List<Category> getMockupCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Familia"));
        categories.add(new Category("Animales"));
        categories.add(new Category("Comida"));
        categories.add(new Category("Saludos I"));
        categories.add(new Category("Saludos II"));

        return categories;
    }

    public ArrayList<Course> connection(){
        ArrayList<Course> aux=new ArrayList<>();
        aux.add(new Course("Cursos","Totales"));
        try {
            aux.addAll(parseToCourse(new ClienteRMI().execute().get(10, TimeUnit.SECONDS)));
            return aux;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("Time out");
        }
        return aux;
    }

}