<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>FIM_SYSTEM</title>

<!-- Bootstrap Core CSS -->
<link href="<c:url value='/vendor/bootstrap/css/bootstrap.min.css'/>"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="<c:url value='/vendor/metisMenu/metisMenu.min.css'/>"
	rel="stylesheet">

<!-- DataTables CSS -->
<link
	href="<c:url value='/vendor/datatables-plugins/dataTables.bootstrap.css'/>"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link
	href="<c:url value='/vendor/datatables-responsive/dataTables.responsive.css'/>"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value='/dist/css/sb-admin-2.css'/>" rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<c:url value='/vendor/font-awesome/css/font-awesome.min.css'/>"
	rel="stylesheet" type="text/css">

<!-- Morris Charts CSS -->
<link href="<c:url value='/vendor/morrisjs/morris.css'/>"
	rel="stylesheet">

<!-- bootstrap-table CSS -->
<link href="<c:url value='/vendor/bootstrap-table/dist/bootstrap-table.min.css'/>"
	rel="stylesheet">

<!-- bootstrap-toggle CSS -->
<link href="<c:url value='/vendor/bootstrap-toggle/css/bootstrap-toggle.css'/>"
	rel="stylesheet">	
	
<!-- bootstrap-editable CSS -->
<link href="<c:url value='/vendor/bootstrap3-editable/css/bootstrap-editable.css'/>"
	rel="stylesheet">	

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- jQuery -->
<script src="<c:url value='/vendor/jquery/jquery.min.js'/>"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value='/vendor/bootstrap/js/bootstrap.min.js'/>"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<c:url value='/vendor/metisMenu/metisMenu.min.js'/>"></script>

<!-- DataTables JavaScript -->
<script
	src="<c:url value='/vendor/datatables/js/jquery.dataTables.min.js'/>"></script>
<script
	src="<c:url value='/vendor/datatables-plugins/dataTables.bootstrap.min.js'/>"></script>
<script
	src="<c:url value='/vendor/datatables-responsive/dataTables.responsive.js'/>"></script>

<!-- Custom Theme JavaScript -->
<script src="<c:url value='/dist/js/sb-admin-2.js'/>"></script>

<!-- Flot Charts JavaScript -->
<script src="<c:url value='/vendor/flot/excanvas.min.js'/>"></script>
<script src="<c:url value='/vendor/flot/jquery.flot.js'/>"></script>
<script src="<c:url value='/vendor/flot/jquery.flot.pie.js'/>"></script>
<script src="<c:url value='/vendor/flot/jquery.flot.resize.js'/>"></script>
<script src="<c:url value='/vendor/flot/jquery.flot.time.js'/>"></script>
<script
	src="<c:url value='/vendor/flot-tooltip/jquery.flot.tooltip.min.js'/>"></script>
<script src="<c:url value='/data/flot-data.js'/>"></script>

<!-- Morris Charts JavaScript -->
<script src="<c:url value='/vendor/raphael/raphael.min.js'/>"></script>
<script src="<c:url value='/vendor/morrisjs/morris.min.js'/>"></script>
<script src="<c:url value='/data/morris-data.js'/>"></script>

<!-- Bootstrap-table.min JavaScript -->
<script src="<c:url value='/vendor/bootstrap-table/dist/bootstrap-table.min.js'/>"></script>

<!-- Bootstrap-table auto JavaScript -->
<script src="<c:url value='/vendor/bootstrap-table/dist/extensions/auto-refresh/bootstrap-table-auto-refresh.min.js'/>"></script>

<!-- Bootstrap-table editable JavaScript -->
<script src="<c:url value='/vendor/bootstrap-table/dist/extensions/editable/bootstrap-table-editable.js'/>"></script>

<!-- Bootstrap-toggle.min JavaScript -->
<script src="<c:url value='/vendor/bootstrap-toggle/js/bootstrap-toggle.js'/>"></script>

<!-- Bootstrap-editable.min JavaScript -->
<script src="<c:url value='/vendor/bootstrap3-editable/js/bootstrap-editable.min.js'/>"></script>

<!-- Highcharts JavaScript -->
<script src="<c:url value='/vendor/highcharts/highcharts.js'/>"></script>
<script src="<c:url value='/vendor/highcharts/highcharts-more.js'/>"></script>
<script src="<c:url value='/vendor/highcharts/highcharts-3d.js'/>"></script>


</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value='/index'/>">FIM_SYSTEM</a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">
				<!-- /.dropdown -->

				<!-- /.dropdown -->

				<!-- /.dropdown -->

				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<c:import url="/decorators/menuTree.jsp" />
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<decorator:body />

	</div>
	<!-- /#wrapper -->

</body>

</html>







