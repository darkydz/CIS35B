package model;
/**
 * 
 * @author Anh
 *
 */
public class Automotive {
	private String name;
	private int baseprice;
	private OptionSet opset[];
	/**
	 * default constructor
	 */
	public Automotive()
	{
		
	}
	/**
	 * Constructor
	 * @param n name of model
	 * @param p base price of model
	 * @param size size of OptionSet array 
	 */
	public Automotive(String n, int p, int size)
	{
		name = n;
		baseprice = p;
		for(int i=0;i<size;i++)
			opset[i] = new OptionSet();
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
	
}
