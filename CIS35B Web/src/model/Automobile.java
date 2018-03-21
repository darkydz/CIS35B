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
public class Automobile implements Serializable {
	private boolean isAvailableForEditing = true; 
	private String model;
	private String make;
	private int year;
	private float baseprice;
	private ArrayList<OptionSet> opset;
	private ArrayList<Option> choice;
	
	public Automobile () {
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
	public Automobile(String mk, String md, int y, float p, int size) {
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
	
	public Automobile(String mk, String md, int y, float p) {
		make = mk;
		model = md;
		year = y;
		baseprice = p;
		opset = new ArrayList<OptionSet>();
	}

	public boolean isEditable() {
		return isAvailableForEditing;
	}
	
	public void setEditable (boolean val) {
		isAvailableForEditing = val;
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
	
	/**
	 * 
	 * @param setName
	 * @return
	 * @throws AutoException
	 */
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
		double total = baseprice;
		for (OptionSet s:opset) {
			if (s == null) throw new AutoException(205);
			else {
				try {
					total += s.getOptionChoicePrice();
				}
				catch (AutoException e) {
					throw new AutoException(205);
				}
			}
		}
		return total;
	}
	
	/**
	 * 
	 * @return an ArrayList of Option names
	 */
	public String[] getOptionSetList() {
		String[] opsetList = new String[opset.size()];
		for (int i = 0; i < opset.size(); i++) {
			opsetList[i] = opset.get(i).getName();
		}
		return opsetList;
	}
	
	/**
	 * 
	 * @return an ArrayList of Option values
	 */
	public String[] getOptionList(String setName) throws AutoException {
		return opset.get(findOptionSet(setName)).getOptionList();
	}
	
	public float getOptionPrice(String setName, String opName) {
		try {
			return opset.get(findOptionSet(setName)).getOptionPrice(opName);
		} catch (AutoException e) {
			e.fix(18);
		}
		return 0;
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
	 * Add an empty OptionSet with new name
	 * @param setName
	 */
	public void addOptionSet(String setName) {
		OptionSet newOp = new OptionSet(setName);
		if (!opset.contains(newOp)) opset.add(newOp);
	}
	
	/**
	 * 
	 * @param setName
	 * @param opName
	 * @param opPrice
	 * @throws AutoException pass to downstream
	 */
	public void addOption(String setName, String opName, float opPrice) throws AutoException
	{
		opset.get(findOptionSet(setName)).addOption(opName, opPrice);
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
	
	/**
	 * 
	 * @param setName
	 * @param optionName
	 * @throws AutoException
	 */
	public void setOptionChoice(String setName, String optionName) throws AutoException {
		try {
			opset.get(findOptionSet(setName)).setOptionChoice(optionName);
		} catch (AutoException e) {
			throw new AutoException(e.getErrorNumber());
		}
	}
	
	/**
	 * Search for OptionSet by its name
	 * @param setName: name of OptionSet
	 * @return current index, if OptionSet exists. Otherwise, -1
	 * @throws AutoException will always throw 18 regardless of what error it catches from upstream
	 */
	public int findOptionSet(String setName) throws AutoException{
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(setName)) return i;
		}
		throw new AutoException(18);
	}
	
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
	 * Update Option Name
	 * @param optionSetname
	 * @param option
	 * @param newOp
	 */
	public synchronized void updateOptionName(String threadName, String optionSetname, String option, String newOp) {
		System.out.println(threadName + " attempts to update Auto!!!");
		while (!isAvailableForEditing)
		{
			System.out.println(threadName + " waits...");
			try {
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		isAvailableForEditing = false;
		try {
			opset.get(findOptionSet(optionSetname)).updateOptionName(option,newOp);
			System.out.println(threadName + " updated Auto!!!");
			print();
		}
		catch (AutoException e) {
			System.out.println(threadName + " can't update Auto!!!");
		}
		finally {
			isAvailableForEditing = true;
			System.out.println(threadName + " set Auto to be editable");
			notifyAll();
			System.out.println(threadName + " notifyAll");
		}
	}
	
	/**
	 * Update Option Name
	 * @param optionSetname
	 * @param option
	 * @param newOp
	 */
	public void updateOptionNamenotSync(String threadName, String optionSetname, String option, String newOp) {
		System.out.println(threadName + " attempts to update Auto!!!");
		while (!isAvailableForEditing)
		{
			System.out.println(threadName + " waits...");
			try {
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		isAvailableForEditing = false;
		try {
			opset.get(findOptionSet(optionSetname)).updateOptionName(option,newOp);
			System.out.println(threadName + " updated Auto!!!");
			print();
		}
		catch (AutoException e) {
			System.out.println(threadName + " can't update Auto!!!");
		}
		finally {
			isAvailableForEditing = true;
			System.out.println(threadName + " set Auto to be editable");
			notifyAll();
			System.out.println(threadName + " notifyAll");
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
