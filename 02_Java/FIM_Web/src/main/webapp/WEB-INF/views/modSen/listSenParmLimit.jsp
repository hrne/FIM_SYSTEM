<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	$(function() {

		//這段須放在表格初始化之前
		function updateStatus(id, state) {
			$.ajax({
				url : "saveSenParmLimitEnabled",
				type : "POST",
				dataType : "JSON",
				data : {
					"id" : id,
					"state" : state
				},
				success : function(data) {
					console.log(data)
				}
			})
		}
		;

		$("#display_resultParmLimit").bootstrapTable({
			url : 'showAllModSen',
			method : 'get',
			dataType : "json",
			detailView : true,//啟用父子表
			striped : true, // 隔行加亮
			columns : [ {
				title : '模組名稱',
				align : "center",
				field : 'senName',
			}, {
				title : '模組代號',
				align : "center",
				field : 'senCode'
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
			$(cur_table)
					.bootstrapTable(
							{
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
								columns : [
										{
											title : '參數名稱',
											align : "center",
											field : 'parmName'
										},
										{
											title : '參數代號',
											align : "center",
											field : 'parmCode'
										},
										{
											title : '上限警示值',
											align : "center",
											field : 'upperLimit',
											editable : {
												type : 'text',
												title : '上限警示值',
												validate : function(v) {
													if (isNaN(v))
														return '警示值必須是數字';
													var age = parseInt(v);
													if (age < 0)
														return '警示值必須正整數或0';
												}
											}
										},
										{
											title : '下限警示值',
											align : "center",
											field : 'lowerLimit',
											editable : {
												type : 'text',
												title : '下限警示值',
												validate : function(v) {
													if (isNaN(v))
														return '警示值必須是數字';
													var age = parseInt(v);
													if (age < 0)
														return '警示值必須正整數或0';
												}
											}
										},
										{
											title : "是否啟用警示",
											field : 'limitEnabled',
											align : "center",
											valign : 'middle',
											formatter : function(value, row,
													index) {
												var $enabled;
												if (row.limitEnabled) {
													$enabled = "<input  value=" + row.id + " name='avaCheck' type='checkbox' checked/>";
												} else {
													$enabled = "<input  value=" + row.id + " name='avaCheck' type='checkbox'/>";
												}
												return $enabled;
											}
										} ],
								//無限讀取子表，直到沒有資料
								onExpandRow : function(index, row, $Subdetail) {
									initSubTable(index, row, $Subdetail);
								},
								onEditableSave : function(field, row, oldValue,
										$el) {
									// field:修改的欄位
									// row:修改後的資料(JSON Object)
									// oldValue:修改前的值
									$.ajax({
										type : "get",
										url : "saveSenParmLimit",
										data : {
											"id" : row.id,
											"field" : field,
											"upperLimit" : row.upperLimit,
											"lowerLimit" : row.lowerLimit
										},
										dataType : 'JSON',
										success : function(status) {
											if (status == "success") {

											}
										},
										error : function() {

										},
										complete : function() {

										}

									});
								},
								onClickCell : function(field, value, row,
										$element) {
									console.log(row);
									$.ajax({
										url : "saveSenParmLimitEnabled",
										type : "POST",
										dataType : "JSON",
										data : {
											"id" : row.id,
											"state" : row.limitEnabled
										},
										success : function(data) {
											console.log(row.limitEnabled);
											
											console.log(row.limitEnabled);
										}
									})
									if (row.limitEnabled) {
												row.limitEnabled = false;
											} else {
												row.limitEnabled = true;
											}
								}
							/**
							onLoadSuccess : function() {
								
								var changeHandler = function() {
									var id = $(this).val();
									var state = !$(this).prop('checked');
									updateStatus(id, state);
								};
								$("[name='avaCheck']").bootstrapToggle(
										'destroy');
								return $("[name='avaCheck']")
										.bootstrapToggle({
											on : '啟用',//選中時顯示文字
											off : '關閉',///選中時顯示文字
											//onstyle: 'success',//on樣式：default,primary,success,info,warning,danger
											//offstyle: 'default',//off樣式：default,primary,success,info,warning,danger
											size : 'small',//物件大小：large,normal,small,mini
										}).off('change.status').on(
												'change.status',
												changeHandler);
							}**/
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
				<div>
					<table width="100%"
						class="table table-striped table-bordered table-hover"
						id="display_resultParmLimit">
					</table>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
</div>

