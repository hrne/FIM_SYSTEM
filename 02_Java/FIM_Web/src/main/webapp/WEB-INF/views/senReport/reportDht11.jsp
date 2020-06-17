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
				<div class="panel-body">
					<div class="row">
						<table class="table table-bordered"">
							<tbody>
								<tr class="text-center">
									<!-- 感應裝置名稱 -->
									<td>查詢期間</td>
									<td><input type="date" class="form-control" id="startTime"
										name="startTime" /></td>
									<td>至</td>
									<td><input type="date" class="form-control" id="startTime"
										name="startTime" /></td>
								</tr>
							
							</tbody>
						</table>
					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
</div>

