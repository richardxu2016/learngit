package com.soufun.java.source.io;

import java.io.IOException;

public class ByteArrayInputStream extends InputStream {

	// 保存字节输入流数据的字节数组
	protected byte buffer[];

	// 下一个被读取字节的索引
	protected int position;

	// 标记的索引
	protected int mark = 0;

	// 字节流的长度
	protected int count;

	// 构造函数
	public ByteArrayInputStream(byte[] buffer) {
		this.buffer = buffer;
		this.position = 0;
		this.count = buffer.length;
	}

	// 创造一个内容为buf的字节流，并且从offset开始读取数组，读取长度为length
	public ByteArrayInputStream(byte[] buf, int offset, int length) {
		super();
		// 字节流对应的字节数组
		this.buffer = buf;
		// 下一个要被读取的字节索引号
		this.position = offset;
		// 标记的字节流的读取位置
		this.mark = offset;
		// 初始化字节流的长度  返回两个值中较小的一个
		this.count = Math.min(offset + length, buf.length);
	}

	// 读取下一个字节
	@Override
	public synchronized int read() {
		//与 0xff 做 & 运算会将 byte 值变成 int 类型的值,也将 -128~0 间的负值都转成正值
		return (position < count) ? (buffer[position++] & 0xff) : -1;
	}

	/*
	 * 将字节流的数据写入字节数组b中 
	 * offset是字节数组b的偏移地址 表示从数组b的off开始写入数据 
	 * length为写入字节的长度
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
	
	//跳过“字节流”中的n个字节
	public synchronized long skip(long n){
		long k = count - position;
		if (n < k) {
			k = n<0?0:n;
		}
		
		position += k ;
		return k;
	}
	
	//能否读取字节流的下一个字节
	public synchronized int available(){
		return count - position ;
	}
	
	//是否支持标签  一直返回true
	public boolean markSupported(){
		return true ;
	}
	
	/*
	 * 保存当前位置
	 * 记录标记位置之后，某一时刻调用reset()则将“字节流下一个被读取的位置”
	 * 重置到mark(int readLimit)所标记的位置；也就是说，reset()之后再读取字节流时，
	 * 是从mark(int readLimit)所标记的位置开始的
	 * @see com.soufun.java.source.io.InputStream#mark(int)
	 */
	public void mark(int readAheadLimit){
		mark = position ;
	}
	
	//重置字节流的读取索引为mark所标记的位置
	public synchronized void reset(){
		position = mark ;
	}
	
	public void close() throws IOException{}
	
	//以下是测试程序
	
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

		//创建ByteArrayInputStream字节流
		ByteArrayInputStream bais = new ByteArrayInputStream(ArrayLetters);
		
		//从字节流中读取5个字节
		for (int i = 0; i < LEN; i++) {
			if (bais.available()>0) {
				int tmp = bais.read();
				//toHexString 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。
				System.out.printf("%d : 0x%s\n",i,Integer.toHexString(tmp));
			}
		}
		
		//若该字节流不支持标记功能，则直接退出
		if (!bais.markSupported()) {
			System.out.println("mark not supported");
			return ;
		}
		
		/*
		 * 标记“字节流中下一个被读取的位置”。即--标记“0x66”.前面已经读取了5个字节，所以下一个被读取的位置是第6个字节”.
		 * 做了标记的作用是，
		 * 1、ByteArrayInputStream类的mark(0)函数中的“参数0”是没有实际意义的。
		 * 2、 mark()与reset()是配套的，reset()会将“字节流中下一个被读取的位置”重置为“mark()中所保存的位置”
		 */
		bais.mark(0);
		// 跳过5个字节。跳过5个字节后，字节流中下一个被读取的值应该是“0x6B”。
		bais.skip(5);
		//bais.mark(1);  //在 0x6B  做标记   且只有一次标记有效  就是最近的一次，reset的时候，是重置到最近的一次
		//从字节流中读取5个 即读取“0x6B, 0x6C, 0x6D, 0x6E, 0x6F”
		byte[] buffer = new byte[LEN];
		bais.read(buffer, 0, LEN);
		//将buffer转化为字符串
		String s1 = new String(buffer);
		System.out.printf("s1=%s\n",s1);
		
		//重置字节流，将字节流中的下一个被读取的位置重置到mark()所标记的位置。即“0x66”
		bais.reset();  //重置到了  0x6B
		//从重置后的字节流中读取5个字节到buffer中，即0x66, 0x67, 0x68, 0x69, 0x6A
		bais.read(buffer, 0, LEN);
		//转化为字符串
		String s2 = new String(buffer);
		System.out.printf("s2=%s\n",s2);
		
	}
}