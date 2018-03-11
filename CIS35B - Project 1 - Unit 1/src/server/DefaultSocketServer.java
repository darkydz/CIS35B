package server;

import java.io.*;
import java.net.*;
import java.util.Properties;

import client.*;

public class DefaultSocketServer extends Thread implements SocketClientInterface, SocketClientConstants {
	private BufferedReader strIn;
	private PrintWriter strOut;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private Socket sock;
	private String clientName = "";
	boolean handshaked = false;
	private String menuTemplate = "$MENU: Enter \"1\" to Upload New Car, \"2\" to Configure An Existing Car, \"0\" to Exit=====";
	private boolean running = true;
	private ServerHelper sh = new ServerHelper();
	
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
				System.err.println("Unable to obtain stream to/from " + this.getName());
			return false;
		}
		return true;
	}

	public synchronized void handleSession() {
		String strInput = "";
		if (DEBUG)
			System.out.println("Start handling session with " + this.getName());
		try {
			strOut.println("What can we do for you?");
			while ((strInput = strIn.readLine()) != null) {
				handleInput(strInput);
			}
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Error: Handling session with " + this.getName());
			stopThread();
		}
	}

	public void sendOutput(String strOutput) {
		strOut.println(strOutput);
	}

	public void handleInput(String strInput) {
//		if (clientName.isEmpty()) {
//			System.out.println("New Client#" + this.getName() + ": " + strInput);
//			if (!handshaked)
//				sendOutput("Hi. What is your name?");
//			else {
//				clientName = strInput;
//				sendOutput("Hi " + clientName + ".");
//				sendOutput("Please enter 1 to open menu. 0 to exit.");
//			}
//			handshaked = true;
//		} else 
		{
//			System.out.println(clientName + ": " + strInput);
			System.out.println("Client: " + strInput);
			String response = "";
			switch (strInput) {
			case "1":
//				sendOutput("we are not open yet... ");
				BuildCarModelOptions bc = new BuildCarModelOptions();
				try {
					if (bc.buildAutoFromProp( (Properties) objIn.readObject()))
					{
						sh.displayAutoList();
						response = "Auto sucessfully added!";
					}
					else
						response = "Error: Auto cannot be added!";
				} catch (ClassNotFoundException e) {
					response = "Error: Object is not class Properties!";
					
				} catch (IOException e) {
					response = "Error: Cannot receive auto Properties!";
				}
				finally{
					System.out.println(response);
					sendOutput(response);
				}
				break;
			case "2":
				sh.sendAutoList(objOut);
				String autoID = "";
				try {
					if ((autoID = strIn.readLine())!= null) {
						sh.sendAutoObject(autoID, objOut);
//						response = autoID
					}
				} catch (IOException e) {
					response = "Error: Cannot receive Auto ID!";
				}
				finally{
					System.out.println(response);
					sendOutput(response);
				}
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
			sock.close();
		} catch (Exception e) {
			if (DEBUG)
				System.out.println("Error: Closing socket to " + this.getName());
		}
	}

	public void setSocket(Socket s) {
		sock = s;
	}
}
