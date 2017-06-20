package com.soufun.java.modal.singleton;

public class TestSington {
	
	public static void main(String[] args) {

		LazySingleton.doSomething();
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new testLazySingle());
			t.start();
		}
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new testSingle());
			t.start();
		}
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new testStaticSingle());
			t.start();
		}
	}
}

class testLazySingle implements Runnable{

	@Override
	public void run() {
		long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) 
			LazySingleton.getInstance();
		System.out.println("lazy sigleton spend:"+Thread.currentThread()+":"+(System.currentTimeMillis()-beginTime));
	}
}

class testSingle implements Runnable{

	@Override
	public void run() {
		long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) 
			Singleton.getInstance();
		System.out.println("sigleton spend:"+Thread.currentThread()+":"+(System.currentTimeMillis()-beginTime));
	}
}

class testStaticSingle implements Runnable{

	@Override
	public void run() {
		long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) 
			StaticSingleton.getInstance();
		System.out.println("static sigleton spend:"+Thread.currentThread()+":"+(System.currentTimeMillis()-beginTime));
	}
}