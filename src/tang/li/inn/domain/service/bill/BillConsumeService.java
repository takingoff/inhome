 /**   
* projectName: InnDomain
*
* fileName: BillConsumeService.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午2:55:28 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.bill;

import tang.li.inn.entity.bill.BillConsume;
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
public interface BillConsumeService
{
	/**
	 * @param entity //应当包含enteredInfo.id
	 * @return 返回新增id。
	 * @throws InnException
	 */
	public String save(BillConsume entity)throws InnException;
	
	/**
	 * @param id
	 * @return 是否删除成功
	 * @throws InnException
	 */
	public boolean delete(String id)throws InnException;
	
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
	public BillConsume getById(String id)throws InnException;
	
	
	public JuiPaginationSupport<BillConsume> juiPageFind(JuiSortingCombineFilter jscf,String enteredID) throws InnException;
}
