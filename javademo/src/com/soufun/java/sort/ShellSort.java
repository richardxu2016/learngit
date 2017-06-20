package com.soufun.java.sort;

import java.util.Arrays;

public class ShellSort {

	/**
	 * 插入排序之
	 * 希尔排序：缩小增量排序，选取一个小于总数的数k，每隔k个数进行直接插入排序，知道k=1，就最终实现了有序。
	 */
	public static void main(String[] args) {

		int[] forSort = new int[] { 12, 3, 45, 13, 0, 1, 7, 5, 89, 35, 23, 46 };
		shellSort(forSort);
		System.out.println(Arrays.toString(forSort));
	}

	public static void shellSort(int[] a){
		int d = a.length/2;
		while (d>0) {
			for (int i = d; i < a.length; i++) {
				int j = i-d ;
				while (j>=0&&a[j]>a[j+d]) {
					int temp = a[j] ;
					a[j] = a[j+d] ;
					a[j+d] = temp ;
					j-=d;
				}
			}
			d /=2;
		}
	}
}
