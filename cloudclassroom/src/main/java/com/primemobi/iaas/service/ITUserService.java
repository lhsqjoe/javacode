package com.primemobi.iaas.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.primemobi.iaas.entity.TUser;
import com.baomidou.mybatisplus.service.IService;
import java.util.Map;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author Liuzhanqiao
 * @since 2017-05-24
 */
public interface ITUserService extends IService<TUser> {
	Integer insertAndGetId(TUser user);
	Page selectUserPage(Page page,Map<String,Object> params);
}
