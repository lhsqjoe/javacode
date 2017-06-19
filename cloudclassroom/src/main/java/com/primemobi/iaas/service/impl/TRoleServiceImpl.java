package com.primemobi.iaas.service.impl;

import com.primemobi.iaas.entity.TRole;
import com.primemobi.iaas.mapper.TRoleMapper;
import com.primemobi.iaas.service.ITRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Liuzhanqiao
 * @since 2017-05-24
 */
@Service
public class TRoleServiceImpl extends ServiceImpl<TRoleMapper, TRole> implements ITRoleService {
    @Autowired
    TRoleMapper roleMapper;


    @Transactional
    public int insertAndGetId(TRole role) {
        return roleMapper.insertAndGetId(role);
    }
}
