package client;

import model.Automobile;

public class SelectCarOption extends ClientHelper {
	public void displayAutoList(String[] autoList) {
		if (autoList.length > 0)
		{
			System.out.println("Please Select an Auto below to configure:");
			for(int i = 0;i<autoList.length; i++)
				System.out.println((i+1) + " - " + autoList[i]);
		}
		else System.out.println("There is no Auto to Configure. Please Upload new one first!");
	}
	
	public void displayAutoInfo(Automobile auto)
	{
		auto.print();
	}
}
