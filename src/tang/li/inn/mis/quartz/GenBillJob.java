package tang.li.inn.mis.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import tang.li.inn.entity.entered.EnteredInfo;
import tang.li.inn.infrastructure.dao.bill.BillRoomDao;
import tang.li.inn.infrastructure.dao.entered.EnteredInfoDao;
import tang.li.inn.infrastructure.dao.room.RoomDao;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.util.InnHQL;

public class GenBillJob implements Job
{


	@Autowired
	RoomDao roomDao;

	@Autowired
	EnteredInfoDao enteredInfoDao;

	@Autowired
	BillRoomDao billRoomDao;

	


	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException
	{

		try
		{
			roomDao.findAll();
			for (EnteredInfo ei : enteredInfoDao.find(InnHQL.ENTEREDINFO_NOW))
			{
				String enteredTime = ei.getEnteredTime();
				System.out.println(enteredTime);
			}
		}
		catch (InnException e)
		{
			e.printStackTrace();
		}

	}

}
