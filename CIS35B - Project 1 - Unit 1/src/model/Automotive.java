package model;

import java.io.Serializable;
import java.util.ArrayList;

import exception.AutoException;
import model.OptionSet.Option;

/**
 * 
 * @author Anh
 *
 */
public class Automotive implements Serializable {
	private String model;
	private String make;
	private int year;
	private float baseprice;
	private ArrayList<OptionSet> opset;
	private ArrayList<Option> choice;
	
	public Automotive () {
		model = "";
		make = "";
		year = 2018;
		baseprice = 0;
		opset = new ArrayList<OptionSet>();
	}
	
	/**
	 * Constructor of Auto class
	 * @param n: name of Auto
	 * @param p: base price of Auto
	 * @param size: number of available Options of this Auto
	 */
	public Automotive(String mk, String md, int y, float p, int size) {
		make = mk;
		model = md;
		year = y;
		baseprice = p;
		opset = new ArrayList<OptionSet>();
		for (int i = 0; i < size; i++)
		{
			opset.add(new OptionSet());
		}
	}
	
	/**
	 * 
	 * @return make of Auto
	 */
	public String getMake() {
		return make;
	}
	
	/**
	 * @return model of Auto
	 */
	public String getModel() {
		return model;
	}
	
	/**
	 * 
	 * @return year of Auto
	 */
	public int getYear() {
		return year;
	}

	public String getAutoID()
	{
		return make + " " + model + " " + String.valueOf(year);
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
		if (i < opset.size())
			return opset.get(i);
		else return null;
	}
	
	public String getOptionChoice(String setName) throws AutoException{
		int index = 0;
		try {
			index = findOptionSet(setName);
		} catch (AutoException e) {
			e.fix(e.getErrorNumber());
		}
		return opset.get(index).getOptionChoiceName();
	}
	
	
	/**
	 * 
	 * @param setName
	 * @return
	 * @throws AutoException pass Exception to downstream
	 */
	public float getOptionChoicePrice(String setName) throws AutoException
	{
		return opset.get(findOptionSet(setName)).getOptionChoicePrice();
	}
	
	/**
	 * 
	 * @return total of all option choices
	 * @throws AutoException 
	 */
	public double getTotalPrice() throws AutoException {
		double total = 0;
		for (OptionSet s:opset) {
			total += s.getOptionChoicePrice();
		}
		return total;
	}
	
	/**
	 * @param n: new name of Auto
	 */
	public void setName(String n) {
		model = n;
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
	 * @param setName: new OptionSet name
	 * @param opSetSize: new OptionSet size
	 */
	public void setOptionSet(int i, String setName, int opSetSize) {
		if (i < opset.size())
			opset.set(i, new OptionSet(setName, opSetSize));
	}
	
	/**
	 * Set an Option for the OptionSet
	 * @param i index of OptionSet
	 * @param j index of Option
	 * @param newOptionName
	 * @param newPrice
	 */
	public void setOption(int i, int j, String opName, float price) {
		if (i < opset.size())
			opset.get(i).setOption(j, opName, price);		
	}
	
	public void setOptionChoice(String setName, String optionName) {
		try {
			opset.get(findOptionSet(setName)).setOptionChoice(optionName);
		} catch (AutoException e) {
			e.fix(e.getErrorNumber());
		}
	}
	
	/**
	 * Search for OptionSet by its name
	 * @param setName: name of OptionSet
	 * @return current index, if OptionSet exists. Otherwise, -1
	 * @throws AutoException will always throw 201 regardless of what error it catches from upstream
	 */
	public int findOptionSet(String setName) throws AutoException{
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(setName)) return i;
		}
		throw new AutoException(18);
	}
	
//	/**
//	 * Find an OptionSet by its name and change its values if exists
//	 * @param setName: existing OptionSet name
//	 * @param newName: new OptionSet name
//	 * @param newSize: new OptionSet size
//	 * @return the index if exists. Otherwise, -1
//	 * @throws AutoException pass exception to downstream
//	 */
//	public int updateOptionSet(String setName, String newName, int newSize) throws AutoException
//	{
//		int index = findOptionSet(setName); 
//		if (index != -1) {
//			setOptionSet(index, newName, newSize);
//		}
//		return index;
//	}
	
	/**
	 * Self-explanatory
	 * @param optionSetname
	 * @param newName
	 * @throws AutoException will always throw 201 regardless of what error it catches from upstream
	 */
	public void updateOptionSetName(String optionSetname, String newName) throws AutoException{
		try {
			opset.get(findOptionSet(optionSetname)).setName(newName);
		}
		catch (AutoException e) {
			throw new AutoException(201);
		}
	}
	
	/**
	 * Self-explanatory
	 * @param optionname
	 * @param option
	 * @param newprice
	 * @throws AutoException will always throw 202 regardless of what error it catches from upstream
	 */
	public void updateOptionPrice(String optionSetname, String option, float newprice) throws AutoException {
		try {
			opset.get(findOptionSet(optionSetname)).updateOptionPrice(option,newprice);	
		}
		catch (AutoException e) {
			throw new AutoException(202);
		}
			
	}
	/**
	 * Delete an OptionSet at index i
	 * @param i: index of OptionSet array
	 * @return the index if exists. Otherwise, -1
	 */
	public int deleteOptionSet(int i) {
		if (i < opset.size()) 
		{
			opset.set(i,new OptionSet());
			return i;
		}
		else return -1;
	}
	
	/**
	 * Display Auto's data
	 */
	public void print() {
		StringBuilder sb = new StringBuilder();
		sb.append("Make: ");
		sb.append(make);
		sb.append("\nModel: ");
		sb.append(model);
		sb.append("\nYear: ");
		sb.append(year);
		sb.append("\nBase Price: ");
		sb.append(baseprice);
		sb.append("\nOptions: ");
		for (int i = 0; i < opset.size(); i++) {
			sb.append(opset.get(i).print());
		}
		System.out.println(sb.toString());
	}
}
