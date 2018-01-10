package Model;

public class OptionSet 
{
	Option opt [];
	String name;
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
		String name;
		float price;
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
