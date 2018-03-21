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
 * Servlet implementation class Page2
 */
@WebServlet("/Page2")
public class Page2 extends HttpServlet implements SocketClientConstants {
	private BufferedReader strIn;
	private PrintWriter strOut;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Page2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Socket sock = new Socket(hostIP, iDAYTIME_PORT);
		strOut = new PrintWriter(sock.getOutputStream(), true);
		strIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		objOut = new ObjectOutputStream(sock.getOutputStream());
		objIn = new ObjectInputStream(sock.getInputStream());
		String fromServer = strIn.readLine();
		if (DEBUG)
			System.out.println("Server Acknowledges: " + fromServer);

		response.setContentType("text/html");

		StringBuilder html = new StringBuilder();
		html.append("<!DOCTYPE html><html><head><title>Car Configuration</title></head><body>\n");

		PrintWriter out = response.getWriter();
		strOut.println("GET_AUTO");// tell Server to wait for Auto ID
		fromServer = strIn.readLine();
		if (DEBUG)
			System.out.println("Server: " + fromServer);
		String autoID = request.getParameter("autoList");
		strOut.println(autoID);
		Automobile selectedAuto = null;
		try {
			selectedAuto = (Automobile) objIn.readObject();
			// request.setAttribute("auto", selectedAuto);
			html.append("<h1>Car Configuration: " + autoID + "</h1><p>Please select options below:</p>");
			html.append("<form action =\"Page3\" method=\"POST\">");
			html.append("<input type=\"hidden\" name=\"autoID\" value=\"" + autoID + "\">");
			html.append("<input type=\"hidden\" name=\"basePrice\" value=\"" + selectedAuto.getBasePrice() + "\">");
			html.append("<table>");
			String[] opSetList = selectedAuto.getOptionSetList();
			html.append("<input type=\"hidden\" name=\"opSetCount\" value=\"" + opSetList.length + "\">");
			for (int i = 0; i < opSetList.length; i++) {
				html.append("<tr>");
				html.append("<td><input name=\"option_"+i+"\" value=\"" + opSetList[i] + "\" readonly></td><td><select name=\"" + opSetList[i] + "\">");
				String[] opList = selectedAuto.getOptionList(opSetList[i]);
				for (int j = 0; j < opList.length; j++) {
					html.append("<option value=\"" + opList[j] + "_"
							+ selectedAuto.getOptionPrice(opSetList[i], opList[j]) + "\">" + opList[j]
							+ "</option>");
					// html.append("<input type=\"hidden\" name=\"" + opList[j] + "\" value=\""
					// + selectedAuto.getOptionPrice(opSetList[i], opList[j]) + "\">");
				}
				html.append("</select></td></tr>");
				
			}
			html.append("</table><input type = \"submit\" value = \"Get Quote\"/></form>");
			html.append("</body></html>");
		} catch (ClassNotFoundException e) {
			html = new StringBuilder("Error1! Please restart!");
		}
		catch (AutoException e) {
			html = new StringBuilder("Error2! Please restart!");
		}
		out.println(html.toString());
		sock.close();
		// RequestDispatcher rd = request.getRequestDispatcher("Page3");
		// rd.forward(request, response);
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
