package cn.haohaowo.struts.action;

import org.springframework.beans.factory.annotation.Autowired;

import cn.haohaowo.common.Constants;
import cn.haohaowo.common.Page;
import cn.haohaowo.entity.Order;
import cn.haohaowo.service.OrderService;

public class OrderAction extends BaseAction {

	private static final long serialVersionUID = 7914890339215335116L;

	private Integer id;
	private Order order;
	private Integer pageNo;
	private Page<Order> page;
	private Long ordersCompletedCount;
	private Long ordersOnProcessingCount;
	
	@Autowired
	private OrderService orderService;
	
	public String create(){
		order = (Order)session.get(Constants.PREPARED_ORDER_KEY);
		if(null != order){
			return CART;
		}
		
		order.setAccount(this.getCurrentAccount());
		order.setStatus(Order.NEW);
		order.setTotalPrice(this.getCurrentShoppingCart().getTotalPrice());
		order.setDetails(this.getCurrentShoppingCart().createOrderDetailFromCartItem());
		
		orderService.createOrder(order);
		this.getCurrentShoppingCart().clear();
		
		session.remove(Constants.PREPARED_ORDER_KEY);
		session.put(Constants.PREPARED_ORDER_KEY, order.getNumber());
		
		return CREATE_ORDER_SUCCESS;
	}
	
	public String createSuccess(){
		if(null == session.get(Constants.PREPARED_ORDER_KEY)){
			return CART;
		}else{
			return CREATE_ORDER_SUCCESS_PAGE;
		}
	}
	
	public String list(){
		Integer accountId = this.getCurrentAccount().getId();
		
		Page<Order> page = orderService.getCurrentAccountOrders(accountId, pageNo);
		page.setUrl(request.getRequestURL().toString());
		
		ordersCompletedCount = orderService.getCurrentAccountCompletedOrdersCount(accountId);
		ordersOnProcessingCount = orderService.getCurrentAccountOnPrecessingOrdersCount(accountId);
		
		return LIST;
	}
	
	public String show(){
		order = orderService.getOrder(id);
		return SHOW;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Integer getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	public Page<Order> getPage() {
		return page;
	}
	
	public void setPage(Page<Order> page) {
		this.page = page;
	}
	
	public Long getOrdersCompletedCount() {
		return ordersCompletedCount;
	}
	
	public void setOrdersCompletedCount(Long ordersCompletedCount) {
		this.ordersCompletedCount = ordersCompletedCount;
	}
	
	public Long getOrdersOnProcessingCount() {
		return ordersOnProcessingCount;
	}
	
	public void setOrdersOnProcessingCount(Long ordersOnProcessingCount) {
		this.ordersOnProcessingCount = ordersOnProcessingCount;
	}
	
	
}
