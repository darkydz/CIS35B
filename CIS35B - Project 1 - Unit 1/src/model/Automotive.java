package model;

public class Automotive {
	private String name;
	private OptionSet opset[];
	Automotive()
	{
		
	}
	
	Automotive(String n, int size)
	{
//		yooo
		name = n;
		for(int i=0;i<size;i++)
			opset[i] = new OptionSet();
	}
}
