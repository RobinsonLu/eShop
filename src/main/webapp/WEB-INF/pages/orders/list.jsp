<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>电子书城</title>
		<link rel="shortcut icon" href="${webctx}/images/favicon.ico" >
		<link rel="stylesheet" type="text/css" href="${webctx}/stylesheets/style.css" media="all" />
	</head>
	
	<body class="two_col orders">
	<%@include file="/commons/header.jsp" %>
		
		<div id="content">
			<%@ include file="../commons/_menu.jsp" %>
			
			<div id="order_list">
				<c:choose>
					<c:when test="${empty page.data}">
						<div class="emphasize">
							您没有订单！
						</div>
					</c:when>
					<c:otherwise>
						<p class="order_header">
							您已经成交了${ordersCompletedCount}笔交易，
							有<span class="emphasize">${ordersOnProcessingCount}</span>笔交易正在处理中。
						</p>
						<table>
							<thead>
								<tr>
									<th>订单号</th>
									<th>收货人</th>
									<th>付款方式</th>
									<th>订单总金额</th>
									<th>下单时间</th>
									<th>订单状态</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="order" items="${page.data}">
									<tr>
										<td><a class="tooltip" href="${webctx}/orders/show.action?id=${order.id}">${order.number}<span>查看订单明细</span></a></td>
										<td>${container.account.username}</td>
										<td>
											<c:choose>
						                        <c:when test="${order.paymentType == '1'}">
													货到付款
												</c:when>
												<c:when test="${order.paymentType == '2'}">
													网上支付
												</c:when>
												<c:when test="${order.paymentType == '3'}">
										    		邮局汇款
												</c:when>
												<c:when test="${order.paymentType == '4'}">
													银行转帐
												</c:when>
											</c:choose>
										</td>
										<td><fmt:formatNumber value="${order.totalPrice}" type="currency" /></td>
										<td><fmt:formatDate value="${order.createdAt}" type="both"/></td>
										<td 
										<c:if test="${order.status == '1' or order.status == '2'}">class="emphasize"</c:if>
										>
											<c:choose>
											    <c:when test="${order.status == '1'}">
													新增
										        </c:when>
										        <c:when test="${order.status == '2'}">
													 已发货
										        </c:when>
										        <c:when test="${order.status == '3'}">
										        	已完成
										        </c:when>
										        <c:when test="${order.status == '4'}">
										        	取消
										        </c:when>
											</c:choose>
										</td>
									</tr>
								</c:forEach>		
							</tbody>
						</table>
						<%@include file="../commons/_pager.jsp" %>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="clear"></div>
		</div>
		
	<%@include file="/commons/footer.jsp" %>
	</body>
</html>
