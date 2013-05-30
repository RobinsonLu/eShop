<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>电子书城</title>
		<link rel="shortcut icon" href="${webctx}/images/favicon.ico" >
		<link rel="stylesheet" type="text/css" href="${webctx}/stylesheets/style.css" media="all" />
		<script type="text/javascript" src="${webctx}/javascripts/jquery-1.4.1.min.js"></script>
		<script type="text/javascript" src="${webctx}/javascripts/application.js"></script>
		
		<script type="text/javascript">
			$(function() {
				$('#quantity').integer().blur(function() {
					var value = parseInt($(this).val(), 10);
					$(this).val((isNaN(value) || value == 0) ? 1 : value);
				});
			});
		</script>
	</head>
	
	<body class="two_col">
		<%@include file="/commons/header.jsp" %>
		<div id="content">
			<div id="recommended">
				<h2>购买本书的顾客还购买过</h2>
				<ul>
					<c:forEach var="rec_book" items="${recommendedBooks}">
						<li><a href="${webctx}/books/show.action?id=${rec_book.id}">${rec_book.title}</a></li>
					</c:forEach>
					<li class="more"><a href="${webctx}/books/list.action">更多&gt;&gt;</a></li>
				</ul>
			</div>
			<div id="book">
				<div id="meta">
					<h2 class="title">${book.title}</h2>
					<div class="author">作者：${book.author}</div>
					<div class="sale">
						优惠价：<span class="saleprice"><fmt:formatNumber value="${book.price}" type="currency"></fmt:formatNumber></span>
						市场价：<span class="orgiprice"><fmt:formatNumber value="${book.orgiPrice}" type="currency" /></span>
					</div>
					<div class="publisher">出版社：${book.publisher}</div>
					<div class="description">
						<h4>内容简介：</h4>
						<p>
							${book.description}
						</p>
					</div>
				</div>
				<div id="purchase">
					<img src="${webctx}/images/${book.imageUrl}" alt="${book.title}">
					<s:form action="addBook" namespace="/carts" method="get" theme="simple">
						<s:hidden name="id" value="%{book.id}"></s:hidden>
						<p>
							<label for="quantity">我要买：</label>
							<s:textfield id="quantity" name="quantity" value="1"></s:textfield>
							<label for="quantity">本</label>
						</p>
						<p>
							<input type="image" src="${webctx}/images/buy2.gif">
						</p>
					</s:form>
				</div>
			</div>
		 <div class="clear"></div>
		</div>
		
		<%@include file="/commons/footer.jsp" %>
	</body>
</html>
