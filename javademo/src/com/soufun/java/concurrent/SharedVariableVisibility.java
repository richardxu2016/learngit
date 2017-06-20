package com.soufun.java.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class SharedVariableVisibility {

	private boolean ready = false ;
	private int result = 0 ;
	private int number = 1 ;
	
	//1.1->2.1->2.2->1.2   3
	//1.2->2.1->2.2->1.1   0
	//1.1->1.2->2.1->2.2   6  µÈµÈ
	public void write(){
		ready = true ;      // 1.1
		number = 2 ;        // 1.2
	}
	public void read(){
		if (ready) {               // 2.1
			result = number * 3 ;  // 2.2
		}
		System.out.println("result:"+result);
		Lock lock = new ReentrantLock();
		lock.lock();
		try{
		}finally{
		   lock.unlock();
		}
	}
	public static void main(String[] args) {

		SharedVariableVisibility sv = new SharedVariableVisibility();
		sv.new ReadWriteThread(true).start();
		sv.new ReadWriteThread(false).start();
	}

	private class ReadWriteThread extends Thread{
		private boolean flag ;
		public ReadWriteThread(boolean flag){
			this.flag = flag ;
		}
		@Override
		public void run() {
			if (flag) 
				write();
			else
				read();
		}
	}
}
