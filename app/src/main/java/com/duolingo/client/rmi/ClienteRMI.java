package com.duolingo.client.rmi;

import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import com.duolingo.client.CursFragment;
import com.duolingo.client.rmi.interfaces.ICom;
import com.duolingo.client.rmi.models.Course;

import java.io.IOException;
import java.util.ArrayList;

import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

//public class ClienteRMI extends AsyncTask<Void, Void, ArrayList<Course>> {
//
//	private static final int port = 25565;
//	private static final String host = "91.126.22.107";
//
////	@Override
////	protected ArrayList<Course> doInBackground(Void... voids) {
////		try {
////			CallHandler callHandler = new CallHandler();
////			Client client = new Client(host, port, callHandler);
////			System.out.println("Client connected successfully");
////			ICom com = (ICom) client.getGlobal(ICom.class);
////			ArrayList<Course> courses = com.getData();
////			client.close();
////			return courses;
////		} catch (IOException e) {
////			System.out.println("ERROR: Imposible conectar con el servidor " + e.getMessage());
////		}
////		return null;
////	}
////}

public class ClienteRMI extends AsyncTask<Void, Void, ArrayList<String>> {

	private static final int port = 25565;
	private static final String host = "91.126.22.107";

	@Override
	protected ArrayList<String> doInBackground(Void... voids) {
		try {
			CallHandler callHandler = new CallHandler();
			Client client = new Client(host, port, callHandler);
			System.out.println("Client connected successfully");
			ICom com = (ICom) client.getGlobal(ICom.class);
			ArrayList<String> courses = com.getData();
			client.close();
			return courses;
		} catch (IOException e) {
			System.out.println("ERROR: Imposible conectar con el servidor " + e.getMessage());
		}
		return null;
	}
}
