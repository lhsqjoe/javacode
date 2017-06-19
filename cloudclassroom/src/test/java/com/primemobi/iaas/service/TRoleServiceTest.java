package com.primemobi.iaas.service;

import com.primemobi.iaas.entity.TRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by qiao on 17-6-2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TRoleServiceTest {

    @Autowired
    ITRoleService roleService;

    @Test
    public void insertAndGetId(){
        TRole role = new TRole();
        role.setName("橘色");
        role.setCreateDatetime(new Date());
        role.setUpdateDatetime(new Date());
        roleService.insertAllColumn(role);
        System.out.println(role.getId()+"-------------------roleId");

    }
}
