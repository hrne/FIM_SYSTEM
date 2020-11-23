<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	$(function() {
		setInterval(refresh, 500); //每3秒刷新一次
		function refresh() {
			$.ajax({
				url : 'senMod/showAllDht11',
				success : function(data) {
					$('#dht11').html(data);
				}
			});

			$.ajax({
				url : 'senMod/showAllHx711',
				success : function(data) {
					$('#hx711').html(data);
				}
			});

			$.ajax({
				url : 'senMod/showAllFireAlm',
				success : function(data) {
					$('#fireAlm').html(data);
				}
			});

			$.ajax({
				url : 'senMod/showAllRespLog',
				success : function(data) {
					$('#respLog').html(data);
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
				<!-- 火災警報感應資料 -->
				<div class="panel-heading ">
					<h4 class="text-info">火災警報感應資料</h4>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive" id="fireAlm">
						<%@include file="tableFireAlm.jsp"%>
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
				<!-- 感應紀錄資料 -->
				<div class="panel-heading ">
					<h4 class="text-info">
						<s:message code='respLog' />
					</h4>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive" id="respLog">
						<%@include file="tableRespLog.jsp"%>
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
				<div class="panel-heading ">

				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive">

					</div>
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
