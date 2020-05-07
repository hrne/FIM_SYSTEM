<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	$(function() {
		//這段須放在表格初始化之前
		function addFunctionAlty(value, row, index) {
			return [
					'<button type="button" class="RoleOfedit btn btn-primary  btn-sm" style="margin-right:15px;">修改</button>', ]
					.join('');
		}
		window.operateEvents = {
			'click .RoleOfedit' : function(e, value, row, index) {
				alert(row.id);
			}
		};

		$("#display_result").bootstrapTable({
			url : 'showModSen',
			method : 'get',
			dataType : "json",
			detailView : true,//啟用父子表
			striped : true, // 隔行加亮
			columns : [ {
				title : '感應模組名稱',
				align : "center",
				field : 'senName',
			}, {
				title : '感應模組代號',
				align : "center",
				field : 'senCode'
			}, {//表格中增加按钮  
				field : 'operate',
				title : '操作',
				align : 'center',
				events : operateEvents,//按鈕事件
				formatter : addFunctionAlty //按鈕格式
			
			} ],
			//無限讀取子表，直到沒有資料
			onExpandRow : function(index, row, $Subdetail) {
				initSubTable(index, row, $Subdetail);
			}
		});
		//初始化子表(無限循環)
		initSubTable = function(index, row, $detail) {
			var parentid = row.id;
			var cur_table = $detail.html('<table></table>').find('table');
			$(cur_table).bootstrapTable({
				url : 'showModParm',
				method : 'get',
				striped : true, // 隔行加亮
				queryParams : {
					id : parentid
				},
				ajaxOptions : {
					id : parentid
				},
				uniqueId : "id",
				columns : [ {
					title : '參數名稱',
					align : "center",
					field : 'parmName'
				}, {
					title : '參數代號',
					align : "center",
					field : 'parmCode'
				}, {
					title : '上限警示值',
					align : "center",
					field : 'upperLimit'
				}, {
					title : '下限警示值',
					align : "center",
					field : 'lowerLimit'
				}, {
					title : '狀態',
					align : "center",
					field : 'shonEnableName'
				} ],
				//無限讀取子表，直到沒有資料
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
			<h1 class="page-header">
				<s:message code='modSenLimit' />
			</h1>
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

