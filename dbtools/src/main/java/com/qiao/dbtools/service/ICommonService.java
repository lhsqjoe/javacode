package com.qiao.dbtools.service;

import java.util.Map;

/**
 * Created by qiao on 2017/6/21.
 */
public interface ICommonService {
    /**
     * 往数据库插入数据
     * @param params
     * @return
     */
    public boolean insertData(Map<String,Object> params);
}
