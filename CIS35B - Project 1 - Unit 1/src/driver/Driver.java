package driver;

import model.*;
import util.*;

public class Driver {
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		String modelName = "ToyotaCamry";
		FileIO io = new FileIO();
		Auto carModel = io.buildAutoObject("src/AutoDataFiles/" + modelName + ".txt");
		System.out.println("Display new Auto:");
		carModel.displayInfo();
		io.serializeAuto(carModel, "src/AutoDataFiles/" + modelName + ".dat");
		Auto carModel2 = io.deserializeAuto("src/AutoDataFiles/" + modelName + ".dat");
		System.out.println("============================");
		System.out.println("Display deserialized Auto:");
		carModel2.displayInfo();
	}
}
