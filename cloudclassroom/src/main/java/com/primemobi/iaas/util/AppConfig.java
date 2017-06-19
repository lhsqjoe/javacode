package com.primemobi.iaas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by qiao on 17-5-26.
 * 加载配置文件
 */
public class AppConfig {

    static Logger logger = LoggerFactory.getLogger(AppConfig.class);
    static Properties appConfig = null;

    /**
     * 初始化，读取系统配置文件
     */
    public static void initConfig(){
        try {
            appConfig= PropertiesLoaderUtils.loadAllProperties("appconf.properties");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 读取某参数
     *
     * @param propName
     *            String
     * @return String
     */
    public static String getProperty(String propName) {
        return appConfig.getProperty(propName);
    }
}
