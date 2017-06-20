package com.soufun.java.source.io;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class ByteArrayOutputStream extends OutputStream{

	//保存字节数组输出流的数据的数组
	protected byte buffer[] ;
	
	//计数
	protected int count ;

	
	
	public ByteArrayOutputStream() {
		this(32);
	}

    //初始化长度
	public ByteArrayOutputStream(int size) {
		if (size<0) {
			throw new IllegalArgumentException("初始化错误");
		}
		buffer = new byte[size];
	}

	/*
	 * 确认“容量”
	 * 若“实际容量<minCapacity”,则增加输出流的容量
	 * @see com.soufun.java.source.io.OutputStream#write(int)
	 */
    public void ensureCapacity(int minCapacity){
    	if (minCapacity>buffer.length) {
			grow(minCapacity);
		}
    }
    
    //增加容量
	public void grow(int minCapacity) {
		int oldCapacity = buffer.length;
		//新的容量是旧容量的2倍
		int newCapacity = oldCapacity<<1;
		if (newCapacity>minCapacity) {
			newCapacity = minCapacity;
		}
		if (newCapacity<0) {
			if (minCapacity<0) {
				 throw new OutOfMemoryError();
			}
			newCapacity = Integer.MAX_VALUE;
		}
		
		buffer =  Arrays.copyOf(buffer, newCapacity);
	}
	
	
   //写入一个字节b到“字节数组输出流”中，并将计数+1
	@Override
	public synchronized void write(int b) throws IOException {
		ensureCapacity(b);
		buffer[count]  = (byte) b;
		count++ ;
	}

	public synchronized void write(byte[] b,int off ,int len){
		  if ((off < 0) || (off > b.length) || (len < 0) ||
				              ((off + len) - b.length > 0)) {
				throw new IndexOutOfBoundsException();
		  }
		  ensureCapacity(count + len);
		  System.arraycopy(b, off, buffer, count, len);
		  count += len;
	}
	
	//写入输入流到字节数组输入流中
	public synchronized void writeTo(OutputStream out) throws IOException{
		out.write(buffer, 0, count);
	}
	
	//重置计数
	public void reset(){
		count = 0 ;
	}
	
	//将输出流转化为字节数组
	public synchronized byte[] toByteArray(){
		return Arrays.copyOf(buffer, count);
	}
	
	public synchronized int size(){
		return count;
	}
	
	public synchronized String toString(){
		return new String(buffer,0,count);
	}
	
	public synchronized String toString(String charset) throws UnsupportedEncodingException{
		return new String(buffer,0,count, charset);
	}

	public void close() throws IOException{}
	
	/**
	 * 测试代码
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		tesByteArrayOutputStream();
	}
	
	private static void tesByteArrayOutputStream() throws IOException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		//依次写入A B C 
		baos.write(0x41);
		baos.write(0x42);
		baos.write(0x43);
		
		System.out.println("baos1:"+baos);
		// 即对应写入“0x64, 0x65, 0x66, 0x67, 0x68”，即“d e f g h”
		baos.write(ArrayLetters,3,5);
		System.out.println("baos2:"+baos);
		
		int size = baos.size();
		System.out.println("size:"+size);
		
		//转化为字节数组
		byte[] buffer = baos.toByteArray();
		System.out.println("string:"+new String(buffer));
		
		ByteArrayOutputStream  b = new ByteArrayOutputStream();
		baos.writeTo(b);
		System.out.println("b:"+b);
	}
	private static final byte[] ArrayLetters ={0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
        0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A};

}
