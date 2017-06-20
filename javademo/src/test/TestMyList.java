package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestMyList
{
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		MyArrayList<String> al = new MyArrayList<String>();
		al.add("sssssssssssssssss");
		al.add("bbbbbbbbbbbbbbbbbbt");
		al.add("gggggggggggggggggg");

		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"D:\\al.tmp"));
		oos.writeObject(al);

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"D:\\al.tmp"));

		@SuppressWarnings("unchecked")
		MyArrayList<String> a = (MyArrayList<String>) ois.readObject();
		System.out.println(a);
		for (String s : a)
		{
			//System.out.println(s);
		}
	}
}
