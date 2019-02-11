package dmit2015.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.math3.util.Precision;

public class Loan {
	
	@DecimalMin(value="50000", message="Mortgate Amount must be as least $50,000" )
	private double mortgageAmount = 100000;
	
	@DecimalMin(value="0.01", message="Interest Rate value be greater than zero" )	
	private double annualInterestRate = 5;
	
	@Min(value=1, message="Amortization must be between 1 and 25 years")
	@Max(value=25, message="Amortization must be between 1 and 25 years")
	private int amortizationPeriod = 25;
	
	public double getMortgageAmount() {
		return mortgageAmount;
	}
	public void setMortgageAmount(double mortgageAmount) {
		this.mortgageAmount = mortgageAmount;
	}
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	public int getAmortizationPeriod() {
		return amortizationPeriod;
	}
	public void setAmortizationPeriod(int amortizationPeriod) {
		this.amortizationPeriod = amortizationPeriod;
	}
	
	public Loan() {

	}
	
	public Loan(double mortgageAmount, double annualInterestRate, int amortizationPeriod) {
		this.mortgageAmount = mortgageAmount;
		this.annualInterestRate = annualInterestRate;
		this.amortizationPeriod = amortizationPeriod;
	}
	
	public double getMonthlyPayment() {
		double monthyPayment = 0;
		monthyPayment = mortgageAmount * (Math.pow(1 + annualInterestRate/200, 1.0/6) - 1)
				/ (1 - Math.pow(1 + annualInterestRate/200, -12 * amortizationPeriod / 6.0));
		monthyPayment = Precision.round(monthyPayment, 2);
		return monthyPayment;
	}
	
	public LoanSchedule[] getLoanSchedule() {
		LoanSchedule[] loanScheduleTable = null;
		int numberOfPayments = amortizationPeriod * 12;
		loanScheduleTable = new LoanSchedule[numberOfPayments];
		double remainingBalance = mortgageAmount;
		double principalPaid = 0;
		double interestPaid = 0;
		double monthlyPercentageRate = Math.pow(1 + annualInterestRate / 200, 1.0/6.0 ) - 1;
		double monthlyPayment = getMonthlyPayment();
		for (int paymentNumber = 1; paymentNumber <= numberOfPayments; paymentNumber++) {
			int index = paymentNumber - 1;
			interestPaid = monthlyPercentageRate * remainingBalance;
			interestPaid = Precision.round(interestPaid, 2);
			
			if (remainingBalance < monthlyPayment) {
				principalPaid = remainingBalance;
			} else {
				principalPaid = monthlyPayment - interestPaid;
				principalPaid = Precision.round(principalPaid, 2);
			}
			
			remainingBalance = remainingBalance - principalPaid;
			remainingBalance = Precision.round(remainingBalance,2 );				
			
			LoanSchedule singleLoanSchedule = new LoanSchedule();
			singleLoanSchedule.setPaymentNumber(paymentNumber);
			singleLoanSchedule.setInterestPaid(interestPaid);
			singleLoanSchedule.setPrincipalPaid(principalPaid);
			singleLoanSchedule.setRemainingBalance(remainingBalance);
			loanScheduleTable[index] = singleLoanSchedule;
		}
		
		return loanScheduleTable;
	}
	
	
}
