package Projects;

public class Problem5 {

	public static void main(String args[]) {
		
		boolean evenDiv = false;
		boolean div = true;
		int num = 1;
		
		while(evenDiv == false) {
			for(int i =1; i < 21; i++) {
				if(num % i == 0) {	//evenDiv  = true;
					if((div == true) && (i == 20)) {
						System.out.println(num);
						evenDiv = true;
					}
				}
				else	div = false;
			}
			num++;
			div = true;
		}
	}
		
}
