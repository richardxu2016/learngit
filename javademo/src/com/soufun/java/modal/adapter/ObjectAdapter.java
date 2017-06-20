package com.soufun.java.modal.adapter;

/**
 * ¶ÔÏóÊÊÅäÆ÷
 * 
 * @author user
 * 
 */
public class ObjectAdapter {

	public static void main(String[] args) {

		Target t = new ConcreteTarget();
		t.request();
		
		Target adapter = new Adapterr(new Adaptee());
		adapter.request();
	}

}

class Adapterr implements Target {

	private Adaptee adaptee;

	public Adapterr(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public void request() {
		this.adaptee.specificRequest();
	}

}