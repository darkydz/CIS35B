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
		String modelName = "WrongModelInfoFormat";
		CreateAuto a1 = new BuildAuto();
		a1.buildAuto("src/AutoDataFiles/" + modelName + ".txt");
//		a1.printAuto(modelName);
		UpdateAuto a2 = new BuildAuto();
		a2.updateOptionSetName(modelName, "Side Impact Air Bags", "Bluetooth");
		a2.updateOptionPrice(modelName, "Color", "Infra-Red Clearcoat", 1000);
		a1.printAuto(modelName);
		FixAuto a3 = new BuildAuto();
		
//		FileIO io = new FileIO();
//		System.out.println("1. Test Working FordZTW");
//		String modelName = "FordZTW";
//		Auto carModel = io.buildAutoObject("src/AutoDataFiles/" + modelName + ".txt");
//		if (carModel != null) {
//			System.out.println("A. Display new Auto:");
//			System.out.println("---------------------");
//			carModel.print();
//			io.serializeAuto(carModel, "src/AutoDataFiles/" + modelName + ".dat");
//			carModel = io.deserializeAuto("src/AutoDataFiles/" + modelName + ".dat");
//			System.out.println("============================");
//			System.out.println("B. Display deserialized Auto:");
//			System.out.println("-----------------------------");
//			carModel.print();
//		} else
//			System.out.println("Error! Input file not formatted correctly!");
//
//		System.out.println("\n\n");
//		System.out.println("2. Test Working ToyotaCamry");
//		modelName = "ToyotaCamry";
//		carModel = io.buildAutoObject("src/AutoDataFiles/" + modelName + ".txt");
//		if (carModel != null) {
//			System.out.println("A. Display new Auto:");
//			System.out.println("---------------------");
//			carModel.print();
//			io.serializeAuto(carModel, "src/AutoDataFiles/" + modelName + ".dat");
//			carModel = io.deserializeAuto("src/AutoDataFiles/" + modelName + ".dat");
//			System.out.println("============================");
//			System.out.println("B. Display deserialized Auto:");
//			System.out.println("-----------------------------");
//			carModel.print();
//		} else
//			System.out.println("Error! Input file not formatted correctly!");
//
//		System.out.println("\n\n");
//		System.out.println("3. Test Empty File");
//		modelName = "EmptyFile";
//		carModel = io.buildAutoObject("src/AutoDataFiles/" + modelName + ".txt");
//		if (carModel != null) {
//			System.out.println("A. Display new Auto:");
//			System.out.println("---------------------");
//			carModel.print();
//			io.serializeAuto(carModel, "src/AutoDataFiles/" + modelName + ".dat");
//			carModel = io.deserializeAuto("src/AutoDataFiles/" + modelName + ".dat");
//			System.out.println("============================");
//			System.out.println("B. Display deserialized Auto:");
//			System.out.println("-----------------------------");
//			carModel.print();
//		} else
//			System.out.println("Error! Input file not formatted correctly!");
//
//		System.out.println("\n\n");
//		System.out.println("4. Test Missing Base Price");
//		modelName = "MissingBasePrice";
//		carModel = io.buildAutoObject("src/AutoDataFiles/" + modelName + ".txt");
//		if (carModel != null) {
//			System.out.println("A. Display new Auto:");
//			System.out.println("---------------------");
//			carModel.print();
//			io.serializeAuto(carModel, "src/AutoDataFiles/" + modelName + ".dat");
//			carModel = io.deserializeAuto("src/AutoDataFiles/" + modelName + ".dat");
//			System.out.println("============================");
//			System.out.println("B. Display deserialized Auto:");
//			System.out.println("-----------------------------");
//			carModel.print();
//		} else
//			System.out.println("Error! Input file not formatted correctly!");
//
//		System.out.println("\n\n");
//		System.out.println("5. Test Missing Option");
//		modelName = "MissingOption";
//		carModel = io.buildAutoObject("src/AutoDataFiles/" + modelName + ".txt");
//		if (carModel != null) {
//			System.out.println("A. Display new Auto:");
//			System.out.println("---------------------");
//			carModel.print();
//			io.serializeAuto(carModel, "src/AutoDataFiles/" + modelName + ".dat");
//			carModel = io.deserializeAuto("src/AutoDataFiles/" + modelName + ".dat");
//			System.out.println("============================");
//			System.out.println("B. Display deserialized Auto:");
//			System.out.println("-----------------------------");
//			carModel.print();
//		} else
//			System.out.println("Error! Input file not formatted correctly!");
//
//		System.out.println("\n\n");
//		System.out.println("6. Test Missing Option Name");
//		modelName = "MissingOptionName";
//		carModel = io.buildAutoObject("src/AutoDataFiles/" + modelName + ".txt");
//		if (carModel != null) {
//			System.out.println("A. Display new Auto:");
//			System.out.println("---------------------");
//			carModel.print();
//			io.serializeAuto(carModel, "src/AutoDataFiles/" + modelName + ".dat");
//			carModel = io.deserializeAuto("src/AutoDataFiles/" + modelName + ".dat");
//			System.out.println("============================");
//			System.out.println("B. Display deserialized Auto:");
//			System.out.println("-----------------------------");
//			carModel.print();
//		} else
//			System.out.println("Error! Input file not formatted correctly!");
//
//		System.out.println("\n\n");
//		System.out.println("7. Test Missing Option Price");
//		modelName = "MissingOptionPrice";
//		carModel = io.buildAutoObject("src/AutoDataFiles/" + modelName + ".txt");
//		if (carModel != null) {
//			System.out.println("A. Display new Auto:");
//			System.out.println("---------------------");
//			carModel.print();
//			io.serializeAuto(carModel, "src/AutoDataFiles/" + modelName + ".dat");
//			carModel = io.deserializeAuto("src/AutoDataFiles/" + modelName + ".dat");
//			System.out.println("============================");
//			System.out.println("B. Display deserialized Auto:");
//			System.out.println("-----------------------------");
//			carModel.print();
//		} else
//			System.out.println("Error! Input file not formatted correctly!");
//
//		System.out.println("\n\n");
//		System.out.println("8. Test Missing OptionSet Data");
//		modelName = "MissingOptionSetData";
//		carModel = io.buildAutoObject("src/AutoDataFiles/" + modelName + ".txt");
//		if (carModel != null) {
//			System.out.println("A. Display new Auto:");
//			System.out.println("---------------------");
//			carModel.print();
//			io.serializeAuto(carModel, "src/AutoDataFiles/" + modelName + ".dat");
//			carModel = io.deserializeAuto("src/AutoDataFiles/" + modelName + ".dat");
//			System.out.println("============================");
//			System.out.println("B. Display deserialized Auto:");
//			System.out.println("-----------------------------");
//			carModel.print();
//		} else
//			System.out.println("Error! Input file not formatted correctly!");
//
//		System.out.println("\n\n");
//		System.out.println("9. Test Missing OptionSet Data 2");
//		modelName = "MissingOptionSetData2";
//		carModel = io.buildAutoObject("src/AutoDataFiles/" + modelName + ".txt");
//		if (carModel != null) {
//			System.out.println("A. Display new Auto:");
//			System.out.println("---------------------");
//			carModel.print();
//			io.serializeAuto(carModel, "src/AutoDataFiles/" + modelName + ".dat");
//			carModel = io.deserializeAuto("src/AutoDataFiles/" + modelName + ".dat");
//			System.out.println("============================");
//			System.out.println("B. Display deserialized Auto:");
//			System.out.println("-----------------------------");
//			carModel.print();
//		} else
//			System.out.println("Error! Input file not formatted correctly!");
//
//		System.out.println("\n\n");
//		System.out.println("10. Test Missing OptionSet Size");
//		modelName = "MissingOptionSetSize";
//		carModel = io.buildAutoObject("src/AutoDataFiles/" + modelName + ".txt");
//		if (carModel != null) {
//			System.out.println("A. Display new Auto:");
//			System.out.println("---------------------");
//			carModel.print();
//			io.serializeAuto(carModel, "src/AutoDataFiles/" + modelName + ".dat");
//			carModel = io.deserializeAuto("src/AutoDataFiles/" + modelName + ".dat");
//			System.out.println("============================");
//			System.out.println("B. Display deserialized Auto:");
//			System.out.println("-----------------------------");
//			carModel.print();
//		} else
//			System.out.println("Error! Input file not formatted correctly!");
	}
}
