package driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Timestamp;
import java.sql.Time;

import adapter.BuildAuto;
import adapter.CreateAuto;
import client.DefaultSocketClient;
import client.SocketClientConstants;

public class ClientDriver implements SocketClientConstants{
	public static void main(String args[]) {
		DefaultSocketClient client = new DefaultSocketClient(hostIP, iDAYTIME_PORT);
		client.start();
	}
}
