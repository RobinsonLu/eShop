<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>电子书城</title>
		<link rel="shortcut icon" href="${webctx}/images/favicon.ico" >
		<link rel="stylesheet" type="text/css" href="${webctx}/stylesheets/style.css" media="all" />
		<script type="text/javascript" src="${webctx}/javascripts/jquery-1.4.1.min.js"></script>
		
		<script type="text/javascript">
			$(function() {
				$('tbody tr:odd').addClass('even');
			});
		</script>
	</head>
	
	<body>
		<%@include file="/commons/header.jsp" %>
		
		<div id="content">
			<div id="order_content">
				<div id="buyflow3"></div>
				<div id="order_info">
					<h2>订单信息</h2>
					<p>
						<span class="label">收货人：</span>${preparedOrder.receiver}
					</p>
					<p>
						<span class="label">联系电话：</span>${preparedOrder.mobile}
					</p>
					<p>
						<span class="label">详细地址：</span>${preparedOrder.shippingAddress}
					</p>
					<p>
						<span class="label">邮政编码：</span>${preparedOrder.zipcode}
					</p>
					<p>
						<span class="label">送货方式：</span>
						<c:choose>
							<c:when test="${preparedOrder.deliveryType == '1'}">
								普通快递送货上门（支持货到付款）
							</c:when>
							<c:when test="${preparedOrder.deliveryType == '2'}">
							 	 加急快递送货上门（支持货到付款）
							</c:when>
							<c:when test="${preparedOrder.deliveryType == '3'}">
								普通邮递（不支持货到付款）
							</c:when>
						</c:choose>
					</p>
					<p>
						<span class="label">送货上门时间：</span>
						<c:choose>
	                         <c:when test="${preparedOrder.deliveryTime== '1'}">
							 	时间不限
							</c:when>
							<c:when test="${preparedOrder.deliveryTime == '2'}">
								周一至周五
							</c:when>
							<c:when test="${preparedOrder.deliveryTime == '3'}">
								周末及法定假日
							</c:when>
						</c:choose>
					</p>
					<p>
						<span class="label">运费：</span><fmt:formatNumber value="${preparedOrder.deliveryFee}" type="currency"></fmt:formatNumber>
					</p>
					<p>
						<span class="label">付款方式：</span>
						<c:choose>
	                        <c:when test="${preparedOrder.paymentType == '1'}">
								货到付款
							</c:when>
							<c:when test="${preparedOrder.paymentType == '2'}">
								网上支付
							</c:when>
							<c:when test="${preparedOrder.paymentType == '3'}">
					    		邮局汇款
							</c:when>
							<c:when test="${preparedOrder.paymentType == '4'}">
								银行转帐
							</c:when>
						</c:choose>
					</p>
				</div>
				<div id="cart_confirm">
					<h3>订单明细</h3>
					<table>
						<thead>
							<tr>
								<th>序号</th>
								<th>图书名称</th>
								<th>优惠价</th>
								<th>数量</th>
								<th>小计</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${container.shoppingCart.cartItems}" var="item" varStatus="status">
							<tr>
								<td class="index">${status.count}</td>
								<td>${item.book.title}</td>
								<td><fmt:formatNumber value="${item.book.price}" type="currency" /></td>
								<td>${item.quantity}</td>
								<td><fmt:formatNumber value="${item.totalPrice}" type="currency" /></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<div id="price_charge">
						<p>
							图书金额总计：<span class="total_sum"><fmt:formatNumber value="${container.shoppingCart.totalPrice}" type="currency" /></span>
						</p>
						<p>
							运费：<span class="total_sum"><fmt:formatNumber value="${preparedOrder.deliveryFee}" type="currency"></fmt:formatNumber></span>
						</p>
						<p class="final_sum">
							您需为订单支付：<span class="total_sum"><fmt:formatNumber value="${container.shoppingCart.totalPrice + preparedOrder.deliveryFee}" type="currency" /></span>
						</p>
					</div>
					<div class="clear">
						<s:a id="order_confirm" action="create" namespace="/orders">确认订单</s:a>
						<s:a id="order_change" action="checkout" namespace="/carts">修改订单</s:a>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	<%@include file="/commons/footer.jsp" %>
	</body>
</html>
