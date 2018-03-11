package client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.Properties;

public class ClientHelper {
	private static final int MENU = -1;
	private static final int EXIT = 0;
	private static final int UPLOAD = 1;
	private static final int CONFIGURE = 2;
	
	private int state = MENU;
	
	private String main_menu = "Please Select an Option below:\n1 - Upload New Car\n2 - Configure An Existing Car\n0 - Exit";
	private String upload_menu = "Please Enter Auto filename to upload:";
	private String configure_menu = "Please Select an Auto below to configure:";
	
	public void displayMainMenu() {
		System.out.println(main_menu);
	}
	
	public String processResponse(String response) {
		String request = "";
		return request;
	}
	
	public String processRequest(String resquest) {
		String response = "";
		return response;
	}
	
	public void processInput(int option, int subOption) {
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		switch (option)
		{
			case MENU:
				displayMainMenu();
				try {
					while ((input = stdIn.readLine()) != null) {
//						if (input > )
					}
				} catch (IOException e) {
					
				}
				break;
			
		}
	}
}
