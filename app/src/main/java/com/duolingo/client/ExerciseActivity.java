package com.duolingo.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.duolingo.client.rmi.models.Exercise;
import com.duolingo.client.rmi.models.Level;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {
    AdapterDatos ad=new AdapterDatos();
    ArrayList<Exercise> arrayExercises;
    int pos;
    Level lvl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        TextView title=findViewById(R.id.textTitleExercise);
        Button b=findViewById(R.id.button);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {

               pos=extras.getInt("pos");
               lvl= (Level) extras.getSerializable("level");
               arrayExercises= (ArrayList<Exercise>) getMockupExercises(); // <---------------------------------------------------------------
            }
        }

        Exercise e=arrayExercises.get(pos);
        title.setText(e.getExercise_name());
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pos==arrayExercises.size()-1){
                    Intent intent=new Intent(v.getContext(),MainActivity.class);
                    startActivity(intent);
                    //aqui cambiaremos el estado del nivel a completado en la base de datos
                }else{
                    pos=pos+1;
                    finish();
                    getIntent().putExtra("pos",pos);
                    startActivity(getIntent());
                }

            }
        });

    }

    public List<Exercise> getMockupExercises() {
        List<Exercise> exercises = new ArrayList<>();

        for (int i = 1; i <= Math.random()*5+1; i++) {
            exercises.add(new Exercise("Saludos 1", 25, "{\"Exercise_Type\":\"TIPUS_TEST\",\"sentenceToTranslate\":\"Hello, my name is Jason\",\"Correct_Answer\":\"Hola, me llamo es Jason\",\"Wrong_Answers\":[\"Hola, Jason me llamo yo\",\"Hola, mi nombre me llamo Jason\"]}"));
        }

        return exercises;
    }
}