package com.duolingo.client.rmi;

import android.os.AsyncTask;

import com.duolingo.client.rmi.interfaces.ICom;

import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

import java.io.IOException;
import java.util.ArrayList;


public class ClienteRMI extends AsyncTask<Void, Void, ArrayList<String>> {

	private static final int port = 25565;
	private static final String host = "91.126.22.107";

	@Override
	protected ArrayList<String> doInBackground(Void... voids) {
		try {
			CallHandler callHandler = new CallHandler();
			Client client = new Client(host, port, callHandler);
			ICom com = (ICom) client.getGlobal(ICom.class);
			ArrayList<String> courses = com.getData();
			System.out.println("Client connected successfully");
			client.close();
			return courses;
		} catch (IOException e) {
			System.out.println("ERROR: Imposible conectar con el servidor " + e.getMessage());
		}
		return null;
	}
}
