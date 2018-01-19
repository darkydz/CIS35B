package driver;
import model.*;
import util.*;

public class Driver {
	public static void main(String args[])
	{
		FileIO io = new FileIO();
		Auto carModel = io.readAutoFile("src/FordZTW.txt");
		carModel.displayInfo();
	}
}
