package epics.binarytrack;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import android.app.Application;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import epics.binarytrack.questions.JsonParsing;
import epics.binarytrack.questions.Question;

public class ServerApplication extends Application {

	public static Socket clientSocket = null;
	public static PrintWriter out = null;
	public static ArrayList<Question> questions = null;

	@Override
	public void onCreate() {
		super.onCreate();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				ServerSocket serverSocket = null;
				try {
					serverSocket = new ServerSocket(8081);
					while (true) {
						clientSocket = serverSocket.accept();
						Log.d("epics", "Waiting for connection.....");
						try {
							out = new PrintWriter(
									clientSocket.getOutputStream(), true);
						} catch (IOException e) {
							Log.d("Connecting", "client did not connect");
						}

					}
				} catch (IOException e) {
					System.err.println("Could not listen on port: 10007.");
					System.exit(0);
				}
				Log.d("epics", "Connection successful");
				Log.d("epics", "Waiting for input.....");
			}
		};
		new Thread(r).start();

		try {
			JsonParsing.process();
		} catch (Exception e) {
			Log.d("processing json", e.getMessage());
			e.printStackTrace();
		}
		if (JsonParsing.getList() != null) {
			Log.d("processing question", "not null");
		}

	}

}
