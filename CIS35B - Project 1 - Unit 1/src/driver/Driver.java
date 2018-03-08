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
		String filetype = "";
		
		System.out.println("\\n\\n1. Test Working ToyotaPrius2018.prop");
		autoID = "Toyota Prius 2018";
		filetype = ".prop";
		a1.buildAuto("src/AutoDataFiles/" + autoID + filetype,filetype);
//		System.out.println("\n\n1.a.Before");
		a1.printAuto(autoID);

		System.out.println("\n\n");
		System.out.println("PROGRAM FINISHED!");
//		int count = 10;
//		System.out.println("test" + (count+1));
	}
}
