package cn.haohaowo.service;


import cn.haohaowo.common.Page;
import cn.haohaowo.entity.Order;

public interface OrderService {
	
	public void createOrder(Order order);

	public Page<Order> getCurrentAccountOrders(Integer accountId, int pageNo);
	
	public Page<Order> getCurrentAccountOrders(Integer accountId, int pageNo, int pageSize);
	
	public Order getOrder(Integer id);
	
	public long getCurrentAccountCompletedOrdersCount(Integer accountId);

	public long getCurrentAccountOnPrecessingOrdersCount(Integer accountId);
}
