package com.soufun.java.sort;

import java.util.Arrays;

public class QuickSort {

	/**
	 * ��������֮�������� 
	 * �ڴ������n����¼����ȡһ����¼(ͨ��ȡ��һ����¼),�Ѹü�¼�����ʵ�λ�ú�,�������б��˼�¼���ֳ������֡�
	 * ���йؼ��ֱȸü�¼�ؼ���С�ļ�¼������ǰһ����,
	 * ���б�����ļ�¼�����ں�һ����,���Ѹü�¼�����������ֵ��м�(��Ϊ�ü�¼��λ),������̳���һ�˿�������
	 * 
	 * ʹ�õ��ǵݹ�ԭ��������ͬ������O(n longn) �����򷽷��У���ƽ��������á���ƽ��ʱ����ԣ���Ŀǰ����Ϊ��õ�һ���ڲ����򷽷�
	 */
	public static void main(String[] args) {
		int[] forSort = new int[] { 12, 3, 45, 13, 0, 1, 7, 5, 89, 35, 23, 46 };
		quickSort(forSort, 0, forSort.length - 1);
		System.out.println(Arrays.toString(forSort));
	}

	/*
	 * �Ȱ�������Ϊ����ԭ��д���㷨����д����չ���㷨������{49,38,65,97,76,13,27}
	 */
	public static void quickSort(int[] n, int left, int right) {
		int pivot;
		if (left < right) {
			// pivot��Ϊ���ᣬ��֮С��Ԫ�����󣬽�֮���Ԫ������
			pivot = partition(n, left, right);
			// ����������ݹ���ÿ�������ֱ��˳����ȫ��ȷ
			quickSort(n, left, pivot - 1);
			quickSort(n, pivot + 1, right);
		}
	}

	public static int partition(int[] n, int left, int right) {
		int pivotkey = n[left];
		// ����ѡ������Զ���䣬�������м䣬ǰС���
		while (left < right) {
			while (left < right && n[right] >= pivotkey)
				--right;
			// ��������С��Ԫ���Ƶ��Ͷˣ���ʱrightλ�൱�ڿգ��ȴ���λ��pivotkey���������
			n[left] = n[right];
			while (left < right && n[left] <= pivotkey)
				++left;
			// ����������Ԫ���Ƶ��߶ˣ���ʱleftλ�൱�ڿգ��ȴ���λ��pivotkeyС��������
			n[right] = n[left];
		}
		// ��left == right�����һ�˿������򣬴�ʱleftλ�൱�ڿգ��ȴ�pivotkey����
		n[left] = pivotkey;
		return left;
	}
}