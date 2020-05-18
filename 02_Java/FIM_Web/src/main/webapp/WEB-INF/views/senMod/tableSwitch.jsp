<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	$(function() {
		//setInterval(refreshSwitch, 3000); //每3秒刷新一次
		/**
		function refreshSwitch() {
			$.ajax({
				success : function(data) {
					$('#display_resultSwitch').bootstrapTable('refresh');
				}
			});
		}
		$(document).ready(function() {
			createSwitch();
		});**/

		//這段須放在表格初始化之前
		function updateStatus(modMainId, state) {
			$.ajax({
				url : "senMod/turnPowerSwitch",
				type : "POST",
				dataType : "JSON",
				data : {
					"modMainId" : modMainId,
					"state" : state
				},
				success : function(data) {
					console.log(data)
				}
			})
		}
		;

		$("#display_resultSwitch")
				.bootstrapTable(
						{
							url : 'senMod/showAllSwitch',
							method : 'get',
							dataType : "json",
							striped : true, // 隔行加亮
							idField : 'modMainId',//指定主键列  
							columns : [
									{
										title : '裝置名稱',
										field : 'modMainName',
										align : "center",
									},
									{
										title : '電池電力(V)',
										field : 'batteryVolt',
										align : "center",
									},
									{
										title : "電源開關",
										field : 'powStatus',
										align : "center",
										formatter : function(value, row, index) {
											var $switch;
											if (value == 1) {
												$switch = "<input data-toggle='toggle' value=" + row.modMainId + " name='avaCheck' type='checkbox' checked/>";
											} else {
												$switch = "<input data-toggle='toggle' value=" + row.modMainId + " name='avaCheck' type='checkbox'/>";
											}
											return $switch;
										}
									} ],
							onLoadSuccess : function() {
								var changeHandler = function() {
									var modMainId = $(this).val();
									var state = !$(this).prop('checked');
									updateStatus(modMainId, state);
								};
								$("[name='avaCheck']").bootstrapToggle(
										'destroy');
								return $("[name='avaCheck']").bootstrapToggle({
									//on: 'on',//選中時顯示文字
									//off: 'off',///選中時顯示文字
									//onstyle: 'success',//on樣式：default,primary,success,info,warning,danger
									//offstyle: 'default',//off樣式：default,primary,success,info,warning,danger
									size : 'small',//物件大小：large,normal,small,mini
								}).off('change.status').on('change.status',
										changeHandler);

							}
						});
	})
</script>

<!-- 電源開關感應資料 -->
<table width="100%" id="display_resultSwitch" data-auto-refresh="true"
	data-auto-refresh-interval="3"
	class="table table-striped table-bordered table-hover">

</table>
