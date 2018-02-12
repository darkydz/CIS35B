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
		AutoChoice a4  = new BuildAuto();
		String autoID = "";

		System.out.println("\n\n");
		System.out.println("1. Test Working Ford Focus Wagon ZTW 2017");
		autoID = "Ford Focus Wagon ZTW 2017";
		a1.buildAuto("src/AutoDataFiles/" + autoID + ".txt");
		a1.printAuto(autoID);
		try {
			a4.setChoice(autoID, "Color", "French Blue Clearcoat Metallic");
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());//Ford Focus Wagon ZTW 2017:Color:French Blue Clearcoat Metallic
		}
		try {
			a4.setChoice(autoID, "Transmission", "Manual");
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());
		}
		try {
			a4.setChoice(autoID, "Brakes/Traction Control", "ABS");
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());
		}
		try {
			a4.setChoice(autoID, "Side Impact Air Bags", "Present");
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());		}
		try {
			a4.setChoice(autoID, "Power Moonroof", "Present");
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());
		}
		try {
			System.out.println("$$$Total of " + autoID + " = " + a4.getTotal(autoID));
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());
		}
		try {
			a2.updateOptionSetName(autoID, "Side Impact Air Bags1", "Bluetooth");
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber()); //Ford Focus Wagon ZTW 2017:Side Impact Air Bags:Bluetooth
		} finally {
			a1.printAuto(autoID);
		}
		try {
			a2.updateOptionPrice(autoID, "Color", "Infra-Red Clearcoat", 1000);
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());
		} finally {
			a1.printAuto(autoID);
		}
//
//		System.out.println("\n\n");
//		System.out.println("2. Test Working Toyota Camry Sport 2018");
//		autoID = "Toyota Camry Sport 2018";
//		a1.buildAuto("src/AutoDataFiles/" + autoID + ".txt");
//		a1.printAuto(autoID);
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
