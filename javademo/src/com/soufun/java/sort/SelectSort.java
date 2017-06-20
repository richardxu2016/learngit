package com.soufun.java.sort;

import java.util.Arrays;

public class SelectSort {

	/**
	 * 选择排序之 直接选择排序
	 *  思想：选择最小的元素与未排序部分的首部交换，使得序列的前面为有序。
	 */
	public static void main(String[] args) {

		int[] forSort = new int[] { 12, 3, 45, 13, 0, 1, 7, 5, 89, 35, 23, 46 };
		selectSort(forSort);
		System.out.println(Arrays.toString(forSort));
	}

	private static void selectSort(int[] forSort) {

		for (int i = 0; i < forSort.length - 1; i++) {
			int k = i;
			for (int j = i + 1; j < forSort.length; j++) {
				if (forSort[j] < forSort[k]) {
					k = j;
				}
			}
			if (k != i) {
				int temp = forSort[i];
				forSort[i] = forSort[k];
				forSort[k] = temp;
			}
		}
	}
}