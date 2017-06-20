package com.soufun.java.sort;

import java.util.Arrays;

public class InsertionSort {

	/**
	 * 插入排序之
	 * 直接插入排序
	 * 1、思想：直接将某一个元素K插入到一个有序的部分中A[1...n],插入过程中,K依次从后到前与A[1...n]中的元素进行比较,如果
	 * A[1...n]中的A[x]>=K,则将K插入到A[x]之后,插入前需要移动元素。 2、算法复杂度：
	 * 最好：正序有序（从小到大）,这样只需要比较n次，不需要移动元素。 最坏：逆序有序（从大到小），这样每个元素比较n次，共有n个元素
	 * 比较适合用于“少量元素的数组”.
	 */

	public static void main(String[] args) {
		int[] forSort = new int[] { 12, 3, 45, 13, 0, 1, 7, 5, 89, 35, 23, 46 };
		Insertion(forSort);
		int[] sort = new int[] { 12, 3, 45, 13, 0, 1, 7, 5, 89, 35, 23, 46 };
		System.out.println();
		insert(sort);
		System.out.println(Arrays.toString(forSort));
	}

	public static void Insertion(int[] sorted) {
		for (int i = 1; i < sorted.length; i++) {
			int temp = sorted[i];
            int j = i - 1;
			while (j >= 0 && temp < sorted[j]) {
				sorted[j + 1] = sorted[j];
				j--;
			}
			sorted[j + 1] = temp;
		}
	}

	public static void insert(int[] a) {
		for (int i = 2; i <= a.length; i++) {
			if (a[i] < a[i - 1]) { // 若第i个元素大于i-1元素，直接插入。小于的话，移动有序表后插入
				int j = i - 1;
				int x = a[i]; // 复制为哨兵，即存储待排序元素
				a[i] = a[i - 1]; // 先后移一个元素
				while (x < a[j]) { // 查找在有序表的插入位置
					a[j + 1] = a[j];
					j--; // 元素后移
				}
				a[j + 1] = x; // 插入到正确位置
			}
		}
	}
}