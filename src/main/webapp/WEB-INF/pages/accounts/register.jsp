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
				$('#captcha_img').next().click(function() {
					$(this).prev().attr('src', '${webctx}/accounts/validationCode.action?time=' + new Date().getTime());
					return false;
				});
				
				$('#register_form').validate({
					rules: {
						"account.username": {
							required: true,
							rangelength: [3, 20],
							remote: {
								url: "${webctx}/validate/uniqueUsername.action"
							}
						},
						"account.password": {
							required: true,
							rangelength: [6, 20]
						},
						"account.passwordConfirm": {
							required: true,
							equalTo: "#password"
						},
						"account.email": {
							required: true,
							email: true,
							remote: {
								url: "${webctx}/validate/uniqueEmail.action"
							}
						},
						"account.mobile": "required",
						"account.address": "required",
						"account.zipcode": "required",
						"account.question": "required",
						"account.answer": "required",
						captcha: {
							required: true,
							remote: {
								url: "${webctx}/validate/captcha.action"
							}
						}
					},
					messages: {
						"account.username": {
							required: "请输入用户名",
							rangelength: "用户名长度为3至20个字符",
							remote: "用户名已经存在"
						},
						"account.password": {
							required: "请输入密码",
							rangelength: "密码长度为6至20个字符"
						},
						"account.passwordConfirm": {
							required: "请输入确认密码",
							equalTo: "两次输入的密码不一致"
						},
						"account.email": {
							required: "请输入邮箱",
							email: "邮箱无效",
							remote: "邮箱已经存在"
						},
						"account.mobile": "请输入电话",
						"account.address": "请输入送货地址",
						"account.zipcode": "请输入邮编",
						"account.question": "请选择密码保护问题",
						"account.answer": "请输入密码保护问题答案",
						captcha: {
							required: "请输入验证码",
							remote: "验证码错误"
						}
					}
				});
			});
		</script>
	</head>
	
	<body>
		<%@include file="/commons/header.jsp" %>
		
		<div id="content">
			<s:form id="register_form" action="/register" namespace="/accounts" theme="simple">
				<h1>新用户注册</h1>
				
				<p id="note">
					<span class="required">*</span>为必填项
				</p>
				
				<fieldset>
					<legend>基本注册信息</legend>
					<p>
						<label for="username"><span class="required">*</span>用户名：</label>
						<s:textfield id="username" name="account.username"></s:textfield>
						<span class="tips">3-20个字符，不能包含字符@和空格且全站唯一</span>
					</p>
					<p>
						<label for="password"><span class="required">*</span>密码：</label>
						<s:password id="password" name="account.password"></s:password>
						<span class="tips">6-20个字符</span>
					</p>
					<p>
						<label for="password_confirm"><span class="required">*</span>密码确认：</label>
						<s:password id="password_confirm" name="account.passwordConfirm"></s:password>
					</p>
					<p>
						<label for="email"><span class="required">*</span>邮箱：</label>
						<s:textfield id="email" name="account.email"></s:textfield>
						<span class="tips">全站唯一</span>
					</p>
				</fieldset>
				<fieldset>
					<legend>个人信息</legend>
					<p>
						<label for="truename">真实姓名：</label>
						<s:textfield id="truename" name="account.truename"></s:textfield>
					</p>
					<p>
						<label>性别：</label>
						<input id="male" type="radio" name="account.sex" value="M" <c:if test="${account.sex == 'M'}">checked="checked"</c:if>>
						<label class="sex" for="male">男</label>
						<input id="female" type="radio" name="account.sex" value="F" <c:if test="${account.sex == 'F'}">checked="checked"</c:if>>
						<label class="sex" for="female">女</label>
					</p>
					<p>
						<label for="mobile"><span class="required">*</span>电话：</label>
						<s:textfield id="mobile" name="account.mobile"></s:textfield>
					</p>
					<p>
						<label for="address"><span class="required">*</span>送货地址：</label>
						<s:textfield id="address" name="account.address"></s:textfield>
					</p>
					<p>
						<label for="zipcode"><span class="required">*</span>邮政编码：</label>
						<s:textfield id="zipcode" name="account.zipcode"></s:textfield>
					</p>
					<p>
						<label for="question"><span class="required">*</span>密码保护问题：</label>
						<s:select name="account.question" list="#{'': '--选择一个问题--', '您的宠物的名字？': '您的宠物的名字？', 
							'您就读的第一所学校的名称？': '您就读的第一所学校的名称？', '少年时代心目中的英雄是谁？': '少年时代心目中的英雄是谁？', 
							'您最喜欢的休闲运动是什么？': '您最喜欢的休闲运动是什么？', '您最喜欢哪支运动队？': '您最喜欢哪支运动队？', 
							'您最喜欢的运动员是谁？': '您最喜欢的运动员是谁？', '您的第一辆汽车或自行车是什么牌子的？': '您的第一辆汽车或自行车是什么牌子的？'}">
    					</s:select>
						<span class="tips">用于取回密码以及处理其他帐户问题</span>
					</p>
					<p>
						<label for="answer"><span class="required">*</span>答案：</label>
						<s:textfield id="answer" name="account.answer"></s:textfield>
					</p>
				</fieldset>
				<fieldset class="last">
					<p>
						<label for="captcha"><span class="required">*</span>验证码：</label>
						<img id="captcha_img" src="${webctx}/accounts/validationCode.action"/>
						<a href="#">看不清楚？</a>
						<input id="captcha" type="text" name="validationCode">
					</p>
					<p>
						<a class="terms" href="${webctx}/terms.html" target="_blank">服务条款</a>
						<input type="submit" class="register_submit" value="同意条款并注册">
						<span class="cancel">or <a href="${webctx}">Cancel</a></span>
					</p>
				</fieldset>
			</s:form>
		</div>
		
		<%@include file="/commons/footer.jsp" %>
	</body>
</html>
