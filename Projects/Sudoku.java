package Projects;


public class Sudoku {	
	//Joshua Reyes
	//initiate a valid sudoku puzzle for debugging and testing
	
	static int[][] puzzleTrue = new int[][] {	{6,2,4,5,3,9,1,8,7}, 
												{5,1,9,7,2,8,6,3,4},
												{8,3,7,6,1,4,2,9,5},
												{1,4,3,8,6,5,7,2,9},
												{9,5,8,2,4,7,3,6,1},
												{7,6,2,3,9,1,4,5,8},
												{3,7,1,9,5,6,8,4,2},
												{4,9,6,1,8,2,5,7,3},
												{2,8,5,4,7,3,9,1,6}
	};
	
	static int[][] puzzleFalse = new int[][] {	{6,2,4,5,3,9,1,8,7}, 
												{5,1,9,7,2,8,6,3,4},
												{8,3,7,6,1,4,2,9,5},
												{4,4,3,8,6,5,7,2,9},
												{9,5,8,2,4,7,3,6,1},
												{7,6,2,3,9,1,4,5,8},
												{3,7,1,9,5,6,8,4,2},
												{4,9,6,1,8,2,5,7,3},
												{2,8,5,4,7,3,9,1,6}
	};

	// init term for thread usage and completeSet to determine if the puzzle is valid
	
	static int term;
	static boolean completeSet = true;
	
	int rows;
	int cols;
	int [][] puzzle;
	
	static Thread[] valid = new Thread[27];
	
	public Sudoku(int rows,int cols,int[][]puzzle) {
		this.rows = rows;
		this.cols = cols;
		this.puzzle = puzzle;
	}
	
	public static void main(String [] args) {
		
		//create threads
		
		
		
		for(int i =0;i<9;i++) {
			for(int j = 0; j<9; j++) {
			valid[i] = new Thread(new Sudoku(i,0,puzzleTrue).new rows());
			valid[i+9] = new Thread(new Sudoku(0,i,puzzleTrue).new columns());
			if(i%3== 0 || j%3 ==0)	valid[i+18] = new Thread(new Sudoku(i,j,puzzleTrue).new square());
			}
		}
		
		//start and join threads
		
		for(int i =0;i<18;i++) {
			valid[i].start();
		}
		
		try {
			for(int i = 0; i<27; i++) {
				valid[i].join();
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		if(completeSet == true)	System.out.println("This is a valid Sudoku puzzle.");
		else					System.out.println("This is not a valid Sudoku puzzle.");
	}	
	
//threads that check if the rows are valid
	
		private class rows implements Runnable{
			
			@Override
			public void run() {
				
				boolean[] allNine = new boolean [9];
		
				int row = rows;
		
				for(int i = 0; i<9;i++){
					
					term = puzzle[row][i];
					if(allNine[term-1]) {
						
						completeSet = false;
						return;
					}else	allNine[term -1] = true;	
				}
				
			}
		}
		
//thread that checks if the columns are valid
		
		private class columns implements Runnable{
			
			@Override
			public void run() {
				
				boolean[] allNine = new boolean [9];
				int col = cols;
				
				for(int i = 0; i<9;i++){
					
					term = puzzle[i][col];

					if(allNine[term-1]) {
						
			
						completeSet = false;
						return;
					}
					else	allNine[term -1] = true;				
				}
			}
		}
		
//thread that checks if the squares are valid
		
		private class square implements Runnable{
			
			@Override
			public void run() {
							
				int row = rows;
				int col = cols;
				
				boolean[] allNine = new boolean[9];
				
				for(int i = row; i<row + 3; i++){
					for(int j = col; j<col + 3; j++) {
						
					term = puzzle[i][j];

					if(allNine[term-1]) {
						
			
						completeSet = false;
						return;
					}
					else	allNine[term -1] = true;
					}
				}
			}
		}				
}
