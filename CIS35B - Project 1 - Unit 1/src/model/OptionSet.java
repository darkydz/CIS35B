package model;

import java.io.Serializable;

public class OptionSet implements Serializable {
	private Option opt[];
	private String name;
	
	public OptionSet() {
		name = "";
		opt = new Option[0];
	}
		
	/**
	 * @param setName: name of OptionSet
	 * @param setSize: size of OptionSet
	 */
	public OptionSet(String setName, int setSize) {
		name = setName;
		opt = new Option[setSize];
	}

	class Option implements Serializable {
		private String optionName;
		private int price;
		
		public Option() {
			optionName = "";
			price = 0;
		}
		
		/**
		 * Constructor for Option
		 * @param n: name of Option
		 * @param p: price of Option
		 */
		public Option(String n, int p) {
			optionName = n;
			price = p;
		}

		/**
		 * @return name of Option
		 */
		public String getName() {
			return optionName;
		}

		/**
		 * @return price of Option
		 */
		public int getPrice() {
			return price;
		}

		/**
		 * Change name of Option
		 * @param n: new name of Option 
		 */
		public void setName(String n) {
			optionName = n;
		}
		
		/**
		 * Change price of Option
		 * @param p: new price of Option
		 */
		public void setPrice(int p) {
			price = p;
		}
	}

	/**
	 * @return name of OptionSet
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return size of OptionSet (Option count of OptionSet)
	 */
	public int getSize() {
		return opt.length;
	}

	/**
	 * @param i: index of Option in OptionSet
	 * @return Option at index i
	 */
	public Option getOption(int i) {
		return opt[i];
	}

	/**
	 * @param n: new name of OptionSet 
	 */
	public void setName(String n) {
		name = n;
	}

	/**
	 * Change Option at index i
	 * @param i: index in OptionSet
	 * @param opName: name of Option
	 * @param opPrice: price of Option
	 */
	public void setOption(int i, String opName, int opPrice) {
		opt[i] = new Option(opName, opPrice);
	}
	
	/**
	 * Search for Option by its name
	 * @param opName: existing Option name 
	 * @return current index, if Option exists. Otherwise, -1 
	 */
	public int findOption(String opName) {
		for (int i=0; i<opt.length; i++){
			if (opt[i].getName().equals(opName)) return i;
		}
		return -1;
	}
	
	/**
	 * Find an Option by its name and change its values if exists
	 * @param opName: existing Option name
	 * @param newName: new Option name
	 * @param newPrice: new Option price
	 * @return the index if exists. Otherwise, -1
	 */
	public int updateOption(String opName, String newName, int newPrice)
	{
		int index = findOption(opName); 
		if (index != -1) {
			setOption(index, newName, newPrice);
		}
		return index;
	}
	
	/**
	 * Delete an Option at index i
	 * @param i: index of OptionSet
	 * @return the index if exists. Otherwise, -1
	 */
	public int deleteOption(int i) {
		if (i < opt.length) {
			opt[i] = new Option();
			return i;
		}
		else return -1;
	}
}
