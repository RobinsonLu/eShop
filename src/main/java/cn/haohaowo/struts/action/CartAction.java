package cn.haohaowo.struts.action;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import cn.haohaowo.common.Constants;
import cn.haohaowo.entity.Account;
import cn.haohaowo.entity.Order;
import cn.haohaowo.service.BookService;
import cn.haohaowo.service.impl.BookServiceImpl;

public class CartAction extends BaseAction {

	private static final long serialVersionUID = -3783693513607158879L;

	private Integer id;
	private Integer quantity;
	private String deliveryType;
	
	private String subtotal;
	private String saved;
	private String total;
	private String freight;
	
	private Order preparedOrder;
	
	@Autowired
	private BookService bookService;
	
	@Override
	public void prepare() throws Exception {
		this.preparedOrder = (Order)session.get(Constants.PREPARED_ORDER_KEY);
	}
	
	public String show(){
		return SUCCESS;
	}

	public String addBook(){
		if(null == quantity || quantity < 1){
			quantity = 1;
		}
		this.getCurrentShoppingCart().addBook(id,quantity);
		return CART;
	}
	
	public String removeBook(){
		this.getCurrentShoppingCart().removeBook(id);
		return CART;
	}
	
	public String clearBook(){
		this.getCurrentShoppingCart().clear();
		return CART;
	}
	
	public String confirm(){
		if(null == this.preparedOrder || this.getCurrentShoppingCart().isEmpty()){
			return SHOW;
		}
		return CONFIRM;
	}
	
	public String updateQuantity(){
		if(null != bookService.getBook(id) && null != this.getCurrentShoppingCart()
				.getCartItem(id)){
			this.getCurrentShoppingCart().updateQuantity(id, quantity);
			subtotal = this.getCurrencyNumber(this.getCurrentShoppingCart().
					getCartItem(id).getTotalPrice());
			saved = this.getCurrencyNumber(this.getCurrentShoppingCart()
					.getTotalSaved());
			total = this.getCurrencyNumber(this.getCurrentShoppingCart()
					.getTotalPrice());
		}
		return UPDATE_QUANTITY;
	}
	
	public String checkout(){
		if(this.getCurrentShoppingCart().isEmpty()){
			return CART;
		}
		if(null != preparedOrder){
			preparedOrder = new Order();
			
			Account account = this.getCurrentAccount();
			preparedOrder.setReceiver(account.getTruename());
			preparedOrder.setMobile(account.getMobile());
			preparedOrder.setShippingAddress(account.getAddress());
			preparedOrder.setZipcode(account.getZipcode());
			preparedOrder.setDeliveryType(Constants.NORMAL_DELIVERY);
			
			session.put(Constants.PREPARED_ORDER_KEY,this.preparedOrder);
		}
		
		this.calculateFreight(preparedOrder, this.getCurrentShoppingCart().getTotalPrice());
		
		return CHECKOUT;
	}
	
	private void calculateFreight(Order order,double totalPrice){
		if(Constants.FAST_DELIVERY.equals(order.getDeliveryType())){
			order.setDeliveryFee(Constants.FAST_DELIVERY_FEE);
		}else if(totalPrice > Constants.FREE_DELIVERY_PRICE){
			order.setDeliveryFee(0.0);
		}else if(Constants.NORMAL_DELIVERY.equals(order.getDeliveryType())){
			order.setDeliveryFee(Constants.NORMAL_DELIVERY_FEE);
		}else if(Constants.POST_DELIVERY.equals(order.getDeliveryType())){
			order.setDeliveryFee(Constants.POST_DELIVERY_FEE);
		}
	}
	
	public String setDeliveryType(){
		if(Constants.NORMAL_DELIVERY.equals(deliveryType) || Constants.FAST_DELIVERY.equals(deliveryType)
				|| Constants.POST_DELIVERY.equals(deliveryType)){
			preparedOrder.setDeliveryType(deliveryType);
			
			double totalPrice = this.getCurrentShoppingCart().getTotalPrice();
			calculateFreight(preparedOrder, this.getCurrentShoppingCart().getTotalPrice());
			
			freight = getCurrencyNumber(preparedOrder.getDeliveryFee());
			total = getCurrencyNumber(totalPrice + preparedOrder.getDeliveryFee());
		}
		return SET_DELIVERY_TYPE;
	}
	
	@JSON(serialize=false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JSON(serialize=false)
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@JSON(serialize=false)
	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getSaved() {
		return saved;
	}

	public void setSaved(String saved) {
		this.saved = saved;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	@JSON(serialize=false)
	public Order getPreparedOrder() {
		return preparedOrder;
	}

	public void setPreparedOrder(Order preparedOrder) {
		this.preparedOrder = preparedOrder;
	}
	
}
