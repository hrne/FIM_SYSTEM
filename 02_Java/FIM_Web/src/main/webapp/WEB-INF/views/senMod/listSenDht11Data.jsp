<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<table width="100%"
	class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th><s:message code='senMachName' /></th>
			<th><s:message code='senDht11TempCal' /></th>
			<th><s:message code='senDht11Humidity' /></th>
			<th><s:message code='updateDate' /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="senDht11" items="${senDht11List}">
			<tr>
				<!-- 感應器名稱 -->
				<td>${senDht11.modData.modName}</td>
				<!-- 溫度(攝氏H) -->
				<td>${senDht11.tempCal}</td>
				<!-- 濕度 -->
				<td>${senDht11.humidity}</td>
				<!-- 更新時間 -->
				<td>${senDht11.updateDate}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
