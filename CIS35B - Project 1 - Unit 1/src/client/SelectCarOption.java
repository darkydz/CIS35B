package client;

import model.Automobile;

public class SelectCarOption extends ClientHelper {
	public void displayAutoList(String[] autoList) {
		System.out.println("Please Select an Auto below to configure:");
		for (int i = 0; i < autoList.length; i++)
			System.out.println((i + 1) + " - " + autoList[i]);

	}

	public void displayAutoInfo(Automobile auto) {
		auto.print();
	}

	public void displayOptionList(String[] optionList, String setName) {
		System.out.println("Please Select an Option for " + setName);
		for (int i = 0; i < optionList.length; i++)
			System.out.println((i + 1) + " - " + optionList[i]);
	}
}
