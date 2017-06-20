package oomdemo;

public class JavaVMStackSOF
{
	private int stackLength = 1;

	// ·´¸´µ÷ÓÃ
	public void stackLeak()
	{
		stackLength++;
		stackLeak();
	}

	public static void main(String[] args) throws Throwable
	{
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try
		{
			oom.stackLeak();
		} catch (Throwable e)
		{
			System.out.println("stack length:" + oom.stackLength);
			throw e;
		}
	}
}
