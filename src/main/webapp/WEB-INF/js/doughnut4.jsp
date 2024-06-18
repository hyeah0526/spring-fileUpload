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
	<canvas id="myChart" style="width:100%; max-width:700px; margin: auto;"></canvas>
	
	<br><hr>
	<canvas id="myChart2" style="width:100%; max-width:700px; margin: auto;"></canvas>
	

<script>
	// x값
	const xValues = [];
	
	// 남자
	const yValues_M = [];
	
	// 여자
	const yValues_F = [];
	
	
	// 차트에 넣어줄 값
	$.ajax({
		async:false,
		url:'/chart/getCountPerson',
		method:'post',
		success:function(json){
			
			//console.log("json : ",json);
			
			json.forEach(function(item){
				//xValues.push(item.y);
				
				if(item.gender == "M"){
					xValues.push(item.y)
					yValues_M.push(item.cnt);
				}else {
					yValues_F.push(item.cnt);
				};
				
			})
			//console.log("xValues : ", xValues);
			//console.log("yValues_M : ", yValues_M);
			//console.log("yValues_F : ", yValues_F);
		}
		
	})
	
	
	new Chart("myChart", {
	  type: "line",
	  data: {
	    labels: xValues, // 년도
	    datasets: [{ 
		  // 남자
	      data: yValues_M,
	      label: "Male",
	      backgroundColor:"rgba(221,120,121,0.3)",
		  borderColor: "rgba(221,120,121,1.0)",
	      fill: true
	    }, { 
		  // 여자
		  data: yValues_F,
		  label: "Female",
		  backgroundColor:"rgba(150,145,119,0.5)",
		  borderColor: "rgba(150,145,119,1.0)",
	      fill: true
	    }]
	  },
	  options: {
		title: {
		      display: true,
		      text: "년도별 부서인원"
		    },
	    legend: {display: true}
	  }
	})

	// --------------------------------------------------------------------------------
	
	// dname
	const values_deptAll = [];

	// year
	const xYear = [];
	
	// cnt
	const yCnt_CS = [];
	const yCnt_dev = [];
	const yCnt_finance = [];
	const yCnt_HR = [];
	const yCnt_marketing = [];
	const yCnt_production = [];
	const yCnt_QM = [];
	const yCnt_research = [];
	const yCnt_sales = [];
	
	
	// 차트에 넣어줄 값
	$.ajax({
		async:false,
		url:'/chart/getCountDept',
		method:'post',
		success:function(json){
			
			// 부서담기
			json.forEach(function(item){
				values_deptAll.push(item.dname);
			});
			
			
			// 부서 중복제거 : values_dept
			const set = new Set(values_deptAll);
			const values_dept = [...set];

			document.writeln(Array.isArray(values_dept));
			document.writeln(values_dept);
			
			console.log("values_dept : ", values_dept);
			
			
			// year / cnt 값 담기
			json.forEach(function(item){
				
				if(item.dname == values_dept[0]){
					xYear.push(item.year);
					yCnt_CS.push(item.cnt);
					
				}else if(item.dname == values_dept[1]){
					yCnt_dev.push(item.cnt);
					
				}else if(item.dname == values_dept[2]){
					yCnt_finance.push(item.cnt);
					
				}else if(item.dname == values_dept[3]){
					yCnt_HR.push(item.cnt);
					
				}else if(item.dname == values_dept[4]){
					yCnt_marketing.push(item.cnt);
					
				}else if(item.dname == values_dept[5]){
					yCnt_production.push(item.cnt);
					
				}else if(item.dname == values_dept[6]){
					yCnt_QM.push(item.cnt);
					
				}else if(item.dname == values_dept[7]){
					yCnt_research.push(item.cnt);
					
				}else if(item.dname == values_dept[8]){
					yCnt_sales.push(item.cnt);
					
				};
				
				
			});
			
		}
		
	});
	
	// 년도별 순 유입 과제
	new Chart("myChart2", {
		  type: "line",
		  data: {
		    labels: xYear, // 년도
		    datasets: [{ 
			  // CS
		      data: yCnt_CS,
		      label: "Customer Service",
			  borderColor: "#b91d47",
		      fill: false
		      
		    }, { 
			  // Development
			  data: yCnt_dev,
			  label: "Development",
			  borderColor: "#00aba9",
		      fill: false
		      
		    }, {
		    	// finance
		    	data: yCnt_finance,
				label: "finance",
				borderColor: "#2b5797",
			    fill: false
			    
		    }, {
		    	// HR
		    	data: yCnt_HR,
				label: "Human Resources",
				borderColor: "#e8c3b9",
			    fill: false
			    
		    }, {
		    	// marketing
		    	data: yCnt_marketing,
				label: "Marketing",
				borderColor: "#1e7145",
			    fill: false
			    
		    }, {
		    	// Production
		    	data: yCnt_production,
				label: "Production",
				borderColor: "#ffaa00",
			    fill: false
			    
		    }, {
		    	// Quality Management
		    	data: yCnt_QM,
				label: "Quality Management",
				borderColor: "#b5dd78",
			    fill: false
			    
		    }, {
		    	// yCnt_research
		    	data: yCnt_research,
				label: "research",
				borderColor: "#6bc4dd",
			    fill: false
			    
		    }, {
		    	// yCnt_sales
		    	data: yCnt_sales,
				label: "sales",
				borderColor: "#9838dd",
			    fill: false
			    
		    }]
		  },
		  options: {
			title: {
			      display: true,
			      text: "년도별 순 유입 과제"
			    },
		    legend: {display: true}
		  }
		})
		
</script>
</body>
</html>