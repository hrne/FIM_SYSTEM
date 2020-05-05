<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<script>
	$(document).ready(function() {
		$('#dataTables-listModDataDto').DataTable({
			responsive : true
		});
	});
</script>

<!-- 感應裝置清單 -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<s:message code='modDataList' />
			</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<c:if test="${not empty msg}">
					<div class="alert alert-${css} alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>${msg}</strong>
					</div>
				</c:if>

				<!-- /.panel-heading -->
				<div class="panel-body">
					<table width="100%"
						class="table table-striped table-bordered table-hover"
						id="dataTables-listModDataDto">
						<thead>
							<tr>
								<th><s:message code='id'/></th>
								<th><s:message code='name' /></th>
								<th><s:message code='ipAddress' /></th>
								<th><s:message code='enabled' /></th>
								<th><s:message code='modSen' /></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="modDataDto" items="${listModDataDto}">
								<tr>
									<!-- 編號 -->
									<td>${modDataDto.id}</td>
									<!-- 名稱 -->
									<td>${modDataDto.modName}</td>
									<!-- ip位址 -->
									<td>${modDataDto.ipAddress}</td>
									<!-- 是否啟用 -->
									<td>${modDataDto.shonEnableName}</td>
									<!-- 感應模組 -->
									<td><c:forEach var="showModSens"
											items="${modDataDto.showModSenList}" varStatus="loop">
												${showModSens} <c:if test="${not loop.last}">,</c:if>
										</c:forEach></td>
									<!-- 修改 按鈕 -->
									<td><s:url value="/modData/${modDataDto.id}/updateModData"
											var="updateUrl" />
										<button onclick="location.href='${updateUrl}'"
											class="btn btn-primary">
											<s:message code='update' />
										</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
</div>
