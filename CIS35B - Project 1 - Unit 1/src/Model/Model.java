package Model;

public class Model {
	String name;
	OptionSet opset[];
	Model()
	{
		
	}
	
	Model(String n, int size)
	{
//		yooo
		name = n;
		for(int i=0;i<size;i++)
			opset[i] = new OptionSet();
	}
}
