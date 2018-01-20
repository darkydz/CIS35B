package util;

import java.io.*;

import model.*;

public class FileIO {
	/**
	 * @param filename
	 * @return
	 */
	public Auto buildAutoObject(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = "";
			line = br.readLine();
			String name = line.split(";")[0];
			int baseprice = Integer.parseInt(line.split(";")[1]);
			int opSetSize = Integer.parseInt(line.split(";")[2]);
			Auto autoObject = new Auto(name, baseprice, opSetSize);

			for (int i = 0; i < opSetSize; i++) {
				line = br.readLine();
				String opSetName = line.split(":")[0];
				String options[] = line.split(":")[1].split(",");
				autoObject.setOptionSet(i, opSetName, options.length);
				for (int j = 0; j < options.length; j++) {
					String optName = options[j].split("=")[0];
					int optPrice = Integer.parseInt(options[j].split("=")[1]);
					autoObject.getOptionSets(i).setOption(j, optName, optPrice);
				}
			}

			br.close();
			return autoObject;
		} catch (IOException e) {
			System.out.println("Error: " + e.toString());
		}
		return null;
	}

	/**
	 * @param autoObject
	 * @param filename
	 */
	public void serializeAuto(Auto autoObject, String filename) {
		try {
			ObjectOutputStream ost = new ObjectOutputStream(new FileOutputStream(filename));
			ost.writeObject(autoObject);
			ost.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}

	/**
	 * @param filename
	 * @return
	 */
	public Auto deserializeAuto(String filename) {
		try {
			ObjectInputStream ist = new ObjectInputStream(new FileInputStream(filename));
			Auto autoObject = (Auto) ist.readObject();
			ist.close();
			return autoObject;
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return null;
	}

}
