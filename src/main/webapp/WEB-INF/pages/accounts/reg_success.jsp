<%@ page language="java" pageEncoding="UTF-8"%>
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
			<div id="register_success">
				<p>注册成功！</p>
				<p>
					<s:a action="list" namespace="/books">马上购物</s:a>
					<s:a action="home" namespace="/">返回首页</s:a>
				</p>
			</div>
		</div>
		<%@include file="/commons/footer.jsp" %>
	</body>
</html>
