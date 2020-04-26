<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!-- 重量hx711感應資料 -->
<table width="100%"
	class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="50%"><s:message code='modDataName' /></th>
			<th width="50%"><s:message code='senHx711Weight' /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="senHx711" items="${senHx711List}">
			<tr>
				<!-- 感應裝置名稱 -->
				<td>${senHx711.modData.modName}</td>
				<!-- 重量 -->
				<td>${senHx711.weight}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
