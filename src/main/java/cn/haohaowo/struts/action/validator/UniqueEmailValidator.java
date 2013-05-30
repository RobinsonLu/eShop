package cn.haohaowo.struts.action.validator;

import org.apache.struts2.json.annotations.JSON;

import cn.haohaowo.entity.Account;
import cn.haohaowo.service.AccountService;
import cn.haohaowo.service.impl.AccountServiceImpl;
import cn.haohaowo.struts.action.BaseAction;


public class UniqueEmailValidator extends BaseAction {
	private static final long serialVersionUID = 169255817235249311L;

	private Account account;
	private boolean result;
	
	private AccountService accountService = new AccountServiceImpl();
	
	@Override
	public String execute() throws Exception {
		result = !accountService.emailExist(account.getId(), account.getEmail());
		return SUCCESS;
	}

	@JSON(serialize=false)
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
