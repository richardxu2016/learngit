package com.soufun.java.source;

public class DoubleLink<T> {

	private DNode<T> mHead; // ��ͷ

	private int mCount; // �ڵ����

	// �ڵ��Ӧ�Ľṹ��
	private class DNode<T> {
		public DNode prev;
		public DNode next;
		public T value;

		public DNode(DNode prev, DNode next, T value) {
			this.prev = prev;
			this.next = next;
			this.value = value;
		}
	}

	// ��ʼ��
	public DoubleLink() {
		this.mHead = new DNode<T>(null, null, null);
		this.mHead.prev = this.mHead.next = this.mHead;
		this.mCount = 0;
	}

	// ���ؽڵ����
	public int getSize() {
		return mCount;
	}

	public boolean isEmpty() {
		return mCount == 0;
	}

	// ��ȡ��i���ڵ�
	DNode<T> getNode(int index) {
		if (index < 0 || index > mCount)
			throw new IndexOutOfBoundsException();
		// Ϊ��ʡʱ�� ��������Һͷ������
		if (index < mCount / 2) {
			DNode<T> node = mHead.next;
			// ֻ�ܴӿ�ͷ��ʼѭ����һֱѭ������index��Ϊֹ
			for (int i = 1; i <= index; i++)
				node = node.next;
			return node;
		} else {
			DNode<T> node = mHead.prev;
			int rindex = mCount - index - 1;
			for (int i = 1; i <= rindex; i++)
				node = node.prev;
			return node;
		}
	}

	// ��ȡ��i���ڵ��ϵ�ֵ
	public T get(int index) {
		return getNode(index).value;
	}

	// ɾ����i���ڵ�
	public void del(int index) {
		DNode<T> node = getNode(index);
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node = null;
		mCount--;
	}

	// ɾ����һ���ڵ�
	public void delHead() {
		del(0);
	}

	// ɾ�����һ���ڵ�
	public void delLast() {
		del(mCount - 1);
	}

	// ���ڵ���뵽��index֮ǰ
	public void insert(int index, T t) {
		// ����ǽ��ڵ���뵽ͷ�ڵ�֮ǰ��ʹ���Ϊͷ���
		if (index == 0) { // ǰ �� ֵ
			DNode<T> node = new DNode<T>(mHead.next, mHead, t);// ��ʼ���ýڵ�
			mHead.next.prev = node;
			mHead.next = node;
			mCount++;
			return;
		} else {
			DNode<T> node = getNode(index); // ��index���ڵ�
			DNode<T> tNode = new DNode<T>(node.prev, node, t);
			node.prev.next = tNode;
			node.next = tNode;
			mCount++;
			return;
		}
	}

	public void insertFirst(T t) {
		insert(0, t);
	}

	// �ڵ�ӵ���β
	public void appendLast(T t) {
		DNode<T> node = new DNode<T>(mHead.prev, mHead, t);
		mHead.prev.next = node;
		mHead.prev = node;
		mCount++;
	}

	/**
	 * ˫������
	 */
	public static void main(String[] args) {
		testIneger();
		testString();
		testObject();
	}

	private static void testIneger() {
		int[] iarr = { 10, 20, 30, 40 };
		System.out.println("====================");
		DoubleLink<Integer> dlink = new DoubleLink<Integer>();
		dlink.insert(0, 20); // 20�ڵ�һ��λ��
		dlink.appendLast(10);
		dlink.insertFirst(30);

		System.out.println("isEmpty====>" + dlink.isEmpty());
		System.out.println("size=====>" + dlink.getSize());
		for (int i = 0; i < dlink.getSize(); i++) {
			System.out.println("dlink(" + i + ")=" + dlink.get(i));
		}
	}

	private static void testString() {
		String[] iarr = { "ten", "twenty", "sex", "forty" };
		System.out.println("====================");
		DoubleLink<String> dlink = new DoubleLink<String>();
		dlink.insert(0, iarr[0]);
		dlink.appendLast(iarr[1]);
		dlink.insertFirst(iarr[2]);

		System.out.println("isEmpty====>" + dlink.isEmpty());
		System.out.println("size=====>" + dlink.getSize());
		for (int i = 0; i < dlink.getSize(); i++) {
			System.out.println("dlink(" + i + ")=" + dlink.get(i));
		}
	}

	private static void testObject() {
		System.out.println("====================");
		DoubleLink<Student> dlink = new DoubleLink<Student>();
		dlink.insert(0, s[0]);
		dlink.appendLast(s[1]);
		dlink.insertFirst(s[2]);

		System.out.println("isEmpty====>" + dlink.isEmpty());
		System.out.println("size=====>" + dlink.getSize());
		for (int i = 0; i < dlink.getSize(); i++) {
			System.out.println("dlink(" + i + ")=" + dlink.get(i));
		}
	}

	private static Student[] s = new Student[]{
		new Student(10, "s1"),
		new Student(20, "s2"),
		new Student(30, "s3"),
		new Student(40, "s4"),
		new Student(50, "s5")
	};
	
	private static class Student {
		private int id;
		private String name;

		
		public Student(int id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public String toString() {
			return "Student [id=" + id + ", name=" + name + "]";
		}

	}
}
