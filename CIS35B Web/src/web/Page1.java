package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.SelectCarOption;
import client.SocketClientConstants;
import exception.AutoException;
import model.Automobile;

/**
 * Servlet implementation class Page1
 */
@WebServlet("/Page1")
public class Page1 extends HttpServlet implements SocketClientConstants {
	private BufferedReader strIn;
	private PrintWriter strOut;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Page1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		StringBuilder html = new StringBuilder();
		PrintWriter out = response.getWriter();
		html.append("<!DOCTYPE html>\n" + "<html>\n" + "<head><title>Car Configuration</title></head>\n" + "<body>\n");
		Socket sock = new Socket(hostIP, iDAYTIME_PORT);
		request.setAttribute("socket", sock);
		strOut = new PrintWriter(sock.getOutputStream(), true);
		strIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		objOut = new ObjectOutputStream(sock.getOutputStream());
		objIn = new ObjectInputStream(sock.getInputStream());
		String fromServer = strIn.readLine();
		if (DEBUG)
			System.out.println("Server Acknowledges: " + fromServer);
		strOut.println("GET_AUTO_LIST");
		fromServer = strIn.readLine();
		if (DEBUG)
			System.out.println("Server: " + fromServer);

		if (fromServer.equals("Error: No Auto to configure. Please Upload new Auto 1st!")) {// server's fleet is empty,
																							// go back to main menu
			html.append("<p>No Auto to configure. Please check back later!</p>\n");
		} else// go to Configure menu
		{
			html.append("<h1>Car Configuration</h1>\n" + "<p>Please select an auto below to configure:</p>\n"
					+ "<form action =\"Page2\" method=\"POST\">" + "<select name=\"autoList\">");
			try {
				String[] autoList = (String[]) objIn.readObject();// receive list of cars from server
				for (int i = 0; i < autoList.length; i++) {
					html.append("<option>" + autoList[i] + "</option>");
				}
				html.append("</select><input type = \"submit\" value = \"Configure\"/></form>");
			} catch (ClassNotFoundException e) {
				if (DEBUG)
					System.out.println("Error: Cannot open Auto List!");
			} catch (IOException e) {
				if (DEBUG)
					System.out.println("Error: Cannot receive Auto List!");
			}
		}
		html.append("</body></html>");
		out.println(html.toString());
//		RequestDispatcher rd =  
//                request.getRequestDispatcher("Content");
//              rd.forward(request, response);
		sock.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
