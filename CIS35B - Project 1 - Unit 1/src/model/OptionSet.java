package model;

import java.io.Serializable;
import java.util.ArrayList;

import exception.AutoException;

public class OptionSet implements Serializable {
	private ArrayList<Option> opt;
	private String name;
	private Option choice;
	
	public OptionSet() {
		name = "";
		opt = new ArrayList<Option>();
	}
		
	/**
	 * @param setName: name of OptionSet
	 * @param setSize: size of OptionSet
	 */
	public OptionSet(String setName, int setSize) {
		name = setName;
		opt = new ArrayList<Option>();
		for (int i = 0; i<setSize; i++) {
			opt.add(new Option());
		}
	}

	public class Option implements Serializable {
		private String optionName;
		private float price;
		
		public Option() {
			optionName = "";
			price = 0;
		}
		
		/**
		 * Constructor for Option
		 * @param n: name of Option
		 * @param p: price of Option
		 */
		public Option(String n, float p) {
			optionName = n;
			price = p;
		}

		/**
		 * @return name of Option
		 */
		protected String getName() {
			return optionName;
		}

		/**
		 * @return price of Option
		 */
		protected float getPrice() {
			return price;
		}

		/**
		 * Change name of Option
		 * @param n: new name of Option 
		 */
		protected void setName(String n) {
			optionName = n;
		}
		
		/**
		 * Change price of Option
		 * @param p: new price of Option
		 */
		protected void setPrice(float p) {
			price = p;
		}
		
		/**
		 * 
		 * @return formatted String of Option
		 */
		protected String print()
		{
			StringBuilder sb = new StringBuilder();
			sb.append("\n\t\t");
			sb.append("+ ");
			sb.append(this.getName());
			sb.append(" : ");
			sb.append(this.getPrice());
			return sb.toString();
		}
	}

	/**
	 * @return name of OptionSet
	 */
	protected String getName() {
		return name;
	}

	/**
	 * @param i: index of Option in OptionSet
	 * @return Option at index i. If index i not exists, return NULL
	 */
	protected Option getOption(int i) {
		return opt.get(i);
	}
	
	/**
	 * 
	 * @return selected Option for this Set
	 */
	protected Option getOptionChoice() {
		return choice;
	}
	
	/**
	 * 
	 * @return name of option choice
	 */
	protected String getOptionChoiceName() throws AutoException {
		if (choice != null)
			return choice.getName();
		else throw new AutoException(20);
	}
	
	protected float getOptionChoicePrice() throws AutoException {
		if (choice != null)
			return choice.getPrice();
		else throw new AutoException(20);
	}

	/**
	 * @param n: new name of OptionSet 
	 */
	protected void setName(String n) {
		name = n;
	}

	/**
	 * Change Option at index i
	 * @param i: index in OptionSet
	 * @param opName: name of Option
	 * @param opPrice: price of Option
	 */
	protected void setOption(int i, String opName, float opPrice) {
//		if (opt[i]!=null)
		if (i < opt.size())
			opt.set(i, new Option(opName, opPrice));
	}
	
	/**
	 * Set selected option of this set
	 * @param optionName
	 */
	protected void setOptionChoice(String optionName)
	{
		try {
			choice = opt.get(findOption(optionName));
		} catch (AutoException e) {
			e.fix(e.getErrorNumber());
		}
	}
	
	/**
	 * Search for Option by its name
	 * @param opName: existing Option name 
	 * @return current index, if Option exists. Otherwise, -1 
	 * @throws AutoException a fake error to downstream to trigger something else
	 */
	protected int findOption(String opName) throws AutoException{
		for (int i=0; i<opt.size(); i++){
			if (opt.get(i).getName().equals(opName)) return i;
		}
		throw new AutoException(19);
	}
	
	/**
	 * Find an Option by its name and change its values if exists
	 * @param opName: existing Option name
	 * @param newName: new Option name
	 * @param newPrice: new Option price
	 * @return the index if exists. Otherwise, -1
	 * @throws AutoException pass exception to downstream
	 */
	protected int updateOption(String opName, String newName, float newPrice) throws AutoException
	{
		int index = findOption(opName); 
		if (index != -1) {
			setOption(index, newName, newPrice);
		}
		return index;
	}
	
	/**
	 * 
	 * @param option
	 * @param newprice
	 * @throws AutoException pass exception to downstream
	 */
	protected void updateOptionPrice(String option, float newprice) throws AutoException {
		opt.get(findOption(option)).setPrice(newprice);
	}
	
	/**
	 * Delete an Option at index i
	 * @param i: index of OptionSet
	 * @return the index if exists. Otherwise, -1
	 */
	protected void deleteOption(int i) {
		if (i < opt.size())
			opt.remove(i);
	}
	
	/**
	 * 
	 * @return formatted String of OptionSet
	 */
	protected String print()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n\t- ");
		sb.append(this.getName());
		sb.append(":");
		for (int i = 0; i < opt.size(); i++) {
			sb.append(opt.get(i).print());
		}
		return sb.toString();
	}
}
