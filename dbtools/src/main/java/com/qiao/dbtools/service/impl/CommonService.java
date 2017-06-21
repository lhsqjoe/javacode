package com.qiao.dbtools.service.impl;

import com.qiao.dbtools.dao.ICommonDao;
import com.qiao.dbtools.service.ICommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by qiao on 2017/6/21.
 */
@Service("commonService")
public class CommonService implements ICommonService{

    static Logger logger = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    ICommonDao commonDao;

    @Override
    public boolean insertData(Map<String, Object> params) {
        //TODO 在这里写自己的业务逻辑就可以了
        logger.debug("CommonService is execute");
        return  commonDao.insertData(params);
    }
}
