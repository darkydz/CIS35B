package model;

import java.io.Serializable;

import model.OptionSet.Option;

/**
 * 
 * @author Anh
 *
 */
public class Auto implements Serializable {
	private String name;
	private int baseprice;
	private OptionSet opset[];
	
	public Auto () {
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
	public Auto(String n, int p, int size) {
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
	public int getBasePrice() {
		return baseprice;
	}

	/**
	 * @param i: index of OptionSet
	 * @return OptionSet at index i
	 */
	public OptionSet getOptionSets(int i) {
//		needs to validate index in the future
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
		opset[i] = new OptionSet(opsetName, opSetSize);
	}
	
	/**
	 * Search for OptionSet by its name
	 * @param opsetName: name of OptionSet
	 * @return current index, if OptionSet exists. Otherwise, -1
	 */
	public int findOptionSet(String opsetName) {
		for (int i = 0; i < opset.length; i++) {
			if (opset[i].getName().equals(opsetName)) return i;
		}
		return -1;
	}
	
	/**
	 * Find an OptionSet by its name and change its values if exists
	 * @param opsetName: existing OptionSet name
	 * @param newName: new OptionSet name
	 * @param newSize: new OptionSet size
	 * @return the index if exists. Otherwise, -1
	 */
	public int updateOptionSet(String opsetName, String newName, int newSize)
	{
		int index = findOptionSet(opsetName); 
		if (index != -1) {
			setOptionSet(index, newName, newSize);
		}
		return index;
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
	 * 
	 */
	public void displayInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("Name: ");
		sb.append(name);
		sb.append("\nBase Price: ");
		sb.append(baseprice);
		sb.append("\nOptions: ");
		for (int i = 0; i < opset.length; i++) {
			OptionSet curOpSet = opset[i];
			sb.append("\n- ");
			sb.append(curOpSet.getName());
			sb.append(":");
			for (int j = 0; j < curOpSet.getSize(); j++) {
				sb.append("\n\t");
				sb.append("+ ");
				sb.append(opset[i].getOption(j).getName());
				sb.append(" : ");
				sb.append(opset[i].getOption(j).getPrice());

			}
		}
		System.out.println(sb.toString());
	}

}
