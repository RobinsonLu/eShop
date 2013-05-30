<%@ page language="java" pageEncoding="UTF-8"%>

<c:if test="${page.needPaged}">
	<div class="pagination">
		<pg:pager url="${page.url}" items="${page.totalCount}" maxPageItems="${page.pageSize}" 
			export="currentPageNumber=pageNumber">
			<pg:param name="keyword"/>
			<pg:param name="type"/>
			<pg:param name="title"/>
			<pg:param name="author"/>
			<pg:param name="publisher"/>
			<pg:param name="mixprice"/>
			<pg:param name="maxprice"/>
			
			<pg:index>
				<pg:first>
					<a href="${pageUrl}&pageNo=${pageNumber}">首页</a>
				</pg:first>
				<pg:prev>
					<a href="${pageUrl}&pageNo=${pageNumber}">上一页</a>
				</pg:prev>
				<pg:pages>
					<c:choose>
						<c:when test="${pageNumber == currentPageNumber}">
							<a href="${pageUrl}&pageNo=${pageNumber}" class="current">${pageNumber}</a>
						</c:when>
						<c:otherwise>
							<a href="${pageUrl}&pageNo=${pageNumber}">${pageNumber}</a>
						</c:otherwise>
					</c:choose>
				</pg:pages>
				<pg:next>
					<a href="${pageUrl}&pageNo=${pageNumber}">下一页</a>
				</pg:next>
				<pg:last>
					<a href="${pageUrl}&pageNo=${pageNumber}">尾页</a>
				</pg:last>
			</pg:index>
		</pg:pager>
	</div> 
</c:if>