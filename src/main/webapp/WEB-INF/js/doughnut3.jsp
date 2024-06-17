<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>doughnut</title>
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

	<!-- chart JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
	
</head>
<body style="text-align: center;">
	
	
	<h1>Chart</h1>
	<canvas id="chart_1" style="width:100%; max-width:700px; margin: auto;"></canvas>
	
	<br><hr>
	<canvas id="chart_2" style="width:100%; max-width:700px; margin: auto;"></canvas>
	
	<br><hr>
	<canvas id="chart_3" style="width:100%; max-width:700px; margin: auto;"></canvas>
	
	<br><hr>
	<canvas id="chart_4" style="width:100%; max-width:700px; margin: auto;"></canvas>

<script>
	// x값
	const xValues = [];
	
	// SUM
	const yValues_1 = [];
	
	// Avg
	const yValues_2 = [];
	
	// Max
	const yValues_3 = [];
	
	// Min
	const yValues_4 = [];
	
	const barColors = [
	  "#b91d47","#00aba9", "#2b5797", "#e8c3b9", "#1e7145","#ffaa00","#b5dd78",
	  "#6bc4dd","#9838dd", "#dd9b3f", "#dd31a7", "#d7ddca",  "#8a69dd", "#ddd720",
	  "#00dd34","#0d10dd"
	];
	
	
	// 차트에 넣어줄 값
	$.ajax({
		async:false, // 동기로 요청하고 싶다는 뜻. 기본값true는 비동기
		url:'/chart/getCountSalary',
		method:'post',
		success:function(json){
			
			// [{'M':179973},{'F':120051}]
			console.log("json : ", json[0]);
			
			json.forEach(function(item){
				xValues.push(item.deptName);
				
				yValues_1.push(item.salarySum);
				yValues_2.push(item.salaryAvg);
				yValues_3.push(item.salaryMax);
				yValues_4.push(item.salaryMin);
				
				//console.log("xValues : ", xValues);
				//console.log("yValues : ", yValues);
			})
			console.log("xValues : ", xValues);
			console.log("yValues_1 : ", yValues_1);
			console.log("yValues_2 : ", yValues_2);
			console.log("yValues_3 : ", yValues_3);
			console.log("yValues_4 : ", yValues_4);
		}
		
	})
	
	// SUM
	new Chart("chart_1", {
		  type: "bar",
		  data: {
		    labels: xValues,
		    datasets: [{
			    backgroundColor: barColors,
			    data: yValues_1
		    }]
		  },
		  options: {
		    title: {
		      display: true,
		      text: "Salary Sum"
		    }
		  }
		});
	
	
	// Avg
	new Chart("chart_2", {
		  type: "doughnut",
		  data: {
		    labels: xValues,
		    datasets: [{
				backgroundColor: barColors,
				data: yValues_2
		    }]
		  },
		  options: {
		    title: {
		      display: true,
		      text: "Salary Avg"
		    }
		  }
		});
	
	
	// Max
	new Chart("chart_3", {
		  type: "pie",
		  data: {
		    labels: xValues,
		    datasets: [{
			    backgroundColor: barColors,
			    data: yValues_3
		    }]
		  },
		  options: {
		    title: {
		      display: true,
		      text: "Salary Max"
		    }
		  }
		});
	
	
	// Min
	new Chart("chart_4", {
		  type: "line",
		  data: {
		    labels: xValues,
		    datasets: [{
				fill: false,
				lineTension: 0,
			    backgroundColor:"rgba(150,145,119,1.0)",
			    borderColor: "rgba(150,145,119,0.5)",
			    data: yValues_4
		    }]
		  },
		  options: {
			legend: {display: true},
		    title: {
				display: true,
				text: "Salary Min"
		    }
		  }
		});
</script>
</body>
</html>