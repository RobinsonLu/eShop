package cn.haohaowo.hibernate.dao.impl;



import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;


import cn.haohaowo.entity.Account;
import cn.haohaowo.hibernate.dao.AccountDao;


@Repository("accountDao")
public class AccountDaoImpl extends HibernateDaoSupport implements AccountDao {
	private String hql = null;
	
	public void createAccount(Account account) {
		getHibernateTemplate().saveOrUpdate(account);
	}
	
	public boolean emailExist(Integer id, String email) {
		
		Object[] obj = new Object[2];
		hql = "from Account a where a.email = :email";
		obj[0] = email;
		Account account = null;
		if(null != id){
			hql += "and id <> :id";
			obj[1] = id; 
			 account = (Account)getHibernateTemplate().find(hql,obj).get(0);
		}else{
			account = (Account)getHibernateTemplate().find(hql,email).get(0);
		}
		if(null != account){
			return true;
		}else{
			return false;
		}
	}
	
	public Account getAccount(Integer id) {
		Account account = (Account)getHibernateTemplate().load(Account.class, id);
		return account;
	}
	
	public Account getAccount(String name) {
		hql = "from Account a where a.username = :username";
		Account account = (Account)getHibernateTemplate().find(hql,name).get(0);
		return account;
	}
	
	public void saveAccount(Account account) {
		getHibernateTemplate().saveOrUpdate(account);
	}

}
