package model;

public class Automobile extends Automotive {
	public Automobile()
	{
		super();
	}
	
	public Automobile(String mk, String md, int y, float p, int size)
	{
		super(mk, md, y, p, size);
	}
	
	public Automobile(String mk, String md, int y, float p)
	{
		super(mk, md, y, p);
	}
}
