 /**   
* projectName: InnDomain
*
* fileName: BillPayService.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午2:55:13 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.bill;

import java.util.List;

import tang.li.inn.entity.bill.BillPay;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public interface BillPayService
{

	/**
	 * @param entity // 应当包含 enteredInfo.id
	 * @return 返回新增id。
	 * @throws InnException
	 */
	public String save(BillPay entity)throws InnException;
	
	/**
	 * @param id
	 * @return 是否删除成功
	 * @throws InnException
	 */
	public boolean delete(List<String> id)throws InnException;
	
	/**
	 * @param entity
	 * @return 是否修改成功
	 * @throws InnException
	 */
	public boolean modifyDescription(String id,String description)throws InnException;
	
	/**
	 * @param id
	 * @return
	 * @throws InnException
	 */
	public BillPay getById(String id)throws InnException;
	
	
	public JuiPaginationSupport<BillPay> juiPageFind(JuiSortingCombineFilter jscf,String enteredId) throws InnException;
	
	
	
	
}