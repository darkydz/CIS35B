package server;

import adapter.ProxyAutomobile;
import exception.AutoException;
import util.FileIO;

public class Server extends ProxyAutomobile{
	public void buildAuto(String filename, String filetype) {
		switch (filetype)
		{
			case ".prop":
				FileIO io = new FileIO();
				try {
					autos.addAuto(io.buildAutomobileObject(filename));
				} catch (AutoException e) {
					e.fix(e.getErrorNumber());
				}
				break;
			case ".txt":
				buildAuto(filename);
				break;
		}
	}
}
