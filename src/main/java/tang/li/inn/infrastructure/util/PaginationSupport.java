package tang.li.inn.infrastructure.util;

import java.util.List;

/**
 * <strong>分页支持对象</strong><BR/>
 * 
 * @author liping 2010-1-19
 */
public class PaginationSupport<T>
{

	public final static int ROWS_PER_PAGE = 20;// 默认每页20条

	//每页行数 默认值为20
	private int rowsPerPage = ROWS_PER_PAGE;  

	//总共的记录数
	private int totalRows;

	//当前页的开始记录索引
	private int startIndex;


	private List<T> items;


	/**
	 * <strong>最经常使用的构造函数</strong>
	 * 
	 * @param items
	 *            查询结果集合
	 * @param totalRows
	 *            总记录数
	 * @param rowsPerPage
	 *            每页显示条数
	 * @param startIndex
	 *            索引开始计数
	 */
	public PaginationSupport(List<T> items, int totalRows, int rowsPerPage, int startIndex)
	{
		this.setTotalRows(totalRows);
		this.setRowsPerPage(rowsPerPage);
		this.setItems(items);
		this.setStartIndex(startIndex);

	}
	
	/**
	 * 每页显示数使用默认值
	 * @param items
	 * @param totalRows
	 * @param startIndex
	 */
	public PaginationSupport(List<T> items, int totalRows,int startIndex)
	{
		this.setTotalRows(totalRows);
		this.setItems(items);
		this.setStartIndex(startIndex);

	}


	public List<T> getItems()
	{
		return items;
	}

	public void setItems(List<T> items)
	{
		this.items = items;
	}

	
	
	public int getRowsPerPage()
	{
		return rowsPerPage;
	}


	public void setRowsPerPage(int rowsPerPage)
	{
		this.rowsPerPage = rowsPerPage;
	}



	public int getTotalRows()
	{
		return totalRows;
	}


	public void setTotalRows(int totalRows)
	{
		this.totalRows = totalRows;
	}



	public int getStartIndex()
	{
		return startIndex;
	}


	public void setStartIndex(int startIndex)
	{
		this.startIndex = startIndex;
	}


	@Override
	public String toString()
	{
		return "PaginationSupport [pageSize=" + rowsPerPage + ", totalCount=" + totalRows  + ", startIndex=" + startIndex + "]";
	}

}
