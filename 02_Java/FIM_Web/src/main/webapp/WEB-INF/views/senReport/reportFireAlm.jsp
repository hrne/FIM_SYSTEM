<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<script>
	window.onload = function() {
		var now = new Date(), year = now.getFullYear(), month = ((now
				.getMonth() + 1) > 9) ? (now.getMonth() + 1) : ("0" + (now
				.getMonth() + 1)), date = translate(now.getDate()), hour = translate(now
				.getHours()), hour_sub = translate(now.getHours() - 1), minute = translate(now
				.getMinutes()), second = translate(now.getSeconds());
		function translate(prop) {
			if (prop <= 9) {
				return "0" + prop;
			} else {
				return prop
			}
		}
		var start_date = year + "-" + month + "-" + date + "T" + hour_sub + ":"
				+ minute;

		var end_date = year + "-" + month + "-" + date + "T" + hour + ":"
				+ minute;

		var start_dateControl = document
				.querySelector('input[id="start_date"]');
		start_dateControl.value = start_date;

		var end_dateControl = document.querySelector('input[id="end_date"]');
		end_dateControl.value = end_date;

	}
	$(function() {
		Highcharts.setOptions({
			global : {
				useUTC : false
			}
		});//設定時區
		function init(){
			chart = {
			credits : {
				enabled : false
			//不顯示LOGO
			},
			title : {
				text : '火災警報感應資料'
			},
			xAxis : {
				type : 'datetime',

				tickPixelInterval : 120
			},
			yAxis : {
				title : {
					text : '狀態'
				},
				labels : {
					formatter : function() {
						if (this.value <= 0) {
							return "正常(" + this.value + ")";
						} else {
							return "異常(" + this.value + ")";
						}
					}
				},
				max : 1, // 定義Y軸 最大值  
				min : 0, // 定義最小值  
				tickInterval : 1
			// 刻度值  
			},
			plotOptions : {
				line : {
					dataLabels : {
						enabled : true
					},
					enableMouseTracking : true
				}
			},
			series : requestData()
		};
		}
		function requestData() {
			var protocol_type= $("#PROTOCOL_TYPE").val();	
			
			//Start Date
			var x_date = $("input[id='start_date']").val();
			var s_date = new Date();
			s_date.setFullYear(parseInt(x_date.substring(0, 4)));
			s_date.setMonth(parseInt(x_date.substring(5, 7)) - 1);
			s_date.setDate(parseInt(x_date.substring(8, 10)));
			s_date.setHours(parseInt(x_date.substring(11, 13)));
			s_date.setMinutes(parseInt(x_date.substring(14, 16)));
	        
			//End Date
			var y_date = $("input[id='end_date']").val();
			var e_date = new Date();
			e_date.setFullYear(parseInt(y_date.substring(0, 4)));
			e_date.setMonth(parseInt(y_date.substring(5, 7)) - 1);
			e_date.setDate(parseInt(y_date.substring(8, 10)));
			e_date.setHours(parseInt(y_date.substring(11, 13)));
			e_date.setMinutes(parseInt(y_date.substring(14, 16)));
			
			var series = new Array();
			$.ajax({
				url : "showCharFireAlm",
				type : "post",
		        data:{
		        	protocol_type:protocol_type,
		        	s_date:s_date,
		        	e_date:e_date
		        },
		        ataType: "json",
				async : false,
				success : function(data) {
					for (var k = 0; k < 2; k++) {
						var seriesData = [];
						for (var j = 0; j < data[k].length; j++) {
							var point = data[k][j];
							var time = point[0];
							var value = point[1];
							console.log(time);
							seriesData.push({
								x : time,
								y : value
							});
						}
						if (k == 0) {
							series.push({
								"name" : "火光",
								"data" : seriesData
							});
						} else {
							series.push({
								"name" : "一氧化碳",
								color : '#89A54E',
								"data" : seriesData
							});

						}

					}
				}
			});
			return series;

		}
		$("#searchBtn").off().on("click", function() {
			init();
			var charSert = Highcharts.chart('container2', chart);
		});


	})
</script>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">火災警報感應報表</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<table class="table table-bordered"">
							<tbody>
								<tr>
									<td class="text-center" width="15%">工具機</td>							
										<td class="text-center" width="%">
										  <select class="form-control"  id="PROTOCOL_TYPE" name="PROTOCOL_TYPE" >
                                          <option value="3">烤箱</option>

                                        </select>
										</td>
									<td class="text-center" width="15%">查詢期間</td>
									<td class="text-center" width="20%"><input
										class="form-control" type="datetime-local" value=""
										id="start_date"></td>
									<td class="text-center" width="5%">至</td>
									<td class="text-center" width="20%"><input
										class="form-control" type="datetime-local" value=""
										id="end_date"></td>
								</tr>

								<tr>
									<td class="text-center" colspan="5" class="heading">
										                                    
										<button type="submit" class="btn btn-primary btn-sm"
											id="searchBtn">
											<i class="fa fa-search fa-large"></i>查询
										</button>                                                                    
									</td>
								</tr>

							</tbody>
						</table>
					</div>
					<!-- /.row (nested) -->
					<div id="container2"></div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
</div>

