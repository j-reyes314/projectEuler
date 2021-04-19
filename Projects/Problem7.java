package Projects;

public class Problem7 {

	public static void main(String args[]) {
		
		/*By listing the first six prime numbers: 
		 * 
		 * 2, 3, 5, 7, 11, and 13, 
		 * we can see that the 6th prime is 13.

		   What is the 10 001st prime number?*/
		
		boolean tenThousand = false;
		
		int num = 1;
		
		int primes =1;
		
		boolean prime = true;
		
		
		while(tenThousand == false) {
			
			for(int i =2; i < num; i++) {
				if(num%i == 0)		prime = false;
				else {
					if(i== num-1 && prime == true)	primes++;
				}
			}
			
			if(primes == 10001) {
				tenThousand = true;
				System.out.println(num);
			}
			
			//System.out.println(num);
			//System.out.println(primes + "prime");
			
			num++;
			prime = true;
		
	}
}
}
