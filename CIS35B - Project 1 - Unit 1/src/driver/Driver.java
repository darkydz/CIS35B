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
		String autoID = "";

		System.out.println("\\n\\n1. Test Working Ford Focus Wagon ZTW 2017: Synched Automobile");
		autoID = "Ford Focus Wagon ZTW 2017";
		a1.buildAuto("src/AutoDataFiles/" + autoID + ".txt");
		System.out.println("\n\n1.a.Before");
		a1.printAuto(autoID);

		AutoThread t1 = new BuildAuto();
		AutoThread t2 = new BuildAuto();
		AutoThread t3 = new BuildAuto();
		System.out.println("\n\n1.a.After changing Color");
		t1.updateOptionName("E1", autoID, "Color", "French Blue Clearcoat Metallic","Test1");
		t2.updateOptionName("E2", autoID, "Color", "French Blue Clearcoat Metallic","Test2");
		t3.updateOptionName("E3", autoID, "Color", "French Blue Clearcoat Metallic","Test3");

		
//		System.out.println("\\n\\n2. Test Working Toyota Camry Sport 2018: Synched EditOtions");
//		autoID = "Toyota Camry Sport 2018";
//		a1.buildAuto("src/AutoDataFiles/" + autoID + ".txt");
//		System.out.println("\n\n2.a.Before");
//		a1.printAuto(autoID);
//		
//		AutoThread t4 = new BuildAuto();
//		AutoThread t5 = new BuildAuto();
//		AutoThread t6 = new BuildAuto();
//		System.out.println("\n\n2.a.After");
//		t1.updateOptionName(2,"E4", autoID, "Color", "Cloud 9 White Clearcoat","Test4");
//		t2.updateOptionName(2,"E5", autoID, "Color", "Cloud 9 White Clearcoat","Test5");
//		t3.updateOptionName(2,"E6", autoID, "Color", "Cloud 9 White Clearcoat","Test6");
		
		System.out.println("\n\n");
		System.out.println("PROGRAM FINISHED!");
	}
}
