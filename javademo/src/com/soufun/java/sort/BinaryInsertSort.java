package com.soufun.java.sort;

import java.util.Arrays;

/**
 * ’€∞Î≤Â»Î≈≈–Ú
 * @author user
 *
 */
public class BinaryInsertSort {

	private static void sort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			int temp = data[i];
			int low = 0;
			int high = i - 1;

			while (low <= high) {
				int mid = (low + high) / 2;
				if (temp < data[mid]) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			}
			for (int j = i - 1; j > high; j--) {
				data[j + 1] = data[j];
			}
			data[high + 1] = temp; // ªÚ’ﬂdata[low] = temp;

		}
	}
	public static void main(String[] args) {
		int[] data = new int[] { 3, 5, 55, 34, 67, 3, 78, 3423, 675, 4567 };
		sort(data);
		System.out.println(Arrays.toString(data));
	}
}
