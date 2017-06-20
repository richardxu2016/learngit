package test;

public class NoVisiabilityTest
{
	private static class  ReadThread extends Thread{
		private volatile boolean ready ;
//		private boolean ready ;
		private int number ;
		
		@Override
		public void run()
		{
			super.run();
			while (!ready)
			{
				number++ ;
			}
			System.out.println("run:"+ready);
		}
	
		public void readyon()
		{
			this.ready = true ;
		}		
		
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		ReadThread readThread = new ReadThread() ;
		readThread.start() ;
		Thread.sleep(1000) ;
		readThread.readyon() ;
		System.out.println("main:"+readThread.ready);
	}
}
