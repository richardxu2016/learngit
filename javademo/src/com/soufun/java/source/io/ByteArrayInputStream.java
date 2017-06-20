package com.soufun.java.source.io;

import java.io.IOException;

public class ByteArrayInputStream extends InputStream {

	// �����ֽ����������ݵ��ֽ�����
	protected byte buffer[];

	// ��һ������ȡ�ֽڵ�����
	protected int position;

	// ��ǵ�����
	protected int mark = 0;

	// �ֽ����ĳ���
	protected int count;

	// ���캯��
	public ByteArrayInputStream(byte[] buffer) {
		this.buffer = buffer;
		this.position = 0;
		this.count = buffer.length;
	}

	// ����һ������Ϊbuf���ֽ��������Ҵ�offset��ʼ��ȡ���飬��ȡ����Ϊlength
	public ByteArrayInputStream(byte[] buf, int offset, int length) {
		super();
		// �ֽ�����Ӧ���ֽ�����
		this.buffer = buf;
		// ��һ��Ҫ����ȡ���ֽ�������
		this.position = offset;
		// ��ǵ��ֽ����Ķ�ȡλ��
		this.mark = offset;
		// ��ʼ���ֽ����ĳ���  ��������ֵ�н�С��һ��
		this.count = Math.min(offset + length, buf.length);
	}

	// ��ȡ��һ���ֽ�
	@Override
	public synchronized int read() {
		//�� 0xff �� & ����Ὣ byte ֵ��� int ���͵�ֵ,Ҳ�� -128~0 ��ĸ�ֵ��ת����ֵ
		return (position < count) ? (buffer[position++] & 0xff) : -1;
	}

	/*
	 * ���ֽ���������д���ֽ�����b�� 
	 * offset���ֽ�����b��ƫ�Ƶ�ַ ��ʾ������b��off��ʼд������ 
	 * lengthΪд���ֽڵĳ���
	 * 
	 * @see com.soufun.java.source.io.InputStream#read(byte[], int, int)
	 */
	public synchronized int read(byte[] b, int offset, int length) {
		if (b == null) {
			throw new NullPointerException();
		} else if (offset < 0 || length < 0 || length > b.length - offset) {
			throw new IndexOutOfBoundsException();
		}

		if (position >= count) {
			return -1;
		}

		int avail = count - position;
		if (length > avail) {
			length = avail;
		}
		if (length <= 0) {
			return 0;
		}
		System.arraycopy(buffer, position, b, offset, length);
		position += length;
		return length;
	}
	
	//�������ֽ������е�n���ֽ�
	public synchronized long skip(long n){
		long k = count - position;
		if (n < k) {
			k = n<0?0:n;
		}
		
		position += k ;
		return k;
	}
	
	//�ܷ��ȡ�ֽ�������һ���ֽ�
	public synchronized int available(){
		return count - position ;
	}
	
	//�Ƿ�֧�ֱ�ǩ  һֱ����true
	public boolean markSupported(){
		return true ;
	}
	
	/*
	 * ���浱ǰλ��
	 * ��¼���λ��֮��ĳһʱ�̵���reset()�򽫡��ֽ�����һ������ȡ��λ�á�
	 * ���õ�mark(int readLimit)����ǵ�λ�ã�Ҳ����˵��reset()֮���ٶ�ȡ�ֽ���ʱ��
	 * �Ǵ�mark(int readLimit)����ǵ�λ�ÿ�ʼ��
	 * @see com.soufun.java.source.io.InputStream#mark(int)
	 */
	public void mark(int readAheadLimit){
		mark = position ;
	}
	
	//�����ֽ����Ķ�ȡ����Ϊmark����ǵ�λ��
	public synchronized void reset(){
		position = mark ;
	}
	
	public void close() throws IOException{}
	
	//�����ǲ��Գ���
	
	private static final int LEN = 5 ;
	//abcdefghijklmnopqrstuvwxyz
	private static final byte[] ArrayLetters ={0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
        0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A};
	public static void main(String[] args) {
		String tmp  =new String(ArrayLetters);
		System.out.println("ArrayLetters:"+tmp);
		testByteArrayInputStream();
	}

	private static void testByteArrayInputStream() {

		//����ByteArrayInputStream�ֽ���
		ByteArrayInputStream bais = new ByteArrayInputStream(ArrayLetters);
		
		//���ֽ����ж�ȡ5���ֽ�
		for (int i = 0; i < LEN; i++) {
			if (bais.available()>0) {
				int tmp = bais.read();
				//toHexString ��ʮ�����ƣ����� 16���޷���������ʽ����һ�������������ַ�����ʾ��ʽ��
				System.out.printf("%d : 0x%s\n",i,Integer.toHexString(tmp));
			}
		}
		
		//�����ֽ�����֧�ֱ�ǹ��ܣ���ֱ���˳�
		if (!bais.markSupported()) {
			System.out.println("mark not supported");
			return ;
		}
		
		/*
		 * ��ǡ��ֽ�������һ������ȡ��λ�á�����--��ǡ�0x66��.ǰ���Ѿ���ȡ��5���ֽڣ�������һ������ȡ��λ���ǵ�6���ֽڡ�.
		 * ���˱�ǵ������ǣ�
		 * 1��ByteArrayInputStream���mark(0)�����еġ�����0����û��ʵ������ġ�
		 * 2�� mark()��reset()�����׵ģ�reset()�Ὣ���ֽ�������һ������ȡ��λ�á�����Ϊ��mark()���������λ�á�
		 */
		bais.mark(0);
		// ����5���ֽڡ�����5���ֽں��ֽ�������һ������ȡ��ֵӦ���ǡ�0x6B����
		bais.skip(5);
		//bais.mark(1);  //�� 0x6B  �����   ��ֻ��һ�α����Ч  ���������һ�Σ�reset��ʱ�������õ������һ��
		//���ֽ����ж�ȡ5�� ����ȡ��0x6B, 0x6C, 0x6D, 0x6E, 0x6F��
		byte[] buffer = new byte[LEN];
		bais.read(buffer, 0, LEN);
		//��bufferת��Ϊ�ַ���
		String s1 = new String(buffer);
		System.out.printf("s1=%s\n",s1);
		
		//�����ֽ��������ֽ����е���һ������ȡ��λ�����õ�mark()����ǵ�λ�á�����0x66��
		bais.reset();  //���õ���  0x6B
		//�����ú���ֽ����ж�ȡ5���ֽڵ�buffer�У���0x66, 0x67, 0x68, 0x69, 0x6A
		bais.read(buffer, 0, LEN);
		//ת��Ϊ�ַ���
		String s2 = new String(buffer);
		System.out.printf("s2=%s\n",s2);
		
	}
}