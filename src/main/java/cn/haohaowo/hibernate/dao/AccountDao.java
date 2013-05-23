package cn.haohaowo.hibernate.dao;


import cn.haohaowo.entity.Account;


public interface AccountDao {
	
	public Account getAccount(Integer id);
	
	public Account getAccount(String name);
	
	public void createAccount(Account account);
	
	public boolean emailExist(Integer id, String email);
	
	public void saveAccount(Account account);
}
