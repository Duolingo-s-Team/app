package com.duolingo.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.duolingo.client.rmi.models.Exercise;
import com.duolingo.client.rmi.models.Level;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class ExerciseOpenTranslationActivity extends AppCompatActivity {
    ArrayList<Exercise> arrayExercises;
    int pos;
    Level lvl;
    boolean error=false;
    boolean match=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_translation_exercise);

        TextView title=findViewById(R.id.fraseInicial);
        EditText editText=findViewById(R.id.editText);
        Button b=findViewById(R.id.buttonNext);
        b.setEnabled(false);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {

                pos=extras.getInt("pos");
                lvl= (Level) extras.getSerializable("lvl");
                error=extras.getBoolean("error");
                arrayExercises= (ArrayList<Exercise>) lvl.getExercises();
            }
        }

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.setEnabled(true);
            }
        });

        Exercise e=arrayExercises.get(pos);
        String content=e.getContent();

        try {
            //Coge los elementos del json y los pone en variables
            JSONObject jsonObject=new JSONObject(content);
            title.setText(jsonObject.getString("sentenceToTranslate"));
            int exp=jsonObject.getInt("exerciseExp");
            int coins=jsonObject.getInt("exerciseCoins");
            JSONArray jsonArray=jsonObject.getJSONArray("Correct_Answer");
            ArrayList<String>list=new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++){
                list.add((String)jsonArray.get(i));
            }
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pos == arrayExercises.size() - 1) {
                        String s=editText.getText().toString();
                        s=fixInput(s);
                        for(String string : list){
                            string=fixInput(string);
                            if(string.equals(s)){
                                match=true;
                            }
                        }

                        if(match){
                                Snackbar snackbar = Snackbar.make(v, "Correcto, has ganado "+exp+"  exp y "+coins+" monedas.", Snackbar.LENGTH_LONG);
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
                            error = true;
                            Snackbar snackbar = Snackbar.make(v, "La respuesta es incorrecta", Snackbar.LENGTH_LONG);
                            snackbar.setAction("Continuar", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            });

                            snackbar.show();
                        }
                    }else{
                        String s=editText.getText().toString();
                        s=fixInput(s);
                        for(String string : list){
                            string=fixInput(string);
                            if(string.equals(s)){
                                match=true;
                            }
                        }
                        if(match){
                            Snackbar snackbar = Snackbar.make(v, "Correcto, has ganado "+exp+"  exp y "+coins+" monedas. Error="+error, Snackbar.LENGTH_INDEFINITE);
                            snackbar.setAction("Continuar", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pos = pos + 1;
                                    finish();
                                    //comprovar el tipo de ejercicio y iniciar una activity u otra

                                    try {
                                        JSONObject json=new JSONObject(arrayExercises.get(pos).getContent());
                                        if(String.valueOf(json.get("Exercise_Type")).equals("TIPUS_TEST")){

                                            Intent intent=new Intent(v.getContext(),ExerciseTestActivity.class).putExtra("pos", pos);
                                            intent.putExtra("lvl",lvl);
                                            intent.putExtra("error",error);
                                            startActivity(intent);

                                        }else{

                                            getIntent().putExtra("pos", pos);
                                            startActivity(getIntent());
                                        }
                                    } catch (JSONException jsonException) {
                                        jsonException.printStackTrace();
                                    }

                                }
                            });
                            PerfilFragment.setCoins(PerfilFragment.getCoins()+coins);
                            PerfilFragment.setPoints(PerfilFragment.getPoints()+exp);
                            snackbar.show();

                        }else{
                            error=true;
                            Snackbar snackbar = Snackbar.make(v, "La respuesta es incorrecta", Snackbar.LENGTH_INDEFINITE);
                            snackbar.setAction("Continuar", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pos = pos + 1;
                                    finish();

                                    try {
                                        JSONObject json=new JSONObject(arrayExercises.get(pos).getContent());
                                        if(String.valueOf(json.get("Exercise_Type")).equals("TIPUS_TEST")){

                                            Intent intent=new Intent(v.getContext(),ExerciseTestActivity.class).putExtra("pos", pos);
                                            intent.putExtra("lvl",lvl);
                                            intent.putExtra("error",error);
                                            startActivity(intent);

                                        }else{
                                            getIntent().putExtra("level", lvl);
                                            getIntent().putExtra("pos", pos);
                                            getIntent().putExtra("error",error);
                                            startActivity(getIntent());
                                        }
                                    } catch (JSONException jsonException) {
                                        jsonException.printStackTrace();
                                    }
                                }
                            });

                            snackbar.show();
                        }

                    }
                }
            });

        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }

    }
    public static String fixInput(String answer) {
        return answer.replaceAll("[.:,;!\"·$%&/()=?¿¡]+", "").replaceAll("[\\s]+", "").toLowerCase();
    }
}