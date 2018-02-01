package util;

import java.io.*;

import exception.AutoException;
import model.*;

public class FileIO {
	/**
	 * @param filename:
	 *            input file that contains Auto model data
	 * @return a new Auto object
	 */
	public Automotive buildAutoObject(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = "";
			line = br.readLine();
			if (line != null && line.split(":").length == 3 && !line.split(":")[0].isEmpty()
					&& !line.split(":")[1].isEmpty() && !line.split(":")[2].isEmpty()) {
				String name = line.split(":")[0];
				float price = Float.parseFloat(line.split(":")[1]);
				int size = Integer.parseInt(line.split(":")[2]);
				Automotive autoObject = new Automotive(name, price, size);
				for (int i = 0; i < size; i++) {
					line = br.readLine();
					if (line != null && line.split(":").length == 2 && !line.split(":")[0].isEmpty()
							&& !line.split(":")[1].isEmpty()) {
						name = line.split(":")[0];
						int setSize = Integer.parseInt(line.split(":")[1]);
						autoObject.setOptionSet(i, name, setSize);
						for (int j = 0; j < setSize; j++) {
							line = br.readLine();
							if (line != null && line.split(":").length == 2 && !line.split(":")[0].isEmpty()
									&& !line.split(":")[1].isEmpty()) {
								name = line.split(":")[0];
								price = Float.parseFloat(line.split(":")[1]);
								autoObject.setOption(i, j, name, price);
							} else {
								br.close();
								return null;
							}
						}
					} else {
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

	public Automobile buildAutomobileObject(String filename) throws AutoException {
		// AutoException ae = new AutoException();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = "";
			line = br.readLine();

			if (line == null)//file empty
				throw new AutoException(102);
			if (line.split(":").length != 3)//wrong model info format
				throw new AutoException(103);
			if (line.split(":")[0].isEmpty())//model name missing
				throw new AutoException(104);
			if (line.split(":")[1].isEmpty())//model base price missing
				throw new AutoException(105);
			if (line.split(":")[2].isEmpty())//OptionSet size missing
				throw new AutoException(106);
			
			String name = line.split(":")[0];
			float price = Float.parseFloat(line.split(":")[1]);
			int size = Integer.parseInt(line.split(":")[2]);
			Automobile autoObject = new Automobile(name, price, size);
			for (int i = 0; i < size; i++) {
				line = br.readLine();

				if (line == null)//missing OptionSet info
					throw new AutoException(107);
				if (line.split(":").length != 2)//wrong OptionSet info format
					throw new AutoException(108);
				if (line.split(":")[0].isEmpty())//OptionSet name missing
					throw new AutoException(109);
				if (line.split(":")[1].isEmpty())//Option size missing
					throw new AutoException(110);

				name = line.split(":")[0];
				int setSize = Integer.parseInt(line.split(":")[1]);
				autoObject.setOptionSet(i, name, setSize);
				for (int j = 0; j < setSize; j++) {
					line = br.readLine();
					if (line == null)//missing Option info
						throw new AutoException(111);
					if (line.split(":").length != 2)//wrong Option info format
						throw new AutoException(112);
					if (line.split(":")[0].isEmpty())//Option name missing
						throw new AutoException(113);
					if (line.split(":")[1].isEmpty())//Option price missing
						throw new AutoException(114);

					name = line.split(":")[0];
					price = Float.parseFloat(line.split(":")[1]);
					autoObject.setOption(i, j, name, price);
				}
			}
			line = br.readLine();
			if (line != null) throw new AutoException(115);//extra data found
			return autoObject;

		} catch (FileNotFoundException e) {
			// ae.fix(101);
			throw new AutoException(101);
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
	 *            Auto object to be serialized
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
