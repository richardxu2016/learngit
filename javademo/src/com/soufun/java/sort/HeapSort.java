package com.soufun.java.sort;

/**
 * ѡ������֮������
 */
public class HeapSort {
	public static void main(String[] args) {
		// ����0�±�Ԫ����Ϊ�ݴ浥Ԫ
		int[] array = { 0, 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5,
				4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51 };
		// int[] array = { 0, 49, 38, 65, 97, 76, 13, 27, 49 };
		heapSort(array);
		for (int i = 1; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

	// ������=====================================================
	public static void heapSort(int[] array) {
		// ���������ɸѡ������һ���󶥶�
		double len = array.length - 1;
		for (int i = (int) Math.floor(len / 2); i > 0; i--) {
			heapAdjust(array, i, array.length - 1);
		}
		for (int i = array.length - 1; i > 1; i--) {
			// ���Ѷ�Ԫ�������һ��Ԫ�ص���λ�ã���������ֵ
			swap(array, 1, i);
			// �����һλ�޳�����������±��Ϊi-1���ԶӶ���Ҷ�ӽ��е������γ�һ���¶ѣ��˹��̳�Ϊɸѡ
			heapAdjust(array, 1, i - 1);
		}
	}

	// ���Ѻ�������Ϊ��s��m����ֻ�� s
	// ��Ӧ�Ĺؼ���δ����󶥶Ѷ��壬ͨ������ʹ��s��m����Ϊ�󶥶�=====================================================
	public static void heapAdjust(int[] array, int s, int m) {
		// ��0�±�Ԫ����Ϊ�ݴ浥Ԫ
		array[0] = array[s];
		// �غ��ӽϴ�Ľ������ɸѡ
		for (int j = 2 * s; j <= m; j *= 2) {
			// ��֤jΪ�ϴ��ӽ����±꣬j < m ��֤ j+1 <= m ����Խ��
			if (j < m && array[j] < array[j + 1]) {
				j++;
			}
			if (!(array[0] < array[j])) {
				break;
			}
			// ��Sλ��С��Ӧ���ϴ�������
			array[s] = array[j];
			// �ϴ��ӵ�ֵ���Sλ�Ľ�Сֵ���������𶥶ѵĲ�ƽ�⣬�ʶ������ڵĶѽ���ɸѡ
			s = j;
		}
		// ��Sλ�ϴ���ֵ���䣻����Sλ�����ƶ���2*s��4*s��������
		array[s] = array[0];
	}

	// ��������=====================================================
	public static void swap(int[] array, int i, int j) {
		int temp;
		temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
