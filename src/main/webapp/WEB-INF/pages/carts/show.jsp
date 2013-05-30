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
				$('tbody tr:odd').addClass('even');
				
				$('input[bookid]').integer().blur(function() {
					var input = $(this);
					var value = parseInt(input.val(), 10);
					var defaultValue = input[0].defaultValue;
					
					if (isNaN(value) || value == 0) { // 如果为空或0则恢复为原始值
						input.val(defaultValue);
						return false;
					}
					 
					$(this).val(value);
					if (value != defaultValue) { // 输入值和原始值不同
						$.getJSON('${webctx}/carts/updateQuantity.action', { 'id': input.attr('bookid'), 'quantity': value }, function(data) {
							if (data) {
								input.parent().next().text(data.subtotal);
								$('#saved').text(data.saved);
								$('#total').text(data.total);
							}
							// 更新原始值
							input[0].defaultValue = value;
						});
					}
				});
			});
		</script>
	</head>
	
	<body>
		<%@include file="/commons/header.jsp" %>
		
		<div id="content">
			<c:choose>
			    <c:when test="${empty container.shoppingCart.cartItems}">
				    <div id="cart">
						 <div id="buyflow1"></div>
						    <div id="empty">
							  <p>
							    	您目前尚未选购任何商品！
							  </p>
							  <s:a action="list" namespace="/books" cssClass="continue">继续购物</s:a>
						    </div>
				     </div>
				</c:when>
				<c:otherwise>
					<div id="cart">
						<div id="buyflow1"></div>
						<s:a id="clear_cart" action="clear" namespace="/carts" cssClass="continue" onclick="return confirm('确定要清空购物车？');">清空购物车</s:a>
	       				<table>
							<thead>
								<tr>
									<th>序号</th>
									<th>图书名称</th>
									<th>市场价</th>
									<th>优惠价</th>
									<th>数量</th>
									<th>小计</th>
									<th>删除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${container.shoppingCart.cartItems}" varStatus="status">
									<tr>
										<td class="index">
											${status.count}
										</td>
										<td>
											<a href="${webctx}/books/show.action?id=${item.book.id}">${item.book.title}</a>
										</td>
										<td class="orgiprice">
											<fmt:formatNumber value="${item.book.orgiPrice}" type="currency" />
										<td>
											<fmt:formatNumber value="${item.book.price}" type="currency" />
										</td>
										<td>
											<input name="quantity" value="${item.quantity}" bookid="${item.book.id}">
										</td>
										<td>
											<fmt:formatNumber value="${item.totalPrice}" type="currency" />
										</td>
										<td class="remove">
											<a href="${webctx}/carts/removeBook.action?id=${item.book.id}" onclick="return confirm('确定要删除该图书？')">X</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="3" class="total">
										为您节省：
									</td>
									<td id="saved" class="totalprice">
										<fmt:formatNumber value="${container.shoppingCart.totalSaved}" type="currency"/>
									</td>
									<td class="total">
										合计：
									</td>
									<td colspan="2" id="total" class="totalprice">
									  <fmt:formatNumber value="${container.shoppingCart.totalPrice}" type="currency" />
									</td>
								</tr>
							</tfoot>
						</table>
						<div id="operation">
							<s:a action="checkout" namespace="/carts" cssClass="checkout">结账</s:a>
							<s:a action="list" namespace="/books" cssClass="continue">继续购物</s:a>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
				
		<%@include file="/commons/footer.jsp" %>
	</body>
</html>
