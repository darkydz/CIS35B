package driver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.*;
import client.*;
import server.*;

public class ServerDriver implements SocketClientConstants{
	public static void main(String args[]) {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;

		try {
			serverSocket = new ServerSocket(iDAYTIME_PORT);
			System.out.println("Start server on port: " + iDAYTIME_PORT);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + iDAYTIME_PORT);
            System.exit(1);
		}
	
		while (true)
		{
			try {
	            clientSocket = serverSocket.accept();
	            DefaultSocketServer serverThread = new DefaultSocketServer(clientSocket);
	            serverThread.start();
	        } catch (IOException e) {
	            System.err.println("Accept failed.");
	        }
		}
	}
}
