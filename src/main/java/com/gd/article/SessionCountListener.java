package com.gd.article;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionCountListener implements HttpSessionListener {
	


	// application.getAttribute("currentCnt")가져와서 1씩 증가시키면 됨
    public void sessionCreated(HttpSessionEvent se)  { 
    	
    	ServletContext application =  se.getSession().getServletContext();
    	Integer i = (Integer)(application.getAttribute("currentCnt"));
    	application.setAttribute("currentCnt", i+1);
    	
    }
    
    // application.getAttribute("currentCnt")가져와서 1씩 감소시키면 됨 (세션이 사라지니까)
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	
    	ServletContext application =  se.getSession().getServletContext();
    	Integer i = (Integer)(application.getAttribute("currentCnt"));
    	application.setAttribute("currentCnt", i-1);
    }
	
}
