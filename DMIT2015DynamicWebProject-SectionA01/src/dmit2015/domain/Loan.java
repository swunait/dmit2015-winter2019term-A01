package dmit2015.domain;

public class Loan {
	
	private double mortgageAmount;
	private double annualInterestRate;
	private int amortizationPeriod;
	
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
		monthyPayment = roundTo2Decimals(monthyPayment);
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
			interestPaid = roundTo2Decimals(interestPaid);
			
			if (remainingBalance < monthlyPayment) {
				principalPaid = remainingBalance;
			} else {
				principalPaid = monthlyPayment - interestPaid;
				principalPaid = roundTo2Decimals(principalPaid);
			}
			
			remainingBalance = remainingBalance - principalPaid;
			remainingBalance = roundTo2Decimals(remainingBalance);				
			
			LoanSchedule singleLoanSchedule = new LoanSchedule();
			singleLoanSchedule.setPaymentNumber(paymentNumber);
			singleLoanSchedule.setInterestPaid(interestPaid);
			singleLoanSchedule.setPrincipalPaid(principalPaid);
			singleLoanSchedule.setRemainingBalance(remainingBalance);
			loanScheduleTable[index] = singleLoanSchedule;
		}
		
		return loanScheduleTable;
	}
	
	public static double roundTo2Decimals(double valueToRound)
	{
		return Math.round( valueToRound * 100 ) / 100.0;
	}
}
