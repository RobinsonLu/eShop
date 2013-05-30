package cn.haohaowo.struts.action;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;

import cn.haohaowo.common.Constants;
import cn.haohaowo.entity.Account;
import cn.haohaowo.service.AccountService;
import cn.haohaowo.util.StringUtils;

public class AccountAction extends BaseAction {

	private static final long serialVersionUID = 5583859910624730852L;

	private String username;
	private String password;
	private String rememberMe;
	private String returnPath;
	private String orginalPassword;
	private String newPassword;
	private String validationCode;
	private String notice;
	private String loginMsg;
	
	private Account account;
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public void prepare() throws Exception {
		account = this.getCurrentAccount();
	}
	
	public String register(){
		if("post".equalsIgnoreCase(request.getMethod())){
			accountService.register(account);
			this.getSessionContainer().setAccount(account);
			return REGISTER_SUCCESS;
		}else{
			account = new Account();
		}
		return REGISTER;
	}
	
	public String registerSuccess(){
		return REGISTER_SUCCESS_PAGE;
	}
	
	public String password(){
		if("post".equalsIgnoreCase(request.getMethod())){
			if(account.getPassword().equals(orginalPassword)){
				account.setPassword(newPassword);
				accountService.saveAccount(account);
				this.notice = "密码修改成功";
			}else{
				this.notice = "密码修改失败";
			}
		}
		return PASSWORD;
	}
	
	public String update(){
		accountService.saveAccount(account);
		this.notice = "个人信息修改成功";
		
		return MY;
	}
	
	public String my(){
		return MY;
	}
	
	public String login(){
		if("get".equalsIgnoreCase(request.getMethod())){
			if(!this.loggedIn()){
				return LOGIN;
			}
		}else if("post".equalsIgnoreCase(request.getMethod())){
			Account account = accountService.login(username, password);
			if(null != account){
				if("true".equals(this.rememberMe)){
					this.rememberMe(username, password);
				}
				
				this.getSessionContainer().setAccount(account);
				this.returnPath = (String) session.get(Constants.RETURN_PATH);
				if(StringUtils.isNotEmpty(this.returnPath)){
					session.remove(Constants.RETURN_PATH);
					return this.returnPath;
				}
			}else{
				this.loginMsg = "用户名和密码不正确";
				return LOGIN;
			}
		}
		
		return HOME;
	}
	
	public String logout(){
		this.removeAccountFromSession();
		this.forgotMe();
		return HOME;
	}

	private void rememberMe(String username,String password){
		Cookie cookie = new Cookie(Constants.REMEMBER_ME_KEY,
				username+Constants.REMEMBER_ME_SEPERATOR+password);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24 * 7 * 2);
		response.addCookie(cookie);
	}
	
	private void forgotMe(){
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals(Constants.REMEMBER_ME_KEY)){
				cookie.setPath("/");
				cookie.setMaxAge(0);
				cookie.setValue(null);
				response.addCookie(cookie);
//				break;
			}
		}
	}
	
	private void removeAccountFromSession(){
		this.getSessionContainer().setAccount(null);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getReturnPath() {
		return returnPath;
	}

	public void setReturnPath(String returnPath) {
		this.returnPath = returnPath;
	}

	public String getOrginalPassword() {
		return orginalPassword;
	}

	public void setOrginalPassword(String orginalPassword) {
		this.orginalPassword = orginalPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getLoginMsg() {
		return loginMsg;
	}

	public void setLoginMsg(String loginMsg) {
		this.loginMsg = loginMsg;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}
	
	
}
