package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

import adapter.ProxyAutomobile;
import exception.AutoException;
import util.FileIO;

public class ServerHelper extends ProxyAutomobile {
	private static final int MENU = 0;
	private static final int UPLOAD = 1;
	private static final int CONFIGURE = 2;
	private static final int EXIT = 3;

	private int state = MENU;

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
			System.out.println(aList[i]);
		}
	}

	public void sendAutoObject(String autoID, ObjectOutputStream out) {
		try {
			out.writeObject(autos.getAuto(autoID));
		} catch (IOException e) {
			System.err.println("Error: Cannot send Auto Object to Client!");
		} catch (AutoException e) {
			// e.fix(e.getErrorNumber());
			System.err.println("Error: Cannot find " + autoID + "!");
		}
	}

	public String processClientRequest(String resquest) {
		String response = "";
		return response;
	}

}
