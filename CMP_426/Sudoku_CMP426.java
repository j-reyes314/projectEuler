package CMP_426;

public class Sudoku_CMP426 {

	
	
	
	public static void main() {
		
		Sudoku_CMP426 obj = new Sudoku_CMP426();
		
		
		Thread t1 = new Thread(obj.new rows());
		t1.start();
	}

	
	
	private class rows implements Runnable{
		
		public void run() {
			System.out.println("hello");
		}
	}
	
}
