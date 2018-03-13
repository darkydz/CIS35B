package client;

import java.io.*;

import exception.AutoException;
import model.Automobile;

public class ClientHelper implements SocketClientConstants {
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
	 * @throws IOException if cannot receive or send data
	 */
	public void processRequest() throws IOException {
		boolean waiting_for_input = false; //indicate if user is required to input something thru console 
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromServer;
		String fromUser;
		while ((fromServer = strIn.readLine()) != null) {//listen to receive msg from server
			System.out.println("Server: " + fromServer);
			if (fromServer.equals("Bye!"))
				break;//get out of this method and close connection
			else {
				waiting_for_input = true;
				menu = MAINMENU;
			}
			while (waiting_for_input) {//user need to input based on menu
				if (menu == MAINMENU) {
					displayMainMenu();
					fromUser = stdIn.readLine();
					if (fromUser.equals("1")) {//go to Menu of Uploading new car
						strOut.println(fromUser);
						menu = UPLOAD;
					} else if (fromUser.equals("2")) {//go to Menu of Configuring car if server's fleet is not empty
						strOut.println(fromUser);
						fromServer = strIn.readLine();
						System.out.println("Server: " + fromServer);
						if (fromServer.equals("Error: No Auto to configure. Please Upload new Auto 1st!")) {//server's fleet is empty, go back to main menu
							menu = MAINMENU;
						} else//go to Configure menu
							menu = CONFIGURE;
					} else if (fromUser.equals("0")) {//user wants to exit
						strOut.println(fromUser);//let the server know to close session
						waiting_for_input = false;//no need for further input from user
					}
				} else if (menu == UPLOAD) {//Upload menu
					System.out.println(upload_menu);
					fromUser = stdIn.readLine();
					CarModelOptionsIO io = new CarModelOptionsIO();
					if (io.sendAutoFromPropFile("src/AutoDataFiles/" + fromUser, objOut)) {//if successfully sent Properties object
						waiting_for_input = false;
						menu = MAINMENU;//go back to main menu after receiving server's msg
					} else {
						waiting_for_input = true;//loop back to Upload menu until a correct Properties object is sent
					}
				} else if (menu == CONFIGURE) {//Configure menu
					SelectCarOption sc = new SelectCarOption();
					try {
						String[] autoList = (String[]) objIn.readObject();//receive list of cars from server
						sc.displayAutoList(autoList);//display list of car with number to select
						try {
							strOut.println(sc.selectAuto(autoList));//send selected auto object to server
						} catch (IOException e) {
							if (DEBUG)
								System.out.println("Error: Cannot select Auto!");
						}
						Automobile selectedAuto = (Automobile) objIn.readObject();
						sc.configureAuto(selectedAuto);//display list of options and user selects option choice

						try {
							System.out.println("$$$\nTotal = " + selectedAuto.getTotalPrice() + "\n$$$\n");//calculate total when all options are configured
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
					waiting_for_input = true;//go back to main menu and wait for user input
				}
			}
		}
	}
}
