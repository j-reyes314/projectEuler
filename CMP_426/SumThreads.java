package CMP_426;

public class SumThreads {

	int[]nums = {1,2,3,50,10,20,5};
	int total = 0;
	
	class SumThreadInnerClass implements Runnable{
		
			@Override
			public void run() {
				if(Thread.currentThread().getName().equals("thread1")) {
					for(int i = 0; i<nums.length/2; i++)	total += nums[i];
				}else if(Thread.currentThread().getName().equals("thread2")) {
					for(int j =nums.length/2; j<nums.length; j++)	total += nums[j];
				}
				System.out.println("Thread is running");
			}
	}
	
	public void main(String []args) {
		
		SumThreads sum = new SumThreads();
		
		Thread t1 = new Thread(sum.new SumThreadInnerClass(), "thread1");
		Thread t2 = new Thread(sum.new SumThreadInnerClass(), "thread2");
		//can write arrays of threads using for loops
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(sum.total);
	}
}
