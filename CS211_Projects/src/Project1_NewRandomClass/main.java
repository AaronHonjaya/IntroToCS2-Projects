
/* Aaron Honjaya
 * Student ID: 202469688
 * 1/16/2023
 * Tests each of the 4 methods and the extra credit method 40 times
 */

package Project1_NewRandomClass;



public class main {
		
	public static void main(String[] args) {
		NewRandom myRandom = new NewRandom();
		int trials = 40;
		
		System.out.println("Method 1, nextInt(1,100):");
		for(int i = 0; i<40; i++) {
			int a = myRandom.nextInt(1,100);
			System.out.print(a + ", ");
		}
		System.out.println();
		
		
		System.out.println("Method 2, nextChar():");
		for(int i = 0; i<40; i++) {
			char a = myRandom.nextChar();
			System.out.print(a + ", ");
		}	
		System.out.println();

		
		System.out.println("Method 3-1, nextChar(g,y):");
		for(int i = 0; i<40; i++) {
			char a = myRandom.nextChar('g','y');
			System.out.print(a + ", ");
		}
		System.out.println();

		
		System.out.println("Method 3-2, nextChar:(z,e):");
		for(int i = 0; i<40; i++) {
			char a = myRandom.nextChar('z','e');
			System.out.print(a + ", ");
		}	
		System.out.println();

		
		System.out.println("Method 4, nextCharExcept(f):");
		for(int i = 0; i<40; i++) {
			char a = myRandom.nextCharExcept('f');
			System.out.print(a + ", ");
		}
		System.out.println();
		
		
		System.out.println("Method 5, nextID():");
		for(int i = 0; i<40; i++) {
			String a = myRandom.nextID();
			System.out.print(a + ", ");
		}
		
		
	}
}
