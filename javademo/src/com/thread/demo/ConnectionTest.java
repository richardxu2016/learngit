package com.thread.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionTest
{

	static ConnectionPool pool = new ConnectionPool(10) ;
	// 保证所有的ConnectionRunner能够同时开始
	static CountDownLatch start = new CountDownLatch(1) ;
	// main线程将会等待所有ConnectionRunner结束后才能继续执行
	static CountDownLatch end ;
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException
	{
		int threadCount = 50 ;
		end = new CountDownLatch(threadCount);
		int count = 20 ;
		AtomicInteger got = new AtomicInteger() ;
		AtomicInteger notGot = new AtomicInteger() ;
		
		for (int i = 0; i < threadCount; i++)
			new Thread(new ConnetionRunner(count,got,notGot),"ConnectionRunnerThread").start();

		start.countDown() ;
		end.await() ;
		
		System.out.println("total invoke:"+(threadCount*count));
		System.out.println("got connection:"+got);
		System.out.println("notGot:"+notGot);
	}

	static  class ConnetionRunner implements Runnable{
		int count ;
		AtomicInteger got ;
		AtomicInteger notGot ;
		
		public ConnetionRunner(int count, AtomicInteger got,
				AtomicInteger notGot)
		{
			super();
			this.count = count;
			this.got = got;
			this.notGot = notGot;
		}

		@Override
		public void run()
		{
			try
			{
				start.await();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			while (count>0)
			{
				try
				{
					Connection connection = pool.fetchConnection(1000) ;
					if (connection!=null)
					{
						try
						{
							connection.createStatement() ;
							connection.commit() ;
						} catch (SQLException e)
						{
							e.printStackTrace();
						}finally{
							pool.releaseConnection(connection) ;
							got.incrementAndGet() ;
						}
					}else{
						notGot.getAndIncrement() ;
					}
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				} finally{
					count-- ;
				}
			}
			end.countDown() ;
		}
	}
}