package tang.li.inn.mis.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;



public class GenBillScheduler
{
	
	
	public static void main(String[] ars)
	{
		
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler;
		try
		{
			scheduler = sf.getScheduler();
			scheduler.start();		//开启线程
			
			
		    JobDetail job =JobBuilder.newJob(GenBillJob.class).withIdentity("job1", "group1").build();

		    Trigger trigger =TriggerBuilder.newTrigger()
		        .withIdentity("trigger1", "group1")
		        .startNow()		//the trigger may or may not fire at this time - depending upon the schedule configured for the Trigger. 
		        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
		                .withIntervalInSeconds(1)
		                .repeatForever())
		        .build();

		    // Tell quartz to schedule the job using our trigger
		    scheduler.scheduleJob(job, trigger);
			
		    
		    
			
		}
		catch (SchedulerException e)
		{
			e.printStackTrace();
		}

	}
	
}
