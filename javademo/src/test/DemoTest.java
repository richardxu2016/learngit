package test;


public class DemoTest
{

	private String name = "base";

	public DemoTest()
	{
		callName();
	}

	public void callName()
	{
		System.out.println("i am " + name);
	}

	static class Sub extends DemoTest
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
 * 设计4个线程，其中两个线程每次对j增加1，另外两个线程对j每次减少1。写出程序。 内部类实现线程，对j增减的时候没有考虑顺序问题。
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
			ThreadA t1 = new ThreadA("t1"); // 新建“线程t1”
			t1.start(); // 启动“线程t1”
			t1.join(); // 将“线程t1”加入到“主线程main”中，并且“主线程main()会等待它的完成”
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

			// 延时操作
			for (int i = 0; i < 1000000; i++)
				;

			System.out.printf("%s finish\n", this.getName());
		}
	}
}