package com.duolingo.client;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class PerfilFragment extends Fragment {


    private static int coins;
    private static int points;
    private String ip, username, pass, sHidePass;
    private TextView textPass;

    public static int getCoins() {
        return coins;
    }

    public static void setCoins(int coins) {
        PerfilFragment.coins = coins;
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int points) {
        PerfilFragment.points = points;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textCoin = view.findViewById(R.id.textCoin);
        TextView textPoints = view.findViewById(R.id.textPoints);

        textCoin.setText(": "+ String.valueOf(getCoins()));
        textPoints.setText(": "+ String.valueOf(getPoints()));


        TextView textIP = view.findViewById(R.id.ipTextView);
        textIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog("ipserver");
            }
        });
        TextView textUser = view.findViewById(R.id.usernameTextView);
        textUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog("username");
            }
        });
        textPass = view.findViewById(R.id.passTextView);
        textPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog("password");
            }
        });



        // Lectura de la configuració
        xmlData();
        textIP.setText("IP: "+ip);
        textUser.setText("Username: "+username);
        textPass.setText("Pass: "+sHidePass);

    }
    public void xmlData(){
        String[] data = new String[3];

        try {
            File file = new File("/data/data/com.duolingo.client/filesconfig.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            NodeList nList = doc.getElementsByTagName("Config");
            Node nNode = nList.item(0);
            Element element = (Element) nNode;

            ip = element.getElementsByTagName("ipserver").item(0).getTextContent();
            username = element.getElementsByTagName("username").item(0).getTextContent();
            pass = element.getElementsByTagName("password").item(0).getTextContent();

            sHidePass = hidePass(pass);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String hidePass(String pass){

        int x = pass.length();
        pass = "";
        for (int i = 0; i < x; i++){
            pass += "*";
        }
        return pass;
    }

    public void Dialog(String type){
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

        alert.setTitle("Editar");
        alert.setMessage("Introduce el nuevo dato");

        Log.v("T",""+type);
        // Set an EditText view to get user input
        final EditText input = new EditText(getContext());
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String newValue = input.getText().toString();
                modifyXML(type, newValue);
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }});
        alert.show();
    }

    public void modifyXML(String node, String newValue){
        try {
            File file = new File("/data/data/com.duolingo.client/filesconfig.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(file);

            //Buscamos los elementos de Config
            Node config = document.getElementsByTagName("Config").item(0);
            Log.v("Config", config.getNodeName());

            //Modificamos el campo necesario
            NodeList nodes = config.getChildNodes();

            if (node.equals("ipserver")){
                Node element = nodes.item(0);
                element.setTextContent(newValue);
            } else if (node.equals("username")){
                Node element = nodes.item(2);
                element.setTextContent(newValue);
            } else if (node.equals("password")){
                Node element = nodes.item(3);
                //aqui se pasa al xml
                element.setTextContent(newValue);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File("/data/data/com.duolingo.client/filesconfig.xml"));
            transformer.transform(domSource, streamResult);

            this.getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}