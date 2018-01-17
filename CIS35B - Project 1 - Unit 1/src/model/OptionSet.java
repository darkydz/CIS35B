package model;

public class OptionSet 
{
	private Option opt [];
	private String name;
	OptionSet()
	{
		
	}
	
	/**
	 * constructor
	 * @param n name of the OptionSet
	 * @param size size of Option array
	 */
	OptionSet(String n, int size)
	{
		name = n;
		for (int i=0; i < size; i++)
			opt[i] = new Option();
	}
	
	private class Option 
	{
		private String name;
		private int price;
		Option (){}
		Option (String n, int p)
		{
			name = n;
			price = p;
		}
		
		String getName()
		{
			return name;
		}
		
		int getPrice()
		{
			return price;
		}
	}
	
	

}
