<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="taglibs.jsp"%>

<div id="masthead">
	<ul id="menubar">
		<c:if test="${not empty container.account}">
			<li class="welcome">您好，${container.account.username}</li>
		</c:if>
		<li><a class="cart" href="${webctx}/carts/show">购物车</a></li>
		<c:choose>
			<c:when test="${empty container.account}">
				<li><a href="${webctx}/accounts/login">登录</a></li>
				<li><a href="${webctx}/accounts/register">免费注册</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${webctx}/accounts/my">我的帐户</a></li>
				<li><a href="${webctx}/accounts/logout" onclick="return confirm('确定要退出？');">退出</a></li>
			</c:otherwise>
		</c:choose>
		<li class="last"><a href="${webctx}/help">帮助中心</a></li>
	</ul>
</div>
<div id="navigation">
	<ul>
		<li><a class="homepage" href="${webctx}">首页</a></li>
		<li><a href="${webctx}/books/list?type=2">文学</a></li>
		<li><a href="${webctx}/books/list?type=3">生活</a></li>
		<li><a href="${webctx}/books/list?type=1">计算机</a></li>
		<li><a href="${webctx}/books/list?type=4">外语</a></li>
		<li><a href="${webctx}/books/list?type=5">经管</a></li>
		<li><a href="${webctx}/books/list?type=6">励志</a></li>
		<li><a href="${webctx}/books/list?type=7">社科</a></li>
		<li><a href="${webctx}/books/list?type=8">学术</a></li>
		<li><a href="${webctx}/books/list?type=9">科技</a></li>
		<li><a class="all" href="${webctx}/books/list" >全部图书</a></li>
	</ul>
</div>
<div id="search">
	<form action="${webctx}/books/search" method="get">
		<input type="text" name="keyword" value="${param.keyword}">
		<input type="image" src="${webctx}/images/search.gif">
	</form>
	<div class="clear"></div>
</div>
			