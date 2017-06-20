package com.soufun.java.modal.singleton;

public class Singleton {

	private static Singleton sigleton = new Singleton();
	
	private Singleton(){
//		System.out.println("Singleton is created");
	}
	
	public static Singleton getInstance(){
//		System.out.println("get the instance");
		return sigleton;
	}
	
	public static void doSomething(){
		System.out.println("do something...");
	}
	
	public static void main(String[] args) {

		Singleton.doSomething();
	}

}
