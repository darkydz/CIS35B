package driver;

import client.DefaultSocketClient;
import client.SocketClientConstants;

public class ClientDriver implements SocketClientConstants{
	public static void main(String args[]) {
		DefaultSocketClient client = new DefaultSocketClient(hostIP, iDAYTIME_PORT);
		client.start();
	}
}