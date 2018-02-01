package exception;

import java.util.Scanner;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.ProxyAutomobile;
import model.Automobile;
import util.FileIO;

public class Fix101to200Util {
	public void fix(int errno) {
		String msg = "";
		String newFile = "src/AutoDataFiles/";
		AutoException ae = new AutoException();
		switch (errno) {
		case 101:
			newFile += "FordZTW.txt";
			msg = "File Not Found! Default file is selected \"" + newFile + "\"";
			System.out.println(msg);
			ae.append_log(msg);
			CreateAuto a101 = new BuildAuto();
			a101.buildAuto(newFile);
			break;
		case 102:
			Scanner scanner = new Scanner(System.in);
			msg = "File Is Empty! Please enter the new File name:";
			System.out.print(msg);
			ae.append_log(msg);
			String input = scanner.nextLine();
			if (input == null || input.isEmpty()) {
				msg = "Looks like you don't want to cooperate. Program is now closing...";
				System.out.println(msg);
				ae.append_log(msg);
				System.exit(0);
			}

			else {
				newFile += input;
				msg = "Start processing new File \"" + newFile + "\"";
				System.out.println(msg);
				ae.append_log(msg);
				CreateAuto a102 = new BuildAuto();
				a102.buildAuto(newFile);
			}
			break;
		case 103:
			msg = "!!!Wrong Model info: Model info requires the following format \"[name]:[base price]:[number of optionsets]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			System.exit(0);
		case 104:
			msg = "!!!Wrong Model info: Model Name is not found! Model info requires the following format \"[name]:[base price]:[number of optionsets]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			System.exit(0);
		case 105:
			msg = "!!!Wrong Model info: Model Base Price is not found! Model info requires the following format \"[name]:[base price]:[number of optionsets]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			System.exit(0);
		case 106:
			msg = "!!!Wrong Model info: Number of OptionSets is not found! Model info requires the following format \"[name]:[base price]:[number of optionsets]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			System.exit(0);
		case 107:
			msg = "!!!Missing OptionSet info: OptionSet info requires the following format \"[name]:[numer of options]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			System.exit(0);
		case 108:
			msg = "!!!Wrong OptionSet info: OptionSet info requires the following format \"[name]:[numer of options]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			System.exit(0);
		case 109:
			msg = "!!!Wrong OptionSet info: OptionSet name is not found. OptionSet info requires the following format \"[name]:[numer of options]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			System.exit(0);
		case 110:
			msg = "!!!Wrong OptionSet info: Number of Options is not found. OptionSet info requires the following format \"[name]:[numer of options]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			System.exit(0);
		case 111:
			msg = "!!!Missing Option info: Option info requires the following format \"[name]:[price]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			System.exit(0);
		case 112:
			msg = "!!!Wrong Option info: Option info requires the following format \"[name]:[price]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			System.exit(0);
		case 113:
			msg = "!!!Wrong Option info: Option Name is not found. Option info requires the following format \"[name]:[price]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			System.exit(0);
		case 114:
			msg = "!!!Wrong Option info: Option Price is not found. Option info requires the following format \"[name]:[price]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			System.exit(0);
		case 115:
			msg = "!!!Extra data found: Redundant data is found. Please clean up or correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			System.exit(0);
		default:
			break;
		}
	}
}
