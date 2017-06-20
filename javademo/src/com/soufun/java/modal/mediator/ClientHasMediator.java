package com.soufun.java.modal.mediator;

/**
 * 使用中介者模式
 * 
 * @author RICHARD 2015/4/28
 */
public class ClientHasMediator {

	
	public static void main(String[] args) {

		ColleaA ca = new ColleaA();
		ColleaB cb = new ColleaB();
		
		AbstractMediator am = new Mediator(cb, ca);
		//A影响B
		ca.setNum(1000,am);
		System.out.println("A:"+ca.getNum());
		System.out.println("B:"+cb.getNum());
		System.out.println("=====================");
		cb.setNum(1000,am);
		System.out.println("A:"+ca.getNum());
		System.out.println("B:"+cb.getNum());
	}
}

abstract class AbstractCollea {

	protected int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public abstract void setNum(int num, AbstractMediator am);
}

class ColleaA extends AbstractCollea {

	@Override
	public void setNum(int num, AbstractMediator am) {
		this.num = num;
		am.AaffectB();
	}

}

class ColleaB extends AbstractCollea {

	@Override
	public void setNum(int num, AbstractMediator am) {
		this.num = num;
		am.BaffectA();
	}
}

abstract class AbstractMediator {
	ColleaB b;
	ColleaA a;

	public AbstractMediator(ColleaB b, ColleaA a) {
		this.b = b;
		this.a = a;
	}

	abstract void AaffectB();

	abstract void BaffectA();
}

//中介者
class Mediator extends AbstractMediator {

	public Mediator(ColleaB b, ColleaA a) {
		super(b, a);
	}

	@Override
	void AaffectB() {
		int aNum = a.getNum();
		b.setNum(aNum * 100);
	}

	@Override
	void BaffectA() {
		int bNum = b.getNum();
		a.setNum(bNum / 100);
	}

}