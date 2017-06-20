package com.soufun.java.effectivejava;

/**
 * 使用工厂类代替构造器对类进行初始化
 * @author user
 *
 */
public class ConstructTest {

	private final String name;
	private final String clazz;

	private final int age;
	private final String email;
	private final String phone;
	private final String qq;

	public static class Builder {

		//required
		private final String name;
		private final String clazz;

		//optional
		private int age = 0;
		private String email = null;
		private String phone = null;
		private String qq = null;

		public Builder(String name, String clazz) {
			this.name = name;
			this.clazz = clazz;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder qq(String qq) {
			this.qq = qq;
			return this;
		}

		public Builder age(int age) {
			this.age = age;
			return this;
		}

		public ConstructTest builder() {
			return new ConstructTest(this);
		}
	}

	private ConstructTest(Builder builder) {
		name = builder.name;
		clazz = builder.clazz;
		age = builder.age;
		email = builder.email;
		phone = builder.phone;
		qq = builder.qq;
	}

	public static void main(String[] args) {

		ConstructTest ct = new ConstructTest.Builder("tom", "260").age(354)
				.builder();
		System.out.println(ct.name);
	}

}
