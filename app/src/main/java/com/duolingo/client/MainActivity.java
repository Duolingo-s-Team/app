package com.duolingo.client;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;

import xml.XML;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = new File(getFilesDir()+"config.xml");
        if (!file.exists()) {
                XML createXML = new XML();
                createXML.readXML(getFilesDir());
        }


        final Button buttonCurs=findViewById(R.id.curs);
        final Button buttonLliga=findViewById(R.id.lliga);
        final Button buttonBotiga=findViewById(R.id.botiga);
        final Button buttonPerfil=findViewById(R.id.perfil);

        CursFragment cf=new CursFragment();
        LligaFragment lf=new LligaFragment();
        PerfilFragment pf= new PerfilFragment();
        BotigaFragment bf= new BotigaFragment();



        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.fragment,cf);
        ft.commit();

        buttonCurs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();

                ft.replace(R.id.fragment,cf);
                ft.commit();
            }
        });


        buttonLliga.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();

                ft.replace(R.id.fragment,lf);
                ft.commit();
            }
        });

        buttonBotiga.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();

                ft.replace(R.id.fragment,bf);
                ft.commit();
            }
        });

        buttonPerfil.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();

                ft.replace(R.id.fragment,pf);
                ft.commit();
            }
        });


    }


}