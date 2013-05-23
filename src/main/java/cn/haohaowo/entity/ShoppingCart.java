package cn.haohaowo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.haohaowo.service.BookService;
import cn.haohaowo.service.impl.BookServiceImpl;

public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 8716301707851139388L;
	
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	private BookService bookservice = new BookServiceImpl();
	
	public void addBook(Integer bookid){
		this.addBook(bookid,1);
	}
	
	public void addBook(Integer bookid,int quantity){
		Book book = bookservice.getBook(bookid);
		if(null != book){
			CartItem item = getCartItem(bookid);
			if(null == item){
				item = new CartItem(bookid,quantity);
				cartItems.add(item);
			}else{
				item.setQuantity(item.getQuantity() + quantity);
			}
		}
	}
	
	public CartItem getCartItem(Integer bookid){
		for (CartItem item : cartItems) {
			if(item.getBoookid().equals(bookid)){
				return item;
			}
		}
		return null;
	}
	
	public void updateQuantity(Integer bookid,int quantity){
		CartItem item = new CartItem(bookid);
		if(null != item){
			item.setQuantity(quantity);
		}
	}
	
	public double getTotalPrice(){
		double totalprice = 0.0;
		for (CartItem item : cartItems) {
			totalprice += item.getTotalPrice();
		}
		return totalprice;
	}
	
	public double getTotalOrgiPrice(){
		double totalorgiprice = 0.0;
		for(CartItem item : cartItems){
			totalorgiprice += item.getTotalOrgiPrice();
		}
		return totalorgiprice;
	}
	
	public double getTotalSaved() {
		return getTotalOrgiPrice() - getTotalPrice();
	}
	
	public void removeBook(Integer bookid){
		CartItem item = getCartItem(bookid);
		if(null != item){
			cartItems.remove(item);
		}
	}
	
	public int getSize(){
		return cartItems.size();
	}
	
	public void clear(){
		cartItems.clear();
	}
	
	public boolean isEmpty(){
		return null == cartItems || cartItems.size() == 0;
	}
	
	public List<OrderDetail> createOrderDetailFromCartItem(){
		List<OrderDetail> details = new ArrayList<OrderDetail>(cartItems.size());
		for(CartItem item : cartItems){
			OrderDetail od = item.createOrderDetail();
			details.add(od);
		}
		return details;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
}
 