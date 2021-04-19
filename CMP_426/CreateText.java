package CMP_426;

import java.io.FileWriter;
import java.io.IOException;


public class CreateText {

	public static void main(String[]args)throws IOException {
		
		//System.out.println("hello");
		String attempt = "P1 0 10\n"
					   + "P2 5 8\n"
					   + "P3 2 10\n"
					   + "P4 35 4";
		System.out.println(attempt);
		try {
			
			FileWriter process = new FileWriter("proces.txt");
			
			process.write(attempt);
			
			System.out.println("file made");
			
			process.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
