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

	private class Option implements Serializable {
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
		return opt[i];
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
		if (i < opt.length)
			opt[i] = new Option(opName, opPrice);
	}
	
	/**
	 * Search for Option by its name
	 * @param opName: existing Option name 
	 * @return current index, if Option exists. Otherwise, -1 
	 */
	protected int findOption(String opName) {
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
	protected int updateOption(String opName, String newName, float newPrice)
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
	protected int deleteOption(int i) {
		if (opt[i]!= null) {
			opt[i] = new Option();
			return i;
		}
		else return -1;
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
		for (int i = 0; i < opt.length; i++) {
			sb.append(opt[i].print());
		}
		return sb.toString();
	}

	public void updateOptionPrice(String option, float newprice) {
		opt[findOption(option)].setPrice(newprice);
	}
}
