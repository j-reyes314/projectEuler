package Projects;

public class Problem4 {

	static String palindrome  = "";
	
	/*A palindromic number reads the same both ways. 
	  The largest palindrome made from the product of
	  two 2-digit numbers is 9009 = 91 × 99.
	  Find the largest palindrome made from the product of two 3-digit numbers.
	*/
	public static void main(String args[]) {
		
		int largest = 999 * 999;
		System.out.println(largest);
		
		int quotient = 0;
		//String palindrome  = "";
		
		for(int i = 900; i <999; i++) {
			for(int k = 900; k < 999; k++) {
				quotient = i * k;
				
				//System.out.println(quotient);
				
				palindrome = String.valueOf(quotient);
				//System.out.println(palindrome);
				palndrome(palindrome);
				//.System.out.println(palindrome.length());
				
				//if(palindrome.charAt(0) == palindrome.charAt(palindrome.length()-1))	System.out.println(palindrome);
				
			}
		}
		
		
		
	}
	
	public static void palndrome(String value) {
		
		for(int i =0; i < ((palindrome.length() / 2)); i++) {
			if(palindrome.charAt(i) == palindrome.charAt((palindrome.length()-1)-i)) {
				if(i == palindrome.length()/2 - 1)		System.out.println(palindrome);
			}
			else	break;
		}
			
	}
	
}
