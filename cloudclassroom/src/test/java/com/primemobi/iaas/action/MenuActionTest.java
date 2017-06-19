package com.primemobi.iaas.action;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.gson.Gson;
import com.primemobi.iaas.entity.TMenu;
import com.primemobi.iaas.service.ITMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qiao on 17-6-1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MenuActionTest {


    @Autowired
    private ITMenuService menuService;

    @Test
    public void selectTest(){
        List<Map<String, Object>> subMenuList=treeMenuList(-1);
        Gson gson = new Gson();
        System.out.println(gson.toJson(subMenuList));
    }


    /**
     * 获取某个父节点下面的所有子节点
     * @param pid
     * @return
     */
    List<Map<String, Object>> treeMenuList(int pid){
        List<TMenu> menuList = menuService.selectList(new EntityWrapper<TMenu>()
                .eq("pid", pid));
        List<Map<String, Object>> subMenuList = new ArrayList<Map<String, Object>>();
        if (menuList != null && menuList.size() > 0) {
            for (TMenu menu : menuList) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("text", menu.getName());
                params.put("id", String.valueOf(menu.getId()));
                params.put("pid", String.valueOf(menu.getPid()));
                params.put("nodes",treeMenuList(menu.getId()));
                subMenuList.add(params);
            }

        }
        return subMenuList;
    }

}
