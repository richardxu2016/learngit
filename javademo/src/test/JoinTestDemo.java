package test;


public class JoinTestDemo
{

	private String name = "base";

	public JoinTestDemo()
	{
		callName();
	}

	public void callName()
	{
		System.out.println("i am " + name);
	}

	static class Sub extends JoinTest
	{
		private String name = "sub";

		public void callName()
		{
			System.out.println("i am " + name);
		}
	}

	public static void main(String[] args)
	{
		new Sub();
	}

}

/*
 * ���4���̣߳����������߳�ÿ�ζ�j����1�����������̶߳�jÿ�μ���1��д������ �ڲ���ʵ���̣߳���j������ʱ��û�п���˳�����⡣
 */
class TestThread3
{
	private int j = 10;

	public static void main(String[] args)
	{
		TestThread3 t = new TestThread3();
		Inc inc = t.new Inc();
		Dec dec = t.new Dec();
		for (int i = 0; i < 2; i++)
		{
			Thread ts = new Thread(inc);
			ts.start();
			ts = new Thread(dec);
			ts.start();
		}
	}

	private synchronized void inc()
	{
		j++;
		System.out.println(Thread.currentThread().getName() + "-inc:" + j);
	}

	private synchronized void dec()
	{
		j--;
		System.out.println(Thread.currentThread().getName() + "-dec:" + j);
	}

	class Inc implements Runnable
	{
		public void run()
		{
			for (int i = 0; i < 10; i++)
			{
				inc();
			}
		}
	}

	class Dec implements Runnable
	{
		public void run()
		{
			for (int i = 0; i < 10; i++)
			{
				dec();
			}
		}
	}
}

class JoinTest
{
	public static void main(String[] args)
	{
		try
		{
			ThreadA t1 = new ThreadA("t1"); // �½����߳�t1��
			t1.start(); // �������߳�t1��
			t1.join(); // �����߳�t1�����뵽�����߳�main���У����ҡ����߳�main()��ȴ�������ɡ�
			System.out.printf("%s finish\n", Thread.currentThread().getName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	static class ThreadA extends Thread
	{
		public ThreadA(String name)
		{
			super(name);
		}

		public void run()
		{
			System.out.printf("%s start\n", this.getName());

			// ��ʱ����
			for (int i = 0; i < 1000000; i++)
				;

			System.out.printf("%s finish\n", this.getName());
		}
	}
}