package client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

public class CarModelOptionsIO {
	/**
	 * Read a file and parse it into Properties object, then send this object to server
	 * @param filename .prop file path
	 * @param out output stream to server
	 * @return true if Properties object is sent
	 */
	public boolean sendAutoFromPropFile(String filename, ObjectOutputStream out) {
		Properties props = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(filename);
			props.load(in);
		} catch (Exception e) {
			System.err.println("Error: Cannot read file \"" + filename + "\"");
			return false;
		}

		try {
			out.writeObject(props);
			return true;
		} catch (IOException e) {
			System.err.println("Error: Cannot send Auto properties to Server!");
			return false;
		}
	}

//	/**
//	 * A backup method to 
//	 * @param out
//	 */
//	public void sendEmptyAutoProp(ObjectOutputStream out) {
//		Properties props = new Properties();
//		try {
//			out.writeObject(props);
//		} catch (IOException e) {
//			System.err.println("Error: Cannot send Auto properties to Server!");
//		}
//	}
}
