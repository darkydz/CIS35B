package exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

public class AutoException extends Exception{
	public AutoException() {
		
	}
	
	public void append_log(String error) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("src/error_log.txt",true));
			bw.write(new Date() + " : " + error);
			bw.newLine();
			bw.close();
		}
		catch (IOException e) {
			System.out.println("Error: " + e.toString());
		}	
	}
}
