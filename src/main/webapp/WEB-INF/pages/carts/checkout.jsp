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
				
				$(':radio[name=deliveryType]').change(function() {
					$.getJSON('${webctx}/carts/setDeliveryType.action', { 'deliveryType': $(this).val() }, function(data) {
						if (data) {
							$('#freight').text(data.freight);
							$('#total').text(data.total);
						}
					}); 
				});
			});
		</script>
	</head>
	
	<body>
		<%@include file="/commons/header.jsp" %>
		
		<div id="content">
		   <div id="checkout">
				<div id="buyflow2"></div>
				<s:form action="confirm" namespace="/carts" theme="simple">
					<fieldset>
						<legend>收货人信息</legend>
						<p>
							<label for="name">收货人：</label>
							<s:textfield id="name" name="preparedOrder.receiver"></s:textfield>
						</p>
						<p>
							<label for="mobile">联系电话：</label>
							<s:textfield id="mobile" name="preparedOrder.mobile"></s:textfield>
						</p>
						<p>
							<label for="address">详细地址：</label>
							<s:textfield id="address" name="preparedOrder.shippingAddress"></s:textfield>
						</p>
						<p>
							<label for="zipcode">邮政编码：</label>
							<s:textfield id="zipcode" name="preparedOrder.zipcode"></s:textfield>
						</p>
					</fieldset>
					<fieldset id="delivery">
						<legend>送货方式</legend>
						<div id="delivery_type">
							<h4>送货方式</h4>
							<ul>
								<li>
									<input type="radio" id="delivery_normal" name="deliveryType" value="1" 
										<c:if test="${preparedOrder.deliveryType != '2' and preparedOrder.deliveryType != '3'}">checked</c:if>>
									<label for="delivery_normal">普通快递送货上门（支持货到付款）</label>
									
									<label>送货上门时间：</label>
									<s:select name="preparedOrder.deliveryTime" list="#{'1': '时间不限', '2': '周一至周五', '3': '周末及法定假日'}">
									</s:select>
								</li>
								<li>
									<input type="radio" id="delivery_fast" name="deliveryType" value="2"
										<c:if test="${preparedOrder.deliveryType == '2'}">checked</c:if>>
									<label for="delivery_fast">加急快递送货上门（支持货到付款）</label>
								</li>
								<li>
									<input type="radio" id="delivery_post" name="deliveryType" value="3"
										<c:if test="${preparedOrder.deliveryType == '3'}">checked</c:if>>
									<label for="delivery_post">普通邮递（不支持货到付款）</label>
								</li>
							</ul>
						</div>
						<div id="delivery_fee">
							<h4>运费</h4>
							<ul>
								<li>￥5.00（订单满￥100.00免运费）</li>
								<li>￥10.00</li>
								<li>￥5.00（订单满￥100.00免运费）</li>
							</ul>
						</div>
					</fieldset>
					<fieldset id="payment">
						<legend>付款方式</legend>
						<ul>
							<li>
								<input type="radio" id="payment_cash" name="payemntType" value="1" 
									<c:if test="${preparedOrder.paymentType != '2' and preparedOrder.paymentType != '3'}">checked</c:if>>
								<label for="payment_cash">
									货到付款<span class="tips">您需要在收货时用现金向送货员支付订单款项</span>
								</label>
							</li>
							<li>
								<input type="radio" id="payment_net" name="payemntType" value="2"
									<c:if test="${preparedOrder.paymentType == '2'}">checked</c:if>>
								<label for="payment_net">
									网上支付<span class="tips">您需要先拥有一张已开通网上支付功能的银行卡</span>
								</label>
							</li>
							<li>
								<input type="radio" id="payment_post" name="payemntType" value="3"
									<c:if test="${preparedOrder.paymentType == '3'}">checked</c:if>>
								<label for="payment_post">
									邮局汇款<span class="tips">您需要先去邮局汇款，所购商品将在款项到达eShop帐户后发出，到款时间一般为2-7个工作日</span>
								</label>
							</li>
							<li>
								<input type="radio" id="payment_transfer" name="payemntType" value="4"
									<c:if test="${preparedOrder.paymentType == '4'}">checked</c:if>>
								<label for="payment_transfer">
									银行转帐<span class="tips">您需要先去银行转帐，所购商品将在款项到达eShop帐户后发出，到款时间一般为1-5个工作日</span>
								</label>
							</li>
						</ul>
					</fieldset>
					<div id="cart_checkout">
						<h3>订单明细</h3>
						<div id="back_to_cart">
							<s:a action="show" namespace="/carts">回到购物车，删除或添加商品</s:a>
						</div>
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
								运费：<span id="freight" class="total_sum"><fmt:formatNumber value="${preparedOrder.deliveryFee}" type="currency" /></span>
							</p>
							<p class="final_sum">
								您需为订单支付：<span id="total" class="total_sum"><fmt:formatNumber value="${container.shoppingCart.totalPrice + preparedOrder.deliveryFee}" type="currency" /></span>
							</p>
						</div>
					</div>
					<input id="order_submit_button" type="image" src="${webctx}/images/order_submit.gif">
				</s:form>
			</div>
			<div class="clear"></div>
		</div>
		
		<%@include file="/commons/footer.jsp" %>
	</body>
</html>
