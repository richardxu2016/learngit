package memcache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemCachedClientTest{
	protected static MemCachedClient mcc = new MemCachedClient();
	static {
		String[] servers = {"192.168.82.219:11211"};
		Integer[] weights = {3};
		
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(servers);
		pool.setWeights(weights); //����Ȩ��
		pool.setInitConn(5); //���ÿ�ʼʱÿ��cache�������Ŀ���������
		pool.setMinConn(5); // ����ÿ�����������ٿ���������
		pool.setMinConn(250); //����ÿ��������������������
		pool.setMaxIdle(1000 * 60 * 60 * 6);  //���ÿ������ӳص���ȴ�ʱ��
		
		/*
		 * �������ӳ�ά���̵߳�˯��ʱ������Ϊ0��ά���̲߳�����
		 * ά���߳���Ҫͨ��log���socket������״�������������
		 * Ŀ�����еȴ�ʱ��Ȳ����Կ������Ӵ����͹رա�
		 */
		pool.setMaintSleep(30);
		
		pool.setNagle(false); //�����Ƿ�ʹ��Nagle�㷨����Ϊ���ǵ�ͨѶ������ͨ�����Ƚϴ����TCP�������ݣ�����Ҫ����Ӧ��ʱ����˸�ֵ��Ҫ����Ϊfalse��Ĭ����true��
		pool.setSocketTO(3000); //����socket�Ķ�ȡ�ȴ���ʱֵ
		pool.setSocketConnectTO(0); //����socket�����ӵȴ���ʱֵ
		
		pool.initialize(); //������pool�����������ø÷���������pool
		
	}

	public static void addCache(){
		/**
		 * expire ���Ϊ0��ʾ�������ڣ���������memcached
		 *        �������Ϊ�������Ϊ 30 * 3600 * 24 s (30��) 
		 *        
		 * key������Ϊ250���ַ���value���Խ��ܵĴ������ݲ��ܳ���1MB
		 */
		mcc.add("string", "This is a test String",new Date(60));
		mcc.set("num", 12345,new Date(60));
		mcc.set("array", new String[]{"a","b","c"},new Date(60));
		mcc.set("student", new Student("Tom",24),new Date(60));
		mcc.set("buer", true,new Date(60));
		mcc.set("kong", null,new Date(60));
		mcc.set("kong", new ArrayList<String>().add("string"),new Date(60));
	}
	
	public static void output(){
		 //��cache��ȡֵ       
	       String value = (String) mcc.get( "string" );          
	       System.out.println(value);   
	       Integer num = (Integer) mcc.get( "num" );          
	       System.out.println(num);   
	       String[] array = (String[]) mcc.get("array");
	       for (String string : array){
			   System.out.print(string+",");	
		   }
	       System.out.println();
	       Student student = (Student) mcc.get( "student" );          
	       System.out.println(student);   
	       boolean buer = (Boolean) mcc.get( "buer" );          
	       System.out.println("buer:"+buer);   
	       Object o =  mcc.get( "kong" );          
	       System.out.println("o:"+o);   
	       ArrayList<String> as =  (ArrayList<String>) mcc.get( "as" );          
	       System.out.println("as:"+as);   
	}
	
	public static void main(String[] args)
	{
		addCache();
		output();
	}
}

class Student implements Serializable{
	private static final long serialVersionUID = 1060515811635325750L;
	private String name ;
	private int age ;
	public Student(String name, int age)
	{
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString()
	{
		return "Student [name=" + name + ", age=" + age + "]";
	} 
}