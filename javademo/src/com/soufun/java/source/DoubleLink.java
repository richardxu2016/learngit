package com.soufun.java.source;

public class DoubleLink<T> {

	private DNode<T> mHead; // 表头

	private int mCount; // 节点个数

	// 节点对应的结构体
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

	// 初始化
	public DoubleLink() {
		this.mHead = new DNode<T>(null, null, null);
		this.mHead.prev = this.mHead.next = this.mHead;
		this.mCount = 0;
	}

	// 返回节点个数
	public int getSize() {
		return mCount;
	}

	public boolean isEmpty() {
		return mCount == 0;
	}

	// 获取第i个节点
	DNode<T> getNode(int index) {
		if (index < 0 || index > mCount)
			throw new IndexOutOfBoundsException();
		// 为节省时间 分正向查找和反向查找
		if (index < mCount / 2) {
			DNode<T> node = mHead.next;
			// 只能从开头开始循环，一直循环到第index个为止
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

	// 获取第i个节点上的值
	public T get(int index) {
		return getNode(index).value;
	}

	// 删除第i个节点
	public void del(int index) {
		DNode<T> node = getNode(index);
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node = null;
		mCount--;
	}

	// 删除第一个节点
	public void delHead() {
		del(0);
	}

	// 删除最后一个节点
	public void delLast() {
		del(mCount - 1);
	}

	// 将节点插入到第index之前
	public void insert(int index, T t) {
		// 如果是将节点插入到头节点之前，使其成为头结点
		if (index == 0) { // 前 后 值
			DNode<T> node = new DNode<T>(mHead.next, mHead, t);// 初始化该节点
			mHead.next.prev = node;
			mHead.next = node;
			mCount++;
			return;
		} else {
			DNode<T> node = getNode(index); // 第index个节点
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

	// 节点加到结尾
	public void appendLast(T t) {
		DNode<T> node = new DNode<T>(mHead.prev, mHead, t);
		mHead.prev.next = node;
		mHead.prev = node;
		mCount++;
	}

	/**
	 * 双向链表
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
		dlink.insert(0, 20); // 20在第一个位置
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
