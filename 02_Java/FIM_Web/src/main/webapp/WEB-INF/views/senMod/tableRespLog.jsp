<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	$(function() {

		function refreshRespLog() {
			$.ajax({
				success : function(data) {
					$('#display_respLog').bootstrapTable('refresh');
				}
			});
		}

		$(document).ready(function() {
			createRespLogTable();
		});
		function createRespLogTable() {
			$("#display_respLog").bootstrapTable({
				url : 'senMod/showAllRespLog',
				method : 'get',
				dataType : "json",
				autoRefreshInterval="3",
				striped : true, // 隔行加亮
				idField : 'modMainId',//指定主键列  
				columns : [ {
					title : '模組名稱',
					field : 'senName',
				}, {
					title : '連線狀態',
					field : 'respStatusName'
				} ]
			});
		}
		setInterval(refreshRespLog, 3000); //每3秒刷新一次
	})
</script>

<!-- 感應紀錄資料 -->
<table width="100%" id="display_respLog"
	class="table table-striped table-bordered table-hover">

</table>
