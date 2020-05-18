<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!-- 溫濕度Dht11資料 -->
<table width="100%"
	class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="25%" class="text-center"><s:message code='modDataName' /></th>
			<th width="25%" class="text-center"><s:message code='senDht11Humidity' /></th>
			<th width="25%" class="text-center"><s:message code='senDht11TempCal' /></th>
			<th width="25%" class="text-center"><s:message code='senDht11TempFah' /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="senDht11Dto" items="${senDht11DtoList}">
			<tr class="text-center">
				<!-- 感應裝置名稱 -->
				<td>${senDht11Dto.modMainName}</td>
				<!-- 濕度 -->
				<td class='${senDht11Dto.classHumidity}'>${senDht11Dto.humidity}</td>
				<!-- 溫度(攝氏C) -->
				<td class='${senDht11Dto.classTempCal}'>${senDht11Dto.tempCal}</td>
				<!-- 溫度(華氏F) -->
				<td class='${senDht11Dto.classTempFah}'>${senDht11Dto.tempFah}</td>

			</tr>
		</c:forEach>
	</tbody>
</table>
