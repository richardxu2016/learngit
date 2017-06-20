package com.soufun.java.modal.mediator;

/**
 * 问题：类A和类B，两个类中有两个数字，并且要保证类B中的数字永远是类A中数字的100倍。
 * 
 * 本类中采用不用中介者模式的方法
 * 
 * @author RICHARD 2015/4/28
 */
public class ClientNoMediator {

	public static void main(String[] args) {

		ColleagueA ca = new ColleagueA();
		ColleagueB cb = new ColleagueB();

		// A影响B
		ca.setNum(1000, cb);
		System.out.println("A:" + ca.getNum());
		System.out.println("B:" + cb.getNum());
		System.out.println("==============");
		cb.setNum(1000, ca);
		System.out.println("A:" + ca.getNum());
		System.out.println("B:" + cb.getNum());
	}

}

abstract class AbstractColleague {
	protected int num;

	public void setNum(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public abstract void setNum(int num, AbstractColleague ac);
}

class ColleagueA extends AbstractColleague {

	@Override
	public void setNum(int num, AbstractColleague ac) {
		this.num = num;
		ac.setNum(num * 100);
	}
}

class ColleagueB extends AbstractColleague {

	@Override
	public void setNum(int num, AbstractColleague ac) {
		this.num = num;
		ac.setNum(num / 100);
	}
}