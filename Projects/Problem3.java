package Projects;

public class Problem3 {

	public static void main(String args[]) {
		
		/*
		The prime factors of 13195 are 5, 7, 13 and 29.

		What is the largest prime factor of the number 600851475143 ?
		*/
		
		double num = 600851475143.0;
		//int prime = 0;
		//int div = 0;
		boolean prime = true;
		
		for (int i = 2; i <= num / 2; ++i)
			if(num%i == 0){
				//System.out.println(i + "i");
				
				for(int j = 2; j < i; j++){
					if(i%j==0) 	prime = false;	
					//System.out.println(j + " j");
					
				}
				if(prime== true)	System.out.println(i);
		
		}
		prime = true;
		//System.out.println(prime);
		
	}
}

