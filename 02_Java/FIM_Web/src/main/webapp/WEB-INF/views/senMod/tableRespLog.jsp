<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!-- 感應紀錄資料 -->
<table width="100%"
	class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="35%">裝置名稱</th>
			<th width="35%">模組名稱</th>
			<th width="30%">連線狀態</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="senHx711Dto" items="${senHx711DtoList}">
			<tr>
				<!-- 感應裝置名稱 -->
				<td>${senHx711Dto.modMainName}</td>
				<!-- 重量(g) -->
				<td class='${senHx711Dto.classWeight}'>${senHx711Dto.weight}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
