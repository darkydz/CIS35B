package client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Properties;

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

	public void displayMainMenu() {
		System.out.println(main_menu);
	}

	public void processRequest() throws IOException {
		boolean waiting_for_input = false;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromServer;
		String fromUser;
		while ((fromServer = strIn.readLine()) != null) {
			System.out.println("Server: " + fromServer);
			if (fromServer.equals("Bye!"))
				break;
			else {
				waiting_for_input = true;
				menu = MAINMENU;
			}
			while (waiting_for_input) {
				if (menu == MAINMENU) {
					displayMainMenu();
					fromUser = stdIn.readLine();
					if (fromUser.equals("1")) {
						strOut.println(fromUser);
						menu = UPLOAD;
					} else if (fromUser.equals("2")) {
						strOut.println(fromUser);
						fromServer = strIn.readLine();
						System.out.println("Server: " + fromServer);
						if (fromServer.equals("Error: No Auto to configure. Please Upload new Auto 1st!")) {
							menu = MAINMENU;
						} else
							menu = CONFIGURE;
					} else if (fromUser.equals("0")) {
						strOut.println(fromUser);
						waiting_for_input = false;
					}
				} else if (menu == UPLOAD) {
					System.out.println(upload_menu);
					fromUser = stdIn.readLine();
					CarModelOptionsIO io = new CarModelOptionsIO();
					if (io.sendAutoFromPropFile("src/AutoDataFiles/" + fromUser, objOut)) {
						waiting_for_input = false;
						menu = MAINMENU;
					} else {
						waiting_for_input = true;
					}
				} else if (menu == CONFIGURE) {
					SelectCarOption sc = new SelectCarOption();
					try {
						String[] autoList = (String[]) objIn.readObject();
						sc.displayAutoList(autoList);
						try {
							strOut.println(sc.selectAuto(autoList));
						} catch (IOException e) {
							if (DEBUG)
								System.out.println("Error: Cannot select Auto!");
						}
						Automobile selectedAuto = (Automobile) objIn.readObject();
						sc.configureAuto(selectedAuto);

						try {
							System.out.println("$$$\nTotal = " + selectedAuto.getTotalPrice() + "\n$$$\n");
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
					waiting_for_input = true;
				}
			}
		}
	}
}
