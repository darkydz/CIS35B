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
		AutoThread t1 = new BuildAuto();
		AutoThread t2 = new BuildAuto();
	
		String autoID = "";

//		System.out.println("\n\n");
//		System.out.println("1. Test Working Ford Focus Wagon ZTW 2017");
		autoID = "Ford Focus Wagon ZTW 2017";
		a1.buildAuto("src/AutoDataFiles/" + autoID + ".txt");
//		a1.printAuto(autoID);
		
//		System.out.println("\n\n");
//		System.out.println("Editor 1 set Color Name");
		
		t1.updateOptionName("E1", autoID, "Color", "French Blue Clearcoat Metallic","Test1");
		t2.updateOptionName("E2", autoID, "Color", "French Blue Clearcoat Metallic","Test2");
//		t1.updateOptionName("E1", autoID, "Color", "Test1","Test11");
		
//		System.out.println("\n\n");
//		System.out.println("After editing");
//		a1.printAuto(autoID);
//		
//		System.out.println("\n\n");
//		System.out.println("PROGRAM FINISHED!");
	}
}
