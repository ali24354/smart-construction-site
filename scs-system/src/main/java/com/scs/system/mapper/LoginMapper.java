package com.scs.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scs.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<SysUser> {

}
