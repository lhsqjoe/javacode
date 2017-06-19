package com.primemobi.iaas.common;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.primemobi.iaas.entity.TConfig;
import com.primemobi.iaas.service.ITConfigService;
import com.primemobi.iaas.util.AppConfig;
import com.primemobi.iaas.util.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
        ApplicationContext applicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);
      /*  for (int i = 0; i < applicationContext.getBeanDefinitionNames().length; i++) {
            System.out.println(applicationContext.getBeanDefinitionNames()[i]);
        }*/
        ITConfigService configService = (ITConfigService)applicationContext.getBean("TConfigServiceImpl");
        TConfig config = configService.selectOne(new EntityWrapper<TConfig>().eq("cname","clcHost"));
        Const.clcHost = config.getCvalue();
        config = configService.selectOne(new EntityWrapper<TConfig>().eq("cname","clcPort"));
        Const.clcPort = config.getCvalue();
        config = configService.selectOne(new EntityWrapper<TConfig>().eq("cname","clcPassword"));
        Const.clcPassword = config.getCvalue();

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
