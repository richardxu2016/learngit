package com.soufun.java.modal.mediator;

/**
 * ���⣺��A����B�������������������֣�����Ҫ��֤��B�е�������Զ����A�����ֵ�100����
 * 
 * �����в��ò����н���ģʽ�ķ���
 * 
 * @author RICHARD 2015/4/28
 */
public class ClientNoMediator {

	public static void main(String[] args) {

		ColleagueA ca = new ColleagueA();
		ColleagueB cb = new ColleagueB();

		// AӰ��B
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