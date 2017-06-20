package oomdemo;

/**
 * 单例在空闲的时候是否会被回收
 * @author user
 * args  -verbose:gc -Xms20M -Xmx20M
 */
public class SingletonDemo
{
	private byte[] a = new byte[6 * 1024 * 1024];
	private static SingletonDemo singleton = new SingletonDemo();

	private SingletonDemo()
	{
	}

	public static SingletonDemo getInstance()
	{
		return singleton;
	}

	public static void main(String[] args)
	{
		SingletonDemo.getInstance();
		while (true)
			new Obj();
	}
}

class Obj
{
	private byte[] a = new byte[3 * 1024 * 1024];
}