package cn.haohaowo.hibernate.dao.impl;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import cn.haohaowo.common.Page;
import cn.haohaowo.entity.Order;
import cn.haohaowo.hibernate.HibernateUitl;
import cn.haohaowo.hibernate.dao.OrderDao;
import cn.haohaowo.util.StringUtils;

@Repository("orderDao")
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
	StringBuffer hql = null;

	public void createOrder(Order order) {
		Date date = new Date();
		order.setCreatedAt(date);
		order.setNumber("ebook" + StringUtils.generateOrderNumber());
		HibernateUitl.getSession().saveOrUpdate(order);
	}

	public long getCompletedOrdersCount(Integer accountId) {
		hql = new StringBuffer("from Order o where o.account.id = :accountId and o.status = :status");
		Page<Order> page = new Page<Order>();
		page.setHql(hql.toString());
		HashMap<String, Object> condition = new HashMap<String, Object>();
		condition.put("accountId", accountId);
		condition.put("status", Order.COMPLETED);
		page.setCondition(condition);
		long count = getTotalCount(page);
		return count;
	}

	public long getOnPrecessingOrdersCount(Integer accountId) {
		hql = new StringBuffer("from Order o where o.account.id = :accountId and (o.status = :status1 or o.status = :status2)");
		Page<Order> page = new Page<Order>();
		page.setHql(hql.toString());
		HashMap<String, Object> condition = new HashMap<String, Object>();
		condition.put("accountId", accountId);
		condition.put("status1", Order.NEW);
		condition.put("status2", Order.SHIPPING);
		page.setCondition(condition);
		long count = getTotalCount(page);
		return count;
	}

	public Order getOrder(Integer id) {
		Order order = (Order)getHibernateTemplate().load(Order.class, id);
		return order;
	}


	public Page<Order> getOrders(Integer accountId, int pageNo, int pageSize) {
		Page<Order> page = new Page<Order>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		HashMap<String, Object> condition = new HashMap<String, Object>();
		
		hql = new StringBuffer("from Order o where 1 = 1");
		
		if(condition.containsKey("accountId")) {
			hql.append(" o.account.id = :accountId ");
			condition.put("accountId", accountId);
		}
		hql.append(" order by o.createdAt desc ");
		page.setHql(hql.toString());
		page.setCondition(condition);
		
		return findPage(page);
	}
	
	public Page<Order> getOrders(int pageNo, int pageSize) {
		Page<Order> page = new Page<Order>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		hql = new StringBuffer("from Order o order by o.createdAt desc ");
		page.setHql(hql.toString());
		
		return findPage(page);
	}

}
