package client;

import java.io.*;
import java.net.*;

public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants {
	private BufferedReader strIn;
	private PrintWriter strOut;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private Socket sock;
	private String strHost;
	private int iPort;

	public DefaultSocketClient(String strHost, int iPort) {
		setPort(iPort);
		setHost(strHost);
	}

	public void run() {
		if (openConnection()) {
			handleSession();
			closeSession();
		}
	}

	public boolean openConnection() {
		try {
			if (sock == null)
				sock = new Socket(strHost, iPort);

		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Unable to connect to " + strHost);
			return false;
		}

		try {
			strOut = new PrintWriter(sock.getOutputStream(), true);
			strIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			objOut = new ObjectOutputStream(sock.getOutputStream());
			objIn = new ObjectInputStream(sock.getInputStream());
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Unable to obtain stream to/from " + strHost);
			return false;
		}
		return true;
	}

	public void handleSession() {
		ClientHelper ch = new ClientHelper(strOut, strIn, objOut, objIn);
		if (DEBUG)
			System.out.println("Start session with " + strHost + ":" + iPort);
		ch.processRequest();
	}

	public void closeSession() {
		try {
			strIn.close();
			strOut.close();
			objIn.close();
			objOut.close();
			sock.close();
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Error: Closing socket to " + strHost);
		}
	}

	public void setHost(String strHost) {
		this.strHost = strHost;
	}

	public void setPort(int iPort) {
		this.iPort = iPort;
	}

}
