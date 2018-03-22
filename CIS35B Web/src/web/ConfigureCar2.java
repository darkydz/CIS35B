package web;

import java.io.*;
import java.net.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.*;

/**
 * Servlet implementation class Page2
 */
@WebServlet("/ConfigureCar2")
public class ConfigureCar2 extends HttpServlet implements SocketClientConstants {
	private BufferedReader strIn;
	private PrintWriter strOut;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfigureCar2() {
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
		Socket sock = new Socket(hostIP, iDAYTIME_PORT);
		strOut = new PrintWriter(sock.getOutputStream(), true);
		strIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		objOut = new ObjectOutputStream(sock.getOutputStream());
		objIn = new ObjectInputStream(sock.getInputStream());

		PrintWriter out = response.getWriter();
		String autoID = request.getParameter("autoList");
		AutoWebConfig ch = new ClientHelper(strOut, strIn, objOut, objIn);
		out.println(ch.getConfigurePage(autoID));
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
