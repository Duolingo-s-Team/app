package com.duolingo.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {
    AdapterDatos ad=new AdapterDatos();
    ArrayList<Exercise> arrayExercises ;
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
               Log.v("DEBUG",String.valueOf(pos));
               lvl= (Level) extras.getSerializable("level");
               arrayExercises= (ArrayList<Exercise>) lvl.getExercises();
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
}