package model;

public class OptionSet 
{
	private Option opt [];
	private String name;
	OptionSet()
	{

	}
	
	OptionSet(String n, int size)
	{
		name = n;
		for (int i=0; i < size; i++)
			opt[i] = new Option();
	}
	
	private class Option 
	{
		private String name;
		private float price;
		Option (){}
		Option (String n, float p)
		{
			name = n;
			price = p;
		}
		
		String get_name()
		{
			return name;
		}
		
		float get_price()
		{
			return price;
		}
	}

}
