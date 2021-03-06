package com.duolingo.client;

import android.content.*;
import android.os.Bundle;
import android.os.Parcelable;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import  java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {
    Intent intent;
    public static ArrayList<Exercise> exercises;

    ArrayList<Category> arrayCategory;

    public AdapterDatos(ArrayList<Category> datos) {
        this.arrayCategory = datos;
    }
    public AdapterDatos() {

    }
    @Override
    public AdapterDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new ViewHolderDatos(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterDatos.ViewHolderDatos holder, int position) {

        holder.asignarDatos(arrayCategory.get(position).getCategory_name(), levelCounter(arrayCategory.get(position).getLevels()));
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Level level=lastLevel(arrayCategory.get(position).getLevels());
                intent=new Intent(v.getContext(),ExerciseActivity.class);
                intent.putExtra("level",level);
                intent.putExtra("pos",0);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayCategory.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder{

        Button b;
        TextView tv;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.levelsTextView);
            b = (Button) itemView.findViewById(R.id.categoryButton);

        }

        void asignarDatos(String s, int completed) {
            b.setText(s);
            tv.setText(String.valueOf(completed));
        }
    }

    // Funcion que devuelve el total niveles completados
    public int levelCounter(List<Level> datos){
        int num=0;
        for (Level l : datos) {
            Log.v("Level name: ", l.getLevel_name());
            if(l.isComplete()){
                num++;
            } else {
                break;
            }
        }
        return num;
        }

    public Level lastLevel(List<Level> datos){

        for (Level l : datos) {
            Log.v("Level name: ", l.getLevel_name());
            if(!l.isComplete()){
              return l;
            }
        }
        return null;
    }

}