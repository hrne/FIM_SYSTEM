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
			<th width="20%" class="text-center"><s:message code='modDataName' /></th>
			<th width="40%" class="text-center">火光警示</th>
			<th width="40%" class="text-center">一氧化碳警示</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="senFireAlmDto" items="${senFireAlmDtoList}">
			<tr class="text-center">
				<!-- 感應裝置名稱 -->
				<td>${senFireAlmDto.modMainName}</td>
				<!-- 火光警示-->
				<td class='${senFireAlmDto.classFireStatus}'>${senFireAlmDto.fireStatusName}</td>
				<!-- 一氧化碳警示-->
				<td class='${senFireAlmDto.classMq7Status}'>${senFireAlmDto.mq7StatusName}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
