package test;


import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzDemo implements Job {

	public static En en ;
	
	public static En getEn() {
		return en;
	}
	public static void setEn(En en) {
		QuartzDemo.en = en;
	}
	public static boolean boo = true;
	public static boolean isBoo() {
		return boo;
	}
	public static void setBoo(boolean boo) {
		QuartzDemo.boo = boo;
	}
	public static int count = 0;
	@Override
	public void execute(JobExecutionContext jobCtx)
			throws JobExecutionException {
		if (boo) {
		    boo=false;
		}else{
			boo = true;
		}
	}
	
	public static void sendMessage(){
		if (boo) {
			System.out.println("ÍÆËÍÏûÏ¢");
			count++;
		}
	}
}

class En {
	private String newCode;
	private boolean flag;
	private Date sendTime;

	public String getNewCode() {
		return newCode;
	}

	public void setNewCode(String newCode) {
		this.newCode = newCode;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
}