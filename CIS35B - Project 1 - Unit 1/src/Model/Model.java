package Model;

public class Model {
	String name;
	OptionSet opset[];
	Model()
	{
		
	}
	
	Model(String n, int size)
	{
		name = n;
		for(int i=0;i<size;i++)
			opset[i] = new OptionSet();
	}
}
