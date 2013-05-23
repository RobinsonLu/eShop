<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>电子书城</title>
		<link rel="shortcut icon" href="${webctx}/images/favicon.ico" >
		<link rel="stylesheet" type="text/css" href="${webctx}/stylesheets/style.css" media="all" />
		<script type="text/javascript" src="${webctx}/javascripts/jquery-1.4.1.min.js"></script>
		<script type="text/javascript" src="${webctx}/javascripts/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${webctx}/javascripts/application.js"></script>
		
		<script type="text/javascript">
			$(function() {
				$('#password_form').validate({
					rules: {
						orginalPassword: "required",
						newPassword: {
							required: true,
							rangelength: [6, 20]
						},
						newPasswordConfirmation: {
							required: true,
							equalTo: "#new_password"
						}
					},
					messages: {
						orginalPassword: "请输入原始密码",
						newPassword: {
							required: "请输入新密码",
							rangelength: "密码长度为6至20个字符"
						},
						newPasswordConfirmation: {
							required: "请输入确认密码",
							equalTo: "两次输入的密码不一致"
						}
					}
				});
			});
		</script>
	</head>
	
	<body class="two_col passwd">
		<%@include file="/commons/header.jsp" %>
		
		<div id="content">
			<%@include file="../commons/_menu.jsp" %>

			<div id="change_password">
				<%@include file="../commons/_notice.jsp" %>
				
				<s:form id="password_form" action="password" namespace="/accounts" theme="simple">
					<fieldset>
						<legend>修改密码</legend>
						<p id="note">
							<span class="required">*</span>为必填项
						</p>
						<p>
							<label for="orginal_password"><span class="required">*</span>原始密码：</label>
							<input type="password" id="orginal_password" name="orginalPassword">
						</p>
						<p>
							<label for="new_password"><span class="required">*</span>新密码：</label>
							<input type="password" id="new_password" name="newPassword">
							<span class="tips">6-20个字符</span>
						</p>
						<p>
							<label for="new_password_confirmation"><span class="required">*</span>新密码确认：</label>
							<input type="password" id="new_password_confirmation" name="newPasswordConfirmation">
						</p>
						<p>
							<input type="image" id="change_password_confirm" src="${webctx}/images/confirm.gif">
						</p>
					</fieldset>
				</s:form>
			</div>
		</div>
		
		<%@include file="/commons/footer.jsp" %>
	</body>
</html>
