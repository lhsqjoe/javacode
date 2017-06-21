package com.qiao.dbtools.dao.impl;

import com.qiao.dbtools.dao.ICommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;

/**
 * Created by qiao on 2017/6/21.
 */
@Repository("commonDao")
public class CommonDaoImpl implements ICommonDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertData(Map<String, Object> params) {
        //TODO 写自己的sql就可以了
        jdbcTemplate.update("insert INTO T_USER(USER_NAME) VALUE (?)",new Object[]{params.get("userName")});
        return false;
    }
}
