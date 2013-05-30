<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>电子书城</title>
		<link rel="shortcut icon" href="${webctx}/images/favicon.ico" >
		<link rel="stylesheet" type="text/css" href="${webctx}/stylesheets/style.css" media="all" />
	</head>
	
	<body class="home">
		<div id="container">
			<%@include file="/commons/header.jsp"%>
			<div id="ad"></div>
			<div id="content">
				<div id="board">
					<ul>
						<li><a href="javascript: void(0)">图书3折起，支持在线浏览 先看再买不后悔,任何商品免费送货</a></li>
						<li><a href="javascript: void(0)">Lonely Planet 已出版600多种旅行指南，几乎覆盖了全世界的每个角落</a></li>
						<li><a href="javascript: void(0)">2007年中旅游图书畅销榜速递 中国游,世界游,旅游图书全部7折封顶</a></li>
						<li><a href="javascript: void(0)">50万种图书3折,百货团购价热卖 畅销榜新书速递,促销天天有</a></li>
						<li><a class="em" href="javascript: void(0)">全场免运费活动开始啦！</a></li>
					</ul>
				</div>
				<div id="hot">
					<c:forEach var="book" items="${hotBooks}">
						<div class="hot_book">
							<a href="${webctx}/books/show.action?id=${book.id}">
								<img src="${webctx}/images/${book.imageUrl}" alt="${book.title}" />
								<div>${book.title}</div>
								<div>作者: ${book.author}</div>
							</a>
						</div>
					</c:forEach>
				</div>
				<div class="clear"></div>
			</div>
			<%@include file="/commons/footer.jsp"%>
		</div>
	</body>
</html>
