package epics.binarytrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Application;
import android.util.Log;

public class ServerApplication extends Application {

	public static Socket clientSocket = null;
	public static PrintWriter out = null;

	@Override
	public void onCreate() {
		super.onCreate();

		Runnable r = new Runnable() {

			@Override
			public void run() {
				ServerSocket serverSocket = null;
				while (true){
				try {
					serverSocket = new ServerSocket(8081);
					clientSocket = serverSocket.accept();
					Log.d("epics","Waiting for connection.....");
					new Thread(new Runnable(){

						@Override
						public void run() {
							try {
								out = new PrintWriter(clientSocket.getOutputStream(), true);
							} catch (IOException e) {
								Log.d("Connecting", "client did not connect");
							}
						}
						
					}).start();
				} catch (IOException e) {
					System.err.println("Could not listen on port: 10007.");
					System.exit(0);
				}
				Log.d("epics","Connection successful");
				Log.d("epics","Waiting for input.....");
				}

//				PrintWriter out;
//				try {
//
//					while (true) {
//					}
//
//					// BufferedReader in = new BufferedReader(
//					// new InputStreamReader( clientSocket.getInputStream()));
//
//					// String inputLine;
//					//
//					// while ((inputLine = in.readLine()) != null)
//					// {
//					// System.out.println ("Server: " + inputLine);
//					// out.println(inputLine);
//					//
//					// if (inputLine.equals("Bye."))
//					// break;
//					// }
//
//					// out.close();
//					// in.close();
//					// clientSocket.close();
//					// serverSocket.close();
//				} catch (IOException e) {
//					Log.d("connecting", e.getMessage());
//					e.printStackTrace();
//				}

			}

		};
		new Thread(r).start();
	}

}
