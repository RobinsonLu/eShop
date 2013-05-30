<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>电子书城</title>
		<link rel="shortcut icon" href="${webctx}/images/favicon.ico" >
		<link rel="stylesheet" type="text/css" href="${webctx}/stylesheets/style.css" media="all" />
		<script type="text/javascript" src="${webctx}/javascripts/jquery-1.4.1.min.js"></script>
		<script type="text/javascript" src="${webctx}/javascripts/jquery.form.min.js"></script>
		
		<script type="text/javascript">
			$(function() {
				$('.reset').click(function() {
					$(this).parents('form').clearForm();
				});
			});
		</script>
	</head>
	
	<body class="two_col">
		<%@include file="/commons/header.jsp" %>
		
		<div id="content">
			<div id="adv_search">
				<h2>高级搜索</h2>
				<s:form action="list" namespace="/books" method="post" theme="simple">
					<p>
						<label for="type">分类：</label>
						<s:select name="type" list="#{'': '--所有分类--', 1: '文学', 2: '生活', 3: '计算机', 4: '外语', 5: '经管', 6: '励志', 7: '社科', 8: '学术', 9: '科技'}">
						</s:select>
					</p>
					<p>
						<label for="title">书名：</label>
						<s:textfield id="title" name="title"></s:textfield>
					</p>
					<p>
						<label for="author">著译者：</label>
						<s:textfield id="author" name="author"></s:textfield>
					</p>
					<p>
						<label for="publisher">出版社：</label>
						<s:textfield id="publisher" name="publisher"></s:textfield>
					</p>
					<p>
						<label for="minprice">售价：</label>
						<s:textfield id="minprice" name="minprice"></s:textfield>
						<label id="to" for="maxprice">至</label>
						<s:textfield id="maxprice" name="maxprice"></s:textfield>
					</p>
					<p>
						<s:submit value="搜索" cssClass="submit"></s:submit>
						<s:submit type="button" cssClass="reset" value="重新填写"></s:submit>
					</p>
				</s:form>
			</div>
			
			<div id="books">
				<c:choose>
					<c:when test="${empty page.data}">
						<div class="emphasize">
							没有符合条件的图书！
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach var="book" items="${page.data}">
							<div class="book">
								<a class="cover" href="${webctx}/books/show.action?id=${book.id}">
									<img src="${webctx}/images/${book.imageUrl}" alt="${book.title}">
								</a>
								<div class="description">
									<a class="title" href="${webctx}/books/show.action?id=${book.id}">
										<h3>${book.title}</h3>
									</a>
									<div class="author">作者：${book.author}</div>
									<div class="publisher">出版社：${book.publisher}</div>
									<p>
									${book.description}
									</p>
									<div class="sale">
										优惠价：<span class="saleprice"><fmt:formatNumber value="${book.price}" type="currency" /></span>
										市场价：<span class="orgiprice"><fmt:formatNumber value="${book.orgiPrice}" type="currency" /></span>
										<a href="${webctx}/carts/addBook.action?id=${book.id}">购买</a>
									</div>
								</div>
								<div class="clear"></div>
						 	</div>
						</c:forEach>
						<%@include file="../commons/_pager.jsp" %>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="clear"></div>
		</div>
		
		<%@include file="/commons/footer.jsp" %>
	</body>
</html>
