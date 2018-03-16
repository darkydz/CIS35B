package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exception.AutoException;
import model.Automobile;

public class SelectCarOption implements SocketClientConstants{
	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	public void displayAutoList(String[] autoList) {
		System.out.println("Please Select an Auto below to configure:");
		for (int i = 0; i < autoList.length; i++)
			System.out.println((i + 1) + " - " + autoList[i]);

	}
	
	/**
	 * Display list of autos with number for user to select
	 * @param autoList
	 * @return selected Auto ID
	 * @throws IOException
	 */
	public String selectAuto(String[] autoList) throws IOException{
		String fromUser = stdIn.readLine();
		int configure_option_1 = Integer.parseInt(fromUser);
		while (configure_option_1 == 0 || configure_option_1 > autoList.length) {//loop until user select the appropriate option
			displayAutoList(autoList);
			fromUser = stdIn.readLine();
			configure_option_1 = Integer.parseInt(fromUser);
		}
		return autoList[(configure_option_1 - 1)];
	}
	
	/**
	 * Display list of option values with number for user to select
	 * @param opList
	 * @param setName
	 * @return selected option
	 * @throws IOException
	 */
	public String selectOption(String[] opList, String setName) throws IOException{
		String fromUser = stdIn.readLine();
		int configure_option_2 = Integer.parseInt(fromUser);
		while (configure_option_2 == 0 || configure_option_2 > opList.length) {//loop until user select the appropriate option
			displayOptionList(opList, setName);
			fromUser = stdIn.readLine();
			configure_option_2 = Integer.parseInt(fromUser);
		}
		return opList[(configure_option_2 - 1)];
	}
	
	/**
	 * Display options from auto to options for user to select	
	 * @param selectedAuto
	 */
	public void configureAuto(Automobile selectedAuto) {
		displayAutoInfo(selectedAuto);
		String[] setList = selectedAuto.getOptionSetList();
		for (int i = 0; i < setList.length; i++) {
			String setName = setList[i];
			String[] opList = new String[0];
			try {
				opList = selectedAuto.getOptionList(setName);
			} catch (AutoException e1) {
				if (DEBUG)
					System.out.println("Error: Cannot find " + setName);
			}
			displayOptionList(opList, setName);

			try {
				selectedAuto.setOptionChoice(setName, selectOption(opList, setName));//set choice when user selects the appropriate option
			} catch (AutoException e) {
				if (DEBUG)
					System.out.println("Error: Cannot set Option Choice for " + setName);
			} catch (IOException e) {
				if (DEBUG)
					System.out.println("Error: Cannot select Option for " + setName);
			}
		}
	}

	/**
	 * Print auto info
	 * @param auto
	 */
	public void displayAutoInfo(Automobile auto) {
		auto.print();
	}

	/**
	 * Print list of option values with number for user to select
	 * @param optionList
	 * @param setName
	 */
	public void displayOptionList(String[] optionList, String setName) {
		System.out.println("Please Select an Option for " + setName);
		for (int i = 0; i < optionList.length; i++)
			System.out.println((i + 1) + " - " + optionList[i]);
	}
}
