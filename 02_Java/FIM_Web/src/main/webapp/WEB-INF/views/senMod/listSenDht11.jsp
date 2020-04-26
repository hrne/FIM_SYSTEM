<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	$(function() {
		setInterval(refresh, 3000); //每3秒刷新一次
		function refresh() {
			$.ajax({
				url : 'listDht11',
				success : function(data) {
					console.log('sucess');
					$('#dht11').html(data);
				}
			});
		}
	})
</script>

<!-- 溫濕度資料 -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<s:message code='senDht11' />
			</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<!-- /.panel-heading -->
				<div class="panel-body" id="dht11">
					<%@include file="tableDht11.jsp"%>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
</div>

<div id="page-wrapper">
<!-- 
	<iframe src="http://192.168.50.238/"  width="1024px" height="768px"> </iframe> -->
</div>