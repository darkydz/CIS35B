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
			line = br.readLine();
			String name = line.split(";")[0];
			int baseprice = Integer.parseInt(line.split(";")[1]);
			int opSetSize = Integer.parseInt(line.split(";")[2]);
			Auto autoObject = new Auto(name,baseprice,opSetSize);
			
			for (int i=0;i<opSetSize;i++)
			{
				line = br.readLine();
				String opSetName = line.split(":")[0];
				String options[] = line.split(":")[1].split(",");
				autoObject.setOptionSet(i,opSetName, options.length);
				for (int j = 0; j < options.length; j++)
				{
					String optName = options[j].split("=")[0];
					int optPrice = Integer.parseInt(options[j].split("=")[1]);
					autoObject.getOptionSets(i).setOption(j, optName, optPrice);
				}
			}

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
