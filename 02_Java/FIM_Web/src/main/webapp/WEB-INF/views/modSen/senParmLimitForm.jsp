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
			<h1 class="page-header">修改警示值</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">

						<s:url value="/modSen/saveModMain" var="saveUrl" />
						<sf:form class="form-horizontal" method="post"
							modelAttribute="modSenDto" action="${saveUrl}">
							<sf:hidden path="id" />
							<div class="col-lg-6">
								<!-- 感應裝置名稱 -->

								<label class="col-sm-2 control-label"><s:message
										code='name' /></label>
								<div class="col-sm-10"></div>
							</div>
							<!-- ip位址 -->
							<div class="col-lg-6">
								<label class="col-sm-2 control-label"><s:message
										code='ipAddress' /></label>
								<div class="col-sm-10"></div>


							</div>
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