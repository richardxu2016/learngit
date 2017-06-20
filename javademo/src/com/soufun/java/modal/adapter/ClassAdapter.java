package com.soufun.java.modal.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ��������
 * 
 * @author RICHARD 2015/4/28
 * 
 */
public class ClassAdapter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// ʹ����ͨ������
		Target t = new ConcreteTarget();
		t.request();

		// ������ʹ�����⹦����
		Target adapter = new Adapter();
		adapter.request();
        List<String> a = new ArrayList<String>();
	}

}

class Adaptee {
	public void specificRequest() {
		System.out.println("�����������е����⹦�ܡ�������");
	}
}

interface Target {
	public void request();
}

class ConcreteTarget implements Target {

	@Override
	public void request() {

		System.out.println("��ͨ�������ͨ���ܡ�����");
	}
}

class Adapter extends Adaptee implements Target {

	@Override
	public void request() {

		super.specificRequest();
	}

}