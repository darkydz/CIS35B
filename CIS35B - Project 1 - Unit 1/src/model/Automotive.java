package model;

import java.io.Serializable;

import exception.AutoException;

/**
 * 
 * @author Anh
 *
 */
public class Automotive implements Serializable {
	private String name;
	private float baseprice;
	private OptionSet opset[];
	
	public Automotive () {
		name = "";
		baseprice = 0;
		opset = new OptionSet[0];
	}
	
	/**
	 * Constructor of Auto class
	 * @param n: name of Auto
	 * @param p: base price of Auto
	 * @param size: number of available Options of this Auto
	 */
	public Automotive(String n, float p, int size) {
		name = n;
		baseprice = p;
		opset = new OptionSet[size];
	}

	/**
	 * @return name of Auto
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return base price of Auto
	 */
	public float getBasePrice() {
		return baseprice;
	}

	/**
	 * @param i: index of OptionSet
	 * @return OptionSet at index i. If index is not exists, return NULL
	 */
	public OptionSet getOptionSets(int i) {
		return opset[i];
	}

	/**
	 * @param n: new name of Auto
	 */
	public void setName(String n) {
		name = n;
	}

	/**
	 * @param p: new base price of Auto
	 */
	public void setBasePrice(int p) {
		baseprice = p;
	}

	/**
	 * Change OptionSet name and price at index i
	 * @param i: index of the OptionSet array
	 * @param opsetName: new OptionSet name
	 * @param opSetSize: new OptionSet size
	 */
	public void setOptionSet(int i, String opsetName, int opSetSize) {
		if (i < opset.length)
			opset[i] = new OptionSet(opsetName, opSetSize);
	}
	
	/**
	 * Set an Option for the OptionSet
	 * @param i index of OptionSet
	 * @param j index of Option
	 * @param newOptionName
	 * @param newPrice
	 */
	public void setOption(int i, int j, String opName, float price) {
		if (opset[i] != null)
			opset[i].setOption(j, opName, price);		
	}
	
	/**
	 * Search for OptionSet by its name
	 * @param opsetName: name of OptionSet
	 * @return current index, if OptionSet exists. Otherwise, -1
	 */
	public int findOptionSet(String opsetName) throws AutoException{
		for (int i = 0; i < opset.length; i++) {
			if (opset[i].getName().equals(opsetName)) return i;
		}
		throw new AutoException(1);
	}
	
	/**
	 * Find an OptionSet by its name and change its values if exists
	 * @param opsetName: existing OptionSet name
	 * @param newName: new OptionSet name
	 * @param newSize: new OptionSet size
	 * @return the index if exists. Otherwise, -1
	 * @throws AutoException 
	 */
	public int updateOptionSet(String opsetName, String newName, int newSize) throws AutoException
	{
		int index = findOptionSet(opsetName); 
		if (index != -1) {
			setOptionSet(index, newName, newSize);
		}
		return index;
	}
	
	public void updateOptionSetName(String optionSetname, String newName) throws AutoException{
		try {
			opset[findOptionSet(optionSetname)].setName(newName);
		}
		catch (AutoException e) {
			throw new AutoException(1);
		}
	}
	public void updateOptionPrice(String optionname, String option, float newprice) throws AutoException {
		try {
			opset[findOptionSet(optionname)].updateOptionPrice(option,newprice);	
		}
		catch (AutoException e) {
			throw new AutoException(2);
		}
			
	}
	/**
	 * Delete an OptionSet at index i
	 * @param i: index of OptionSet array
	 * @return the index if exists. Otherwise, -1
	 */
	public int deleteOptionSet(int i) {
		if (i < opset.length) {
			opset[i] = new OptionSet();
			return i;
		}
		else return -1;
	}
	
	/**
	 * Display Auto's data
	 */
	public void print() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: ");
		sb.append(name);
		sb.append("\nBase Price: ");
		sb.append(baseprice);
		sb.append("\nOptions: ");
		for (int i = 0; i < opset.length; i++) {
			sb.append(opset[i].print());
		}
		System.out.println(sb.toString());
	}
}
