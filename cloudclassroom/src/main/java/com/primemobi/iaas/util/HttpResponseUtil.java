package com.primemobi.iaas.util;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpResponseUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpResponseUtil.class);
	 public static void writeStr(String str){
	        try {
	            ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
	            ServletActionContext.getResponse().getWriter().write(str);
	        } catch (IOException e) {
	        	logger.error("response"+e);
	        }
	    }
}
