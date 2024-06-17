package com.gd.article.js;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeesMapper {
	
	// DB : employees
	@Select("SELECT gender, COUNT(*) cnt FROM employees GROUP BY gender")
	List<Map<String, Object>> selectCountGender();
	
	@Select("SELECT YEAR(hire_date) hireYear, COUNT(*) cnt FROM employees GROUP BY YEAR(hire_date)")
	List<Map<String, Object>> selectCountHireYear();
	
	@Select("SELECT t.deptName deptName, COUNT(*) cnt"
			+ " FROM "
			+ " (SELECT e.emp_no empNo, d.dept_name deptName"
			+ " FROM dept_emp e"
			+ " INNER JOIN departments d ON e.dept_no = d.dept_no"
			+ " WHERE e.to_date = '9999-01-01') t"
			+ " GROUP BY t.deptName")
	List<Map<String, Object>> selectCountDeptName();
	
	
	@Select("SELECT t.dept_name deptName, SUM(t.salary) salarySum, ROUND(AVG(t.salary)) salaryAvg, "
			+ " MAX(t.salary) salaryMax, MIN(t.salary) salaryMin"
			+ " FROM"
			+ " (SELECT s.emp_no emp_no, s.salary salary, d.dept_name dept_name"
				+ " FROM"
				+ " (SELECT  emp_no, salary FROM salaries WHERE to_date = '9999-01-01') s"
				+ " INNER JOIN"
				+ " (SELECT emp_no, dept_no FROM dept_emp WHERE to_date = '9999-01-01') de"
				+ " ON s.emp_no = de.emp_no"
					+ " INNER JOIN"
					+ " departments d "
					+ " ON d.dept_no = de.dept_no) t"
			+ " GROUP BY t.dept_name"
			+ " ORDER BY t.dept_name ASC")
	List<Map<String, Object>> selectCountSalary();
	
}
