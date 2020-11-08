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
		var chart = {

			credits : {
				enabled : false
			//不顯示LOGO
			},
			title : {
				text : '溫濕度'
			},
			xAxis : [ {
				type : 'datetime',
				labels : {
					format : '{value:%H:%M}'
				},
				tickPixelInterval : 120
			} ],
			yAxis : {
				title : {
					text : '攝氏 (°C)-濕度(%)'
				},
				max : 100,
				min : 0
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

		function requestData() {
			var series = new Array();
			$.ajax({
				url : "showChartDht11",
				type : "post",
				async : false,
				success : function(data) {
					for (var k = 0; k < 2; k++) {
						var seriesData = [];
						for (var j = 0; j < data[k].length; j++) {
							var point = data[k][j];
							var time = point[0];
							var value = point[1];
							seriesData.push({
								x : time,
								y : value
							});
						}
						if (k == 0) {
							series.push({
								"name" : "溫度",
								"data" : seriesData
							});
						} else {
							series.push({
								"name" : "濕度",
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
			var charSert = Highcharts.chart('container2', chart);
		});

		$("#resetBtn").off().on("click", function() {
			var start_date = $("input[id='start_date']").val();
			console.log(start_date);
		});
	})
</script>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">溫濕度報表</h1>
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
									<td class="text-center" width="%"><div class="dropdown">
											<button type="button" class="btn dropdown-toggle"
												id="dropdownMenu1" data-toggle="dropdown">
												裁切機 <span class="caret"></span>
											</button>
											<ul class="dropdown-menu" role="menu"
												aria-labelledby="dropdownMenu1">
												<li role="presentation"><a role="menuitem"
													tabindex="-1" href="#">沖床機</a></li>
												<li role="presentation"><a role="menuitem"
													tabindex="-1" href="#">壓縮機</a></li>
												<li role="presentation"><a role="menuitem"
													tabindex="-1" href="#"> 切割機 </a></li>
											</ul>
										</div></td>
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
										<button type="reset" class="btn btn-primary btn-sm"
											id="resetBtn" style="margin-right: 30px;">
											<i class="fa fa-undo fa-large"></i>重置
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

