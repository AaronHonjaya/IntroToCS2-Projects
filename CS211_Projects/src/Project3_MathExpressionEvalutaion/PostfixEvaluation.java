package Project3_MathExpressionEvalutaion;

import java.util.*;

public class PostfixEvaluation {

	public double evaluate(String postfix) {
		
		//use double so that something like 2/3 doesn't return 0;
		double result = 0, num1 = 0, num2 ;
		Stack<Double> stack = new Stack<>();
		
		//we create a new scanner for the postfix expression.
		Scanner input = new Scanner(postfix);
		
		//continues only while the postfix expression has more operators or operands. 
		while(input.hasNext()) {
			
			//tries to get next token as a double;
			/*Since there are only operators or numbers, if an Exception is thrown, 
			 *then it must be because the next token is an operator, 
			 *or it is a number with multiple negative signs (see note).
			 *Note: scanner can read -2 as a number but cannot read --2 or ---2 as a number. 
			 */
			try {
				double currentNumber = input.nextDouble();
				stack.push(currentNumber);
				
			}catch(Exception e) {
				
				//we set the next token as a string and the operator as the first char of the string
				String s = input.next();
				char operator = s.charAt(0);
				
				//if the operator is not '-', then we can perform the operation.
				if(operator != '-') {
					//operands will be the top two numbers of the stack. 
					num1 = stack.pop();
					num2 = stack.pop();
					//we push the operation using the operator and operands. 
					//note: see below for "performOperation" method. 
					stack.push(performOperation(num2, num1, operator));
				}else {
					//in the case of a negative number with multiple negative signs
					//e.g. --2
					int numOfNegatives = 0;
					double num = 0;
					
					//counts number of negative signs
					for(int i = 0; i<s.length(); i++) {
						if(s.charAt(i) == '-') {
							numOfNegatives++;
						}
					}		
					//new string with negatives as zeroes 
					//e.g. --2 goes to 002.
					String numWithoutNegatives = s.replace('-', '0');
					
					//parse the new string as a double. zeroes add no value to the double. 
					num = Double.parseDouble(numWithoutNegatives);

					//multiply the number by -1 raised to the power of the number of negatives. 
					num *= Math.pow(-1, numOfNegatives);
					
					//push the final number.
					stack.push(num);
				}
			}
		}
		//pop the final result and return it. 
		result = stack.pop();
		return result;	
	}
	
	//method to perform operation given two doubles and an operator.
	//again, no - operator necessary. 
	private double performOperation(double a, double b, char operator) {
		double result = 0;
		
		switch(operator) {
			case '+':
				result = a+b;
				break; 
					
			case '/':
				result = a/b;
				break; 
				
			case '*':
				result = a*b;
				break;
		}
		
		return result;
		
	}
	
}
