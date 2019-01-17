package dmit2015.demo01;

public class Calculator 
{

	public static void main(String[] args) 
	{
		// Check the number of strings passed 
		if (args.length != 3) 
		{
			System.out.println("Usage: java Calculator operand1 operator operand2");
			System.exit(1);
		}
		
		// The result of the operation
		int result = 0;
		int operand1 = Integer.parseInt(args[0]);
		char operator = args[1].charAt(0);
		int operand2 = Integer.parseInt(args[2]);
		
		// Determine the result (+, -, ., /)
		// Use the . symbol for multiplication instead of * symbol, 
		// as * refers to all the files in the current directory
		if (operator == '+') {
			result = operand1 + operand2;
		} else if (operator == '-') {
			result = operand1 - operand2;
		} else if( operator == '.' ) {
			result = operand1 * operand2;
		} else if( operator == '/' ) {
			result = operand1 / operand2;
		}		
		
		// Display the result in the following format: operator1 operand operator2 = result
		// Display the result using string concatenation technique
		System.out.println(operand1 + " " + operator + " " + operand2 + " = " + result);
		// Display the result using String formatting
		String message = String.format("%d %s %d = %d", operand1, operator, operand2, result);
		System.out.println(message);

	}

}
