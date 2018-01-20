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
//			System.out.println(line);
			String name = line.split(":")[0];
			int price = Integer.parseInt(line.split(":")[1]);
			int size = Integer.parseInt(line.split(":")[2]);
			Auto autoObject = new Auto(name, price, size);

			for (int i = 0; i < size; i++) {
				line = br.readLine();
				name = line.split(":")[0];
				int setSize = Integer.parseInt(line.split(":")[1]);
				autoObject.setOptionSet(i, name, setSize);
				for (int j = 0; j < setSize; j++) {
					line = br.readLine();
					name = line.split(":")[0];
					price = Integer.parseInt(line.split(":")[1]);
					autoObject.getOptionSets(i).setOption(j, name, price);
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
