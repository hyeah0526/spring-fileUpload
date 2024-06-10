package com.gd.article;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

// 톰캣의 생명주기와 관련된 리스너
@WebListener
public class CountListener implements ServletContextListener {
	
	// 톰캣 ON
    public void contextInitialized(ServletContextEvent sce)  {
    	// 현재 접속자 수를 저장하기 위한 application 속성 변수
    	System.out.println("CountListner 실행 확인!");
    	ServletContext application = sce.getServletContext();
    	application.setAttribute("currentCnt", 0);
    	
    }
    
    // 톰캣 OFF
    public void contextDestroyed(ServletContextEvent sce)  { 
    }
	
}
