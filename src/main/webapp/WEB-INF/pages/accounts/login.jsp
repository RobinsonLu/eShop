<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="cn.haohaowo.common.Constants"%>
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
				$('#username, #password').focus(function() {
					$('#login_msg').html('<br>');
				});
				
				$('#login_form').validate({
					rules: {
						username: "required",
						password: "required"
					},
					messages: {
						username: "请输入用户名",
						password: "请输入密码"
					}
				});
			});
		</script>
	</head>
	
	<body>
		<%@include file="/commons/header.jsp" %>
		
		<div id="content">
			<form id="login_form" action="login.action" method="post">
				<h1>个人用户登录</h1>
				<div id="login_msg">
					<c:choose>
						<c:when test="${not empty loginMsg}">					
			            	${loginMsg}
			            </c:when>
			            <c:otherwise>
			            	<br/>
			            </c:otherwise>
					</c:choose>
				</div>
				<p>
					<label for="username">用户名：</label>
					<input id="username" type="text" name="username">
				</p>
				<p>
					<label for="password">密码：</label>
					<input id="password" type="password" name="password">
					<a href="javascript: void(0)">忘记密码？</a>
				</p>
				<p>
					<input id="remember_me" type="checkbox" name="rememberMe" value="true">
					<label id="remember_me_label" for="remember_me">记住我的登录状态</label>
				</p>
				<p>
					<input id="login_submit" type="image" src="${webctx}/images/login_button.gif">
				</p>
			</form>
			<div id="register_note">
				<h3>您还没有注册？</h3>
				<p>
					注册新会员，享受更优惠价格!
				</p>
				<p>
					千种图书，供你挑选！注册即享受丰富折扣和优惠，便宜有好货！超过万本图书任您选。
				</p>
				<p>
					超人气社区！精彩活动每一天。买卖更安心！支付宝交易超安全。
				</p>
				<s:a action="register" namespace="/accounts">注册新会员</s:a>
			</div>
		</div>
		
		<%@include file="/commons/footer.jsp" %>
	</body>
</html>

<%
	session.removeAttribute(Constants.LOGIN_MSG_KEY);
 %>
