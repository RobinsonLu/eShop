package cn.haohaowo.common;

import cn.haohaowo.entity.Account;
import cn.haohaowo.entity.ShoppingCart;


public class SessionContainer {

	private Account account;
	private ShoppingCart shoppingCart = new ShoppingCart();

	//===============setter && getter

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
}
