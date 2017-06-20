package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class ReadString
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		InputStreamReader andis = new FileReader(new File("D:\\java\\myeclipse1064wp\\Java_test\\src\\source\\android.txt"));
		InputStreamReader iosis = new FileReader(new File("D:\\java\\myeclipse1064wp\\Java_test\\src\\source\\ios.txt"));
		
		BufferedReader bis = new BufferedReader(andis);
		StringBuilder sb = new StringBuilder();
		String tempstr = null ;
		while ((tempstr=bis.readLine())!=null)
		{
			sb.append(tempstr+",");
		}
		BufferedReader iosisbr = new BufferedReader(iosis);
		StringBuilder iossb = new StringBuilder();
		String tempstrios = null ;
		while ((tempstrios=iosisbr.readLine())!=null)
		{
			iossb.append(tempstrios+",");
		}
		String[] andArr = sb.toString().split(",");
		String[] iosArr = iossb.toString().split(",");
		Set<String> set = new HashSet<String>();
		StringBuilder setstr = new StringBuilder();
		for (String and : andArr)
		{
			set.add(and);
		}
		for (String and : iosArr)
		{
			if(set.contains(and)){  // 如果包含的话
				setstr.append(and+",");
			}
		}
		bis.close();
		andis.close();
		iosis.close();
		System.out.println("android:"+sb.toString());
		System.out.println("ios:"+iossb.toString());
		System.out.println("set:"+set);
		System.out.println(setstr.toString());
	}

}
