package util;

import java.util.Properties;

public class Page {
	Properties p = Env.getInstance();
	public int billPageSize = Integer.parseInt(p.getProperty("billPageSize"));
	public int supplierPageSize = Integer.parseInt(p
			.getProperty("supplierPageSize"));
	public int userPageSize = Integer.parseInt(p.getProperty("userPageSize"));
	// 总页数
	private int totalPageCount = 1;
	// 页面大小，即每页显示记录数
	private int pageSize = 0;
	// 记录总数
	private int recordCount = 0;
	// 当前页号
	private int currPageNo = 1;

	public int getCurrPageNo() {
		if (totalPageCount == 0)
			return 0;
		return currPageNo;
	}

	public void setCurrPageNo(int currPageNo) {
		if (currPageNo > 0)
			this.currPageNo = currPageNo;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0)
			this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		if (recordCount > 0) {
			this.recordCount = recordCount;
		}

	}

	// 设置总页数
	public int getTotalPageCountByRs() {
		if (this.recordCount % this.pageSize == 0)
			this.totalPageCount = this.recordCount / this.pageSize;
		else if (this.recordCount % this.pageSize > 0)
			this.totalPageCount = this.recordCount / this.pageSize + 1;
		else {
			this.totalPageCount = 0;
		}
		return totalPageCount;
	}

	/**
	 * 得到开始记录数
	 * 
	 * @return
	 */
	public int getStartRow() {
		return (currPageNo - 1) * pageSize + 1;
	}

	/**
	 * 得到结束记录数
	 * 
	 * @return
	 */
	public int getEndRow() {
		return currPageNo * pageSize;
	}
}
