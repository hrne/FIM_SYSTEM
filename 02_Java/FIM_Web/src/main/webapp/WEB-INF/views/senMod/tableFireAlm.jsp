<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!-- 火災警報感應資料資料 -->
<table width="100%"
	class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="20%"><s:message code='modDataName' /></th>
			<th width="40%">火光警示</th>
			<th width="40%">一氧化碳警示</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="senFireAlmDto" items="${senFireAlmDtoList}">
			<tr>
				<!-- 感應裝置名稱 -->
				<td>${senFireAlmDto.modMainName}</td>
				<!-- 火光警示-->
				<td>${senFireAlmDto.fireStatusName}</td>
				<!-- 一氧化碳警示-->
				<td>${senFireAlmDto.mq7StatusName}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
