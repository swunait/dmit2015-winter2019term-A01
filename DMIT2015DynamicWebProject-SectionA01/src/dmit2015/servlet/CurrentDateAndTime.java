package dmit2015.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CurrentDateAndTime
 */
@WebServlet("/CurrentDateAndTime")
public class CurrentDateAndTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LocalDateTime currentDateTime = LocalDateTime.now();

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>CurrentDateAndTime</title>");
			out.println("</head>");
			out.println("<body>");
			synchronized (currentDateTime) {
				currentDateTime = LocalDateTime.now();
				out.println("<h2>The current date and time is " + currentDateTime + "</h2>" );
			}			
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
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
