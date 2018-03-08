package util;

import java.io.*;

import exception.AutoException;
import model.*;

public class FileIO {
	/**
	 * @param filename:
	 *            input file that contains Automobile model data
	 * @return a new Automobile object
	 */
	public Automobile buildAutomobileObject(String filename) throws AutoException {
		// AutoException ae = new AutoException();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = "";
			line = br.readLine();

			if (line == null)// file empty
				throw new AutoException(102);
			if (line.split(":").length != 5)// wrong Auto info format
				throw new AutoException(3);
			if (line.split(":")[0].isEmpty())// make name missing
				throw new AutoException(16);
			if (line.split(":")[1].isEmpty())// model name missing
				throw new AutoException(4);
			if (line.split(":")[2].isEmpty())// year missing
				throw new AutoException(17);
			if (line.split(":")[3].isEmpty())// base price missing
				throw new AutoException(5);
			if (line.split(":")[4].isEmpty())// OptionSet size missing
				throw new AutoException(6);

			String make = line.split(":")[0];
			String model = line.split(":")[1];
			int year = Integer.parseInt(line.split(":")[2]);
			float price = Float.parseFloat(line.split(":")[3]);
			int size = Integer.parseInt(line.split(":")[4]);
			Automobile autoObject = new Automobile(make, model, year, price, size);
			for (int i = 0; i < size; i++) {
				line = br.readLine();

				if (line == null)// missing OptionSet info
					throw new AutoException(7);
				if (line.split(":").length != 2)// wrong OptionSet info format
					throw new AutoException(8);
				if (line.split(":")[0].isEmpty())// OptionSet name missing
					throw new AutoException(9);
				if (line.split(":")[1].isEmpty())// Option size missing
					throw new AutoException(10);

				String name = line.split(":")[0];
				int setSize = Integer.parseInt(line.split(":")[1]);
				autoObject.setOptionSet(i, name, setSize);
				for (int j = 0; j < setSize; j++) {
					line = br.readLine();
					if (line == null)// missing Option info
						throw new AutoException(11);
					if (line.split(":").length != 2)// wrong Option info format
						throw new AutoException(12);
					if (line.split(":")[0].isEmpty())// Option name missing
						throw new AutoException(13);
					if (line.split(":")[1].isEmpty())// Option price missing
						throw new AutoException(14);

					name = line.split(":")[0];
					price = Float.parseFloat(line.split(":")[1]);
					autoObject.setOption(i, j, name, price);
				}
			}
			line = br.readLine();
			if (line != null)
				throw new AutoException(15);// extra data found
			return autoObject;

		} catch (FileNotFoundException e) {
			// ae.fix(101);
			throw new AutoException(102);
		} catch (IOException e) {
			System.out.println("Error: " + e.toString());
		} finally {
			try {
				br.close();
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * @param autoObject:
	 *            Automotive object to be serialized
	 * @param filename:
	 *            destination serialized file
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
	 * Take a serialized Automotive file and read into an Auto object
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
	
	public Automobile buildAutomobileObjectProp(String filename) {
		Automobile auto = new Automobile();
		return auto;
	}

}
