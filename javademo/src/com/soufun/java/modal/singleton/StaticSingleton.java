package com.soufun.java.modal.singleton;

public class StaticSingleton {

	private StaticSingleton() {
		System.out.println("staticSingleton is created");
	}

	private static class SingletonHoder {
		private static StaticSingleton singleton = new StaticSingleton();
	}

	public static StaticSingleton getInstance() {
		return SingletonHoder.singleton;
	}
}
