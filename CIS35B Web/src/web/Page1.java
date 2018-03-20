package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

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
public class Page1 extends HttpServlet implements SocketClientConstants{
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	    out.println("Page 1");
	    Socket sock = new Socket(hostIP, iDAYTIME_PORT);
	    strOut = new PrintWriter(sock.getOutputStream(), true);
		strIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		objOut = new ObjectOutputStream(sock.getOutputStream());
		objIn = new ObjectInputStream(sock.getInputStream());
		String fromServer = strIn.readLine();
		strOut.println("2");
		fromServer = strIn.readLine();
		System.out.println("Server: " + fromServer);
		if (fromServer.equals("Error: No Auto to configure. Please Upload new Auto 1st!")) {//server's fleet is empty, go back to main menu
			out.println("Server: " + fromServer);
		} else//go to Configure menu
		{
			SelectCarOption sc = new SelectCarOption();
			try {
				String[] autoList = (String[]) objIn.readObject();//receive list of cars from server
				sc.displayAutoList(autoList);//display list of car with number to select
				try {
					strOut.println(sc.selectAuto(autoList));//send selected auto object to server
				} catch (IOException e) {
					if (DEBUG)
						System.out.println("Error: Cannot select Auto!");
				}
				Automobile selectedAuto = (Automobile) objIn.readObject();
				sc.configureAuto(selectedAuto);//display list of options and user selects option choice

				try {
					System.out.println("$$$\nTotal = " + selectedAuto.getTotalPrice() + "\n$$$\n");//calculate total when all options are configured
				} catch (AutoException e) {
					if (DEBUG)
						System.out.println("Error: Cannot calculate Total for " + selectedAuto.getAutoID());
				}
			} catch (ClassNotFoundException e) {
				if (DEBUG)
					System.out.println("Error: Cannot open Auto List!");
			} catch (IOException e) {
				if (DEBUG)
					System.out.println("Error: Cannot receive Auto List!");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
