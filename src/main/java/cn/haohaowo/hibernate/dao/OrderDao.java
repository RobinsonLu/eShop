package cn.haohaowo.hibernate.dao;



import cn.haohaowo.common.Page;
import cn.haohaowo.entity.Order;


public interface OrderDao {
	

	public void createOrder(Order order);
	
	public Page<Order> getOrders(int pageNo, int pageSize);
		
	public long getCompletedOrdersCount(Integer accountId);
	
	public long getOnPrecessingOrdersCount(Integer accountId);
	
	public Order getOrder(Integer id);
	
	public Page<Order> getOrders(Integer accountId, int pageNo,
			int pageSize);
	
}
