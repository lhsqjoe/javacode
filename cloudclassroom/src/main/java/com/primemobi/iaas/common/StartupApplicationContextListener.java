package com.primemobi.iaas.common;

import com.primemobi.iaas.util.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by qiao on 17-5-26.
 */
public class StartupApplicationContextListener implements ServletContextListener {
    static Logger logger = LoggerFactory.getLogger(StartupApplicationContextListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        String basePath = servletContext.getContextPath();
        servletContext.setAttribute("BASE_PATH", basePath);
        logger.debug("项目根路径 BASE_PATH:" + basePath);
        AppConfig.initConfig();
        String projectName = AppConfig.getProperty("project.name");
        servletContext.setAttribute("PROJECT_NAME", projectName);
        logger.debug("项目名称 PROJECT_NAME:" + projectName);
       /* ApplicationContext applicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);*/
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
