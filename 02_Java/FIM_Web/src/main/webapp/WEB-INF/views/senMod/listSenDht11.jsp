<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<script>
	$(function() {
		var chart = 
				{
					chart : {
						type : 'line',
						events : {
							load : function() {
								var series = this.series;
								setInterval(function() {
										$.ajax({
											url : "senMod/showInitChartDht11",
											type : "POST",
											dataType : "JSON",
											success : function(data) {

												for (var k = 0; k < series.length; k++) {
													var modMainId=series[k].options.id;
													var tempDate=data[modMainId.toString()+"Date"];
													var tempCal=data[modMainId.toString()+"Cal"];
													console.log(tempDate);
													console.log(tempCal);
													series[k].addPoint(
															[tempDate,tempCal], true,
															true);
												}
											}
										
									})
								}, 1000);
							}
						}
					},
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
					}
				};		
		 var charSert=Highcharts.chart('container2',chart);

		 charSert.addSeries({
		          id:2,
		          name:"test"
		        });
		 
		 $.ajax({
				url : "senMod/showInitChartDht11",
				type : "POST",
				dataType : "JSON",
				success : function(data) {

				}
			
		})
	})
</script>

<div id="container2"></div>

