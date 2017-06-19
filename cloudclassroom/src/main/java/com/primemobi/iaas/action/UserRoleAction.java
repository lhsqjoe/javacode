package com.primemobi.iaas.action;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.primemobi.iaas.entity.TRole;
import com.primemobi.iaas.entity.TRolePermission;
import com.primemobi.iaas.service.ITRolePermissionService;
import com.primemobi.iaas.service.ITRoleService;
import com.primemobi.iaas.util.DateFormat;
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
 * 平台角色管理
 */
@ParentPackage("primemobi-json-action")
public class UserRoleAction {
    private static Logger logger = LoggerFactory.getLogger(UserRoleAction.class);


    @Autowired
    ITRoleService roleService;

    @Autowired
    ITRolePermissionService rolePermissionService;


    @Action(value = "listUserRole", results = {@Result(name = "success", location = "/pages/role/list.jsp")})
    public String listUserRole() {
        logger.debug("平台角色管理listUserRole");
        return "success";
    }


    @Action(value = "listUserRoleJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String listUserRoleJson() {
        logger.debug("平台角色管理listUserRoleJson");
        //计算当前页码
        int pageNum = 1;
        if (iDisplayStart != null && iDisplayLength != null) {
            pageNum = (Integer.parseInt(iDisplayStart) / Integer.parseInt(iDisplayLength)) + 1;
        }
        String beginTimeStr="",endTimeStr="";
        if(StringUtils.isNotBlank(dateRange)){
            logger.debug("dateRange"+dateRange);
            String[] dateArray = dateRange.split("-");
            if(dateArray!=null){
                //转换成数据库能是识别的日期格式
                Date beginDate = DateFormat.converStrToDateByFormatStr(dateArray[0].trim(),"MM/dd/yyyy");
                Date endDate = DateFormat.converStrToDateByFormatStr(dateArray[1].trim(),"MM/dd/yyyy");
                beginTimeStr=DateFormat.formatDateByFormatStr(beginDate,"yyyy-MM-dd")+" 00:00:00";
                endTimeStr =DateFormat.formatDateByFormatStr(endDate,"yyyy-MM-dd")+" 23:59:59";
            }
        }
        Page<TRole> page = roleService.selectPage(
                new Page<TRole>(Integer.valueOf(pageNum), 10),
                new EntityWrapper<TRole>()
                        .like("name", roleName.trim())
//                        .eq("leaf", 1)
                        .ge("update_datetime",beginTimeStr)
                        .lt("update_datetime",endTimeStr)
                        .orderBy("update_datetime", false));
        Gson gson = new Gson();
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("sEcho", "");
        resMap.put("iTotalRecords", page.getTotal());
        resMap.put("iTotalDisplayRecords", page.getTotal());
        resMap.put("aaData", page.getRecords());
        jsonStr = gson.toJson(resMap);
        return "success";
    }

    @Action(value = "listRoleJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String listRoleJson() {
        logger.debug("平台角色管理listRoleJson");
        List<TRole> roleList = roleService.selectList(new EntityWrapper<TRole>()
                .orderBy("update_datetime", false));
        Gson gson = new Gson();
        jsonStr = gson.toJson(roleList);
        return "success";
    }


    @Action(value = "addRole", results = {@Result(name = "success", location = "/pages/role/upload.jsp")})
    public String addMenu() {
        return "success";
    }

    @Action(value = "addRoleJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String addRoleJson() {
        TRole role = new TRole();
        role.setName(roleName);
        role.setCreateDatetime(new Date());
        role.setUpdateDatetime(new Date());
        roleService.insertAndGetId(role);
        String[] menuIdArray = menuId.split(",");
        for (String mId : menuIdArray) {
            TRolePermission rolePermission = new TRolePermission();
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(Integer.valueOf(mId));
            rolePermissionService.insert(rolePermission);
        }
        Gson gson = new Gson();
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("flag", "success");
        jsonStr = gson.toJson(resMap);
        return "success";
    }


    @Action(value = "updateRole", results = {@Result(name = "success", location = "/pages/role/update.jsp")})
    public String updateRole() {
        role = roleService.selectById(roleId);
        return "success";
    }


    @Action(value = "updateRoleJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String updateRoleJson() {
        rolePermissionService.delete(new EntityWrapper<TRolePermission>().eq("role_id", roleId));//先删除
        String[] menuIdArray = menuId.split(",");
        for (String mId : menuIdArray) {
            TRolePermission rolePermission = new TRolePermission();
            rolePermission.setRoleId(Integer.valueOf(roleId));
            rolePermission.setPermissionId(Integer.valueOf(mId));
            rolePermissionService.insert(rolePermission);
        }
        TRole role = roleService.selectById(roleId);
        role.setName(roleName);
        role.setUpdateDatetime(new Date());
        roleService.updateAllColumnById(role);
        Gson gson = new Gson();
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("flag", "success");
        jsonStr = gson.toJson(resMap);
        return "success";
    }

    /**
     * 删除角色
     *
     * @return
     */
    @Action(value = "delRoleJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String delRoleJson() {
        rolePermissionService.delete(new EntityWrapper<TRolePermission>().eq("role_id", roleId));//先删除
        roleService.deleteById(Integer.valueOf(roleId));
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


    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String menuId;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public TRole role;

    public TRole getRole() {
        return role;
    }

    public void setRole(TRole role) {
        this.role = role;
    }


    private String dateRange;

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }
}
