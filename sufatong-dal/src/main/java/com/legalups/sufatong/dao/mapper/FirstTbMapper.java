/**
 * 
 * Copyright From 2015, 微贷（杭州）金融信息服务有限公司. All rights reserved.
 * 
 * FirstTbMapper.java
 * 
 */
package com.legalups.sufatong.dao.mapper;

import com.legalups.sufatong.model.FirstTb;

/**
 * <p>
 * 表 : first_tb的 Mapper 类
 * 
 * @author 	$author$
 * @date 	2017年06月06日
 */
public interface FirstTbMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FirstTb record);

    int insertSelective(FirstTb record);

    FirstTb selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FirstTb record);

    int updateByPrimaryKey(FirstTb record);
}