package util;
import java.io.*;

import model.*;

public class FileIO {
	public Auto readAutoFile(String filename)
	{
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = "";
			line=br.readLine();
			String name = line.split(";")[0];
			int baseprice = Integer.parseInt(line.split(";")[1]);
			int size = Integer.parseInt(line.split(";")[2]);
			
			String optionSetStrings[] = new String[size];
			for (int i=0;i<size;i++)
			{
				optionSetStrings[i]=br.readLine();
			}
			
			Auto autoObject = new Auto(name,baseprice,optionSetStrings);
//			autoObject.displayInfo();
			
			return autoObject;
		}
		catch (IOException e)
		{
			System.out.println(e.toString());
		}
		return null;
	}
}
