package util;

import java.io.*;

import exception.AutoException;
import model.*;

public class FileIO {
	/**
	 * @param filename: input file that contains Auto model data 
	 * @return a new Auto object 
	 */
	public Automotive buildAutoObject(String filename)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = ""; 
			line = br.readLine();
			if (line != null && line.split(":").length == 3 && !line.split(":")[0].isEmpty() && !line.split(":")[1].isEmpty() && !line.split(":")[2].isEmpty())
			{
				String name = line.split(":")[0];
				float price = Float.parseFloat(line.split(":")[1]);
				int size = Integer.parseInt(line.split(":")[2]);
				Automotive autoObject = new Automotive(name, price, size);
				for (int i = 0; i < size; i++) {
					line = br.readLine();
					if (line != null && line.split(":").length == 2 && !line.split(":")[0].isEmpty() && !line.split(":")[1].isEmpty())
					{
						name = line.split(":")[0];
						int setSize = Integer.parseInt(line.split(":")[1]);
						autoObject.setOptionSet(i, name, setSize);
						for (int j = 0; j < setSize; j++) {
							line = br.readLine();
							if (line != null && line.split(":").length == 2 && !line.split(":")[0].isEmpty() && !line.split(":")[1].isEmpty())
							{
								name = line.split(":")[0];
								price = Float.parseFloat(line.split(":")[1]);
								autoObject.setOption(i,j,name,price);
							}
							else
							{
								br.close();
								return null;
							}
						}
					}
					else
					{
						br.close();
						return null;
					}
				}	
				br.close();
				return autoObject;
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.toString());
		}
		return null;
	}
	
	public Automobile buildAutomobileObject(String filename) //throws AutoException 
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = ""; 
			line = br.readLine();
			if (line != null && line.split(":").length == 3 && !line.split(":")[0].isEmpty() && !line.split(":")[1].isEmpty() && !line.split(":")[2].isEmpty())
			{
				String name = line.split(":")[0];
				float price = Float.parseFloat(line.split(":")[1]);
				int size = Integer.parseInt(line.split(":")[2]);
				Automobile autoObject = new Automobile(name, price, size);
				for (int i = 0; i < size; i++) {
					line = br.readLine();
					if (line != null && line.split(":").length == 2 && !line.split(":")[0].isEmpty() && !line.split(":")[1].isEmpty())
					{
						name = line.split(":")[0];
						int setSize = Integer.parseInt(line.split(":")[1]);
						autoObject.setOptionSet(i, name, setSize);
						for (int j = 0; j < setSize; j++) {
							line = br.readLine();
							if (line != null && line.split(":").length == 2 && !line.split(":")[0].isEmpty() && !line.split(":")[1].isEmpty())
							{
								name = line.split(":")[0];
								price = Float.parseFloat(line.split(":")[1]);
								autoObject.setOption(i,j,name,price);
							}
							else
							{
								br.close();
								return null;
							}
						}
					}
					else
					{
						br.close();
						return null;
					}
				}	
				br.close();
				return autoObject;
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.toString());
		}
		return null;
	}

	/**
	 * @param autoObject: Auto object to be serialized
	 * @param filename: destination serialized file
	 */
	public void serializeAuto(Automotive autoObject, String filename) {
		try {
			ObjectOutputStream ost = new ObjectOutputStream(new FileOutputStream(filename));
			ost.writeObject(autoObject);
			ost.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}

	/**
	 * Take a serialized Auto file and read into an Auto object
	 * 
	 * @param filename:
	 *            the serialized file
	 * @return Auto object
	 */
	public Automotive deserializeAuto(String filename) {
		try {
			ObjectInputStream ist = new ObjectInputStream(new FileInputStream(filename));
			Automotive autoObject = (Automotive) ist.readObject();
			ist.close();
			return autoObject;
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return null;
	}

}
