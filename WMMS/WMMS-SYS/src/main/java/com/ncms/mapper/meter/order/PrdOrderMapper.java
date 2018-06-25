package com.ncms.mapper.meter.order;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.meter.order.PrdOrder;
import com.ncms.model.meter.order.PrdOrderVO;

/**
 * @date 2018-05-23 15:31:02
 */
public interface PrdOrderMapper extends MyMapper<PrdOrder> {

	public Page<PrdOrderVO> queryAllOrder(Map<String,Object> map);
}