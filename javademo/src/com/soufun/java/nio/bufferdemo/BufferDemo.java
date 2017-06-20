package com.soufun.java.nio.bufferdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo
{
    public static void main(String[] args) 
	{
//    	  try
//		{
//			  File in = new File("D:\\in.txt");  
//			  File out = new File("D:\\out.txt");  
//			  if(in.createNewFile()){  
//			      System.out.println("in.txt������");  
//			      FileOutputStream is = new FileOutputStream(in);  
//			      byte[] b = "��һ�����е�".getBytes();  
//			      is.write(b, 0, b.length);  
//			  }  
//			  if(out.createNewFile()){  
//			      System.out.println("out.txt������");  
//			  }  
//			  FileInputStream is = new FileInputStream(in);  
//			  FileOutputStream os = new FileOutputStream(out);  
//			  FileChannel fis = is.getChannel();  
//			  FileChannel fos = os.getChannel();  
//			  ByteBuffer bytedata = ByteBuffer.allocate(100);  
//			  while(fis.read(bytedata)!= -1){  
//			      //ͨ��ͨ����д������С�  
//			      bytedata.flip();  
//			      fos.write(bytedata);  
//			      bytedata.clear();  
//			  }  
//			  fis.close();  
//			  fos.close();  
//			  is.close();  
//			  os.close();
//		} catch (FileNotFoundException e)
//		{
//			e.printStackTrace();
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		}  
		try
		{
			File file = new File("nio-data.txt");
			FileInputStream is = new FileInputStream(file);
			FileChannel inChannel = is.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(100);
			int byteRead =  inChannel.read(buf);
			System.out.println(byteRead);
			while (byteRead != -1)
			{
				buf.flip();
				while (buf.hasRemaining())
				{
					System.out.println((char)buf.get());
				}
				buf.clear();
				byteRead = inChannel.read(buf);
			}
			is.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} 
	}
}
