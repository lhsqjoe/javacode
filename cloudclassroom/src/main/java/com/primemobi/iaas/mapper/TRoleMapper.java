package com.primemobi.iaas.mapper;

import com.primemobi.iaas.entity.TRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Liuzhanqiao
 * @since 2017-05-24
 */
public interface TRoleMapper extends BaseMapper<TRole> {
    Integer insertAndGetId(TRole role);
}