package com.soufun.java.sort;

import java.util.Arrays;

public class InsertionSort {

	/**
	 * ��������֮
	 * ֱ�Ӳ�������
	 * 1��˼�룺ֱ�ӽ�ĳһ��Ԫ��K���뵽һ������Ĳ�����A[1...n],���������,K���δӺ�ǰ��A[1...n]�е�Ԫ�ؽ��бȽ�,���
	 * A[1...n]�е�A[x]>=K,��K���뵽A[x]֮��,����ǰ��Ҫ�ƶ�Ԫ�ء� 2���㷨���Ӷȣ�
	 * ��ã��������򣨴�С����,����ֻ��Ҫ�Ƚ�n�Σ�����Ҫ�ƶ�Ԫ�ء� ����������򣨴Ӵ�С��������ÿ��Ԫ�رȽ�n�Σ�����n��Ԫ��
	 * �Ƚ��ʺ����ڡ�����Ԫ�ص����顱.
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
			if (a[i] < a[i - 1]) { // ����i��Ԫ�ش���i-1Ԫ�أ�ֱ�Ӳ��롣С�ڵĻ����ƶ����������
				int j = i - 1;
				int x = a[i]; // ����Ϊ�ڱ������洢������Ԫ��
				a[i] = a[i - 1]; // �Ⱥ���һ��Ԫ��
				while (x < a[j]) { // �����������Ĳ���λ��
					a[j + 1] = a[j];
					j--; // Ԫ�غ���
				}
				a[j + 1] = x; // ���뵽��ȷλ��
			}
		}
	}
}