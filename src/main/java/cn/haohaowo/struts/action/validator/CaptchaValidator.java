package cn.haohaowo.struts.action.validator;

import nl.captcha.Captcha;

import org.apache.struts2.json.annotations.JSON;

import cn.haohaowo.struts.action.BaseAction;


public class CaptchaValidator extends BaseAction {
	private static final long serialVersionUID = 169255817235249311L;

	private String captcha;
	private boolean result;
	
	@Override
	public String execute() throws Exception {
		Captcha capt = (Captcha) session.get(Captcha.NAME);
		result = capt.isCorrect(captcha);
		
		return SUCCESS;
	}

	@JSON(serialize=false)
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
