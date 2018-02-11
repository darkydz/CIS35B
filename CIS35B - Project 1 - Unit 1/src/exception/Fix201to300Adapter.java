package exception;

import java.util.Scanner;

import adapter.BuildAuto;
import adapter.UpdateAuto;

public class Fix201to300Adapter {
	public void fix(int errno) {
		String msg = "";
		AutoException ae = new AutoException();
		switch (errno) {
		case 201:
			Scanner scanner201 = new Scanner(System.in);
			msg = "OptionSet not found! Please re-enter the following info in the same format \"[Old OptionSet Name]:[New OptionSet Name]\"";
			System.out.println(msg);
			ae.append_log(msg);
			String input201 = scanner201.nextLine();
			if (input201.isEmpty()) {
				msg = "Looks like you don't want to cooperate. Program is now closing...";
				System.out.println(msg);
				ae.append_log(msg);
				// System.exit(0);
			} else {
				ae.append_log(input201);
				if (input201.split(":").length != 2) {
					ae.fix(201);
				} else {
					UpdateAuto a1 = new BuildAuto();
					try {
						a1.updateOptionSetName("", input201.split(":")[0], input201.split(":")[1]);
					} catch (AutoException e) {
						System.out.println(e.getErrorNumber());
						e.fix(e.getErrorNumber());
					}
				}
			}
			break;
		case 202:
			Scanner scanner202 = new Scanner(System.in);
			msg = "OptionSet not found or Option not found or Price is invalid! Please re-enter the following info in the same format \"[OptionSet Name]:[Option Name]:[New Option Price]\"";
			System.out.println(msg);
			ae.append_log(msg);
			String input202 = scanner202.nextLine();
			if (input202.isEmpty()) {
				msg = "Looks like you don't want to cooperate. Program is now closing...";
				System.out.println(msg);
				ae.append_log(msg);
				// System.exit(0);
			} else {
				ae.append_log(input202);
				if (input202.split(":").length != 3) {
					ae.fix(202);
				} else {
					UpdateAuto a1 = new BuildAuto();
					try {
						a1.updateOptionPrice("", input202.split(":")[0], input202.split(":")[1],
								Float.parseFloat(input202.split(":")[2]));
					} catch (NumberFormatException e) {
						ae.fix(202);
					} catch (AutoException e) {
						e.fix(e.getErrorNumber());
					}
				}
			}
			break;
		}
	}
}
