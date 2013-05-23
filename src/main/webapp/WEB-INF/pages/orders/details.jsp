<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>电子书城</title>
		<link rel="shortcut icon" href="${webctx}/images/favicon.ico">
		<link rel="stylesheet" type="text/css" href="${webctx}/stylesheets/style.css" media="all" />
	</head>

	<body class="two_col order_details">
		<%@include file="/commons/header.jsp"%>
		<div id="content">
			<%@include file="../commons/_menu.jsp" %>
			
			<div id="details">
				<h2>
					订单明细
				</h2>
				<div>
					<p><span>订单号：</span>${order.number}</p>
					<p><span>收货人：</span>${order.receiver}</p>
					<p><span>联系电话：</span>${order.mobile}</p>
					<p><span>详细地址：</span>${order.shippingAddress}</p>
					<p><span>邮政编码：</span>${order.zipcode}</p>
					<p>
						<span>送货方式：</span>
						<c:choose>
	                        <c:when test="${order.deliveryType == '1'}">
								普通快递
							</c:when>
							<c:when test="${order.deliveryType == '2'}">
								加急快递
							</c:when>
							<c:when test="${order.deliveryType == '3'}">
					    		普通邮递
							</c:when>
						</c:choose>
					</p>
					<p>
						<span>送货上门时间：</span>
						<c:choose>
	                        <c:when test="${order.deliveryTime == '1'}">
								时间不限
							</c:when>
							<c:when test="${order.deliveryTime == '2'}">
								周一至周五
							</c:when>
							<c:when test="${order.deliveryTime == '3'}">
					    		周末及法定假日
							</c:when>
						</c:choose>
					</p>
					<p>
						<span>付款方式：</span>
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
					</p>
					<p>
						<span>订单总金额：</span><span class="emphasize"><fmt:formatNumber value="${order.totalPrice + order.deliveryFee}" type="currency"/></span>
						（图书总计：<fmt:formatNumber value="${order.totalPrice}" type="currency"/>，运费：<fmt:formatNumber value="${order.deliveryFee}" type="currency"/>）
					</p>
					<p>
						<span>下单时间：</span>${order.createdAt}
					</p>
					<p>
						<span>订单状态：</span>
						<span class="emphasize">
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
						</span>
					</p>
				</div>
				<table>
					<thead>
						<tr>
							<th>
								序号
							</th>
							<th>
								图书名称
							</th>
							<th>
								市场价
							</th>
							<th>
								优惠价
							</th>
							<th>
								数量
							</th>
							<th>
								小计
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="detail" items="${order.details}" varStatus="status">
							<tr>
								<td class="index">
									${status.count}
								</td>
								<td>
									${detail.book.title}
								</td>
								<td class="orgiprice">
									<fmt:formatNumber value="${detail.book.orgiPrice}" type="currency" />
								</td>
								<td>
									<fmt:formatNumber value="${detail.unitPrice}" type="currency" />
								</td>
								<td>
									${detail.quantity}
								</td>
								<td>
									<fmt:formatNumber value="${detail.unitPrice * detail.quantity}" type="currency" />
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<a href="javascript: history.back()">返回</a>
			</div>
			<div class="clear"></div>
		</div>
		
		<%@include file="/commons/footer.jsp"%>
	</body>
</html>
