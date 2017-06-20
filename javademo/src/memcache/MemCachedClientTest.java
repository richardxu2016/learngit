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
		pool.setWeights(weights); //设置权重
		pool.setInitConn(5); //设置开始时每个cache服务器的可用连接数
		pool.setMinConn(5); // 设置每个服务器最少可用连接数
		pool.setMinConn(250); //设置每个服务器最大可用连接数
		pool.setMaxIdle(1000 * 60 * 60 * 6);  //设置可用连接池的最长等待时间
		
		/*
		 * 设置连接池维护线程的睡眠时间设置为0，维护线程不启动
		 * 维护线程主要通过log输出socket的运行状况，监测连接数
		 * 目及空闲等待时间等参数以控制连接创建和关闭。
		 */
		pool.setMaintSleep(30);
		
		pool.setNagle(false); //设置是否使用Nagle算法，因为我们的通讯数据量通常都比较大（相对TCP控制数据）而且要求响应及时，因此该值需要设置为false（默认是true）
		pool.setSocketTO(3000); //设置socket的读取等待超时值
		pool.setSocketConnectTO(0); //设置socket的连接等待超时值
		
		pool.initialize(); //设置完pool参数后最后调用该方法，启动pool
		
	}

	public static void addCache(){
		/**
		 * expire 如果为0表示永不过期，除非重启memcached
		 *        如果设置为秒数，最长为 30 * 3600 * 24 s (30天) 
		 *        
		 * key最大键长为250个字符，value可以接受的储存数据不能超过1MB
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
		 //从cache里取值       
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