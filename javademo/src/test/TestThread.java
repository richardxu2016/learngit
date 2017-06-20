package test;

public class TestThread implements Runnable {

	Thread t1;
	Thread t2;
	int count = 100;
	public static int k = 0;

	public TestThread() {
		// t1.setName("thread1");
		// t2.setName("thread2");
	}

	public TestThread(Thread t1, Thread t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	public static void main(String[] args) {
		 float   a  =   123.2334f;   
		 float  b   =  (float)(Math.round(a*100))/100;
		 System.out.println(b);
	}

	@Override
	public void run() {
		synchronized (TestThread.class) {
			int i = 0;
			while (i < count) {
				System.out.println("---->" + Thread.currentThread().getName());
				if (Thread.currentThread().getName().equals("thread1")) {
					k++;
					System.out.println(Thread.currentThread().getName() + ":"
							+ k);
					try {
						TestThread.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					TestThread.class.notify();
				} else {
					k++;
					System.out.println(Thread.currentThread().getName() + ":"
							+ k);
					try {
						TestThread.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					TestThread.class.notify();
				}
				i++;
			}
		}

	}
}

class Test1 implements Runnable {

	int k;

	public Test1(String threadName, int k) {
		Thread.currentThread().setName(threadName);
		this.k = k;
	}

	@Override
	public synchronized void run() {
		for (int i = 0; i < 10; i++) {
			if (Thread.currentThread().getName().equals("thread1")) {
				k++;
				System.out.println(Thread.currentThread().getName() + ":" + k);
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {

			}
		}
	}

}
