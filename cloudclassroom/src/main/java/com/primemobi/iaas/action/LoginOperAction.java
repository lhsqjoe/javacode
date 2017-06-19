package com.primemobi.iaas.action;

import com.primemobi.iaas.entity.TUser;
import com.primemobi.iaas.service.ITUserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by qiao on 17-5-25.
 * 平台登录管理
 */
@ParentPackage("primemobi-action")
public class LoginOperAction{
    private static Logger logger = LoggerFactory.getLogger(LoginOperAction.class);

    @Autowired
    ITUserService userService;

    @Action(value = "login", results = {@Result(name = "success",location ="/pages/index.jsp")})
    public String login() {
       /* TUser user = userService.selectById(1);
        logger.info("login action 用户名称"+user.getUserName());*/
        return "success";
    }

}
