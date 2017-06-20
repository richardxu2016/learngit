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
				//�ͷ������൱�ڸ����ӳ���������
				pool.addLast(connection) ;
				// �����ͷ���֮����Ҫ֪ͨ���������߽�������
				pool.notifyAll() ;
			}
		}
	}
	
	/**
	 * ��ȡ����  �൱�������ӳ���ȥ������
	 * @param mills ��ʾ���ٺ����ڳ�ʱ��ȡ����
	 * @return
	 * @throws InterruptedException
	 */
	public Connection fetchConnection(long mills) throws InterruptedException{
		synchronized (pool)
		{
			if (mills<=0)
			{
				// �����ǰ���ӳ�Ϊ��   �ȴ�
				while (pool.isEmpty())
				{
					pool.wait() ;
				}
			}else{
				// ��ʱ��ʱ���
				long future = System.currentTimeMillis()+mills ;
				//��ȡ���ӵ�ʱ�䣬��������ʱ������Ϊ��ʱ
				long remaining = mills ;
				// ���ӳ�Ϊ����û�г�����ʱʱ���������  �ȴ�����
				while (pool.isEmpty() && remaining>0)
				{
					pool.wait(remaining); 
					remaining = future-System.currentTimeMillis() ;
				}
				Connection result = null ;
				if (!pool.isEmpty())
				{
					// 	ȡ�������еĵ�һ������
					result = pool.removeFirst() ;
				}
				return result;
			}
		}
		return null ;
	}
}
