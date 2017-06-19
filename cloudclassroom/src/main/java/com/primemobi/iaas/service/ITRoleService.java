package com.primemobi.iaas.service;

import com.primemobi.iaas.entity.TRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Liuzhanqiao
 * @since 2017-05-24
 */
public interface ITRoleService extends IService<TRole> {
    public int insertAndGetId(TRole role);
}
