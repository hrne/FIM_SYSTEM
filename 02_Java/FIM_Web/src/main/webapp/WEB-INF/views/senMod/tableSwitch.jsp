<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!-- 電源開關感應資料 -->
<table width="100%"
	class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="20%"><s:message code='modDataName' /></th>
			<th width="30%"><s:message code='senSwitchBatteryVolt' /></th>
			<th width="50%"><s:message code='senSwitchPowerStatus' /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="senSwitch" items="${senSwitchList}">
			<tr>
				<!-- 感應裝置名稱 -->
				<td>${senSwitch.modData.modName}</td>
				<!-- 電池電力(v) -->
				<td>${senSwitch.batteryVolt}</td>
				<!-- 電源開關狀態 -->
				<td><input id="toggle-event" type="checkbox"
					data-toggle="toggle" data-size="small"></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
