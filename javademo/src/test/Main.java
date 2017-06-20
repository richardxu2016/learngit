package test;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main{
	public static void main(String[] args){
		// 用类装载器bb装载B
//		try{
//			MyClassLoader bb = new MyClassLoader();
//			B b = new B(new Integer(1));
//			b.printB();
//			Class<?> clazz = bb.loadClass("B");
//			Constructor<?> constructor = clazz.getConstructor(new Class<?>[]{ Integer.class });
//			Object object = constructor.newInstance(new Object[]{ new Integer(2) });
//			Method method = clazz.getDeclaredMethod("printB", new Class[0]);
//			method.invoke(object, new Object[0]);
//		} catch (Exception e){
//			e.printStackTrace();
//		}
		String username ="网奖励：133 5292 5352嘎嘎嘎";
		String content ="价格价格就无法133 5292 5352案件改革就发发";
		Pattern pattern = Pattern.compile("13352925352".replaceAll("\\d{1}", "$0[-|_|\\\\s]*"));
		Matcher userM = pattern.matcher(username); 
		Matcher contentM = pattern.matcher(content); 
		if (userM.find()||contentM.find())
		{
			System.out.println("you");
		}else{
			System.out.println("meiyou");
		}
		//System.out.println("===============");
		// [/s|-|_]*3[/s|-|_]*5[/s|-|_]*2[/s|-|_]*9[/s|-|_]*2[/s|-|_]*5[/s|-|_]*3[/s|-|_]*5[/s|-|_]*2
		//if (username.matches("1[-|_|\\s]*3[-|_|\\s]*3[-|_|\\s]*5[-|_|\\s]*2[-|_|\\s]*9[-|_|\\s]*2[-|_|\\s]*5[-|_|\\s]*3[-|_|\\s]*5[-|_|\\s]*2")||content.matches("1[-|_|\\s]*3[-|_|\\s]*3[-|_|\\s]*5[-|_|\\s]*2[-|_|\\s]*9[-|_|\\s]*2[-|_|\\s]*5[-|_|\\s]*3[-|_|\\s]*5[-|_|\\s]*2"))
		//System.out.println("133-5292-5352".replaceAll("\\d{1}", "$0[-|_|\\s]*"));
		//System.out.println("13352925352".matches("1[-|_|\\s]*3[-|_|\\s]*3[-|_|\\s]*5[-|_|\\s]*2[-|_|\\s]*9[-|_|\\s]*2[-|_|\\s]*5[-|_|\\s]*3[-|_|\\s]*5[-|_|\\s]*2"));
	}
}
class B{
	static int b;
	public B(Integer testb){
		b = testb.intValue();
	}
	public void printB(){
		System.out.println("my static field b is " + b);
	}
}
class MyClassLoader extends URLClassLoader{
	//Class文件所在的目录
	private static File file = new File("D:\\java\\myeclipse1064wp\\Java_test\\bin\\test");
	public MyClassLoader(){
		super(getURL());
	}
	@SuppressWarnings("deprecation")
	public static URL[] getURL(){
		try{
			return new URL[]{ file.toURL() };
		} catch (Exception e){
			return new URL[0];
		}
	}
}