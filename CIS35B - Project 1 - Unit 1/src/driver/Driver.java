package driver;

import adapter.*;
import exception.*;
import model.*;
import util.*;

public class Driver {
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		CreateAuto a1 = new BuildAuto();
		UpdateAuto a2 = new BuildAuto();
		FixAuto a3 = new BuildAuto();
		String modelName = "";

		System.out.println("\n\n");
		System.out.println("1. Test Working FordZTW");
		modelName = "FordZTW";
		a1.buildAuto("src/AutoDataFiles/" + modelName + ".txt");
		a1.printAuto(modelName);
//		try {
//			a2.updateOptionSetName(modelName, "Side Impact Air Bags", "Bluetooth");
//		} catch (AutoException e) {
//			a3.fix(e.getErrorNumber());
//		} finally {
//			a1.printAuto(modelName);
//		}
//		try {
//			a2.updateOptionPrice(modelName, "Color", "Infra-Red Clearcoat", 1000);
//		} catch (AutoException e) {
//			a3.fix(e.getErrorNumber());
//		} finally {
//			a1.printAuto(modelName);
//		}
//
//		System.out.println("\n\n");
//		System.out.println("2. Test Working ToyotaCamry");
//		modelName = "ToyotaCamry";
//		a1.buildAuto("src/AutoDataFiles/" + modelName + ".txt");
//		a1.printAuto(modelName);
//		try {
//			a2.updateOptionSetName(modelName, "Side Impact Air Bags", "Bluetooth");
//		} catch (AutoException e) {
//			a3.fix(e.getErrorNumber());
//		} finally {
//			a1.printAuto(modelName);
//		}
//		try {
//			a2.updateOptionPrice(modelName, "Color", "Infra-Red Clearcoat", 1000);
//		} catch (AutoException e) {
//			a3.fix(e.getErrorNumber());
//		} finally {
//			a1.printAuto(modelName);
//		}
//
//		System.out.println("\n\n");
//		System.out.println("3. Test Empty File");
//		modelName = "EmptyFile";
//		a1.buildAuto("src/AutoDataFiles/" + modelName + ".txt");
//		a1.printAuto(modelName);
//
//		System.out.println("\n\n");
//		System.out.println("4. Test Missing Base Price");
//		modelName = "MissingBasePrice";
//		a1.buildAuto("src/AutoDataFiles/" + modelName + ".txt");
//
//		System.out.println("\n\n");
//		System.out.println("5. Test Missing Option");
//		modelName = "MissingOption";
//		a1.buildAuto("src/AutoDataFiles/" + modelName + ".txt");
//
//		System.out.println("\n\n");
//		System.out.println("6. Test Missing Option Name");
//		modelName = "MissingOptionName";
//		a1.buildAuto("src/AutoDataFiles/" + modelName + ".txt");
//
//		System.out.println("\n\n");
//		System.out.println("7. Test Missing Option Price");
//		modelName = "MissingOptionPrice";
//		a1.buildAuto("src/AutoDataFiles/" + modelName + ".txt");
//
//		System.out.println("\n\n");
//		System.out.println("8. Test Missing OptionSet Data");
//		modelName = "MissingOptionSetData";
//		a1.buildAuto("src/AutoDataFiles/" + modelName + ".txt");
//
//		System.out.println("\n\n");
//		System.out.println("9. Test Missing OptionSet Data 2");
//		modelName = "MissingOptionSetData2";
//		a1.buildAuto("src/AutoDataFiles/" + modelName + ".txt");
//
//		System.out.println("\n\n");
//		System.out.println("10. Test Missing OptionSet Size");
//		modelName = "MissingOptionSetSize";
//		a1.buildAuto("src/AutoDataFiles/" + modelName + ".txt");
//
//		System.out.println("\n\n");
//		System.out.println("11. Test Wrong Model Info");
//		modelName = "WrongModelInfoFormat";
//		a1.buildAuto("src/AutoDataFiles/" + modelName + ".txt");

		System.out.println("\n\n");
		System.out.println("PROGRAM FINISHED!");
	}
}
