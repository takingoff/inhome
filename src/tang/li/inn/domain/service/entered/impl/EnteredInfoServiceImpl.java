 /**   
* projectName: InnDomain
*
* fileName: EnteredInfoServiceImpl.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午3:48:48 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.entered.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import tang.li.inn.domain.service.entered.EnteredInfoService;
import tang.li.inn.entity.bill.BillPay;
import tang.li.inn.entity.bill.BillRoom;
import tang.li.inn.entity.entered.EnteredInfo;
import tang.li.inn.entity.room.Room;
import tang.li.inn.infrastructure.dao.bill.BillConsumeDao;
import tang.li.inn.infrastructure.dao.bill.BillPayDao;
import tang.li.inn.infrastructure.dao.bill.BillRoomDao;
import tang.li.inn.infrastructure.dao.entered.EnteredInfoDao;
import tang.li.inn.infrastructure.dao.room.RoomDao;
import tang.li.inn.infrastructure.exception.InnErrorsUtil;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiElementFilter;
import tang.li.inn.infrastructure.jui.JuiElementSorting;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;
import tang.li.inn.infrastructure.util.DateTimeUtil;
import tang.li.inn.infrastructure.util.InnConstant;
import tang.li.inn.infrastructure.util.InnHQL;
import tang.li.inn.infrastructure.util.StringUtil;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class EnteredInfoServiceImpl implements EnteredInfoService
{

	private EnteredInfoDao enteredInfoDao;
	
	private RoomDao roomDao;
	
	private BillPayDao billPayDao;
	
	private BillConsumeDao billConsumeDao;
	
	private BillRoomDao billRoomDao;
	
	
	public BillConsumeDao getBillConsumeDao()
	{
		return billConsumeDao;
	}


	public void setBillConsumeDao(BillConsumeDao billConsumeDao)
	{
		this.billConsumeDao = billConsumeDao;
	}


	public BillRoomDao getBillRoomDao()
	{
		return billRoomDao;
	}


	public void setBillRoomDao(BillRoomDao billRoomDao)
	{
		this.billRoomDao = billRoomDao;
	}


	public BillPayDao getBillPayDao()
	{
		return billPayDao;
	}


	public void setBillPayDao(BillPayDao billPayDao)
	{
		this.billPayDao = billPayDao;
	}


	public RoomDao getRoomDao()
	{
		return roomDao;
	}


	public void setRoomDao(RoomDao roomDao)
	{
		this.roomDao = roomDao;
	}


	public EnteredInfoDao getEnteredInfoDao()
	{
		return enteredInfoDao;
	}

	
	public void setEnteredInfoDao(EnteredInfoDao enteredInfoDao)
	{
		this.enteredInfoDao = enteredInfoDao;
	}


	@Override
	public String save(EnteredInfo entity)throws InnException
	{
		try
		{
			return (String) enteredInfoDao.save(entity);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.save"),e);
		}
	}


	@Override
	public boolean delete(String id) throws InnException
	{
		try
		{
			if(enteredInfoDao.get(id).getIsCheckOut())
			{
				return enteredInfoDao.delete(id);
			}
			return false;
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.delete"),e);
		}
	}

	@Override
	public boolean update(EnteredInfo entity) throws InnException
	{
		try
		{
			return enteredInfoDao.update(entity);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"),e);
		}
	}
	
	
	@Override
	public EnteredInfo getById(String id)throws InnException
	{
		EnteredInfo ei = null;
		try
		{
			ei =  enteredInfoDao.get(id);
			if(ei == null)
			{
				return null;
			}
			
			Double result = (Double)billConsumeDao.executeHQLQuery(InnHQL.SUM_BILLCONSUME_ENTERED_SPECIFIC,id);
			ei.setBillConsume(result==null?0:result);
			
			result = (Double)billPayDao.executeHQLQuery(InnHQL.SUM_BILLPAY_ENTERED_SPECIFIC,id);
			ei.setBillPay(result==null?0:result);
			
			result = (Double)billRoomDao.executeHQLQuery(InnHQL.SUM_BILLROOM_ENTERED_SPECIFIC,id);
			ei.setBillRoom(result==null?0:result);
			
			return ei;
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}


	@Override
	public JuiPaginationSupport<EnteredInfo> juiPageFind(JuiSortingCombineFilter jscf) throws InnException
	{
		try
		{
			List<Object> values = new ArrayList<Object>();
			String filtersHql = JuiElementFilter.juiElementFiltersToHQL(jscf.getFilters(),values,EnteredInfo.class);
			String hql ;
			if(StringUtil.checkNotEmpty(filtersHql))
			{
				
				hql =  InnHQL.PAGINATION_ENTEREDINFO + "where " + filtersHql  + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
			}
			else
			{
				hql =  InnHQL.PAGINATION_ENTEREDINFO + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
				
			}
			
			return enteredInfoDao.juiPageFind(jscf.getPageNum(),jscf.getRowsPerPage(),hql,values);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}


	@Override
	public boolean enterIn(EnteredInfo entity,int payWay,double money,int enterDays)throws InnException
	{
		try
		{
			Room room = roomDao.get(entity.getRoom().getId());
			//如果发现要换的房间已经被删除了。或者房间不是干净房
			if(room == null || room.getState() != InnConstant.T_ROOM_STATE_CLEAN)
			{
				return false;
			}
			entity.setIsCheckOut(false);
			
			//小时房的退房时间设置。
			if(entity.getIsHourRoom())
			{
				Calendar now=Calendar.getInstance(TimeZone.getTimeZone(InnConstant.TIME_ZONE)); 
				int nowHour = now.get(Calendar.HOUR_OF_DAY);
				// 如果现在的时间已经超过了 小时房截至入住时间。 将退房时间设置为小时房结束时间。
				if(nowHour >= (HOURROOM_END -InnConstant.HOUR_ROOM_HOURS))
				{
					now.set(Calendar.HOUR_OF_DAY,HOURROOM_END);
					now.set(Calendar.SECOND,0);
					now.set(Calendar.MINUTE,0);
					entity.setOutTime(DateTimeUtil.convertCalendarToInnDateTimeFormat(now));
				}
				// 当前时间加上 4 小时。
				else
				{
					entity.setOutTime(DateTimeUtil.convertNowToInnDateTimeFormat(0,InnConstant.HOUR_ROOM_HOURS,0));
				}
				
			}
			else
			{
				entity.setOutTime(DateTimeUtil.convertNowToInnDateFormat(enterDays));
			}
			//保存入住信息。
			String eid = (String)enteredInfoDao.save(entity);
			entity.setId(eid);
			//设置房间状态
			roomDao.executeUpdate(InnHQL.ENTERIN_UPDATE_ROOM,entity.getId(),entity.getRoom().getId());
			//添加首次入账。
			BillPay bp = new BillPay();
			bp.setBill(money);
			bp.setDescription(InnConstant.BILL_ENTERIN);
			bp.setPayWay(payWay);
			bp.setRoom(room);
			bp.setEnteredInfo(entity);
			billPayDao.save(bp);
			return true;
			
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.save"),e);
		}
	}


	@Override
	public boolean enteredSwitchRoom(String newRoomId,String oldRoomId, String enterInfoId) throws InnException
	{
		try
		{
			Room room = roomDao.get(newRoomId);
			EnteredInfo ei = enteredInfoDao.get(enterInfoId);
			
			//如果发现要换的房间已经被删除了。或者房间不是干净房
			//入住信息被删除，入住以退房
			//入住信息不再指定的旧房间
			if(room == null || room.getState() != InnConstant.T_ROOM_STATE_CLEAN ||
					ei==null || ei.getIsCheckOut() ||
					ei.getRoom() == null || !ei.getRoom().getId().equals(oldRoomId) )
			{
				return false;
			}
			
			roomDao.executeUpdate(InnHQL.NEWROOM_SWITCH,enterInfoId,newRoomId);
			enteredInfoDao.executeUpdate(InnHQL.ENTERED_SWITCH,newRoomId,enterInfoId);
			roomDao.executeUpdate(InnHQL.OLDROOM_SWITCH,oldRoomId);
			return true; 
			
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"),e);
		}
	}
	

	@Override
	public boolean enteredCheckOut(String id,int payWay,double money) throws InnException
	{
		try
		{
			EnteredInfo ei =  enteredInfoDao.get(id);
			
			//入住信息被删，房间被删，已经退房，
			if(ei == null || ei.getRoom() == null || ei.getIsCheckOut() )
			{
				return false;
			}
			
			
			Double billConsume = (Double)billConsumeDao.executeHQLQuery(InnHQL.SUM_BILLCONSUME_ENTERED_SPECIFIC,id);
			billConsume = billConsume==null?0:billConsume;
			
			Double billPay = (Double)billPayDao.executeHQLQuery(InnHQL.SUM_BILLPAY_ENTERED_SPECIFIC,id);
			billPay = billPay==null?0:billPay;
			
			Double billRoom = (Double)billRoomDao.executeHQLQuery(InnHQL.SUM_BILLROOM_ENTERED_SPECIFIC,id);
			billRoom = billRoom==null?0:billRoom;
			
			//只允许1 的误差。
			double result = billPay -billRoom-billConsume;
			if((result + money) >1 ||(money + result)>1)
			{
				return false;
			}
			
			//添加 结账
			BillPay bp = new BillPay();
			bp.setBill(money);
			bp.setDescription(InnConstant.BILL_CHECKOUT);
			bp.setPayWay(payWay);
			bp.setRoom(ei.getRoom());
			bp.setEnteredInfo(ei);
			billPayDao.save(bp);
			
			//更新入住信息
			enteredInfoDao.executeUpdate(InnHQL.ENTERED_CHEKOUT,DateTimeUtil.convertNowToInnDateTimeFormat(0,0,0),id);
			//更新房间。
			roomDao.executeUpdate(InnHQL.ROOM_CHEKOUT,ei.getRoom().getId());
			return true;
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"),e);
		}
	}
	
	@Override
	public boolean enteredModify(EnteredInfo entity) throws InnException
	{
		try
		{
			return enteredInfoDao.executeUpdate(InnHQL.ENTERED_MODIFY,entity.getName(),entity.getPhoneNumber(),
					entity.getNumberPeople(),entity.getDescription(),entity.getId());
			
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"),e);
		}
	}
	
	@Override
	public boolean enteredModifyDescription(String id,String description) throws InnException
	{
		try
		{
			return enteredInfoDao.executeUpdate(InnHQL.ENTERED_MODIFY_DESCRIPTION,description,id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"),e);
		}
	}

	@Override
	public boolean enteredContinue(String enterInfoId, int days) throws InnException
	{
		try
		{
			EnteredInfo ei = enteredInfoDao.get(enterInfoId);
			if(ei==null || ei.getIsCheckOut())
			{
				return false;
			}
			
			//小时房
			if(ei.getIsHourRoom())
			{
				Calendar checkOut= DateTimeUtil.convertInnDateTimeStringToCalendar(ei.getOutTime());
				int checkOutHour = checkOut.get(Calendar.HOUR_OF_DAY);
				// 如果现在的小时数为小时房结束时间。
				if(checkOutHour >= HOURROOM_END)
				{
					return false;
				}
				// 最多续住到 结束时间。
				else if(checkOutHour >= (HOURROOM_END- InnConstant.HOUR_ROOM_HOURS))
				{
					checkOut.set(Calendar.HOUR_OF_DAY,HOURROOM_END);
					checkOut.set(Calendar.SECOND,0);
					checkOut.set(Calendar.MINUTE,0);
					return enteredInfoDao.executeUpdate(InnHQL.ENTERED_CONTINUE,DateTimeUtil.convertCalendarToInnDateTimeFormat(checkOut),enterInfoId);
				}
				else
				{
					return enteredInfoDao.executeUpdate(InnHQL.ENTERED_CONTINUE,DateTimeUtil.convertDateTimeStringToInnDateTimeFormat(ei.getOutTime(),0,4,0),enterInfoId);
				}
			}
			//普通房。
			return enteredInfoDao.executeUpdate(InnHQL.ENTERED_CONTINUE,DateTimeUtil.convertDateStringToInnDateFormat(ei.getOutTime(),days),enterInfoId);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"),e);
		}
	}

	
	public static int GEN_DELAY_MINUTES = 15;
	public static int GEN_MORNING = 6;
	public static int GEN_NOON = 13;
	public static int GEN_AFTTERNOON = 18;
	
	public static int HOURROOM_END = 22;
	
	private void saveBillRoom(EnteredInfo ei ,double price,int billType,String genDate) throws InnException
	{
		
		BillRoom br = new BillRoom();
		br.setEnteredInfo(ei);
		br.setRoom(ei.getRoom());
		br.setBill(price);
		br.setRoomExpenseType(billType);
		br.setGenDate(genDate);
		billRoomDao.save(br);
	}
	
	private void updateRecentBillRoom(EnteredInfo ei,double price,int type) throws InnException
	{
		List<BillRoom> list =  billRoomDao.findRecent(InnHQL.ENTEREDINFO_RECENT_BILLROOM, 1,ei.getId());
		if(list.size() >0)
		{
			BillRoom br2 = list.get(0);
			if(br2.getRoomExpenseType()!= type)
			{
				br2.setRoomExpenseType(type);
				br2.setBill(price);
				billRoomDao.update(br2);
			}
		}
	}
	
	private void genHourRoom(EnteredInfo ei,Calendar now,Calendar enteredTime,int dayCount) throws InnException
	{
		
		int nowHour =  now.get(Calendar.HOUR_OF_DAY);
		int nowMinutes = now.get(Calendar.MINUTE);
		
		Calendar checkOutTime = Calendar.getInstance(TimeZone.getTimeZone(InnConstant.TIME_ZONE));
		try
		{
			DateFormat format  = new SimpleDateFormat(InnConstant.INN_DATE_TIME_FORMAT);
			checkOutTime.setTime(format.parse(ei.getOutTime()));
		}
		catch (ParseException e)
		{
			DateFormat format  = new SimpleDateFormat(InnConstant.INN_DATE_FORMAT);
			try
			{
				checkOutTime.setTime(format.parse(ei.getOutTime()));
			}
			catch (ParseException e1)
			{
				new InnException(InnErrorsUtil.getInnError("inn.service.genRoomBill.timeParse"),e);
			}
		}
		
		//入住天数超过一天，退房期限已经过了，   或者现在是[22:15--23:59]，均改作正常入住
		if(dayCount > 0 ||now.after(checkOutTime)|| (nowHour > HOURROOM_END || (nowHour==HOURROOM_END&&nowMinutes>=GEN_DELAY_MINUTES)))
		{
			//更新为非小时房,退房时间改为 今天。并删除所有房费。交由正常方式处理。
			ei.setIsHourRoom(false);
			ei.setOutTime(DateTimeUtil.convertNowToInnDateFormat(0));
			enteredInfoDao.update(ei);
			billRoomDao.executeUpdate(InnHQL.ENTEDINFO_DELETE_BILLROOM,ei.getId());
			//正常方式处理
			if(dayCount > 0)
			{
				genNotTodayEnterIn(ei,now,enteredTime,dayCount);
			}
			else
			{
				genTodayEnterIn(ei,now,enteredTime);
			}
			return;
		}
		
		
		//小时房处理
		int enteredHour = now.get(Calendar.HOUR_OF_DAY);
		int enteredMinutes= enteredTime.get(Calendar.MINUTE);
		int minuteDistance = DateTimeUtil.countMinuteDistance(enteredMinutes, enteredHour, nowMinutes, nowHour);
	
		//还未到 15分钟 不产生费用。
		if(minuteDistance < GEN_DELAY_MINUTES)
		{
			return; 
		}
		
		
		int billCount = ((Long) billRoomDao.executeHQLQuery(InnHQL.ENTEREDINFO_NOW_BILLROOM_COUNT, ei.getId())).intValue();
		int shouldBillCount = 1;
		shouldBillCount += (minuteDistance- GEN_DELAY_MINUTES) /(InnConstant.HOUR_ROOM_HOURS*60);
		
		
		//补上缺少的房费记录。
		for(int i = 1 ; i <= (shouldBillCount-billCount) ;i ++)
		{
			saveBillRoom(ei ,ei.getRoom().getRoomType().getHourPrice(),
					InnConstant.T_BILLPAY_EXPENSE_HOUR,DateTimeUtil.convertNowToInnDateFormat(0));
		}
		
		
	}
	
	private void genNotTodayEnterIn(EnteredInfo ei,Calendar now,Calendar enteredTime,int dayCount) throws InnException
	{
		int nowHour = now.get(Calendar.HOUR_OF_DAY);
		int nowMinute = now.get(Calendar.MINUTE);
		
		//[13:15--18:15)
		int nowTimeType = 1;
		//[6:00--13:15)
		if(nowHour < GEN_NOON ||(nowHour==GEN_NOON&&nowMinute<GEN_DELAY_MINUTES))
		{
			nowTimeType = 0;
		}
		//[18:15--23:59]
		else if(nowHour>GEN_AFTTERNOON ||(nowHour == GEN_AFTTERNOON && nowMinute >= GEN_DELAY_MINUTES))
		{
			nowTimeType = 2;
			
		}
		
		
		int billCount = ((Long) billRoomDao.executeHQLQuery(InnHQL.ENTEREDINFO_NOW_BILLROOM_COUNT, ei.getId())).intValue();
		
		//保证有dayCount个房费记录。并且全部是全天房费。
		if(billCount <= dayCount)
		{
			//更新最近一条。
			updateRecentBillRoom(ei,ei.getRoom().getRoomType().getDayPrice(),InnConstant.T_BILLPAY_EXPENSE_ALLDAY);
			
			//补上缺少的房费记录。
			for(int i = 1 ; i <= (dayCount-billCount) ;i ++)
			{
				saveBillRoom(ei ,ei.getRoom().getRoomType().getDayPrice(),
						InnConstant.T_BILLPAY_EXPENSE_ALLDAY,DateTimeUtil.convertNowToInnDateFormat(-i));
			}
		}
		
		// 针对今天的房费是否已经生成
		boolean genToday = billCount > dayCount ? true : false;

		// 半天房费的情况 [13:15--18:15)
		if (nowTimeType == 1)
		{
			// 半天房费
			if (!genToday)
			{
				saveBillRoom(ei ,ei.getRoom().getRoomType().getDayPrice()/2,
						InnConstant.T_BILLPAY_EXPENSE_HALFDAY,DateTimeUtil.convertNowToInnDateFormat(0));
				
			}
		}
		// 全天房费[18:15--23:59]
		else if (nowTimeType == 2)
		{
			// 全天房费
			if (genToday)
			{
				updateRecentBillRoom(ei,ei.getRoom().getRoomType().getDayPrice(),InnConstant.T_BILLPAY_EXPENSE_ALLDAY);
			}
			else
			{
				saveBillRoom(ei ,ei.getRoom().getRoomType().getDayPrice(),
						InnConstant.T_BILLPAY_EXPENSE_ALLDAY,DateTimeUtil.convertNowToInnDateFormat(0));
			}
		}
	}
	
	private void genTodayEnterIn(EnteredInfo ei,Calendar now,Calendar enteredTime)throws InnException
	{
		int enteredHour = enteredTime.get(Calendar.HOUR_OF_DAY);
		int enteredMinute = enteredTime.get(Calendar.MINUTE);
		int nowHour = now.get(Calendar.HOUR_OF_DAY);
		int nowMinute = now.get(Calendar.MINUTE);
		int minuteDistance = DateTimeUtil.countMinuteDistance(enteredMinute, enteredHour, nowMinute, nowHour);

		//入住数小于15 分钟直接返回。
		if(minuteDistance < GEN_DELAY_MINUTES)
		{
			return ;
		}
		
		//[13:15--18:15)
		int nowTimeType = 1;
		//[6:00--13:15)
		if(nowHour < GEN_NOON ||(nowHour==GEN_NOON&&nowMinute<GEN_DELAY_MINUTES))
		{
			nowTimeType = 0;
		}
		//[18:15--23:59]
		else if(nowHour>GEN_AFTTERNOON ||(nowHour == GEN_AFTTERNOON && nowMinute >= GEN_DELAY_MINUTES))
		{
			nowTimeType = 2;
		}
		
		//入住时间未超过延迟时间
		boolean minuteDistanceMoreThanDelayMinutes = false;
		//入住时间超过延迟时间
		if(minuteDistance > GEN_DELAY_MINUTES)
		{
			minuteDistanceMoreThanDelayMinutes = true;
		}
		
		// 当前时间[6:00--13:15) 且超过15 分钟。
		// 当前时间[13:15--18:15) 入住时间 [13:00--17:59],且超过15分钟
		// 当前时间[13:15--18:15) 入住时间 [6:00--12:59],且未超过15分钟
		if ((nowTimeType == 0 && minuteDistanceMoreThanDelayMinutes)
				|| (nowTimeType == 1 && enteredHour >= GEN_NOON && minuteDistanceMoreThanDelayMinutes)
				|| (nowTimeType == 1 && enteredHour < GEN_NOON && !minuteDistanceMoreThanDelayMinutes))
		{
			int billCount = ((Long) billRoomDao.executeHQLQuery(InnHQL.ENTEREDINFO_NOW_BILLROOM_COUNT, ei.getId())).intValue();
			// 半天房费
			if (billCount == 0)
			{
				saveBillRoom(ei ,ei.getRoom().getRoomType().getDayPrice()/2,
						InnConstant.T_BILLPAY_EXPENSE_HALFDAY,DateTimeUtil.convertNowToInnDateFormat(0));
			}

		}
		// 当前时间[13:15--18:15) 入住时间 [6:00--12:59],且超过15分钟
		// 当前时间[18:15--] 入住时间 超过15分钟
		else if ((nowTimeType == 1 && enteredHour < GEN_NOON && minuteDistanceMoreThanDelayMinutes)
				|| (nowTimeType == 2 && minuteDistanceMoreThanDelayMinutes))
		{
			int billCount = ((Long) billRoomDao.executeHQLQuery(InnHQL.ENTEREDINFO_NOW_BILLROOM_COUNT, ei.getId())).intValue();
			// 全天房费
			if (billCount == 0)
			{
				saveBillRoom(ei ,ei.getRoom().getRoomType().getDayPrice(),
						InnConstant.T_BILLPAY_EXPENSE_ALLDAY,DateTimeUtil.convertNowToInnDateFormat(0));
			}
			else
			{
				updateRecentBillRoom(ei,ei.getRoom().getRoomType().getDayPrice(),InnConstant.T_BILLPAY_EXPENSE_ALLDAY);
			}

		}
		
		
	}
	
	@Override
	public void genBillRoom(EnteredInfo ei) throws InnException
	{
		
		//房间和入住信息关联错误，将不会做房费生成。
		if(ei.getRoom() == null || !ei.getRoom().getEnteredId().equals(ei.getId()))
		{
			return;
		}
		
		Calendar enteredTime = Calendar.getInstance(TimeZone.getTimeZone(InnConstant.TIME_ZONE));
		DateFormat format  = new SimpleDateFormat(InnConstant.INN_DATE_TIME_FORMAT);
		try
		{
			enteredTime.setTime(format.parse(ei.getEnteredTime()));
		}
		catch (ParseException e)
		{
			new InnException(InnErrorsUtil.getInnError("inn.service.genRoomBill.timeParse"),e);
		}
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone(InnConstant.TIME_ZONE));
		int dayCount =DateTimeUtil.countDayDistance(enteredTime, now);

		// 如果是凌晨 到 GEN_MORNING 时间段入住的 天数差距要加一
		int enteredHour = enteredTime.get(Calendar.HOUR_OF_DAY);
		if (enteredHour >= 0 && enteredHour < GEN_MORNING)
		{
			dayCount += 1;
		}

		//小时房生成。
		if(ei.getIsHourRoom())
		{
			genHourRoom(ei,now,enteredTime,dayCount);
		}
		// 入住天数超过 1 那么以当前时间来算房费
		else if (dayCount > 0)
		{
			genNotTodayEnterIn( ei,now,enteredTime,dayCount);
		}
		// 入住天数为0 以当前时间和入住时间一起来算房费。
		else
		{
			genTodayEnterIn(ei,now,enteredTime);
		}

	}

}
