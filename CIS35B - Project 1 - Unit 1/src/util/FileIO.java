package util;
import java.io.*;

import model.*;

public class FileIO {
	public Auto buildAutoObject(String filename)
	{
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = "";
			line=br.readLine();
			String name = line.split(";")[0];
			int baseprice = Integer.parseInt(line.split(";")[1]);
			int opSetSize = Integer.parseInt(line.split(";")[2]);
			Auto autoObject = new Auto(name,baseprice,opSetSize);
			
			String optionSetStrings[] = new String[opSetSize];
			for (int i=0;i<opSetSize;i++)
			{
//				optionSetStrings[i]=br.readLine();
				
			}
//			Auto autoObject = new Auto(name,baseprice,optionSetStrings);
//			autoObject.displayInfo();
			br.close();
			return autoObject;
		}
		catch (IOException e)
		{
			System.out.println(e.toString());
		}
		return null;
	}
}
