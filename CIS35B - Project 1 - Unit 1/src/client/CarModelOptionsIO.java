package client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

public class CarModelOptionsIO {
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

	public void sendEmptyAutoProp(ObjectOutputStream out) {
		Properties props = new Properties();
		try {
			out.writeObject(props);
		} catch (IOException e) {
			System.err.println("Error: Cannot send Auto properties to Server!");
		}
	}
}
