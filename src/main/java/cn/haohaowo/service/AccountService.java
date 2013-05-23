package cn.haohaowo.service;

import cn.haohaowo.entity.Account;

public interface AccountService {
	
	public Account login(String username, String password);
	
	public boolean usernameExist(String username);

	public boolean emailExist(Integer id, String email);
	
	public void register(Account account);

	public void saveAccount(Account account);
}
