package com.thread.demo;

import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionPool
{
	private LinkedList<Connection> pool = new LinkedList<Connection>();
	
	public ConnectionPool(int initialSize){
		if (initialSize>0)
		{
			for (int i = 0; i < initialSize; i++)
			{
				pool.addLast(ConnectionDriver.createConnection()) ;
			}
		}
	}
	
	public void releaseConnection(Connection connection){
		if (connection!=null)
		{
			synchronized (pool)
			{
				//释放连接相当于给连接池增加连接
				pool.addLast(connection) ;
				// 连接释放完之后需要通知其他消费者进行消费
				pool.notifyAll() ;
			}
		}
	}
	
	/**
	 * 获取连接  相当于在连接池中去除连接
	 * @param mills 表示多少毫秒内超时获取连接
	 * @return
	 * @throws InterruptedException
	 */
	public Connection fetchConnection(long mills) throws InterruptedException{
		synchronized (pool)
		{
			if (mills<=0)
			{
				// 如果当前连接池为空   等待
				while (pool.isEmpty())
				{
					pool.wait() ;
				}
			}else{
				// 超时的时间点
				long future = System.currentTimeMillis()+mills ;
				//获取连接的时间，当超过该时间则被视为超时
				long remaining = mills ;
				// 连接池为空且没有超过超时时间点的情况下  等待连接
				while (pool.isEmpty() && remaining>0)
				{
					pool.wait(remaining); 
					remaining = future-System.currentTimeMillis() ;
				}
				Connection result = null ;
				if (!pool.isEmpty())
				{
					// 	取出链表中的第一个返回
					result = pool.removeFirst() ;
				}
				return result;
			}
		}
		return null ;
	}
}
