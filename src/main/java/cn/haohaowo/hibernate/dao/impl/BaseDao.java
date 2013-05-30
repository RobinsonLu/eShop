package cn.haohaowo.hibernate.dao.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.haohaowo.common.Page;


public abstract class BaseDao<T> extends HibernateDaoSupport {
	
	protected long getTotalCount(Page<T> page) {
		String hql = "select count(*) " + removeSelect(removeOrderby(page.getHql()));
		page.setHql(hql);
		long count = (Long)getQueryBySession(page).uniqueResult();
		return count;
	}
	
	/**
	 * Remove select clause in hql.
	 */
	private String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPos);
	}

	/**
	 * Remove order by clause in hql.
	 */
	private String removeOrderby(String sql) {
		String pattern = "order\\s*by[\\w|\\W|\\s|\\S]*";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	protected Page<T> findPage(Page<T> page) {
		//List
		List<T> list = (List<T>)getQueryBySession(page).setFirstResult(page.getStartOfPage())
			.setMaxResults(page.getPageSize()).list();
		page.setData(list);
		
		//count
		long count = getTotalCount(page);
		page.setTotalCount(count);
		return page;
	}
	
	private Query getQueryBySession(Page<T> page) {
		Query query = getSession().createQuery(page.getHql());
		if(!page.getCondition().isEmpty()) {
			query.setProperties(page.getCondition());
		}
		return query;
	}
}
