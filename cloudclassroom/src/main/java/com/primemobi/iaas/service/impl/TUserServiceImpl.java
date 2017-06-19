package com.primemobi.iaas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.primemobi.iaas.entity.TUser;
import com.primemobi.iaas.mapper.TUserMapper;
import com.primemobi.iaas.service.ITUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Liuzhanqiao
 * @since 2017-05-24
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {
    @Autowired
    TUserMapper userMapper;

    @Transactional
    public Integer insertAndGetId(TUser user) {
        return userMapper.insertAndGetId(user);
    }


    public Page selectUserPage(Page page,Map<String, Object> params) {
        page.setRecords(userMapper.selectUserPage(page,params));
        return page;
    }
}
