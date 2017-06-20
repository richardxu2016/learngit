package com.soufun.java.modal.singleton;

public class LazySingleton {

	private static LazySingleton singleton = null;

	private LazySingleton() {
//		System.out.println("lazy is created");
	}

	public static synchronized LazySingleton getInstance() {
		if (singleton == null)
			return new LazySingleton();
		return singleton;
	}

	public static void doSomething() {
		System.out.println("do something");
	}
}

