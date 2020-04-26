<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 新增/修改感應裝置 -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<c:choose>
				<c:when test="${modDataDto['new']}">
					<h1 class="page-header">
						<s:message code='modDataAdd' />
					</h1>
				</c:when>
				<c:otherwise>
					<h1 class="page-header">
						<s:message code='modDataUpdate' />
					</h1>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<s:url value="/modData/save" var="modDataActionUrl" />
							<sf:form class="form-horizontal" method="post"
								modelAttribute="modDataDto" action="${modDataActionUrl}">
								<sf:hidden path="id" />
								<!-- 感應裝置名稱 -->
								<s:bind path="modName">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="col-sm-2 control-label"><s:message
												code='name' /></label>
										<div class="col-sm-10">
											<sf:input path="modName" class="form-control "
												placeholder="modName" />
											<sf:errors path="modName" class="control-label" />
										</div>
									</div>
								</s:bind>
								<!-- ip位址 -->
								<s:bind path="ipAddress">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="col-sm-2 control-label"><s:message
												code='ipAddress' /></label>
										<div class="col-sm-10">
											<sf:input path="ipAddress" class="form-control "
												placeholder="ipAddress" />
											<sf:errors path="ipAddress" class="control-label" />
										</div>
									</div>
								</s:bind>
								<!-- 是否啟用 -->
								<s:bind path="modEnable">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="col-sm-2 control-label"><s:message
												code='enabled' /></label>
										<div class="col-sm-10">
											<label class="radio-inline"> <sf:radiobutton
													path="modEnable" value="true" /> <s:message
													code='enable_Y' />
											</label> <label class="radio-inline"> <sf:radiobutton
													path="modEnable" value="false" /> <s:message
													code='enable_N' />
											</label> <br />
											<sf:errors path="modEnable" class="control-label" />
										</div>
									</div>
								</s:bind>
								<!-- 感應模組 -->
								<s:bind path="modSenIDs">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="col-sm-2 control-label"><s:message
												code='modSen' /></label>
										<div class="col-sm-10">
											<sf:checkboxes path="modSenIDs" items="${senList}"
												element="label class='checkbox-inline'" />
											<br />
											<sf:errors path="modSenIDs" class="control-label" />
										</div>
									</div>
								</s:bind>
								<!-- 儲存按鈕 -->
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn-lg btn-primary pull-right">
											<s:message code='save' />
										</button>
									</div>
								</div>
							</sf:form>
						</div>
					</div>
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