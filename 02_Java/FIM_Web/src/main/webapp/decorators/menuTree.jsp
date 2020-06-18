<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<ul class="nav" id="side-menu">

	<c:if test='${applicationScope["settings"]["demo.enable"] == "true"}'>

		<li class="sidebar-search">
			<div class="input-group custom-search-form">
				<input type="text" class="form-control" placeholder="Search...">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div> <!-- /input-group -->
		</li>
		<li><a href="<c:url value='/index'/>"><i
				class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
		<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>
				Charts<span class="fa arrow"></span></a>
			<ul class="nav nav-second-level">
				<li><a href="<c:url value='/flot'/>">Flot Charts</a></li>
				<li><a href="<c:url value='/morris'/>">Morris.js Charts</a></li>
			</ul> <!-- /.nav-second-level --></li>
		<li><a href="<c:url value='/tables'/>"><i
				class="fa fa-table fa-fw"></i> Tables</a></li>
		<li><a href="<c:url value='/forms'/>"><i
				class="fa fa-edit fa-fw"></i> Forms</a></li>
		<li><a href="#"><i class="fa fa-wrench fa-fw"></i> UI
				Elements<span class="fa arrow"></span></a>
			<ul class="nav nav-second-level">
				<li><a href="<c:url value='/panels-wells'/>">Panels and
						Wells</a></li>
				<li><a href="<c:url value='/buttons'/>">Buttons</a></li>
				<li><a href="<c:url value='/notifications'/>">Notifications</a>
				</li>
				<li><a href="<c:url value='/typography'/>">Typography</a></li>
				<li><a href="<c:url value='/icons'/>"> Icons</a></li>
				<li><a href="<c:url value='/grid'/>">Grid</a></li>
			</ul> <!-- /.nav-second-level --></li>
		<li><a href="#"><i class="fa fa-sitemap fa-fw"></i>
				Multi-Level Dropdown<span class="fa arrow"></span></a>
			<ul class="nav nav-second-level">
				<li><a href="#">Second Level Item</a></li>
				<li><a href="#">Second Level Item</a></li>
				<li><a href="#">Third Level <span class="fa arrow"></span></a>
					<ul class="nav nav-third-level">
						<li><a href="#">Third Level Item</a></li>
						<li><a href="#">Third Level Item</a></li>
						<li><a href="#">Third Level Item</a></li>
						<li><a href="#">Third Level Item</a></li>
					</ul> <!-- /.nav-third-level --></li>
			</ul> <!-- /.nav-second-level --></li>
		<li><a href="#"><i class="fa fa-files-o fa-fw"></i> Sample
				Pages<span class="fa arrow"></span></a>
			<ul class="nav nav-second-level">
				<li><a href="<c:url value='/blank'/>">Blank Page</a></li>
				<li><a href="<c:url value='/login'/>">Login Page</a></li>
			</ul> <!-- /.nav-second-level --></li>

	</c:if>

	<!-- 感應裝置設定 -->
	<li><a href="#"><i class="fa fa-files-o fa-fw"></i> <s:message
				code='modData' /><span class="fa arrow"></span></a>
		<ul class="nav nav-second-level collapse">
			<!-- 新增感應裝置 -->
			<li><a href="<c:url value='/modData/showAddModMain'/>"><s:message
						code='modDataAdd' /></a></li>
			<!-- 感應裝置列表 -->
			<li><a href="<c:url value='/modData/showAllModMain'/>"><s:message
						code='modDataList' /></a></li>

		</ul></li>

	<!-- 感應模組 -->
	<li><a href="#"><i class="fa fa-files-o fa-fw"></i> <s:message
				code='modSen' /><span class="fa arrow"></span></a>
		<ul class="nav nav-second-level collapse">
			<!-- 感應模組警示值 -->
			<li><a href="<c:url value='/modSen/showAllSenParmLimit'/>"><s:message
						code='modSenLimit' /></a></li>
		</ul></li>

	<!-- 報表資料 -->
	<li><a href="#"><i class="fa fa-files-o fa-fw"></i> 報表資料<span
			class="fa arrow"></span></a>
		<ul class="nav nav-second-level collapse">
			<!-- 溫濕度報表 -->
			<li><a href="<c:url value='/senReport/reportDht11'/>">溫濕度報表</a></li>
			<!-- 重量報表報表 -->
			<li><a href="<c:url value='/senReport/reportHx711'/>">重量報表</a></li>
			<!-- 火災警報感應報表 -->
			<li><a href="<c:url value='/senReport/reportFireAlm'/>">火災警報感應報表</a></li>
		</ul></li>

</ul>


