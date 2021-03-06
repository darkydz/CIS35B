package exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

public class AutoException extends Exception {
	private int errNum;

	public AutoException() {
	}

	public AutoException(int errno) {
		errNum = errno;
	}

	/**
	 * 
	 * @return the error number that was passed
	 */
	public int getErrorNumber() {
		return errNum;
	}

	/**
	 * This will write any message to error_log with timestamp
	 * @param error
	 */
	public void append_log(String error) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("src/error_log.txt", true));
			bw.write(new Date() + " : " + error);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.toString());
		}
	}

	/**
	 * A universal Fixer for this program, taking an error number and pass to the right helper
	 * @param errno
	 */
	public void fix(int errno) {
		append_log("Error#" + errno);
		switch ((errno + 99) / 100) {
		case 1:
			Fix1to100Auto f1 = new Fix1to100Auto();
			f1.fix(errno);
			break;
		case 2:
			Fix101to200Util f2 = new Fix101to200Util();
			f2.fix(errno);
			break;
		case 3:
			Fix201to300Adapter f3 = new Fix201to300Adapter();
			f3.fix(errno);
			break;
		default:
			System.out.println("No such Error Number exists! Program is now exiting...");
			append_log("No such Error Number exists! Program is now exiting...");
			System.exit(0);
		}
	}
}
