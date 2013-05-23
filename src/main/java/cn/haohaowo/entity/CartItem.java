package cn.haohaowo.entity;

import java.io.Serializable;

import cn.haohaowo.service.BookService;
import cn.haohaowo.service.impl.BookServiceImpl;

public class CartItem implements Serializable {

	private static final long serialVersionUID = -1014427200669332238L;
	
	private Integer boookid;
	private Integer quantity;
	
	private BookService bookservice = new BookServiceImpl();
	
	public CartItem(Integer bookid){
		this(bookid,1);
	}

	public CartItem(Integer bookid,Integer quantity){
		this.boookid = bookid;
		this.quantity = (quantity < 1)?  1:quantity;
	}
	
	public Book getBook(){
		return bookservice.getBook(this.boookid);
	}
	
	public double getTotalPrice(){
		return getBook().getPrice()*(this.quantity);
	}
	
	public double getTotalOrgiPrice(){
		return getBook().getOrgiPrice() * (this.quantity);
	}
	
	public void incrementQuantity() {
		this.quantity++;
	}
	
	public OrderDetail createOrderDetail(){
		OrderDetail od = new OrderDetail();
		od.setBook(getBook());
		od.setQuantity(this.quantity);
		od.setUnitPrice(getBook().getPrice());
		
		return od;
	}
	
	public Integer getBoookid() {
		return boookid;
	}

	public void setBoookid(Integer boookid) {
		this.boookid = boookid;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}
	

}
