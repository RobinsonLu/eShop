package cn.haohaowo.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

public class ValidationCodeResult implements Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8224051479697761906L;

	public void execute(ActionInvocation invo) throws Exception {
		// TODO Auto-generated method stub
		ValidationCodeAction action = (ValidationCodeAction)invo.getAction();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		response.setContentType(action.getContentType());
		response.setContentLength(action.getContentLength());
		ServletOutputStream sos = response.getOutputStream();
		sos.write(action.getImageInBytes());
		sos.close();
	}

}
