 /**   
* projectName: InnInfrastructure
*
* fileName: InnHQL.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-25 下午1:53:51 
*
* version : V1.0 
*/
package tang.li.inn.infrastructure.util;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class InnHQL
{
	public static String PAGINATION_ROOMTYPE ;
	public static String PAGINATION_ROOM ;
	public static String PAGINATION_ENTEREDINFO;
	public static String PAGINATION_BILL_PAY;
	public static String PAGINATION_BILL_ROOM;
	public static String PAGINATION_BILL_CONSUME;
	public static String PAGINATION_ORDER = "from Order ";
	
	public static String PAGINATION_ROOM_SWITCHROOM  = "from Room where state = 1 and roomType.id = ? ";
	
	public static String PAGINATION_BILLPAY_ENTERED_SPECIFIC  = "from BillPay where enteredInfo.id = ? ";
	
	public static String PAGINATION_BILLCONSUME_ENTERED_SPECIFIC  = "from BillConsume where enteredInfo.id = ? ";
	
	public static String PAGINATION_BILLROOOM_ENTERED_SPECIFIC  = "from BillRoom where enteredInfo.id = ? ";
	
	
	//查询入账，消费，房费
	public static String SUM_BILLPAY_ENTERED_SPECIFIC  = "select SUM(bill) from BillPay where enteredInfo.id = ? ";
	
	public static String SUM_BILLCONSUME_ENTERED_SPECIFIC  = "select SUM(bill) from BillConsume where enteredInfo.id = ? ";
	
	public static String SUM_BILLROOM_ENTERED_SPECIFIC  = "select SUM(bill) from BillRoom where enteredInfo.id = ? ";
	
	
	//入住时修改房间状态，以及设置enteredId字段
	public static String ENTERIN_UPDATE_ROOM  = "update Room set state = 2 , enteredId = ? where id = ?";
	//入住后修改入住人信息
	public static String ENTERED_MODIFY  = "update EnteredInfo set name = ? , phoneNumber = ? ,numberPeople = ? , description = ? where id = ?";
	//只修改description字段
	public static String ENTERED_MODIFY_DESCRIPTION  = "update EnteredInfo set description = ? where id = ?";
	//续住设置outTime
	public static String ENTERED_CONTINUE  = "update EnteredInfo set outTime = ?  where id = ?";
	//换房关联新房间id
	public static String ENTERED_SWITCH  = "update EnteredInfo set room.id = ?  where id = ?";
	//换房后房间设置状态，更新enteredId字段
	public static String OLDROOM_SWITCH  = "update Room set enteredId = '' , state = 3 where id = ?";
	
	public static String NEWROOM_SWITCH  = "update Room set state = 2 ,enteredId = ? where id = ?";
	//退房 操作和换房差不多
	public static String ROOM_CHEKOUT  = "update Room set enteredId = '' , state = 3 where id = ?";
	//退房 更新outTime字段
	public static String ENTERED_CHEKOUT  = "update EnteredInfo set outTime = ? , isCheckOut = true where id = ?";
	
	
	//billConsume 更新description
	public static String BILLCONSUME_MODIFY_DESCRIPTION  = "update BillConsume set description = ? where id = ?";
	
	//billPay 更新description
	public static String BILLPAY_MODIFY_DESCRIPTION  = "update BillPay set description = ? where id = ?";
	

	
	//查询现在没有退房的入住信息
	public static String ENTEREDINFO_NOW = "from EnteredInfo where isCheckOut = false ";
	//查询指定入住信息的房费记录数
	public static String ENTEREDINFO_NOW_BILLROOM_COUNT = "select count(*) from BillRoom where enteredInfo.id = ? ";
	//查询指定入住信息的房费按时间排序的第一条
	public static String ENTEREDINFO_RECENT_BILLROOM = "from BillRoom where enteredInfo.id = ?  order by genDate DESC ";
	
	//删除入住信息对应的房费，用于房费生成。
	public static String ENTEDINFO_DELETE_BILLROOM ="delete BillRoom where enteredInfo.id = ?";
	
	
	//订单修改。更新字段包括 ，入住天数、入住日期、房间类型、姓名、电话号码、延迟小时数、说明信息、状态。
	public static String ORDER_EXECUTE_MODIFY ="update Order set enteredId = '' , state = 3 where id = ?";
	
	
	
	
	
	
	
	
	
	
	
	
	public static void setPAGINATION_ENTEREDINFO(String pAGINATION_ENTEREDINFO)
	{
		PAGINATION_ENTEREDINFO = pAGINATION_ENTEREDINFO;
	}



	public static void setPAGINATION_BILL_PAY(String pAGINATION_BILL_PAY)
	{
		PAGINATION_BILL_PAY = pAGINATION_BILL_PAY;
	}



	public static void setPAGINATION_BILL_ROOM(String pAGINATION_BILL_ROOM)
	{
		PAGINATION_BILL_ROOM = pAGINATION_BILL_ROOM;
	}



	public static void setPAGINATION_BILL_CONSUME(String pAGINATION_BILL_CONSUME)
	{
		PAGINATION_BILL_CONSUME = pAGINATION_BILL_CONSUME;
	}



	public static void setPAGINATION_ROOMTYPE(String pAGINATION_ROOMTYPE)
	{
		PAGINATION_ROOMTYPE = pAGINATION_ROOMTYPE;
	}



	public static void setPAGINATION_ROOM(String pAGINATION_ROOM)
	{
		PAGINATION_ROOM = pAGINATION_ROOM;
	}

	
}
