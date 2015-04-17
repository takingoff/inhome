package tang.li.inn.infrastructure.util;

import java.util.List;

public class InnConstant
{

public static String TIME_ZONE = "GMT+8:00";
public static String INN_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
public static String INN_DATE_FORMAT = "yyyy-MM-dd";
public static int HOUR_ROOM_HOURS=4;

	
/*数据库字段可选值*/	
public static int T_ROOM_STATE_CLEAN;
public static int T_ROOM_STATE_ENTERED;
public static int T_ROOM_STATE_DIRTY;
public static int T_ROOM_STATE_UNVALIABLE;
public static int T_BILLPAY_PAYWAY_BANK=1;
public static int T_BILLPAY_PAYWAY_CASH=2;
public static int T_BILLPAY_PAYWAY_OTHER=3;
public static int T_BILLPAY_EXPENSE_ALLDAY=1;
public static int T_BILLPAY_EXPENSE_HALFDAY=2;
public static int T_BILLPAY_EXPENSE_HOUR=3;

public static int T_ORDER_STATE_NORMAL=1;
public static int T_ORDER_STATE_EXPIER=2;
public static int T_ORDER_STATE_GIVEUP=3;
public static int T_ORDER_STATE_USED=4;
/*vo 提示*/
public static String VO_INVALIDATE = "字段无效";

public static String ROOMVO_STATE_CLEAN;
public static String ROOMVO_STATE_ENTERED;
public static String ROOMVO_STATE_DIRTY;
public static String ROOMVO_STATE_UNVALIABLE;
public static String BILLPAYVO_PAYWAY_CASH = "现金";
public static String BILLPAYVO_PAYWAY_BANK = "银行卡";
public static String BILLPAYVO_PAYWAY_OTHER = "其他";
public static String BILLROOMVO_EXPENSE_ALLDAY = "全天房费";
public static String BILLROOMVO_EXPENSE_HALFDAY = "半天房费";
public static String BILLROOMVO_EXPENSE_HOUR = "小时房费";

public static String ENTEREDINFOVO_CHECKOUT = "已退";
public static String ENTEREDINFOVO_NOT_CHECKOUT = "未退";
public static String ENTEREDINFOVO_NOT_HOURROOM = "普通入住";
public static String ENTEREDINFOVO_HOURROOM = "小时房入住";
	
public static String ORDERVO_GIVEUPSTATE = "放弃入住";
public static String ORDERVO_EXPIERSTATE = "已过期";
public static String ORDERVO_NORMALSTATE = "有效状态";
public static String ORDERVO_USEDLSTATE = "已入住";

	
/*数据库表的字段名*/
public static String T_STAFF_NAME;
public static String T_ROOMTYPE_NAME;
public static String T_ROOM_NAME;
public static String T_ROOM_ROOMTYPEID;
public static String T_INNCONTAINER_KEY;


/*说明信息*/
public static String LOGIN_STAFF_NOT_EXIST;
public static String LOGIN_STAFF_PSSWORD_ERROR;
public static String AUTHCODE_ERROR;
public static String LOGIN_STAFF_SUCCESS;
public static String ACCESS_EXCEPTION;
public static String LOGIN_TIME_OUT; 
public static String ADD_SUCCESS; 
public static String ADD_FAILED; 
public static String ADD_FAILED_NAME_EXISTED; 
public static String MODIFY_SUCCESS; 
public static String MODIFY_FAILED; 
public static String MODIFY_FAILED_NAME_EXISTED; 
public static String DELETE_SUCCESS; 
public static String DELETE_FAILED; 
public static String DELETE_ROOM_SUCCESS; 
public static String DELETE_ROOMTYPE_SUCCESS; 
public static String LIST_EXCEPTION; 
public static String NAME_VALIDATE_GOOD; 
public static String NAME_VALIDATE_ERROR; 
public static String NAME_VALIDATE_EXCEPTION; 
public static String OPERATE_FAILED_TERMINATE = "操作发生错误，被终止！请刷新重试"; 
public static String OPERATE_SUCCESS = "操作成功！"; 
public static String OPERATE_FAILD = "操作失败！"; 
public static String OPERATE_EXCEPTION = "操作异常！"; 
public static String CHECKOUT_FAILED = "退房失败，可能是账目发生变化，请重试"; 
public static String CONTINUE_FAILED = "续住失败"; 
public static String ELETE_ENTEREDIFNO_FINISH = "删除操作完成，未退房的的入住信息不能删除！"; 

public static String BILL_ENTERIN = "**入住首次入账**"; 
public static String BILL_CHECKOUT = "**退房结算结果**"; 



/*开关*/
public static boolean OPEN_AUTH_CODE;
public static boolean OPEN_ACCESS_INTERCEPTOR;


/*Exclude interceptor*/
public static List<String> EXCLUDE_INTERCEPTOR;


/*login路径*/
public static String LOGIN_PATH;


/*验证码字体*/
public static String AUTH_CODE_FONT;



public static String FILTER_ORDER_BY;









public static String getNAME_VALIDATE_GOOD()
{
	return NAME_VALIDATE_GOOD;
}


public static void setNAME_VALIDATE_GOOD(String nAME_VALIDATE_GOOD)
{
	NAME_VALIDATE_GOOD = nAME_VALIDATE_GOOD;
}


public static String getNAME_VALIDATE_ERROR()
{
	return NAME_VALIDATE_ERROR;
}


public static void setNAME_VALIDATE_ERROR(String nAME_VALIDATE_ERROR)
{
	NAME_VALIDATE_ERROR = nAME_VALIDATE_ERROR;
}


public static String getNAME_VALIDATE_EXCEPTION()
{
	return NAME_VALIDATE_EXCEPTION;
}


public static void setNAME_VALIDATE_EXCEPTION(String nAME_VALIDATE_EXCEPTION)
{
	NAME_VALIDATE_EXCEPTION = nAME_VALIDATE_EXCEPTION;
}


public static int getT_ROOM_STATE_CLEAN()
{
	return T_ROOM_STATE_CLEAN;
}


public static int getT_ROOM_STATE_ENTERED()
{
	return T_ROOM_STATE_ENTERED;
}


public static int getT_ROOM_STATE_DIRTY()
{
	return T_ROOM_STATE_DIRTY;
}


public static int getT_ROOM_STATE_UNVALIABLE()
{
	return T_ROOM_STATE_UNVALIABLE;
}


public static String getROOMVO_STATE_CLEAN()
{
	return ROOMVO_STATE_CLEAN;
}


public static String getROOMVO_STATE_ENTERED()
{
	return ROOMVO_STATE_ENTERED;
}


public static String getROOMVO_STATE_DIRTY()
{
	return ROOMVO_STATE_DIRTY;
}


public static String getROOMVO_STATE_UNVALIABLE()
{
	return ROOMVO_STATE_UNVALIABLE;
}


public static String getT_STAFF_NAME()
{
	return T_STAFF_NAME;
}


public static String getT_ROOMTYPE_NAME()
{
	return T_ROOMTYPE_NAME;
}


public static String getT_ROOM_NAME()
{
	return T_ROOM_NAME;
}


public static String getT_ROOM_ROOMTYPEID()
{
	return T_ROOM_ROOMTYPEID;
}


public static String getT_INNCONTAINER_KEY()
{
	return T_INNCONTAINER_KEY;
}


public static String getLOGIN_STAFF_NOT_EXIST()
{
	return LOGIN_STAFF_NOT_EXIST;
}


public static String getLOGIN_STAFF_PSSWORD_ERROR()
{
	return LOGIN_STAFF_PSSWORD_ERROR;
}


public static String getAUTHCODE_ERROR()
{
	return AUTHCODE_ERROR;
}


public static String getLOGIN_STAFF_SUCCESS()
{
	return LOGIN_STAFF_SUCCESS;
}


public static String getACCESS_EXCEPTION()
{
	return ACCESS_EXCEPTION;
}


public static String getLOGIN_TIME_OUT()
{
	return LOGIN_TIME_OUT;
}


public static String getADD_SUCCESS()
{
	return ADD_SUCCESS;
}


public static String getADD_FAILED()
{
	return ADD_FAILED;
}


public static String getADD_FAILED_NAME_EXISTED()
{
	return ADD_FAILED_NAME_EXISTED;
}


public static String getMODIFY_SUCCESS()
{
	return MODIFY_SUCCESS;
}


public static String getMODIFY_FAILED()
{
	return MODIFY_FAILED;
}


public static String getMODIFY_FAILED_NAME_EXISTED()
{
	return MODIFY_FAILED_NAME_EXISTED;
}


public static String getDELETE_SUCCESS()
{
	return DELETE_SUCCESS;
}


public static String getDELETE_FAILED()
{
	return DELETE_FAILED;
}


public static String getDELETE_ROOM_SUCCESS()
{
	return DELETE_ROOM_SUCCESS;
}


public static String getDELETE_ROOMTYPE_SUCCESS()
{
	return DELETE_ROOMTYPE_SUCCESS;
}


public static String getLIST_EXCEPTION()
{
	return LIST_EXCEPTION;
}


public static boolean isOPEN_AUTH_CODE()
{
	return OPEN_AUTH_CODE;
}


public static boolean isOPEN_ACCESS_INTERCEPTOR()
{
	return OPEN_ACCESS_INTERCEPTOR;
}


public static List<String> getEXCLUDE_INTERCEPTOR()
{
	return EXCLUDE_INTERCEPTOR;
}


public static String getLOGIN_PATH()
{
	return LOGIN_PATH;
}


public static String getAUTH_CODE_FONT()
{
	return AUTH_CODE_FONT;
}


public static String getFILTER_ORDER_BY()
{
	return FILTER_ORDER_BY;
}


public static void setROOM_NAME_VALIDATE_GOOD(String rOOM_NAME_VALIDATE_GOOD)
{
	NAME_VALIDATE_GOOD = rOOM_NAME_VALIDATE_GOOD;
}


public static void setROOM_NAME_VALIDATE_ERROR(String rOOM_NAME_VALIDATE_ERROR)
{
	NAME_VALIDATE_ERROR = rOOM_NAME_VALIDATE_ERROR;
}


public static void setROOM_NAME_VALIDATE_EXCEPTION(String rOOM_NAME_VALIDATE_EXCEPTION)
{
	NAME_VALIDATE_EXCEPTION = rOOM_NAME_VALIDATE_EXCEPTION;
}


public static void setOPEN_ACCESS_INTERCEPTOR(boolean oPEN_ACCESS_INTERCEPTOR)
{
	OPEN_ACCESS_INTERCEPTOR = oPEN_ACCESS_INTERCEPTOR;
}


public static void setDELETE_ROOM_SUCCESS(String dELETE_ROOM_SUCCESS)
{
	DELETE_ROOM_SUCCESS = dELETE_ROOM_SUCCESS;
}


public static void setDELETE_ROOMTYPE_SUCCESS(String dELETE_ROOMTYPE_SUCCESS)
{
	DELETE_ROOMTYPE_SUCCESS = dELETE_ROOMTYPE_SUCCESS;
}


public static void setT_ROOM_ROOMTYPEID(String t_ROOM_ROOMTYPEID)
{
	T_ROOM_ROOMTYPEID = t_ROOM_ROOMTYPEID;
}


public static void setLIST_EXCEPTION(String lIST_EXCEPTION)
{
	LIST_EXCEPTION = lIST_EXCEPTION;
}


public static void setMODIFY_FAILED_NAME_EXISTED(String mODIFY_FAILED_NAME_EXISTED)
{
	MODIFY_FAILED_NAME_EXISTED = mODIFY_FAILED_NAME_EXISTED;
}


public static void setADD_FAILED_NAME_EXISTED(String aDD_FAILED_NAME_EXISTED)
{
	ADD_FAILED_NAME_EXISTED = aDD_FAILED_NAME_EXISTED;
}


public static void setADD_SUCCESS(String aDD_SUCCESS)
{
	ADD_SUCCESS = aDD_SUCCESS;
}


public static void setADD_FAILED(String aDD_FAILED)
{
	ADD_FAILED = aDD_FAILED;
}


public static void setMODIFY_SUCCESS(String mODIFY_SUCCESS)
{
	MODIFY_SUCCESS = mODIFY_SUCCESS;
}


public static void setMODIFY_FAILED(String mODIFY_FAILED)
{
	MODIFY_FAILED = mODIFY_FAILED;
}


public static void setDELETE_SUCCESS(String dELETE_SUCCESS)
{
	DELETE_SUCCESS = dELETE_SUCCESS;
}


public static void setDELETE_FAILED(String dELETE_FAILED)
{
	DELETE_FAILED = dELETE_FAILED;
}


public static void setT_ROOMTYPE_NAME(String t_ROOMTYPE_NAME)
{
	T_ROOMTYPE_NAME = t_ROOMTYPE_NAME;
}


public static void setT_ROOM_NAME(String t_ROOM_NAME)
{
	T_ROOM_NAME = t_ROOM_NAME;
}


public static void setT_ROOM_STATE_CLEAN(int t_ROOM_STATE_CLEAN)
{
	T_ROOM_STATE_CLEAN = t_ROOM_STATE_CLEAN;
}


public static void setT_ROOM_STATE_ENTERED(int t_ROOM_STATE_ENTERED)
{
	T_ROOM_STATE_ENTERED = t_ROOM_STATE_ENTERED;
}


public static void setT_ROOM_STATE_DIRTY(int t_ROOM_STATE_DIRTY)
{
	T_ROOM_STATE_DIRTY = t_ROOM_STATE_DIRTY;
}


public static void setT_ROOM_STATE_UNVALIABLE(int t_ROOM_STATE_UNVALIABLE)
{
	T_ROOM_STATE_UNVALIABLE = t_ROOM_STATE_UNVALIABLE;
}


public static void setROOMVO_STATE_CLEAN(String rOOMVO_STATE_CLEAN)
{
	ROOMVO_STATE_CLEAN = rOOMVO_STATE_CLEAN;
}


public static void setROOMVO_STATE_ENTERED(String rOOMVO_STATE_ENTERED)
{
	ROOMVO_STATE_ENTERED = rOOMVO_STATE_ENTERED;
}


public static void setROOMVO_STATE_DIRTY(String rOOMVO_STATE_DIRTY)
{
	ROOMVO_STATE_DIRTY = rOOMVO_STATE_DIRTY;
}


public static void setROOMVO_STATE_UNVALIABLE(String rOOMVO_STATE_UNVALIABLE)
{
	ROOMVO_STATE_UNVALIABLE = rOOMVO_STATE_UNVALIABLE;
}


public static void setFILTER_ORDER_BY(String fILTER_ORDER_BY)
{
	FILTER_ORDER_BY = fILTER_ORDER_BY;
}


public static void setLOGIN_PATH(String lOGIN_PATH)
{
	LOGIN_PATH = lOGIN_PATH;
}




public static void setT_INNCONTAINER_KEY(String t_INNCONTAINER_KEY)
{
	T_INNCONTAINER_KEY = t_INNCONTAINER_KEY;
}


public static void setAUTH_CODE_FONT(String aUTH_CODE_FONT)
{
	AUTH_CODE_FONT = aUTH_CODE_FONT;
}


public static void setEXCLUDE_INTERCEPTOR(List<String> eXCLUDE_INTERCEPTOR)
{
	EXCLUDE_INTERCEPTOR = eXCLUDE_INTERCEPTOR;
}


public static void setLOGIN_TIME_OUT(String lOGIN_TIME_OUT)
{
	LOGIN_TIME_OUT = lOGIN_TIME_OUT;
}


public static void setOPEN_AUTH_CODE(boolean oPEN_AUTH_CODE)
{
	OPEN_AUTH_CODE = oPEN_AUTH_CODE;
}


public static void setACCESS_EXCEPTION(String aCCESS_EXCEPTION)
{
	ACCESS_EXCEPTION = aCCESS_EXCEPTION;
}


public static void setLOGIN_STAFF_SUCCESS(String lOGIN_STAFF_SUCCESS)
{
	LOGIN_STAFF_SUCCESS = lOGIN_STAFF_SUCCESS;
}


public void setT_STAFF_NAME(String t_STAFF_NAME)
{
	T_STAFF_NAME = t_STAFF_NAME;
}


public static void setLOGIN_STAFF_NOT_EXIST(String lOGIN_STAFF_NOT_EXIST)
{
	LOGIN_STAFF_NOT_EXIST = lOGIN_STAFF_NOT_EXIST;
}


public static void setLOGIN_STAFF_PSSWORD_ERROR(String lOGIN_STAFF_PSSWORD_ERROR)
{
	LOGIN_STAFF_PSSWORD_ERROR = lOGIN_STAFF_PSSWORD_ERROR;
}


public static void setAUTHCODE_ERROR(String lOGIN_STAFF_AUTHCODE_ERROR)
{
	AUTHCODE_ERROR = lOGIN_STAFF_AUTHCODE_ERROR;
}

	
	
}
