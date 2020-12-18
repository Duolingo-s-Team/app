package com.duolingo.client;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duolingo.client.rmi.models.Category;
import com.duolingo.client.rmi.models.Exercise;
import com.duolingo.client.rmi.models.Level;

import org.json.JSONException;
import org.json.JSONObject;

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

                Level level = lastLevel(getMockupLevels());
                //condicion if del tipo de ejercicio, puede ser test o traduccion, dependiendo de eso se abre un intent con una clase u otra
                ArrayList<Exercise> arrayExercises = (ArrayList<Exercise>) level.getExercises();
                JSONObject json= null;
                try {
                    json = new JSONObject(arrayExercises.get(0).getContent());
                    if(String.valueOf(json.get("Exercise_Type")).equals("TIPUS_TEST")) {

                            intent = new Intent(v.getContext(), ExerciseTestActivity.class);
                            intent.putExtra("lvl", level);
                            intent.putExtra("pos", 0);
                            intent.putExtra("error", false);
                            v.getContext().startActivity(intent);
                    }else{

                        intent = new Intent(v.getContext(), ExerciseOpenTranslationActivity.class);
                        intent.putExtra("lvl", level);
                        intent.putExtra("pos", 0);
                        intent.putExtra("error", false);
                        v.getContext().startActivity(intent);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
            if(!l.isComplete()){
              return l;
            }
        }
        return null;
    }

    public List<Level> getMockupLevels() {
        List<Level> levels = new ArrayList<>();

        for (int i = 1; i <= Math.random()*3+2; i++) {
            Level lvl =new Level("Level " + i);
            lvl.addExercise(new Exercise("Saludos 1", "{\"Exercise_Type\":\"TIPUS_TEST\", \"exerciseExp\": 25, \"exerciseCoins\": 10,\"sentenceToTranslate\":\"Hello, my name is Jason\",\"Correct_Answer\":\"Hola, me llamo Jason\",\"Wrong_Answers\":[\"Hola, Jason me llamo yo\",\"Hola, mi nombre me llamo Jason\"]}"));
            lvl.addExercise(new Exercise("Saludos 2", "{\"Exercise_Type\":\"TIPUS_TRADUCCIO_OBERTA\", \"exerciseExp\": 50, \"exerciseCoins\": 20,\"sentenceToTranslate\":\"Hello world\",\"Correct_Answer\":[\"Hola mundo\"]}"));
            lvl.addExercise(new Exercise("Saludos 3", "{\"Exercise_Type\":\"TIPUS_TRADUCCIO_OBERTA\", \"exerciseExp\": 50, \"exerciseCoins\": 20,\"sentenceToTranslate\":\"Hello world\",\"Correct_Answer\":[\"Hola mundo\"]}"));
            lvl.addExercise(new Exercise("Saludos 4", "{\"Exercise_Type\":\"TIPUS_TEST\", \"exerciseExp\": 25, \"exerciseCoins\": 10,\"sentenceToTranslate\":\"Hello, my name is Dani\",\"Correct_Answer\":\"Hola, me llamo Dani\",\"Wrong_Answers\":[\"Hola, Dani me llamo yo\",\"Hola, mi nombre me llamo Dani\"]}"));
            levels.add(lvl);
        }

        return levels;
    }

}