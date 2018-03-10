package server;

import java.io.*;
import java.net.*;
import client.*;

public class DefaultSocketServer extends Thread implements SocketClientInterface, SocketClientConstants {
	private BufferedReader strIn;
	private PrintWriter strOut;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private Socket sock;
	private String clientName = "";
	boolean waiting_for_name = false;
	private Server serverHelper = new Server();
	private String menuTemplate = "$MENU: Enter \"1\" to Upload New Car, \"2\" to Configure An Existing Car, \"0\" to Exit=====";
	private boolean running = true;
	
	public DefaultSocketServer(Socket s) {
		sock = s;
	}

	public Socket getSocket() {
		return sock;
	}

	public void run() {
		while(running) {
			if (openConnection()) {
				handleSession();
				// closeSession();
			}
		}
		
	}
	
	public void stopThread() {
		running = false;
	}
	public boolean openConnection() {
		try {
			strOut = new PrintWriter(sock.getOutputStream(), true);
			strIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			objOut = new ObjectOutputStream(sock.getOutputStream());
			objIn = new ObjectInputStream(sock.getInputStream());
		} catch (IOException e) {
			if (DEBUG)
				System.err.println("Unable to obtain stream to/from " + sock.getInetAddress());
			return false;
		}
		return true;
	}

	public synchronized void handleSession() {
		String strInput = "";
		if (DEBUG)
			System.out.println("Start handling session with " + sock.getInetAddress());
		try {
			while ((strInput = strIn.readLine()) != null) {
				handleInput(strInput);
			}
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Error: Handling session with " + sock.getInetAddress());
		}
	}

	public void sendOutput(String strOutput) {
		strOut.println(strOutput);
	}

	public void handleInput(String strInput) {
		if (clientName.isEmpty()) {
			System.out.println("New Client#" + this.getName() + ": " + strInput);
			if (!waiting_for_name)
				sendOutput("Hi. What is your name?");
			else {
				clientName = strInput;
				sendOutput("Hi " + clientName + "." + menuTemplate);
			}
			waiting_for_name = true;
		} else {
			System.out.println(clientName + ": " + strInput);
			switch (strInput) {
			case "1":
				sendOutput("we are not open yet... "+ menuTemplate);
				break;
			case "2":
				sendOutput("Woooooo! Still not open... "+ menuTemplate);
				break;
			case "0":
				sendOutput("Bye!");
				closeSession();
				break;
			default:
				sendOutput("We received \"" + strInput + "\". That's not we really do here... Enter 1 for Menu, 0 to Exit.");
				break;
			}
		}
	}
	
	public void closeSession() {
		strOut = null;
		strIn = null;
		try {
			stopThread();
			sock.close();
		} catch (Exception e) {
			if (DEBUG)
				System.out.println("Error: Closing socket to " + sock.getInetAddress());
		}
	}

	public void setSocket(Socket s) {
		sock = s;
	}
}
