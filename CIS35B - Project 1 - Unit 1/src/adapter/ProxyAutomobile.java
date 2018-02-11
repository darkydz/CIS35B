package adapter;

import java.util.LinkedHashMap;

import exception.AutoException;
import model.Automobile;
import util.FileIO;

public abstract class ProxyAutomobile {
	private static Automobile a1;
	private static LinkedHashMap<String,Automobile> autos = new LinkedHashMap<String, Automobile>();
	
	/**
	 * Self-explanatory
	 * @param Modelname
	 * @param OptionSetname
	 * @param newName
	 * @throws AutoException
	 */
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName) throws AutoException {
		a1.updateOptionSetName(OptionSetname, newName);
	}

	/**
	 * Self-explanatory
	 * @param Modelname
	 * @param OptionSetname
	 * @param Option
	 * @param newprice
	 * @throws AutoException
	 */
	public void updateOptionPrice(String Modelname, String OptionSetname, String Option, float newprice)
			throws AutoException {
		a1.updateOptionPrice(OptionSetname, Option, newprice);
	}
	
	/**
	 * Self-explanatory
	 * @param filename
	 */
	public void buildAuto(String filename) {
		FileIO io = new FileIO();
		try {
			a1 = io.buildAutomobileObject(filename);
			addAuto(a1);
		} catch (AutoException e) {
			e.fix(e.getErrorNumber());
		}
	}

	/**
	 * In the future, will take a model name to print its content. Right now, always print variable a1
	 * @param modelName
	 * @throws AutoException 
	 */
	public void printAuto(String autoID) {
		Automobile a = getAuto(autoID); 
		if ( a != null)
			a.print();
		else System.out.println("Auto cannot be found!");
	}

	/**
	 * Take an error number and process that error
	 * @param errno
	 */
	public void fix(int errno) {
		AutoException ae = new AutoException(errno);
		ae.fix(errno);
	}
	
	public double getTotal(String autoID) throws AutoException {
		return a1.getTotalPrice();
	}
	
	public void setChoice(String autoID, String setName, String optionName) {
		getAuto(autoID).setOptionChoice(setName, optionName);
	}
	
	public void addAuto(Automobile a) {
		String autoID = a.getAutoID();
		if (autos.isEmpty() || !autos.containsKey(autoID))
			autos.put(autoID, a);
	}
	
	public Automobile getAuto(String autoID) {
		return autos.get(autoID);
	}
}
