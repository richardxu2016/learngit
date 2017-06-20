package com.soufun.java.source.io;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public abstract class OutputStream implements Closeable ,Flushable{

	//���ֽ�bд�뵽�������
	public abstract void write(int b) throws IOException ;
	
	public void write(byte[] b) throws IOException{
		write(b,0,b.length);
	}
	
	//���ֽ�����bд�뵽�ֽ�������У�offsetΪ��ʵλ�ã�lengthΪд�볤��
	public void write(byte[] b, int offset, int length) throws IOException {
		
		if (b==null) {
			throw new NullPointerException();
		}else if(offset<0||offset>b.length||length<0||offset+length<0
				||offset+length>b.length){
			throw new IndexOutOfBoundsException();
		}else if (length==0) {
			return ;
		}
		
		for (int i = 0; i < length; i++) {
			write(b[offset+i]);
		}
	}

	@Override
	public void flush() throws IOException {
	}

	@Override
	public void close() throws IOException {
	}

}
