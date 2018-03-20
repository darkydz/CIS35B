<!DOCTYPE html>
<html>
<head><title>Client Driver</title></head>
<body>
<h1>Client Driver Page</h1>
<%@ page import="java.util.*" %>
<%@
public class ClientDriver implements SocketClientConstants{
	public static void main(String args[]) {
		DefaultSocketClient client = new DefaultSocketClient(hostIP, iDAYTIME_PORT);
		client.start();
	}
} %>
<p>
This is a simple JSP page. When first learning, make a new Dynamic Web app in Eclipse,
copy this file to the WebContent folder, deploy the app, start the server, and access 
the page with http://localhost/<i>appName</i>/hello.jsp.
</p>
</body></html>