package server;

//import java.io.ObjectOutputStream;
import java.util.Properties;

public interface AutoServer {
	public boolean createAutoFromProp(Properties props);
	// public void sendAutoList(ObjectOutputStream out);
	// public void sendAutoObject(String autoID, ObjectOutputStream out);
}
