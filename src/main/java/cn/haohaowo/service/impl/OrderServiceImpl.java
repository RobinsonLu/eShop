package cn.haohaowo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.haohaowo.common.Page;
import cn.haohaowo.entity.Order;
import cn.haohaowo.hibernate.dao.OrderDao;
import cn.haohaowo.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;

	public void createOrder(Order order) {
		orderDao.createOrder(order);
	}

	public long getCurrentAccountCompletedOrdersCount(Integer accountId) {
		return orderDao.getCompletedOrdersCount(accountId);
	}

	public long getCurrentAccountOnPrecessingOrdersCount(Integer accountId) {
		return orderDao.getOnPrecessingOrdersCount(accountId);
	}

	public Page<Order> getCurrentAccountOrders(Integer accountId, int pageNo) {
		int pageSize = 12;
		
		return getCurrentAccountOrders(accountId, pageNo, pageSize);
	}

	public Page<Order> getCurrentAccountOrders(Integer accountId, int pageNo, int pageSize) {
		return orderDao.getOrders(accountId, pageNo, pageSize);
	}

	public Order getOrder(Integer id) {
		return orderDao.getOrder(id);
	}


}
