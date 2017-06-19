package com.primemobi.iaas.action;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.primemobi.iaas.entity.TLoginfo;
import com.primemobi.iaas.service.ITLoginfoService;
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
import java.util.Map;

/**
 * Created by qiao on 17-5-27.
 * 平台日志管理
 */
@ParentPackage("primemobi-json-action")
public class LogInfoAction {

    private static Logger logger = LoggerFactory.getLogger(LogInfoAction.class);


    @Autowired
    ITLoginfoService loginfoService;

    @Action(value = "listLog", results = {@Result(name = "success", location = "/pages/log/list.jsp")})
    public String listLog() {
        logger.debug("平台日志管理");
        return "success";
    }


    @Action(value = "listLogJson", results = {@Result(name = "success", type = "json")}, params = {
            "contentType", "text/html"})
    public String listLogJson() {
        logger.debug("平台日志管理");
        //计算当前页码
        int pageNum = 1;
        if (iDisplayStart != null && iDisplayLength != null) {
            pageNum = (Integer.parseInt(iDisplayStart) / Integer.parseInt(iDisplayLength)) + 1;
        }
        String beginTimeStr = "", endTimeStr = "";
        Page<TLoginfo> page = null;
        if (StringUtils.isNotBlank(dateRange)) {
            logger.debug("dateRange" + dateRange);
            String[] dateArray = dateRange.split("-");
            if (dateArray != null) {
                //转换成数据库能识别的日期格式
                Date beginDate = DateFormat.converStrToDateByFormatStr(dateArray[0].trim(), "MM/dd/yyyy");
                Date endDate = DateFormat.converStrToDateByFormatStr(dateArray[1].trim(), "MM/dd/yyyy");
                beginTimeStr = DateFormat.formatDateByFormatStr(beginDate, "yyyy-MM-dd") + " 00:00:00";
                endTimeStr = DateFormat.formatDateByFormatStr(endDate, "yyyy-MM-dd") + " 23:59:59";
                page = loginfoService.selectPage(new Page<TLoginfo>(pageNum, 10), new EntityWrapper<TLoginfo>()
                        .ge("create_time", beginTimeStr)
                        .lt("create_time", endTimeStr)
                        .orderBy("create_time", false));
            }
        }else{
           page = loginfoService.selectPage(new Page<TLoginfo>(pageNum, 10), new EntityWrapper<TLoginfo>()
                   .orderBy("create_time", false));
        }

        Gson gson = new Gson();
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("sEcho", "");
        resMap.put("iTotalRecords", page.getTotal());
        resMap.put("iTotalDisplayRecords", page.getTotal());
        resMap.put("aaData", page.getRecords());
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


    private String dateRange;

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }



}
