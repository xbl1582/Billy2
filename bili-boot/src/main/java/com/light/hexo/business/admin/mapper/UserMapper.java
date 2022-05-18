package com.light.hexo.business.admin.mapper;

import com.light.hexo.business.admin.model.User;
import com.light.hexo.common.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: UserMapper
 * @ProjectName hexo-boot
 * @Description: 用户 Mapper
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}