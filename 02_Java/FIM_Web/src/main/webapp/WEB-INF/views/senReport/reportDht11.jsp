<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<script>
	$(function() {
		var chart = {

			credits : {
				enabled : false
			//不顯示LOGO
			},
			title : {
				text : '溫度'
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
					text : '攝氏 (°C)'
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
			series : [ {
				name : 'Random data',
				data : (function() {
					// generate an array of random data
					var data = [], time = (new Date()).getTime(), i;

					for (i = -10; i <= 0; i += 1) {
						data.push([ time + i * 1000,
							Math.random()*(40-30+1)+30 ]);
					}
					return data;
				}())
			} ]
		};
		var charSert = Highcharts.chart('container2', chart);

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
								<tr class="text-center">
									<!-- 感應裝置名稱 -->
									<td>查詢期間</td>
									<td><input class="form-control" type="datetime-local"
										value="" id="example-datetime-local-input"></td>
									<td>至</td>
									<td><input class="form-control" type="datetime-local"
										value="" id="example-datetime-local-input"></td>
								</tr>
								<tr>
									<td class="text-center" colspan="4" class="heading">
										                                    
										<button type="submit" class="btn btn-primary btn-sm">
											<i class="fa fa-search fa-large"></i>查询
										</button>                                      
										<button type="reset" class="btn btn-primary btn-sm"
											style="margin-right: 30px;">
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

