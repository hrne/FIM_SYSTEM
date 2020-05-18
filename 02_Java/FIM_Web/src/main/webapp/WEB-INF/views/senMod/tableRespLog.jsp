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
			<th width="35%" class="text-center">裝置名稱</th>
			<th width="35%" class="text-center">模組名稱</th>
			<th width="30%" class="text-center">連線狀態</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="modRespLogDto" items="${modRespLogDtoList}">
			<tr class="text-center">
				<!-- 感應裝置名稱 -->
				<td>${modRespLogDto.modMainName}</td>
				<!-- 模組名稱 -->
				<td>${modRespLogDto.modSenName}</td>
				<!-- 連線狀態 -->
				<td class='${modRespLogDto.classRespStatus}'>${modRespLogDto.respStatusName}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
