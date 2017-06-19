package com.primemobi.iaas.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.primemobi.iaas.entity.TUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 用户 Mapper 接口
 * </p>
 *
 * @author Liuzhanqiao
 * @since 2017-05-24
 */
public interface TUserMapper extends BaseMapper<TUser> {
    Integer insertAndGetId(TUser user);
    List<Map<String,Object>> selectUserPage(RowBounds rowBounds,Map<String,Object> params);

}