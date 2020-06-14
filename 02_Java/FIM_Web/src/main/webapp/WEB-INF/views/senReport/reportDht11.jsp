<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	
</script>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">溫濕度報表</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">

				<div class="alert alert-${css} alert-dismissible" role="alert">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="col-sm-2 control-label"><s:message
								code='ipAddress' /></label>
						<div class="col-sm-10"></div>
					</div>

				</div>

				<!-- 儲存按鈕 -->
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn-lg btn-primary pull-left">
							查詢</button>
					</div>
				</div>
				<!-- /.panel-heading -->

				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
</div>

