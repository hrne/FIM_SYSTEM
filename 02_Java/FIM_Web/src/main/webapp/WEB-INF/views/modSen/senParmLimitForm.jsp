<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- 修改感應裝置 -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<s:message code='senParmLimitUpdate' />
			</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<s:url value="/modSen/saveSenParmLimit" var="saveUrl" />
					<sf:form class="form-horizontal" method="post"
						modelAttribute="modSenDto" action="${saveUrl}">
						<sf:hidden path="id" />
						<table class="table table-striped table-bordered table-hover">
							<tbody>
								<tr>
									<td class="bg-info text-white text-center" width="25%"><s:message
											code='senName' /></td>
									<td width="25%">${modSenDto.senName}</td>
									<td class="bg-info text-white text-center" width="25%"><s:message
											code='senCode' /></td>
									<td width="25%">${modSenDto.senCode}</td>
								</tr>
							</tbody>
						</table>

						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="text-center" width="20%"><s:message
											code='parmName' /></th>
									<th class="text-center" width="20%"><s:message
											code='parmCode' /></th>
									<th class="text-center" width="20%"><s:message
											code='parmUpper' /></th>
									<th class="text-center" width="20%"><s:message
											code='parmLower' /></th>
									<th class="text-center" width="20%"><s:message
											code='enabled' /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="modParmDataDto"
									items="${modSenDto.modParmDataDtoList}">
									<tr>
										<!-- 參數名稱 -->
										<td class="text-center">${modParmDataDto.parmName}</td>
										<!-- 參數代號 -->
										<td class="text-center">${modParmDataDto.parmCode}</td>
										<!-- 上限警示值-->
										<td><input type="text" class="text-right"
											onkeyup="this.value=this.value.replace(/\D/g,'')"
											value='${modParmDataDto.upperLimit}'></td>
										<!-- 下現警示值 -->
										<td><input type="text" class="text-right"
											onkeyup="this.value=this.value.replace(/\D/g,'')"
											value='${modParmDataDto.lowerLimit}'></td>
										<!-- 是否啟用 -->
										<td>${modParmDataDto.limitEnabled}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 儲存按鈕 -->
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn-lg btn-primary pull-right">
									<s:message code='save' />
								</button>
							</div>
						</div>
					</sf:form>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->