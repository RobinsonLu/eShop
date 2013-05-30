<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="cn.haohaowo.common.Constants"%>
<%@include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>电子书城</title>
		<link rel="shortcut icon" href="${webctx}/images/favicon.ico" >
		<link rel="stylesheet" type="text/css" href="${webctx}/stylesheets/style.css" media="all" />
		<script type="text/javascript" src="${webctx}/javascripts/jquery-1.4.1.min.js"></script>
		<script type="text/javascript" src="${webctx}/javascripts/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${webctx}/javascripts/application.js"></script>
		
		<script type="text/javascript">
			$(function() {
				$('#info_form').validate({
					rules: {
						email: {
							required: true,
							email: true,
							remote: {
								url: "${webctx}/accounts/unique/email/validate",
								data: {
									id: ${container.account.id}
								}
							}
						},
						mobile: "required",
						address: "required",
						zipcode: "required"
					},
					messages: {
						email: {
							required: "请输入邮箱",
							email: "邮箱无效",
							remote: "邮箱已经存在"
						},
						mobile: "请输入电话",
						address: "请输入送货地址",
						zipcode: "请输入邮编" 
					}
				});
			});
		</script>
	</head>
	
	<body class="two_col my">
		<%@include file="/commons/header.jsp" %>
		<div id="content">
			<%@include file="../commons/_menu.jsp" %>
			
			<div id="basic_info">
				<%@include file="../commons/_notice.jsp" %>
				
				<s:form id="info_form" action="update" namespace="/accounts" theme="simple">
					<fieldset>
						<legend>基本信息</legend>
						<p id="note">
							<span class="required">*</span>为必填项
						</p>
				
						<p>
							<label>用户名：</label>
							<span><s:property value="account.username"/></span>
						</p>
						<p>
							<label for="email"><span class="required">*</span>邮箱：</label>
							<s:textfield id="email" name="account.email"></s:textfield>
							<span class="tips">全站唯一</span>
						</p>
						<p>
							<label for="name">真实姓名：</label>
							<s:textfield id="name" name="account.truename"></s:textfield>
						</p>
						<p>
							<label>性别：</label>
							<input id="male" type="radio" name="account.sex" value="M" <c:if test="${account.sex == 'M'}">checked="checked"</c:if>>
							<label class="sex" for="male">男</label>
							<input id="female" type="radio" name="account.sex" value="F" <c:if test="${account.sex == 'F'}">checked="checked"</c:if>>
							<label class="sex" for="female">女</label>
						</p>
						<p>
							<label for="mobile"><span class="required">*</span>电话：</label>
							<s:textfield id="mobile" name="account.mobile"></s:textfield>
						</p>
						<p>
							<label for="address"><span class="required">*</span>送货地址：</label>
							<s:textfield id="address" name="account.address"></s:textfield>
						</p>
						<p>
							<label for="zipcode"><span class="required">*</span>邮政编码：</label>
							<s:textfield id="zipcode" name="account.zipcode"></s:textfield>
						</p>
						<p>
							<input id="basic_info_submit" type="image" src="${webctx}/images/confirm.gif">
						</p>
					</fieldset>
				</s:form>
			</div>
			<div class="clear"></div>
		</div>
		
		<%@include file="/commons/footer.jsp" %>
	</body>
</html>

<%
	session.removeAttribute(Constants.NOTICE);
 %>
