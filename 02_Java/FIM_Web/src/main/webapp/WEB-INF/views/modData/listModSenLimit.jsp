<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	$(function() {
		$("#display_result").bootstrapTable({
			url : 'showModSen',
			method : 'get',
			dataType : "json",
			detailView : true,//啟用父子表
			striped : true, // 隔行加亮
			columns : [ {
				field : 'senName',
				title : '感應模組名稱'
			}, {
				field : 'senCode',
				title : '感應模組代號'
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
				queryParams : {
					id : parentid
				},
				ajaxOptions : {
					id : parentid
				},
				uniqueId : "id",
				striped : true, // 隔行加亮
				columns : [ {
					field : 'parmName',
					title : '參數名稱'
				}, {
					field : 'parmCode',
					title : '參數代號'
				}, {
					field : 'upperLimit',
					title : '上限警示值'
				}, {
					field : 'lowerLimit',
					title : '下限警示值'
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

