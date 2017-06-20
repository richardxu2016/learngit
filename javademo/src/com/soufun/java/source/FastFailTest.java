package com.soufun.java.source;

import java.util.*;

/*
 * @desc java������Fast-Fail�Ĳ��Գ���
 *
 *   fast-fail�¼�������������������̶߳�Collection���в���ʱ��������ĳһ���߳�ͨ��iteratorȥ��������ʱ���ü��ϵ����ݱ������߳����ı䣻����׳�ConcurrentModificationException�쳣��
 *   fast-fail����취��ͨ��util.concurrent���ϰ��µ���Ӧ��ȥ�����򲻻����fast-fail�¼���
 *
 *   �����У��ֱ����ArrayList��CopyOnWriteArrayList�����������ArrayList�����fast-fail�¼�����CopyOnWriteArrayList�������fast-fail�¼���
 *   (01) ʹ��ArrayListʱ�������fast-fail�¼����׳�ConcurrentModificationException�쳣���������£�
 *            private static List<String> list = new ArrayList<String>();
 *   (02) ʹ��ʱCopyOnWriteArrayList���������fast-fail�¼����������£�
 *            private static List<String> list = new CopyOnWriteArrayList<String>();
 *
 * @author skywang
 */
public class FastFailTest {

    private static List<String> list = new ArrayList<String>(); 
    //private static List<String> list = new CopyOnWriteArrayList<String>();
    public static void main(String[] args) {
    
        // ͬʱ���������̶߳�list���в�����
        new ThreadOne().start();
        new ThreadTwo().start();
    }

    private static void printAll() {
        System.out.println("");

        String value = null;
        Iterator iter = list.iterator();
        while(iter.hasNext()) {
            value = (String)iter.next();
            System.out.print(value+", ");
        }
    }

    /**
     * ��list���������0,1,2,3,4,5��ÿ���һ����֮�󣬾�ͨ��printAll()��������list
     */
    private static class ThreadOne extends Thread {
        public void run() {
            int i = 0;
            while (i<6) {
                list.add(String.valueOf(i));
                printAll();
                i++;
            }
        }
    }

    /**
     * ��list���������10,11,12,13,14,15��ÿ���һ����֮�󣬾�ͨ��printAll()��������list
     */
    private static class ThreadTwo extends Thread {
        public void run() {
            int i = 10;
            while (i<16) {
                list.add(String.valueOf(i));
                printAll();
                i++;
            }
        }
    }

}