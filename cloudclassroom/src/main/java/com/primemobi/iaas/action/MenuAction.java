package com.primemobi.iaas.action;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.primemobi.iaas.entity.TMenu;
import com.primemobi.iaas.service.ITMenuService;
import com.primemobi.iaas.util.DateFormat;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by qiao on 17-5-27.
 * 平台菜单管理
 */
@ParentPackage("primemobi-json-action")
public class MenuAction {

    private static Logger logger = LoggerFactory.getLogger(MenuAction.class);

    @Autowired
    private ITMenuService menuService;

    @Action(value = "listMenu", results = {@Result(name = "success", location = "/pages/menu/list.jsp")})
    public String listMenu() {
        logger.debug("平台菜单管理");
        return "success";
    }


    @Action(value = "listMenuJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String listMenuJson() {
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
                //轉換成數據庫能識別的日期格式
                Date beginDate = DateFormat.converStrToDateByFormatStr(dateArray[0].trim(),"MM/dd/yyyy");
                Date endDate = DateFormat.converStrToDateByFormatStr(dateArray[1].trim(),"MM/dd/yyyy");
                beginTimeStr=DateFormat.formatDateByFormatStr(beginDate,"yyyy-MM-dd");
                endTimeStr =DateFormat.formatDateByFormatStr(endDate,"yyyy-MM-dd");
            }
        }
        Page<TMenu> page = menuService.selectPage(
                new Page<TMenu>(Integer.valueOf(pageNum), 10),
                new EntityWrapper<TMenu>()
                        .like("name", menuName.trim())
                        .ge("update_datetime",beginTimeStr)
                        .lt("update_datetime",endTimeStr)
//                        .eq("leaf", 1)
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

    @Action(value = "listTreeJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String listTreeJson() {
        List<Map<String, Object>> menuList = treeMenuList(-1);
        Gson gson = new Gson();
        jsonStr = gson.toJson(menuList);
        return "success";
    }


    @Action(value = "addMenu", results = {@Result(name = "success", location = "/pages/menu/upload.jsp")})
    public String addMenu() {
        logger.debug("平台权限管理addMenu");
        return "success";
    }

    @Action(value = "addMenuJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String addMenuJson() {
        logger.debug("权限管理addMenuJson");
        TMenu menu = new TMenu();
        menu.setPid(Integer.valueOf(pid));
        menu.setName(menuName);
        menu.setLeaf(0);
        if (StringUtils.isNotBlank(url)) {
//            menu.setLeaf(1);
            menu.setUrl(url);
        }
        menu.setIconskin(iconskin);
        menu.setSn(Integer.valueOf(sn));
        menu.setCreateDatetime(new Date());
        menu.setUpdateDatetime(new Date());
        menuService.insert(menu);
        Gson gson = new Gson();
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("flag", "success");
        jsonStr = gson.toJson(resMap);
        return "success";
    }


    @Action(value = "updateMenu", results = {@Result(name = "success", location = "/pages/menu/update.jsp")})
    public String updateUser() {
        logger.debug("平台权限管理updateMenu");
        menuInfo = menuService.selectById(menuId);
        return "success";
    }


    @Action(value = "updateMenuJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String updateMenuJson() {
        logger.debug("平台权限管理updateMenu");
        TMenu menu = menuService.selectById(menuId);
        menu.setPid(Integer.valueOf(pid));
        menu.setName(menuName);
        menu.setLeaf(0);
        if (StringUtils.isNotBlank(url)) {
            menu.setUrl(url);
        }
        menu.setIconskin(iconskin);
        menu.setSn(Integer.valueOf(sn));
        menu.setUpdateDatetime(new Date());
        menuService.updateAllColumnById(menu);
        Gson gson = new Gson();
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("flag", "success");
        jsonStr = gson.toJson(resMap);
        return "success";
    }

    /**
     * 删除权限
     *
     * @return
     */
    @Action(value = "delMenuJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String delMenuJson() {
        logger.debug("平台权限管理delMenuJson");
     /*   TMenu menu = menuService.selectById(id);
        if(menu.getLeaf()==1){
            menuService.deleteById(id);
        }*/
        menuService.deleteById(Integer.valueOf(menuId));
        Gson gson = new Gson();
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("flag", "success");
        jsonStr = gson.toJson(resMap);
        return "success";
    }


    /**
     * 获取某个父节点下面的所有子节点
     *
     * @param pid
     * @return
     */
    public List<Map<String, Object>> treeMenuList(int pid) {
        List<TMenu> menuList = menuService.selectList(new EntityWrapper<TMenu>()
                .eq("pid", pid));
        List<Map<String, Object>> subMenuList = new ArrayList<Map<String, Object>>();
        if (menuList != null && menuList.size() > 0) {
            for (TMenu menu : menuList) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("text", menu.getName());
//                params.put("icon", menu.getIconskin());
                params.put("id", String.valueOf(menu.getId()));
                params.put("pid", String.valueOf(menu.getPid()));

                List<TMenu> resList = menuService.selectList(new EntityWrapper<TMenu>()
                        .eq("pid", menu.getId()));
                if (resList!=null && resList.size()>0) {
                    List<Map<String, Object>> subMenuListOne = treeMenuList(menu.getId());
                    if (subMenuListOne.size() > 0) {
                        params.put("nodes", subMenuListOne);
                    }
                }
                subMenuList.add(params);
            }

        }
        return subMenuList;
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


    private String menuName;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }


    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String iconskin;

    public String getIconskin() {
        return iconskin;
    }

    public void setIconskin(String iconskin) {
        this.iconskin = iconskin;
    }

    private String sn;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    private String pid;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    private String menuId;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    private TMenu menuInfo;

    public TMenu getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(TMenu menuInfo) {
        this.menuInfo = menuInfo;
    }


    private String dateRange;

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }
}
