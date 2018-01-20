package model;

import java.io.Serializable;

public class OptionSet implements Serializable
{
	private Option opt [];
	private String name;
	public OptionSet(String setName, int setSize)
	{
		name = setName;
		opt = new Option[setSize];
	}
		
	class Option 
	{
		private String optionName;
		private int price;
		
		public Option ()
		{
			optionName = "";	
			price = 0;
		}
		
		public Option (String n, int p)
		{
			optionName = n;
			price = p;
		}

		public String getName()
		{
			return optionName;
		}
		
		public int getPrice()
		{
			return price;
		}
		
		public void setName(String n)
		{
			optionName = n;
		}
		
		public void setPrice(int p)
		{
			price = p;
		}
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getSize()
	{
		return opt.length;
	}
	
	public Option getOption(int i)
	{
		return opt[i];
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setOption(int i, String opName, int opPrice)
	{
		opt[i] = new Option(opName, opPrice);
	}
}
