package com.soufun.java.source;

import java.lang.reflect.Array;

public class GeneralArrayStack<T> {

	private static final int DEFAULT_SIZE = 12;
	private T[] mArray;
	private int count;

	@SuppressWarnings("unchecked")
	public GeneralArrayStack(Class<T> type, int size) {
		// ����ֱ���� mArray = new T[DEFAULT_SIZE]
		mArray = (T[]) Array.newInstance(type, size);
		count = 0;
	}

	public GeneralArrayStack(Class<T> type) {
		this(type, DEFAULT_SIZE);
	}

	// ��val��ӵ�ջ��
	public void push(T val) {
		mArray[count++] = val;
	}

	// ����ջ��Ԫ��
	public T peek() {
		return mArray[count - 1];
	}

	// ���ء�ջ��Ԫ��ֵ������ɾ����ջ��Ԫ�ء�
	public T pop() {
		T r = mArray[count - 1];
		count--;
		return r;
	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	// ��ӡջ
	public void PrintArrayStack() {
		if (isEmpty()) {
			System.out.println(" statck is empty");
		} else {
			int i = size() - 1;
			while (i > 0) {
				System.out.print(mArray[i] + " ");
				i--;
			}
		}
	}

	public static void main(String[] args) {
		    String tmp;
	        GeneralArrayStack<String> astack = new GeneralArrayStack<String>(String.class);

	        // ��10, 20, 30 ��������ջ��
	        astack.push("10");
	        astack.push("20");
	        astack.push("30");

	        // ����ջ��Ԫ�ء���ֵ��tmp����ɾ����ջ��Ԫ�ء�
	        tmp = astack.pop();
	        System.out.println("tmp="+tmp);

	        // ֻ����ջ������ֵ��tmp����ɾ����Ԫ��.
	        tmp = astack.peek();
	        System.out.println("tmp="+tmp);

	        astack.push("40");
	        astack.PrintArrayStack();    // ��ӡջ
	}
}
