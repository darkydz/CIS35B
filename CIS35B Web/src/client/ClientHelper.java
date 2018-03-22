package client;

import java.io.*;

import javax.servlet.http.HttpServletRequest;

import exception.AutoException;
import model.Automobile;

public class ClientHelper implements SocketClientConstants, AutoWebConfig {
	private BufferedReader strIn;
	private PrintWriter strOut;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private static final int MAINMENU = -1;
	private static final int UPLOAD = 1;
	private static final int CONFIGURE = 2;

	private int menu = MAINMENU;

	private String main_menu = "Please Select an Option below:\n1 - Upload New Car\n2 - Configure An Existing Car\n0 - Exit";
	private String upload_menu = "Please Enter Auto filename with .prop to upload:";

	public ClientHelper() {
	};

	public ClientHelper(PrintWriter sOut, BufferedReader sIn, ObjectOutputStream oOut, ObjectInputStream oIn) {
		strOut = sOut;
		strIn = sIn;
		objOut = oOut;
		objIn = oIn;
	}

	/**
	 * Print main menu
	 */
	public void displayMainMenu() {
		System.out.println(main_menu);
	}

	/**
	 * 
	 * @return page 1 HTML
	 * @throws IOException
	 */
	public String getSelectAutoPage() throws IOException {

		/* Handshake with Server */
		String fromServer = strIn.readLine();// receive Ack from Server: What can we do for you?
		// if (DEBUG)
		// System.out.println("Server Acknowledges: " + fromServer);
		strOut.println("GET_AUTO_LIST");// request auto list from Server
		fromServer = strIn.readLine();// receive Ack from Server: Sending Auto List...
		// if (DEBUG)
		// System.out.println("Server: " + fromServer);

		StringBuilder html = new StringBuilder();
		html.append("<!DOCTYPE html>\n" + "<html>\n" + "<head><title>Car Configuration</title></head>\n" + "<body>\n");
		if (fromServer.equals("Error: No Auto to configure. Please Upload new Auto 1st!")) {// server's fleet is empty,
																							// go back to main menu
			html.append("<p>No Auto to configure. Please check back later!</p>\n");
		} else// go to Configure menu
		{
			html.append("<h1>Car Configuration</h1>\n" + "<p>Please select an auto below to configure:</p>\n");
			html.append("<form action ='" + dispatcherURL + "' method=\"POST\">");
			html.append("<input type=\"hidden\" name=\"forward2\" value=\"ConfigureCar2\">");
			html.append("<select name=\"autoList\">");
			try {
				if (DEBUG)
					System.out.println("Waiting for AutoList");
				String[] autoList = (String[]) objIn.readObject();// receive list of cars from server
				for (int i = 0; i < autoList.length; i++) {
					html.append("<option>" + autoList[i] + "</option>");
				}
				html.append("</select><input type = \"submit\" value = \"Configure\"/></form>");
			} catch (ClassNotFoundException e) {
				if (DEBUG)
					System.out.println("Error: Cannot open Auto List!");
			} catch (IOException e) {
				if (DEBUG)
					System.out.println("Error: Cannot receive Auto List!");
			}
		}
		html.append("</body></html>");
		return html.toString();
	}
	
	/**
	 * 
	 * @param autoID Selected Auto
	 * @return page 2 HTML
	 * @throws IOException
	 */
	public String getConfigurePage(String autoID) throws IOException {

		/* Handshake with Server */
		String fromServer = strIn.readLine();// receive Ack from Server: What can we do for you?
		// if (DEBUG)
		// System.out.println("Server Acknowledges: " + fromServer);

		strOut.println("GET_AUTO");// tell Server to wait for Auto ID
		fromServer = strIn.readLine();// receive another Ack from Server: What's the Auto ID?
		// if (DEBUG)
		// System.out.println("Server: " + fromServer);
		strOut.println(autoID);// request auto object with this autoID from Server

		StringBuilder html = new StringBuilder();
		Automobile selectedAuto = null;
		try {
			selectedAuto = (Automobile) objIn.readObject(); // receive Auto Object from Server
			html.append("<!DOCTYPE html><html><head><title>Car Configuration</title></head><body>\n");
			/*
			 * Generate HTML to hold all the necessary values of Auto to send to next Page
			 */
			html.append("<h1>Car Configuration: " + autoID + "</h1><p>Please select options below:</p>");
			html.append("<form action ='" + dispatcherURL
					+ "' method=\"POST\"><input type=\"hidden\" name=\"forward2\" value=\"ConfigureCar3\">");
			html.append("<input type=\"hidden\" name=\"autoID\" value=\"" + autoID + "\">");
			html.append("<input type=\"hidden\" name=\"basePrice\" value=\"" + selectedAuto.getBasePrice() + "\">");
			html.append("<table>");
			String[] opSetList = selectedAuto.getOptionSetList();
			html.append("<input type=\"hidden\" name=\"opSetCount\" value=\"" + opSetList.length + "\">");
			for (int i = 0; i < opSetList.length; i++) {
				html.append("<tr>");
				html.append("<td><input name=\"option_" + i + "\" value=\"" + opSetList[i]
						+ "\" readonly></td><td><select name=\"" + opSetList[i] + "\">");
				String[] opList = selectedAuto.getOptionList(opSetList[i]);
				for (int j = 0; j < opList.length; j++) {
					html.append("<option value=\"" + opList[j] + "_"
							+ selectedAuto.getOptionPrice(opSetList[i], opList[j]) + "\">" + opList[j] + "</option>");
				}
				html.append("</select></td></tr>");

			}
			html.append("</table><input type = \"submit\" value = \"Get Quote\"/></form>");
			html.append("</body></html>");
		} catch (ClassNotFoundException e) {
			html = new StringBuilder("Error1! Please restart!");
		} catch (AutoException e) {
			html = new StringBuilder("Error2! Please restart!");
		}
		return html.toString();
	}

