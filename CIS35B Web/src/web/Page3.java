package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Page3
 */
@WebServlet("/Page3")
public class Page3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Page3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		StringBuilder html = new StringBuilder();
		html.append("<!DOCTYPE html><html><head><title>Car Configuration</title></head><body>");
		html.append("<style>"
				+ "table, td, th {"
					+ "border: 1px solid black;"
					+ "border-collapse: collapse;"
					+ "padding: 5px;"
					+ "}"
				+ "</style>");
		html.append("<p>Below is you quote:</p>");
		PrintWriter out = response.getWriter();
//		Enumeration<String> paraList = request.getParameterNames();
		float basePrice = Float.parseFloat(request.getParameter("basePrice"));
		String autoID = request.getParameter("autoID");
		html.append("<table><tr><th>" + autoID + "</th><td>Base Price</td><td>$" + basePrice + "</td></tr>");
		double total = basePrice;
		int opSetCount = Integer.parseInt(request.getParameter("opSetCount"));
		for (int i = 0; i < opSetCount; i++) {
			String setName = request.getParameter("option_" + i);
			String opname_price = request.getParameter(setName);
			String opName = opname_price.split("_")[0];
			float opPrice = Float.parseFloat(opname_price.split("_")[1]);
			html.append("<tr><td>" + setName + "</td>");
			html.append("<td>" + opName + "</td>");
			html.append("<td>$" + opPrice + "</td></tr>");
			total += opPrice; 
		}
		html.append("<tr><th>Total Cost</th><td></td><td>$" + total + "</td></tr>");
//		while (paraList.hasMoreElements()) {
//			String para = (String) paraList.nextElement();
//			html.append(para);
//			html.append(":");
//			String opname_price = request.getParameter(para);
//			String opName = opname_price.split("_")[0];
//			float opPrice = Float.parseFloat(opname_price.split("_")[1]);
//			total += opPrice;
//			html.append(opName + " = " + opPrice);
//			html.append("<br>");
//		}
		html.append("</table></body></html>");
		out.println(html.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
