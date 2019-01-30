package dmit2015.domain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoanRedirectServlet
 */
@WebServlet("/LoanRedirectServlet")
public class LoanRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String amountString = request.getParameter("amount");
		String interestRateString = request.getParameter("interestRate");
		String periodString = request.getParameter("period");
		
		double amount = Double.parseDouble(amountString);
		double interestRate = Double.parseDouble(interestRateString);
		int period = Integer.parseInt(periodString);
		
		Loan currentLoan = new Loan();
		currentLoan.setMortgageAmount(amount);
		currentLoan.setAnnualInterestRate(interestRate);
		currentLoan.setAmortizationPeriod(period);

		// Use the dispatcher to forward the request to another URL
		request.setAttribute("currentLoan", currentLoan);
		getServletContext().getRequestDispatcher("/mortgageCalculatorRedirectResult.jsp").forward(request, response);
		
		// Redirect the request to another URL
//		HttpSession session = request.getSession();
//		session.setAttribute("currentLoan", currentLoan);
//		response.sendRedirect(
//				request.getContextPath() 
//				+
//				"/mortgageCalculatorRedirectResult.jsp");
	}

}
