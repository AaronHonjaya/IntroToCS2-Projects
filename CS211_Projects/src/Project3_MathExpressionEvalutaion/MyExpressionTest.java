package Project3_MathExpressionEvalutaion;

import java.util.Scanner;

public class MyExpressionTest {
	
	public static void main(String[] args){
		
		//declare objects and variables
		ExpressionEvaluation ee = new ExpressionEvaluation();
		InfixToPostfix infixToPostfix = new InfixToPostfix();
		PostfixEvaluation postEval = new PostfixEvaluation();
		Scanner input = new Scanner(System.in);
		boolean isValid;
		String infix;
		
		while(true) {
			//get user input/infix
			System.out.println("Input a Math Expression OR Press Stop to Quit: ");
			infix = input.nextLine();
			
			//stop program if user wants to stop. otherwise evaluate infix
			if(infix.equalsIgnoreCase("Stop")) {
				System.out.println("Bye!");
				break;
			}else {
				//check if infix is valid before evaluation.
				isValid = ee.isValid(infix);
			
				if(isValid) {
					//get postfix and print it. 
					String postfix = infixToPostfix.toPostfix(infix);
					System.out.println("Postfix Expression: " + postfix);
					
					//get result and print it. 
					double result = postEval.evaluate(postfix);
					System.out.println("result: " + result);
				

				}
			}
		}
	}
}
