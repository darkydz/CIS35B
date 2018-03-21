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
	
	/**
	 * Assign IO objects
	 */
	public ServerHelper(PrintWriter sOut, BufferedReader sIn, ObjectOutputStream oOut, ObjectInputStream oIn) {
		strOut = sOut;
		strIn = sIn;
		objOut = oOut;
		objIn = oIn;
	}

	/**
	 * 
	 * @return true if fleet is empty; otherwise false.
	 */
	public boolean isAutoListEmpty() {
		return autos.isEmpty();
	}

	/**
	 * 
	 * @param props Properties object
	 * @return true if new Automobile is added to fleet; otherwise, false.
	 */
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

	/**
	 * 
	 * @param out IO object to serialize and send data out
	 */
	public void sendAutoList(ObjectOutputStream out) {
		try {
			out.writeObject(autos.getAutoList());
		} catch (IOException e) {
			System.err.println("Error: Cannot send Auto list to Client!");
		}
	}

	/**
	 * Print list of current Autos in the fleet
	 */
	public void displayAutoList() {
		String[] aList = autos.getAutoList();
		for (int i = 0; i < aList.length; i++) {
			System.out.println((i + 1) + " - " + aList[i]);
		}
	}

	/**
	 * 
	 * @param autoID ID of Auto to be sent
	 * @param out IO object to serialize and send data out
	 */
	public void sendAutoObject(String autoID, ObjectOutputStream out) {
		try {
			out.writeObject(autos.getAuto(autoID));
//			System.out.println("Sending \""+autoID+"\" to Client!");
		} catch (IOException e) {
			System.err.println("Error: Cannot send Auto Object to Client!");
		} catch (AutoException e) {
			System.err.println("Error: Cannot find " + autoID + "!");
		}
	}

	/**
	 * Self-explanatory
	 */
	public void displayClientRequest(String request) {
		System.out.println("Client: " + request);
	}

	/**
	 * Self-explanatory
	 */
	public void displayServerResponse(String response) {
		System.out.println("Server: " + response);
	}

	/**
	 * Self-explanatory
	 */
	public void sendAndLogResponse(String response) {
		strOut.println(response);
		displayServerResponse(response);
	}
	
	public void getAuto() {
		String response = "";
		String autoID = "";
		try {
			if ((autoID = strIn.readLine()) != null) {
				sendAutoObject(autoID, objOut); //send auto object to client to configure for Total price
			}
		} catch (IOException e) {
			response = "Error: Cannot receive Auto ID!";
			sendAndLogResponse(response);
		}
	}

	/**
	 * 
	 * @throws IOException if cannot receive or send data
	 */
	public void processRequest() throws IOException {
		strOut.println("What can we do for you?");//acknowledge connection with client to trigger menu
		String request = "";
		while ((request = strIn.readLine()) != null) {//wait for client to send a request
			displayClientRequest(request);
			String response = "";
			switch (request) {
			case "1"://add new auto to the fleet
				BuildCarModelOptions bc = new BuildCarModelOptions();
				try {
					if (bc.buildAutoFromProp((Properties) objIn.readObject())) { //if valid Properties object is received
						displayServerResponse("Auto List:");
						displayAutoList();
						response = "Auto sucessfully added!";
						sendAndLogResponse(response); //send response to client
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
			case "2"://send list of autos for client to select then send the selected auto object to client to configure for Total price
				// System.out.println("Checking AutoList");
				if (!isAutoListEmpty()) { //fleet is not empty
					response = "Sending Auto List...";
					sendAndLogResponse(response);
					sendAutoList(objOut);//send list of autos for client to select
					getAuto();
				} else {//send msg to ask for 
					response = "Error: No Auto to configure. Please Upload new Auto 1st!";
					sendAndLogResponse(response);
				}
				break;
			case "0"://client exits
				response = "Bye!";
				sendAndLogResponse(response);
				return;//get out of this method to stop the current thread
			case "GET_AUTO":
				response = "What's the Auto ID?";
				sendAndLogResponse(response);
				getAuto();
				break;
			case "GET_AUTO_LIST":
				if (!isAutoListEmpty()) { //fleet is not empty
					response = "Sending Auto List...";
					sendAndLogResponse(response);
					sendAutoList(objOut);//send list of autos for client to select
				} else {//send msg to ask for 
					response = "Error: No Auto to configure. Please Upload new Auto 1st!";
					sendAndLogResponse(response);
				}
				break;
			default:
				response = "We received \"" + request
						+ "\". That's not we really do here... Enter 1 for Menu, 0 to Exit.";
				sendAndLogResponse(response);
				break;
			}
		}

	}

}
