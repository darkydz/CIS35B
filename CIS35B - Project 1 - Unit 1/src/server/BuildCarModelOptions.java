package server;

import java.util.Properties;

public class BuildCarModelOptions {	
	public boolean buildAutoFromProp(Properties props) {
		ServerHelper sh = new ServerHelper();
		return sh.buildAutoFromProp(props);
	}
}
