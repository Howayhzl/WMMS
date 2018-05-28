package com.ncms.mapper.meter.order;

import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.meter.order.PrdOrder;

/**
 * @date 2018-05-23 15:31:02
 */
public interface PrdOrderMapper extends MyMapper<PrdOrder> {

	public Page<PrdOrder> queryAllChangeOrder(Map<String,Object> map);
}