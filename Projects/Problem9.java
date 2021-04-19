package Projects;

public class Problem9 {

	public static void main(String args[]) {
		
		double a  =0;
		double b = 0;
		double c = 0;
		
		double aSquared = 0;
		double bSquared = 0;
		double cSquared = 0;
		
		
	/*	A Pythagorean triplet is a set of three natural numbers, a < b < c, 
	 * for which, a2 + b2 = c2

	For example, 32 + 42 = 9 + 16 = 25 = 52.

	There exists exactly one Pythagorean triplet for which a + b + c = 1000.
    Find the product abc.

	
		ORIGINAL ATTEMPT: FAILED
		
		for(int i = 1; i<26; i++) {
			for(int k = 0; k<26; i++) {
				aSquared = Math.pow(i,2);
				bSquared = Math.pow(k,2);
				
				System.out.println("works1");
				cSquared = aSquared + bSquared;
				
				if(cSquared == 25)	break;
				System.out.println("works2");
			}
			
			System.out.println("works3");
			
		}
		
		a = Math.sqrt(aSquared);
		b = Math.sqrt(bSquared);
		c = Math.sqrt(cSquared);*/
		
		int s = 1000;
		
		for(int i =1; i <s/3; i++) {
			for(int j = i; j <s/2; j++) {
				c = s - j - i;
				
				if((j*j) + (i*i) == (c*c)) {
					a =j;
					b = i;
					break;
				}
				if( a == j)	break;
			}
		}
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
		System.out.println("hello world");
	}
	
}
