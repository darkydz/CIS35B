package adapter;

import java.util.LinkedHashMap;

import exception.AutoException;
import model.Automobile;
import util.FileIO;

public abstract class ProxyAutomobile {
	private static LinkedHashMap<String, Automobile> autos = new LinkedHashMap<String, Automobile>();

	/**
	 * Self-explanatory
	 * 
	 * @param Modelname
	 * @param OptionSetname
	 * @param newName
	 * @throws AutoException
	 */
	public void updateOptionSetName(String autoID, String OptionSetname, String newName) throws AutoException {
		try {
		getAuto(autoID).updateOptionSetName(OptionSetname, newName);
		}
	 catch (AutoException e) {
		throw new AutoException(201);
	}
	}

	/**
	 * Self-explanatory
	 * 
	 * @param Modelname
	 * @param OptionSetname
	 * @param Option
	 * @param newprice
	 * @throws AutoException
	 */
	public void updateOptionPrice(String autoID, String OptionSetname, String Option, float newprice)
			throws AutoException {
		try {
			getAuto(autoID).updateOptionPrice(OptionSetname, Option, newprice);
		} catch (AutoException e) {
			throw new AutoException(202);
		}
	}

	/**
	 * Self-explanatory
	 * 
	 * @param filename
	 */
	public void buildAuto(String filename) {
		FileIO io = new FileIO();
		try {
			addAuto(io.buildAutomobileObject(filename));
		} catch (AutoException e) {
			e.fix(e.getErrorNumber());
		}
	}

	/**
	 * In the future, will take a model name to print its content. Right now, always
	 * print variable a1
	 * 
	 * @param modelName
	 * @throws AutoException
	 */
	public void printAuto(String autoID) {
		try {
			getAuto(autoID).print();
		} catch (AutoException e) {
			e.fix(e.getErrorNumber());
		}
	}

	/**
	 * Take an error number and process that error
	 * 
	 * @param errno
	 */
	public void fix(int errno) {
		AutoException ae = new AutoException(errno);
		ae.fix(errno);
	}

	public double getTotal(String autoID) throws AutoException {
		return getAuto(autoID).getTotalPrice();
	}

	public void setChoice(String autoID, String setName, String optionName) throws AutoException {
		try {
			getAuto(autoID).setOptionChoice(setName, optionName);
		} catch (AutoException e) {
			throw new AutoException(203);
		}
	}

	public void addAuto(Automobile a) {
		String autoID = a.getAutoID();
		if (autos.isEmpty() || !autos.containsKey(autoID))
			autos.put(autoID, a);
	}

	public Automobile getAuto(String autoID) throws AutoException {
		Automobile a = autos.get(autoID);
		if (a != null) {
			return a;
		} else
			throw new AutoException(204);
	}
}
