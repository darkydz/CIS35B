package adapter;

import exception.AutoException;
import model.Automobile;
import util.FileIO;

public abstract class ProxyAutomobile {
	private static Automobile a1;
	
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName) throws AutoException {
		a1.updateOptionSetName(OptionSetname, newName);
	}

	public void updateOptionPrice(String Modelname, String OptionSetname, String Option, float newprice) throws AutoException{
		a1.updateOptionPrice(OptionSetname, Option, newprice);
		
	}

	public void buildAuto(String filename) {
		FileIO io = new FileIO();
		try {
			a1 = io.buildAutomobileObject(filename);
		} catch (AutoException e) {
			// TODO Auto-generated catch block
			e.fix(e.getErrorNumber());
		}
		
	}

	public void printAuto(String modelName) {
		// TODO Auto-generated method stub
		a1.print();
	}
	
	public void fix (int errno) {
		AutoException ae = new AutoException(errno);
		ae.fix(errno);
	}
}
