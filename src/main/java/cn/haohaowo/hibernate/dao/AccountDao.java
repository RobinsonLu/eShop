package cn.haohaowo.hibernate.dao;


import cn.haohaowo.entity.Account;

/**
 * 
 * Created on May 30, 2013
 * <p>Title:       []_[]_[]</p>
 * <p>Description: []</p>
 * <p>Copyright:   Copyright (c) 2011</p>
 * <p>Company:     上海易饰嘉网络科技有限公司</p>
 * <p>Department:  技术部</p>
 * @author         [鲁万财] [luwancai]@homevv.com
 * @version        1.0
 */
public interface AccountDao {
	
	public Account getAccount(Integer id);
	
	public Account getAccount(String name);
	
	public void createAccount(Account account);
	
	public boolean emailExist(Integer id, String email);
	
	public void saveAccount(Account account);
}
