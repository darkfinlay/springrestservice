package com.example.demo.interceptor;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import java.text.*;


@Component
public class MyInterceptor implements HandlerInterceptor {

	
	static Logger logger = Logger.getLogger(MyInterceptor.class) ;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
		
		
		
		logger.info(getTime());
		
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
		logger.info(getTime());
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info(getTime());
		return true;
	}
	
	private String getTime() {
		Calendar cal = Calendar.getInstance();
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	     return sdf.format(cal.getTime()) ;
		
	}

}
