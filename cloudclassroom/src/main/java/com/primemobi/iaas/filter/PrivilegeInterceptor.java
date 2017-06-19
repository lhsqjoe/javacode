package com.primemobi.iaas.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PrivilegeInterceptor extends AbstractInterceptor {


    /**
     * 1、通过动态代理类和反射机制获取调用的方法 2、获取方法的Permission注解
     * 3、获取Permission中包含的权限信息（构造SystemPrivilege权限类）
     * 4、和用户的权限信息进行对比（PrivilegeGroup中的SystemPrivilege）判断用户的权限
     */
    @Override
    public String intercept(ActionInvocation invocation)throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();

        //用户权限证书验证
        return invocation.invoke();
    }
}