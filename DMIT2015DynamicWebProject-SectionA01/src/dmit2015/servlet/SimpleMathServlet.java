package dmit2015.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleMathServlet
 */
@WebServlet("/public/servlet/AdditionServlet")
public class SimpleMathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Store the input parameters into Strings
		String num1String = request.getParameter("num1");
		String num2String = request.getParameter("num2");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>SimpleMathServlet</title>");
			out.println("</head>");
			out.println("<body>");
			
			try {
				int result = Integer.parseInt(num1String) + Integer.parseInt(num2String);
				out.println("<p>Solution: " 
						+ num1String + " + " + num2String 
						+ " = " + result + "</p>");
			} catch(NumberFormatException ex) {
				out.println("<p>Please enter numbers only.</p>");
			}
			
			
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
		
	}

}
