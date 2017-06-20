package com.soufun.java.sort;

import java.util.Arrays;

public class BubbleSort {

	/**
	 * Ωªªª≈≈–Ú÷Æ
	 * √∞≈›≈≈–Ú:
	 */
	public static void main(String[] args) {
		int[] forSort = new int[]{ 0, 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5,
				4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51 };
		bubble(forSort);
		System.out.println(Arrays.toString(forSort));
	}

	public static void bubble(int[] R) {
		int temp;
		for (int i = 0; i < R.length; i++) {
			for (int j = R.length - 1; j > i; j--) {
				if (R[j] < R[j - 1]) {
					temp = R[j];
					R[j] = R[j - 1];
					R[j - 1] = temp;
				}
			}
		}
	}
}
