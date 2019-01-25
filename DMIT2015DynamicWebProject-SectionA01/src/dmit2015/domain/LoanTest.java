package dmit2015.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoanTest {

	@Test
	void testGetMonthlyPayment() {
		Loan loan1 = new Loan();
		loan1.setMortgageAmount(250000);
		loan1.setAnnualInterestRate(5.29);
		loan1.setAmortizationPeriod(25);
		
		assertEquals(1495.56, loan1.getMonthlyPayment());
	}

	@Test
	void testGetLoanSchedlue() {
		Loan loan1 = new Loan();
		loan1.setMortgageAmount(250000);
		loan1.setAnnualInterestRate(5.29);
		loan1.setAmortizationPeriod(25);
		
		LoanSchedule loanSchedule1 = loan1.getLoanSchedule()[0];
		assertEquals(1, loanSchedule1.getPaymentNumber());
		assertEquals(1090.13, loanSchedule1.getInterestPaid());
		assertEquals(405.43, loanSchedule1.getPrincipalPaid());
		assertEquals(249594.57, loanSchedule1.getRemainingBalance());

		LoanSchedule loanSchedule2 = loan1.getLoanSchedule()[1];
		assertEquals(2, loanSchedule2.getPaymentNumber());
		assertEquals(1088.36, loanSchedule2.getInterestPaid());
		assertEquals(407.20, loanSchedule2.getPrincipalPaid());
		assertEquals(249187.37, loanSchedule2.getRemainingBalance());

		LoanSchedule loanSchedule299 = loan1.getLoanSchedule()[298];
		assertEquals(299, loanSchedule299.getPaymentNumber());
		assertEquals(12.95, loanSchedule299.getInterestPaid());
		assertEquals(1482.61, loanSchedule299.getPrincipalPaid());
		assertEquals(1487.71, loanSchedule299.getRemainingBalance());

		LoanSchedule loanSchedule300 = loan1.getLoanSchedule()[299];
		assertEquals(300, loanSchedule300.getPaymentNumber());
		assertEquals(6.49, loanSchedule300.getInterestPaid());
		assertEquals(1487.71, loanSchedule300.getPrincipalPaid());
		assertEquals(0, loanSchedule300.getRemainingBalance());

	}
	
//	@Test
//	void testGetMonthlyPayment5Year() {
//		Loan loan1 = new Loan();
//		loan1.setMortgageAmount(250000);
//		loan1.setAnnualInterestRate(5.34);
//		loan1.setAmortizationPeriod(25);
//		
//		assertEquals(1503, loan1.getMonthlyPayment());
//	}

	
	
}
