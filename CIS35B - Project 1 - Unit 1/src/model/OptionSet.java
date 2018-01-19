package model;

import java.io.Serializable;

public class OptionSet implements Serializable
{
	private Option opt [];
	private String name;
	
	public OptionSet(String setName, String options[])
	{
		name = setName;
		opt = new Option[options.length];
		for (int i=0; i < options.length; i++)
		{
			String opName = options[i].split("=")[0];
			int opPrice = Integer.parseInt(options[i].split("=")[1]);
//			System.out.println(i);
			opt[i] = new Option(opName, opPrice);
		}
	}

	
	class Option 
	{
		private String optionName;
		private int price;
		
		public Option (String n, int p)
		{
			optionName = n;
//			System.out.println(optionName);
			price = p;
//			System.out.println(price);
		}
		
		public Option() {
			// TODO Auto-generated constructor stub
		}

		public String getName()
		{
			return optionName;
		}
		
		public int getPrice()
		{
			return price;
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
	
}
