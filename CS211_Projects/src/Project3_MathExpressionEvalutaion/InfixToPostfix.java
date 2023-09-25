package Project3_MathExpressionEvalutaion;

import java.util.*;

public class InfixToPostfix {
	
	private Stack<Character> expressionStack = new Stack<>();
	private HashMap<Character, Integer> operatorPrecedence = new HashMap<>();
	
	//postfix was set as a private variable so that a method "pushOperator" could be created with ease;
	//Everything changes this private variable instead of having two methods create two different strings, 
	//and then having to concatenate them. 
	private String postfix;
	
	public InfixToPostfix() {
		//bigger integer value = higher precedence.
		//again, minus not treated as an operator so we can work with negative numbers. 
		operatorPrecedence.put('+', 1);
		operatorPrecedence.put('*', 2);
		operatorPrecedence.put('/', 2);
		operatorPrecedence.put('{', 3);
		operatorPrecedence.put('(', 3);
		
	}
	
	public String toPostfix(String infix) {
		
		//because postfix is a private variable, it must be reset when this method is used. 
		this.postfix = "";
		
		for(int i = 0; i<infix.length(); i++) {

			System.out.println(expressionStack);
			//current char = c
			char c = infix.charAt(i);
				
			
			if(Character.isDigit(c)) {
				
				//makes sure index out of bounds error isn't generated
				if(i<infix.length()-1) {
					
					//to calculate expressions such as 2(3+2);
					//turns expression from 2(3+2) to 2*(3+2) which is then readable.  
					if(infix.charAt(i+1) == '{' || infix.charAt(i+1) == '(')
					{
						pushOperator('*');
					}	
				}
				
				//makes sure index out of bounds error isn't generated
				if(i>0) {
					//to calculate expression such as (2+3)2. 
					//turns expression from 2(3+2) to (3+2)*2 which is then readable.  
					if(infix.charAt(i-1) == '}' || infix.charAt(i-1) == ')')  
					{
						pushOperator('*');
					}
				}	
					
				this.postfix += c;
				
				//adds space so that postfix can be read for evaluation. 
				if(i+1>=infix.length() || !Character.isDigit(infix.charAt(i+1))) 
				{
					this.postfix += " ";
				}	
			}
			
			//so that negative numbers can be read & evaluated.
			if(c == '-') {
			
				//makes sure index out of bounds exception isn't thrown. 
				if(i>0) {
					
					//to get the last char that isn't an empty space
					char lastChar = ' ';
					int j = 1;
					while(lastChar == ' ') {
						lastChar = infix.charAt(i-j);
						j++;
					}
					
					/*
					 *we add the negative number to the postfix. 
					 *This means that 2-3 doesn't have any operator. 
					 *therefore, we must push a + operator in such as a scenario. 
					 */
					if(Character.isDigit(lastChar) || infix.charAt(i-1) == '}' || 
							infix.charAt(i-1) == ')') {
						
						pushOperator('+');
					}
				}
				this.postfix += c;
				
			}
			
			if(c == '(' || c=='{') {
				expressionStack.push(c);
			}
			
			if(c == '+' || c == '*' || c == '/') 
			{
				pushOperator(c);
			}
			
			if (c == ')') {

				//pops & adds to posftix until paired parenthesis is found
				while(expressionStack.peek() != '(' ) 
				{
					this.postfix += expressionStack.pop();
					//space must be added in between every operator. 
					this.postfix += " ";
					
				}
				//throws away the ( at the end. 
				expressionStack.pop();
			
			}
			
			//same exact concept as ) but with } instead.
			if (c == '}') {
				while(expressionStack.peek() != '{' ) 
				{
					this.postfix += expressionStack.pop();
					this.postfix += " ";
				}
				expressionStack.pop();	
			}
			
		}//end of for
		
		//pops and adds all left over operators. 
		while(!expressionStack.isEmpty()) {
			this.postfix += expressionStack.pop();
			this.postfix += " ";
		}
		
		
		return this.postfix;
	}
	
	
	public void pushOperator(char operator) {
		
		//checks if the stack is empty before doing anything to avoid generating an error. 
		if(!expressionStack.isEmpty()) 
		{
			
			char topChar = expressionStack.peek();
			
			//pushes if top of stack is lower priority than the current operator.
			//should also push if ( or { are at the top since they can be inside the parenthesis. 
			if(topChar == '(' || topChar == '{' || 
					operatorPrecedence.get(topChar) < operatorPrecedence.get(operator)) 
			{
				expressionStack.push(operator);
				
			}
		
			else if(operatorPrecedence.get(topChar) >= operatorPrecedence.get(operator))
			{
				
				while(operatorPrecedence.get(topChar) >= operatorPrecedence.get(operator)) 
				{
					//System.out.println("hi");
					topChar = expressionStack.peek();															
					//must check if the expression stack is empty or if the operator is a parenthesis
					//in such cases we can exit the for loop. 
					if(topChar == '(' || topChar == '{' || expressionStack.isEmpty()) 
					{
						break;
					}
						
					this.postfix += expressionStack.pop();
					this.postfix += " ";
				} // end of while
				
				//after popping all necessary elements from stack, we can push the operator onto the stack.
				expressionStack.push(operator);
			}
		}
		//if the expression stack is empty we can just push the operator. 
		else {
			expressionStack.push(operator);
		}
	}

}
