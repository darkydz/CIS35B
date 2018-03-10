package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants{
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
	
	public Socket getSocket() {
		return sock;
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
			if (DEBUG) System.err.println("Unable to connect to " + strHost);
			return false;
		}
		
		try {
			strOut = new PrintWriter(sock.getOutputStream(),true);
			strIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			objOut = new ObjectOutputStream(sock.getOutputStream());
			objIn = new ObjectInputStream(sock.getInputStream());
		} catch (IOException e) {
			if (DEBUG) System.err.println("Unable to obtain stream to/from " + strHost);
			return false;
		}
		return true;
	}

	
	public void handleSession() {
		String strInput = "";
		if (DEBUG) System.out.println("Handling session with " + strHost + ":" + iPort);
		try {
			strOut.println("Hi!");
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			String fromUser;
			while ((fromServer = strIn.readLine()) != null) {
				System.out.println("Server: " + fromServer);
				if (fromServer.equals("Bye."))
					break;

				fromUser = stdIn.readLine();
				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					strOut.println(fromUser);
					if (fromUser.equals("0")) closeSession();
				}
			}
//			while ((strInput = strIn.readLine())!= null)
//			{
//				handleInput(strInput);
//			}
		} catch (IOException e) {
			if (DEBUG) System.out.println("Error: Handling session with " + strHost + ":" + iPort);
		}
	}
	
	public void sendOutput(String strOutput) {
		strOut.println(strOutput);
	}
	
	public void handleInput(String strInput) {
		System.out.println(strInput);
	}

	public void closeSession() {
		strOut = null;
		strIn = null;
		try {
			sock.close();
		} catch (IOException e) {
			if (DEBUG) System.out.println("Error: Closing socket to " + strHost);
		}
	}
	
	public void setSocket(Socket s) {
		sock = s;
	}
	
	public void setHost(String strHost) {
		this.strHost = strHost;
	}
	public void setPort(int iPort) {
		this.iPort = iPort;
	}
	
}
