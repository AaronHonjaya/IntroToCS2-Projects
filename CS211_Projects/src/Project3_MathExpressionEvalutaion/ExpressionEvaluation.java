package Project3_MathExpressionEvalutaion;

import java.util.*;

public class ExpressionEvaluation {
	
	//operator and operand stack used to catch errors such as +2+3 or 2++2 or 2 3 + 3
	//cannot just check if charAt(i-1) since error could be something like "2     3 + 2" with many spaces
	Stack<Character> OperatorAndOperandStack = new Stack<>();
	
	//parenthesis and bracket stack used to catch errors discussed in class such as ((1+2) or (1+2
	Stack<Character> ParenthesisAndBracketStack = new Stack<>();
	
	//list of error messages. 
	HashMap<Integer, String> errorMessages = new HashMap<>();
	
	public boolean isValid(String expression) {
		
		//initializes errorMessages and stacks
		initialize();
		
		
		for(int i = 0; i<expression.length(); i++) {
			
			//c is current char
			char c = expression.charAt(i);
		
			// only ( and { are pushed to the parenthesis and bracket stack
			if(c == '(' || c == '{') {
				ParenthesisAndBracketStack.push(c);
			}
			
			//minus sign "-" is not treated as an operator because I decided to allow expressions like 2+-3. 
			//My other programs can evaluate such expressions. 
			//Note: I know that this was not required, I just did it for fun and practice. 
			if(c == '+' || c == '*' || c == '/') {
	
				//this catches the error if the expression begins with an operator
				//e.g.  +2+3 or *2+3
				if(OperatorAndOperandStack.isEmpty()){
					printError(i, 4, expression);
					return false;
				}
				
				//catches double operator errors such as 3++2 OR 3*+2 OR 3+2+
				if(i+1 >= expression.length() || !Character.isDigit(OperatorAndOperandStack.peek())) {
					printError(i, 4, expression);
					return false;
				}
				OperatorAndOperandStack.push(c);
			}
			
			if(c == '-') {
				OperatorAndOperandStack.push(c);
			}
			
			if(Character.isDigit(c)) {
				//must check if the stack is empty before peeking. otherwise it can generate an error. 
				if(!OperatorAndOperandStack.isEmpty()) {
					
					//catches an error if there is a space in between two numbers. 
					//e.g. 2 3
					//however, if it is a negative or operator, spaces are allowed. 
					//e.g. 3 - 3 is okay. 
					if( (Character.isDigit(OperatorAndOperandStack.peek()) && OperatorAndOperandStack.peek() != '-')
							&& expression.charAt(i-1) == ' ') {
						printError(i-1, 3, expression);
						return false;
					}
				}
				OperatorAndOperandStack.push(c);
			}
			
			if(c == '}') {
				//catches error such as }2+3
				if(ParenthesisAndBracketStack.isEmpty()) 
				{
					printError(i, 5, expression);
					return false;
				}
				//only  ( and { are added to this stack so only need to pop once and check if it is paired.
				else if(ParenthesisAndBracketStack.pop() == '(')
				{
					printError(i, 1, expression);
					return false;
				}
					
				
			}
			if(c == ')') {
				//catches error such as )2+3
				if(ParenthesisAndBracketStack.isEmpty()) 
				{
					printError(i, 5, expression);
					return false;
				}
				//only  ( and { are added to this stack so only need to pop once and check if it is paired.
				else if(ParenthesisAndBracketStack.pop() == '{')
				{
					printError(i, 2, expression);
					return false;
				}
			}	
			
		}//end of for
		
		//checks if the expression is missing a close parenthesis at the end.
		if(!ParenthesisAndBracketStack.isEmpty()) {
			printError(expression.length(), 6, expression);
			return false;
		}
		
		return true;
	}//end of method
	

	
	//initializes all messages and stacks.
	private void initialize() {
		errorMessages.put(1, " ')' Expected");
		errorMessages.put(2, " '}' Expected");
		errorMessages.put(3, "Unexpected Space");
		errorMessages.put(4, "Unexpected Operator");
		errorMessages.put(5, "Unpaired Parenthesis OR Curly Bracket");
		errorMessages.put(6, "Missing Parenthesis OR Curly Bracket");
		
		//must clear stacks before running program again. OperatorAndOperand stack never really pops. 
		OperatorAndOperandStack.clear();
		ParenthesisAndBracketStack.clear();


	}
	
	//error print method shown in class
	private void printError(int location, int errorNumber, String expression) {
		
		System.out.println(expression);
		for(int i=0; i < location; i++) {
			System.out.print(" ");
		}
		System.out.print("^ " + errorMessages.get(errorNumber));
		System.out.println();
	}
	
	
}
