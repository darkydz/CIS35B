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
			msg = "File Not Found or File is Empty! Default file is selected \"" + newFile + "\"";
			System.out.println(msg);
			ae.append_log(msg);
			CreateAuto a101 = new BuildAuto();
			a101.buildAuto(newFile);
			break;
		case 102:
			Scanner scanner = new Scanner(System.in);
			msg = "File Not Found or File is Empty! Please enter the new File name:";
			System.out.print(msg);
			ae.append_log(msg);
			String input = scanner.nextLine();
			if (input.trim().isEmpty()) {
				msg = "Looks like you don't want to cooperate...";
				System.out.println(msg);
				ae.append_log(msg);
				ae.fix(101);
				// System.exit(0);
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
		}
	}
}