	/**
	 * 
	 * @param request Request sent from previous page
	 * @return page 3 HTML
	 */
	public String getQuotePage(HttpServletRequest request) {
		StringBuilder html = new StringBuilder();
		html.append("<!DOCTYPE html><html><head><title>Car Configuration</title></head><body>");
		
		/*CSS Style in this page*/
		html.append("<style>" + "table, td, th {" + "border: 1px solid black;" + "border-collapse: collapse;"
				+ "padding: 5px;" + "}" + "</style>");
		
		html.append("<p>Below is you quote:</p>");
		float basePrice = Float.parseFloat(request.getParameter("basePrice"));
		String autoID = request.getParameter("autoID");
		html.append("<table><tr><th>" + autoID + "</th><td>Base Price</td><td>$" + basePrice + "</td></tr>");
		double total = basePrice;
		int opSetCount = Integer.parseInt(request.getParameter("opSetCount"));
		for (int i = 0; i < opSetCount; i++) {
			String setName = request.getParameter("option_" + i);
			String opname_price = request.getParameter(setName);
			String opName = opname_price.split("_")[0];
			float opPrice = Float.parseFloat(opname_price.split("_")[1]);
			html.append("<tr><td>" + setName + "</td>");
			html.append("<td>" + opName + "</td>");
			html.append("<td>$" + opPrice + "</td></tr>");
			total += opPrice;
		}
		html.append("<tr><th>Total Cost</th><td></td><td>$" + total + "</td></tr>");
		html.append("</table></body></html>");
		return html.toString();
	}

	/**
	 * 
	 * @throws IOException
	 *             if cannot receive or send data
	 */
	public void processRequest() throws IOException {
		boolean waiting_for_input = false; // indicate if user is required to input something thru console
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromServer;
		String fromUser;
		while ((fromServer = strIn.readLine()) != null) {// listen to receive msg from server
			System.out.println("Server: " + fromServer);
			if (fromServer.equals("Bye!"))
				break;// get out of this method and close connection
			else {
				waiting_for_input = true;
				menu = MAINMENU;
			}
			while (waiting_for_input) {// user need to input based on menu
				if (menu == MAINMENU) {
					displayMainMenu();
					fromUser = stdIn.readLine();
					if (fromUser.equals("1")) {// go to Menu of Uploading new car
						strOut.println(fromUser);
						menu = UPLOAD;
					} else if (fromUser.equals("2")) {// go to Menu of Configuring car if server's fleet is not empty
						strOut.println(fromUser);
						fromServer = strIn.readLine();
						System.out.println("Server: " + fromServer);
						if (fromServer.equals("Error: No Auto to configure. Please Upload new Auto 1st!")) {// server's
																											// fleet is
																											// empty, go
																											// back to
																											// main menu
							menu = MAINMENU;
						} else// go to Configure menu
							menu = CONFIGURE;
					} else if (fromUser.equals("0")) {// user wants to exit
						strOut.println(fromUser);// let the server know to close session
						waiting_for_input = false;// no need for further input from user
					}
				} else if (menu == UPLOAD) {// Upload menu
					System.out.println(upload_menu);
					fromUser = stdIn.readLine();
					CarModelOptionsIO io = new CarModelOptionsIO();
					if (io.sendAutoFromPropFile("src/AutoDataFiles/" + fromUser, objOut)) {// if successfully sent
																							// Properties object
						waiting_for_input = false;
						menu = MAINMENU;// go back to main menu after receiving server's msg
					} else {
						waiting_for_input = true;// loop back to Upload menu until a correct Properties object is sent
					}
				} else if (menu == CONFIGURE) {// Configure menu
					SelectCarOption sc = new SelectCarOption();
					try {
						String[] autoList = (String[]) objIn.readObject();// receive list of cars from server
						sc.displayAutoList(autoList);// display list of car with number to select
						try {
							strOut.println(sc.selectAuto(autoList));// send selected auto object to server
						} catch (IOException e) {
							if (DEBUG)
								System.out.println("Error: Cannot select Auto!");
						}
						Automobile selectedAuto = (Automobile) objIn.readObject();
						sc.configureAuto(selectedAuto);// display list of options and user selects option choice

						try {
							System.out.println("$$$\nTotal = " + selectedAuto.getTotalPrice() + "\n$$$\n");// calculate
																											// total
																											// when all
																											// options
																											// are
																											// configured
						} catch (AutoException e) {
							if (DEBUG)
								System.out.println("Error: Cannot calculate Total for " + selectedAuto.getAutoID());
						}
					} catch (ClassNotFoundException e) {
						if (DEBUG)
							System.out.println("Error: Cannot open Auto List!");
					} catch (IOException e) {
						if (DEBUG)
							System.out.println("Error: Cannot receive Auto List!");
					}
					menu = MAINMENU;
					waiting_for_input = true;// go back to main menu and wait for user input
				}
			}
		}
	}
}
