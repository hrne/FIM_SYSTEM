<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 新增/修改感應器 -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<c:choose>
				<c:when test="${senMachDto['new']}">
					<h1 class="page-header">
						<s:message code='senMachAdd' />
					</h1>
				</c:when>
				<c:otherwise>
					<h1 class="page-header">
						<s:message code='senMachUpdate' />
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
							<s:url value="/senMach/save" var="senMachActionUrl" />
							<sf:form class="form-horizontal" method="post"
								modelAttribute="senMachDto" action="${senMachActionUrl}">
								<sf:hidden path="id" />
								<!-- 感應器名稱 -->
								<s:bind path="machName">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="col-sm-2 control-label"><s:message
												code='name' /></label>
										<div class="col-sm-10">
											<sf:input path="machName" class="form-control "
												placeholder="machName" />
											<sf:errors path="machName" class="control-label" />
										</div>
									</div>
								</s:bind>
								<!-- ip位址 -->
								<s:bind path="ip">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="col-sm-2 control-label"><s:message
												code='ipAddress' /></label>
										<div class="col-sm-10">
											<sf:input path="ip" class="form-control " placeholder="ip" />
											<sf:errors path="ip" class="control-label" />
										</div>
									</div>
								</s:bind>
								<!-- 是否啟用 -->
								<s:bind path="machEnable">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="col-sm-2 control-label">是否啟用</label>
										<div class="col-sm-10">
											<label class="radio-inline"> <sf:radiobutton
													path="machEnable" value="true" /> 是
											</label> <label class="radio-inline"> <sf:radiobutton
													path="machEnable" value="false" /> 否
											</label> <br />
											<sf:errors path="machEnable" class="control-label" />
										</div>
									</div>
								</s:bind>
								<!-- 感應裝置 -->
								<s:bind path="senModsID">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="col-sm-2 control-label">感應裝置</label>
										<div class="col-sm-10">
											<sf:checkboxes path="senModsID" items="${senModList}"
												element="label class='checkbox-inline'" />
											<br />
											<sf:errors path="senModsID" class="control-label" />
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