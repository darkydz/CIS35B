package server;

import java.util.Properties;

public class BuildCarModelOptions {
	/**
	 * 
	 * @param props Properties object
	 * @return true if new Automobile is added to fleet; otherwise, false.
	 */
	public boolean buildAutoFromProp(Properties props) {
		ServerHelper sh = new ServerHelper();
		return sh.buildAutoFromProp(props);
	}
}
