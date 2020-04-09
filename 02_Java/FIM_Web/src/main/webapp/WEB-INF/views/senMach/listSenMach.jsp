<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
	$(document).ready(function() {
		$('#dataTables-senMachs').DataTable({
			responsive : true
		});
	});
</script>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<s:message code='senMachList' />
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
						id="dataTables-senMachs">
						<thead>
							<tr>
								<th><spring:message code='id' /></th>
								<th><spring:message code='name' /></th>
								<th><spring:message code='ipAddress' /></th>
								<th><spring:message code='enabled' /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="senMach" items="${senMachs}">
								<tr>
									<td>${senMach.id}</td>
									<td>${senMach.machName}</td>
									<td>${senMach.ip}</td>
									<td>${senMach.shonEnableName}</td>							
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
