package server;

import java.io.*;
import java.util.Properties;

import adapter.ProxyAutomobile;
import exception.AutoException;
import util.FileIO;

public class ServerHelper extends ProxyAutomobile {
	private BufferedReader strIn;
	private PrintWriter strOut;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;

	public ServerHelper() {
	}

	public ServerHelper(PrintWriter sOut, BufferedReader sIn, ObjectOutputStream oOut, ObjectInputStream oIn) {
		strOut = sOut;
		strIn = sIn;
		objOut = oOut;
		objIn = oIn;
	}

	public boolean isAutoListEmpty() {
		return autos.isEmpty();
	}

	public boolean buildAutoFromProp(Properties props) {
		FileIO io = new FileIO();
		try {
			autos.addAuto(io.buildAutomobileFromPropObject(props));
			return true;
		} catch (AutoException e) {
			e.fix(e.getErrorNumber());
			return false;
		}
	}

	public void sendAutoList(ObjectOutputStream out) {
		try {
			out.writeObject(autos.getAutoList());
		} catch (IOException e) {
			System.err.println("Error: Cannot send Auto list to Client!");
		}
	}

	public void displayAutoList() {
		String[] aList = autos.getAutoList();
		for (int i = 0; i < aList.length; i++) {
			System.out.println((i + 1) + " - " + aList[i]);
		}
	}

	public void sendAutoObject(String autoID, ObjectOutputStream out) {
		try {
			out.writeObject(autos.getAuto(autoID));
		} catch (IOException e) {
			System.err.println("Error: Cannot send Auto Object to Client!");
		} catch (AutoException e) {
			System.err.println("Error: Cannot find " + autoID + "!");
		}
	}

	public void displayClientRequest(String request) {
		System.out.println("Client: " + request);
	}

	public void displayServerResponse(String response) {
		System.out.println("Server: " + response);
	}

	public void sendAndLogResponse(String response) {
		strOut.println(response);
		displayServerResponse(response);
	}

	public void processRequest() throws IOException {
		strOut.println("What can we do for you?");
		String request = "";
		while ((request = strIn.readLine()) != null) {
			displayClientRequest(request);
			String response = "";
			switch (request) {
			case "1":
				BuildCarModelOptions bc = new BuildCarModelOptions();
				try {
					if (bc.buildAutoFromProp((Properties) objIn.readObject())) {
						displayServerResponse("Auto List:");
						displayAutoList();
						response = "Auto sucessfully added!";
						sendAndLogResponse(response);
					} else {
						response = "Error: Auto cannot be added!";
						sendAndLogResponse(response);
					}
				} catch (ClassNotFoundException e) {
					response = "Error: Object is not class Properties!";
					sendAndLogResponse(response);
				} catch (IOException e) {
					response = "Error: Cannot receive auto Properties!";
					sendAndLogResponse(response);
				}
				break;
			case "2":
				// System.out.println("Checking AutoList");
				if (!isAutoListEmpty()) {
					response = "Sending Auto List...";
					sendAndLogResponse(response);
					sendAutoList(objOut);
					String autoID = "";
					try {
						if ((autoID = strIn.readLine()) != null) {
							sendAutoObject(autoID, objOut);
						}
					} catch (IOException e) {
						response = "Error: Cannot receive Auto ID!";
						sendAndLogResponse(response);
					}
				} else {
					response = "Error: No Auto to configure. Please Upload new Auto 1st!";
					sendAndLogResponse(response);
				}
				break;
			case "0":
				response = "Bye!";
				sendAndLogResponse(response);
				return;
			default:
				response = "We received \"" + request
						+ "\". That's not we really do here... Enter 1 for Menu, 0 to Exit.";
				sendAndLogResponse(response);
				break;
			}
		}

	}

}
