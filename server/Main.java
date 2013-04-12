import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) {
		new PlayerResponse(1).start();
		new PlayerResponse(2).start();
	}

	public static void runScript(int player) {
		Process p;
		try {
			if (player == 1) {
				p = Runtime.getRuntime().exec("sh rahul_code_4_player1.sh");
			} else {
				p = Runtime.getRuntime().exec("sh rahul_code_4_player2.sh");
			}
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class PlayerResponse extends Thread {
	int player = 0;

	public PlayerResponse(int player) {
		this.player = player;
	}

	public void run() {

		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		String serverHostname = "localhost";
		try {
			if (player == 1) {
				echoSocket = new Socket("localhost", 5001);
			} else {
				echoSocket = new Socket("localhost", 5002);
			}
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
            String line = null;
			while ((line=in.readLine())!=null) {
				Main.runScript(player);
			}

			out.close();
			in.close();
			echoSocket.close();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + serverHostname);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for "
					+ "the connection to: " + serverHostname);
			System.exit(1);
		}
	}
}