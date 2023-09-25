package Project1_NewRandomClass;

import java.util.Random;

public class NewRandom extends Random{
	public int nextInt(int low, int high) {
		/*to make this random, I had to use the random function already available. 
		 * Dividing the random number by the range enusres that the 
		 * change from low will not exceed the upper bound. 
		 * Adding one is to enusre that the remainder can be equal to
		 * the range itself. If 1 wasn't added, the upper bound would
		 * never be included.
		 * e.g. 5-9, range = 4, greatest remainder possible = 3, 9 never included. 
		 * Must be the absolute value since the random int could be negatvie. 
		 */
		int range = high - low + 1; 
		int changeFromLow = Math.abs(super.nextInt())%range;
		return low+changeFromLow;
	}
	
	public char nextChar() {
		//makes use of ASCII values and the next Int function. 
		return (char) nextInt('a', 'z');
	}
	
	public char nextChar(char from, char to) {
		char c;
		if(from < to) {
			c = (char)nextInt(from, to);
		}else {
			c = (char)nextInt('a', 'z');
			while(to < c && c < from) {
				c = (char)nextInt('a', 'z');
			}
		}
		return c;
	}
	
	
	public char nextCharExcept(char c) {
		int exception = c;
		char nextChar = (char) nextInt('a', 'z');
		while(nextChar == exception) {
			nextChar = (char) nextInt('a', 'z');
		}
		return nextChar;
	}
	
	public String nextID() {
		//array used so that index could also be randomized.
		char[] randomIDCharList = new char[8];
		boolean firstCharIsUpperCase = super.nextBoolean();
		char firstChar = nextChar();
		randomIDCharList[0] = firstCharIsUpperCase? Character.toUpperCase(firstChar) : firstChar;	
	
		//starts at 1 because first char must be a letter
		int upperCaseIndex = nextInt(1, randomIDCharList.length-1);
		int numberIndex = nextInt(1, randomIDCharList.length-1);
		int specialCharIndex = nextInt(1, randomIDCharList.length-1);
		
		//makes sure none of them replace another
		while(upperCaseIndex == numberIndex ||
				upperCaseIndex == specialCharIndex ||
				numberIndex == specialCharIndex) {
			//index starts from 1 so that the first letter isn't replaced
			upperCaseIndex = nextInt(1, randomIDCharList.length-1);
			numberIndex = nextInt(1, randomIDCharList.length-1);
			specialCharIndex = nextInt(1, randomIDCharList.length-1);
		}	
		
		randomIDCharList[numberIndex] = (char)nextInt(0,9);
		randomIDCharList[specialCharIndex] = randomSpecialChar();
		
		if(!firstCharIsUpperCase) {
			randomIDCharList[upperCaseIndex] = Character.toUpperCase(nextChar());
		}
		
		for(int i = 1; i<randomIDCharList.length; i++) {
			/*when array is created, the default values are zero. 
			*So if its equal to zero we can replace the index given
			*its not the same index as a the number since the random number can equal zero
			*/
			if(randomIDCharList[i] == 0 && i != numberIndex) {
				
				randomIDCharList[i] = nextChar('!','~');
			}
		}
		
		String randomID = "";
		for(int i = 0; i<randomIDCharList.length; i++) {
			//the int generated earlier was casted to a char
			//to fit into the array and must be cast back to an int.
			if(i == numberIndex) {
				randomID += (int) randomIDCharList[i];	
			}else {
			randomID = randomID + randomIDCharList[i];
			}
		}
		
		return randomID;
	}
	
	
	//method made to get a random special char and clean up the nextID method
	public char randomSpecialChar() {
		char c = nextChar('!', '~');
		while(Character.isLetterOrDigit(c)) {
			c = nextChar('!', '~');
		}
		return c;
	}

	
	//Was used to debug. Currently Unused
	private static void printArray(char[] array) {
		
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}
}
