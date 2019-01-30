package dmit2015.domain;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoanJsonResultServlet
 */
@WebServlet("/LoanJsonResultServlet")
public class LoanJsonResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		JsonArrayBuilder loanScheduleBuilder = Json.createArrayBuilder();
		for (LoanSchedule currentLoanSchedule : currentLoan.getLoanSchedule()) {
			JsonObject loanScheduleJson = Json.createObjectBuilder()
					.add("paymentNumber", currentLoanSchedule.getPaymentNumber())
					.add("interestPaid", currentLoanSchedule.getInterestPaid())
					.add("principalPaid", currentLoanSchedule.getPrincipalPaid())
					.add("remainingBalance", currentLoanSchedule.getRemainingBalance())
					.build();		
			loanScheduleBuilder.add(loanScheduleJson);
		}
		JsonObject resultJson = Json.createObjectBuilder()
				.add("monthlyPayment", currentLoan.getMonthlyPayment())
				.add("loanSchedules", loanScheduleBuilder)
				.build();
		
		out.println(resultJson.toString());
		out.close();	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
