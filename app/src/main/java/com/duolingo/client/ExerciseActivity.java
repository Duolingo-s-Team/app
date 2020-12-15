package com.duolingo.client;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.duolingo.client.models.Exercise;
import com.duolingo.client.models.Level;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class ExerciseActivity extends AppCompatActivity {
    AdapterDatos ad=new AdapterDatos();
    ArrayList<Exercise> arrayExercises ;
    int pos;
    Level lvl;
    String opcion;
    boolean error=false;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);


        TextView title=findViewById(R.id.textTitleExercise);
        Button b=findViewById(R.id.next);
        b.setEnabled(false);
        Button ba1=findViewById(R.id.answer1);
        Button ba2=findViewById(R.id.answer2);
        Button ba3=findViewById(R.id.answer3);


        ba1.setBackgroundColor(Color.BLUE);
        ba2.setBackgroundColor(Color.BLUE);
        ba3.setBackgroundColor(Color.BLUE);

       ba1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ba1.setBackgroundColor(Color.BLUE);
               ba2.setBackgroundColor(Color.BLUE);
               ba3.setBackgroundColor(Color.BLUE);
               ba1.setBackgroundColor(Color.GREEN);
               b.setEnabled(true);
               opcion=ba1.getText().toString();

           }
       });

        ba2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ba1.setBackgroundColor(Color.BLUE);
                ba2.setBackgroundColor(Color.BLUE);
                ba3.setBackgroundColor(Color.BLUE);
                ba2.setBackgroundColor(Color.GREEN);
                b.setEnabled(true);
                opcion=ba2.getText().toString();
            }
        });
        ba3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ba1.setBackgroundColor(Color.BLUE);
                ba2.setBackgroundColor(Color.BLUE);
                ba3.setBackgroundColor(Color.BLUE);
                ba3.setBackgroundColor(Color.GREEN);
                b.setEnabled(true);
                opcion=ba3.getText().toString();
            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {

               pos=extras.getInt("pos");
               lvl= (Level) extras.getSerializable("level");
               arrayExercises= (ArrayList<Exercise>) lvl.getExercises();
            }
        }

        Exercise e=arrayExercises.get(pos);
        String content=e.getContent();
        try {
            //Coge los elementos del json y los pone en variables
            JSONObject jsonObject=new JSONObject(content);
            title.setText(jsonObject.getString("sentenceToTranslate"));
            int exp=jsonObject.getInt("exersiceExp");
            int coins=jsonObject.getInt("exersiceCoins");
            JSONArray jsonArray=jsonObject.getJSONArray("Wrong_Answers");
            String text1=jsonArray.getString(0);
            String text2=jsonArray.getString(1);
            String text3=jsonObject.getString("Correct_Answer");
            String correct=jsonObject.getString("Correct_Answer");
            System.out.println(jsonObject.getString("Correct_Answer"));
            ArrayList<String> lista=new ArrayList<>();
            lista.add(text1);
            lista.add(text2);
            lista.add(text3);
            Collections.shuffle(lista);
            ba1.setText(lista.get(0));
            ba2.setText(lista.get(1));
            ba3.setText(lista.get(2));
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (pos == arrayExercises.size() - 1) {
                        if (correct.equals(opcion)) {
                            Snackbar snackbar = Snackbar.make(v, "La respuesta es correcta, has ganado "+exp+"de experiencia y "+coins+" monedas.", Snackbar.LENGTH_LONG);
                            snackbar.setAction("Continuar", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                            if(error){
                                PerfilFragment.setCoins(PerfilFragment.getCoins()+coins);
                                PerfilFragment.setPoints(PerfilFragment.getPoints()+exp);
                            }else{
                                PerfilFragment.setCoins(PerfilFragment.getCoins()+coins+150);
                                PerfilFragment.setPoints(PerfilFragment.getPoints()+exp);
                            }
                            snackbar.show();

                        }else {
                            Snackbar snackbar = Snackbar.make(v, "La respuesta es incorrecta", Snackbar.LENGTH_LONG);
                            snackbar.setAction("Continuar", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pos = pos + 1;
                                    finish();
                                    getIntent().putExtra("pos", pos);
                                    startActivity(getIntent());
                                }
                            });
                            error=true;
                            snackbar.show();
                        }
                    }else{
                        if (correct.equals(opcion)) {
                            Snackbar snackbar = Snackbar.make(v, "La respuesta es correcta, has ganado "+exp+"de experiencia y "+coins+" monedas.", Snackbar.LENGTH_LONG);
                            snackbar.setAction("Continuar", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pos = pos + 1;
                                    finish();
                                    getIntent().putExtra("pos", pos);
                                    startActivity(getIntent());
                                }
                            });
                            PerfilFragment.setCoins(PerfilFragment.getCoins()+coins);
                            PerfilFragment.setPoints(PerfilFragment.getPoints()+exp);
                            snackbar.show();
                        }else{
                            Snackbar snackbar = Snackbar.make(v, "La respuesta es incorrecta", Snackbar.LENGTH_LONG);
                            snackbar.setAction("Continuar", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pos = pos + 1;
                                    finish();
                                    getIntent().putExtra("pos", pos);
                                    startActivity(getIntent());
                                }
                            });
                            error=true;
                            snackbar.show();
                        }

                    }

                }
            });

        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }


    }
}