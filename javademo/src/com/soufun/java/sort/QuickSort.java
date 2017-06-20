package com.soufun.java.sort;

import java.util.Arrays;

public class QuickSort {

	/**
	 * 交换排序之快速排序 
	 * 在待排序的n个记录中任取一个记录(通常取第一个记录),把该记录放入适当位置后,数据序列被此记录划分成两部分。
	 * 所有关键字比该记录关键字小的记录放置在前一部分,
	 * 所有比它大的记录放置在后一部分,并把该记录排在这两部分的中间(称为该记录归位),这个过程称作一趟快速排序。
	 * 
	 * 使用的是递归原理，在所有同数量级O(n longn) 的排序方法中，其平均性能最好。就平均时间而言，是目前被认为最好的一种内部排序方法
	 */
	public static void main(String[] args) {
		int[] forSort = new int[] { 12, 3, 45, 13, 0, 1, 7, 5, 89, 35, 23, 46 };
		quickSort(forSort, 0, forSort.length - 1);
		System.out.println(Arrays.toString(forSort));
	}

	/*
	 * 先按照数组为数据原型写出算法，再写出扩展性算法。数组{49,38,65,97,76,13,27}
	 */
	public static void quickSort(int[] n, int left, int right) {
		int pivot;
		if (left < right) {
			// pivot作为枢轴，较之小的元素在左，较之大的元素在右
			pivot = partition(n, left, right);
			// 对左右数组递归调用快速排序，直到顺序完全正确
			quickSort(n, left, pivot - 1);
			quickSort(n, pivot + 1, right);
		}
	}

	public static int partition(int[] n, int left, int right) {
		int pivotkey = n[left];
		// 枢轴选定后永远不变，最终在中间，前小后大
		while (left < right) {
			while (left < right && n[right] >= pivotkey)
				--right;
			// 将比枢轴小的元素移到低端，此时right位相当于空，等待低位比pivotkey大的数补上
			n[left] = n[right];
			while (left < right && n[left] <= pivotkey)
				++left;
			// 将比枢轴大的元素移到高端，此时left位相当于空，等待高位比pivotkey小的数补上
			n[right] = n[left];
		}
		// 当left == right，完成一趟快速排序，此时left位相当于空，等待pivotkey补上
		n[left] = pivotkey;
		return left;
	}
}