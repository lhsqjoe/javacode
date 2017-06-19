package com.primemobi.iaas.action;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.primemobi.iaas.entity.TConfig;
import com.primemobi.iaas.mapper.TConfigMapper;
import com.primemobi.iaas.service.ITConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by qiao on 17-6-5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SessionFactoryTest {

    @Resource
    SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    ITConfigService configService;


    @Test
    public void testSelect(){
        TConfigMapper configMapper = sqlSessionTemplate.getMapper(TConfigMapper.class);
        List<TConfig> list = configMapper.selectList(new EntityWrapper<TConfig>());
        for(TConfig config:list){
            System.out.println(config.getCname());
        }
    }

    @Test
    public void testSelectTwo(){
        System.out.println("testSelectTwo-----------------------------------------");
        List<TConfig> list = configService.selectList(new EntityWrapper<TConfig>());
        for(TConfig config:list){
            System.out.println(config.getCname());
        }
        System.out.println("testSelectTwo-----------------------------------------");
    }
}
