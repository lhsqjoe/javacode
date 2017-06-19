package com.primemobi.iaas.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.primemobi.iaas.entity.TUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;


/**
 * Created by qiao on 2017/5/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TUserServiceTest {

    @Autowired
    private ITUserService userService;



/*
    @Test
    public void selectTest() {
        TUser user = userService.selectById(1);
        System.out.println("用戶名:" + user.getUserName());
    }
*/


    @Test
    public void selectUserPage() {
//        Page page = userService.selectPage(new Page(1,10));
        Page page = userService.selectUserPage(new Page(1,10),new HashMap<String, Object>());
        Gson gson = new Gson();
        System.out.println(gson.toJson(page.getRecords()));
    }
}
