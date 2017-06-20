package com.soufun.java.source.io;

import java.io.Closeable;
import java.io.IOException;

public abstract class InputStream implements Closeable {

	private static final int MAX_SKIP_BUFFER_SIZE = 2048;

	// 从输入流中读取数据的下一个字节
	public abstract int read() throws IOException;

	// 将数据从输入流读入byte数组
	public int read(byte[] b) throws IOException {
		return read(b, 0, b.length);
	}

	// 将最多length个数据字节从输入流写入字节数组
	public int read(byte[] b, int off, int length) throws IOException {
		if (b == null)
			throw new NullPointerException();
		else if (off < 0 || length < 0 || length > b.length - off) {
			throw new IndexOutOfBoundsException();
		} else if (length == 0) {
			return 0;
		}

		int c = read();
		if (c == -1) {
			return -1;
		}

		b[off] = (byte) c;
		int i = 1;
		try {
			for (; i < length; i++) {
				c = read();
				if (c == -1) {
					break;
				}
				b[off + i] = (byte) c;
			}
		} catch (IOException e) {
		}
		return i;
	}

	@Override
	public void close() throws IOException {
	}

	public long skip(long n) throws IOException {

		long remaining = n;
		int nr;
		if (n <= 0) {
			return 0;
		}
		int size = (int) Math.min(MAX_SKIP_BUFFER_SIZE, remaining);
		byte[] skipBuffer = new byte[size];
		while (remaining > 0) {
			nr = read(skipBuffer, 0,
					(int) Math.min(MAX_SKIP_BUFFER_SIZE, remaining));
			if (nr < 0) {
				break;
			}
			remaining -= nr;
		}

		return n - remaining;
	}
	
	public int available() throws IOException{
		return 0  ;
	}
	
	public synchronized void mark(int readlimit){}
	
	public synchronized void reset() throws IOException{
		throw new IOException("mark/reset not supported");
	}
	
	public boolean markSupported(){
		return false ;
	}
}