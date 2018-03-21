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
		html.append("<!DOCTYPE html><html><head><title>Car Configuration</title></head><body>\n");
//		html.append("auto:" + request.getAttribute("auto") + "<hr>");
		PrintWriter out = response.getWriter();
		Enumeration<String> paraList = request.getParameterNames();
		double total = 0;
		while (paraList.hasMoreElements()) {
			String para = (String) paraList.nextElement();
			html.append(para);
			html.append(":");
			String opname_price = request.getParameter(para);
			String opName = opname_price.split("_")[0];
			float opPrice = Float.parseFloat(opname_price.split("_")[1]);
			total += opPrice;
			html.append(opName + " = " + opPrice);
			html.append("<br>");
		}
		html.append("</body></html>");
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
