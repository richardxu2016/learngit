package test;

public class Interview
{
	public static void main(String[] args)
	{

		NoVisiabilityTest d = new D();

		//d.deal();

	}
}

class A
{

	static
	{
		System.out.println("11");
	}

	{
		System.out.println("22");
	}

	public A()
	{
		System.out.println("33");
	}

	public void deal()
	{
		System.out.println("44");
	}

}

class D extends NoVisiabilityTest
{

	static
	{
		System.out.println("55");
	}
	{
		System.out.println("66");
	}

	public D()
	{
		System.out.println("77");
	}

	public void deal()
	{
		System.out.println("88");
	}
}

