<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	$(function() {
		$("#display_result").bootstrapTable({
			url : 'listModSen',
			method : 'get',
			dataType: "json",
            pagination: true,    // 显示页码等信息 
            showColumns: false,  // 选择显示的列 
            clickToSelect: true, //在点击行时，自动选择rediobox 和 checkbox
            pageNumber: 1,         //首页页码
            pageSize: 10,           // 当前分页值 
            pageList: [10, 20],  // 分页选页 
            cache: false, // 不缓存
            striped: true, // 隔行加亮
			columns : [ {
				field : 'senName',
				title : '感應模組名稱',
			}, {
				field : 'senCode',
				title : '感應模組代號',
			} ]
		});
		//初始化子表格(无线循环)
		initSubTable = function(index, row, $detail) {
			var parentid = row.id;
			var cur_table = $detail.html('<table></table>').find('table');
			$(cur_table).bootstrapTable({
				url : 'listParm',
				method : 'get',
				queryParams : {
					id : parentid
				},
				ajaxOptions : {
					id : parentid
				},
				uniqueId : "id",
				striped : true, //是否显示行间隔色
				pagination : false,//显示分页

				columns : [ {
					field : 'parmName',
					title : '參數名稱'
				}, {
					field : 'parmCode',
					title : '參數代號'
				}],
				//无线循环取子表，直到子表里面没有记录
				onExpandRow : function(index, row, $Subdetail) {
					initSubTable(index, row, $Subdetail);
				}
			});
		};
	})
</script>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">參數修改</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<c:if test="${not empty msg}">
					<div class="alert alert-${css} alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>${msg}</strong>
					</div>
				</c:if>

				<!-- /.panel-heading -->
				<div class="panel-body">
					<table width="100%"
						class="table table-striped table-bordered table-hover"
						id="display_result">


					</table>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
</div>

