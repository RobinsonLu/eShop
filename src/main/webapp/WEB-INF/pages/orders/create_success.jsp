<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="cn.haohaowo.common.Constants"%>
<%@include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>电子书城</title>
		<link rel="shortcut icon" href="${webctx}/images/favicon.ico" >
		<link rel="stylesheet" type="text/css" href="${webctx}/stylesheets/style.css" media="all" />
	</head>
	
	<body>
		<%@include file="/commons/header.jsp" %>
		
		<div id="content">
			<div id="order_success">
				<p>您的订单已经提交成功！订单号为：${sessionScope.orderNumber}</p>
				<p>
					<s:a action="list" namespace="/orders">我的订单</s:a>
					<s:a action="list" namespace="/orders">继续购物</s:a>
				</p>
			</div>
		</div>
		
		<%@include file="/commons/footer.jsp" %>
	</body>
</html>

<%
	// 删除session中的订单号
	session.removeAttribute(Constants.ORDER_NUMBER_KEY);
 %>
