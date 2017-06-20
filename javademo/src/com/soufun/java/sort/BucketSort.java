package com.soufun.java.sort;

/**
 * 桶排序
 * @author user
 * 
 * 
 *
 */
public class BucketSort {
	public void sort(int[] keys, int from, int len, int max) {
		int[] temp = new int[len];
		int[] count = new int[max];

		for (int i = 0; i < len; i++) {
			count[keys[from + i]]++;
		}
		// calculate position info
		for (int i = 1; i < max; i++) {
			count[i] = count[i] + count[i - 1];// 这意味着有多少数目小于或等于i，因此它也是position+
			// 1
		}
		System.arraycopy(keys, from, temp, 0, len);
		for (int k = len - 1; k >= 0; k--)// 从最末到开头保持稳定性
		{
			keys[--count[temp[k]]] = temp[k];// position +1 =count
		}
	}

	public static void main(String[] args) {
		int[] a = { 1, 4, 8, 3, 2, 9, 5, 0, 7, 6, 9, 10, 9, 13, 14, 15, 11, 12,
				17, 16 };
		BucketSort bucketSort = new BucketSort();
		bucketSort.sort(a, 0, a.length, 20);// actually is 18, but 20 will also
		// work
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + ",");
		}
	}
}