package cn.haohaowo.struts.action;

import java.text.NumberFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import cn.haohaowo.common.Constants;
import cn.haohaowo.common.SessionContainer;
import cn.haohaowo.entity.Account;
import cn.haohaowo.entity.ShoppingCart;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, SessionAware, Preparable {
	
	private static final long serialVersionUID = 948685017152082601L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> session;
	
	public static final String HOME = "home";
	public static final String CART = "cart";
	public static final String SHOW = "show";
	public static final String LIST = "list";
	public static final String SEARCH = "search";
	public static final String BOOK_NOT_FOUND = "book_not_found";
	public static final String MY = "my";
	public static final String PASSWORD = "password";
	public static final String REGISTER = "register";
	public static final String REGISTER_SUCCESS = "register_success";
	public static final String REGISTER_SUCCESS_PAGE = "register_success_page";
	public static final String RETURN_PATH = "return_path";
	public static final String UPDATE_QUANTITY = "update_quantity";
	public static final String CHECKOUT = "checkout";
	public static final String SET_DELIVERY_TYPE = "set_delivery_type";
	public static final String CONFIRM = "confirm";
	public static final String CREATE_ORDER_SUCCESS = "create_order_success";
	public static final String CREATE_ORDER_SUCCESS_PAGE = "create_order_success_page";

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}
	
	protected SessionContainer getSessionContainer(){
		SessionContainer container = (SessionContainer) session.get(Constants.SESSION_CONTAINER_KEY);
		if(null == container){
			container = new SessionContainer();
			session.put(Constants.SESSION_CONTAINER_KEY, container);
		}
		return container;
	}
	
	protected Account getCurrentAccount(){
		return this.getSessionContainer().getAccount();
	}
	
	protected ShoppingCart getCurrentShoppingCart(){
		return this.getSessionContainer().getShoppingCart();
	}

	protected boolean loggedIn(){
		return null != this.getCurrentAccount() && null != this.getCurrentAccount().getId();
	}
	
	protected String getCurrencyNumber(Number amount){
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return nf.format(amount);
	}
	
}
