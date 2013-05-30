package cn.haohaowo.common;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * @author Administrator
 *
 */
public class ConditionMap implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2364102948014016901L;

	private String hql;
	private String countHql;
	private HashMap<String, Object> condition;
	private int pageSize = 50;
	private int pageNo;
	
	private HashMap<String, Boolean> initialize;

	public HashMap<String, Boolean> getInitialize() {
		return initialize;
	}

	public void setInitialize(HashMap<String, Boolean> initialize) {
		this.initialize = initialize;
	}

	/**
	 * @return the hql
	 */
	public String getHql() {
		return hql;
	}

	/**
	 * @param hql
	 *            the hql to set
	 */
	public void setHql(String hql) {
		this.hql = hql;
	}

	public HashMap<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(HashMap<String, Object> condition) {
		this.condition = condition;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getCountHql() {
		return countHql;
	}

	public void setCountHql(String countHql) {
		this.countHql = countHql;
	}
	
}
