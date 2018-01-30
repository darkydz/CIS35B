package adapter;

import model.Automobile;
import util.FileIO;

public abstract class ProxyAutomobile {
	private static Automobile a1;
	
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName) {
		a1.updateOptionSetName(OptionSetname, newName);
	}

	public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice){
		a1.updateOptionPrice(Optionname, Option, newprice);
		
	}

	public void buildAuto(String filename) {
		FileIO io = new FileIO();
		System.out.println("1. Test Working FordZTW");
		String modelName = "FordZTW";
		a1 = io.buildAutomobileObject("src/AutoDataFiles/" + modelName + ".txt");
		
	}

	public void printAuto(String modelName) {
		// TODO Auto-generated method stub
		a1.print();
	}
}
