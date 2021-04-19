package Projects;

public class Problem6 {

	public static void main(String args[]) {
		double sumSquared = 0;
		double squaredSum = 0;
		
		
		for(double i = 1; i<101; i++) {
			sumSquared += i;
			squaredSum += Math.pow(i,2.0);
		}
		System.out.println(sumSquared);
		
		sumSquared *= sumSquared;
		System.out.println(sumSquared);
		
		System.out.println(squaredSum);
		
		double diff = sumSquared - squaredSum;
		
		System.out.println(diff);
		
		//sumSquared *= sumSquared;
		/*System.out.println(sumSquared);
		System.out.println(squaredSum);
		double diff = squaredSum - sumSquared;
		System.out.println(diff);*/
	}
}
