package test;

public class SleepThread {

	private static int state = 1;

	private static int num1 = 1;
	private static int num2 = 2;

	public static void main(String[] args) {

		final SleepThread t = new SleepThread();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (num1 < 100) { // �����̶߳���t������Ϊ������֤ÿ�������ڼ�ֻ��һ���߳��ڴ�ӡ
					synchronized (t) { // ���state!=1,˵����ʱ��δ�ֵ��߳�1��ӡ,
										// �߳�1������t��wait()����, ֱ���´α�����
						if (state != 1) {
							try {
								t.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						// ��state=1ʱ, �ֵ��߳�1��ӡ5������
						for (int j = 0; j < 5; j++) {
							System.out.println(num1);
							num1 += 2;
						} // �߳�1��ӡ��ɺ�, ��state��ֵΪ2,��ʾ���������ֵ��߳�2��ӡ state = 2; //
							// notifyAll()����������t��wait���߳�2, ͬʱ�߳�1���˳�ͬ�������, �ͷ�t��
						t.notifyAll();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (num2 < 100) {
					synchronized (t) {
						if (state != 2) {
							try {
								t.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						for (int j = 0; j < 5; j++) {
							System.out.println(num2);
							num2 += 2;
						}
						state = 1;
						t.notifyAll();
					}
				}
			}
		}).start();
	}

	// Thread t1 = new Thread(new Test1("thread1", k));
	// Thread t2 = new Thread(new Test1("thread2", k));
	// t1.start();
	// t2.start();

}
