package cn.haohaowo.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = 6162556177685197916L;

	// 每页默认显示的记录数
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	// 每页最少记录数
	public static final int MIX_PAGE_SIZE = 5;

	// ~ Properties=====================================================
	/**
	 * Record start number in current page. Start from 0.
	 */
	private int start;
	
	private int pageNo;
	
	private String hql;
	
	private String countHql;
	
	private HashMap<String, Object> condition;

	/**
	 * Records number in one page.
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * Total records number.
	 */
	private long totalCount;

	/**
	 * Records in current page.
	 */
	private List<T> data;
	
	private String url;

	// ~ Constructors==========================================================
	public Page() {
		this(0, 0, DEFAULT_PAGE_SIZE, 0, new ArrayList<T>(0));
	}

	public Page(int start, long totalCount, int pageSize, int pageNo, List<T> data) {
		this.start = start;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.data = data;
	}

	// ~ Methods================================================================
	
	/**
	 * Get record number in one page.
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * Get current page number. Page number start from 1.
	 */
	public int getCurrentPageNo() {
		return (start / pageSize + 1);
	}
	
	/**
	 * Get total record number.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * Get records list in current page.
	 */
	public List<T> getData() {
		return data;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean isNeedPaged() {
		return totalCount > pageSize;
	}

	/**
	 * Get the start index in certain page. Use default page size.
	 */
	public int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	 * Get the start index in certain page.
	 */
	public int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
	
	public int getStartOfPage() {
		return (pageNo - 1) * pageSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public String getCountHql() {
		return countHql;
	}

	public void setCountHql(String countHql) {
		this.countHql = countHql;
	}

	public HashMap<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(HashMap<String, Object> condition) {
		this.condition = condition;
	}
}
