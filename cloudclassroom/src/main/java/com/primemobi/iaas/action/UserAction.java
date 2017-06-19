package com.primemobi.iaas.action;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.primemobi.iaas.entity.TUser;
import com.primemobi.iaas.entity.TUserRole;
import com.primemobi.iaas.service.ITUserRoleService;
import com.primemobi.iaas.service.ITUserService;
import com.primemobi.iaas.util.DateFormat;
import com.primemobi.iaas.util.Md5;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qiao on 17-5-27.
 * 平台用户管理
 */
@ParentPackage("primemobi-json-action")
public class UserAction {

    private static Logger logger = LoggerFactory.getLogger(UserAction.class);

    @Autowired
    ITUserService userService;

    @Autowired
    ITUserRoleService userRoleService;

    @Action(value = "listUser", results = {@Result(name = "success", location = "/pages/user/list.jsp")})
    public String listUser() {
        logger.debug("平台用户管理");
        return "success";
    }

    @Action(value = "listUserJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String listUserJson() {
        //计算当前页码
        int pageNum = 1;
        if (iDisplayStart != null && iDisplayLength != null) {
            pageNum = (Integer.parseInt(iDisplayStart) / Integer.parseInt(iDisplayLength)) + 1;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userName", username.trim());
        if(StringUtils.isNotBlank(dateRange)){
            logger.debug("dateRange"+dateRange);
            String[] dateArray = dateRange.split("-");
            if(dateArray!=null){
                //转换成数据库能识别的日期格式
                Date beginDate = DateFormat.converStrToDateByFormatStr(dateArray[0].trim(),"MM/dd/yyyy");
                Date endDate = DateFormat.converStrToDateByFormatStr(dateArray[1].trim(),"MM/dd/yyyy");
                params.put("beginTime", DateFormat.formatDateByFormatStr(beginDate,"yyyy-MM-dd"));
                params.put("endTime",DateFormat.formatDateByFormatStr(endDate,"yyyy-MM-dd"));
            }
        }

        Page<List<Map<String, Object>>> page = userService.selectUserPage(new Page(Integer.valueOf(pageNum), 10), params);
        Gson gson = new Gson();
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("sEcho", "");
        resMap.put("iTotalRecords", page.getTotal());
        resMap.put("iTotalDisplayRecords", page.getTotal());
        resMap.put("aaData", page.getRecords());
        jsonStr = gson.toJson(resMap);
        return "success";
    }

    @Action(value = "addUser", results = {@Result(name = "success", location = "/pages/user/upload.jsp")})
    public String addUser() {
        logger.debug("平台用户管理addUser");
        return "success";
    }

    @Action(value = "addUserJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String addUserJson() {
        logger.debug("平台用户管理saveUser");
        TUser user = new TUser();
        user.setUserName(userName);
        if(StringUtils.isBlank(userPass)){
            userPass="123456";//平台默认密码123456
        }
        user.setPassword(Md5.md5(userPass));
        user.setEmail(userEmail);
        user.setMobile(userTel);
        user.setStatus(1);//默认状态可用
        user.setCreateTime(new Date());
        userService.insertAndGetId(user);
        TUserRole userRole = new TUserRole();
        userRole.setRoleId(Integer.valueOf(userRoleId));
        userRole.setUserId(user.getId());
        userRoleService.insertAllColumn(userRole);
        Gson gson = new Gson();
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("flag", "success");
        jsonStr = gson.toJson(resMap);
        return "success";
    }


    @Action(value = "updateUser", results = {@Result(name = "success", location = "/pages/user/update.jsp")})
    public String updateUser() {
        logger.debug("平台用户管理updateUser");
        userInfo = userService.selectById(userId);
        TUserRole userRole = userRoleService.selectOne(new EntityWrapper<TUserRole>()
        .eq("user_id",userId));
        userRoleId = userRole.getRoleId().toString();
        return "success";
    }


    @Action(value = "updateUserJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String updateUserJson() {
        logger.debug("平台用户管理updateUser");
        TUser user = userService.selectById(userId);
        user.setUserName(userName);
        if(StringUtils.isNotBlank(userPass)){
            user.setPassword(Md5.md5(userPass));
        }
        user.setEmail(userEmail);
        user.setMobile(userTel);
        user.setCreateTime(new Date());
        userService.update(user, new EntityWrapper<TUser>()
                .eq("ID", userId));
        userRoleService.delete(new EntityWrapper<TUserRole>()
        .eq("USER_ID",userId));
        TUserRole userRole = new TUserRole();
        userRole.setRoleId(Integer.valueOf(userRoleId));
        userRole.setUserId(Integer.valueOf(userId));
        userRoleService.insertAllColumn(userRole);
        Gson gson = new Gson();
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("flag", "success");
        jsonStr = gson.toJson(resMap);
        return "success";
    }

    @Action(value = "delUserJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String delUserJson() {
        logger.debug("平台用户管理delUser");
        userService.deleteById(userId);
        userRoleService.delete(new EntityWrapper<TUserRole>()
                .eq("user_id",userId));
        Gson gson = new Gson();
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("flag", "success");
        jsonStr = gson.toJson(resMap);
        return "success";
    }


    private String jsonStr;

    @JSON
    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //开始记录 分页
    private String iDisplayStart;

    public String getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(String iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    //单页显示记录数
    private String iDisplayLength;

    public String getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(String iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }


    //用户添加用户名
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userPass;

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    private String userTel;

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private String userRoleId;

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private TUser userInfo;

    public TUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(TUser userInfo) {
        this.userInfo = userInfo;
    }

    private String dateRange;

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }
}
