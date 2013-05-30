<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>

<div id="account">
	<h2>
		我的账户
	</h2>
	<ul>
		<li>
			<s:a action="my" namespace="/accounts" cssClass="info">基本信息</s:a>
		</li>
		<li>
			<s:a action="password" namespace="/accounts" cssClass="password">修改密码</s:a>
		</li>
		<li>
			<s:a action="list" namespace="/orders" cssClass="list">订单查询</s:a>
		</li>
	</ul>
</div>