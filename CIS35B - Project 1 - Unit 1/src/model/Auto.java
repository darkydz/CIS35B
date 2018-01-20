package model;

import java.io.Serializable;

import model.OptionSet.Option;

/**
 * 
 * @author Anh
 *
 */
public class Auto implements Serializable 
{
	private String name;
	private int baseprice;
	private OptionSet opset[];
	/**
	 * default constructor
	 */
	public Auto(String n, int p, int size)
	{
		name = n;
		baseprice = p;
		opset = new OptionSet[size];
	}
	
	/**
	 * Return name of this car model
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Return base price of model
	 */
	public int getBasePrice()
	{
		return baseprice;
	}
	
	/**
	 * Return an OptionSet by index
	 */
	public OptionSet getOptionSets(int i)
	{
		return opset[i];
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setBasePrice(int p)
	{
		baseprice = p;
	}
	
	public int findOptionSet(String opsetName)
	{
		for (int i = 0; i<opset.length; i++)
		{
			if (opset[i].getName().equals(opsetName)) return i;
		}
		return -1;
	}
	
	public boolean setOptionSet(int i, String opsetName, int opSetSize)
	{
		opset[i] = new OptionSet(opsetName, opSetSize);
		return true;
	}
	
	public void displayInfo()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("Name: ");
		sb.append(name);
		sb.append("\nBase Price: ");
		sb.append(baseprice);
		sb.append("\nOptions: ");
		for (int i = 0; i<opset.length; i++)
		{
			OptionSet curOpSet = opset[i]; 
			sb.append("\n- ");
			sb.append(curOpSet.getName());
			sb.append(":");
			for (int j = 0; j < curOpSet.getSize(); j++)
			{
				sb.append("\n\t");
				sb.append("+ ");
				sb.append(opset[i].getOption(j).getName());
				sb.append(" : ");
				sb.append(opset[i].getOption(j).getPrice());
				
			}
		}
		
		System.out.println(sb.toString());
		
	}
	
}
