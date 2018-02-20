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
		AutoChoice a4 = new BuildAuto();
		String autoID = "";

		System.out.println("\n\n");
		System.out.println("1. Test Working Ford Focus Wagon ZTW 2017");
		autoID = "Ford Focus Wagon ZTW 2017";
		a1.buildAuto("src/AutoDataFiles/" + autoID + ".txt");
		a1.printAuto(autoID);
		try {
			System.out.println("\n\n");
			System.out.println("Set Wrong Color1");
			a4.setChoice(autoID, "Color1", "French Blue Clearcoat Metallic");
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());// Ford Focus Wagon ZTW 2017:Color:French Blue Clearcoat Metallic
		}
		try {
			System.out.println("\n\n");
			System.out.println("Set Choice Transmission");
			a4.setChoice(autoID, "Transmission", "Manual");
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());
		}
		try {
			System.out.println("\n\n");
			System.out.println("Set Choice Brakes/Traction Control");
			a4.setChoice(autoID, "Brakes/Traction Control", "ABS");
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());
		}
		try {
			System.out.println("\n\n");
			System.out.println("Set Choice Side Impact Air Bags");
			a4.setChoice(autoID, "Side Impact Air Bags", "Present");
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());
		}
		try {
			System.out.println("\n\n");
			System.out.println("Set Choice Power Moonroof");
			a4.setChoice(autoID, "Power Moonroof", "Present");
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());
		}
		try {
			System.out.println("$$$ Total of " + autoID + " = " + a4.getTotal(autoID));
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());
		}
		try {
			System.out.println("\n\n");
			System.out.println("Update wrong OptionSetName Side Impact Air Bags1");
			a2.updateOptionSetName(autoID, "Side Impact Air Bags1", "Bluetooth");
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber()); // Ford Focus Wagon ZTW 2017:Side Impact Air Bags:Bluetooth
		} finally {
			a1.printAuto(autoID);
		}
		try {
			System.out.println("\n\n");
			System.out.println("Update Option Price Color");
			a2.updateOptionPrice(autoID, "Color", "Infra-Red Clearcoat", 1000);
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());
		} finally {
			a1.printAuto(autoID);
		}

		System.out.println("\n\n");
		System.out.println("2. Test Working Toyota Camry Sport 2018");
		autoID = "Toyota Camry Sport 2018";
		a1.buildAuto("src/AutoDataFiles/" + autoID + ".txt");
		a1.printAuto(autoID);
		try {
			System.out.println("\n\n");
			System.out.println("Update wrong OptionSetName Side Impact Air Bags");
			a2.updateOptionSetName(autoID, "Side Impact Air Bags", "Bluetooth");
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber()); //Toyota Camry Sport 2018:Sound:Sound System
		} finally {
			a1.printAuto(autoID);
		}
		try {
			a2.updateOptionPrice(autoID, "Power Moonroof", "Not present", 1000);
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());
		} finally {
			a1.printAuto(autoID);
		}
		try {
			System.out.println("$$$ Total of " + autoID + " = " + a4.getTotal(autoID));
		} catch (AutoException e) {
			a3.fix(e.getErrorNumber());
		}
		
		System.out.println("\n\n");
		System.out.println("PROGRAM FINISHED!");
	}
}
