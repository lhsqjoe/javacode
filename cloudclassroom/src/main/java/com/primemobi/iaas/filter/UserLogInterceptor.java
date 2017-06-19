package com.primemobi.iaas.filter;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.primemobi.iaas.entity.TLoginfo;
import com.primemobi.iaas.entity.TMenu;
import com.primemobi.iaas.service.ITLoginfoService;
import com.primemobi.iaas.service.ITMenuService;
import com.primemobi.iaas.util.IPUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class UserLogInterceptor extends AbstractInterceptor {
    @Autowired
    ITLoginfoService loginfoService;
    @Autowired
    ITMenuService menuService;


    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            ActionProxy actionProxy = invocation.getProxy();
            String methodName = actionProxy.getMethod();
            HttpSession session = request.getSession();
            String userName = "";
            if (session != null) {
                userName = (String) session.getAttribute("userName");
            }

            TMenu menu = menuService.selectOne(new EntityWrapper<TMenu>().eq("url", methodName + ".action"));
            TLoginfo loginfo = new TLoginfo();
            loginfo.setUserId(userName);
            loginfo.setUserIp(IPUtil.getIpAddress(request));//获取客户端IP地址
            loginfo.setCreateTime(new Date());
            if(menu!=null){
                loginfo.setMessage(menu.getName());
            }else{
                loginfo.setMessage(methodName+".action");
            }
            loginfoService.insertAllColumn(loginfo);

        } catch (Exception e) {
            return invocation.invoke();
        }
        return invocation.invoke();

    }
}

