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
	private boolean running = true;

	public DefaultSocketServer(Socket s) {
		sock = s;
	}

	public void run() {
		while (running) {
			if (openConnection()) {
				handleSession();
				closeSession();
			}
		}
	}
	
	/**
	 * to stop this server thread
	 */
	public void stopThread() {
		if (DEBUG)
			System.out.println("Ending connection to " + this.getName());
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
				System.out.println("Unable to obtain stream to/from " + this.getName());
			return false;
		}
		return true;
	}

	public synchronized void handleSession() {
		// pass the IO objects to the helper
		ServerHelper sh = new ServerHelper(strOut, strIn, objOut, objIn);
		if (DEBUG)
			System.out.println("Start handling session with " + this.getName());
		try {
			sh.processRequest();
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Error: Handling session with " + this.getName());
		}
	}

	public void sendOutput(String strOutput) {
		System.out.println(strOutput);
		strOut.println(strOutput);
	}

	public void closeSession() {
		stopThread();
		try {
			strIn.close();
			strOut.close();
			objIn.close();
			objOut.close();
			sock.close();
		} catch (Exception e) {
			if (DEBUG)
				System.out.println("Error: Closing socket to " + this.getName());
		}
	}

}
