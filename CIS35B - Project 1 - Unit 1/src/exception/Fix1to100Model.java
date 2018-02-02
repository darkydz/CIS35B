package exception;

import java.util.Scanner;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.FixAuto;
import adapter.UpdateAuto;

public class Fix1to100Model {
	public void fix(int errno) {
		String msg = "";
		AutoException ae = new AutoException();
		switch (errno) {
		case 1:
			Scanner scanner1 = new Scanner(System.in);
			msg = "OptionSet not found! Please re-enter the following info in the same format \"[Old OptionSet Name]:[New OptionSet Name]\"";
			System.out.println(msg);
			ae.append_log(msg);
			String input1 = scanner1.nextLine();
			if (input1.isEmpty()) {
				msg = "Looks like you don't want to cooperate. Program is now closing...";
				System.out.println(msg);
				ae.append_log(msg);
				System.exit(0);
			}
			else{
				ae.append_log(input1);
				if (input1.split(":").length != 2)
				{
					ae.fix(1);
				}
				else {
					UpdateAuto a1 = new BuildAuto();
					try {
						a1.updateOptionSetName("", input1.split(":")[0], input1.split(":")[1]);
					} catch (AutoException e) {
						System.out.println(e.getErrorNumber());
						e.fix(e.getErrorNumber());
					}
				}
			}
			break;
		case 2:
			Scanner scanner2 = new Scanner(System.in);
			msg = "OptionSet not found or Option not found or Price is invalid! Please re-enter the following info in the same format \"[OptionSet Name]:[Option Name]:[New Option Price]\"";
			System.out.println(msg);
			ae.append_log(msg);
			String input2 = scanner2.nextLine();
			if (input2.isEmpty()) {
				msg = "Looks like you don't want to cooperate. Program is now closing...";
				System.out.println(msg);
				ae.append_log(msg);
				System.exit(0);
			}
			else{
				ae.append_log(input2);
				if (input2.split(":").length != 3)
				{
					ae.fix(2);
				}
				else {
					UpdateAuto a1 = new BuildAuto();
					try {
						a1.updateOptionPrice("", input2.split(":")[0], input2.split(":")[1], Float.parseFloat(input2.split(":")[2]));
					} 
					catch (NumberFormatException e) {
						ae.fix(2);
					}
					catch (AutoException e) {
						e.fix(e.getErrorNumber());
					}
				}
			}
			break;
		}
	}
}
