package com.soufun.java.modal.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 类适配器
 * 
 * @author RICHARD 2015/4/28
 * 
 */
public class ClassAdapter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 使用普通功能类
		Target t = new ConcreteTarget();
		t.request();

		// 适配器使用特殊功能类
		Target adapter = new Adapter();
		adapter.request();
        List<String> a = new ArrayList<String>();
	}

}

class Adaptee {
	public void specificRequest() {
		System.out.println("被适配器具有的特殊功能。。。。");
	}
}

interface Target {
	public void request();
}

class ConcreteTarget implements Target {

	@Override
	public void request() {

		System.out.println("普通类具有普通功能。。。");
	}
}

class Adapter extends Adaptee implements Target {

	@Override
	public void request() {

		super.specificRequest();
	}

}