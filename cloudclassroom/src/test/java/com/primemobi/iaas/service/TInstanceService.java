package com.primemobi.iaas.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.primemobi.iaas.entity.TInstance;
import com.primemobi.iaas.util.SSHUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TInstanceService {
	@Autowired
	ITInstanceService InstanceService;
	
	@Test
	    public void selectTest(){
		/*TInstance instance=new TInstance();
		List<TInstance> list = InstanceService.selectList(new EntityWrapper<TInstance>().eq("valid", "1"));
	        System.out.println(":"+list.size());
	        int count =0;
	        for (TInstance tInstance : list) {
	        	count+=tInstance.getCpu();
			}
	        System.out.println("cpu个数"+count);
	    }*/
		
	}
}
