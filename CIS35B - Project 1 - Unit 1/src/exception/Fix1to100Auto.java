package exception;

import java.util.Scanner;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.FixAuto;
import adapter.UpdateAuto;

public class Fix1to100Auto{
	public void fix(int errno) {
		String msg = "";
		AutoException ae = new AutoException();
		switch (errno) {
		case 3:
			msg = "!!!Wrong Auto info: Auto info requires the following format \"[make]:[model]:[year]:[base price]:[number of optionsets]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 4:
			msg = "!!!Wrong Auto info: Auto Model name is not found! Auto info requires the following format \"[make]:[model]:[year]:[base price]:[number of optionsets]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 5:
			msg = "!!!Wrong Auto info: Auto Base Price is not found! Auto info requires the following format \"[make]:[model]:[year]:[base price]:[number of optionsets]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 6:
			msg = "!!!Wrong Auto info: Number of OptionSets is not found! Auto info requires the following format \"[make]:[model]:[year]:[base price]:[number of optionsets]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 7:
			msg = "!!!Missing OptionSet info: OptionSet info requires the following format \"[name]:[numer of options]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 8:
			msg = "!!!Wrong OptionSet info: OptionSet info requires the following format \"[name]:[numer of options]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 9:
			msg = "!!!Wrong OptionSet info: OptionSet name is not found. OptionSet info requires the following format \"[name]:[numer of options]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 10:
			msg = "!!!Wrong OptionSet info: Number of Options is not found. OptionSet info requires the following format \"[name]:[numer of options]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 11:
			msg = "!!!Missing Option info: Option info requires the following format \"[name]:[price]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 12:
			msg = "!!!Wrong Option info: Option info requires the following format \"[name]:[price]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 13:
			msg = "!!!Wrong Option info: Option Name is not found. Option info requires the following format \"[name]:[price]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 14:
			msg = "!!!Wrong Option info: Option Price is not found. Option info requires the following format \"[name]:[price]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 15:
			msg = "!!!Extra data found: Redundant data is found. Please clean up or correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 16:
			msg = "!!!Wrong Auto info: Auto Make name is not found! Auto info requires the following format \"[make]:[model]:[year]:[base price]:[number of optionsets]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 17:
			msg = "!!!Wrong Auto info: Auto Year is not found! Auto info requires the following format \"[make]:[model]:[year]:[base price]:[number of optionsets]\". Please correct your file! Program is now closing...";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 18:
			msg = "!!!No OptionSet found!";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;	
		case 19:
			msg = "!!!No Option found!";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;
		case 20:
			msg = "!!!No Option selected yet!";
			System.out.println(msg);
			ae.append_log(msg);
			// System.exit(0);
			break;	
		}
	}
}
