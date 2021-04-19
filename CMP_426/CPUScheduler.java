package CMP_426;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class CPUScheduler {
	
	static int process =0;	
	static int queue_size = 0;	
	static ready_queue [] queue;
	
	public static void main(String[] args) throws IOException{
		
	    try{
	    	
	        File myObj = new File("proces.txt");
	        Scanner myReader = new Scanner(myObj);
	        
	        while (myReader.hasNextLine()) {
	        	queue_size++;
	        	myReader.nextLine();
	        }
	        
	        myReader.reset();
	        myReader = new Scanner(myObj);
	        queue = new ready_queue[queue_size];
	        
	        while (myReader.hasNextLine()) {
	        	
	        	queue[process] = new ready_queue(myReader.nextLine());
	        	process++;
	        	
	        }
        
	        myReader.close();
	        
	    }catch(FileNotFoundException e){
	    	
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	        
	    }	    
	    
	    System.out.println("CPU Scheduling Simulator\n");
	     
	    System.out.println("First Come First Served Scheduling\n");
	    
	    CPUscheduler schedule = new CPUscheduler(queue);
	    
	    schedule.reorder();
	    schedule.print();
	    
	    System.out.println("\nRound Robin\n");
	    
	    schedule.printRR(5);
	}
		

	private static class ready_queue{
		
		String prc;
		String[] data;
		int CPUBurst;
		int pid;
		int arrival_time;
		boolean complete = false;
		
		public ready_queue(String prc) {

			this.prc = prc.replace("P", "");
			//System.out.println(this.prc);
			man();
		}
		
		void man() {
				data = prc.split(" ");	
				CPUBurst = Integer.valueOf(data[2]);
				arrival_time = Integer.valueOf(data[1]);
				pid = Integer.valueOf(data[0]);
		}
		
		
		boolean complete() {
			complete = true;
			return complete;
		}
	}
	
	private static class CPUscheduler{
		
		ready_queue[] stack;
		ready_queue holder;
		
		public CPUscheduler(ready_queue[] stack) {
			this.stack = stack;
		}
		
		
		void reorder() {
			
			for(int j =0; j<stack.length; j++) {
				holder = stack[j];
				for(int i = j+1; i<stack.length; i++) {
					if(stack[i].arrival_time< holder.arrival_time) {
						holder = stack[i];
						stack[i] = stack[j];
					}
				}
				stack[j] = holder;
				
			}
		}
		
		void print() {
			
			int CPUtime= 0;
			int[] turnaround = new int[stack.length];
			int[] waitTime= new int[stack.length];
			int[] responseTime= new int[stack.length];
			int[] averages = new int[3];
			Arrays.fill(averages, 0);
			
			for(int i =0; i<stack.length; i++) {
			
				if(stack[i].arrival_time <= CPUtime) {
					responseTime[i] = CPUtime-stack[i].arrival_time;
					waitTime[i] = CPUtime -stack[i].arrival_time;
					
					System.out.println("Running Process: "+ stack[i].pid);
					System.out.println("Runs on CPU: "+ CPUtime+" - "+(CPUtime + stack[i].CPUBurst)+"\n");
					
					CPUtime += stack[i].CPUBurst;
					
					
					turnaround[i] = CPUtime-stack[i].arrival_time;
					
					averages[0] += responseTime[i];
					averages[1] += waitTime[i];
					averages[2] += turnaround[i];
				}else {

					System.out.println("Idling .....\n");
					CPUtime = stack[i].arrival_time;
					responseTime[i] = CPUtime-stack[i].arrival_time;
					waitTime[i] = CPUtime -stack[i].arrival_time;
					System.out.println("Running Process: "+ stack[i].pid);
					System.out.println("Runs on CPU: "+ CPUtime+" - "+(CPUtime + stack[i].CPUBurst)+"\n");
					
					CPUtime +=stack[i].CPUBurst;
					
					turnaround[i] = CPUtime-stack[i].arrival_time;
					
					averages[0] += responseTime[i];
					averages[1] += waitTime[i];
					averages[2] += turnaround[i];
				}
			}
			
			System.out.println("Turnaround Times: \n");
		
			for(int i = 0; i<stack.length; i++)	System.out.println("P"+stack[i].pid+" = "+turnaround[i]);
		
			System.out.println("\nWait Times: \n");
			
			for(int i = 0; i<stack.length; i++)	System.out.println("P"+stack[i].pid+" = "+waitTime[i]);
			
			System.out.println("\nResponse Times: \n");
			
			for(int i = 0; i<stack.length; i++)	System.out.println("P"+stack[i].pid+" = "+responseTime[i]);
		
			System.out.println("\nAverage Response Time: "+ (averages[0]/stack.length));
			System.out.println("Average Wait Time: "+ (averages[1]/stack.length));
			System.out.println("Average Turnaround: "+ (averages[2]/stack.length));
		}
		
		void printRR(int timeQuantum) {

			int CPUtime= 0;
			boolean complete = true;
			int completed = 0;
			
			while(complete) {
				
				for(int i =0; i<stack.length; i++) {
					
					if(stack[i].arrival_time <= CPUtime && stack[i].complete == false) {
						
						
						
						System.out.println("Running Process: "+ stack[i].pid);
						
						if(stack[i].CPUBurst < timeQuantum) {
							System.out.println("Runs on CPU: "+ CPUtime+" - "+(CPUtime + stack[i].CPUBurst)+"\n");
							CPUtime+=stack[i].CPUBurst;
							stack[i].complete();
						}
						else {							
							System.out.println("Runs on CPU: " + CPUtime+" - "+ (CPUtime + timeQuantum)+'\n');
							CPUtime += timeQuantum;
							stack[i].CPUBurst = stack[i].CPUBurst - timeQuantum;
							if(stack[i].CPUBurst == 0)	stack[i].complete();
						}
								
						
						if(stack[i].complete)	completed++;
						if(completed ==stack.length)	complete = false;
						
					}
					
					if(CPUtime <stack[i].arrival_time&& completed == stack.length-1) {
						System.out.println("Idling...");
						CPUtime = stack[i].arrival_time;
					}
				}

			}
		}
	}
	
}
