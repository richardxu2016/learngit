package com.soufun.java.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<E> timeList = new ArrayList<E>();
		E e1 = new E("20140304125200");
		E e2 = new E("20140404125200");
		E e3 = new E("20140604125200");
		E e4 = new E("20150204125200");
		E e5 = new E("20150404125200");
		E e6 = new E("20130404125200");
		E e7 = new E("20120404125200");
		timeList.add(e1);
		timeList.add(e2);
		timeList.add(e3);
		timeList.add(e4);
		timeList.add(e5);
		timeList.add(e6);
		timeList.add(e7);
//		for (int i = timeList.size()-1; i >=0; i--) {
//			System.out.println(timeList.get(i).getTime());
//		}
		//quickSort(timeList, 0, timeList.size() - 1);
		for (E e : timeList) {
			//System.out.println(e.getTime());
		}
		E[] e = shellSort(timeList);
		for (int i = e.length-1; i >=0; i--) {
			System.out.println(e[i].getTime());
		}
	}

	private static E[] shellSort(List<E> m) {
		E[] a = (E[]) m.toArray(new E[m.size()]);
		int d = a.length/2;
		while (d>0) {
			for (int i = d; i < a.length; i++) {
				int j = i-d ;
				while (j>=0&&a[j].getTime().compareTo(a[j+d].getTime())>0) {
					E temp = a[j] ;
					a[j] = a[j+d] ;
					a[j+d] = temp ;
					j-=d;
				}
			}
			d /=2;
		}
		return a;
	}

	public static void quickSort(List<E> n, int left, int right) {
		int pivot;
		if (left < right) {
			pivot = partition(n, left, right);
			quickSort(n, left, pivot - 1);
			quickSort(n, pivot + 1, right);
		}
	}

	public static int partition(List<E> m, int left, int right) {
		E[] n = (E[]) m.toArray(new E[m.size()]);
		E pivotkey = n[left];
		while (left < right) {
			while (left < right
					&& n[right].getTime().compareTo(pivotkey.getTime()) >= 0)
				--right;
			n[left] = n[right];
			while (left < right
					&& n[left].getTime().compareTo(pivotkey.getTime()) <= 0)
				++left;
			n[right] = n[left];
		}
		n[left] = pivotkey;
		return left;
	}
}

class E {

	private String time;

	public E(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}