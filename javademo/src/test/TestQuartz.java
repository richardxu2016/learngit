package test;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.WeakHashMap;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class TestQuartz {
	private static final SimpleTrigger CronTrigger = null;

	public static void main(String[] args) {
//		Test t = new Test();
//		t.test2();
	}

	public static Map<String,String> getJsonMap(String jsonStr) {
		String[] j  = jsonStr.split("}");
		String  json = j[0].replace("{", "").replace("}", "")
				.replace("\"", "").replace("\"", "");
		System.out.println(json);
		String[] jsonArr = json.split("\\,");
		Map<String,String> map = new WeakHashMap<String, String>();
		for (String key : jsonArr) {
			String [] s = key.split("\\:");
 			map.put(s[0], s[1]);
		}
		return map;
	}
	
	public void test1() {
		// ͨ��SchedulerFactory����ȡһ��������
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler;
		try {
			scheduler = schedulerFactory.getScheduler();
			// ������ҵ����
			JobDetail jobDetail = new JobDetail("job1",
					"jobGroup1", QuartzDemo.class);
			// newһ��������
			SimpleTrigger simpleTrigger = new SimpleTrigger("simpleTrigger",
					"triggerGroup-s1");
			// ������ҵ����ʱ��
			long ctime = System.currentTimeMillis();
			simpleTrigger.setStartTime(new Date(ctime));

			// ������ҵִ�м��
			simpleTrigger.setRepeatInterval(1000);

			// ������ҵִ�д���
			simpleTrigger.setRepeatCount(10);

			// ������ҵִ�����ȼ�Ĭ��Ϊ5
			// simpleTrigger.setPriority(10);

			// ��ҵ�ʹ��������õ���������
			scheduler.scheduleJob(jobDetail, simpleTrigger);

			// ����������
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void test2() {

		try {
			SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
			Scheduler sched = schedFact.getScheduler();
			sched.start();
			JobDetail jobDetail = new JobDetail(" Income Report ",
					" Report Generation ", QuartzDemo.class);
			//����ǵ�һ�ε���  ֱ������
			if (QuartzDemo.count==0) {
				En e = new En();
				QuartzDemo.setEn(e);
				QuartzDemo.sendMessage();
			}else{
				En e = QuartzDemo.getEn();
				//����ʱ���¥���ж��Ƿ�Ӧ���ٴη���
				e.getSendTime(); // ...
				//���Ӧ���ٴη���
				CronTrigger trigger = new CronTrigger(" Income Report "," Report Generation ");
				//�����ǰʱ���
				/**//* ÿ1����ִ��һ�� */
				trigger.setCronExpression("0/5 * * * * ?");
				sched.scheduleJob(jobDetail, trigger);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test3() {
		// ͨ��SchedulerFactory����ȡһ��������
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler;
		try {
			scheduler = schedulerFactory.getScheduler();
			// ������ҵ����
			JobDetail jobDetail = new JobDetail("jobDetail-s1",
					"jobDetailGroup-s1", QuartzDemo.class);
			
			// newһ�������� "16 26/1 8-17 * * ?"
			CronTrigger simpleTrigger = new CronTrigger("trigger", "group",
					"job", "group", "0/5 * * * * ?");
			// new SimpleTrigger("simpleTrigger", "triggerGroup-s1");

			// ������ҵ����ʱ��
			// Calendar excelCal = Calendar.getInstance();
			// excelCal.add(Calendar.DAY_OF_MONTH, 1);
			// /excelCal.set(Calendar.HOUR_OF_DAY, 16);
			// excelCal.set(Calendar.SECOND, 0);
			// excelCal.add(Calendar.MINUTE, 9);
			// long ctime = System.currentTimeMillis();
			// simpleTrigger.setStartTime(excelCal.getTime());
			// ������ҵִ�м��
			// simpleTrigger.setRepeatInterval(1000);
			// ������ҵִ�д���
			// simpleTrigger.setRepeatCount(10);
			// ������ҵִ�����ȼ�Ĭ��Ϊ5
			// simpleTrigger.setPriority(10);

			// ��ҵ�ʹ��������õ���������
			scheduler.scheduleJob(jobDetail, simpleTrigger);

			// ����������
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

