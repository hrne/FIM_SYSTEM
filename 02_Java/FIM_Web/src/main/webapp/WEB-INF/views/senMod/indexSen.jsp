<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	$(function() {
		setInterval(refresh, 3000); //每3秒刷新一次
		function refresh() {
			$.ajax({
				url : 'senMod/listDht11',
				success : function(data) {
					$('#dht11').html(data);
				}
			});

			$.ajax({
				url : 'senMod/listHx711',
				success : function(data) {
					$('#hx711').html(data);
				}
			});
			
			$.ajax({
				url : 'senMod/listSwitch',
				success : function(data) {
					$('#switch').html(data);
				}
			});
		}
	})
</script>

<!-- 首頁 -->
<div id="page-wrapper">
	<!-- 標題 -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<s:message code='Dashboard' />
			</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->

	<!-- /.row -->
	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-default">
				<!-- 溫濕度資料 -->
				<div class="panel-heading ">
					<h4 class="text-info">
						<s:message code='senDht11' />
					</h4>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive" id="dht11">
						<%@include file="tableDht11.jsp"%>
					</div>
					<!-- /.table-responsive -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-6 -->
		<div class="col-lg-6">
			<div class="panel panel-default">
				<!-- 重量資料 -->
				<div class="panel-heading">
					<h4 class="text-info">
						<s:message code='senHx711' />
					</h4>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive" id="hx711">
						<%@include file="tableHx711.jsp"%>
					</div>
					<!-- /.table-responsive -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-6 -->
	</div>

	<!-- /.row -->
	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-default">
				<!-- 電源開關資料 -->
				<div class="panel-heading ">
					<h4 class="text-info">
						<s:message code='senSwitch' />
					</h4>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive" id="switch">
						<%@include file="tableSwitch.jsp"%>
					</div>
					<!-- /.table-responsive -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-6 -->
		<div class="col-lg-6">
			<div class="panel panel-default">
				<!-- -->
				<div class="panel-heading">

				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">

					<!-- /.table-responsive -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-6 -->
	</div>

</div>
<!-- /#page-wrapper -->
